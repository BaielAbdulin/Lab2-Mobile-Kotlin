package com.example.shoppinglist.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF777777),
    secondary = Color(0xFF03DAC5),
    background = Color(0xFF000000),
    surface = Color(0xFF000000)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF777777),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFE4E4E4),
    surface = Color(0xFFFFFFFF)
)

@Composable
fun ShoppingListTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}