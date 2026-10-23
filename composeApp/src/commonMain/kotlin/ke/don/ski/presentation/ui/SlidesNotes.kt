package ke.don.ski.presentation.ui

import androidx.compose.ui.text.AnnotatedString

// One named constant per slide, referenced from skiPresentationSlides().
// Every constant is a placeholder until the presenter writes the real notes.
// See docs/speaker-notes.md for how to write these.

private fun placeholderNotes(presenter: String, intent: String) = listOf(
    AnnotatedString("PLACEHOLDER. Presenter: $presenter. $intent")
)

val openingTitleNotes = placeholderNotes(
    "Annunziata Kinya",
    "Talk title, the app, and the three presenters"
)

val openingThesisNotes = placeholderNotes(
    "Annunziata Kinya",
    "Thesis on screen and the arc: history, about to launch, future"
)

val historyBootstrapNotes = placeholderNotes(
    "Annunziata Kinya",
    "2023 layered modules (app, chai, data, domain, presentation), Chai colours (`740ce113`)"
)

val historyPluginsNotes = placeholderNotes(
    "Annunziata Kinya",
    "Convention plugins replace per-module build setup (`6a6845e4`, `996ca6fe`)"
)

val historyChurnNotes = placeholderNotes(
    "Annunziata Kinya",
    "Contributor rotation, offline-first feed (`91322d29`), and the silence after 2023-11-09"
)

val detourKmmNotes = placeholderNotes(
    "Annunziata Kinya",
    "2024 KMM experiment in parallel with the Android modules (`3433931f`, `26f6a3d0`), gone within a quarter"
)

val detourKmpNotes = placeholderNotes(
    "Annunziata Kinya",
    "2025 Compose Multiplatform attempt (`cdd713bd`) and its rollback (`c42e0cdb`, `26a9e9cf`)"
)

val dormantYearsNotes = placeholderNotes(
    "Annunziata Kinya",
    "Droidcon 2024 work (`c02c6652`) and the Navigation 3 move (`8faf624f`)"
)

val launchStackNotes = placeholderNotes(
    "Donald Isoe",
    "Stack jump from Kotlin 2.1.21 and AGP 8.10.1 to Kotlin 2.4.21 and AGP 9.3.1"
)

val launchModulesNotes = placeholderNotes(
    "Donald Isoe",
    "Module split: 17 modules and `:presentation` removed (`ac81b8ac`), then 21 modules"
)

val launchPerfNotes = placeholderNotes(
    "Donald Isoe",
    "Baseline profile, release APK down 28% (`5a42a5a7`), size gate in CI (`1fc20ee5`)"
)

val launchDesignNotes = placeholderNotes(
    "Donald Isoe",
    "Chai on Material 3 Expressive (`87d23cb7`) and the 2026 identity (`eb7c436e`)"
)

val launchFeaturesNotes = placeholderNotes(
    "Donald Isoe",
    "Done features on main: cube hero (`5941f20f`) and Glance widget (`4492346b`)"
)

val launchWipNotes = placeholderNotes(
    "Donald Isoe",
    "In progress: notifications module on branch `feat/notifications` (not yet on main at 2026-10-23)"
)

val futurePlanNotes = placeholderNotes(
    "Ian Nthuli",
    "The improvement plan: what it is, whose idea it was (Harun Wangereka), and that it is a living roadmap, not a commitment"
)

val futureRoadmapNotes = placeholderNotes(
    "Ian Nthuli",
    "The ranked items: accessibility pass, ticketing and QR, notifications leftovers, venue map and calendar, AI last"
)

val futureInviteNotes = placeholderNotes(
    "Ian Nthuli",
    "Make the app your own: tracks for a small contributor pool, how to join through a pull request, the rotation model"
)

val lessonsNotes = placeholderNotes(
    "Ian Nthuli",
    "Three lessons from a codebase that changes hands: write it down for the next person, let CI guard it while people are away, make it easy to start"
)

val qaNotes = placeholderNotes(
    "All, moderated by Annunziata Kinya",
    "Questions, with the open questions from `docs/talk/history.md` as backup"
)
