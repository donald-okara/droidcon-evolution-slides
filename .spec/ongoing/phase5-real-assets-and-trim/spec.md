# Phase 5: Specification

## Requirements

### R1. New drawables are registered
- `Resources.Images` exposes the six new drawables: Play QR, repo QR, 2026 Home, 2026 Sessions, `settings.gradle.kts`, top of AGENTS.md.
- **Check:** `shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt` lists them; `./gradlew :shared:resources:compileKotlinJvm` passes.

### R2. Shared slide image block
- One composable in `segments/demos/.../droidcon/common/` shows a drawable with the deck's media styling (rounded clip, outline border, fit without cropping) and a content description.
- Phone screenshots (720x1600) keep their aspect ratio and are not stretched.
- **Check:** read the composable; S21 and S23 on desktop show uncropped phone screenshots.

### R3. Slides with assets show them
- S02 shows the Play and repo QR codes; S09 `settings.gradle.kts`; S18 top of AGENTS.md; S21 the 2026 Sessions screen; S23 the 2026 Home as "before".
- Labels next to each image name what it is (e.g. "Play listing", "Repo"), not the old placeholder text.
- **Check:** desktop run (`./gradlew :desktopApp:run`), step to each slide.

### R4. Placeholders without assets are removed
- No `MediaPlaceholder` calls remain in `segments/demos`. Removed: S02 APK QR, S07 pair, S08 pair, S12 chai cover, S13 terminal, S15 diff, S17 recording, S21 Foldable/Tablet/Desktop, S22 widget, S23 during/after, S24 mirror.
- Each affected slide reflows so its remaining content fills the slide (no empty column or `weight` gap). Helpers left unused (`ScreenshotPair`, `ScreenshotCard`, `QrCard` if replaced) are deleted.
- `MediaPlaceholder` itself is deleted if nothing uses it.
- **Check:** `grep -rn "MediaPlaceholder" segments/` returns nothing; desktop run of S07, S08, S12, S13, S15, S17, S22, S24 shows no blank half.

### R7. S03 is removed
- `SameAppSlide`, its private helpers, its `slide(...)` registration and `sameAppNotes` are gone; the deck goes S02 → S04.
- **Check:** `grep -rn "SameAppSlide\|sameAppNotes" composeApp segments` returns nothing; desktop run shows S04 after S02.

### R5. Live cube on S22
- S22 shows the spinning cube hero (the same `CubeHero` the background uses, via a shared spin-in composable also used by S01) in place of the cube placeholder.
- The background corner cube is hidden on S01 and S22, and visible on every other slide.
- **Check:** desktop run: S01 and S22 show one large cube, no corner cube; S21 and S23 show the corner cube.

### R6. Notes and asset list match the slides
- Presenter notes for every slide touched in R3-R5 describe what is now on screen; notes no longer say "point at the screenshot" for media that was removed.
- `docs/talk/needed-from-us.md` has every "Include?" filled: `yes (<file>)`, or `no (trimmed)`.
- **Check:** read `SlidesNotes.kt` entries for touched slides; read the table.

## Non-Functional Requirements

- **NF1. Builds:** `./gradlew :composeApp:compileKotlinJvm` passes after every task.
- **NF2. Web size:** no image is resized or duplicated into another module; the web bundle grows only by the six files.
