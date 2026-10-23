# Phase 4: Walkthrough

## Current state

- Tasks 1 and 2 done (project renamed; outline S01-S31 and 40 minute session).
- Next: Tasks 3 and 4 (independent), then slides.
- Known issues: none.

## How to verify

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

## Deviations from spec

None
