package ke.don.demos.droidcon.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import ke.don.design.theme.Pre2026Palette
import ke.don.domain.values.Values
import kotlin.math.roundToInt

/** The pre-2026 palette, as named swatches for [SwatchRow]. */
internal fun pre2026Swatches(): List<Pair<String, Color>> = listOf(
    "ChaiBlue" to Pre2026Palette.ChaiBlue,
    "ChaiRed" to Pre2026Palette.ChaiRed,
    "ChaiTeal" to Pre2026Palette.ChaiTeal,
    "ChaiCoal" to Pre2026Palette.ChaiCoal,
    "ChaiLightGrey" to Pre2026Palette.ChaiLightGrey
)

/** The current colour scheme's roles, as named swatches for [SwatchRow]. */
@Composable
internal fun currentSchemeSwatches(): List<Pair<String, Color>> {
    val scheme = MaterialTheme.colorScheme
    return listOf(
        "primary" to scheme.primary,
        "secondary" to scheme.secondary,
        "secondaryContainer" to scheme.secondaryContainer,
        "tertiary" to scheme.tertiary,
        "background" to scheme.background
    )
}

/**
 * A labelled row of colour swatches. Each swatch has its name and hex value under it.
 */
@Composable
internal fun SwatchRow(label: String, swatches: List<Pair<String, Color>>) {
    val scheme = MaterialTheme.colorScheme
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = scheme.primary
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
        ) {
            swatches.forEach { (name, color) ->
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .background(color, RoundedCornerShape(Values.Dimens.smallPadding))
                            .border(1.dp, scheme.outline, RoundedCornerShape(Values.Dimens.smallPadding))
                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.labelLarge,
                        color = scheme.onSurface
                    )
                    Text(
                        text = hexOf(color),
                        style = MaterialTheme.typography.labelMedium,
                        fontFamily = FontFamily.Monospace,
                        color = scheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

/** Formats an opaque colour as `#RRGGBB`, for the swatch labels. Uses no `String.format`, since this is common code. */
private fun hexOf(color: Color): String {
    fun channel(value: Float): String = (value * 255f).roundToInt().toString(16).padStart(2, '0')
    return "#" + (channel(color.red) + channel(color.green) + channel(color.blue)).uppercase()
}
