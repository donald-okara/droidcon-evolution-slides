package ke.don.ski.presentation.ui

import androidx.compose.ui.text.AnnotatedString

// One named constant per slide, referenced from skiPresentationSlides().
// Every constant is a placeholder until the presenter writes the real notes.
// See docs/speaker-notes.md for how to write these.

private fun placeholderNotes(presenter: String, intent: String) = listOf(
    AnnotatedString("PLACEHOLDER. Presenter: $presenter. $intent")
)

val openingTitleNotes = listOf(
    AnnotatedString("Welcome, and thank you for coming. I'm Annunziata Kinya, and I'm presenting with Donald Isoe and Ian Nthuli."),
    AnnotatedString("This talk is about the droidcon Kenya Android app from 2023 to 2026, and why it is the community's shared teaching codebase."),
    AnnotatedString("Donald and Ian follow my history section, so please hold questions for the Q&A at the end.")
)

val openingThesisNotes = listOf(
    AnnotatedString("Here is the whole talk in one sentence, and I'll read it slowly."),
    AnnotatedString("The app has outlived years of contributors joining through pull requests and stepping back when busy, and two multiplatform detours, because it stays a shared teaching codebase."),
    AnnotatedString("The 2026 update shows what that buys us, and the improvement plan is an invitation to make the app your own."),
    AnnotatedString("Takeaway to return to: history, then about to launch, then the future. Hand-off cue: \"So let's start in 2023, with the bootstrap.\"")
)

val historyBootstrapNotes = listOf(
    AnnotatedString("Draft from history.md; Annie to replace with her own content."),
    AnnotatedString("The app started in February 2023 with five layers: app, chai, data, domain and presentation. The founding commit is 5b83edae, by Evans Chepsiror."),
    AnnotatedString("Chai colours arrived in commit 740ce113 on 2023-04-02. Each press of Right reveals one library: Hilt, then Room, Ktor and Compose."),
    AnnotatedString("Takeaway to return to: the layers came first, before any feature. Hand-off cue: \"Those layers made the next step possible: convention plugins.\"")
)

val historyPluginsNotes = listOf(
    AnnotatedString("Draft from history.md; Annie to replace with her own content."),
    AnnotatedString("Commit 6a6845e4, on 2023-07-17, added the Hilt convention plugin in pull request 147."),
    AnnotatedString("Commit 996ca6fe then moved the Compose dependencies and Kotlin application config into plugins too. Each press of Right reveals one: Hilt, Compose, then Kotlin application."),
    AnnotatedString("Hand-off cue: \"Plugins kept the build setup in one place. Next, who was doing this work?\"")
)

val historyChurnNotes = listOf(
    AnnotatedString("Draft from history.md; Annie to replace with her own content."),
    AnnotatedString("Evans Chepsiror was the top committer in both 2023 windows, with 17 commits from February to June and 41 from July to November. Other people joined and stepped back, with no formal handover."),
    AnnotatedString("The counts are commits per author in each window. They do not measure effort, review time or the size of each change."),
    AnnotatedString("Commit 91322d29 added the offline-first feed in September 2023. The last commit before the silence is 2023-11-09, and the next is 2024-03-28. The git history does not say why the silence happened.")
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
