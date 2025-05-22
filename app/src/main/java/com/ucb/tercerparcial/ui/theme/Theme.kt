package com.ucb.tercerparcial.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF0D47A1),          // Azul oscuro
    secondary = Color(0xFF1976D2),        // Azul medio
    tertiary = Color(0xFF90CAF9)          // Azul claro
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1565C0),          // Azul principal
    secondary = Color(0xFF42A5F5),        // Azul secundario
    tertiary = Color(0xFF90CAF9)          // Azul claro

    // Puedes descomentar y personalizar más si quieres:
    // background = Color(0xFFF5F5F5),
    // surface = Color.White,
    // onPrimary = Color.White,
    // onSecondary = Color.Black,
    // onTertiary = Color.Black,
    // onBackground = Color.Black,
    // onSurface = Color.Black,
)

@Composable
fun TercerParcialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
