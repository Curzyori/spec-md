package com.curzy.specmd.ui.screen.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curzy.specmd.R
import com.curzy.specmd.domain.repository.SpecRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val specRepository: SpecRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val clipboardManager by lazy {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    init {
        onEvent(HomeEvent.LoadSpecs)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadSpecs -> loadSpecs()
            is HomeEvent.CopyToClipboard -> copyToClipboard()
            is HomeEvent.TogglePreview -> togglePreview()
            is HomeEvent.DismissToast -> dismissToast()
            is HomeEvent.ShowExportSheet -> {
                _state.update { it.copy(showSettingsSheet = true) }
                fetchDonateConfig()
            }
            is HomeEvent.DismissExportSheet -> _state.update { it.copy(showSettingsSheet = false) }
        }
    }

    private fun fetchDonateConfig() {
        if (_state.value.isConfigLoaded) return

        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            try {
                val url = java.net.URL("https://raw.githubusercontent.com/Curzyori/spec-md/main/config/donate.json")
                val connection = url.openConnection() as java.net.HttpURLConnection
                connection.connectTimeout = 5000
                connection.readTimeout = 5000
                
                if (connection.responseCode == 200) {
                    val jsonText = connection.inputStream.bufferedReader().use { it.readText() }
                    val jsonObject = org.json.JSONObject(jsonText)
                    
                    val donate = jsonObject.optString("donate_url", _state.value.donateUrl)

                    _state.update {
                        it.copy(
                            donateUrl = donate,
                            isConfigLoaded = true
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isConfigLoaded = true) }
            }
        }
    }

    private fun loadSpecs() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val specs = specRepository.getDeviceSpecs()
            val markdown = specRepository.generateMarkdown(specs)

            _state.update {
                it.copy(
                    isLoading = false,
                    deviceSpec = specs,
                    markdownOutput = markdown
                )
            }
        }
    }

    private fun copyToClipboard() {
        val markdown = _state.value.markdownOutput
        if (markdown.isBlank()) return

        try {
            val clip = ClipData.newPlainText("Device Specs", markdown)
            clipboardManager.setPrimaryClip(clip)

            _state.update {
                it.copy(
                    toastMessage = context.getString(R.string.toast_copied),
                    isCopied = true
                )
            }

            viewModelScope.launch {
                delay(1500)
                _state.update { it.copy(isCopied = false) }
            }
        } catch (e: Exception) {
            _state.update { it.copy(toastMessage = context.getString(R.string.toast_copy_failed)) }
        }

        viewModelScope.launch {
            delay(2000)
            dismissToast()
        }
    }

    fun shareContent() {
        val markdown = _state.value.markdownOutput
        if (markdown.isBlank()) return

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, markdown)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, context.getString(R.string.export_share))
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(shareIntent)
    }

    fun saveToFile(): Boolean {
        val markdown = _state.value.markdownOutput
        if (markdown.isBlank()) return false

        return try {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val specMdDir = File(downloadsDir, "SpecMD")
            if (!specMdDir.exists()) specMdDir.mkdirs()

            val timestamp = System.currentTimeMillis()
            val file = File(specMdDir, "device-specs-$timestamp.md")
            FileWriter(file).use { it.write(markdown) }

            _state.update { it.copy(toastMessage = "${context.getString(R.string.toast_saved)} ${file.name}") }
            viewModelScope.launch {
                delay(2000)
                dismissToast()
            }
            true
        } catch (e: Exception) {
            _state.update { it.copy(toastMessage = context.getString(R.string.toast_error)) }
            viewModelScope.launch {
                delay(2000)
                dismissToast()
            }
            false
        }
    }

    private fun togglePreview() {
        _state.update { it.copy(showPreview = !it.showPreview) }
    }

    private fun dismissToast() {
        _state.update { it.copy(toastMessage = null) }
    }
}
