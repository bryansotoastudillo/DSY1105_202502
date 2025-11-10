package cl.duoc.dsy1105.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DuocColorScheme = lightColorScheme(
    primary = Color(0xFF003B5C),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFFFC20E),
    onSecondary = Color(0xFF000000),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF)
)

@Composable
fun DuocDSY1105Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DuocColorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
