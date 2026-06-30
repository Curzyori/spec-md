package com.curzy.specmd.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val SpecMDDarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = TextOnAccent,
    primaryContainer = BgOverlay,
    onPrimaryContainer = PrimarySoft,
    secondary = Primary,
    onSecondary = TextOnAccent,
    background = BgBase,
    onBackground = TextPrimary,
    surface = BgElevated,
    onSurface = TextPrimary,
    surfaceVariant = BgOverlay,
    onSurfaceVariant = TextSecondary,
    outline = Border,
    outlineVariant = BorderHover,
    error = Error,
    onError = TextOnAccent
)

@Composable
fun SpecMDTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = SpecMDDarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = BgBase.toArgb()
            window.navigationBarColor = BgBase.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
