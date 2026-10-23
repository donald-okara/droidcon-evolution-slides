package ke.don.demos.droidcon.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import ke.don.demos.droidcon.common.BulletList
import ke.don.demos.droidcon.common.CommitTag
import ke.don.demos.droidcon.common.RevealBulletList
import ke.don.demos.droidcon.common.RevealItem
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.domain.values.Values

/** Number of reveal presses on [HistoryBootstrapSlide]: one per library. Used by `SkiPresentationSlides.kt`. */
const val HISTORY_BOOTSTRAP_STEPS = 4

/** Number of reveal presses on [HistoryPluginsSlide]: one per plugin. Used by `SkiPresentationSlides.kt`. */
const val HISTORY_PLUGINS_STEPS = 3

/** Number of reveal presses on [DetourKmpSlide]: one per rollback step. Used by `SkiPresentationSlides.kt`. */
const val DETOUR_KMP_STEPS = 3

/**
 * 2023 bootstrap: the five layered modules as a static row, the Chai colour commit, then the libraries revealed one by one.
 */
@Composable
fun HistoryBootstrapSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Bootstrap: five layers", kicker = "2023")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
        ) {
            listOf("app", "chai", "data", "domain", "presentation").forEach { layer ->
                LayerBox(name = layer, modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Chai colours",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag("740ce113")
        }
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        RevealBulletList(listOf("Hilt", "Room", "Ktor", "Compose"))
    }
}

/**
 * Convention plugins: build setup moved out of each module, with the commits cited and the plugins revealed one by one.
 */
@Composable
fun HistoryPluginsSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Convention plugins", kicker = "2023")
        Text(
            text = "Build setup moved out of each module and into shared convention plugins.",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Hilt plugin",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag("6a6845e4")
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Compose and Kotlin config",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag("996ca6fe")
        }
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        RevealBulletList(listOf("Hilt", "Compose", "Kotlin application"))
    }
}

/**
 * Contributor churn: the top maintainers in 2023 by commit count, the offline-first feed, and the silence after 2023-11-09.
 *
 * No reveal. The counts are commits per author in each window, from `docs/talk/history.md` eras 1 and 2.
 */
@Composable
fun HistoryChurnSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Contributor churn", kicker = "2023")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Feb to Jun 2023",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
                BulletList(
                    listOf(
                        "Evans Chepsiror (chepsi) 17",
                        "Waitara 9",
                        "Kenneth Mathari 8",
                        "Harun Wangereka 8"
                    )
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Jul to Nov 2023",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
                BulletList(
                    listOf(
                        "Evans Chepsiror (chepsi) 41",
                        "yveskalume 10",
                        "robert-nganga 10",
                        "Waitara 10",
                        "Hannah Olukoye 9"
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Offline-first feed, Sep 2023 (Peter Chege)",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag("91322d29")
        }
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Silence after 2023-11-09: the next commit is 2024-03-28.",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * One layer of the 2023 module set, as a labelled outlined box.
 *
 * @param name The module name, as it appears in `settings.gradle.kts`.
 */
@Composable
private fun LayerBox(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(Values.Dimens.smallPadding))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(Values.Dimens.smallPadding)
            )
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(Values.Dimens.smallPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * 2024 detour: a Kotlin Multiplatform experiment that ran next to the Android modules and was gone within a quarter.
 */
@Composable
fun DetourKmmSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Detour: KMM", kicker = "2024")
        Text(
            text = "A Kotlin Multiplatform experiment ran next to the Android modules, not in place of them.",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        CitedLine(text = "KMM setup", sha = "3433931f")
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        CitedLine(text = "Shared modules migrated", sha = "26f6a3d0")
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        Text(
            text = "Active 2024-03 to 2024-06, then gone. Why it stopped: not recorded.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * 2025 detour: the Compose Multiplatform attempt, then its rollback, revealed one step per press.
 *
 * The slide says the reason is not recorded, because `history.md` open question 2 has no answer.
 */
@Composable
fun DetourKmpSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Detour: KMP", kicker = "2025")
        CitedLine(text = "Compose Multiplatform attempt", sha = "cdd713bd")
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Rollback, one step per press",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Column(verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)) {
            RevealItem(index = 1) { CitedLine(text = "Drop KMP", sha = "c42e0cdb") }
            RevealItem(index = 2) { CitedLine(text = "CI fixes") }
            RevealItem(index = 3) { CitedLine(text = "Five-layer layout back", sha = "26a9e9cf") }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        Text(
            text = "Why it was dropped: reason not recorded.",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * The quiet years between the detours and the 2026 update, as a small timeline of commits and silences.
 *
 * The gap labels count days with no commits in git, not effort.
 */
@Composable
fun DormantYearsSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The dormant years", kicker = "2024 to 2025")
        TimelineRow(date = "2024-11-05", text = "Droidcon 2024 work", sha = "c02c6652")
        TimelineGap(text = "211 days with no commits, 2024-11-13 to 2025-06-12")
        TimelineRow(date = "2025-06-12", text = "Compose Multiplatform attempt", sha = "cdd713bd")
        TimelineGap(text = "126 days with no commits, 2025-06-19 to 2025-10-23")
        TimelineRow(date = "2025-10-23", text = "Drop KMP", sha = "c42e0cdb")
        TimelineRow(date = "2025-12-23", text = "Navigation 3 move", sha = "8faf624f")
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        Text(
            text = "Gaps count days with no commits in git. They do not measure effort.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * A line of text followed by the commit it cites.
 *
 * @param text The line of text, in `headlineSmall`.
 * @param sha The abbreviated commit SHA, or null when the step cites no commit.
 */
@Composable
private fun CitedLine(text: String, sha: String? = null, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        if (sha != null) {
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag(sha)
        }
    }
}

/**
 * One dated commit on the dormant-years timeline.
 *
 * @param date The commit date, in `yyyy-mm-dd` form.
 * @param text What the commit did.
 * @param sha The abbreviated commit SHA.
 */
@Composable
private fun TimelineRow(date: String, text: String, sha: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = date,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
        CommitTag(sha)
    }
}

/**
 * The silence between two [TimelineRow]s, indented to sit under their text.
 *
 * @param text The gap, with its dates.
 */
@Composable
private fun TimelineGap(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(start = 120.dp, top = Values.Dimens.tinyPadding, bottom = Values.Dimens.tinyPadding)
    )
}
