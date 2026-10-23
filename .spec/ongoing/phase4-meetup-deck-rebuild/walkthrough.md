# Phase 4: Walkthrough

## Current state

- Task 1 done (Gradle root project renamed).
- Next: Task 2 (outline and session length), then Tasks 3 and 4 (independent), then slides.
- Known issues: none.

## How to verify

- Task 1: `./gradlew projects` reports root project 'droidcon-evolution-slides' with the same modules as before.

## Changes by task

### Task 1: Rename the Gradle project
- `settings.gradle.kts`: `rootProject.name` is now `droidcon-evolution-slides`.
- No other build reference to the old name found; no further edits.

## Deviations from spec

None
