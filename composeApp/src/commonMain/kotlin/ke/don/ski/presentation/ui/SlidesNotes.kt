package ke.don.ski.presentation.ui

import androidx.compose.ui.text.AnnotatedString

// One named constant per slide, referenced from skiPresentationSlides().
// See docs/speaker-notes.md for how to write these.

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

val detourKmmNotes = listOf(
    AnnotatedString("Draft from history.md; Annie to replace with her own content."),
    AnnotatedString("From March to June 2024 the app ran a Kotlin Multiplatform experiment next to the Android modules. Commit 3433931f, on 2024-05-17, set up KMM, and commit 26f6a3d0, on 2024-06-05, moved the shared modules over."),
    AnnotatedString("It was kept in parallel, not as a replacement, and the last commit before the quiet was on 2024-06-14. Git does not record why it stopped, so say that plainly."),
    AnnotatedString("Takeaway to return to: the experiment was gone within a quarter. Hand-off cue: \"After that, the app went quiet for almost five months.\"")
)

val detourKmpNotes = listOf(
    AnnotatedString("Draft from history.md; Annie to replace with her own content."),
    AnnotatedString("In 2025, Michael Ndiritu led the Compose Multiplatform attempt, which started with commit cdd713bd on 2025-06-12."),
    AnnotatedString("Each press of Right reveals one rollback step: drop KMP in commit c42e0cdb on 2025-10-23, then the CI fixes, then the five-layer layout back in commit 26a9e9cf."),
    AnnotatedString("Nobody on the team knows why KMP was dropped, and no commit message says. This is open question 2 in history.md, so do not guess. Takeaway to return to: the reason is not recorded.")
)

val dormantYearsNotes = listOf(
    AnnotatedString("Draft from history.md; Annie to replace with her own content."),
    AnnotatedString("Commit c02c6652, on 2024-11-05, is the Droidcon 2024 work, and Donald's first commit came on 2024-11-13 with efacf25f."),
    AnnotatedString("The two long silences are 211 days with no commits, from 2024-11-13 to 2025-06-12, and 126 days, from 2025-06-19 to 2025-10-23. Those day counts measure commits in git. They do not measure effort or work done outside this repo."),
    AnnotatedString("Commit 8faf624f, on 2025-12-23, is the Navigation 3 move. Hand-off cue: \"That is where the year ended, and 2026 is where the update starts.\"")
)

val launchStackNotes = listOf(
    AnnotatedString("This is the build stack for the 2026 update, set against the last snapshot before it, from December 2025 at commit 26a9e9cf."),
    AnnotatedString("Kotlin went from 2.1.21 to 2.4.21, and the Android Gradle Plugin from 8.10.1 to 9.3.1. Gradle moved to 9.7 in commit 6bb9b99c, and minSdk went to 26 in commit 8f7577d5."),
    AnnotatedString("The snapshots show versions at two dates. They do not show when each change landed, and Gradle and minSdk have no before value in them, so do not quote one."),
    AnnotatedString("Takeaway to return to: the stack was brought current before the modules were split. Hand-off cue: \"Once the stack was current, the module split was next.\"")
)

val launchModulesNotes = listOf(
    AnnotatedString("The module split started on 2026-09-07 with commit d66ca423, and it finished on 2026-09-10 with commit ac81b8ac: 17 modules, and `:presentation` gone."),
    AnnotatedString("Then the count reached 21 modules. Each press of Right reveals one group: core, then feature, then widget and benchmarks."),
    AnnotatedString("The count of 21 says how the project is divided up. It does not measure build time, code size, or whether the split is good."),
    AnnotatedString("Takeaway to return to: the split came with the pull request workflow, which is the same change that let CI guard the codebase. Hand-off cue: \"Splitting the code is one part. Here is what it did to the numbers.\"")
)

val launchPerfNotes = listOf(
    AnnotatedString("These are the 2026 performance gains, and the baseline profile and benchmarks are the cause of them."),
    AnnotatedString("Startup time is 35% faster. Harun Wangereka confirmed this figure on 2026-10-23, and it has no commit SHA, so credit him by name. It is startup time only, not frame time or memory."),
    AnnotatedString("The release APK is 28% smaller in commit 5a42a5a7, on 2026-09-14. The size is 7.4% smaller and gated in CI by commit 1fc20ee5."),
    AnnotatedString("The APK and size figures measure download size, not memory or frame time. Takeaway to return to: startup, download size and the CI gate. Hand-off cue: \"Those are the numbers. Next, the design.\"")
)

val launchDesignNotes = listOf(
    AnnotatedString("The top row is the palette the app used before 2026, and the bottom row is the live 2026 scheme, so the bottom row changes when the theme does."),
    AnnotatedString("Chai moved onto Material 3 and Material 3 Expressive in commit 87d23cb7 on 2026-10-04, and the 2026 droidcon identity, blue and neon green, came in commit eb7c436e on 2026-10-06."),
    AnnotatedString("Takeaway to return to: the same app, with the colours now assigned to Material roles. Hand-off cue: \"Here is the home screen, before and after. Next, what shipped as features.\"")
)

