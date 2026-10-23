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

val twoReadersNotes = listOf(
    AnnotatedString("The fix for quiet years is to write the things down. The same file, AGENTS.md, now briefs a new contributor and a coding agent, and it was dated 13 August 2026."),
    AnnotatedString("The 3,500-line plan deletes items as they are done, so it stays a plan and not a log. The line count does not measure how good the plan is, only how much of it there is."),
    AnnotatedString("Takeaway to return to: this afternoon we test whether the file works for a new person, so say it as a test and not a promise. Hand-off cue: \"Seven modules to twenty.\"")
)

val modulesNotes = listOf(
    AnnotatedString("The counter runs from 7 modules on 1 August, after KMP was removed, to 20 by 22 October. The 17 on 11 September is the split that took out :presentation, so the climb was not one step."),
    AnnotatedString("A module count does not measure build time, code size or whether the split is a good one. It counts Gradle modules and nothing else."),
    AnnotatedString("Takeaway to return to: the rules matter more than the count. The third rule came from a feature's tests reaching into another feature's fakes, which is why core:testing exists. Hand-off cue: \"Now the numbers, measured.\"")
)

val measuredNotes = listOf(
    AnnotatedString("Every figure here was measured on the CS50C at API 34, as the median of 15 runs. Cold start went from 1,335 to 1,047 ms, which is 22 percent down, and the release APK went from 6.4 to 4.6 MB, which is 28 percent down."),
    AnnotatedString("The cold start figure excludes warm starts, so it says nothing about a process that is already running. The counters also do not measure frame time, jank or battery use, and the download figure is Pixel-class, not a promise for every phone."),
    AnnotatedString("Takeaway to return to: every number has a device, a median and a caveat. Full AOT was slower at 1,228 ms, and the doc keeps it as an experiment that did not help. Hand-off cue: \"Last, the new look.\"")
)

val newLookNotes = listOf(
    AnnotatedString("The tablet promise from 2018 is finally kept. The sessions screen now adapts to phones, foldables, tablets and desktop windows, and the four placeholders are the goldens for each one."),
    AnnotatedString("Credit Joel Kanyi for the shared-element transitions and mutukuian for the redesigned speakers. The WCAG AA check covers every colour pair, and it does not measure how the palette feels to a reader with low vision."),
    AnnotatedString("Takeaway to return to: nothing of the I/O app is left on screen, and the brand, the motion and the cube are droidcon Kenya's own. Hand-off cue: \"That is 2026. The next act is the live demo.\"")
)

val cubeWidgetNotes = listOf(
    AnnotatedString("The cube is pure play. It is drawn on a Compose canvas, and the plan has a list of easter eggs it could grow, so say that before anyone asks whether it does anything useful."),
    AnnotatedString("The widget is donald-okara's, and it was written as a design spec first, then a plan, then code. A spec does not prove the widget works on every launcher, so the phone demo on the next slides is the proof."),
    AnnotatedString("Takeaway to return to: spec, plan, code, and each one is a file you can read. Hand-off cue: \"The app also follows the conference. Let's see it.\"")
)

val followsConferenceNotes = listOf(
    AnnotatedString("The app reads the clock and the schedule, and the home screen changes with the event: a countdown before, today's agenda with clashes during, and a thank-you with a feedback link after."),
    AnnotatedString("The three screens are goldens, so they are placeholders here. The phase comes from the clock and the session times, not from a switch, which is why the live demo can move the phone's date to show the agenda."),
    AnnotatedString("Takeaway to return to: the app changes with the event, not with a release. Hand-off cue: \"Let's see it live.\"")
)

val liveDemoNotes = listOf(
    AnnotatedString("This is the live demo, about four minutes of the act. Mirror the phone with scrcpy, and have the fallback video recorded on Friday ready to play if the mirror drops or the phone misbehaves."),
    AnnotatedString("Set the phone's date to conference day 1 for step 2, and the home screen switches to the agenda with clashes. Rehearse that change first. Set the date back before step 5, as rehearsed, so the purchase runs on the right day."),
    AnnotatedString("Step 5 needs the chosen volunteer on mobile data, not venue Wi-Fi. If the email is slow, carry on and come back to it at the end of act 7; if it never arrives, use the ticket from Friday's dry run. Do not scan the pass with the real gate check-in."),
    AnnotatedString("Takeaway to return to: turn on airplane mode and reopen the pass, and it is still there. The checks fill as each step arrives, so keep to the order. Hand-off cue: \"Now the stack, layer by layer.\"")
)

val stackNotes = listOf(
    AnnotatedString("This slide is optional, so cut it if the demo ran long. Read across a row to see one layer change over the years, from the Java and XML of 2018 to Compose and convention plugins now."),
    AnnotatedString("The table is condensed to five layers, so it leaves out navigation, images, async and the quality tools in the full table. It shows the shape of the change, not the whole inventory of libraries."),
    AnnotatedString("Takeaway to return to: Android's history is in the rows. Hand-off cue: \"And how the code grew, in lines and in tests.\"")
)

