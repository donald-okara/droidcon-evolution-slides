# Phase 5: Walkthrough

## Current state

- Task 1 done: six talk drawables registered in `Resources.Images`, and the `SlideImage` block added in `common/SlideBlocks.kt`.
- Task 2 done (4196744, reviewed PASS): `SpinningCube` lifted to `common/`, live cube on S01 and S22, corner cube hidden on both.
- Plan revised 2026-10-24: slides that lost their media use a shared `SplitSlide` (R8); Task 4 adds it, Task 7 covers S13/S15/S17, Task 5 uses it on S24.
- Task 3 done: S02 shows real Play and Repo QRs (APK card gone); S03 and its notes removed.
- Next: Task 4, then 7, 5, 6. `todayNotes` still mentions the APK QR (Task 6 fixes it). Run only via /donatello:phase-run.
- Known issues: none.

## How to verify

- `./gradlew :segments:demos:compileKotlinJvm` passes (also compiles `:shared:resources`).
- Read `shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt`: the `//Talk assets` group lists the six drawables.
- Read `SlideImage` in `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt`: fit, no cropping, rounded clip and outline, no `fillMaxSize()`.
- No slide uses `SlideImage` yet, so there is nothing to see on the desktop run.
- Task 2: `./gradlew :composeApp:compileKotlinJvm`; desktop run: S01 and S22 show one big spinning cube and no corner cube; S21 shows the corner cube.
- Task 3: `./gradlew :composeApp:compileKotlinJvm`; `grep -rn "SameAppSlide\|sameAppNotes" composeApp segments` returns nothing; desktop run: S02 shows two square, fully visible QRs, next slide is the family tree.

## Changes by task

### Task 1: Register drawables and add a SlideImage block
- `shared/resources/.../ke/don/resources/Resources.kt`: added `PLAY_QR`, `REPO_QR`, `HOME_2026`, `SESSIONS_2026`, `SETTINGS_GRADLE`, `TOP_OF_AGENTS` under `//Talk assets`.
- `segments/demos/.../droidcon/common/SlideBlocks.kt`: added `SlideImage(resource, contentDescription, modifier)` after `MediaPlaceholder`.
- Commit: `feat: register talk drawables and add SlideImage`.

### Task 2: Lift SpinningCube and show the live cube on S22
- `common/SpinningCube.kt` (new): public `SpinningCube(modifier)` with `CUBE_SPIN_MILLIS`, `CUBE_HERO_FILL`; `TitleSlide` (S01) calls it.
- `talk/LiveSlides.kt`: S22 "Cube hero" placeholder replaced by `SpinningCube`; widget placeholder left for Task 5.
- `talk/ColdOpenSlides.kt`: `CUBE_WIDGET_LABEL` added; used for S22 in `SkiPresentationSlides.kt`.
- `composeApp/.../ski/Deck.kt`: background cube hidden for `COLD_TITLE_LABEL` and `CUBE_WIDGET_LABEL`.
- NOTE (reviewer): compile passes; desktop run not done. Possible leftover unused animation imports in `ColdOpenSlides.kt` (harmless).
- Commit: 4196744 `feat: live cube hero on S22` (committed before review).

### Task 3: Cold open: S02 assets, remove S03
- `talk/ColdOpenSlides.kt`: `QrCard` takes a `DrawableResource` and renders `SlideImage` with `aspectRatio(1f, matchHeightConstraintsFirst = true)`; cards "Play listing" (`PLAY_QR`) and "Repo" (`REPO_QR`), pop-in 0 and 1. Deleted `SameAppSlide`, `ScreenshotCard`, `CommitCountUp`, `withThousands`, `SAME_APP_COMMITS`, `COUNT_UP_MILLIS` and unused imports.
- `SkiPresentationSlides.kt`: S03 `slide(...)` and import removed. `SlidesNotes.kt`: `sameAppNotes` removed.
- NOTE (reviewer): QR sizing checked by reading only; confirm on desktop that both QRs are square and uncropped.
- One fix round (unused `COUNT_UP_MILLIS`; QR sizing switched to height-first).

## Deviations from spec

- None.
