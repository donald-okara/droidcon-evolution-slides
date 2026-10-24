# Phase 5: Walkthrough

## Current state

- All seven tasks done and reviewed PASS (Tasks 1-7). Real assets on S02, S09, S18, S21, S23; live cube on S01/S22; S03 removed; trimmed slides use `SplitSlide`.
- Notes describe what is on screen and say trimmed media aloud; `docs/talk/needed-from-us.md` Include column filled; `MediaPlaceholder` deleted.
- Open: desktop visual runs for Tasks 2-7 not done (compile-only). S10 caption "of them dynamic features" sits beside 13 with no 7 shown; copy needs fixing (outside this phase).
- Next: `/donatello:phase-review`, then `/donatello:phase-archive`.
- Known issues: Gradle build-cache store can fail transiently ("Could not pack tree ... shrunk-classpath-snapshot.bin") on `:segments:demos:compileKotlinJvm`; rerun once.

## How to verify

- `./gradlew :segments:demos:compileKotlinJvm` passes (also compiles `:shared:resources`).
- Read `shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt`: the `//Talk assets` group lists the six drawables.
- Read `SlideImage` in `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt`: fit, no cropping, rounded clip and outline, no `fillMaxSize()`.
- No slide uses `SlideImage` yet, so there is nothing to see on the desktop run.
- Task 2: `./gradlew :composeApp:compileKotlinJvm`; desktop run: S01 and S22 show one big spinning cube and no corner cube; S21 shows the corner cube.
- Task 3: `./gradlew :composeApp:compileKotlinJvm`; `grep -rn "SameAppSlide\|sameAppNotes" composeApp segments` returns nothing; desktop run: S02 shows two square, fully visible QRs, next slide is the family tree.
- Task 4: `./gradlew :segments:demos:compileKotlinJvm`; desktop run S07, S08, S12: title and focal element left, details right; S09 shows the `settings.gradle.kts` image.
- Task 6: `grep -rn "MediaPlaceholder" segments/` returns nothing; `./gradlew :composeApp:compileKotlinJvm` passes; read `docs/talk/needed-from-us.md` Include column.
- Task 5: `./gradlew :segments:demos:compileKotlinJvm`; desktop run S18 (AGENTS.md image), S21 (Sessions 2026 phone shot beside swatches), S22 (full-width cube, chips beneath), S23 (2026 Home as before state), S24 (title left, steps right).
- Task 7: `./gradlew :segments:demos:compileKotlinJvm`; desktop run S13, S15, S17: title and focal element left, details right.

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

### Task 4: SplitSlide layout and early years (S07, S08, S09, S12)
- `common/SlideBlocks.kt`: new `SplitSlide(lead, details, modifier)` (no existing two-column block): full-size `Row`, two equal-weight vertically centred `Column`s, `largePadding` gap, KDoc.
- `talk/EarlyYearsSlides.kt`: S07 `ScreenshotPair` deleted; lead = title + both README lines stacked (side by side too narrow at half width), details = screen-name rows; sweep unchanged. S08 placeholders removed; lead = title + `PackageChip`, details = bullets. S09 uses `SlideImage(SETTINGS_GRADLE, ...)` in the weighted slot. `MediaPlaceholder` import dropped.
- `talk/OneRepoSlides.kt`: S12 lead = title + `ChaiSwatches`, details = bullets and the 2018→2022 line. `MediaPlaceholder` import kept for S13/S15/S17 (Task 7).
- NOTE (reviewer): `EarlyYearsSlides.kt` may keep unused `Box`/`Alignment` imports; harmless. Desktop run not done.

### Task 5: Measured and live slides (S18, S21, S22 widget, S23, S24)
- `talk/MeasuredSlides.kt`: S18 uses `SlideImage(TOP_OF_AGENTS, ...)`; S21 swatches and bullets in a left column, `SlideImage(SESSIONS_2026)` at phone aspect on the right captioned "Sessions, 2026"; four golden placeholders and `FORM_FACTOR_STAGGER_MILLIS` removed.
- `talk/LiveSlides.kt`: S22 widget placeholder removed; cube fills width and remaining height, Spec/Plan/Code chips in a full-width row beneath. S23 shows `HOME_2026` as before state, three text labels kept with pop-in; during/after slots removed. S24 is `SplitSlide` (lead = `SlideTitle`, details = six `DemoStepRow`s + "Airplane mode" line); scrcpy placeholder removed.
- One fix round (S22 cube was left at half width behind a spacer).
- NOTE (reviewer): `PHONE_ASPECT` is duplicated as a private constant in both files; not blocking. Desktop run not done.

### Task 7: One-repo slides in split layout (S13, S15, S17)
- `talk/OneRepoSlides.kt`: S13 lead = title + `OneRepoCountUp`, "files carried over" caption in details; S15 lead = title + headline "3" (popIn); S17 lead = title + `StatusChip` row. Terminal, diff and recording placeholders removed; `MediaPlaceholder` import removed; S15/S17 KDoc updated.
- NOTE (reviewer): reveal state and animations unchanged. Desktop run not done.

### Task 6: Notes, asset list, and cleanup
- `SlidesNotes.kt`: the 13 listed notes describe current slides; trimmed media said aloud (S13 one commit/280 files, S15 three lines/three files, S17 tab switch, S21 other form factors, S22 widget, S23 during/after, S24 switch to scrcpy). `todayNotes` names the Play and repo QRs and points to the README for the APK.
- `docs/talk/needed-from-us.md`: six used assets `yes (<file>)`, rest `no (trimmed)`; S21 goldens and S23 home screens split one row per image; intro no longer says "placeholders".
- `common/SlideBlocks.kt`: `MediaPlaceholder` and unused `TextAlign` import deleted.
- NOTE (implementer): S10 caption copy issue (13 shown, 7 not). Transient build-cache failure on first compile; rerun passed.
- NOTE (reviewer): `todayNotes` QR line checked against the asset table, not the slide visuals.

## Deviations from spec

- None.
