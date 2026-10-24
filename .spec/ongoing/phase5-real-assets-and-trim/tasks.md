# Phase 5: Tasks

```
1. Register drawables + SlideImage block
   ├─> 2. Lift SpinningCube, live cube on S22, hide corner cube
   ├─> 3. Cold open: S02, remove S03
   ├─> 4. Early years + One repo: S07, S08, S09, S12, S13, S15, S17
   └─> 5. Measured + Live: S18, S21, S22 widget, S23, S24
         └─> 6. Notes, needed-from-us, delete MediaPlaceholder
```

Slide composables live in `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/` (written `talk/` below) and shared blocks in `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/` (written `common/`).

---

### Task 1: Register drawables and add a SlideImage block
**Serves:** R1, R2
**Files:** shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt (edit)
**Pattern:** composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/Components.kt (the `Image(painter = painterResource(...))` at line 130)

- [x] In `Resources.Images`, add a `//Talk assets` group: `PLAY_QR = Res.drawable.playstore_droidcon_kotlin`, `REPO_QR = Res.drawable.github_droidcon_ke_kotlin`, `HOME_2026 = Res.drawable.home_screen_2026_w_agenda`, `SESSIONS_2026 = Res.drawable.sessions_2026`, `SETTINGS_GRADLE = Res.drawable.settings_gradle`, `TOP_OF_AGENTS = Res.drawable.top_of_agents`.
- [x] In `common/SlideBlocks.kt`, next to `MediaPlaceholder`, add `SlideImage(resource: DrawableResource, contentDescription: String, modifier: Modifier = Modifier)`: an `Image` with `painterResource(resource)`, `ContentScale.Fit`, clipped and bordered with the same shape and outline as `MediaPlaceholder`. It must not call `fillMaxSize()` itself, so callers size it.
- [x] Add a KDoc line matching the file's style.
- [x] Check: `./gradlew :segments:demos:compileKotlinJvm`
- [x] Commit: `feat: register talk drawables and add SlideImage`

### Task 2: Lift SpinningCube and show the live cube on S22
**Serves:** R5
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SpinningCube.kt (new), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/ColdOpenSlides.kt (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/LiveSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/Deck.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/ColdOpenSlides.kt (`SpinningCube`, lines 89-105)

- [ ] Move `SpinningCube` and its constants (`CUBE_SPIN_MILLIS`, `CUBE_HERO_FILL`) from `ColdOpenSlides.kt` to `common/SpinningCube.kt` as a public `SpinningCube(modifier: Modifier = Modifier)`. `TitleSlide` (S01) calls the shared one; S01 looks unchanged.
- [ ] In `LiveSlides.kt` `CubeWidgetSlide` (S22), replace the "Cube hero" `MediaPlaceholder` (line 68) with `SpinningCube`, sized to the space the placeholder had.
- [ ] Add a public `const val CUBE_WIDGET_LABEL = "y2026-cube-widget"` next to `COLD_TITLE_LABEL` in `ColdOpenSlides.kt` (same visibility as that constant), and use it for the S22 `slide(label = ...)` in `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt` (line 146).
- [ ] In `Deck.kt` line 51, hide the background cube when the current label is `COLD_TITLE_LABEL` or `CUBE_WIDGET_LABEL`; update the comment on line 49 to name both slides.
- [ ] Leave the S22 widget placeholder for Task 5.
- [ ] Check: `./gradlew :composeApp:compileKotlinJvm`; desktop run: S01 and S22 show one big spinning cube and no corner cube; S21 shows the corner cube.
- [ ] Commit: `feat: live cube hero on S22`

### Task 3: Cold open: S02 assets, remove S03
**Serves:** R3, R4, R7
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/ColdOpenSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit)

- [ ] S02 `TodaySlide`: keep two QR cards, "Play listing" with `Resources.Images.PLAY_QR` and "Repo" with `Resources.Images.REPO_QR`; delete the "APK fallback" card. Keep the pop-in by index (0, 1). In `QrCard`, replace `MediaPlaceholder` with `SlideImage`, keeping the QR square (`aspectRatio(1f)`).
- [ ] Remove S03: delete `SameAppSlide` and the private helpers only it uses (`ScreenshotCard`, `CommitCountUp`, `withThousands`, and any constants only they use) from `ColdOpenSlides.kt`; delete its `slide(...)` block and import in `SkiPresentationSlides.kt`; delete `sameAppNotes` from `SlidesNotes.kt`.
- [ ] Check: `./gradlew :composeApp:compileKotlinJvm`; `grep -rn "SameAppSlide\|sameAppNotes" composeApp segments` returns nothing; desktop run: S02 shows two QRs, the next slide is the family tree.
- [ ] Commit: `feat: real QR codes on S02, remove S03`

