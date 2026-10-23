# Phase 3: Walkthrough

## Current state

- Tasks 1 to 4 done and committed (navigator steps; host and web sync; shared slide blocks; stat tile).
- Next: Task 5 (opening slides).
- Open: desktop manual checks (one Right press on `opening-title`, window and web popup sync, stat tile animation) are left to the user.

## How to verify

- Task 1: `./gradlew :core:domain:compileKotlinJvm :core:domain:compileKotlinWasmJs` passes; `git diff --stat dd4c27b -- shared/components` is empty.

- Task 2: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes. User: on `:desktopApp:run`, one Right press on `opening-title` changes slides.
- Task 3: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes.
- Task 4: same compile check as Task 3 passes.

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

## Deviations from spec

None.
