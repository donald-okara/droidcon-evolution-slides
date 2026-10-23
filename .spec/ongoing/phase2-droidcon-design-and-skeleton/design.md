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

### Snake frame with logo-and-cube header (2026-10-23)
**Decision:** The main frame goes back to the stock `snake` frame; `DroidconFrame` is deleted. `MainHeader` shows only the droidcon logo and `CubeHero`, dropping the "Ski" title, deck-mode subtitle and bullet rail. The footer keeps `CubeBullet`, and slides hide it with `showFooter = false` (the flag added in Task 6). Replaces "Droidcon frame in `:composeApp`" (R5).
**Why:** Maintainer review 1, 2026-10-23.
**Tradeoff:** The presenter window loses its deck-mode label in the header. The `showFooter` flag in `:core:domain` stays as a small addition to the domain API.

### Cube as background decorator, checkered floor (2026-10-23)
**Decision:** The animated cube leaves the header and footer and becomes the background decorator (bottom-right, 200dp), so its turning faces "loop through" the six marks. The header shows only the logo; the footer bullet is an 8dp neon-green dot. The background is a custom `DroidconBackground` in `:composeApp` (implementing the public `Background` interface) that draws a perspective checkered floor in neon green at alpha 0.06, scrolling toward the horizon. Changes R4's check and R5; adds R8. Tasks 4 and 5 (ticked, R4) need no change, since the cube code is reused as is; Task 11 reworks Task 10 (R5).
**Why:** Maintainer review 2, 2026-10-23. `BackgroundBuilder`'s `Pattern` is sealed and has no perspective support, so a deck-specific `Background` is the only route that leaves the library untouched (NF2).
**Tradeoff:** A continuously animating full-screen background costs some rendering in both windows. `CubeBullet` and `ANDROID_ROBOT` become unused and stay until removals are picked up.

### Checker alpha 0.10 (2026-10-23)
**Decision:** The floor cells use alpha 0.10 in both themes, up from 0.06. Changes R8; Task 13 reworks Task 12.
**Why:** Maintainer review 3, 2026-10-23: the checkers were too faint.
**Tradeoff:** Neon green reads weaker on light surfaces than on dark; a per-theme alpha is deferred.

### Fade transitions and per-theme checker alpha (2026-10-23)
**Decision:** Every slide uses `ScreenTransition.Fade`, set per slide in `skiPresentationSlides()` (adds R9; Task 14). The floor cells use alpha 0.20 in light and 0.25 in dark (changes R8; Task 15 reworks Task 13), which drops the per-theme deferral. Ticked Tasks 12 and 13 need no change beyond Task 15.
**Why:** Maintainer review 4, 2026-10-23.
**Tradeoff:** Setting the transition on each slide repeats it 19 times but keeps `:core:domain` and the library untouched. A stronger floor raises the risk to text legibility, so it is checked on desktop.
