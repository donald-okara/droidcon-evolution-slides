# Phase 3: Tasks

Rules for every task:
- Facts only from `docs/talk/outline.md`, `docs/talk/history.md` and `docs/talk/design-inventory.md`. Don't open the droidcon app repo.
- Notes follow `docs/speaker-notes.md`: 2 to 4 spoken sentences per slide, numbers and SHAs in the notes, and a "what this number does not measure" line for every on-screen number.
- Each reveal slide file exposes `const val <SLIDE>_STEPS`, used both by the composable and by `steps =` in `SkiPresentationSlides.kt`.
- The standard check `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs` passes and `git diff --stat dd4c27b -- shared/components` is empty. Visual checks are left to the user on `./gradlew :desktopApp:run`; don't `pkill` desktopApp.

```
1. Reveal steps in :core:domain
   └─> 2. Wire steps into DeckHost and web sync
         └─> 3. Shared slide blocks (title, bullets, reveal, SHA tag, media placeholder)
               ├─> 4. Stat tile
               │     └─> 8. Launch: stack, modules, perf
               ├─> 5. Opening slides
               ├─> 6. History: bootstrap, plugins, churn
               │     └─> 7. Detours: KMM, KMP, dormant years
               ├─> 9. Launch: design, features, WIP   (after 8: same files)
               ├─> 10. Future slides
               └─> 11. Close slides
                     └─> 12. Remove placeholder scaffolding and full build   (after 5 to 11)
```

Tasks 5 to 11 all edit `SlidesNotes.kt` and `SkiPresentationSlides.kt`, so run them one at a time.

---

### Task 1: Reveal steps in the navigator
**Serves:** R1
**Files:** core/domain/src/commonMain/kotlin/ke/don/domain/SlideConfig.kt (edit), core/domain/src/commonMain/kotlin/ke/don/domain/DeckBuilder.kt (edit), core/domain/src/commonMain/kotlin/ke/don/domain/DeckNavigator.kt (edit), core/domain/src/commonMain/kotlin/ke/don/domain/DeckSyncState.kt (edit), core/domain/src/commonMain/kotlin/ke/don/domain/CompositionLocals.kt (edit)
**Pattern:** core/domain/src/commonMain/kotlin/ke/don/domain/DeckNavigator.kt

- [x] Add `val steps: Int = 0` to `SlideConfig`, and a `steps: Int = 0` parameter to `DeckBuilder.slide(...)` that is passed through.
- [x] In `DeckNavigator` add `var currentStep by mutableStateOf(0)` with a private setter. `next()`: if `currentStep < currentSlide.steps`, increment the step; otherwise go to the next slide with step 0. `previous()`: if `currentStep > 0`, decrement it; otherwise go to the previous slide and set its step to that slide's `steps`. Keep `direction` set as today. At the first or last slide with nothing left to step, do nothing.
- [x] Change `goTo(index: Int)` to `goTo(index: Int, step: Int = 0)`. It sets both, with the step clamped to `0..slides[index].steps`, and returns early only when both index and step are unchanged. Existing callers keep step 0.
- [x] Add `val step: Int = 0` to `DeckSyncState`.
- [x] Add `val LocalRevealStep = compositionLocalOf { Int.MAX_VALUE }` to `CompositionLocals.kt`, with a one-line comment saying that the default shows everything outside a deck.
- [x] Check: `./gradlew :core:domain:compileKotlinJvm :core:domain:compileKotlinWasmJs` passes; `git diff --stat dd4c27b -- shared/components` is empty.
- [x] Commit: `feat: reveal steps in DeckNavigator`

### Task 2: Wire steps into the deck host and web sync
**Serves:** R1, R2
**Files:** composeApp/src/commonMain/kotlin/ke/don/ski/presentation/DeckHost.kt (edit), webApp/src/commonMain/kotlin/ke/don/ski/web/WebMain.kt (edit)
**Pattern:** composeApp/src/commonMain/kotlin/ke/don/ski/presentation/DeckHost.kt

- [ ] In `DeckHost`, wrap `slides[index].Render()` in `CompositionLocalProvider(LocalRevealStep provides ...)`. The current index gets `navigator.currentStep`. An outgoing index gets `slides[index].steps` (fully revealed during the fade).
- [ ] In `WebMain.kt`, add `navigator.currentStep` to the `LaunchedEffect` keys, write `step = navigator.currentStep` into `DeckSyncState`, and call `navigator.goTo(syncState.slideIndex, syncState.step)` in the `storage` listener.
- [ ] Leave the TOC call `navigator.goTo(it)` in `DeckScaffolding.kt:198` as it is (step 0 by default).
- [ ] Check: the standard check plus `./gradlew :webApp:wasmJsBrowserDistribution` passes. The user checks that `opening-title` still changes slides on one Right press on desktop.
- [ ] Commit: `feat: provide reveal step to slides and sync it on web`

