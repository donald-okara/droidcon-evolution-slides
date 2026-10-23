# Phase 3: Walkthrough

## Current state

- Phase closed. All 12 tasks done: reveal steps in `DeckNavigator` and web sync, shared slide blocks and stat tile, content and notes for all 19 slides, placeholder scaffolding removed. The full build passed.
- Review 1 (2026-10-23): changes requested. Archived anyway; its comments (more pleasant and delightful slides, an evolution timeline with hover details and labelled stages) moved to phase 4.
- Deferred: Rebuild the deck from Harun Wangereka's 2018-to-2026 meetup overview (saved at `docs/talk/harun-overview.md`): new slide list, the "I/O clone to its own app" thread, an animated evolution timeline, a polish pass, and the 22% versus 35% startup figure. Moved to phase 4 on 2026-10-23.
- Open: desktop manual checks (reveal counts, stat tiles, window and web sync) were never done by the user.
- Open for the presenters: KMP note attribution (Task 7); notifications merge status by talk day (Task 9); "not married to it" sign-off and the "Harun included" line (Task 10); shared close (Task 11).
- Known conflict: `launch-perf` shows startup 35% faster (Harun, 2026-10-23), while his overview says cold start -22% (1,335 to 1,047 ms, `docs/performance.md`).

## How to verify

- Task 1: `./gradlew :core:domain:compileKotlinJvm :core:domain:compileKotlinWasmJs` passes; `git diff --stat dd4c27b -- shared/components` is empty.

- Task 2: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes. User: on `:desktopApp:run`, one Right press on `opening-title` changes slides.
- Task 3: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes.
- Task 4: same compile check as Task 3 passes.
- Task 5: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs` passes. User: look at `opening-title` and `opening-thesis` on `:desktopApp:run`.
- Task 6: compile check passes. User: on desktop, Right reveals 4 steps on `history-bootstrap` and 3 on `history-plugins`.
- Task 7: compile check passes. User: on desktop, Right reveals 3 rollback items on `detour-kmp`.
- Task 8: compile check passes. User: on desktop, the three perf tiles count up, shrink, show down arrows, all green; Right reveals 3 module groups.
- Task 9: compile check passes. User: on desktop, Right reveals 5 items on `launch-features` and 6 on `launch-wip`; swatch labels fit on `launch-design`.
- Task 10: compile check passes. User: on desktop, Right reveals 5 stages on `future-roadmap`.
- Task 11: compile check passes. User: on desktop, Right reveals 3 lessons on `lessons`; `qa` shows no footer.
- Task 12: `grep -rn "PlaceholderSlide\|placeholderNotes" segments composeApp/src` is empty; `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes; `git diff --stat dd4c27b -- shared/components` is empty.

## Changes by task

### Task 1: Reveal steps in the navigator
- `SlideConfig.steps`, `DeckBuilder.slide(steps)`, `DeckNavigator.currentStep` with step-aware `next()`/`previous()`, `goTo(index, step)` (step clamped to `0..steps`), `DeckSyncState.step`, `LocalRevealStep`.
- `previous()` into a slide sets its step to that slide's `steps`. `DeckSyncState.step` defaults to 0, so older payloads still decode.
- `LocalRevealStep` is a non-static `compositionLocalOf`, since it changes on every press.
- Reviewer NOTE: `next()`/`previous()` set `direction` even when nothing moves, as before.

### Task 2: Wire steps into the deck host and web sync
- `DeckHost.kt`: each `slides[index].Render()` is wrapped in `CompositionLocalProvider(LocalRevealStep provides ...)`; current index gets `navigator.currentStep`, other (outgoing) indexes get `slides[index].steps`.
- `WebMain.kt`: `currentStep` added to the `LaunchedEffect` keys and written into `DeckSyncState`; the `storage` listener calls `goTo(slideIndex, step)`. TOC call unchanged.

### Task 3: Shared slide building blocks
- New `SlideBlocks.kt`: `SlideTitle`, `BulletList`, `CommitTag`, `MediaPlaceholder` (solid outlined border, since Compose has no built-in dashed border), internal `BulletRow`.
- New `Reveal.kt`: `RevealItem(index, content)` (alpha fade, `tween(300)`, keeps its space when hidden) and `RevealBulletList` (item i uses step i+1).
- Fix round: the fade first used the default spring; changed to `tween(300)` after review.

### Task 4: Stat tile
- New `StatTile.kt`: `StatTile(value, unit, label, direction, modifier, improvement, sha, source, decimals)` and `StatDirection { Increase, Decrease }`. Count-up via one `Animatable` (1200 ms); decrease shrinks the number with `graphicsLayer`; arrow fades in after 600 ms and slides 24 dp toward its settled position.
- Colour: improvement uses `secondary` on light and `primary` on dark; not improvement uses `error`.
- Reviewer NOTE: `modifier` sits after `direction` in the parameter list rather than last; use named arguments at call sites.

### Task 5: Opening slides
- `OpeningSlides.kt`: `OpeningTitleSlide` (title, subtitle, three presenter cards with photo placeholders) and `OpeningThesisSlide` (thesis verbatim from the outline, then History / About to launch / Future chips). Cube hero skipped (optional in the task).
- `SlidesNotes.kt`: real `openingTitleNotes` and `openingThesisNotes` for Annunziata Kinya. No "draft, Annie to replace" line, since the task doesn't ask for it.

