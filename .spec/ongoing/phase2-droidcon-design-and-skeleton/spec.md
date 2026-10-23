# Phase 2: Specification

## Requirements

### R1. Droidcon colours
- `AppTheme` uses the 2026 Chai light and dark colour schemes from design-inventory §1.2; no Ski purple value (`#6D538B` and its scheme) remains in `Color.kt`.
- The `ChaiColors` extras (§1.3) are reachable from composables through a `CompositionLocal` provided by `AppTheme`.
- Cube colours (§1.4) and the pre-2026 palette (§4, "Pre-2026 palette") exist as named constants in `:shared:design`.
- **Check:** `grep -n "6D538B" shared/design/src` returns nothing; the deck on desktop shows blue/green/black in both light and dark.

### R2. Montserrat everywhere
- `montserrat_variable.ttf` and `OFL.txt` are in `shared/resources/src/commonMain/composeResources/font/`, exposed as `Resources.Font.MONTSERRAT`.
- Every `TextStyle` in `Type.kt` except code uses Montserrat; sizes, weights, line heights and letter spacing are unchanged.
- No resource name points at the wrong file (the `GOOGLE_SANS_EXTRA_LIGHT` bug is fixed or the Google Sans entries are removed).
- **Check:** `git diff shared/design/src/commonMain/kotlin/ke/don/design/theme/Type.kt` touches only font families; slide text renders in Montserrat on desktop.

### R3. Droidcon logo
- Light and dark droidcon logos exist as SVG in `shared/resources/src/commonMain/composeResources/drawable/` and are exposed in `Resources.Images`.
- **Check:** both logos render on desktop (the header in R5 shows them per theme).

### R4. Cube hero in common code
- The cube hero renders and animates (hold, travel, marks) from `commonMain`, with no `android.*` import.
- A static cube composable is available at bullet size.
- **Check:** `grep -rn "import android\." shared/design/src/commonMain` returns nothing; the cube animates as the background decorator (R8).

### R5. Snake frame with droidcon header
- The deck's main frame is the stock snake frame in both windows.
- Its header shows only the droidcon logo (light or dark per theme): no cube, "Ski" title, deck-mode subtitle or bullet rail. Its footer bullet is a small neon-green dot.
- Slides registered with `showFooter = false` show no footer.
- **Check:** `./gradlew :desktopApp:run`; look at the presenter and audience windows.

### R6. Slide skeleton
- The deck registers exactly the 19 slides from the outline's slide list, in order, with labels matching the outline character for character.
- Each slide body is a named composable in a sub-package of `ke.don.demos.droidcon`, showing the slide's title and its one-line intent as placeholder text.
- Each slide has a notes constant in `SlidesNotes.kt` naming the presenter (from the outline's section table) and the intent, marked as a placeholder.
- **Check:** the labels in `SkiPresentationSlides.kt` match `docs/talk/outline.md` in order; stepping through the deck shows 19 slides.

### R7. Ski demos removed
- No Ski demo slide, demo notes constant or demo-only composable remains; `VideoPlayer*` stays.
- **Check:** `grep -rn "ke.don.demos\.\(FeatureSlide\|FramesDemo\|RevealDemo\|KodeViewerSlide\)" composeApp` returns nothing; `ls segments/demos/src/commonMain/kotlin/ke/don/demos` shows only `droidcon/` and `VideoPlayer.kt`.

### R8. Droidcon background
- The deck background is a checkered floor in perspective (Star Wars crawl style) in the Chai neon green at a very low alpha, scrolling slowly and continuously toward the horizon; it is barely noticeable and never hurts text legibility in light or dark.
- The animated cube hero is the background decorator at bottom-right, replacing the Android robot.
- Built in `:composeApp` against the public `Background` interface; `:shared:components` is unchanged.
- **Check:** `./gradlew :desktopApp:run`; both windows show the faint moving floor and the animated cube; slide text stays readable.

## Non-Functional Requirements

- **NF1. Builds:** `./gradlew :desktopApp:run` and `./gradlew :webApp:wasmJsBrowserDistribution` succeed.
- **NF2. Library untouched:** `git diff --stat main -- shared/components` is empty.
- **NF3. Source repo read-only:** nothing is written to `/Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin`.
