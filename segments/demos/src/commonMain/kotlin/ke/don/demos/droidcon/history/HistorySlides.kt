package ke.don.demos.droidcon.history

import androidx.compose.runtime.Composable
import ke.don.demos.droidcon.common.PlaceholderSlide

private const val ANNUNZIATA = "Annunziata Kinya"

@Composable
fun HistoryBootstrapSlide() {
    PlaceholderSlide(
        title = "2023: bootstrap",
        intent = "2023 layered modules (app, chai, data, domain, presentation), Chai colours (`740ce113`)",
        presenter = ANNUNZIATA
    )
}

@Composable
fun HistoryPluginsSlide() {
    PlaceholderSlide(
        title = "Convention plugins",
        intent = "Convention plugins replace per-module build setup (`6a6845e4`, `996ca6fe`)",
        presenter = ANNUNZIATA
    )
}

@Composable
fun HistoryChurnSlide() {
    PlaceholderSlide(
        title = "Contributor churn",
        intent = "Contributor rotation, offline-first feed (`91322d29`), and the silence after 2023-11-09",
        presenter = ANNUNZIATA
    )
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
