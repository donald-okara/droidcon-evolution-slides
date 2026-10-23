# Phase 1: Design

### Talk docs live in docs/talk/
**Decision:** History, outline, and abstract go in `docs/talk/`, next to the existing authoring docs.
**Why:** They are inputs for the slides that follow, and `docs/` already holds the abstract template and notes guide.
**Tradeoff:** `docs/` now mixes framework guides with talk content; the subfolder keeps them apart.

### Three presenters, placeholders until named
**Decision:** The outline and abstract use `Presenter 1/2/3`; the user supplies names and photos later.
**Why:** The user will provide them after this phase; the section split can be planned without them.
**Tradeoff:** The presenter split may change once names are mapped to who knows which era.

### Story from git history, not memory
**Decision:** The eras and maintainers come from the app clone's git log, with SHAs cited.
**Why:** The clone has 713 commits and no tags. Citing commits keeps the talk's claims checkable.
**Tradeoff:** Commits without context (no PR text) may need the user to explain them; those are marked "(inferred)".

### Design port: inventory in phase 1, port in phase 2 (2026-10-23)
**Decision:** Phase 1 adds a design inventory doc (R4). Porting the theme and assets into `:shared:design` and `:shared:resources` happens in phase 2, before content slides.
**Why:** Phase 1 stays docs-only. The inventory gives phase 2 concrete paths and conversion verdicts, and the design timeline feeds the talk itself.
**Tradeoff:** The deck keeps the purple Ski theme until phase 2. Theme changes live in `:shared:design`, which the published `ski` library does not depend on, so the port won't leak into it.
