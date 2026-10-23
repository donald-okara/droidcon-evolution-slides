package ke.don.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Colours no Material role holds. Source: droidconKeKotlin DS/colors/ChaiColors.kt (section 1.3 of docs/talk/design-inventory.md).
// Read MaterialTheme.colorScheme first; reach for these only for the hero panel and fixed logo backings.

@Immutable
data class ChaiColors(
    val heroContainerColor: Color,
    /** Neon on the blue panel, for headline and display text only (4.1:1). */
    val heroContentColor: Color,
    /** White on the blue panel, for anything smaller than display type. */
    val heroOnContainerColor: Color,
    /** Fixed light backing for content (e.g. a dark logo) that must read against either theme. */
    val contrastLightSurface: Color,
    /** Fixed dark backing for content (e.g. a light logo) that must read against either theme. */
    val contrastDarkSurface: Color,
)

// Same values in both themes, as in the source.
val lightChaiColors = ChaiColors(
    heroContainerColor = Color(0xFF0055FF),
    heroContentColor = Color(0xFF00FF4F),
    heroOnContainerColor = Color(0xFFFFFFFF),
    contrastLightSurface = Color(0xFFF5F5F5),
    contrastDarkSurface = Color(0xFF191D1D),
)

val darkChaiColors = lightChaiColors

val LocalChaiColors = staticCompositionLocalOf { lightChaiColors }

// The cube hero's colours, the same in both themes (section 1.4 of docs/talk/design-inventory.md).
object CubeColors {
    val Gap = Color(0xFF000000)
    val TileShadow = Color(0xFF0B1117)
    val TileLit = Color(0xFF26343F)
    val Sheen = Color(0xFFFFFFFF)
    val Edge = Color(0xFFE5E7EB)
    val Stage = Color(0xFF000000)
    val StageGlow = Color(0xFF0055FF)
    val StageTitleGradient = listOf(Color(0xFF83C6FF), Color(0xFFA66BFF), Color(0xFFFF57E9))
    val StageText = Color(0xFFE5E7EB)
}

// Pre-2026 palette, kept for reference (section 4 of docs/talk/design-inventory.md). Not used by AppTheme.
object Pre2026Palette {
    val ChaiBlue = Color(0xFF000CEB)
    val ChaiRed = Color(0xFFFF6E4D)
    val ChaiTeal = Color(0xFF00E2C3)
    val ChaiTeal90 = Color(0xFF7DE1C3)
    val ChaiWhite = Color(0xFFFFFFFF)
    val ChaiBlack = Color(0xFF000000)
    val ChaiLightGrey90 = Color(0xFFF6F6F8)
    val ChaiLightGrey = Color(0xFFF5F5F5)
    val ChaiGrey = Color(0xFFB1B1B1)
    val ChaiSmokeyGrey = Color(0xFF707070)
    val ChaiDarkGrey = Color(0xFF5A5A5A)
    val ChaiCoal = Color(0xFF191D1D)
    val ChaiGrey90 = Color(0xFF20201E)
    val ChaiSubtleGrey = Color(0xFF383836)
}
