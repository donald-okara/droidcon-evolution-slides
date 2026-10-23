# Phase 2: Tasks

Source repo (read-only) is `/Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin`, abbreviated `APP`. `DS` = `APP/core/designsystem/src/main/java/com/droidconke/chai`. Never write to `APP`.

```
1. Montserrat font ───────────────┐
2. Droidcon colours ──────────────┼─> 7. Placeholder + sections 1-3 ─> 8. Sections 4-8 ─┐
3. Logo SVGs ─────────────┐       │                                                    ├─> 9. Register slides, remove demos ─┬─> 11. Logo-only header, green dot bullet
4. Cube model port ─> 5. Cube painter + composables ─> 6. Droidcon frame ─> 10. Snake frame + logo/cube header ┘                    └─> 12. Checkered floor background + cube decorator
   (5 also needs 2; 11 and 12 are independent of each other)
```

---

### Task 1: Montserrat font
**Serves:** R2
**Files:** `shared/resources/src/commonMain/composeResources/font/montserrat_variable.ttf` (new), `shared/resources/src/commonMain/composeResources/font/OFL.txt` (new), `shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt` (edit), `shared/design/src/commonMain/kotlin/ke/don/design/theme/Type.kt` (edit)

- [x] Copy `APP/core/designsystem/src/main/res/font/montserrat_variable.ttf` and `APP/core/designsystem/OFL.txt` into the font directory.
- [x] In `Resources.Font`, add `MONTSERRAT = Res.font.montserrat_variable`. Fix `GOOGLE_SANS_EXTRA_LIGHT` (it points at `google_sans_regular`): remove the Google Sans entries if nothing else uses them after the next step, otherwise leave them pointing at real files.
- [x] In `Type.kt`, build one Montserrat `FontFamily` with a `Font(Resources.Font.MONTSERRAT, weight, variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)))` per weight Thin to ExtraBold, and use it for every family that is not monospace. Do not change any size, weight, line height or letter spacing. Roboto Mono stays for code.
- [x] Check: `./gradlew :shared:design:compileKotlinJvm` passes; `git diff Type.kt` touches only font families.
- [x] Commit: `feat: switch deck typography to Montserrat`

### Task 2: Droidcon colours
**Serves:** R1
**Files:** `shared/design/src/commonMain/kotlin/ke/don/design/theme/Color.kt` (edit), `shared/design/src/commonMain/kotlin/ke/don/design/theme/ChaiColors.kt` (new), `shared/design/src/commonMain/kotlin/ke/don/design/theme/Theme.kt` (edit)
**Pattern:** `DS/colors/ChaiColorScheme.kt`

- [x] Replace the Ski purple values in `Color.kt` with the Chai light and dark scheme values from `docs/talk/design-inventory.md` §1.2 (source: `DS/colors/ChaiColorScheme.kt`, atoms in `DS/atoms/Color.kt`), keeping the existing `*Light`/`*Dark` naming that `Theme.kt` reads. Remove the contrast variants if Chai has none and point `Theme.kt` at the base schemes.
- [x] In `ChaiColors.kt`, add a `ChaiColors` data class with the extras from §1.3 (`DS/colors/ChaiColors.kt`), light and dark instances, `CubeColors` from §1.4, a `Pre2026Palette` object with the colours from §4 "Pre-2026 palette", and `val LocalChaiColors = staticCompositionLocalOf { lightChaiColors }`.
- [x] In `AppTheme` (`Theme.kt`), provide `LocalChaiColors` with the light or dark instance to match the scheme.
- [x] Check: `./gradlew :shared:design:compileKotlinJvm` passes; `grep -n "6D538B" shared/design/src` returns nothing.
- [x] Commit: `feat: adopt the droidcon 2026 colour schemes`

### Task 3: Logo SVGs
**Serves:** R3
**Files:** `shared/resources/src/commonMain/composeResources/drawable/droidcon_logo.svg` (new), `shared/resources/src/commonMain/composeResources/drawable/droidcon_logo_dark.svg` (new), `shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt` (edit)

