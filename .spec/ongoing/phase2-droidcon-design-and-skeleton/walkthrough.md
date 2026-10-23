# Phase 2: Walkthrough

## Current state

- Done: Tasks 1 to 4 (Montserrat font, droidcon colours, logo SVGs, cube model port). Nothing checked visually on desktop yet; the Task 6 run covers fonts, colours, logo and cube.
- Next: Task 5 (cube painter and composables, on Sonnet).
- Logos are 1246x246 intrinsic size; the Task 6 header must size them explicitly.
- Open with the user: code snippets use `FontFamily.Monospace`, not Roboto Mono; fixing it means editing `:shared:components` (NF2).
- Open: the unused Google Sans TTFs (about 4 MB) are still in `shared/resources/.../font/`; delete them in a cleanup if wanted.
- Open: the spec/tasks check `grep "import android"` also matches `androidx`; use `grep "import android\."` instead.
- Known issues: none. Mobile is not a priority; verify on desktop and wasm only. Check common code on wasm too (`:shared:design:compileKotlinWasmJs`), since JVM-only calls compile on jvm.

## How to verify

- Task 1: `./gradlew :shared:design:compileKotlinJvm` passes; `git diff 9832e28 -- shared/design/src/commonMain/kotlin/ke/don/design/theme/Type.kt` touches only font families; slide text renders in Montserrat on `./gradlew :desktopApp:run` (not yet checked visually).
- Task 2: `./gradlew :shared:design:compileKotlinJvm` passes; `grep -n "6D538B" shared/design/src` returns nothing; deck shows blue/green/black in light and dark (not yet checked visually).
- Task 3: `./gradlew :shared:resources:compileKotlinJvm` passes; `xmllint --noout` on both SVGs; logos render in the header after Task 6.
- Task 4: `./gradlew :shared:design:compileKotlinJvm :shared:design:compileKotlinWasmJs` passes; `grep -rn "import android\." shared/design/src/commonMain` returns nothing.

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

## Deviations from spec

- Task 4: not a pure copy. JVM-only calls were replaced so common code compiles for wasm (see Changes by task).
