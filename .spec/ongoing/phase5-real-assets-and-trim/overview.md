# Phase 5: Real Assets and Trim

## Goal

Replace every `MediaPlaceholder` in the meetup deck with a real asset from `shared/resources/.../drawable`, or remove it from the slide when no asset exists. The S22 cube becomes the live cube hero (the same composable the background draws), not a placeholder. The deck should show no labelled grey boxes when presented.

## What This Phase Delivers

- Six new drawables registered in `Resources.Images` and shown on their slides: Play and repo QR codes (S02), 2026 Home (S23), `settings.gradle.kts` (S09), top of AGENTS.md (S18), 2026 Sessions (S21).
- A shared slide image block for screenshots and QR codes, so slides do not each hand-roll `Image` + clip + border.
- The spinning cube from S01 lifted to a shared composable and reused live on S22; the background cube hides on S22 as it does on S01.
- S03 ("same app", 2018 vs 2026) removed from the deck entirely.
- Every placeholder without an asset removed, with the slide's layout rebalanced and its notes updated.
- `docs/talk/needed-from-us.md` updated: "Include?" filled in for every row.

## Out of Scope

- New assets (2018/2019 screenshots, chai cover, terminal and diff captures, recordings, goldens, widget). If they arrive later, a follow-up phase adds them.
- S32-S53 (deferred from phase 4).
- The phase 4 "Open" items (showFooter on S01/S31, S14 confetti, constant names, `iosName` tidy).
- Android/iOS checks (desktop and web are the targets).

## Success Criteria

- `grep -rn "MediaPlaceholder(" segments/` returns only the definition in `SlideBlocks.kt`, or nothing if the definition is deleted.
- Desktop run: S02, S09, S18, S21, S23 show their images; S22 shows one live spinning cube and no corner cube.
- Slides that lost media still read as finished: no empty half-columns.
- `./gradlew :composeApp:compileKotlinJvm` passes.

## Related Docs

- `docs/talk/needed-from-us.md` (the asset list this phase settles)
- `context.md` (slide authoring rules)
- Phase 4 archive: `.spec/archive/2026-10-23-phase4-meetup-deck-rebuild/`