- [x] Convert `APP/core/ui/src/main/res/drawable/droidcon_logo.xml` and `droidcon_logo_dark.xml` to SVG: `viewBox` from the viewport (1246x246), one `<path d=... fill=...>` per `<path android:pathData=... android:fillColor=...>`, keeping colours (`#000000`/`#FFFFFF` and `#01FF4F`).
- [x] Add `DROIDCON_LOGO` and `DROIDCON_LOGO_DARK` to `Resources.Images`.
- [x] Check: `./gradlew :shared:resources:compileKotlinJvm` passes; open each SVG in a browser and confirm it matches the original logo.
- [x] Commit: `feat: add droidcon logo SVGs`

### Task 4: Cube model port
**Serves:** R4
**Files:** `shared/design/src/commonMain/kotlin/ke/don/design/cube/CubeState.kt`, `CubeMotion.kt`, `CubeProjection.kt`, `CubeMarks.kt`, `CubeArt.kt`, `Orientation.kt` (all new)
**Pattern:** `DS/hero/` (the same file names)

- [x] Copy the six files from `DS/hero/` into `ke.don.design.cube`, changing only the package and imports. Do not copy `CubePainter`, `CubeHero`, `CubeHeroBanner` or `CubeHeroStage`.
- [x] Replace any `ChaiTheme`/Chai colour reference with `CubeColors` from Task 2 (or a parameter); drop anything Android-only.
- [x] Check: `./gradlew :shared:design:compileKotlinJvm` passes; `grep -rn "import android" shared/design/src/commonMain` returns nothing.
- [x] Commit: `feat: port cube hero model to common code`

### Task 5: Cube painter and composables
**Serves:** R4
**Files:** `shared/design/src/commonMain/kotlin/ke/don/design/cube/CubePainter.kt` (new), `shared/design/src/commonMain/kotlin/ke/don/design/cube/CubeHero.kt` (new)
**Pattern:** `DS/hero/CubePainter.kt`, `DS/hero/CubeHero.kt`

This task rewrites Android graphics code; run the implementer on Sonnet.

- [x] Port `CubePainter` replacing `android.graphics.Matrix` and `nativeCanvas` (source lines 18, 27, 56, 74) with `androidx.compose.ui.graphics.Matrix`, `Path.transform`, and `DrawScope.drawIntoCanvas`/`drawPath`. Same visual output.
- [x] Port `CubeHero(modifier, size)` as the animated cube (hold 2600 ms, travel 1300 ms, `CubicBezierEasing(0.2f, 0f, 0f, 1f)`); drop click handling if it needs anything not in common code.
- [x] Add `CubeBullet(modifier, size = 12.dp)`: the cube drawn statically in its first orientation, no animation.
- [x] Add a `@Preview` for `CubeHero` and `CubeBullet` inside `AppTheme`, if previews already exist in `:shared:design`; otherwise skip.
- [x] Check: `./gradlew :shared:design:compileKotlinJvm` passes; no `android.` import in `shared/design/src/commonMain`.
- [x] Commit: `feat: port cube painter and add cube hero and bullet`

### Task 6: Droidcon frame
**Serves:** R5
**Files:** `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/frame/DroidconFrame.kt` (new), `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/Components.kt` (edit), `composeApp/src/commonMain/kotlin/ke/don/ski/Deck.kt` (edit)
**Pattern:** `shared/components/src/commonMain/kotlin/io/github/donald_okara/components/frames/basic_frame/BasicFrame.kt`

- [x] Add `DroidconFrame` implementing `ke.don.domain.frames.SkiFrame`, laid out like `BasicFrame` (header row, content box, footer row), using theme colours. Expose it as a `SkiFrameFactory` (`droidconFrameFactory`).
- [x] In `MainHeader` (`Components.kt:102`), show the droidcon logo (`Resources.Images.DROIDCON_LOGO`, or `_DARK` when the scheme is dark) with `CubeHero` beside it, keeping anything the header already shows for `DeckMode`.
- [x] In `MainFooter` (`Components.kt:37`), use `CubeBullet` as the leading bullet in place of the current marker.
- [x] In `Deck.kt`, build `mainFrame` from the droidcon frame instead of `snake`. Leave `guidesFrame` as is. Do not edit `:shared:components`.
- [x] Check: `./gradlew :desktopApp:run`; both windows show the logo and animated cube in the header and the cube bullet in the footer; the "Questions" slide (footer = null) has no footer.
- [x] Commit: `feat: add droidcon frame with logo, cube and cube bullet`

