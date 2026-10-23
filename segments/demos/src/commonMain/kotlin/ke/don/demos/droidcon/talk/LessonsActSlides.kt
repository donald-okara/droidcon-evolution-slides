package ke.don.demos.droidcon.talk

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.don.demos.droidcon.common.ChartPoint
import ke.don.demos.droidcon.common.ColumnChart
import ke.don.demos.droidcon.common.RevealItem
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.SourceLine
import ke.don.demos.droidcon.common.popIn
import ke.don.domain.values.Values

/** The number of reveal presses on [EightYearsTeachSlide]: one per lesson. Used by `SkiPresentationSlides.kt`. */
const val EIGHT_YEARS_STEPS = 4

private const val NAME_STAGGER_MILLIS = 80
private const val ROW_STAGGER_MILLIS = 320
private const val WIGGLE_DEGREES = 12f
private const val WIGGLE_HALF_PERIOD_MILLIS = 220
private const val LESSON_NUMBER_SIZE_DP = 44

/**
 * S27. Lost and found. A two-column "then and now" table: ideas from the 2018 and 2019 repos, what became of
 * each, and what is still missing. Rows drop in one after another, so the eye reads across each row.
 */
@Composable
fun LostAndFoundSlide() {
    val scheme = MaterialTheme.colorScheme
    val rows = listOf(
        Triple("Starred-session pushes", "2018", "#448 (open)"),
        Triple("Venue map", "2018–19", "§11.6 (needs an SVG)"),
        Triple("Phones and tablets", "Promised 2018", "Kept 2026"),
        Triple("Travel details", "2018–19", "Gone. Should it come back?")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Lost and found: what came back, what didn't", kicker = "Act 7")
        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("Idea", "Then", "Now").forEachIndexed { index, header ->
                Text(
                    text = header,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = scheme.onSurfaceVariant,
                    modifier = Modifier.weight(if (index == 0) 1.2f else 1f)
                )
            }
        }
        rows.forEachIndexed { index, (idea, then, now) ->
            val lastRow = index == rows.lastIndex
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Values.Dimens.smallPadding)
                    .popIn(delayMillis = index * ROW_STAGGER_MILLIS)
            ) {
                Text(
                    text = idea,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = scheme.primary,
                    modifier = Modifier.weight(1.2f)
                )
                Text(
                    text = then,
                    style = MaterialTheme.typography.titleMedium,
                    color = scheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = now,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (lastRow) scheme.secondary else scheme.onSurface,
                    fontWeight = if (lastRow) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        SourceLine(text = "Source: package lists of the 2018 and 2019 repos; plan §11.6; #410; #448")
    }
}

/**
 * S28. The uncomfortable chart. Two bar charts side by side: people committing per year (D5), which stays flat
 * after 2023, against Kotlin lines of code (D4), which grows. The community bar does not move; the code bar does.
 */
@Composable
fun UncomfortableChartSlide() {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The uncomfortable chart", kicker = "Act 7")
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            Text(
                text = "5",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = scheme.primary
            )
            Column(modifier = Modifier.padding(bottom = Values.Dimens.smallPadding)) {
                Text(
                    text = "people committing in 2026 (41 in 2023)",
                    style = MaterialTheme.typography.titleLarge,
                    color = scheme.onSurface
                )
                Text(
                    text = "The code grew. The community didn't.",
                    style = MaterialTheme.typography.titleMedium,
                    color = scheme.onSurfaceVariant,
                    modifier = Modifier.popIn(delayMillis = 800)
                )
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            ColumnChart(
                points = listOf(
                    ChartPoint(label = "2023", value = 41f, highlight = true),
                    ChartPoint(label = "2024", value = 8f),
                    ChartPoint(label = "2025", value = 9f),
                    ChartPoint(label = "2026", value = 5f)
                ),
                caption = "People committing per year, bots excluded (D5)",
                modifier = Modifier.weight(1f).fillMaxSize()
            )
            ColumnChart(
                points = listOf(
                    ChartPoint(label = "Mar 23", value = 14.7f),
                    ChartPoint(label = "Dec 24", value = 17.4f),
                    ChartPoint(label = "Oct 1", value = 27.8f),
                    ChartPoint(label = "Oct 22", value = 39.9f, highlight = true)
                ),
                caption = "Kotlin lines of code, thousands (D4)",
                modifier = Modifier.weight(1f).fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        SourceLine(text = "Source: distinct author names per year, bots excluded (D5)")
    }
}

/**
 * S29. What eight years teach. Four lessons, one per reveal press. Each lesson is one sentence beside a numbered
 * disc that fills in as its lesson arrives. Hidden lessons keep their space, so the list does not jump.
 */
@Composable
fun EightYearsTeachSlide() {
    val lessons = listOf(
        "Copy the best, then stand on your own",
        "Repos with a year in the name reset the community",
        "Removing an experiment is still progress",
        "Write it down for whoever comes next, human or agent"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "What eight years teach", kicker = "Act 7")
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Column(verticalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)) {
            lessons.forEachIndexed { index, lesson ->
                EightYearsLesson(number = index + 1, text = lesson)
            }
        }
    }
}