### Task 3: Shared slide building blocks
**Serves:** R3
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt (new), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/Reveal.kt (new)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/PlaceholderSlide.kt

- [ ] In `SlideBlocks.kt`, add `SlideTitle(title: String, kicker: String? = null)` (kicker in `labelLarge`/`primary`, title in `displaySmall` bold), `BulletList(items: List<String>)` (a green dot from `colorScheme.secondaryContainer`, then the text in `headlineSmall`), `CommitTag(sha: String)` (small rounded tag, `FontFamily.Monospace`, `labelMedium`, `onSurfaceVariant` on `surfaceVariant`), and `MediaPlaceholder(label: String, modifier)` (dashed or outlined `outline`-coloured box with the label centred). Give each a KDoc, like `PlaceholderSlide`.
- [ ] In `Reveal.kt`, add `RevealItem(index: Int, content: @Composable () -> Unit)`, which reads `LocalRevealStep.current` and shows its content at full alpha when `step >= index` (1-based) and alpha 0 otherwise, animated with `animateFloatAsState` (about 300 ms). Hidden items keep their space, so the layout doesn't jump. Also add `RevealBulletList(items: List<String>)`, which renders item `i` inside `RevealItem(i + 1)`.
- [ ] Use `Values.Dimens` padding as `PlaceholderSlide` does.
- [ ] Check: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes.
- [ ] Commit: `feat: shared droidcon slide blocks and reveal item`

### Task 4: Stat tile
**Serves:** R3, R6
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/StatTile.kt (new)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt

