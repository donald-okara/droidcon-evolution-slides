# Phase 4: Walkthrough

## Current state

- Shipped: the 31-slide meetup deck (S01-S31) with timeline, charts, delight and a big S01 hero; registered, phase 3 slides removed.
- Reviews: round 1 (timeline flicker, selected-stage details, big hero) fixed by Tasks 13-14; round 2 (ticket flow gap) was a stale checkout, no change needed. Archived with round 2 still "changes requested".
- Deferred: S32-S53 (bounty, feature bash, close); real screenshots and recordings (see docs/talk/needed-from-us.md).
- Open: showFooter=false on S01/S31, S14 confetti across both windows, notes constant names vs spec, S05 marks only Oct 2026, iosName -> ioName tidy.
- Manual checks not run: S04 hover without flicker, S01 hero, corner cube back on S02, deployed site.
- Known issues: none.

## How to verify

- Task 14: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs`; user: look at S01 on `:desktopApp:run`, and check the corner cube reappears on S02.
- Task 13: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs`; user: on `:desktopApp:run` S04, move across stages without flicker and see the lit stage's details on each step.
- Task 12: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution`; user steps through the deck on `:desktopApp:run`.
- Task 11: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs`; `grep -c "label ="` on SkiPresentationSlides.kt is 31; `git diff --stat e02c712 -- shared/components` is empty.
- Task 10: Task 5 compile check (plus `:composeApp:compileKotlinJvm`).
- Task 9: Task 5 compile check (plus `:composeApp:compileKotlinJvm`).
- Task 8: Task 5 compile check; `grep -rn "35%" segments/demos/src/commonMain/kotlin/ke/don/demos/droidcon/talk` is empty.
- Task 7: same compile check as Task 5.
- Task 6: same compile check as Task 5 (`:segments:demos` JVM and WasmJs).
- Task 5: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs`; step through S04 on `:desktopApp:run` once registered (Task 11).
- Task 4: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs`; look at it in S04 (Task 5).
- Task 3: `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs`.
- Task 2: `./gradlew :core:domain:compileKotlinJvm`; outline act minutes sum to 30.
- Task 1: `./gradlew projects` reports root project 'droidcon-evolution-slides' with the same modules as before.

## Changes by task

### Task 1: Rename the Gradle project
- `settings.gradle.kts`: `rootProject.name` is now `droidcon-evolution-slides`.
- No other build reference to the old name found; no further edits.

### Task 2: Outline and session length
- `docs/talk/outline.md` rewritten as the S01-S31 table (labels, intents, reveal steps S04 7, S24 6, S29 4; S25 optional); act minutes sum to 30, plus 10 for Q&A.
- `DeckBuilder.kt`: `SESSION_DURATION` 45 to 40 minutes.
- NOTE: overview 1.7 lists act 0 as S03-S05; outline uses S01-S05 so all 31 are covered.

### Task 3: Chart blocks and callout card
- `common/Charts.kt`: `ChartPoint`, `ColumnChart`, `BarChart` (Animatable, tween(800), highlight uses secondary), `Modifier.popIn`, `Confetti`.
- `common/CalloutCard.kt`: outlined card with title chip.
- NOTE: unused imports in Charts.kt (optional tidy).

### Task 4: Evolution timeline
- `common/EvolutionTimeline.kt`: `TimelineStage`, `EvolutionStages` (7 stages, 2-3 detail lines each), `EvolutionTimeline(stages, modifier, gapAfterIndex)`.
- Stage i lights at `LocalRevealStep >= i+1`; springs, pulses once, line draws on (tween 500); hover/click shows details; gap "?" wobbles on hover.
- NOTE: no `@Preview`; some detail citations not verified by reviewer, check in S04.

### Task 5: Cold open slides S01-S05
- `talk/ColdOpenSlides.kt`: `TitleSlide`, `TodaySlide`, `SameAppSlide`, `FamilyTreeSlide` (`FAMILY_TREE_STEPS = 7`, `gapAfterIndex = 2`), `SeasonsSlide`. Notes added to `SlidesNotes.kt`.
- Delight: S01 cube turns face-on; S02 QR cards pop in staggered; S03 commit count-up; S04 nodes light per step; S05 meetup-month flag pops in.
- S04 detail citations in EvolutionTimeline.kt verified against the overview (all 21 supported).
- NOTE: S05 only highlights the 2026-10 meetup month; S04 sibling lines are plain text, not drawn.

### Task 6: 2018-2020 slides S06-S10
- `talk/EarlyYearsSlides.kt`: `Y2018FirstSlide`, `Y2018CloneSlide`, `Y2019Slide`, `Y2020ModularSlide`, `Y2020NoConferenceSlide`; notes in `SlidesNotes.kt`.
- Delight: S06 bars grow; S07 matching-line sweep across both READMEs; S08 package chip pops in; S09 module count-up; S10 shrug tilts upright.
- NOTE: S07 sweep and S10 shrug are local composables. S06 chart shows Java files only; Kotlin counts are in the caption. `iosName` parameter in `ScreenNameRow` should read `ioName`.