### Task 7: Placeholder slide and sections 1 to 3
**Serves:** R6
**Files:** `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/PlaceholderSlide.kt` (new), `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/opening/OpeningSlides.kt` (new), `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/history/HistorySlides.kt` (new)
**Pattern:** `segments/demos/src/commonMain/kotlin/ke/don/demos/FeatureSlides.kt`

- [x] Add `PlaceholderSlide(title: String, intent: String, presenter: String)`: title in `displayMedium`, intent in `bodyLarge`, presenter in `labelLarge`, centred, theme colours only.
- [x] In `OpeningSlides.kt` add `OpeningTitleSlide()` and `OpeningThesisSlide()`; in `HistorySlides.kt` add `HistoryBootstrapSlide()`, `HistoryPluginsSlide()`, `HistoryChurnSlide()`, `DetourKmmSlide()`, `DetourKmpSlide()`, `DormantYearsSlide()`. Each calls `PlaceholderSlide` with the intent text copied from the `docs/talk/outline.md` slide list and the presenter from its section table.
- [x] Check: `./gradlew :segments:demos:compileKotlinJvm` passes.
- [x] Commit: `feat: add placeholder slides for opening and history`

### Task 8: Sections 4 to 8
**Serves:** R6
**Files:** `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/launch/LaunchSlides.kt` (new), `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/future/FutureSlides.kt` (new), `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/close/CloseSlides.kt` (new)
**Pattern:** `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/history/HistorySlides.kt`

- [x] `LaunchSlides.kt`: `LaunchStackSlide()`, `LaunchModulesSlide()`, `LaunchPerfSlide()`, `LaunchDesignSlide()`, `LaunchFeaturesSlide()`, `LaunchWipSlide()`.
- [x] `FutureSlides.kt`: `FuturePlanSlide()`, `FutureRoadmapSlide()`, `FutureInviteSlide()`.
- [x] `CloseSlides.kt`: `LessonsSlide()`, `QaSlide()`.
- [x] Each calls `PlaceholderSlide` with intent and presenter from `docs/talk/outline.md`, as in Task 7.
- [x] Check: `./gradlew :segments:demos:compileKotlinJvm` passes.
- [x] Commit: `feat: add placeholder slides for launch, future and close`

### Task 9: Register the talk and remove the demos
**Serves:** R6, R7, NF1
**Files:** `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt` (edit), `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt` (edit), `segments/demos/src/commonMain/kotlin/ke/don/demos/{DemoLayoutExample,DeviceGallery,ExampleSlide,FeatureSlides,KodeViewerExample,SegmentedDemos,VideoSlide,WhiteboardExample}.kt` (delete)

- [x] In `SlidesNotes.kt`, delete every demo notes constant and add one constant per slide (e.g. `openingTitleNotes`): `"PLACEHOLDER. Presenter: <name>. <intent>"`.
- [x] In `skiPresentationSlides()`, replace all demo `slide(...)` calls with the 19 slides in outline order, `slide(label = "<label>", notes = <notes>) { <Composable>() }`, labels exactly as in the outline. `opening-title` uses `transition = ScreenTransition.Fade` and `showFooter = false`; `qa` uses `showFooter = false`. Keep the timer setup. Remove unused imports, including `IntroductionScreen`.
- [x] Delete the eight demo files listed above. Keep `VideoPlayer.kt` and its platform `actual`s.
- [x] Check: `./gradlew :desktopApp:run` shows 19 slides in outline order in both windows; `./gradlew :webApp:wasmJsBrowserDistribution` succeeds; `git diff --stat main -- shared/components` is empty.
- [x] Commit: `feat: register talk skeleton and remove Ski demos`

