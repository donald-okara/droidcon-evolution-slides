# Phase 3: Walkthrough

## Current state

- Tasks 1 to 7 done and committed (through the detour and dormant-years slides).
- Next: Task 8 (launch: stack, modules, perf), then 9 to 12.
- Open: desktop manual checks (Right press on `opening-title`, window and web popup sync, stat tile, opening slides, history reveals 4 then 3, `detour-kmp` 3 steps) are left to the user.
- `placeholderNotes` and `PlaceholderSlide.kt` stay until Task 12.
- Presenters should check the KMP note's attribution wording (see Task 7).

## How to verify

- Task 1: `./gradlew :core:domain:compileKotlinJvm :core:domain:compileKotlinWasmJs` passes; `git diff --stat dd4c27b -- shared/components` is empty.

- Task 2: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes. User: on `:desktopApp:run`, one Right press on `opening-title` changes slides.
- Task 3: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes.
- Task 4: same compile check as Task 3 passes.
- Task 5: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs` passes. User: look at `opening-title` and `opening-thesis` on `:desktopApp:run`.
- Task 6: compile check passes. User: on desktop, Right reveals 4 steps on `history-bootstrap` and 3 on `history-plugins`.
- Task 7: compile check passes. User: on desktop, Right reveals 3 rollback items on `detour-kmp`.

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

## Deviations from spec

None.