### Task 7: 2022-2025 slides S11-S17
- `talk/OneRepoSlides.kt`: `Y2022ComposeSlide`, `ChaiSlide`, `OneRepoSlide`, `CommunityPeakSlide`, `QuietYearSlide`, `KmpThreadSlide`, `Navigation3Slide`; notes in `SlidesNotes.kt`.
- Delight: S11 columns grow, July highlighted; S12 chai swatches pop in; S13 file count 0 to 280; S14 confetti on "41"; S15 "3" pops in; S16 callout pops in after module chart; S17 chips pop in.
- NOTE: S11 also has a D2 chart; S14 shows four named contributors, not a wall of names; S16 omits "-2,111 lines" and the "14 modules" figure appears only in the chart.

### Task 8: 2026 slides S18-S21
- `talk/MeasuredSlides.kt`: `TwoReadersSlide`, `ModulesSlide`, `MeasuredSlide`, `NewLookSlide`; notes in `SlidesNotes.kt` (`measuredNotes` states device CS50C API 34, median of 15, warm starts excluded).
- `SwatchRow`, `hexOf` and palette lists moved from `LaunchSlides.kt` to `common/Swatches.kt` (internal); `LaunchDesignSlide` unchanged in output.
- Delight: S18 quote pops in; S19 module count 7 to 20; S20 tiles count up and shrink; S21 form-factor placeholders pop in staggered.
- NOTE: S19 third rule taken from overview S19 text (feature tests and fakes, core:testing).

### Task 9: Live and stack slides S22-S26
- `talk/LiveSlides.kt`: `CubeWidgetSlide`, `FollowsConferenceSlide`, `LiveDemoSlide` (`LIVE_DEMO_STEPS = 6`), `StackSlide`, `CodeGrewSlide`; notes in `SlidesNotes.kt` (`liveDemoNotes` has the date-back and fallback-video cues).
- Delight: S22 widget and Spec/Plan/Code chips pop in; S23 Before/During/After placeholders pop in; S24 step numbers turn into springing checks; S25 layer rows drop in; S26 StatTiles count up.
- NOTE: S24 uses a `RevealItem`-based `DemoStepRow` checklist instead of `RevealBulletList` (same reveal mechanism). S22 cube is a `MediaPlaceholder`. S26 is a D4 `ColumnChart` of Kotlin LOC plus test-file StatTile.

### Task 10: Lessons and close slides S27-S31
- `talk/LessonsActSlides.kt`: `LostAndFoundSlide`, `UncomfortableChartSlide`, `EightYearsTeachSlide` (`EIGHT_YEARS_STEPS = 4`), `ThankYouSlide`, `BreakItSlide`; notes in `SlidesNotes.kt` (S30 has the "Pass the mic" guest list).
- Delight: S27 rows drop in staggered; S28 flat community bar vs growing code bar, tagline pops in; S29 numbered disc per lesson; S30 names pop in; S31 cockroach wiggles.
- NOTE: S28 large number is "5" (people committing in 2026, 41 in 2023); each chart scales to its own max. Review fix round: added the focal number.

### Task 11: Register the new deck and remove the old slides
- `SkiPresentationSlides.kt` registers 31 slides in outline order, Fade transition; steps on S04, S24, S29; `showFooter = false` on S01 and S31.
- Deleted `droidcon/{opening,history,launch,future,close}/` and 130 lines of phase 3 notes (the "35% faster" note is gone). `common/` untouched.
- NOTE: notes constants keep the talk tasks' names rather than literal `<label>Notes` (spec.md:41).

### Task 13: Timeline details without flicker
- `EvolutionTimeline.kt`: flicker was per-slot hover dropping when the pointer moved from a node onto its card. Now one pointer region covers node row plus card; per-slot hover kept only for the gap "?" wobble.
- Card precedence: hovered, then clicked, then lit stage (`LocalRevealStep - 1`). A click pins a stage until the next reveal step. Reveal logic untouched.
- NOTE: hovering an unlit stage previews its details (allowed by the task).

### Task 14: Big cube hero on the title slide
- `ColdOpenSlides.kt`: `SpinningCube` renders the real `CubeHero` at 90% of the smaller side of the space above the title; face-on turn entrance kept. Added `COLD_TITLE_LABEL`.
- `DroidconBackground.kt` gains `cubeVisible: () -> Boolean`; `Deck.kt` hides the 200dp corner cube on S01 only; `SkiPresentationSlides.kt` uses the shared label.
- Escalated: no (one fix round). Review issues were the placeholder still shown and a duplicate cube from the background.
- Deviation-ish: touched composeApp files beyond the task's single file, to avoid two cubes on S01.

### Task 12: Delight audit, polish and full build
- `SlideTitle` fades in and rises over 400ms; `BulletList` rows stagger 120ms; `RevealBulletList`/`RevealItem` untouched.
- `SourceLine(text)` added to `SlideBlocks.kt`; 24 ad hoc "Source:" blocks replaced.
- Delight audit: every slide already had a moment; none added. One-shot effects are composition-scoped.
- NOTE: desktop step-through still pending (user).

### Fix: compose resources package (Task 1 regression)
- Renaming the root project changed the generated `Res` package. `shared/resources/build.gradle.kts` now pins `packageOfResClass = "ski.shared.resources.generated.resources"`. Commit 0f47f4a.

## Deviations from spec

- Task 12: S20 now has one `StatTile` (cold start); APK 28% and download 7.4% are text, to keep one focal number (NF1). Notes for S06, S09, S28 gained "does not measure" caveats (R11).
