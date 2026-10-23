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
import ke.don.demos.droidcon.common.PlaceholderSlide
import ke.don.demos.droidcon.common.RevealBulletList
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.domain.values.Values

private const val ANNUNZIATA = "Annunziata Kinya"

/** Number of reveal presses on [HistoryBootstrapSlide]: one per library. Used by `SkiPresentationSlides.kt`. */
const val HISTORY_BOOTSTRAP_STEPS = 4

/** Number of reveal presses on [HistoryPluginsSlide]: one per plugin. Used by `SkiPresentationSlides.kt`. */
const val HISTORY_PLUGINS_STEPS = 3

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

@Composable
fun DetourKmmSlide() {
    PlaceholderSlide(
        title = "Detour: KMM",
        intent = "2024 KMM experiment in parallel with the Android modules (`3433931f`, `26f6a3d0`), gone within a quarter",
        presenter = ANNUNZIATA
    )
}

@Composable
fun DetourKmpSlide() {
    PlaceholderSlide(
        title = "Detour: KMP",
        intent = "2025 Compose Multiplatform attempt (`cdd713bd`) and its rollback (`c42e0cdb`, `26a9e9cf`)",
        presenter = ANNUNZIATA
    )
}

@Composable
fun DormantYearsSlide() {
    PlaceholderSlide(
        title = "The dormant years",
        intent = "Droidcon 2024 work (`c02c6652`) and the Navigation 3 move (`8faf624f`)",
        presenter = ANNUNZIATA
    )
}
