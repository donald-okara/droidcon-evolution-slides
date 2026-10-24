# Phase 5: Walkthrough

## Current state

- Task 1 done: six talk drawables registered in `Resources.Images`, and the `SlideImage` block added in `common/SlideBlocks.kt`.
- Task 2 code committed (4196744, live cube on S22) but not yet reviewed or ticked; review it next.
- Plan revised 2026-10-24: slides that lost their media use a shared `SplitSlide` (R8); Task 4 adds it, Task 7 covers S13/S15/S17, Task 5 uses it on S24.
- Then: Task 3, 4, 7, 5, 6. Run only via /donatello:phase-run.
- Known issues: none.

## How to verify

- `./gradlew :segments:demos:compileKotlinJvm` passes (also compiles `:shared:resources`).
- Read `shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt`: the `//Talk assets` group lists the six drawables.
- Read `SlideImage` in `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt`: fit, no cropping, rounded clip and outline, no `fillMaxSize()`.
- No slide uses `SlideImage` yet, so there is nothing to see on the desktop run.

## Changes by task

### Task 1: Register drawables and add a SlideImage block
- `shared/resources/.../ke/don/resources/Resources.kt`: added `PLAY_QR`, `REPO_QR`, `HOME_2026`, `SESSIONS_2026`, `SETTINGS_GRADLE`, `TOP_OF_AGENTS` under `//Talk assets`.
- `segments/demos/.../droidcon/common/SlideBlocks.kt`: added `SlideImage(resource, contentDescription, modifier)` after `MediaPlaceholder`.
- Commit: `feat: register talk drawables and add SlideImage`.

## Deviations from spec

- None.