### Task 4: Early years and one-repo slides (S07, S08, S09, S12, S13, S15, S17)
**Serves:** R3, R4
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/EarlyYearsSlides.kt (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/OneRepoSlides.kt (edit)

- [ ] S09 `Y2020ModularSlide`: replace the `settings.gradle.kts` placeholder (EarlyYearsSlides.kt line 336) with `SlideImage(Resources.Images.SETTINGS_GRADLE, "settings.gradle.kts include block")`, keeping its weight in the row.
- [ ] S07 `Y2018CloneSlide`: remove the two `ScreenshotPair` calls and delete `ScreenshotPair`; let the README sweep and screen-name rows fill the slide.
- [ ] S08 `Y2019Slide`: remove the two 2019 placeholders (lines 273-274) and the `Row` around them; the package chips fill the space.
- [ ] S12 `ChaiSlide`, S13 `OneRepoSlide`, S15 `QuietYearSlide`, S17 `Navigation3Slide`: remove the placeholder and give the remaining content (swatches, count-up, text, status chips) the freed `weight` so nothing sits in a blank half.
- [ ] Remove now-unused `MediaPlaceholder` imports.
- [ ] Check: `./gradlew :segments:demos:compileKotlinJvm`; desktop run S07, S08, S09, S12, S13, S15, S17: no empty column.
- [ ] Commit: `feat: settings.gradle on S09, trim missing media from early years and one repo`

### Task 5: Measured and live slides (S18, S21, S22 widget, S23, S24)
**Serves:** R3, R4
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/MeasuredSlides.kt (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/LiveSlides.kt (edit)

- [ ] S18 `TwoReadersSlide`: replace the "Top of AGENTS.md" placeholder (MeasuredSlides.kt line 86) with `SlideImage(Resources.Images.TOP_OF_AGENTS, "Top of AGENTS.md")`.
- [ ] S21 `NewLookSlide`: replace the four golden placeholders (line 246 loop) with one `SlideImage(Resources.Images.SESSIONS_2026, "Sessions screen, 2026")` at phone aspect, labelled "Sessions, 2026" (not "Golden"), beside the swatch rows.
- [ ] S22 `CubeWidgetSlide`: remove the widget placeholder (LiveSlides.kt line 76); the cube from Task 2 takes the freed space.
- [ ] S23 `FollowsConferenceSlide`: show the 2026 Home as the "before" state via `SlideImage(Resources.Images.HOME_2026, ...)` at phone aspect; remove "during" and "after" image slots but keep their text labels if the slide lists the three states.
- [ ] S24 `LiveDemoSlide`: remove the scrcpy placeholder (line 180); the demo steps fill the slide.
- [ ] Remove now-unused `MediaPlaceholder` imports.
- [ ] Check: `./gradlew :segments:demos:compileKotlinJvm`; desktop run S18, S21, S22, S23, S24.
- [ ] Commit: `feat: real screenshots on S18, S21, S23, trim missing media`

### Task 6: Notes, asset list, and cleanup
**Serves:** R4, R6
**Files:** composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit), docs/talk/needed-from-us.md (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt (edit)

- [ ] In `SlidesNotes.kt`, update `todayNotes`, `y2018CloneNotes`, `y2019AllJetpackNotes`, `y2020ModularNotes`, `chaiNotes`, `oneRepoNotes`, `quietYearNotes`, `navigation3Notes`, `twoReadersNotes`, `newLookNotes`, `cubeWidgetNotes`, `followsConferenceNotes`, `liveDemoNotes` so each describes what is on screen now. Where media was removed, say it aloud instead (e.g. S13: "say the number: one commit, N files"). `liveDemoNotes` says to switch to the scrcpy window. `todayNotes` drops the APK QR and says the APK link is in the repo README if Play has not reached a phone.
- [ ] In `docs/talk/needed-from-us.md`, fill every "Include?" cell: `yes (<file name>)` for the six used assets, `no (trimmed)` for the rest; S22 cube is `yes (live CubeHero)`.
- [ ] If `grep -rn "MediaPlaceholder" segments/` shows only the definition, delete `MediaPlaceholder` from `SlideBlocks.kt` and its now-unused imports.
- [ ] Check: `grep -rn "MediaPlaceholder" segments/` returns nothing; `./gradlew :composeApp:compileKotlinJvm` passes.
- [ ] Commit: `docs: notes and asset list match trimmed slides`
