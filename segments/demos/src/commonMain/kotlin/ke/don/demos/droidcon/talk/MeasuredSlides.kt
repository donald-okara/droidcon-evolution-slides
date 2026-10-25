package ke.don.demos.droidcon.talk

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ke.don.demos.droidcon.common.BarChart
import ke.don.demos.droidcon.common.BulletList
import ke.don.demos.droidcon.common.ChartPoint
import ke.don.demos.droidcon.common.SlideImage
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.StatDirection
import ke.don.demos.droidcon.common.StatTile
import ke.don.demos.droidcon.common.SwatchRow
import ke.don.demos.droidcon.common.currentSchemeSwatches
import ke.don.demos.droidcon.common.pre2026Swatches
import ke.don.demos.droidcon.common.popIn
import ke.don.domain.values.Values
import ke.don.resources.Resources
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

private const val PHONE_ASPECT = 9f / 19.5f
private const val MODULE_COUNT_FROM = 7
private const val MODULE_COUNT_TO = 20
private const val MODULE_TICK_MILLIS = 1600
private const val TWO_READERS_QUOTE_DELAY_MILLIS = 300
private const val MODULE_TICK_DELAY_MILLIS = 400L

/**
 * S18. Written for two readers. The same file, `AGENTS.md`, briefs a new contributor and a coding agent.
 * The quote fades in, then the improvement plan that deletes what is done is named.
 */
@Composable
fun TwoReadersSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Written for two readers", kicker = "Act 6")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                Text(
                    text = "2026-08-13: AGENTS.md",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "\"Working notes for AI agents and new contributors.\"",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.popIn(delayMillis = TWO_READERS_QUOTE_DELAY_MILLIS)
                )
                BulletList(
                    items = listOf(
                        "A 3,500-line improvement plan that deletes what is done",
                        "The fix for quiet years: write down what you know",
                        "This afternoon we test whether it works for a new contributor"
                    )
                )
            }
            SlideImage(
                resource = Resources.Images.TOP_OF_AGENTS,
                contentDescription = "Top of AGENTS.md",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * S19. Seven modules to twenty. The module count ticks from 7 to 20 (D8), then three rules follow.
 * The rules matter more than the count.
 */
@Composable
fun ModulesSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Seven modules to twenty", kicker = "Act 6")
        Row(verticalAlignment = Alignment.CenterVertically) {
            ModuleCountTicker()
            Spacer(modifier = Modifier.width(Values.Dimens.mediumPadding))
            Text(
                text = "modules: 7 on 1 Aug, 17 on 11 Sep, 20 on 22 Oct",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        BulletList(
            items = listOf(
                "Features never depend on features",
                "core:model has no Android in it",
                "A feature's tests never reach into another feature's fakes, which is why core:testing exists"
            )
        )
    }
}

/**
 * S20. Measured, not asserted. Three stat tiles for the drops, then the D7 bar chart of the four startup modes.
 * The tiles count up and shrink (built into [StatTile] for decreases).
 */
@Composable
fun MeasuredSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Measured, not asserted", kicker = "Act 6")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatTile(
                value = 22f,
                unit = "%",
                label = "Cold start, 1,335 to 1,047 ms",
                direction = StatDirection.Decrease,
                source = "docs/performance.md",
                modifier = Modifier.weight(1f)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                Text(
                    text = "Release APK 28% smaller, 6.4 to 4.6 MB",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Download size 7.4% smaller, enforced in CI",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                Text(
                    text = "Cold start by mode, median ms (D7)",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                BarChart(
                    points = listOf(
                        ChartPoint(label = "No compilation", value = 1334.9f),
                        ChartPoint(label = "Warmed, no profile", value = 953.8f),
                        ChartPoint(label = "Baseline profile", value = 1046.7f, highlight = true),
                        ChartPoint(label = "Full AOT", value = 1228.2f)
                    ),
                    valueSuffix = " ms"
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                Text(
                    text = "Full AOT was slower: 1,228 ms against 1,047 ms with the baseline profile.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "The doc keeps the experiments that did not help, like this one.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * S21. A new look. The then and now palettes reuse the swatch rows from `LaunchDesignSlide`, beside the 2026
 * sessions screen at phone aspect.
 */
@Composable
fun NewLookSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "A new look", kicker = "Act 6")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                SwatchRow(label = "Then: pre-2026 palette", swatches = pre2026Swatches())
                Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
                SwatchRow(label = "Now: 2026 scheme", swatches = currentSchemeSwatches())
                Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
                BulletList(
                    items = listOf(
                        "Material 3 Expressive and the 2026 droidcon rebrand",
                        "Phones, foldables, tablets and desktop windows",
                        "Shared-element transitions and redesigned speakers",
                        "WCAG AA contrast tested for every colour pair"
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Credit: Joel Kanyi for the shared elements, mutukuian for the speakers redesign",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight().popIn(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)
            ) {
                SlideImage(
                    resource = Resources.Images.SESSIONS_2026,
                    contentDescription = "Sessions screen, 2026",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(PHONE_ASPECT, matchHeightConstraintsFirst = true)
                )
                Text(
                    text = "Sessions, 2026",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * The module count, counting up from [MODULE_COUNT_FROM] to [MODULE_COUNT_TO] once it composes.
 * It is linear, so it reads as a tick per module.
 */
@Composable
private fun ModuleCountTicker() {
    val count = remember { Animatable(MODULE_COUNT_FROM.toFloat()) }
    LaunchedEffect(Unit) {
        delay(MODULE_TICK_DELAY_MILLIS)
        count.animateTo(
            targetValue = MODULE_COUNT_TO.toFloat(),
            animationSpec = tween(durationMillis = MODULE_TICK_MILLIS, easing = LinearEasing)
        )
    }
    Text(
        text = count.value.roundToInt().toString(),
        style = MaterialTheme.typography.displayLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}
