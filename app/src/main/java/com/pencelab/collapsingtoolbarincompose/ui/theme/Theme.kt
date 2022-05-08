package com.pencelab.collapsingtoolbarincompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DeepSapphire,
    secondary = MineShaft,
    background = Black,
    surface = DeepSapphire,
    onPrimary = White,
    onSecondary = White,
    onSurface = White,
    onBackground = White
)

private val LightColorPalette = lightColors(
    primary = Denim,
    secondary = Scorpion,
    background = White,
    surface = Denim,
    onPrimary = White,
    onSecondary = White,
    onSurface = White,
    onBackground = Black
)

@Composable
fun CollapsingToolbarInComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
