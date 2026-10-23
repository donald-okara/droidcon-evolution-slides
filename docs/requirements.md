# Requirements

Describes the current state of the talk material; updated as phases ship.

## Talk docs

- `docs/talk/history.md` splits the app's life (2023-02-11 to 2026-10-23) into eras with dates. Each era lists its maintainers with commit counts, its main changes and its stack shifts, and every claim cites a commit SHA. The 2026 period, when the app was updated alongside 2 other apps, is its own era. Anything inferred is marked "(inferred)".
- `docs/talk/outline.md` gives a one-line thesis and a story arc in three segments (history, about to launch, future). Section budgets add up to 45 minutes including Q&A, and every section names its presenter.
- The outline's slide list gives a `slide(label = ...)` label and a one-line intent for each slide. Progressive reveals happen within one slide, never as duplicated slides.
- `docs/talk/abstract.md` follows every section of `docs/abstract-template.md` with no template comments left in. The body is 100 to 150 words, and the speaker line names all three presenters.
- `docs/talk/design-inventory.md` lists the app's 2026 design tokens, brand assets (each with a Compose Multiplatform conversion verdict), signature components with notes on portability, a design timeline that includes the pre-2026 palette, and a mapping onto the slides' theme and resource files. Every entry cites its source path in the app.

## Source repo

- The droidcon app clone is read-only from this repo. No task writes to it.
