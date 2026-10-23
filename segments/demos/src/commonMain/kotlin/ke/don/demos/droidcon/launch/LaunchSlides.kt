package ke.don.demos.droidcon.launch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ke.don.demos.droidcon.common.CommitTag
import ke.don.demos.droidcon.common.MediaPlaceholder
import ke.don.demos.droidcon.common.RevealBulletList
import ke.don.demos.droidcon.common.RevealItem
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.StatDirection
import ke.don.demos.droidcon.common.StatTile
import ke.don.demos.droidcon.common.SwatchRow
import ke.don.demos.droidcon.common.currentSchemeSwatches
import ke.don.demos.droidcon.common.pre2026Swatches
import ke.don.domain.values.Values

/** Number of reveal presses on [LaunchModulesSlide]: one per module group. Used by `SkiPresentationSlides.kt`. */
const val LAUNCH_MODULES_STEPS = 3

/** Number of reveal presses on [LaunchFeaturesSlide]: one per done feature or fix. Used by `SkiPresentationSlides.kt`. */
const val LAUNCH_FEATURES_STEPS = 5

/**
 * Number of reveal presses on [LaunchWipSlide]: four done items, then two still to do.
 * Used by `SkiPresentationSlides.kt`.
 */
const val LAUNCH_WIP_STEPS = 6

/**
 * The build stack before and after the 2026 update, from the stack snapshots in `docs/talk/history.md`.
 *
 * No reveal. The Gradle and minSdk rows have no "before" value in the snapshots, so they say so.
 */
@Composable
fun LaunchStackSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "2026: the stack", kicker = "Stack snapshots, before and after")
        StackRow(item = "", before = "Before: 2025-12", after = "After: 2026-10", header = true)
        StackRow(item = "Kotlin", before = "2.1.21", after = "2.4.21")
        StackRow(item = "Android Gradle Plugin", before = "8.10.1", after = "9.3.1")
        StackRow(item = "Gradle", before = "not in snapshots", after = "9.7 (6bb9b99c)")
        StackRow(item = "minSdk", before = "not in snapshots", after = "26 (8f7577d5)")
    }
}

/**
 * The module split: 17 modules with `:presentation` gone, then 21 modules, with the groups revealed one by one.
 *
 * Reveal steps are [LAUNCH_MODULES_STEPS]: core, then feature, then widget and benchmarks.
 */
@Composable
fun LaunchModulesSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Module split", kicker = "2026")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "17 modules, :presentation gone",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag("ac81b8ac")
        }
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Then 21 modules",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        RevealBulletList(listOf("core:*", "feature:*", "widget and benchmarks"))
    }
}

/**
 * The performance figures as three green stat tiles: startup time, release APK size, and size gated in CI.
 *
 * No reveal. The tiles count up and shrink when the slide appears.
 */
@Composable
fun LaunchPerfSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Faster and smaller", kicker = "Baseline profile and benchmarks")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            StatTile(
                value = 35f,
                unit = "%",
                label = "Startup time",
                direction = StatDirection.Decrease,
                source = "Confirmed by Harun Wangereka",
                modifier = Modifier.weight(1f)
            )
            StatTile(
                value = 28f,
                unit = "%",
                label = "Release APK",
                direction = StatDirection.Decrease,
                sha = "5a42a5a7",
                modifier = Modifier.weight(1f)
            )
            StatTile(
                value = 7.4f,
                unit = "%",
                label = "Size, gated in CI",
                direction = StatDirection.Decrease,
                sha = "1fc20ee5",
                decimals = 1,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * One row of the stack table. The header row uses the primary colour, and the other rows use body text.
 */
@Composable
private fun StackRow(
    item: String,
    before: String,
    after: String,
    header: Boolean = false
) {
    val scheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Values.Dimens.tinyPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (header) {
            Text(
                text = item,
                style = MaterialTheme.typography.labelLarge,
                color = scheme.primary,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = before,
                style = MaterialTheme.typography.labelLarge,
                color = scheme.primary,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = after,
                style = MaterialTheme.typography.labelLarge,
                color = scheme.primary,
                modifier = Modifier.weight(1f)
            )
        } else {
            Text(
                text = item,
                style = MaterialTheme.typography.headlineSmall,
                color = scheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = before,
                style = MaterialTheme.typography.bodyLarge,
                color = scheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = after,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = scheme.onSurface,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * The palette before 2026 next to the live 2026 scheme, as two rows of swatches with their hex values.
 *
 * No reveal. The "now" row reads [MaterialTheme.colorScheme], so it follows the deck theme.
 */
@Composable
fun LaunchDesignSlide() {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Then and now", kicker = "Design, 2026")
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        SwatchRow(label = "Then: pre-2026 palette", swatches = pre2026Swatches())
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        SwatchRow(label = "Now: 2026 scheme", swatches = currentSchemeSwatches())
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Chai on Material 3 Expressive",
                style = MaterialTheme.typography.bodyLarge,
                color = scheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag("87d23cb7")
            Spacer(modifier = Modifier.width(Values.Dimens.mediumPadding))
            Text(
                text = "2026 identity",
                style = MaterialTheme.typography.bodyLarge,
                color = scheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag("eb7c436e")
        }
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        MediaPlaceholder(
            label = "Screenshot: home, before and after",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

/**
 * The done features and fixes on main, one reveal step each. The cube hero and the widget carry their commit tags.
 */
@Composable
fun LaunchFeaturesSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Features on main", kicker = "Done, 2026")
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        RevealLine(step = 1, text = "Cube hero", sha = "5941f20f")
        RevealLine(step = 2, text = "Glance widget", sha = "4492346b")
        RevealLine(step = 3, text = "Adaptive large-screen layouts")
        RevealLine(step = 4, text = "Speaker details fix")
        RevealLine(step = 5, text = "Sponsor logo contrast fix")
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        MediaPlaceholder(
            label = "Screen recording: cube hero and widget",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

/**
 * The notifications work in progress on `feat/notifications`. Four done items are revealed, then two still to do,
 * in a muted colour.
 */
@Composable
fun LaunchWipSlide() {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Work in progress", kicker = "Notifications")
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Notifications, on feat/notifications, not yet on main (2026-10-23)",
            style = MaterialTheme.typography.headlineSmall,
            color = scheme.onSurface
        )
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Done so far",
            style = MaterialTheme.typography.labelLarge,
            color = scheme.primary
        )
        RevealLine(step = 1, text = "Channels")
        RevealLine(step = 2, text = "Reminders")
        RevealLine(step = 3, text = "Preferences")
        RevealLine(step = 4, text = "Deep links")
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Still to do",
            style = MaterialTheme.typography.labelLarge,
            color = scheme.primary
        )
        RevealLine(step = 5, text = "Topic subscriptions", muted = true)
        RevealLine(step = 6, text = "Reminder actions", muted = true)
    }
}

/**
 * One line that appears at its reveal step, with an optional commit tag after it.
 *
 * @param step the 1-based reveal step at which the line shows.
 * @param sha an optional commit SHA shown as a [CommitTag].
 * @param muted true to draw the text in `onSurfaceVariant`, for items still to do.
 */
@Composable
private fun RevealLine(step: Int, text: String, sha: String? = null, muted: Boolean = false) {
    val scheme = MaterialTheme.colorScheme
    RevealItem(index = step) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Values.Dimens.tinyPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                color = if (muted) scheme.onSurfaceVariant else scheme.onSurface
            )
            if (sha != null) {
                Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
                CommitTag(sha)
            }
        }
    }
}

