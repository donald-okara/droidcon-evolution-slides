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
