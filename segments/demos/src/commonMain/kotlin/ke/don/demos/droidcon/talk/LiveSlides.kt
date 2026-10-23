package ke.don.demos.droidcon.talk

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ke.don.demos.droidcon.common.BulletList
import ke.don.demos.droidcon.common.ChartPoint
import ke.don.demos.droidcon.common.ColumnChart
import ke.don.demos.droidcon.common.MediaPlaceholder
import ke.don.demos.droidcon.common.RevealItem
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.SourceLine
import ke.don.demos.droidcon.common.StatDirection
import ke.don.demos.droidcon.common.StatTile
import ke.don.demos.droidcon.common.popIn
import ke.don.domain.LocalRevealStep
import ke.don.domain.values.Values

/** The number of reveal steps on the live demo slide: one per demo step, in the order of section 1.7. */
const val LIVE_DEMO_STEPS = 6

private const val PHASE_STAGGER_MILLIS = 250
private const val WIDGET_CHIP_DELAY_MILLIS = 600
private const val WIDGET_CHIP_STAGGER_MILLIS = 200
private const val STACK_ROW_STAGGER_MILLIS = 120
private const val DEMO_STEP_CHECK_SIZE_DP = 28
private const val DEMO_STEP_SCALE_HIDDEN = 0.6f

/**
 * S22. The cube and the widget. The cube is a hero drawn on a Compose canvas; the widget shows what is on now
 * and next. Three chips name the order the widget was built in: spec, then plan, then code.
 */