/**
 * S30. Thank you. The contributors grouped by era, one column per era. Each name pops in as the slide opens,
 * one after another, in reading order. Not reveal-stepped, so the slide never waits on a press.
 */
@Composable
fun ThankYouSlide() {
    val scheme = MaterialTheme.colorScheme
    val eras = listOf(
        "2018–19" to listOf("Harun Wangereka", "Michael Bukachi", "muhosteve", "Victor", "johnGachihi"),
        "2020" to listOf(
            "Oliver Muthomi", "Benard Mathu", "David Odari", "Jabez Magomere", "philomenambura", "Jama Mohamed"
        ),
        "2022" to listOf(
            "Gibson Silali", "Waitara", "Theophilus Kibet", "Evans Chepsiror", "Juma Allan", "tamzi"
        ),
        "2023–25" to listOf(
            "Hannah Olukoye", "Peter Chege", "Kenneth Mathari", "Yves Kalume", "robert-nganga",
            "Raynafs", "Michael Ndiritu", "MamboBryan", "brian.orwe"
        ),
        "2026" to listOf("donald-okara", "Joel Kanyi", "mutukuian")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Thank you", kicker = "Act 7")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            eras.forEachIndexed { eraIndex, (era, names) ->
                val namesBefore = eras.take(eraIndex).sumOf { it.second.size }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)
                ) {
                    Text(
                        text = era,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = scheme.primary,
                        modifier = Modifier.padding(bottom = Values.Dimens.tinyPadding)
                    )
                    names.forEachIndexed { nameIndex, name ->
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyLarge,
                            color = scheme.onSurface,
                            modifier = Modifier.popIn(
                                delayMillis = (namesBefore + nameIndex) * NAME_STAGGER_MILLIS
                            )
                        )
                    }
                }
            }
        }
        Text(
            text = "Ask anyone from the list who is in the room to stand up.",
            style = MaterialTheme.typography.titleMedium,
            color = scheme.onSurfaceVariant
        )
        SourceLine(text = "Source: git shortlog -sn per repo; checked against the README contributors section")
    }
}

/**
 * S31. Now, let's break it. The handover to the bounty. A large cockroach wiggles on the slide, and two
 * lines say what is about to start and what to have ready.
 */
@Composable
fun BreakItSlide() {
    val scheme = MaterialTheme.colorScheme
    val transition = rememberInfiniteTransition(label = "breakItWiggle")
    val wiggle by transition.animateFloat(
        initialValue = -WIGGLE_DEGREES,
        targetValue = WIGGLE_DEGREES,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = WIGGLE_HALF_PERIOD_MILLIS, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breakItWiggleAngle"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🪳",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 160.sp),
            modifier = Modifier.graphicsLayer {
                rotationZ = wiggle
                transformOrigin = TransformOrigin.Center
            }
        )
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Now, let's break it",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = scheme.primary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Msako wa Mende starts in 10 minutes",
            style = MaterialTheme.typography.headlineSmall,
            color = scheme.onSurface,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Phone ready · app updated · GitHub account open",
            style = MaterialTheme.typography.titleMedium,
            color = scheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * One lesson on [EightYearsTeachSlide]: a numbered disc and one sentence. It appears at its reveal step.
 *
 * @param number the 1-based lesson number, which is also its reveal step.
 * @param text the lesson sentence.
 */
@Composable
private fun EightYearsLesson(number: Int, text: String) {
    val scheme = MaterialTheme.colorScheme
    RevealItem(index = number) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            Box(
                modifier = Modifier
                    .size(LESSON_NUMBER_SIZE_DP.dp)
                    .clip(CircleShape)
                    .background(scheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$number",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = scheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium,
                color = scheme.onSurface
            )
        }
    }
}
