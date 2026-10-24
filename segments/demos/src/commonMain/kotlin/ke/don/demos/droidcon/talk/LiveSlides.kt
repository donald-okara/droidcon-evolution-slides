package ke.don.demos.droidcon.talk

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ke.don.demos.droidcon.common.BulletList
import ke.don.demos.droidcon.common.ChartPoint
import ke.don.demos.droidcon.common.ColumnChart
import ke.don.demos.droidcon.common.SlideImage
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.SpinningCube
import ke.don.demos.droidcon.common.SourceLine
import ke.don.demos.droidcon.common.StatDirection
import ke.don.demos.droidcon.common.StatTile
import ke.don.demos.droidcon.common.popIn
import ke.don.domain.values.Values
import ke.don.resources.Resources

private const val PHONE_ASPECT = 9f / 19.5f

private const val PHASE_STAGGER_MILLIS = 250
private const val STACK_ROW_STAGGER_MILLIS = 120

/**
 * S22. The cube. The cube is a hero drawn on a Compose canvas, filling the space under the title.
 */
@Composable
fun CubeWidgetSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The cube", kicker = "Act 6")
        SpinningCube(modifier = Modifier.fillMaxWidth().weight(1f).popIn())
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "The cube is pure play. The plan has a list of easter eggs it could grow.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SourceLine(text = "Source: #446, Oct 2026")
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
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            SlideImage(
                resource = Resources.Images.HOME_2026,
                contentDescription = "Home screen, 2026, before the conference",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(PHONE_ASPECT, matchHeightConstraintsFirst = true)
                    .popIn()
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                listOf(
                    "Before: a countdown",
                    "During: today's agenda, clashes named",
                    "After: thank you and a feedback link"
                ).forEachIndexed { index, label ->
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(vertical = Values.Dimens.tinyPadding)
                            .popIn(delayMillis = index * PHASE_STAGGER_MILLIS)
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

