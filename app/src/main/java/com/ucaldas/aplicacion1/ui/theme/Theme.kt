package com.ucaldas.aplicacion1.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EsquemaClaro = lightColorScheme(
    primary = AzulHeroe,
    onPrimary = Color.White,
    primaryContainer = AzulHeroeClaro,
    onPrimaryContainer = TextoOscuro,
    secondary = RojoHeroe,
    secondaryContainer = RojoHeroeClaro,
    onSecondaryContainer = TextoOscuro,
    background = FondoClaro,
    surface = FondoClaro,
    onBackground = TextoOscuro,
    onSurface = TextoOscuro
)

private val EsquemaOscuro = darkColorScheme(
    primary = AzulHeroeClaro,
    onPrimary = Color(0xFF0B2B7A),
    secondaryContainer = RojoHeroe,
    onSecondaryContainer = Color.White,
    background = FondoOscuro,
    surface = FondoOscuro,
    onBackground = TextoClaro,
    onSurface = TextoClaro
)

/**
 * Tema global de la app: colores y tipografia.
 * Se aplica una unica vez en MainActivity, dentro de setContent { }.
 */
@Composable
fun Aplicacion1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) EsquemaOscuro else EsquemaClaro,
        typography = Typography,
        content = content
    )
}
