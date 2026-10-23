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

## Deck look

- `AppTheme` uses the droidcon 2026 Chai light and dark colour schemes; no Ski purple remains. `ChaiColors` extras are provided through `LocalChaiColors`; cube colours and the pre-2026 palette are named constants in `:shared:design`.
- Every non-code text style uses Montserrat; Ski's sizes, weights, line heights and letter spacing are unchanged.
- Light and dark droidcon logos are SVG resources in `Resources.Images`.
- The cube hero renders and animates from common code; a static bullet-size cube is also available.
- The main frame is the snake frame in both windows. Its header shows only the droidcon logo (per theme), and its footer bullet is a small neon-green dot. Slides registered with `showFooter = false` show no footer.
- The background is a neon-green checkered floor in perspective, scrolling toward the horizon, at alpha 0.20 in light and 0.25 in dark, with the animated cube at bottom-right. It is built in `:composeApp` on the public `Background` interface.
- Every slide fades in and out (`ScreenTransition.Fade`) in both directions and both windows.

## Slide skeleton

- The deck registers the outline's 19 slides in order, with labels matching the outline exactly. Each body is a named composable under `ke.don.demos.droidcon`, and each has a notes constant in `SlidesNotes.kt`.
- No Ski demo slide, demo notes or demo-only composable remains; `VideoPlayer*` stays.
- `PlaceholderSlide` and the placeholder notes helper no longer exist.

## Slide content

- All 19 slides show the content in the outline's rows, drafted from `docs/talk/history.md`, `outline.md` and `design-inventory.md`. Commit-backed claims show their short SHA, and "(inferred)" stays marked. The history slides are a draft for Annunziata Kinya to replace.
- `launch-perf` shows three stat tiles: startup 35% faster (Harun Wangereka, no SHA), release APK 28% smaller (`5a42a5a7`) and download 7.4% smaller (`1fc20ee5`). Each counts up from 0, shrinks while it counts, shows a down arrow and is green.
- `launch-design` sets the pre-2026 palette against the 2026 Chai colours as "then" and "now" swatches. `launch-wip` shows notifications as in progress on its branch.
- `lessons` has three lessons delivered by Ian Nthuli; `qa` has no footer.
- Recordings, screenshots and presenter photos are labelled placeholders until the presenters choose them.
- Shared blocks (`SlideTitle`, `BulletList`, `CommitTag`, `MediaPlaceholder`, `RevealItem`, `RevealBulletList`, `StatTile`) are in `ke.don.demos.droidcon.common` and use `MaterialTheme` colours and typography.

## Reveals

- A slide can declare reveal steps (default 0). Right, Space and Enter show the next hidden item before moving on; Left hides the last one. Going back into a slide shows it fully revealed; table-of-contents jumps open at step 0.
- Reveal slides and their steps: `history-bootstrap` 4, `history-plugins` 3, `detour-kmp` 3, `launch-modules` 3, `launch-features` 5, `launch-wip` 6, `future-roadmap` 5, `lessons` 3.
- The presenter and audience windows show the same step on desktop, and the web `/?slides` popup follows the step as well as the slide index.

## Speaker notes

- Every slide has a notes constant with spoken points following `docs/speaker-notes.md`: numbers, names and SHAs, a "what this number does not measure" line for each on-screen number, and what each press shows on reveal slides.

## Library

- `:shared:components` (the published `ski` library) is not changed by the talk work.