- [ ] Add `enum class StatDirection { Increase, Decrease }` and `StatTile(value: Float, unit: String, label: String, direction: StatDirection, improvement: Boolean = true, sha: String? = null, source: String? = null, decimals: Int = 0, modifier)`.
- [ ] The number animates from 0 to `value` over about 1200 ms when the tile first composes (`Animatable` in a `LaunchedEffect(Unit)`, `FastOutSlowInEasing`). It is shown with `decimals` places plus `unit` (for example "7.4%") in `displayLarge` bold. Use string padding, not `String.format`, because this is common code.
- [ ] For `Decrease`, the number also shrinks progressively during the count. Drive `Modifier.graphicsLayer { scaleX = s; scaleY = s }` from the same animation progress `p` (0 to 1), with `s = 1f - p * (value / 100f) * 0.5f` (so 28% ends at 0.86 scale). Use transform origin centre, so the tile doesn't resize. `Increase` keeps scale 1.
- [ ] The arrow (`Icons.Default.ArrowUpward` or `ArrowDownward`, or a drawn triangle if those icons aren't available in common) fades and slides in from 24 dp below or above, then settles. Up for `Increase`, down for `Decrease`.
- [ ] When `improvement` is true, the colour is the scheme's green: `colorScheme.secondary` (0xFF06752A) in light and `colorScheme.primary` (0xFF00FF4F) in dark. Pick dark when `colorScheme.background.luminance() < 0.5f`, so the tile follows the deck theme rather than the system setting. When it is false, the colour is `colorScheme.error`. The colour depends only on `improvement`, never on `direction`. It applies to the number and the arrow. The label is in `onSurface`. Under the label sits `CommitTag(sha)` when given, else `source` in `labelMedium`/`onSurfaceVariant` when given.
- [ ] Put the tile in a rounded `surfaceContainer` card with `Values.Dimens` padding.
- [ ] Check: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes.
- [ ] Commit: `feat: animated stat tile`

### Task 5: Opening slides
**Serves:** R4, R9
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/opening/OpeningSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt

- [ ] `OpeningTitleSlide`: the title "The droidcon Kenya app, 2023 to 2026", a subtitle saying it is the community's shared teaching codebase (`history.md` era 5, README line), and a row of three presenter cards (`MediaPlaceholder("Photo: <name>")` above the name and segment: Annunziata Kinya / History, Donald Isoe / About to launch, Ian Nthuli / The future). The cube hero (`ke.don.design.cube.CubeHero`) is optional, used only if it fits beside the title without crowding.
- [ ] `OpeningThesisSlide`: the outline's thesis as a quote in `headlineMedium`, then three arc chips: History, About to launch, Future.
- [ ] Replace `openingTitleNotes` and `openingThesisNotes` with real notes for Annunziata Kinya: the welcome and introductions, then the thesis said aloud and a hand-off cue into history.
- [ ] Check: the standard check.
- [ ] Commit: `feat: opening slides content and notes`

### Task 6: History, bootstrap to churn
**Serves:** R5, R9
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/history/HistorySlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/Reveal.kt

- [ ] `HistoryBootstrapSlide`: kicker "2023", the five layered modules (app, chai, data, domain, presentation) shown as a static row of labelled boxes, `CommitTag("740ce113")`, then `RevealBulletList(listOf("Hilt", "Room", "Ktor", "Compose"))`. `HISTORY_BOOTSTRAP_STEPS = 4`.
- [ ] `HistoryPluginsSlide`: convention plugins replace per-module build setup, with `CommitTag`s `6a6845e4` and `996ca6fe`, and a reveal of the Hilt, Compose and Kotlin application plugins. `HISTORY_PLUGINS_STEPS = 3`.
- [ ] `HistoryChurnSlide`: contributor rotation (maintainers and commit counts from `history.md` eras 1 and 2), the offline-first feed (`91322d29`), and "silence after 2023-11-09". No reveal.
- [ ] Register `steps = HISTORY_BOOTSTRAP_STEPS` and `steps = HISTORY_PLUGINS_STEPS` in `SkiPresentationSlides.kt`. Replace the three notes constants with real notes for Annunziata Kinya. Mark the slides as a draft she may replace, with a first note line: "Draft from history.md; Annie to replace with her own content."
- [ ] Check: the standard check. The user checks that Right reveals four, then three items on desktop.
- [ ] Commit: `feat: history bootstrap, plugins and churn slides`

### Task 7: Detours and the dormant years
**Serves:** R5, R9
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/history/HistorySlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/history/HistorySlides.kt

- [ ] `DetourKmmSlide`: kicker "2024", the KMM experiment in parallel with the Android modules (`3433931f`, `26f6a3d0`), gone within a quarter (dates from `history.md` era 3).
- [ ] `DetourKmpSlide`: kicker "2025", the Compose Multiplatform attempt (`cdd713bd`), then a reveal of the rollback steps: "Drop KMP" (`c42e0cdb`), "CI fixes", "Five-layer layout back" (`26a9e9cf`). No reason is given for the rollback, and the slide says "reason not recorded". `DETOUR_KMP_STEPS = 3`.
- [ ] `DormantYearsSlide`: the Droidcon 2024 work (`c02c6652`) and the Navigation 3 move (`8faf624f`, 2025-12-23), with the gaps from `history.md` era 4 as a small timeline.
- [ ] Register `steps = DETOUR_KMP_STEPS`. Replace `detourKmmNotes`, `detourKmpNotes` and `dormantYearsNotes` with real notes, including the same draft line as Task 6. The KMP notes say plainly that nobody knows why it was dropped (`history.md` open question 2).
- [ ] Check: the standard check.
- [ ] Commit: `feat: detour and dormant-years slides`

### Task 8: Launch, stack to perf
**Serves:** R6, R9
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/launch/LaunchSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt (edit), docs/talk/history.md (edit), docs/talk/outline.md (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/StatTile.kt

- [ ] `LaunchStackSlide`: a before/after table from `history.md` stack snapshots: Kotlin 2.1.21 → 2.4.21, AGP 8.10.1 → 9.3.1, plus Gradle 9.7 and minSdk 26 (`6bb9b99c`, `8f7577d5`). No reveal.
- [ ] `LaunchModulesSlide`: "17 modules, `:presentation` gone" (`ac81b8ac`), then 21 modules, with a reveal of the module groups: core, feature, then widget and benchmarks. `LAUNCH_MODULES_STEPS = 3`.
- [ ] `LaunchPerfSlide`: `SlideTitle("Faster and smaller", kicker = "Baseline profile and benchmarks")`, then a grid (`LazyVerticalGrid` with `GridCells.Fixed(3)`, or a `Row` of equal-weight tiles) of `StatTile`s: startup time 35% faster (`Decrease`, `source = "Confirmed by Harun Wangereka"`), release APK 28% smaller (`Decrease`, `5a42a5a7`), size 7.4% smaller, gated in CI (`Decrease`, `decimals = 1`, `1fc20ee5`). All three keep `improvement = true`, so all are green. No reveal.
- [ ] Add the startup figure to `docs/talk/history.md` era 5 (the module split sub-phase) as "startup time 35% faster (confirmed by Harun Wangereka, 2026-10-23; no SHA)", and add "startup time 35% faster (Harun Wangereka)" to the `launch-perf` row of `docs/talk/outline.md`.
- [ ] Register `steps = LAUNCH_MODULES_STEPS`. Replace `launchStackNotes`, `launchModulesNotes` and `launchPerfNotes` with real notes for Donald Isoe. The perf notes say what each number doesn't measure: the APK and size figures are download size, not memory or frame time, and the 35% startup figure was confirmed by Harun Wangereka but has no commit. Credit him by name, and say it is startup time only, not frame time or memory.
- [ ] Check: the standard check. The user checks that the tiles count up while shrinking, the down arrows animate in, and all three tiles are green on desktop.
- [ ] Commit: `feat: launch stack, modules and perf slides`

### Task 9: Launch, design to WIP
**Serves:** R6, R9
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/launch/LaunchSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/launch/LaunchSlides.kt

- [ ] `LaunchDesignSlide`: two labelled swatch rows. "Then" uses `ke.don.design.theme.Pre2026Palette` (ChaiBlue, ChaiRed, ChaiTeal, ChaiCoal, ChaiLightGrey); "now" uses the live `MaterialTheme.colorScheme` (primary, secondary, secondaryContainer, tertiary, background). Each swatch has its hex below. Add `CommitTag`s `87d23cb7` (Chai on Material 3 Expressive) and `eb7c436e` (2026 identity). Add a `MediaPlaceholder("Screenshot: home, before and after")`.
- [ ] `LaunchFeaturesSlide`: a reveal of five done items: cube hero (`5941f20f`), Glance widget (`4492346b`), adaptive large-screen layouts, speaker details fix, sponsor logo contrast fix. Add a `MediaPlaceholder("Screen recording: cube hero and widget")`. `LAUNCH_FEATURES_STEPS = 5`.
- [ ] `LaunchWipSlide`: "Notifications, on `feat/notifications`, not yet on main (2026-10-23)". Reveal "Done so far": channels, reminders, preferences, deep links. Then "Still to do": topic subscriptions, reminder actions, styled in `onSurfaceVariant`. `LAUNCH_WIP_STEPS = 6`.
- [ ] Register both step constants. Replace `launchDesignNotes`, `launchFeaturesNotes` and `launchWipNotes` with real notes. The WIP notes remind Donald to check whether notifications has merged by talk day.
- [ ] Check: the standard check.
- [ ] Commit: `feat: launch design, features and WIP slides`

### Task 10: Future slides
**Serves:** R7, R9
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/future/FutureSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/launch/LaunchSlides.kt

- [ ] `FuturePlanSlide`: "The improvement plan", with `docs/IMPROVEMENT_PLAN.md`, `CommitTag("62f45e2a")`, "Harun Wangereka's idea", "A living roadmap, not a commitment", and "We're not married to it."
- [ ] `FutureRoadmapSlide`: a numbered reveal of five stages: accessibility listening pass, ticketing and QR, notifications leftovers, venue map and calendar export, AI experiences last (behind default-off flags). `FUTURE_ROADMAP_STEPS = 5`.
- [ ] `FutureInviteSlide`: "Make the app your own", five track chips (Foundations, Design system, Adaptive, AI, Conference ops), and "Join with a pull request: that's how every maintainer started."
- [ ] Register `steps = FUTURE_ROADMAP_STEPS`. Replace the three notes constants with real notes for Ian Nthuli. Flag that the "not married to it" wording needs the presenters' sign-off.
- [ ] Check: the standard check.
- [ ] Commit: `feat: future slides content and notes`

### Task 11: Close slides
**Serves:** R8, R9
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/close/CloseSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/future/FutureSlides.kt

- [ ] `LessonsSlide`: three revealed lessons, each a headline plus one evidence line from the outline's "Lessons (draft)": 1. Write it down for the next person (`AGENTS.md`, `CLAUDE.md`, the improvement plan; the KMP reversal `c42e0cdb` with no recorded reason). 2. Let CI guard it while people are away (`d66ca423`, `pr.yml`, size gate). 3. Make it easy to start (one line). `LESSONS_STEPS = 3`.
- [ ] `QaSlide`: a large "Questions?" with the three presenter names. Keep its `showFooter = false` registration.
- [ ] Register `steps = LESSONS_STEPS`. Replace `lessonsNotes` (Ian delivers all three, with a cue that the shared close is an option for the presenters to decide) and `qaNotes` (Annunziata Kinya moderates, with backup questions drawn from `history.md` open questions 1 and 2) with real notes.
- [ ] Check: the standard check.
- [ ] Commit: `feat: lessons and Q&A slides`

### Task 12: Remove placeholder scaffolding and run the full build
**Serves:** R9, R10, NF1, NF2
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/PlaceholderSlide.kt (delete), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit)

- [ ] Delete `PlaceholderSlide.kt`, and remove `placeholderNotes` and the "Every constant is a placeholder" comment from `SlidesNotes.kt`.
- [ ] Remove any now-unused `private const val` presenter-name constants or imports left in the slide files.
- [ ] Check: `grep -rn "PlaceholderSlide\|placeholderNotes" segments composeApp/src` and `grep -n "PLACEHOLDER" composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt` return nothing; `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes; `git diff --stat dd4c27b -- shared/components` is empty.
- [ ] Commit: `chore: remove placeholder slide scaffolding`
