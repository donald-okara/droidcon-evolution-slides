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

val titleNotes = listOf(
    AnnotatedString("Welcome. Thank both communities, Android254 and Kotlin Kenya, and the hosts for putting this meetup together."),
    AnnotatedString("Three things today: a story about how this app grew from an I/O clone, a bug hunt, and a build."),
    AnnotatedString("Takeaway to return to: the app began as someone else's app, and eight years later it is the community's own.")
)

val todayNotes = listOf(
    AnnotatedString("Install or update the app now, because you will need it within the hour: the talk starts at 10:05, the bounty at 10:55, the feature bash at 13:05, and results at 15:35."),
    AnnotatedString("If you have a ticket and did not add it at home, wait and use mobile data for the lookup."),
    AnnotatedString("The ticket lookup is rate limited per Wi-Fi address, so one shared connection can hit the limit before everyone has finished.")
)

val sameAppNotes = listOf(
    AnnotatedString("Both of these screenshots are the droidcon Kenya app. The one on the left began as a copy of somebody else's app."),
    AnnotatedString("Everything between them is in git, and we read all of it."),
    AnnotatedString("The counter shows how many commits the five app repos have between them. It does not measure effort, review time, or how large each change was.")
)

val familyTreeNotes = listOf(
    AnnotatedString("Each press lights the next stage on the timeline, in order: 2018, 2019, 2020, 2022, 2023, 2025, then 2026."),
    AnnotatedString("Hover or click a stage to open its details card. Every fact on the card is cited in the overview, so you can point to it if someone asks."),
    AnnotatedString("A new repo almost every year until 2023. After that, one repo with no year in its name. Keep the gap in 2021 in mind; we come back to it.")
)

val seasonsNotes = listOf(
    AnnotatedString("It's a volunteer project, and volunteers work to a deadline. The quiet stretches matter as much as the spikes."),
    AnnotatedString("The chart counts commits per month in droidconKeKotlin. It does not measure effort, review time, or how big each commit was."),
    AnnotatedString("Takeaway to return to: hold on to the quiet months for the end of the talk.")
)

val y2018FirstNotes = listOf(
    AnnotatedString("Kotlin had been official on Android for a year, and this app still started in Java."),
    AnnotatedString("A Kotlin branch ran beside the Java code from August 2018 and took over by April 2019, and the chart shows the Java count collapsing to two that month."),
    AnnotatedString("Takeaway to return to: real migrations look like that, a branch, then a long overlap, then a switch. Hand-off cue: \"Here is where the app started, copied from somebody else.\"")
)

val y2018CloneNotes = listOf(
    AnnotatedString("Give this one time. The first droidcon Kenya app was a clone of Google's I/O app, and the highlight sweeps across the two READMEs, which share the same line word for word."),
    AnnotatedString("The screens had the same names, so fragment_info, fragment_event and fragment_travel came across unchanged. The I/O code was already Kotlin, and we cloned its screens in Java anyway."),
    AnnotatedString("Takeaway to return to: copy the best app you can find and learn from it. The rest of this talk is the app slowly becoming its own.")
)

val y2019AllJetpackNotes = listOf(
    AnnotatedString("The app was 100 percent Kotlin from day one, and it was built on Jetpack Navigation, Safe Args, LiveData and Firestore KTX."),
    AnnotatedString("The community's name went into the package that year, com.android254, and it is still there today."),
    AnnotatedString("Still one module. The README promised phones and tablets. Remember that promise.")
)

val y2020ModularNotes = listOf(
    AnnotatedString("This is the biggest jump in ambition: 13 modules, 7 of them dynamic features, plus static analysis and CI through GitHub Actions."),
    AnnotatedString("Dynamic features were the modularisation advice of the time. Nobody uses them for this now, but the team learned modules here."),
    AnnotatedString("On the I/O thread, this is the first README without the I/O app's words, and the first architecture the team designed for itself. Takeaway to return to: the team learned modules here.")
)

val y2020NoConferenceNotes = listOf(
    AnnotatedString("Mic hand-over: pass the mic to a guest from the 2020 team for 90 seconds, and ask what building an app for a conference that was cancelled was like."),
    AnnotatedString("Both apps call themselves the third droidcon, one for August 2020 and one for November 2022. Nothing was held in 2021, and the shrug is for that gap."),
    AnnotatedString("Takeaway to return to: the 2020 team built something for a conference that never happened. Hand-off cue: \"Now the 2022 Compose year.\"")
)

