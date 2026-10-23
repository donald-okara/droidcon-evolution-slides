# Phase 4: Walkthrough

## Current state

- Tasks 1-4 done (rename, outline/40 min session, chart blocks and callout card, evolution timeline).
- Next: Task 5 (cold open slides S01-S05); use `gapAfterIndex = 2` for the 2021 gap.
- Open: check the S04 detail-line citations (e.g. "173 of 177 Kotlin files kept their path") against the overview.
- Known issues: none.

## How to verify

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

### Fix: compose resources package (Task 1 regression)
- Renaming the root project changed the generated `Res` package. `shared/resources/build.gradle.kts` now pins `packageOfResClass = "ski.shared.resources.generated.resources"`. Commit 0f47f4a.

## Deviations from spec

None
