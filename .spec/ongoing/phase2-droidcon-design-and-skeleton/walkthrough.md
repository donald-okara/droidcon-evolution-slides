# Phase 2: Walkthrough

## Current state

- Done: Tasks 1 to 6. Review 1 (partial) requested changes: snake frame, header with only the logo and cube.
- Next: Task 10 (snake frame with logo-and-cube header, revises Task 6), then Tasks 7, 8 and 9.
- Task 9 uses `showFooter = false` on `opening-title` and `qa`.
- Open: nothing has been checked visually. The user must run `./gradlew :desktopApp:run` and confirm the font, colours, logo, cube perspective and animation, cube bullet, and that Questions has no footer.
- Open with the user: code snippets use `FontFamily.Monospace`, not Roboto Mono; fixing it means editing `:shared:components` (NF2).
- Open: the unused Google Sans TTFs (about 4 MB) are still in `shared/resources/.../font/`.
- Open: the R4 check `grep "import android"` also matches `androidx`; use `grep "import android\."` instead.
- Known issues: Mobile is not a priority; verify on desktop and wasm only. Check common code on wasm too. Agents cannot see the desktop windows, so leave visual checks to the user and do not `pkill` desktopApp.

## How to verify

- Task 1: `./gradlew :shared:design:compileKotlinJvm` passes; `git diff 9832e28 -- shared/design/src/commonMain/kotlin/ke/don/design/theme/Type.kt` touches only font families; slide text renders in Montserrat on `./gradlew :desktopApp:run` (not yet checked visually).
- Task 2: `./gradlew :shared:design:compileKotlinJvm` passes; `grep -n "6D538B" shared/design/src` returns nothing; deck shows blue/green/black in light and dark (not yet checked visually).
- Task 3: `./gradlew :shared:resources:compileKotlinJvm` passes; `xmllint --noout` on both SVGs; logos render in the header after Task 6.
- Task 4: `./gradlew :shared:design:compileKotlinJvm :shared:design:compileKotlinWasmJs` passes; `grep -rn "import android\." shared/design/src/commonMain` returns nothing.
- Task 5: same compile and grep checks as Task 4; the cube animates without distortion in the header after Task 6 (not yet checked visually).
- Task 6: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs` passes; `git diff --stat -- shared/components` is empty; on `./gradlew :desktopApp:run`, both windows show the logo and animated cube in the header and the cube bullet in the footer, and the Questions slide has no footer (not yet checked visually).

## Changes by task

### Task 1: Montserrat font
- Added `montserrat_variable.ttf` and `OFL.txt` to `shared/resources/src/commonMain/composeResources/font/`.
- `Resources.Font`: added `MONTSERRAT`; removed `GOOGLE_SANS_EXTRA_LIGHT` and `GOOGLE_SANS_BOLD` (unreferenced), which removes the bug. `ROBOTO_MONO_EXTRA_LIGHT` kept.
- `Type.kt`: `montserratFontFamily` (Thin to ExtraBold via `FontVariation.weight`) backs the body, display and subheadings families; the type ramp is unchanged. Code uses `FontFamily.Monospace`.
- Reviewer NOTE: the Google Sans TTFs are now unreferenced; desktop rendering was not checked.
### Task 2: Droidcon colours
- `Color.kt`: only the Chai base-scheme roles (§1.2) under the existing `*Light`/`*Dark` names; contrast variants and roles Chai doesn't set (surfaceDim, surfaceBright, scrim, errorContainer) removed so Material defaults apply.
- `Theme.kt`: schemes rebuilt from the Chai roles; `AppTheme` provides `LocalChaiColors`.
- `ChaiColors.kt` (new): `ChaiColors`, `lightChaiColors`/`darkChaiColors` (identical, as in the source), `LocalChaiColors`, `object CubeColors`, `object Pre2026Palette`.
- Reviewer NOTE: all values match design-inventory §1.2 to 1.4 and §4; visual check not done.
### Task 3: Logo SVGs
- `droidcon_logo.svg` and `droidcon_logo_dark.svg` (new) in `shared/resources/.../drawable/`: 9 paths each, path data copied verbatim from the source XML, `viewBox="0 0 1246 246"`, alpha byte dropped from opaque fills.
- `Resources.Images`: added `DROIDCON_LOGO` and `DROIDCON_LOGO_DARK`.
- Reviewer NOTE: path data, order and colours match the source; browser check replaced by xmllint plus path/colour comparison.
### Task 4: Cube model port
- Added `CubeState.kt`, `CubeMotion.kt`, `CubeProjection.kt`, `CubeMarks.kt`, `CubeArt.kt`, `Orientation.kt` in `shared/design/.../ke/don/design/cube/` from the source's `hero` package; only the package changed, apart from the wasm fixes below.
- `CubeArt` keeps its hardcoded mark colours and has no Chai references, so `CubeColors` is not used yet.
- For wasm: `Math.toRadians`/`toDegrees` replaced with `kotlin.math.PI` arithmetic (`CubeProjection.kt`, `Orientation.kt`); `synchronized(this)` dropped from `CubeMark.layers` in `CubeMarks.kt`.
- Reviewer NOTE: the PI arithmetic is correct. Without the lock, a mark can be cut twice in a race, with the same result, which is harmless. The reviewer could not find the source on disk to diff against.
- Reviewer NOTE: the literal `grep "import android"` check matches `androidx`; `import android\.` finds nothing.
### Task 5: Cube painter and composables
- Added `CubePainter.kt` and `CubeHero.kt` in `ke.don.design.cube`. Escalated to Sonnet (as the task required).
- `CubePainter`: uses `drawIntoCanvas` with `save`/`concat`/`restore` and a Compose `Matrix` rather than `Path.transform`. A private `setProjective` loads the 3x3 homography into the 4x4 matrix. Colours come from `CubeColors`.
- `CubeHero(modifier, size = 240.dp)` runs `CubeMotion.run()` while composed. The animate flag, lifecycle pause/resume, visibility callback, test tag, saveable scene and click handling were dropped. `CubeBullet(modifier, size = 12.dp)` draws a solved cube at `Poses[0]`, with no animation.
- No `@Preview`, because `:shared:design` has none.
- Reviewer NOTE: the matrix index mapping matches `android.graphics.Matrix` perspective behaviour. Visual output is unchecked; confirm in the Task 6 run.
### Task 6: Droidcon frame
- `DroidconFrame.kt` (new, `composeApp/.../presentation/frame/`): uses `BasicFrame`'s layout (header, curved weighted content box, footer) with theme colours; `droidconFrameFactory` is a top-level `SkiFrameFactory`.
- `MainHeader`: keeps the "Ski" title, the `DeckMode` subtitle and the bullet rail, and adds the logo (180dp wide, 1246/246 aspect ratio, dark variant when surface luminance is below 0.5) and `CubeHero(96.dp)`.
- `MainFooter`: `CubeBullet` replaces `DotBullet`.
- `Deck.kt`: `mainFrame` uses `droidconFrameFactory`; `guidesFrame` is unchanged.
- User decision: `footer = null` could not hide the footer (it defaults to null on every slide, and `PresentationDeck` never read it). Added `showFooter: Boolean = true` to `SlideConfig` and `slide()` in `:core:domain`; `PresentationDeck` omits `MainFooter` when it is false; the Questions slide uses `showFooter = false`.
- Not checked visually: the agent could not see the windows. The cleanup ran `pkill -f desktopApp`.
- Reviewer NOTE: `SlideConfig` is constructed only in `DeckBuilder.kt`, so no other caller breaks. The visual Check is still pending.

## Deviations from spec

- Task 6: slides hide their footer with `showFooter = false` (new flag in `:core:domain`), not `footer = null`. This also applies to Task 9.
- Task 4: not a pure copy. JVM-only calls were replaced so common code compiles for wasm (see Changes by task).
