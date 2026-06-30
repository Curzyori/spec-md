package com.curzy.specmd.ui.screen.home

import com.curzy.specmd.domain.model.DeviceSpec

data class HomeState(
    val isLoading: Boolean = true,
    val deviceSpec: DeviceSpec? = null,
    val markdownOutput: String = "",
    val showSettingsSheet: Boolean = false,
    val showPreview: Boolean = false,
    val toastMessage: String? = null,
    val isCopied: Boolean = false,
    val donateUrl: String = "https://donate.curzy.dev/",
    val isConfigLoaded: Boolean = false
)

sealed class HomeEvent {
    data object LoadSpecs : HomeEvent()
    data object CopyToClipboard : HomeEvent()
    data object TogglePreview : HomeEvent()
    data object DismissToast : HomeEvent()
    data object ShowExportSheet : HomeEvent()
    data object DismissExportSheet : HomeEvent()
}
