package ke.don.demos.droidcon.talk

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ke.don.demos.droidcon.common.BulletList
import ke.don.demos.droidcon.common.CalloutCard
import ke.don.demos.droidcon.common.ChartPoint
import ke.don.demos.droidcon.common.Confetti
import ke.don.demos.droidcon.common.ColumnChart
import ke.don.demos.droidcon.common.MediaPlaceholder
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.popIn
import ke.don.domain.values.Values
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

private const val ONE_REPO_COUNT_UP_MILLIS = 900
private const val CHAI_SWATCH_DELAY_MILLIS = 350
private const val PEAK_SETTLE_MILLIS = 900L
private const val KMP_CALLOUT_DELAY_MILLIS = 800
private const val NAV_CHIP_DELAY_MILLIS = 300
private val ChaiSwatchSize = 56.dp

/**
 * S11. Compose arrives. The 2022 app was built on alpha Material 3, shipped to the Play Store in November,
 * and switched sign-in off the day before the conference. The chart shows the 2022 commits per month.
 */
@Composable
fun Y2022ComposeSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "2022: Compose arrives", kicker = "Act 4")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                Text(
                    text = "622 commits in 5 months · about two dozen volunteers",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                BulletList(
                    items = listOf(
                        "Jetpack Compose · Material 3 (alpha) · Hilt · Ktor · Room + Paging",
                        "Version catalog · spotless · fastlane to the Play Store",
                        "2022-11-15: feat: Disable auth. The conference opened 2022-11-16"
                    )
                )
            }
            ColumnChart(
                points = listOf(
                    ChartPoint(label = "Jul", value = 212f, highlight = true),
                    ChartPoint(label = "Aug", value = 115f),
                    ChartPoint(label = "Sep", value = 94f),
                    ChartPoint(label = "Oct", value = 121f),
                    ChartPoint(label = "Nov", value = 80f)
                ),
                modifier = Modifier.weight(1f),
                caption = "Commits per month, droidconKE2022Android (D2)"
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: droidconKE2022Android gradle/libs.versions.toml; commit \"Deploy to playstore\" (2022-11-17)",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * S12. Standing on its own. The team had its own designs and named its design system chai, the Swahili word
 * for tea. The swatches spill in one after another as the chai palette.
 */
@Composable
fun ChaiSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Standing on its own: chai", kicker = "Act 4")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                BulletList(
                    items = listOf(
                        "Its own designs, light and dark, in Adobe XD",
                        "Its own design system: chai. \"Chai is swahili word for tea.\"",
                        "Born in Aug 2022 · its own repo in Mar 2023 · still com.droidconke.chai"
                    )
                )
                ChaiSwatches()
                Text(
                    text = "2018: I/O's screens  →  2020: its own structure  →  2022: its own look",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            MediaPlaceholder(
                label = "chai cover (droidconKE/iconPack), next to the S07 I/O screenshot",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: the Designs section of the droidconKE2022Android README; the chai README",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * The four theme colours as swatches. Each one pops in after the one before it.
 */
@Composable
private fun ChaiSwatches(modifier: Modifier = Modifier) {
    val scheme = MaterialTheme.colorScheme
    val colours = listOf(scheme.primary, scheme.secondary, scheme.tertiary, scheme.primaryContainer)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        colours.forEachIndexed { index, colour ->
            Box(
                modifier = Modifier
                    .popIn(delayMillis = index * CHAI_SWATCH_DELAY_MILLIS)
                    .size(ChaiSwatchSize)
                    .clip(RoundedCornerShape(Values.Dimens.tinyPadding))
                    .background(colour)
            )
        }
    }
}

/**
 * S13. One repo to keep. The day-three commit carried 280 files from the 2022 app into droidconKeKotlin.
 * The file count counts up from zero.
 */
@Composable
fun OneRepoSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "2023: one repo to keep", kicker = "Act 5")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                OneRepoCountUp(target = CARRIED_FILES)
                Text(
                    text = "files carried over on day three, from droidconKE2022Android",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                BulletList(
                    items = listOf(
                        "droidconKeKotlin, with no year in its name",
                        "15,888 lines · 173 of 177 Kotlin files kept their path",
                        "136 byte-for-byte identical: not a rewrite, a carry-forward"
                    )
                )
            }
            MediaPlaceholder(
                label = "Terminal: git show --stat f3b81067 | tail -1",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: git show --stat f3b81067; file-by-file comparison with droidconKE2022Android",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/** Files carried into droidconKeKotlin on day three, from the commit `f3b81067`. */
private const val CARRIED_FILES = 280

/**
 * The file count as a large figure. It counts up from zero when the slide first composes.
 *
 * @param target The final count.
 */
@Composable
private fun OneRepoCountUp(target: Int) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        progress.animateTo(1f, tween(durationMillis = ONE_REPO_COUNT_UP_MILLIS, easing = FastOutSlowInEasing))
    }
    Text(
        text = (target * progress.value).roundToInt().toString(),
        style = MaterialTheme.typography.displayLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

/**
 * S14. The 2023 community peak. The distinct author count grows as a column chart, and when the chart
 * settles on the 2023 peak, confetti bursts over the large figure.
 */
@Composable
fun CommunityPeakSlide() {
    var peakReached by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(PEAK_SETTLE_MILLIS)
        peakReached = true
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "2023: the community peak", kicker = "Act 5")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    Text(
                        text = "41",
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Confetti(trigger = peakReached, modifier = Modifier.fillMaxSize())
                }
                Text(
                    text = "people committed in 2023, about 41 authors (bots left out)",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Hannah Olukoye · Peter Chege · Kenneth Mathari · robert-nganga",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            ColumnChart(
                points = listOf(
                    ChartPoint(label = "2023", value = 41f, highlight = true),
                    ChartPoint(label = "2024", value = 8f),
                    ChartPoint(label = "2025", value = 9f),
                    ChartPoint(label = "2026", value = 5f)
                ),
                modifier = Modifier.weight(1f),
                caption = "Distinct author names per year, bots excluded; approximate (D5)"
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: git shortlog for 2023; 326 commits in 2023",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * S15. The quiet year. The Droidcon 2024 change was three lines, and the "3" pops in over the diff note.
 */
@Composable
fun QuietYearSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "2024: the quiet year", kicker = "Act 5")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                Text(
                    text = "3",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.popIn()
                )
                Text(
                    text = "lines changed in Feature: Droidcon 2024 (#305), the event slug",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                BulletList(
                    items = listOf(
                        "55 commits in 2024",
                        "K2 and the Compose compiler plugin · coverage on CI"
                    )
                )
            }
            MediaPlaceholder(
                label = "Diff of #305, full screen: 3 files, 3 lines",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: git show --stat of #305; 2024 commit count from the git history",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * S16. The Kotlin Kenya corner: the KMP thread. The module count from D8 is a column chart, with the two
 * KMP years highlighted, and the callout card pops in beside it.
 */
@Composable
fun KmpThreadSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The KMP thread", kicker = "Act 5 · Kotlin Kenya corner")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            ColumnChart(
                points = listOf(
                    ChartPoint(label = "Dec 2023", value = 7f),
                    ChartPoint(label = "Dec 2024", value = 11f, highlight = true),
                    ChartPoint(label = "Jul 2025", value = 14f, highlight = true),
                    ChartPoint(label = "Aug 2026", value = 7f),
                    ChartPoint(label = "Oct 2026", value = 20f)
                ),
                modifier = Modifier.weight(1f),
                caption = "Gradle modules over time (D8). Highlighted: the KMP stretch."
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                BulletList(
                    items = listOf(
                        "May 2024: KMM set up",
                        "Jun 2025: Compose Multiplatform + iOS",
                        "Oct 2025: removed, moved to droidconKeKMP"
                    )
                )
                CalloutCard(
                    title = "Kotlin Kenya corner",
                    modifier = Modifier.popIn(delayMillis = KMP_CALLOUT_DELAY_MILLIS)
                ) {
                    Text(
                        text = "Today core:model is JVM-only. A third try is a port, not a rewrite.",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: git log; docs/IMPROVEMENT_PLAN.md §17.2 question 10",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * S17. Late 2025: Navigation 3 and multiple back stacks. The status chips pop in, and the screen recording
 * goes where the tab switching clip will play.
 */
@Composable
fun Navigation3Slide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Late 2025: Navigation 3", kicker = "Act 5")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                BulletList(
                    items = listOf(
                        "Dec 2025: Navigation 3, with multiple back stacks",
                        "2026: animated transitions · MVI-style intents",
                        "2026: live \"Now / Up next\" sessions"
                    )
                )
                Row(horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)) {
                    StatusChip(label = "Now", delayMillis = 0)
                    StatusChip(label = "Up next", delayMillis = NAV_CHIP_DELAY_MILLIS)
                }
            }
            MediaPlaceholder(
                label = "Screen recording: switching tabs with back stacks kept, and the Now / Up next rail",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: git log, Dec 2025 to Aug 2026 (commit 8faf624f and the back stack commits)",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * A small monospace chip for the live-session status, popping in after [delayMillis].
 *
 * @param label The chip text.
 * @param delayMillis How long to wait before the chip pops in.
 */
@Composable
private fun StatusChip(label: String, delayMillis: Int) {
    Box(
        modifier = Modifier
            .popIn(delayMillis = delayMillis)
            .clip(RoundedCornerShape(Values.Dimens.tinyPadding))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = Values.Dimens.mediumPadding, vertical = Values.Dimens.smallPadding)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Monospace),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}
