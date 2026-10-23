# Phase 2: Design

### Scope: design port plus skeleton
**Decision:** This phase ports the design and replaces the demos with 19 placeholder slides; content and notes wait for phase 3.
**Why:** Chosen by the maintainer on 2026-10-23 so the deck has its final shape and look before the presenters' evening review.
**Tradeoff:** The deck shows placeholders until phase 3 lands.

### 2026 identity everywhere
**Decision:** Adopt the 2026 Chai light/dark schemes (blue, neon green, black) as the deck theme, replacing Ski purple. The pre-2026 palette is kept as named constants for the "then vs now" slide.
**Why:** design-inventory §6.1 and §6.7.
**Tradeoff:** The deck no longer looks like Ski; the published `ski` library keeps its own look because the change lives in `:shared:design`.

### Montserrat with Ski's type ramp
**Decision:** Every text style uses Montserrat (variable font, weights via `FontVariation.weight`); sizes, weights and line heights in `Type.kt` stay as they are. Code snippets keep Roboto Mono (confirmed 2026-10-23).
**Why:** design-inventory §6.2: Ski's ramp is tuned for slides, the app's is tuned for phones. Code needs a monospace font.
**Tradeoff:** Montserrat ships with its OFL.txt beside it.

### Logo as SVG
**Decision:** Convert `droidcon_logo.xml` and `droidcon_logo_dark.xml` to SVG in `shared/resources/.../drawable/`.
**Why:** design-inventory §6.8.
**Tradeoff:** Compose Resources does not render SVG on Android; accepted on 2026-10-23 because mobile is not a priority (desktop and web are the presenting targets). If the Android build matters later, add a vector XML copy.

### Cube in `:shared:design`
**Decision:** The cube hero (state, motion, projection, marks, art, painter) lives in `ke.don.design.cube`, with `CubePainter` rewritten on common `Matrix`/`drawIntoCanvas`/`Path`.
**Why:** It is design-system code used by both the frame and slides (`launch-features`), and its marks are path strings in Kotlin, so it needs no resource files.
**Tradeoff:** None beyond the painter rewrite (design-inventory §3).

### Droidcon frame in `:composeApp`
**Decision:** A new `DroidconFrame` (`SkiFrame`) in `composeApp/.../ski/presentation/frame/`, with the header (logo + cube) and footer (cube bullet) supplied by `MainHeader`/`MainFooter`.
**Why:** design-inventory §6.3 to §6.5; the frame is deck-specific, so it stays out of the published `:shared:components`.
**Tradeoff:** The stock `snake`/`basic` frames stay available but unused by the main frame.

### Demo removal keeps the video player
**Decision:** Delete the demo composables in `segments/demos` except `VideoPlayer*` (all source sets), which phase 3 may use for recordings.
**Why:** `docs/video.md` documents video in slides, and recordings are still an open choice.
**Tradeoff:** One unused file set stays until phase 3 decides.