val codeGrewNotes = listOf(
    AnnotatedString("Flat for three years, then a step. Kotlin lines went from 14.7 thousand in March 2023 to 39.9 thousand on 22 October 2026, and test files went from 24 to 110."),
    AnnotatedString("Lines of code do not measure quality, complexity or how much of the app a user touches. The test file count does not measure coverage, so it does not say whether those tests check the right things."),
    AnnotatedString("The 180 screenshot goldens are a check that screens still look the way they did, and they do not show that a screen works. Takeaway to return to: the tests grew faster than the code. Hand-off cue: \"Now, what it means.\"")
)

val lostAndFoundNotes = listOf(
    AnnotatedString("This is the lost-and-found slide, and it is the first of act 7. Read the table across each row, from the idea, to what it was then, to what became of it now. Keep it light: this is the room remembering ideas, not a list of failures."),
    AnnotatedString("The first three rows are kept or in progress. The starred-session pushes are in pull request #448, which is still open, so say that plainly and do not promise a date. The venue map was planned in section 11.6 and needs an SVG before it can ship. Phones and tablets were promised in 2018 and are kept in 2026, which is the one good news line on the slide."),
    AnnotatedString("The last row is the question. Travel details existed in the 2018 and 2019 repos and are gone now. Ask the room directly whether they want them back, and let the answer be the hand-off to the bounty and the bash. Sources are the package lists of the 2018 and 2019 repos, plan section 11.6, and issues #410 and #448."),
    AnnotatedString("Takeaway to return to: some of the best ideas are already in the old repos. Hand-off cue: \"The code grew, and the community didn't. Here is the chart.\"")
)

val uncomfortableChartNotes = listOf(
    AnnotatedString("This is the uncomfortable chart. The left bars are people committing per year, distinct author names with bots excluded, so the figures are approximate because some people commit under two names. The figures are 41 in 2023, then 8, 9 and 5 in 2024, 2025 and 2026."),
    AnnotatedString("The right bars are Kotlin lines of code in thousands, from the same snapshots as the line on the previous slide: 14.7 thousand in March 2023, 17.4 in December 2024, 27.8 on 1 October 2026 and 39.9 on 22 October 2026. Each chart is scaled to its own largest value, so read the captions, not the heights, when you compare them."),
    AnnotatedString("Say it plainly: the year with the most code had the fewest people. That is not a boast. It is a risk, and it is the reason for this afternoon. Let the flat community bar sit for a beat before the code bar finishes growing."),
    AnnotatedString("Takeaway to return to: the code grew, and the community didn't. Hand-off cue: \"Here is what eight years teach us.\"")
)

val eightYearsTeachNotes = listOf(
    AnnotatedString("This slide has four lessons, one per press of Right. Keep each one to one sentence, and say it in the order shown. Each lesson appears with its number, and the hidden ones keep their space, so the list does not jump."),
    AnnotatedString("Lesson one closes the I/O thread. We started as a clone of Google's app, and we became our own. That is a good way to begin anything, so say it as a thank-you to the first version. If there is time, point at the 2018 I/O-clone thumbnail from the earlier slide."),
    AnnotatedString("Lesson two is that repos with a year in the name reset the community, so each new one started the count again. Lesson three is that removing an experiment is still progress. Lesson four is write it down for whoever comes next, human or agent, and it sets up the bash."),
    AnnotatedString("Takeaway to return to: copy the best, then stand on your own, and write it down. Hand-off cue: \"Thank you. Here are the people who wrote it.\"")
)

val thankYouNotes = listOf(
    AnnotatedString("This is the thank-you slide. The names are grouped by era: 2018 and 2019, 2020, 2022, 2023 to 2025, and 2026. The names pop in as the slide opens, one after another, so give them a moment to land before you talk."),
    AnnotatedString("Ask anyone from the list who is in the room to stand up. Do not read every name out. The list was taken from git shortlog per repo, and it was checked against the README contributors section; check it again against the README on the day, because people join and leave."),
    AnnotatedString("Pass the mic: the strongest version of this talk is not one voice. Invite one person per era for about 90 seconds each, with one memory and one decision they would make differently. Candidates from the log, to ask on the day: 2018 and 2019, Michael Bukachi. 2020, Oliver Muthomi, Jabez Magomere or David Odari. 2022, Evans Chepsiror, Gibson Silali or Waitara. Chai, tamzi. 2023, Hannah Olukoye or Peter Chege. 2025, Michael Ndiritu on Kotlin Multiplatform. 2026, donald-okara, Joel Kanyi or mutukuian."),
    AnnotatedString("Takeaway to return to: the app is ours because all of these people kept it going, era by era. Hand-off cue: \"Now, let's break it.\"")
)

val breakItNotes = listOf(
    AnnotatedString("This is the hand-over to the bounty. The cockroach wiggles on screen, which is the cue for the bug hunt. Say that Msako wa Mende, the cockroach crackdown, starts in 10 minutes, and that the bounty lead takes over from here."),
    AnnotatedString("Before you hand over, check the on-screen line: phone ready, app updated and GitHub account open. The bounty briefing slides carry the QR code to the bounty issue template, so have them ready to show next."),
    AnnotatedString("If this slide is shown without a footer, the talk ends cleanly here. Hand the stage to the bounty lead and step aside. Takeaway to return to: break it, and file what you find so the next person can fix it. Hand-off cue: \"Over to the bounty lead.\"")
)
