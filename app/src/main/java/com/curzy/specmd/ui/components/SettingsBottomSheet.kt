package com.curzy.specmd.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.curzy.specmd.R
import com.curzy.specmd.ui.theme.BgElevated
import com.curzy.specmd.ui.theme.Border
import com.curzy.specmd.ui.theme.Primary
import com.curzy.specmd.ui.theme.TextOnAccent
import com.curzy.specmd.ui.theme.TextSecondary
import java.util.Locale

private const val PREFS_NAME = "specmd_prefs"
private const val KEY_LANGUAGE = "language"

@Composable
fun SettingsBottomSheet(
    donateUrl: String,
    onCopy: () -> Unit,
    onShare: () -> Unit,
    onSave: () -> Unit,
    onPreview: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val currentLang = prefs.getString(KEY_LANGUAGE, "id") ?: "id"
    var selectedLang by remember { mutableStateOf(currentLang) }

    val listState = rememberLazyListState()

    // Scroll to top when sheet opens
    LaunchedEffect(Unit) {
        listState.scrollToItem(0)
    }

    val isScrolledToTop by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0 }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp)
    ) {
        item {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = Primary
                )
                Text(
                    text = stringResource(id = R.string.settings),
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Border, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Language Section
        item {
            SettingsSection(title = stringResource(id = R.string.settings_language)) {
                LanguageOption(
                    flag = "🇮🇩",
                    label = "Bahasa Indonesia",
                    selected = selectedLang == "id",
                    onClick = {
                        if (selectedLang != "id") {
                            selectedLang = "id"
                            setLocale(context, "id")
                        }
                    }
                )
                LanguageOption(
                    flag = "🇺🇸",
                    label = "English",
                    selected = selectedLang == "en",
                    onClick = {
                        if (selectedLang != "en") {
                            selectedLang = "en"
                            setLocale(context, "en")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Border, thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Export Section
        item {
            SettingsSection(title = stringResource(id = R.string.export_title)) {
                ExportOptionItem(
                    icon = Icons.Default.ContentCopy,
                    title = stringResource(id = R.string.export_copy),
                    subtitle = stringResource(id = R.string.export_copy_subtitle),
                    onClick = onCopy
                )
                ExportOptionItem(
                    icon = Icons.Default.SaveAlt,
                    title = stringResource(id = R.string.export_save),
                    subtitle = stringResource(id = R.string.export_save_subtitle),
                    onClick = onSave
                )
                ExportOptionItem(
                    icon = Icons.AutoMirrored.Filled.OpenInNew,
                    title = stringResource(id = R.string.export_share),
                    subtitle = stringResource(id = R.string.export_share_subtitle),
                    onClick = onShare
                )
                ExportOptionItem(
                    icon = Icons.Default.Info,
                    title = stringResource(id = R.string.export_preview),
                    subtitle = stringResource(id = R.string.export_preview_subtitle),
                    onClick = onPreview
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Border, thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Support Section
        item {
            SettingsSection(title = stringResource(id = R.string.settings_support)) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(donateUrl)
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary,
                        contentColor = TextOnAccent
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.support_donate_button),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Border, thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // About Section
        item {
            SettingsSection(title = stringResource(id = R.string.settings_about)) {
                SettingsItem(
                    icon = Icons.Default.Code,
                    title = stringResource(id = R.string.about_github),
                    subtitle = "github.com/Curzyori/spec-md",
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(context.getString(R.string.github_url))
                        }
                        context.startActivity(intent)
                    }
                )

                SettingsItem(
                    icon = Icons.Default.Web,
                    title = stringResource(id = R.string.about_website),
                    subtitle = stringResource(id = R.string.about_website_url),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("https://spec-md.curzy.dev/")
                        }
                        context.startActivity(intent)
                    }
                )

                SettingsItem(
                    icon = Icons.Default.Info,
                    title = stringResource(id = R.string.about_version),
                    subtitle = "1.0.0",
                    onClick = { }
                )
            }
        }
    }
}

private fun setLocale(context: Context, languageCode: String) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    prefs.edit().putString(KEY_LANGUAGE, languageCode).apply()

    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = context.resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)

    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    // Recreate the activity to apply changes
    if (context is android.app.Activity) {
        context.recreate()
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = TextSecondary,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        content()
    }
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
        }
    }
}

@Composable
private fun LanguageOption(
    flag: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) BgElevated else androidx.compose.ui.graphics.Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = flag, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = if (selected) "●" else "○",
            color = if (selected) Primary else TextSecondary
        )
    }
}

@Composable
private fun ExportOptionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
        }
    }
}
