package ke.don.demos.droidcon.launch

import androidx.compose.runtime.Composable
import ke.don.demos.droidcon.common.PlaceholderSlide

private const val DONALD = "Donald Isoe"

@Composable
fun LaunchStackSlide() {
    PlaceholderSlide(
        title = "2026: the stack",
        intent = "Stack jump from Kotlin 2.1.21 and AGP 8.10.1 to Kotlin 2.4.21 and AGP 9.3.1",
        presenter = DONALD
    )
}

@Composable
fun LaunchModulesSlide() {
    PlaceholderSlide(
        title = "Module split",
        intent = "Module split: 17 modules and `:presentation` removed (`ac81b8ac`), then 21 modules",
        presenter = DONALD
    )
}

@Composable
fun LaunchPerfSlide() {
    PlaceholderSlide(
        title = "Performance",
        intent = "Baseline profile, release APK down 28% (`5a42a5a7`), size gate in CI (`1fc20ee5`)",
        presenter = DONALD
    )
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
