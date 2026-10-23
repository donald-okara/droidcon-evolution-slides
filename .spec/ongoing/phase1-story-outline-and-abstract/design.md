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