### Task 10: Snake frame with logo-and-cube header (revises Task 6)
**Serves:** R5
**Files:** `composeApp/src/commonMain/kotlin/ke/don/ski/Deck.kt` (edit), `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/Components.kt` (edit), `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/frame/DroidconFrame.kt` (delete)

- [x] In `Deck.kt`, build `mainFrame` from the stock `snake` frame again (as before Task 6) and drop the `droidconFrameFactory` import. Leave `guidesFrame` as is. Do not edit `:shared:components`.
- [x] In `MainHeader` (`Components.kt`), remove the "Ski" title, the `DeckMode` subtitle ("Ski presentation deck" text) and the bullet rail, so the header shows only the droidcon logo (light or dark per theme, sized explicitly) and `CubeHero`. Remove imports that become unused.
- [x] Delete `DroidconFrame.kt`. Keep `CubeBullet` in `MainFooter` and the `showFooter` handling in `PresentationDeck`.
- [x] Check: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs` passes; `grep -rn "DroidconFrame\|droidconFrameFactory" composeApp/src` returns nothing; `git diff --stat main -- shared/components` is empty; on `./gradlew :desktopApp:run` (checked by the user) both windows show the snake frame with only the logo and cube in the header and the cube bullet in the footer.
- [x] Commit: `feat: use snake frame with logo-and-cube header`

### Task 11: Logo-only header and green dot footer bullet (revises Task 10)
**Serves:** R5
**Files:** `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/Components.kt` (edit)

- [x] In `MainHeader`, remove `CubeHero` so the header shows only the droidcon logo (light or dark per theme, same size as now). Keep the `mode` parameter (removals are deferred).
- [x] In `MainFooter`, replace `CubeBullet` with an 8dp filled circle in the Chai neon green (from the Task 2 colours, design-inventory §1.2). Do not delete `CubeBullet` from `:shared:design`.
- [x] Remove imports that become unused in `Components.kt`.
- [x] Check: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs` passes; `grep -n "CubeHero\|CubeBullet" composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/Components.kt` returns nothing; `git diff --stat main -- shared/components` is empty; on `./gradlew :desktopApp:run` (checked by the user) the header shows only the logo and the footer a green dot.
- [x] Commit: `feat: logo-only header and green dot footer bullet`

### Task 12: Droidcon background with checkered floor and cube decorator
**Serves:** R8, R4
**Files:** `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/background/DroidconBackground.kt` (new), `composeApp/src/commonMain/kotlin/ke/don/ski/Deck.kt` (edit)
**Pattern:** `shared/components/src/commonMain/kotlin/io/github/donald_okara/components/backgrounds/Background.kt` (interface), `.../backgrounds/BackgroundComponent.kt` (decorator placement)

This task writes perspective drawing code; run the implementer on Sonnet.

- [x] Add `DroidconBackground` implementing the public `Background` interface (`pattern` and `decoratorImage` may be null/unused; `Render()` does the drawing). Do not edit `:shared:components`.
- [x] In `Render()`, fill the theme background, then draw a checkered floor in perspective, Star Wars crawl style: horizon at about 40% of the height, the floor below it; alternating cells filled with the Chai neon green at alpha 0.06 (other cells empty); cells shrink toward the horizon; the floor scrolls continuously away from the viewer, about one tile depth every 4 s (infinite transition, seamless loop). Draw with `Canvas`/`DrawScope` paths only.
- [x] On top, draw `CubeHero(size = 200.dp)` aligned `BottomEnd` with 16dp padding, replacing the Android robot decorator.
- [x] In `Deck.kt`, pass `DroidconBackground()` in place of the `BackgroundBuilder` chain; drop imports that become unused. Leave `Resources.Images.ANDROID_ROBOT` in `Resources.kt` (removals are deferred).
- [x] Check: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs` and `./gradlew :webApp:wasmJsBrowserDistribution` pass; `git diff --stat main -- shared/components` is empty; on `./gradlew :desktopApp:run` (checked by the user) both windows show a faint green checkered floor drifting toward the horizon, noticeable only on a close look, with the animated cube at bottom-right and slide text readable in light and dark.
- [x] Commit: `feat: add checkered floor background with cube decorator`
