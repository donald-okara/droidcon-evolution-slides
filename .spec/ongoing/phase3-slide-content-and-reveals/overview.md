# Phase 3: Slide content, reveals and notes

## Goal

Turn the 19 placeholder slides into the finished talk, with real on-screen content, arrow-key reveals that stay in sync across both windows, and detailed presenter notes, so the presenters can review the whole deck on the evening of 2026-10-23.

## What This Phase Delivers

- Reveal steps owned by the deck navigator. Right, Space and Enter show the next hidden item on a slide before moving on, and Left steps back. The presenter and audience windows (desktop and web) show the same step.
- Shared slide building blocks under `ke.don.demos.droidcon.common`: a slide title, bullet lists, revealable items, a short-SHA commit tag, and a labelled media placeholder.
- Real content for all 19 slides, in outline order: opening, history (drafted from `docs/talk/history.md` as a stand-in for Annunziata Kinya's own material), about to launch (including the "then vs now" palette on `launch-design`), the future, lessons, and Q&A.
- Reveals on the 9 slides the outline marks, with the step counts given there.
- Detailed presenter notes for every slide, written to `docs/speaker-notes.md`.
- Labelled placeholders wherever a screen recording, screenshot or presenter photo will go.
- `PlaceholderSlide` and the placeholder notes helper removed.

## Out of Scope

- Real recordings, screenshots and presenter photos. The placeholders are swapped for files once the presenters choose (phase 4).
- Annunziata Kinya's own history content. The history slides are a draft she can replace.
- Rehearsal polish, timing passes, the app identity rename (`ke.don.ski`, "ski") (phase 4).
- The cleanup deferred in phase 2 (unused fonts, images, `VideoPlayer*`, `:segments:introduction`, the gallery).
- Any change to the published library `:shared:components`.
- Tests and lint, per `docs/architecture.md`.
- Deferred: Rebuild the deck from Harun Wangereka's 2018-to-2026 meetup overview (new slide list, the "I/O clone to its own app" thread, an animated evolution timeline, a polish pass, and the 22% versus 35% startup figure): moved to phase 4 on 2026-10-23.

## Success Criteria

- On `./gradlew :desktopApp:run`, stepping through with Right shows 19 finished slides in outline order. Reveal slides disclose their items one per press, in both windows at once, and Left steps back through them.
- Every slide's notes in the presenter panel are real spoken notes. No `PLACEHOLDER` text remains.
- Every claim on screen matches `docs/talk/outline.md` or `docs/talk/history.md`, and commit-backed claims show their short SHA.
- `./gradlew :webApp:wasmJsBrowserDistribution` succeeds, and in the web build the `/?slides` popup follows reveal steps.
- `git diff --stat dd4c27b -- shared/components` is empty.

## Related Docs

- Slide list, labels, reveals, presenters, lessons: `docs/talk/outline.md`.
- Facts and SHAs: `docs/talk/history.md`; pre-2026 palette: `docs/talk/design-inventory.md` section 4.
- Notes conventions: `docs/speaker-notes.md`. Slide-authoring rules: `context.md`.
- Where slides and notes go, and the no-tests decision: `docs/architecture.md` (Decisions).
- Model usage and escalation: `docs/preferences.md`.
