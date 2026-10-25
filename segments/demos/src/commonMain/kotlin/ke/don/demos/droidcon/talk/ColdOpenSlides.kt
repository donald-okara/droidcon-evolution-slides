package ke.don.demos.droidcon.talk

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ke.don.demos.droidcon.common.ChartPoint
import ke.don.demos.droidcon.common.ColumnChart
import ke.don.demos.droidcon.common.EvolutionStages
import ke.don.demos.droidcon.common.EvolutionTimeline
import ke.don.demos.droidcon.common.SlideImage
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.SpinningCube
import ke.don.demos.droidcon.common.popIn
import ke.don.domain.values.Values
import ke.don.resources.Resources
import org.jetbrains.compose.resources.DrawableResource

/** The number of reveal steps on the family tree slide: one per stage in [EvolutionStages]. */
const val FAMILY_TREE_STEPS = 7

/** The stage after which the family tree draws its 2021 gap. Stage 2 is 2020; stage 3 is 2022. */
private const val FAMILY_TREE_GAP_AFTER = 2

private const val QR_STAGGER_MILLIS = 150
private const val FLAG_DELAY_MILLIS = 900

/** The label of S01. The deck hides its background cube on this slide, because the hero replaces it. */
const val COLD_TITLE_LABEL = "cold-title"

/** The label of S22. The deck hides its background cube on this slide too, because the live cube hero replaces it. */
const val CUBE_WIDGET_LABEL = "y2026-cube-widget"

/**
 * S01. The title: the talk's name, the meetup line, and this year's cube hero, which spins into place.
 */
@Composable
fun TitleSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpinningCube(modifier = Modifier.fillMaxWidth().weight(1f))
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        Text(
            text = "From I/O clone to our own app: eight years of the droidcon Kenya app",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Android254 × Kotlin Kenya · pre-droidcon meetup · Saturday 24 October 2026",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = Values.Dimens.mediumPadding)
        )
    }
}

/**
 * S02. Today's schedule, with two QR codes that pop in one after another: the Play listing and the repo.
 */
@Composable
fun TodaySlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Today")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                ScheduleRow(time = "10:05", item = "The talk")
                ScheduleRow(time = "10:55", item = "Msako wa Mende: the bug bounty")
                ScheduleRow(time = "13:05", item = "Feature bash: Mkono, Centaur, Wakala")
                ScheduleRow(time = "15:35", item = "Results and prizes")
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                QrCard(
                    label = "Play listing",
                    resource = Resources.Images.PLAY_QR,
                    index = 0,
                    modifier = Modifier.weight(1f)
                )
                QrCard(
                    label = "Repo",
                    resource = Resources.Images.REPO_QR,
                    index = 1,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Text(
            text = "Wi-Fi · app QR · repo QR",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * One line of the schedule: a start time and the item that starts then.
 *
 * @param time The start time, for example "10:05".
 * @param item The session or activity, as it appears on the slide.
 */
@Composable
private fun ScheduleRow(time: String, item: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = time,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(96.dp)
        )
        Text(
            text = item,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * A QR code with its label underneath. It pops in after a short delay based on [index].
 *
 * @param label What the code links to, shown under the code.
 * @param resource The QR code image.
 * @param index The position of the code, from 0, which sets the stagger.
 */
@Composable
private fun QrCard(label: String, resource: DrawableResource, index: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .popIn(delayMillis = index * QR_STAGGER_MILLIS)
        ) {
            SlideImage(
                resource = resource,
                contentDescription = "QR code: $label",
                modifier = Modifier.aspectRatio(1f, matchHeightConstraintsFirst = true)
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.tinyPadding))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * S04. The repo family tree as the evolution timeline, with a gap drawn at 2021. Each reveal step lights the next stage.
 */
@Composable
fun FamilyTreeSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The family tree")
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentAlignment = Alignment.Center
        ) {
            EvolutionTimeline(
                stages = EvolutionStages,
                modifier = Modifier.fillMaxWidth(),
                gapAfterIndex = FAMILY_TREE_GAP_AFTER
            )
        }
        Text(
            text = "Side branches: chai (2023), droidconKeKMP (2025)",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Same app, other stacks: Flutter ×3, React Native ×2",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/** The months of Appendix D, D1 that have commits. Months missing from this map are plotted as zero. */
private val SeasonCommits: Map<String, Int> = mapOf(
    "2023-02" to 44, "2023-03" to 40, "2023-04" to 25, "2023-05" to 14, "2023-06" to 27,
    "2023-07" to 11, "2023-08" to 14, "2023-09" to 54, "2023-10" to 62, "2023-11" to 35,
    "2024-03" to 2, "2024-05" to 7, "2024-06" to 40, "2024-11" to 6,
    "2025-06" to 54, "2025-10" to 2, "2025-11" to 7, "2025-12" to 12,
    "2026-01" to 6, "2026-02" to 5, "2026-03" to 7, "2026-04" to 24, "2026-05" to 4,
    "2026-08" to 79, "2026-09" to 38, "2026-10" to 67
)

private const val MEETUP_MONTH = "2026-10"
private val MonthNames = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

/**
 * S05. Commits per month in droidconKeKotlin, Feb 2023 to Oct 2026, one column per month. Only the quarter
 * starts and the meetup month are labelled, so the axis stays readable. The meetup month is highlighted.
 */
@Composable
fun SeasonsSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "We code in seasons")
        Text(
            text = "Commits come before the conference",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "2018: 78 in May · 2020: 159 in April · 2022: 212 in July · 2026: 79 in August",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = Values.Dimens.tinyPadding, bottom = Values.Dimens.smallPadding)
        )
        ColumnChart(
            points = seasonPoints(),
            modifier = Modifier.fillMaxWidth().weight(1f),
            caption = "Commits per month in droidconKeKotlin, Feb 2023 to Oct 2026"
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        MeetupFlag()
    }
}

/**
 * Builds one chart point per month from Feb 2023 to Oct 2026, including zero-commit months.
 */
private fun seasonPoints(): List<ChartPoint> {
    val points = mutableListOf<ChartPoint>()
    var year = 2023
    var month = 2
    while (year < 2026 || (year == 2026 && month <= 10)) {
        val key = "$year-${month.toString().padStart(2, '0')}"
        val isQuarterStart = (month - 2) % 3 == 0
        val label = when {
            key == MEETUP_MONTH -> MonthNames[month - 1]
            isQuarterStart -> MonthNames[month - 1]
            else -> ""
        }
        points += ChartPoint(
            label = label,
            value = (SeasonCommits[key] ?: 0).toFloat(),
            highlight = key == MEETUP_MONTH
        )
        month++
        if (month > 12) {
            month = 1
            year++
        }
    }
    return points
}

/**
 * The flag over the meetup month. It pops in after the columns have grown.
 */
@Composable
private fun MeetupFlag(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .popIn(delayMillis = FLAG_DELAY_MILLIS)
            .clip(RoundedCornerShape(percent = 50))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = Values.Dimens.mediumPadding, vertical = Values.Dimens.smallPadding)
    ) {
        Text(
            text = "Highlighted: the pre-droidcon meetup, 24 October 2026",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}
