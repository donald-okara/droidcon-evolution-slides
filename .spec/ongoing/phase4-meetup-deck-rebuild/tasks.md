# Phase 4: Tasks

Common to every slide task: read only the named slide entries in `docs/talk/harun-overview.md` (find them with `grep -n "^#### S06" docs/talk/harun-overview.md`), chart data in its Appendix D, and follow `docs/speaker-notes.md` for notes. New slide composables go in `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk/`, notes as `<label>Notes` constants in `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt`. Do not register slides in `SkiPresentationSlides.kt` until Task 11. Reveal slides expose `const val <SLIDE>_STEPS`. Credit contributors by name where the overview does. Leave screenshots as `MediaPlaceholder`.

```
1. Rename project
2. Outline + session length
3. Chart blocks + callout  ──┐
4. Evolution timeline ───────┤
                             ├─> 5. S01-S05 ─> 6. S06-S10 ─> 7. S11-S17 ─> 8. S18-S21 ─> 9. S22-S26 ─> 10. S27-S31 ─> 11. Register + remove old ─> 12. Polish + full build
```

---

### Task 1: Rename the Gradle project
**Serves:** R1
**Files:** `settings.gradle.kts` (edit)

- [x] Change `rootProject.name = "Ski"` to `rootProject.name = "droidcon-evolution-slides"`.
- [x] `grep -rn "\"Ski\"" --include=*.kts --include=*.properties .` and report any other build reference to the root name; change one only if the build breaks without it.
- [x] Check: `./gradlew projects` lists the same modules as before.
- [x] Commit: `chore: rename gradle root project`

### Task 2: Outline and session length
**Serves:** R2
**Files:** `docs/talk/outline.md` (edit), `core/domain/src/commonMain/kotlin/ke/don/domain/DeckBuilder.kt` (edit)

- [x] Replace the outline's arc, sections and slide list with S01 to S31 from Harun's "Shape: seven acts" table (section 1.7) and Part 4: a row per slide with label (kebab-case, e.g. `cold-same-app`, `y2018-clone`), one-line intent, reveal steps (S04 7 stages, S24 6 demo steps, S29 4 lessons; others 0) and minutes per act. Mark S25 optional.
- [x] Keep the thesis line as "From I/O clone to our own app"; note the 3-presenter droidcon plan is superseded by phase 4.
- [x] In `DeckBuilder.kt` set `SESSION_DURATION` to 40 minutes, keeping the existing type and unit.
- [x] Check: act minutes in the outline sum to 30; `./gradlew :core:domain:compileKotlinJvm` passes.
- [x] Commit: `docs: outline for meetup deck and 40 minute session`

### Task 3: Chart blocks and callout card
**Serves:** R4, R11, NF1
**Files:** `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/Charts.kt` (new), `.../common/CalloutCard.kt` (new)
**Pattern:** `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/StatTile.kt`

- [x] `ChartPoint(label: String, value: Float, highlight: Boolean = false)`.
- [x] `ColumnChart(points, modifier, caption)`: vertical columns scaled to the max value, value label above each, bar heights animate from 0 with `Animatable` and `tween(800)`, highlighted points use `secondary`.
- [x] `BarChart(points, modifier, valueSuffix)`: horizontal bars, label left, value right, same animation.
- [x] `CalloutCard(title, modifier, content)`: rounded outlined box with a small title chip ("Kotlin Kenya corner") using `MaterialTheme` colours.
- [x] `Modifier.popIn(delayMillis: Int = 0)`: scale 0.8 to 1 and fade in with a spring that overshoots slightly. `Confetti(trigger: Boolean, modifier)`: a 1.2 s burst of 24 coloured dots from `Canvas` using theme colours, playing once per `trigger` change.
- [x] Check: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes.
- [x] Commit: `feat: chart blocks and callout card`

### Task 4: Evolution timeline
**Serves:** R3, R11
**Files:** `segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/common/EvolutionTimeline.kt` (new)
**Pattern:** `.../common/Reveal.kt` (reading `LocalRevealStep`), `.../common/StatTile.kt`