@Composable
fun CubeWidgetSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The cube and the widget", kicker = "Act 6")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            MediaPlaceholder(
                label = "Cube hero: a 3D Rubik's cube on a Compose Canvas",
                modifier = Modifier.weight(1f).popIn()
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                MediaPlaceholder(
                    label = "Glance widget: what's on now and next",
                    modifier = Modifier.weight(1f).popIn(delayMillis = PHASE_STAGGER_MILLIS)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)) {
                    listOf("Spec", "Plan", "Code").forEachIndexed { index, step ->
                        WorkflowChip(
                            text = step,
                            modifier = Modifier
                                .weight(1f)
                                .popIn(delayMillis = WIDGET_CHIP_DELAY_MILLIS + index * WIDGET_CHIP_STAGGER_MILLIS)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "The cube is pure play. The plan has a list of easter eggs it could grow.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SourceLine(text = "Source: #446; the widget commits, Oct 2026")
    }
}

/**
 * S23. An app that follows the conference. Three home screens, one per phase: before, during and after.
 * They pop in one after another, then the two standing features are listed.
 */
@Composable
fun FollowsConferenceSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "An app that follows the conference", kicker = "Act 6")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
        ) {
            listOf(
                "Before: a countdown",
                "During: today's agenda, clashes named",
                "After: thank you and a feedback link"
            ).forEachIndexed { index, label ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .popIn(delayMillis = index * PHASE_STAGGER_MILLIS),
                    verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    MediaPlaceholder(
                        label = "Home: ${label.substringBefore(':')}",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        BulletList(
            items = listOf(
                "Updates: schedule changes and organiser news in one timeline",
                "Your ticket, at the door"
            )
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        SourceLine(text = "Source: core/domain/.../schedule/Schedule.kt; #451, #453. Goldens: home_before, home_agenda, home_after")
    }
}

/**
 * S24. Live demo: from purchase to pass. The six demo steps from section 1.7 reveal one at a time, and each
 * check fills in as its step arrives. The phone mirror sits on the right, with a placeholder for the fallback video.
 */
@Composable
fun LiveDemoSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Live demo: from purchase to pass", kicker = "Act 6")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                DemoStepRow(number = 1, text = "Time travel: home with the cube hero, pull to refresh")
                DemoStepRow(number = 2, text = "Clashes: set the date to conference day 1, the agenda appears")
                DemoStepRow(number = 3, text = "Widget: star two overlapping sessions, the clash banner appears")
                DemoStepRow(number = 4, text = "Add the Next-session widget to the launcher")
                DemoStepRow(number = 5, text = "A ticket, bought now: purchase on mobile data, add it, show the pass")
                DemoStepRow(number = 6, text = "Optional: tablet or resizable window, list-detail")
            }
            MediaPlaceholder(
                label = "Phone mirrored with scrcpy. Fallback video ready",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Airplane mode, then reopen the pass: it is still there.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/**
 * S25. The stack, layer by layer (optional). A condensed table: five layers down, four years across.
 * Each row drops in after the one above it, so the eye reads across a row.
 */
@Composable
fun StackSlide() {
    val years = listOf("2018", "2020", "2022", "2026")
    val rows = listOf(
        "UI" to listOf("XML + ButterKnife", "XML + data binding", "Compose + M3 alpha", "Compose + M3 Expressive, adaptive"),
        "DI" to listOf("Koin", "Koin", "Hilt", "Hilt"),
        "Data" to listOf("Firebase", "Retrofit + Room", "Ktor + Room", "Offline first + sync"),
        "Build" to listOf("Groovy", "Kotlin DSL + buildSrc", "Version catalog", "Convention plugins, AGP 9"),
        "Tests" to listOf("Espresso", "MockK, Kakao", "Compose UI tests", "Roborazzi, Macrobenchmark")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The stack, layer by layer", kicker = "Act 6")
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Layer",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(0.8f)
            )
            years.forEach { year ->
                Text(
                    text = year,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (year == "2026") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        rows.forEachIndexed { index, (layer, cells) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Values.Dimens.tinyPadding)
                    .popIn(delayMillis = index * STACK_ROW_STAGGER_MILLIS)
            ) {
                Text(
                    text = layer,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(0.8f)
                )
                cells.forEachIndexed { column, cell ->
                    Text(
                        text = cell,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (column == cells.lastIndex) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        modifier = Modifier.weight(1f).padding(end = Values.Dimens.tinyPadding)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Read across a row and you get Android's history.",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SourceLine(text = "Source: section 1.3 of the overview")
    }
}

/**
 * S26. How the code grew. Kotlin lines per snapshot (D4) as columns, with the last one highlighted. Two stat
 * tiles count up the test files and the screenshot goldens.
 */
@Composable
fun CodeGrewSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "How the code grew", kicker = "Act 6")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            ColumnChart(
                points = listOf(
                    ChartPoint(label = "Mar 23", value = 14.7f),
                    ChartPoint(label = "Dec 23", value = 17.0f),
                    ChartPoint(label = "Dec 24", value = 17.4f),
                    ChartPoint(label = "Jul 25", value = 17.3f),
                    ChartPoint(label = "Aug 26", value = 18.8f),
                    ChartPoint(label = "Sep 26", value = 24.8f),
                    ChartPoint(label = "Oct 1", value = 27.8f),
                    ChartPoint(label = "Oct 22", value = 39.9f, highlight = true)
                ),
                caption = "Kotlin lines of code, thousands (D4)",
                modifier = Modifier.weight(1.6f).fillMaxSize()
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                StatTile(
                    value = 110f,
                    unit = "",
                    label = "Test files, up from 24 in March 2023",
                    direction = StatDirection.Increase,
                    source = "D4",
                    modifier = Modifier.fillMaxWidth()
                )
                StatTile(
                    value = 180f,
                    unit = "",
                    label = "Screenshot goldens, up from none in 2023",
                    direction = StatDirection.Increase,
                    source = "D4",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Flat for three years, then a step. The tests grew faster than the code.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        SourceLine(text = "Source: snapshots from git ls-tree (D4)")
    }
}

/**
 * One demo step. Its number becomes a check mark and the row brightens once the reveal reaches it.
 * The check springs from a smaller size, so each step reads as ticked off as it arrives.
 */
@Composable
private fun DemoStepRow(number: Int, text: String) {
    RevealItem(index = number) {
        val done = LocalRevealStep.current >= number
        val pop by animateFloatAsState(
            targetValue = if (done) 1f else DEMO_STEP_SCALE_HIDDEN,
            animationSpec = spring(dampingRatio = 0.5f, stiffness = 400f),
            label = "demoStepPop"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
        ) {
            Box(
                modifier = Modifier
                    .size(DEMO_STEP_CHECK_SIZE_DP.dp)
                    .graphicsLayer {
                        scaleX = pop
                        scaleY = pop
                    }
                    .clip(CircleShape)
                    .background(
                        if (done) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (done) "✓" else "$number",
                    style = MaterialTheme.typography.labelLarge,
                    color = if (done) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = if (done) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/** A small rounded label for the widget's build order. */
@Composable
private fun WorkflowChip(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(percent = 50))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = Values.Dimens.mediumPadding, vertical = Values.Dimens.smallPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center
        )
    }
}
