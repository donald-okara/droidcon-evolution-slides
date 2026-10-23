# Phase 3: Design

### Reveal steps live in DeckNavigator
**Decision:** `SlideConfig` gets a `steps: Int = 0` count and `DeckNavigator` a `currentStep`. Next and previous step through reveals before changing slides, and a `LocalRevealStep` CompositionLocal gives slides the current step.
**Why:** Right, Space and Enter already change slides, and the two windows have separate composition state. One shared step keeps the presenter panel and audience window in sync on desktop, and it can go through `DeckSyncState` on web. The presenter paces reveals with the same keys they already use.
**Tradeoff:** It touches `:core:domain`, which phase 2 tried to leave alone (it did add `showFooter`). `:shared:components` stays unchanged. The step count is declared at registration and has to match the slide's items, so each slide file exposes a `const val <SLIDE>_STEPS` that both the composable and `SkiPresentationSlides.kt` use.

### Going back shows the previous slide fully revealed
**Decision:** Previous at step 0 goes to the previous slide at its last step. Forward entry and table-of-contents jumps start at step 0.
**Why:** It matches how presenters expect "back" to work, and a presenter who goes back to point at something doesn't have to click through the reveals again.
**Tradeoff:** The outgoing slide may briefly show its old step during the fade. That's acceptable.

### History slides are drafted from the fact base
**Decision:** The six history slides are built from `docs/talk/history.md` eras 1 to 4, as a draft Annunziata Kinya can replace.
**Why:** The whole deck has to be reviewable on the evening of 2026-10-23, and her own material hasn't arrived yet.
**Tradeoff:** She may rewrite them. Because her slides are kept in their own file (`HistorySlides.kt`) with their own notes constants, a rewrite stays local.

### Media is a labelled placeholder
**Decision:** Recordings, screenshots and presenter photos appear as outlined boxes labelled with what goes there.
**Why:** The presenters haven't chosen the media yet, and a visible label shows the gap during a dry run.
**Tradeoff:** Layouts may shift when real media replaces the boxes, so a phase 4 pass is needed.

### Lessons follow the outline as written
**Decision:** Ian Nthuli delivers all three lessons, lesson 3 is one line, and notifications is shown as work in progress on its branch.
**Why:** The user chose to use the outline as-is and let the presenters adjust at the review.
**Tradeoff:** The shared-close option and a notifications merge before talk day are left to the presenters. The notes flag both.

### Improvements as animated stat tiles
**Decision:** `launch-perf` shows each improvement as a big-number tile in a grid. The number counts up from 0 and, for a decrease, shrinks progressively as it counts. An arrow animates in to show the direction, and the colour shows the meaning: green for an improvement, red otherwise. The 35% startup-time figure, confirmed by Harun Wangereka (2026-10-23), is one of the tiles.
**Why:** The user asked for it. Big numbers carry from the back of the room, and the motion draws the eye to the result.
**Tradeoff:** Direction and meaning are separate parameters, so each tile states both, and a drop that is good (smaller APK) still reads green. The startup figure has no SHA, so the slide credits Harun instead of showing a commit tag.

### SHAs on screen as small tags
**Decision:** Commit-backed claims show their short SHA as a small monospace tag next to the claim.
**Why:** `docs/architecture.md` requires claims to stay checkable, and the tags let an audience member look a claim up.
**Tradeoff:** They add visual noise. Keeping them small and in `onSurfaceVariant` limits it.