- [x] `TimelineStage(year: String, title: String, repo: String, details: List<String>)`.
- [x] `EvolutionTimeline(stages, modifier, gapAfterIndex: Int? = null)`: a horizontal line with a node per stage, year above and title below; a dashed gap marker after `gapAfterIndex` labelled with the missing year.
- [x] Highlight: stage i is lit when `LocalRevealStep.current >= i + 1`; unlit stages are muted; nodes scale up with a spring when lit.
- [x] Hover: `Modifier.hoverable` with `collectIsHoveredAsState` and `clickable` toggle show a details card under the hovered/selected stage listing `details`.
- [x] Lit nodes pulse once on lighting; the line up to the lit stage draws on with `tween(500)`; the 2021 gap marker shows a "?" that wobbles on hover.
- [x] Add `val EvolutionStages: List<TimelineStage>` with the seven stages (2018 `droidconKE`, 2019 `droidconKE2019App`, 2020 `droidconKE2020App`, 2022 `droidconKE2022Android`, 2023 `droidconKeKotlin`, 2025 `droidconKeKMP`, 2026 ticketing and new look) and two to three cited detail lines each from Harun's overview sections 1.1 and 1.2.
- [x] Check: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes. User: look at it in Task 5's slide S04.
- [x] Commit: `feat: evolution timeline with hover details`

### Task 5: Cold open slides S01 to S05
**Serves:** R5, R11, NF1, NF2
**Files:** `segments/demos/.../droidcon/talk/ColdOpenSlides.kt` (new), `SlidesNotes.kt` (edit)
**Pattern:** `segments/demos/.../droidcon/opening/OpeningSlides.kt`

- [x] `TitleSlide` (S01), `TodaySlide` (S02, three QR `MediaPlaceholder`s), `SameAppSlide` (S03, one large "~2,100 commits", two side-by-side screenshot placeholders), `FamilyTreeSlide` (S04, `EvolutionTimeline` with `gapAfterIndex` at 2021; `FAMILY_TREE_STEPS = 7`), `SeasonsSlide` (S05, `ColumnChart` of D1).
- [x] Notes: `titleNotes`, `todayNotes`, `sameAppNotes`, `familyTreeNotes` (what each press shows), `seasonsNotes` (what commit counts do not measure).
- [x] Delight: add one delight moment per slide from the Task 3 and 4 blocks (S01 cube spins in, S03 count-up, S05 conference flags pop) and list them one line per slide in your report.
- [x] Check: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes.
- [x] Commit: `feat: cold open slides S01-S05`

### Task 6: 2018 to 2020 slides S06 to S10
**Serves:** R5, R11
**Files:** `segments/demos/.../droidcon/talk/EarlyYearsSlides.kt` (new), `SlidesNotes.kt` (edit)
**Pattern:** `.../talk/ColdOpenSlides.kt`

- [x] `Y2018FirstSlide` (S06, D6 Java to Kotlin as a `BarChart`), `Y2018CloneSlide` (S07, side-by-side README and screenshot placeholders, the highlight slide), `Y2019Slide` (S08), `Y2020ModularSlide` (S09), `Y2020NoConferenceSlide` (S10, with a "pass the mic" `CalloutCard` naming Oliver Muthomi, Jabez Magomere, David Odari).
- [x] Notes for each, prefixed by the mic-hand-over cue where the overview has one.
- [x] Delight: add one delight moment per slide from the Task 3 and 4 blocks (S07 matching-line highlight sweep across the two READMEs, S10 shrug on the 2021 gap; others of your choice) and list them one line per slide in your report.
- [x] Check: compile check from Task 5 passes.
- [x] Commit: `feat: 2018-2020 slides S06-S10`

### Task 7: 2022 to 2025 slides S11 to S17
**Serves:** R6, R11
**Files:** `segments/demos/.../droidcon/talk/OneRepoSlides.kt` (new), `SlidesNotes.kt` (edit)
**Pattern:** `.../talk/EarlyYearsSlides.kt`

- [x] `Y2022ComposeSlide` (S11), `ChaiSlide` (S12), `OneRepoSlide` (S13), `CommunityPeakSlide` (S14, D5 `ColumnChart`), `QuietYearSlide` (S15), `KmpThreadSlide` (S16, "Kotlin Kenya corner" `CalloutCard` and the D8 module line as a `ColumnChart`), `Navigation3Slide` (S17, screen-recording placeholder).
- [x] Notes for each.
- [x] Delight: add one delight moment per slide from the Task 3 and 4 blocks (S12 chai colours spill in one by one, S14 confetti on the peak; others of your choice) and list them one line per slide in your report.
- [x] Check: compile check from Task 5 passes.
- [x] Commit: `feat: 2022-2025 slides S11-S17`

### Task 8: 2026 slides S18 to S21
**Serves:** R7, R11, NF1
**Files:** `segments/demos/.../droidcon/talk/MeasuredSlides.kt` (new), `SlidesNotes.kt` (edit)
**Pattern:** `segments/demos/.../droidcon/launch/LaunchSlides.kt` (for `StatTile` use)

