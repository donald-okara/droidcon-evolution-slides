# Architecture

This repo is the slide deck for a talk on the evolution of the droidcon Kenya app, built on Ski, the Compose Multiplatform presentation framework. The Ski framework source (including the published `:shared:components` library) stays in the repo; the talk is the deck it renders.

Already documented elsewhere, so not repeated here:
- Build commands, module graph, runtime architecture (deck, frames, navigation, two windows): see `CLAUDE.md`.
- Slide-authoring rules (DSL, `SlideConfig`, reveals, code snippets, no new modules): see `context.md`.
- Speaker notes conventions: `docs/speaker-notes.md`. Video in slides: `docs/video.md`. Abstract shape: `docs/abstract-template.md`.
- Framework overview and toolkit: `README.md`.

## The talk

- Subject: the droidcon Kenya Android app from 2023 to 2026. It has had several maintainers, rotated through the community; this year it has been updated alongside 2 other apps.
- Source of truth for the story: the app's local clone at `/Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin` (2023-02-11 to 2026-10-23, no git tags). Read its history with `git -C <path> log`; never edit that repo from here.
- Presenters and segments: Annunziata Kinya (history), Donald Isoe (about to launch), Ian Nthuli (future). Photos still come from the user.
- Talk planning docs (history, outline, abstract, design inventory) live in `docs/talk/`.
- Session length: `SlidesConstants.SESSION_DURATION` (45 min). Time budgets in the outline must add up to it.

## Decisions

- The droidcon talk replaces the 18 Ski template demo slides and their notes; demos are removed once the real slides exist.
- New slide composables go in a sub-package of `:segments:demos` (per `context.md`), registered in `composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SkiPresentationSlides.kt`, with notes as named constants in `SlidesNotes.kt` next to it.
- No DI and no state management library: slide state is local Compose state; shared context goes through `CompositionLocal`s.
- No tests and no lint. Verification is running the deck (`./gradlew :desktopApp:run`) and looking at the slide in both windows.
- Talk claims come from the app's git history with short SHAs cited, and guesses are marked "(inferred)", because the clone has no tags or PR text and the claims must stay checkable.
- The droidcon theme and assets go in `:shared:design` and `:shared:resources`, not `:shared:components`, so the published `ski` library keeps its own look.

## Known stale facts

- `docs/speaker-notes.md` mentions a "Remember to say hallo" intro note that is not in the code.
- `README.md` links `ski-slides.vercel.app`; the Questions slide shows `ski-gallery.vercel.app`.
- App identity (`ke.don.ski`, app name "ski", web title) is still Ski's; renaming is a later-phase choice.
