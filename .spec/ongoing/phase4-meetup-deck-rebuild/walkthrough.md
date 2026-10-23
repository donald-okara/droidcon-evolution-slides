# Phase 4: Walkthrough

## Current state

- Tasks 1-5 done (rename, outline/40 min session, chart blocks, evolution timeline, cold open slides S01-S05).
- Next: Task 6 (2018-2020 slides S06-S10).
- Open: S05 marks only the Oct 2026 meetup month (overview gives no dates for earlier conferences). Slides and notes are not registered until Task 11.
- Known issues: none.

## How to verify

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

### Fix: compose resources package (Task 1 regression)
- Renaming the root project changed the generated `Res` package. `shared/resources/build.gradle.kts` now pins `packageOfResClass = "ski.shared.resources.generated.resources"`. Commit 0f47f4a.

## Deviations from spec

None