val launchFeaturesNotes = listOf(
    AnnotatedString("Five features and fixes are done and on main. Each press of Right adds one, starting with the cube hero."),
    AnnotatedString("The cube hero came in commit 5941f20f on 2026-10-15, and the Glance widget in commit 4492346b on 2026-10-09."),
    AnnotatedString("The adaptive large-screen layouts, the speaker details fix and the sponsor logo contrast fix are done too, and this talk gives them no commit SHA, so describe them in words."),
    AnnotatedString("Takeaway to return to: the cube and the widget are what people see, and the other three make the app work on more screens. Hand-off cue: \"Everything there is on main. Here is what is not there yet.\"")
)

val launchWipNotes = listOf(
    AnnotatedString("Notifications is still in progress. It lives on the feat/notifications branch, and as of 2026-10-23 it is not on main."),
    AnnotatedString("Four parts are done: channels, reminders, preferences and deep links. Two are still to do: topic subscriptions and reminder actions, which are the notification leftovers on the improvement plan's roadmap."),
    AnnotatedString("Takeaway to return to: this is the one feature that is not finished, and the next slides show what the team plans next. Hand-off cue: \"That is where the 2026 update stands. Now, the plan for what comes after.\""),
    AnnotatedString("Reminder for Donald: check whether notifications has merged to main by talk day, and if it has, change this slide's status line and the done list.")
)

val futurePlanNotes = listOf(
    AnnotatedString("Sign-off needed: the presenters must approve the \"not married to it\" wording before the talk."),
    AnnotatedString("This is the improvement plan, and it was Harun Wangereka's idea, so credit him by name. It lives in the app repo as docs/IMPROVEMENT_PLAN.md, added in commit 62f45e2a."),
    AnnotatedString("It is a living roadmap of pending work only, last pruned on 2026-10-12, so finished items come off it as they land. Say plainly that we are not married to it."),
    AnnotatedString("Takeaway to return to: a living roadmap, not a commitment. Hand-off cue: \"So what is on it? Here are the ranked items.\"")
)

val futureRoadmapNotes = listOf(
    AnnotatedString("Each press of Right reveals one stage, in the order the plan ranks them, starting with the accessibility listening pass, which is next up and needs a person."),
    AnnotatedString("Ticketing and QR waits on a backend question, and the notifications leftovers are topic subscriptions and reminder actions, the two items still to do on the notifications branch."),
    AnnotatedString("The venue map and calendar export are the other surfaces on the plan. AI experiences come last on purpose, behind default-off flags, so they never ship on by default."),
    AnnotatedString("Takeaway to return to: the order is deliberate, and the AI work waits until the basics are in. Hand-off cue: \"Those are the stages. Here is how you can take one on.\"")
)

val futureInviteNotes = listOf(
    AnnotatedString("This is the invitation. Five tracks give a newcomer a lane to pick: Foundations, Design system, Adaptive, AI and Conference ops."),
    AnnotatedString("The tracks are for a small contributor pool, so pick one lane rather than the whole plan. The rotation model means people step in and step back, and that is expected."),
    AnnotatedString("Every maintainer started with a pull request, Harun included, so the way in is the same for you. Takeaway to return to: join with a pull request. Hand-off cue: \"That is the plan. Now, three lessons to close.\"")
)

val lessonsNotes = listOf(
    AnnotatedString("Ian Nthuli delivers all three lessons. Presenters: the shared close, where each speaker takes one lesson from their own segment, is an option for you to decide before the talk."),
    AnnotatedString("Each press of Right reveals one lesson, each with one line of evidence. The first is to write it down for the next person, because the KMP reversal, commit c42e0cdb, was dropped with no recorded reason."),
    AnnotatedString("The second is to let CI guard the codebase while people are away, with the pr.yml workflow and the size gate added in commit d66ca423, and the third is to make it easy to start."),
    AnnotatedString("Takeaway to return to: write it down, let CI guard it, make it easy to start. Hand-off cue: \"That is the talk. Here are your questions.\"")
)

val qaNotes = listOf(
    AnnotatedString("Annunziata Kinya moderates the Q&A, with Donald Isoe and Ian Nthuli on stage. If a question touches the open questions below, say plainly that git does not answer it, and do not guess."),
    AnnotatedString("Backup question one: why did the 2024 and 2025 gaps happen, and who owned the app between them? Git does not say. The maintainership model was the community rotation, with no formal handovers."),
    AnnotatedString("Backup question two: why was KMP dropped on 2025-10-23 in commit c42e0cdb, and rolled back on 2025-11-04 in commit c29b4d01? No commit message gives a reason, and the team does not know one."),
    AnnotatedString("If nobody asks, close on the invitation: join with a pull request, the way every maintainer started.")
)
