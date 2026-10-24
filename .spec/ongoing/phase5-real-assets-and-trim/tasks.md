# Phase 5: Tasks

```
1. Register drawables + SlideImage block
   ├─> 2. Lift SpinningCube, live cube on S22, hide corner cube
   ├─> 3. Cold open: S02, remove S03
   └─> 4. SplitSlide block; early years: S07, S08, S09, S12
         ├─> 7. One repo split: S13, S15, S17
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

- [x] Move `SpinningCube` and its constants (`CUBE_SPIN_MILLIS`, `CUBE_HERO_FILL`) from `ColdOpenSlides.kt` to `common/SpinningCube.kt` as a public `SpinningCube(modifier: Modifier = Modifier)`. `TitleSlide` (S01) calls the shared one; S01 looks unchanged.
- [x] In `LiveSlides.kt` `CubeWidgetSlide` (S22), replace the "Cube hero" `MediaPlaceholder` (line 68) with `SpinningCube`, sized to the space the placeholder had.
- [x] Add a public `const val CUBE_WIDGET_LABEL = "y2026-cube-widget"` next to `COLD_TITLE_LABEL` in `ColdOpenSlides.kt` (same visibility as that constant), and use it for the S22 `slide(label = ...)` in `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt` (line 146).
- [x] In `Deck.kt` line 51, hide the background cube when the current label is `COLD_TITLE_LABEL` or `CUBE_WIDGET_LABEL`; update the comment on line 49 to name both slides.
- [x] Leave the S22 widget placeholder for Task 5.
- [x] Check: `./gradlew :composeApp:compileKotlinJvm`; desktop run: S01 and S22 show one big spinning cube and no corner cube; S21 shows the corner cube.
- [x] Commit: `feat: live cube hero on S22`

### Task 3: Cold open: S02 assets, remove S03
**Serves:** R3, R4, R7
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/ColdOpenSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt (edit), composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit)

- [x] S02 `TodaySlide`: keep two QR cards, "Play listing" with `Resources.Images.PLAY_QR` and "Repo" with `Resources.Images.REPO_QR`; delete the "APK fallback" card. Keep the pop-in by index (0, 1). In `QrCard`, replace `MediaPlaceholder` with `SlideImage`, keeping the QR square (`aspectRatio(1f)`).
- [x] Remove S03: delete `SameAppSlide` and the private helpers only it uses (`ScreenshotCard`, `CommitCountUp`, `withThousands`, and any constants only they use) from `ColdOpenSlides.kt`; delete its `slide(...)` block and import in `SkiPresentationSlides.kt`; delete `sameAppNotes` from `SlidesNotes.kt`.
- [x] Check: `./gradlew :composeApp:compileKotlinJvm`; `grep -rn "SameAppSlide\|sameAppNotes" composeApp segments` returns nothing; desktop run: S02 shows two QRs, the next slide is the family tree.
- [x] Commit: `feat: real QR codes on S02, remove S03`

### Task 4: SplitSlide layout and early years (S07, S08, S09, S12)
**Serves:** R3, R4, R8
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/EarlyYearsSlides.kt (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/OneRepoSlides.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt (existing slide blocks and their spacing from `Values.Dimens`)

- [x] In `common/SlideBlocks.kt`, add `SplitSlide(lead: @Composable ColumnScope.() -> Unit, details: @Composable ColumnScope.() -> Unit, modifier: Modifier = Modifier)`: a full-size `Row` of two equal-weight `Column`s with the deck's standard gap; `lead` is vertically centred, `details` is vertically centred. If `SlideBlocks.kt` already has an equivalent two-column block, reuse it and say so in the report. Add a KDoc line.
- [x] S09 `Y2020ModularSlide`: replace the `settings.gradle.kts` placeholder (EarlyYearsSlides.kt line 336) with `SlideImage(Resources.Images.SETTINGS_GRADLE, "settings.gradle.kts include block")`, keeping its weight in the row. S09 does not use `SplitSlide`.
- [x] S07 `Y2018CloneSlide`: remove the two `ScreenshotPair` calls and delete `ScreenshotPair`. Use `SplitSlide`: lead = `SlideTitle` + the README sweep line; details = the screen-name rows and any remaining text.
- [x] S08 `Y2019Slide`: remove the two 2019 placeholders (lines 273-274) and the `Row` around them. Use `SplitSlide`: lead = `SlideTitle` + the package chips; details = the remaining text.
- [x] S12 `ChaiSlide` (OneRepoSlides.kt): remove the chai cover placeholder. Use `SplitSlide`: lead = `SlideTitle` + `ChaiSwatches`; details = the remaining text.
- [x] Keep each slide's existing reveal state and animations; only the layout changes. Remove now-unused `MediaPlaceholder` imports.
- [x] Check: `./gradlew :segments:demos:compileKotlinJvm`; desktop run S07, S08, S12: title and focal element left, details right; S09 shows the image.
- [x] Commit: `feat: SplitSlide layout, settings.gradle on S09, trim early years media`

### Task 5: Measured and live slides (S18, S21, S22 widget, S23, S24)
**Serves:** R3, R4, R8
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/MeasuredSlides.kt (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/LiveSlides.kt (edit)

- [ ] S18 `TwoReadersSlide`: replace the "Top of AGENTS.md" placeholder (MeasuredSlides.kt line 86) with `SlideImage(Resources.Images.TOP_OF_AGENTS, "Top of AGENTS.md")`.
- [ ] S21 `NewLookSlide`: replace the four golden placeholders (line 246 loop) with one `SlideImage(Resources.Images.SESSIONS_2026, "Sessions screen, 2026")` at phone aspect, labelled "Sessions, 2026" (not "Golden"), beside the swatch rows.
- [ ] S22 `CubeWidgetSlide`: remove the widget placeholder (LiveSlides.kt line 76); the cube from Task 2 takes the freed space.
- [ ] S23 `FollowsConferenceSlide`: show the 2026 Home as the "before" state via `SlideImage(Resources.Images.HOME_2026, ...)` at phone aspect; remove "during" and "after" image slots but keep their text labels if the slide lists the three states.
- [ ] S24 `LiveDemoSlide`: remove the scrcpy placeholder (line 180). Use `SplitSlide` (from Task 4): lead = `SlideTitle` alone (no focal element), details = the `DemoStepRow`s, keeping their pop-in.
- [ ] Remove now-unused `MediaPlaceholder` imports.
- [ ] Check: `./gradlew :segments:demos:compileKotlinJvm`; desktop run S18, S21, S22, S23, S24 (S24: title left, steps right).
- [ ] Commit: `feat: real screenshots on S18, S21, S23, trim missing media`

### Task 6: Notes, asset list, and cleanup
**Serves:** R4, R6
**Files:** composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt (edit), docs/talk/needed-from-us.md (edit), segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/SlideBlocks.kt (edit)

- [ ] In `SlidesNotes.kt`, update `todayNotes`, `y2018CloneNotes`, `y2019AllJetpackNotes`, `y2020ModularNotes`, `chaiNotes`, `oneRepoNotes`, `quietYearNotes`, `navigation3Notes`, `twoReadersNotes`, `newLookNotes`, `cubeWidgetNotes`, `followsConferenceNotes`, `liveDemoNotes` so each describes what is on screen now. Where media was removed, say it aloud instead (e.g. S13: "say the number: one commit, N files"). `liveDemoNotes` says to switch to the scrcpy window. `todayNotes` drops the APK QR and says the APK link is in the repo README if Play has not reached a phone.
- [ ] In `docs/talk/needed-from-us.md`, fill every "Include?" cell: `yes (<file name>)` for the six used assets, `no (trimmed)` for the rest; S22 cube is `yes (live CubeHero)`.
- [ ] If `grep -rn "MediaPlaceholder" segments/` shows only the definition, delete `MediaPlaceholder` from `SlideBlocks.kt` and its now-unused imports.
- [ ] Check: `grep -rn "MediaPlaceholder" segments/` returns nothing; `./gradlew :composeApp:compileKotlinJvm` passes.
- [ ] Commit: `docs: notes and asset list match trimmed slides`

### Task 7: One-repo slides in split layout (S13, S15, S17)
**Serves:** R4, R8
**Files:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/OneRepoSlides.kt (edit)
**Pattern:** segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/OneRepoSlides.kt (`ChaiSlide`, after Task 4 put it in `SplitSlide`)

- [ ] S13 `OneRepoSlide` ("2023: one repo to keep"): remove the terminal placeholder (line 204). Use `SplitSlide`: lead = `SlideTitle` + `OneRepoCountUp`; details = the remaining text.
- [ ] S15 `QuietYearSlide`: remove the diff placeholder (line 335). Use `SplitSlide`: lead = `SlideTitle` plus the slide's headline number if it has one (e.g. a "3 files, 3 lines" line), otherwise the title alone; details = the remaining text.
- [ ] S17 `Navigation3Slide`: remove the recording placeholder (line 432). Use `SplitSlide`: lead = `SlideTitle` + the `StatusChip` row; details = the remaining text.
- [ ] Keep reveal state and animations; remove the `MediaPlaceholder` import if now unused.
- [ ] Check: `./gradlew :segments:demos:compileKotlinJvm`; desktop run S13, S15, S17: title and focal element left, details right.
- [ ] Commit: `feat: split layout for S13, S15, S17`