val y2022ComposeNotes = listOf(
    AnnotatedString("Compose 1.0 came out in July 2021, and a year later this volunteer team shipped a Compose app to the Play Store. It was built on alpha Material 3, which took some nerve."),
    AnnotatedString("The chart shows commits per month in droidconKE2022Android: 212 in July alone, then 80 to 121 a month. Commits count activity, not effort or review time, and they do not measure how big each change was."),
    AnnotatedString("Takeaway to return to: sign-in was switched off the day before the conference, so ship what works. Hand-off cue: \"Standing on its own came next, with chai.\"")
)

val chaiNotes = listOf(
    AnnotatedString("Remember the I/O clone from 2018? This is the year the app stopped looking like anyone else's. The swatches spill in one at a time, so let each one land before you go on."),
    AnnotatedString("The team had its own designs and named its design system after tea. Chai got its own repo in March 2023, and it still lives in the app as core:designsystem, with the same com.droidconke.chai package."),
    AnnotatedString("The swatches show the four theme colours, not a full palette, so do not describe them as the complete chai set. Takeaway to return to: the same app, with its own look. Hand-off cue: \"In 2023 the code moved into one repo and stayed there.\"")
)

val oneRepoNotes = listOf(
    AnnotatedString("Every earlier year started a fresh repo, and every fresh repo reset the contributor list. In February 2023 the code moved over and stayed, and the count shows how many files came along on day three."),
    AnnotatedString("The 280 files and 15,888 lines come from commit f3b81067, and 173 of the 177 Kotlin files kept their path. The count of files does not measure code quality, test coverage or how much was rewritten."),
    AnnotatedString("Takeaway to return to: this was a carry-forward, not a rewrite. Hand-off cue: \"Then came the busiest year for people.\"")
)

val communityPeakNotes = listOf(
    AnnotatedString("2023 was the busiest year for people, with about 41 distinct authors, and the confetti is for them. Name a few: Hannah Olukoye's convention plugins, Peter Chege's offline-first work, Kenneth Mathari's injected dispatchers and robert-nganga's loading states."),
    AnnotatedString("The chart counts distinct author names per year with bots left out, and the names are approximate, because some people commit under two names. It does not measure effort, review time or how much each person did."),
    AnnotatedString("Takeaway to return to: the peak was a crowd, and most of them stepped back, with no formal handover. Hand-off cue: \"Quiet years come next, and they are fine.\"")
)

val quietYearNotes = listOf(
    AnnotatedString("Quiet years happen in volunteer projects, and that is fine. The 2024 change was the Droidcon 2024 event slug, and it touched three lines across three files."),
    AnnotatedString("The 55 commits in 2024 count activity in this repo only. They do not measure work done outside it, or whether the three lines were the only thing anyone cared about that year."),
    AnnotatedString("Takeaway to return to: what matters is whether the next person can pick it up. Hand-off cue: \"That question shaped the Kotlin Multiplatform thread.\"")
)

val kmpThreadNotes = listOf(
    AnnotatedString("This is the Kotlin Kenya corner. KMP was tried twice, in 2024 and in 2025, and both times it was taken out to keep the Android app shippable. That is not a failure, it is a decision about what ships."),
    AnnotatedString("The chart shows the Gradle module count from D8: 7, then 11, then 14, then back to 7 when KMP was removed, and 20 by October 2026. The highlighted bars are the KMP stretch. A module count does not measure build time, code size or how well the split works."),
    AnnotatedString("Today core:model is JVM-only, so a third attempt would be a port, not a rewrite. The plan asks one question: does someone want to build the iOS app? Takeaway to return to: a port, not a rewrite. Hand-off cue: \"Last, the newest work: Navigation 3.\"")
)

val navigation3Notes = listOf(
    AnnotatedString("donald-okara carried the app through the winter. The Navigation 3 move with multiple back stacks landed in December 2025, in commit 8faf624f and the commits after it."),
    AnnotatedString("Most of what the app now does with live sessions started here. The Now and Up next chips are the live rail, and the recording shows tabs switching with each back stack kept."),
    AnnotatedString("The recording shows the behaviour, not the performance of it, so do not quote timings from it. Takeaway to return to: the back stacks kept, so the app remembers where you were. Hand-off cue: \"That is where 2025 ended. Now, 2026.\"")
)
