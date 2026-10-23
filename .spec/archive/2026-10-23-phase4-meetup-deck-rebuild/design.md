# Phase 4: Design

### The deck becomes the 30-minute meetup talk
**Decision:** The deck is S01 to S31 of Harun's overview (seven acts), not the three-presenter droidcon talk from phases 1 to 3.
**Why:** Phase 3 deferred "rebuild the deck from Harun's overview", and the talk is on Saturday 2026-10-24.
**Tradeoff:** The per-presenter segments (Annunziata, Donald, Ian) go away; era slides credit contributors by name instead, and the "pass the mic" guests speak during Acts 1 to 4.

### Session length follows the overview
**Decision:** `SlidesConstants.SESSION_DURATION` becomes 40 minutes (30 talk plus 10 Q&A); the outline budgets add up to 30 before Q&A.
**Why:** The overview's run of show allots 10:05 to 10:45; `context.md` says to read the duration from the constant.
**Tradeoff:** The timer in the presenter panel changes for every deck built on this repo.

### Documented startup numbers win
**Decision:** Slides use `docs/performance.md` figures (cold start -22%, 1,335 to 1,047 ms, median of 15 runs) and drop the 35% claim.
**Why:** Architecture decision: claims must stay checkable; the 35% had no source command.
**Tradeoff:** A smaller headline number. If Harun confirms 35% with a method, a later change can restore it.

### Timeline built on common Compose
**Decision:** `EvolutionTimeline` uses `hoverable` plus `collectIsHoveredAsState`, with click as the touch fallback, and reveal steps highlight stages in order.
**Why:** Desktop and web are the presenting targets, and the same state driving the reveal keeps both windows in sync.
**Tradeoff:** Hover details appear only in the window where the pointer is; the presenter window and audience window each keep their own hover state.

### Rename only the Gradle project
**Decision:** `rootProject.name` changes from "Ski" to "droidcon-evolution-slides"; module paths, packages and the published artifact are untouched.
**Why:** The user asked to rename the project in `settings.gradle`; the identity rename was excluded.
**Tradeoff:** Build output and IDE names change; nothing the published library depends on does.

### Delight is a standing requirement (2026-10-23)
**Decision:** Every slide task adds a named delight moment built from shared blocks (`popIn`, `Confetti`, count-ups, timeline pulses), instead of leaving it to a final polish task (R11).
**Why:** The phase 3 review asked for more pleasant, entertaining slides, and the user wants it applied at every chance.
**Tradeoff:** More work per slide task and more animation code to keep in sync across both windows; the Task 12 audit catches slides that skipped it.

### Timeline details follow the lit stage, hero cube goes big (2026-10-23)
**Decision:** The timeline shows details for the lit or clicked stage by default and hover only previews; the title slide shows the cube hero large (R3, R12).
**Why:** Phase 4 review 1: hover details flickered, the selected phase needed no hover, and the intro cube was too small.
**Tradeoff:** The details card is always visible on S04, so it takes slide space. Task 4 and Task 5 stay ticked as written; Tasks 13 and 14 revise them.

### Ticket flow gap: no change needed (2026-10-23)
**Decision:** Review 2's ticket flow gap needs no plan change. S23 and S24 step 5 stay as written (R7).
**Why:** The scout surveyed a stale checkout. After `git fetch`, `origin/main` (e890100c) contains #453 and `:feature:ticket`, so the deck's claim holds. Tasks 9 and 10 stay ticked with no revisit.
**Tradeoff:** Some features are still in review. The presenter says so aloud, or teases "download the app and see it at the event", rather than the slides hedging.
