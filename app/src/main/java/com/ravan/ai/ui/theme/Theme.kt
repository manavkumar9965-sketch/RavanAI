package com.ravan.ai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = RavanRed,
    onPrimary = RavanBlack,
    primaryContainer = RavanRedDeep,
    onPrimaryContainer = RavanWhite,
    secondary = RavanRedDeep,
    onSecondary = RavanWhite,
    background = RavanBlack,
    onBackground = RavanWhite,
    surface = RavanSurface,
    onSurface = RavanWhite,
    surfaceVariant = RavanSurface,
    onSurfaceVariant = RavanGrey
)

private val LightColors = lightColorScheme(
    primary = RavanRedDeep,
    onPrimary = RavanWhite,
    background = RavanWhite,
    onBackground = RavanBlack,
    surface = RavanWhite,
    onSurface = RavanBlack
)

@Composable
fun RavanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
