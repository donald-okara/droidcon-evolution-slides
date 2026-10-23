# Phase 1: Story Outline and Abstract

## Goal

Turn the droidcon Kenya app's real history into a talk: a fact base mined from its git history, a story outline that fits the session, and an abstract. This comes before any slide code so the deck is built against an agreed story.

## What This Phase Delivers

- `docs/talk/history.md`: eras of the app with date ranges, maintainers per era, notable changes and stack shifts, all traced to commits in the app's clone.
- `docs/talk/outline.md`: the story arc, sections with minute budgets that add up to the session length, a split of sections across the 3 presenters, and a slide list (label plus one-line intent per slide).
- `docs/talk/abstract.md`: the talk abstract, filled from `docs/abstract-template.md`.
- `docs/talk/design-inventory.md`: the droidcon app's design language (colors, type, shapes, motion), its assets with what each needs to work in Compose Multiplatform, its signature components, how the design changed over time, and a mapping onto the slides' theme and resource files.

## Out of Scope

- Any slide code, and removing the Ski demo slides (phase 2: scaffold the droidcon sub-package and intro slide, then remove demos and their notes).
- Porting the design language and assets into `:shared:design` and `:shared:resources` (phase 2, driven by the design inventory).
- Presenter names and photos: the user adds these later. Until then docs and slides use `Presenter 1/2/3` placeholders.
- Content slides and detailed notes (phase 3), and rehearsal polish: video, screenshots, app identity, web deploy (phase 4).
- Editing the droidcon app repo; it is read-only from here.

## Success Criteria

- Every era and maintainer claim in `history.md` cites a commit SHA or date range from the clone.
- The outline's minute budgets add up to 45 (`SlidesConstants.SESSION_DURATION`), and every section has a presenter placeholder.
- The abstract is 100 to 150 words and follows the template's sections.
- Every token and asset in the design inventory names its source path in the app, and every asset has a conversion verdict.
- The user has read the outline and abstract and approved them, or asked for changes.

## Related Docs

- `docs/architecture.md` (talk context, clone path, decisions)
- `docs/abstract-template.md`, `docs/speaker-notes.md`
- `context.md` (slide-authoring rules the slide list must respect)
