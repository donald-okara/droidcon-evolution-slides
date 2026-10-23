package ke.don.demos.droidcon.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ke.don.domain.values.Values
import kotlin.math.roundToInt

/**
 * Which way a figure moved. The arrow points the same way.
 */
enum class StatDirection { Increase, Decrease }

/**
 * A big-number tile for a before-and-after figure, such as a drop in startup time or size.
 *
 * The number counts up from 0 to [value] when the tile first composes. For [StatDirection.Decrease] the
 * number also shrinks while it counts, ending at a scale of `1 - value / 200` (so 28 ends at 0.86).
 * The arrow fades and slides in after the count starts.
 *
 * @param value The figure, in the same units as [unit] (for example 7.4 for "7.4%").
 * @param unit The suffix shown after the number, for example "%".
 * @param label A short description of the figure.
 * @param direction Whether the figure went up or down. Sets the arrow direction and the number's shrink.
 * @param improvement Whether the change is good. Green when true, `colorScheme.error` when false.
 * @param sha A commit SHA shown under the label as a [CommitTag]. Takes priority over [source].
 * @param source Plain text shown under the label when there is no [sha], for example a person's name and date.
 * @param decimals The number of decimal places shown while counting and at the end.
 */
@Composable
fun StatTile(
    value: Float,
    unit: String,
    label: String,
    direction: StatDirection,
    modifier: Modifier = Modifier,
    improvement: Boolean = true,
    sha: String? = null,
    source: String? = null,
    decimals: Int = 0
) {
    val progress = remember { Animatable(0f) }
    val arrowIn = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(1f, tween(durationMillis = 1200, easing = FastOutSlowInEasing))
    }
    LaunchedEffect(Unit) {
        arrowIn.animateTo(1f, tween(durationMillis = 600, delayMillis = 600, easing = FastOutSlowInEasing))
    }

    val p = progress.value
    val scheme = MaterialTheme.colorScheme
    val isDark = scheme.background.luminance() < 0.5f
    val tint: Color = when {
        !improvement -> scheme.error
        isDark -> scheme.primary
        else -> scheme.secondary
    }

    val numberScale = if (direction == StatDirection.Decrease) {
        1f - p * (value / 100f) * 0.5f
    } else {
        1f
    }
    val arrowSign = if (direction == StatDirection.Increase) 1f else -1f
    val arrowIcon = if (direction == StatDirection.Increase) {
        Icons.Filled.ArrowUpward
    } else {
        Icons.Filled.ArrowDownward
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(Values.Dimens.smallPadding))
            .background(scheme.surfaceContainer)
            .padding(Values.Dimens.mediumPadding),
        verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = formatValue(value * p, decimals) + unit,
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = tint,
                modifier = Modifier.graphicsLayer {
                    scaleX = numberScale
                    scaleY = numberScale
                    transformOrigin = TransformOrigin.Center
                }
            )
            Spacer(modifier = Modifier.size(Values.Dimens.smallPadding))
            Icon(
                imageVector = arrowIcon,
                contentDescription = if (direction == StatDirection.Increase) "Increase" else "Decrease",
                tint = tint,
                modifier = Modifier
                    .size(40.dp)
                    .graphicsLayer {
                        alpha = arrowIn.value
                        translationY = (1f - arrowIn.value) * 24.dp.toPx() * arrowSign
                    }
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = scheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        when {
            sha != null -> CommitTag(sha)
            source != null -> Text(
                text = source,
                style = MaterialTheme.typography.labelMedium,
                color = scheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Formats [value] with [decimals] places using string padding, so it works in common code without `String.format`.
 */
private fun formatValue(value: Float, decimals: Int): String {
    var factor = 1
    repeat(decimals) { factor *= 10 }
    val scaled = (value * factor).roundToInt()
    val whole = scaled / factor
    if (decimals == 0) return whole.toString()
    val fraction = (scaled % factor).toString().padStart(decimals, '0')
    return "$whole.$fraction"
}
