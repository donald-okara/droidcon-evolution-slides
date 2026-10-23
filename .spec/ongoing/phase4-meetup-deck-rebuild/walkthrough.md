# Phase 4: Walkthrough

## Current state

- Tasks 1-7 done (through 2022-2025 slides S11-S17).
- Next: Task 8 (2026 slides S18-S21).
- Open: S05 marks only the Oct 2026 meetup month. Slides and notes are not registered until Task 11. Optional tidy: rename `iosName` to `ioName` in `ScreenNameRow` (EarlyYearsSlides.kt).
- Known issues: none.

## How to verify

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

### Fix: compose resources package (Task 1 regression)
- Renaming the root project changed the generated `Res` package. `shared/resources/build.gradle.kts` now pins `packageOfResClass = "ski.shared.resources.generated.resources"`. Commit 0f47f4a.

## Deviations from spec

None
