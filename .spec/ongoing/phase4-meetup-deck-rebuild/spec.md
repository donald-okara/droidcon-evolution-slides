# Phase 4: Specification

## Requirements

### R1. Project name
- `rootProject.name` in `settings.gradle.kts` is "droidcon-evolution-slides". No module path, package or published artifact changes.
- **Check:** `grep rootProject settings.gradle.kts`; `./gradlew projects` lists the same modules as before.

### R2. Outline and session length
- `docs/talk/outline.md` lists S01 to S31 in seven acts, each row with a slide label, a one-line intent, any reveal steps, and minutes. Act budgets add up to 30.
- `SlidesConstants.SESSION_DURATION` is 40 minutes.
- **Check:** read the outline's table; grep the constant.

### R3. Evolution timeline
- `EvolutionTimeline` shows stages 2018, 2019, 2020, 2022, 2023, 2025 and 2026, each with a label, the repo or era name, and a gap marker at 2021.
- Hovering a stage (click on touch) shows its details: dates, main change and one cited fact. Reveal steps highlight stages left to right.
- **Check:** on `:desktopApp:run`, hover each stage on the timeline slide and see its details; Right highlights the next stage.

### R4. Chart and callout blocks
- `BarChart` and `ColumnChart` render labelled values from lists, animate in, and use `MaterialTheme` colours. `CalloutCard` renders a titled box ("Kotlin Kenya corner").
- **Check:** the slides that use them (S05, S20, S15) show the Appendix D data.

### R5. Acts 0 to 3 slides (S01 to S10)
- Each slide shows its "On screen" text from the overview, a visual (chart, timeline or labelled placeholder), notes from "Say", and its "Source" in small type.
- S04 uses the timeline; S05 uses a column chart of D1; S07 is the I/O-clone slide with the side-by-side placeholder.
- **Check:** compare each slide with its overview entry.

### R6. Acts 4 to 5 slides (S11 to S17)
- Same rules as R5. S16 carries the Kotlin Kenya corner callout; S13 to S17 credit contributors by name where the overview does.
- **Check:** compare with the overview.

### R7. Act 6 slides (S18 to S26)
- Same rules as R5. S20 shows cold start -22% (1,335 to 1,047 ms), APK -28%, download -7.4%, with D7 bars; no 35% figure anywhere. S24 is the live-demo cue slide with a numbered checklist reveal. S25 is optional and marked so in the outline.
- **Check:** compare with the overview; `grep -rn "35%" segments composeApp/src` is empty.

### R8. Act 7 slides (S27 to S31)
- Same rules as R5. S28 shows the D5 chart ("the uncomfortable chart"); S30 names contributors; S31 hands over to the bounty.
- **Check:** compare with the overview.

### R9. Deck registration and cleanup
- `SkiPresentationSlides.kt` registers exactly the 31 slides with outline labels, `showFooter` as needed and `transition = ScreenTransition.Fade`; each has a `<label>Notes` constant in `SlidesNotes.kt`; reveal slides expose `const val <SLIDE>_STEPS`.
- The phase 3 slides and notes the outline drops are deleted, with unused imports.
- **Check:** `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes; the label count is 31.

### R10. Library untouched
- **Check:** `git diff --stat e02c712 -- shared/components` is empty.

## Non-Functional Requirements

- **NF1. One number per slide:** a slide that leads with a number shows one large number; the rest is secondary text. Checked by looking at S03, S20, S28.
- **NF2. Notes:** every slide's notes follow `docs/speaker-notes.md` (2 to 4 points, what a number does not measure).
