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
import ke.don.demos.droidcon.common.PlaceholderSlide
import ke.don.demos.droidcon.common.RevealBulletList
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.StatDirection
import ke.don.demos.droidcon.common.StatTile
import ke.don.domain.values.Values

private const val DONALD = "Donald Isoe"

/** Number of reveal presses on [LaunchModulesSlide]: one per module group. Used by `SkiPresentationSlides.kt`. */
const val LAUNCH_MODULES_STEPS = 3

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

@Composable
fun LaunchDesignSlide() {
    PlaceholderSlide(
        title = "Design",
        intent = "Chai on Material 3 Expressive (`87d23cb7`) and the 2026 identity (`eb7c436e`)",
        presenter = DONALD
    )
}

@Composable
fun LaunchFeaturesSlide() {
    PlaceholderSlide(
        title = "Features on main",
        intent = "Done features on main: cube hero (`5941f20f`) and Glance widget (`4492346b`)",
        presenter = DONALD
    )
}

@Composable
fun LaunchWipSlide() {
    PlaceholderSlide(
        title = "Work in progress",
        intent = "In progress: notifications module on branch `feat/notifications` (not yet on main at 2026-10-23)",
        presenter = DONALD
    )
}