### Task 6: History: bootstrap, plugins, churn
- `HistorySlides.kt`: `HistoryBootstrapSlide` (five layer boxes, `740ce113`, reveal; `HISTORY_BOOTSTRAP_STEPS = 4`), `HistoryPluginsSlide` (`6a6845e4`, `996ca6fe`, reveal; `HISTORY_PLUGINS_STEPS = 3`), `HistoryChurnSlide` (no reveal; era counts, `91322d29`, silence after 2023-11-09). Steps registered in `SkiPresentationSlides.kt`.
- `SlidesNotes.kt`: real notes for the three history slides; each opens with a "Draft from history.md; Annie to replace" line. No on-slide draft marker.
- Reviewer NOTE: WasmJs compile was not run by the reviewer; the implementer's run passed both targets.

### Task 7: Detours: KMM, KMP, dormant years
- `HistorySlides.kt`: `DetourKmmSlide` (`3433931f`, `26f6a3d0`, why it stopped: not recorded), `DetourKmpSlide` (`cdd713bd`, 3-step rollback reveal, `DETOUR_KMP_STEPS = 3`, registered), `DormantYearsSlide` (static timeline with 211-day and 126-day gaps, labelled as commits, not effort). Removed unused `ANNUNZIATA` constant and `PlaceholderSlide` import.
- `SlidesNotes.kt`: real notes for the three slides, each with the draft line; KMP notes say the reason is not recorded (history.md open question 2).
- Reviewer NOTE: the KMP note credits Michael Ndiritu with leading the attempt, but `cdd713bd` is authored by chepsiror per `history.md`. Wording is accurate to the era; non-blocking.

### Task 8: Launch: stack, modules, perf
- `LaunchSlides.kt`: `LaunchStackSlide` (before 2025-12 `26a9e9cf` vs after 2026-10 `575e6055`; Gradle and minSdk before values "not in snapshots"), `LaunchModulesSlide` (3 groups: core:*, feature:*, widget and benchmarks; `LAUNCH_MODULES_STEPS = 3`, registered), `LaunchPerfSlide` (three `StatTile`s, all Decrease and green; 35% sourced to Harun Wangereka, others carry SHAs; 7.4% uses `decimals = 1`).
- `SlidesNotes.kt`: real launch notes for Donald Isoe, no draft line. Perf note says APK and size figures are download size and the 35% figure is startup only.
- `docs/talk/history.md` and `outline.md`: added the sourced "startup time 35% faster (Harun Wangereka)" fact, as the task asks.
- Reviewer NOTE: `launchModulesNotes` says the split came with the pull request workflow (outline's `pr.yml` claim, `d66ca423`); supported, optionally name the commit.

### Task 9: Launch: design, features, WIP
- `LaunchSlides.kt`: `LaunchDesignSlide` (`Pre2026Palette` swatches vs live `colorScheme` swatches with hex labels, `87d23cb7`, `eb7c436e`, media placeholder), `LaunchFeaturesSlide` (5 reveals, `LAUNCH_FEATURES_STEPS = 5`; only cube hero and widget carry SHAs, as the outline gives no others), `LaunchWipSlide` (6 reveals: four done, two muted still to do; `LAUNCH_WIP_STEPS = 6`). Steps registered.
- `SlidesNotes.kt`: real notes for Donald Isoe, with a reminder to check whether notifications has merged by talk day.
- Reviewer NOTE: swatch labels such as "secondaryContainer" and "ChaiLightGrey" may wrap on a small window.

### Task 10: Future slides
- `FutureSlides.kt`: `FuturePlanSlide` (plan path, `62f45e2a`, Harun credit, "not married to it"), `FutureRoadmapSlide` (5 revealed stages, last muted; `FUTURE_ROADMAP_STEPS = 5`, registered), `FutureInviteSlide` (five track chips, join line).
- `SlidesNotes.kt`: real notes for Ian Nthuli. `futurePlanNotes` opens with a "Sign-off needed" line for the "not married to it" wording. `docs/IMPROVEMENT_PLAN.md` is not in this repo, so facts come from the outline only.
- Reviewer NOTE: "Harun included" in `futureInviteNotes` is not backed by the outline or history; drop it or verify before the talk. Left as is, since fixing it is outside the task's pass criteria.

### Task 11: Close slides
- `CloseSlides.kt`: `LessonsSlide` (3 revealed lessons, headline plus one evidence line; `LESSONS_STEPS = 3`, registered) and `QaSlide` ("Questions?" with the three presenter names; `showFooter = false` kept).
- `SlidesNotes.kt`: real `lessonsNotes` (Ian delivers all three; shared close flagged as the presenters' decision) and `qaNotes` (Annunziata Kinya moderates; backup questions from `history.md` open questions 1 and 2). The private `placeholderNotes()` helper is now unused; Task 12 removes it.
- Reviewer NOTE: lessons notes say "Each press of Right"; keep consistent with other reveal slides' wording.

### Task 12: Remove placeholder scaffolding and run the full build
- Deleted `PlaceholderSlide.kt`; removed `placeholderNotes` and its header comment from `SlidesNotes.kt`; removed an unused `Box` import in `StatTile.kt`. Presenter-name constants in `OpeningSlides.kt` are still used once each, so they stay.
- Reviewer NOTE: the build finished in 2s, probably from the Gradle cache; it still reflects current sources.

## Deviations from spec

None.
