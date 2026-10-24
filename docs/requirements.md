# Requirements

Describes the current state of the talk material; updated as phases ship.

## Talk docs

- `docs/talk/history.md` splits the app's life (2023-02-11 to 2026-10-23) into eras with dates. Each era lists its maintainers with commit counts, its main changes and its stack shifts, and every claim cites a commit SHA. The 2026 period, when the app was updated alongside 2 other apps, is its own era. Anything inferred is marked "(inferred)".
- `docs/talk/outline.md` lists S01-S31 in seven acts with label, intent, reveal steps and minutes; act budgets add up to 30 (plus 10 for Q&A). Progressive reveals happen within one slide, never as duplicated slides.
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

- The deck registers 31 slides in outline order with matching labels, `ScreenTransition.Fade`, and a `<label>Notes` constant each; reveal slides expose `const val <SLIDE>_STEPS`.
- No Ski demo slide, demo notes or demo-only composable remains; `VideoPlayer*` stays.
- `PlaceholderSlide` and the placeholder notes helper no longer exist.

## Slide content

- Each slide shows its overview "On screen" text, a visual (chart, timeline or labelled placeholder), notes from "Say" and a small source line (`SourceLine`).
- S20 leads with cold start -22%; APK -28% and download -7.4% are text; the 35% figure appears nowhere.
- S10 and S30 credit contributors by name; S16 carries the Kotlin Kenya corner callout.
- Recordings, screenshots and presenter photos are labelled placeholders until the presenters choose them (see `docs/talk/needed-from-us.md`).
- Shared blocks (`SlideTitle`, `BulletList`, `CommitTag`, `MediaPlaceholder`, `RevealItem`, `RevealBulletList`, `StatTile`, `SourceLine`) are in `ke.don.demos.droidcon.common` and use `MaterialTheme` colours and typography.

## Reveals

- A slide can declare reveal steps (default 0). Right, Space and Enter show the next hidden item before moving on; Left hides the last one. Going back into a slide shows it fully revealed; table-of-contents jumps open at step 0.
- S04 has 7 steps, S29 has 4.
- The presenter and audience windows show the same step on desktop, and the web `/?slides` popup follows the step as well as the slide index.

## Timeline, charts and delight

- `EvolutionTimeline` shows 2018-2026 with a 2021 gap marker; hover or click shows details without flicker, and the lit or clicked stage shows its details with no hover.
- `ColumnChart`, `BarChart` and `CalloutCard` animate in with theme colours.
- Every slide has a delight moment that never hides content or desynchronises the two windows.
- S01 shows the cube hero large; the corner background cube is hidden on S01 only.

## Speaker notes

- Every slide has a notes constant with spoken points following `docs/speaker-notes.md`: numbers, names and SHAs, a "what this number does not measure" line for each on-screen number, and what each press shows on reveal slides.

## Library

- `:shared:components` (the published `ski` library) is not changed by the talk work.
