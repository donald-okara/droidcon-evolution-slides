# Phase 4: Meetup deck rebuild

## Goal

Rebuild the deck as the 30-minute meetup talk in Harun Wangereka's overview (`docs/talk/harun-overview.md`, Part 4, slides S01 to S31), "From I/O clone to our own app: eight years of the droidcon Kenya app", and make it delightful: an animated, hoverable evolution timeline, charts, and a polish pass. This closes the work phase 3 deferred and the phase 3 review comments (more pleasant and delightful slides; a timeline with hover details and labelled stages).

## What This Phase Delivers

- The Gradle project renamed in `settings.gradle.kts` so it matches this repo's purpose (the Ski library modules and package names stay).
- A new talk outline in `docs/talk/outline.md`: 31 slides in seven acts, with labels, one-line intents, reveals and minute budgets that add up to the session length.
- An `EvolutionTimeline` component: labelled stages (2018, 2019, 2020, 2022, 2023, 2025, 2026), hover (or tap) to show a stage's details, and a reveal-driven highlight.
- Simple chart blocks (`BarChart`, `ColumnChart`) and a `CalloutCard` ("Kotlin Kenya corner") in `ke.don.demos.droidcon.common`, with animated entrances.
- Slides S01 to S31 as named composables with detailed presenter notes, replacing the 19 slides from phase 3.
- The startup figure reconciled: the slides use the documented numbers (cold start -22%, 1,335 to 1,047 ms) and the 35% claim is dropped from slides and notes.
- A polish pass: consistent entrances, one large number per slide, source text under claims.

## Out of Scope

- Slides S32 to S53 (bug bounty, feature bash, close): not part of the talk slot. Revisit if the hosts want one deck for the whole day.
- Real screenshots, recordings and photos: labelled placeholders stay until the presenters supply files.
- The app identity rename (`ke.don.ski` package, app name "ski", web title). Only the Gradle `rootProject.name` is renamed.
- The phase 2 cleanup (unused fonts and images, `:segments:introduction`, the gallery).
- Any change to the published library `:shared:components`.
- Tests and lint, per `docs/architecture.md`.
- Deferred: Notes on what is missing from our side to tie it all together (review 1): to be added later.

## Success Criteria

- `./gradlew :desktopApp:run` steps through 31 slides in outline order, in both windows, with reveals in sync.
- The timeline shows seven labelled stages and a hover (desktop and web) shows each stage's details.
- No slide or note says startup is 35% faster; `launch`-style perf slide shows 1,335 to 1,047 ms.
- No `ke.don.demos.droidcon` slide from phase 3 that the new outline drops remains in the code.
- `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes.
- `git diff --stat e02c712 -- shared/components` is empty.

## Related Docs

- Source of the talk: `docs/talk/harun-overview.md` (Part 4 slides, Appendix D chart data, Appendix E assets).
- Earlier facts: `docs/talk/history.md`, `docs/talk/design-inventory.md`.
- Slide and notes conventions: `context.md`, `docs/speaker-notes.md`, `docs/architecture.md` (Decisions).
- Model usage and escalation: `docs/preferences.md`.