- [x] `TwoReadersSlide` (S18), `ModulesSlide` (S19, module-count line from D8, plus three rule bullets), `MeasuredSlide` (S20: `StatTile` for cold start 22% down, APK 28% down, download 7.4% down, and a `BarChart` of D7's four startup modes; full AOT slower), `NewLookSlide` (S21, then and now palette swatches reused from `LaunchDesignSlide`, form-factor placeholders).
- [x] Notes: `measuredNotes` states device, median of 15 runs and that startup excludes warm starts. The 35% figure appears nowhere.
- [x] Delight: add one delight moment per slide from the Task 3 and 4 blocks (S19 module count ticks 7 to 20, S20 tiles shrink as they count; others of your choice) and list them one line per slide in your report.
- [x] Check: compile check from Task 5 passes; `grep -rn "35%" segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk` is empty.
- [x] Commit: `feat: 2026 slides S18-S21`

### Task 9: Live and stack slides S22 to S26
**Serves:** R7, R11
**Files:** `segments/demos/.../droidcon/talk/LiveSlides.kt` (new), `SlidesNotes.kt` (edit)
**Pattern:** `.../talk/MeasuredSlides.kt`

- [ ] `CubeWidgetSlide` (S22), `FollowsConferenceSlide` (S23), `LiveDemoSlide` (S24, `RevealBulletList` of the six demo steps from section 1.7, `LIVE_DEMO_STEPS = 6`), `StackSlide` (S25, optional: layer table from section 1.3), `CodeGrewSlide` (S26, D4 `ColumnChart`).
- [ ] `liveDemoNotes` include the "set the date back before step 5" and fallback-video cues.
- [ ] Delight: add one delight moment per slide from the Task 3 and 4 blocks (S24 demo steps check themselves off; others of your choice) and list them one line per slide in your report.
- [ ] Check: compile check from Task 5 passes.
- [ ] Commit: `feat: live demo and stack slides S22-S26`

### Task 10: Lessons and close slides S27 to S31
**Serves:** R8, R11
**Files:** `segments/demos/.../droidcon/talk/LessonsActSlides.kt` (new), `SlidesNotes.kt` (edit)
**Pattern:** `segments/demos/.../droidcon/close/CloseSlides.kt`

- [ ] `LostAndFoundSlide` (S27), `UncomfortableChartSlide` (S28, D5 chart, one large number), `EightYearsTeachSlide` (S29, 4 revealed lessons, `EIGHT_YEARS_STEPS = 4`), `ThankYouSlide` (S30, contributor names), `BreakItSlide` (S31, bounty hand-over, `showFooter = false` candidate).
- [ ] Notes for each; S30 notes include the "Pass the mic" guest list.
- [ ] Delight: add one delight moment per slide from the Task 3 and 4 blocks (S28 flat community bar against a growing code bar, S30 names pop in, S31 wiggling bug) and list them one line per slide in your report.
- [ ] Check: compile check from Task 5 passes.
- [ ] Commit: `feat: lessons and close slides S27-S31`

### Task 11: Register the new deck and remove the old slides
**Serves:** R9, R10
**Files:** `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt` (edit), `SlidesNotes.kt` (edit), `segments/demos/.../droidcon/{opening,history,launch,future,close}/` (delete)
**Pattern:** the current `SkiPresentationSlides.kt`

- [ ] Rewrite `skiPresentationSlides()` to register the 31 slides in outline order with outline labels, `transition = ScreenTransition.Fade`, `steps = *_STEPS` for reveal slides (S04, S24, S29) and `showFooter = false` where noted.
- [ ] Delete the phase 3 slide files listed above and every now-unused notes constant; keep `common/` and anything the new slides import (move `LaunchDesignSlide` swatch helpers into `common/` first if Task 8 imports them).
- [ ] Check: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs` passes; `grep -c "label =" composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt` equals 31; `git diff --stat e02c712 -- shared/components` is empty.
- [ ] Commit: `feat: register meetup deck, remove phase 3 slides`

### Task 12: Delight audit, polish and full build
**Serves:** R9, R11, NF1, NF2
**Files:** `segments/demos/.../droidcon/talk/*.kt` (edit), `segments/demos/.../droidcon/common/SlideBlocks.kt` (edit)

- [ ] Give `SlideTitle` an entrance (fade plus slight upward slide, `tween(400)`) and make `BulletList` rows stagger in; keep reveal slides' behaviour unchanged.
- [ ] Each slide that cites a source shows it via one shared `SourceLine(text)` block in `SlideBlocks.kt` (small, muted); replace per-slide ad hoc text.
- [ ] Audit: read each slide task's delight list and add a moment to any slide missing one.
- [ ] Make one-shot effects (Confetti, pulses) fire from the reveal step or slide entry, not from window-local timers, so both windows show them.
- [ ] Check: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes. User: step through on `:desktopApp:run`.
- [ ] Commit: `feat: delight audit, entrances and shared source line`
