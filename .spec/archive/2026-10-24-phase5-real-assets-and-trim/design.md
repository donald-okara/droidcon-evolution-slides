# Phase 5: Design

### Trim rule: drawables are the source of truth
**Decision:** An asset is in the deck only if its file is in `shared/resources/src/commonMain/composeResources/drawable/`. Every other placeholder is removed, not kept as a labelled box.
**Why:** The user asked to trim anything not in drawable resources; the deck is presented on 2026-11-05 and labelled boxes read as unfinished.
**Tradeoff:** S07, S08, S12, S13, S15, S17 lose their visual and lean on text, charts and notes. Adding an asset later means re-adding a block.

### Asset-to-slide mapping
**Decision:**
- `playstore_droidcon_kotlin.png` → S02 Play listing QR; `github_droidcon_ke_kotlin.png` → S02 repo QR. The APK fallback QR is removed.
- `home_screen_2026_w_agenda.jpeg` → S23 (the "before" state; "during" and "after" are removed).
- `settings_gradle.png` → S09.
- `top_of_agents.png` → S18.
- `sessions_2026.jpeg` → S21, as the phone view of the new look; the Foldable, Tablet and Desktop goldens are removed.
**Why:** These are the only matching files; the two phone screenshots fit the slides whose placeholder names the same screen.
**Tradeoff:** S21 shows a real phone capture rather than a golden, so its label changes from "Golden".

### S03 is cut
**Decision:** S03 (`SameAppSlide`, 2018 vs 2026 with the commit count-up) is removed from the deck, with its notes and helpers.
**Why:** User call at planning review: the 2018 screenshot does not exist and the slide is not worth keeping half-built.
**Tradeoff:** The deck loses its early "same app, eight years" beat and the commit count; S04 follows S02 directly. Slide numbers after S02 in this phase's docs keep their phase 4 IDs.

### Cube hero on S22 is the live composable
**Decision:** S01's private `SpinningCube` moves to `segments/demos/.../droidcon/common/SpinningCube.kt` as a public composable, used by S01 and S22. `Deck.kt` hides the background cube on both slides, by label.
**Why:** The user said the cube hero is the background decorator and to lift it if needed. `CubeHero` already lives in `:shared:design`; the spin-in wrapper is what S01 owns privately.
**Tradeoff:** Two slides now hide the corner cube; the hide check becomes a label set instead of one constant.

### Removed media: no live mirror box on S24
**Decision:** The S24 "phone mirrored with scrcpy" placeholder is removed; the slide keeps its demo steps. The mirror runs in its own scrcpy window during the talk.
**Why:** There is no drawable for it, and a live mirror cannot be embedded in the deck.
**Tradeoff:** The presenter switches windows for the demo; the notes say when.

### Split layout for slides that lost their media (2026-10-24)
**Decision:** S07, S08, S12, S13, S15, S17 and S24 use a shared two-column layout: title and focal number or visual on the left, details on the right (R8). S22 is excluded because the live cube fills its media slot.
**Why:** User call at plan review, with "2023: one repo to keep" (S13) as the example: once the screenshot column is gone, stretching the remaining content reads as filler; a fixed left/right split keeps the slides consistent.
**Tradeoff:** Seven slides are re-laid out rather than just reflowed, so Tasks 4 and 5 grow and Task 4 is split (Task 7). The shared layout lives in `common/SlideBlocks.kt`, which Task 6 also edits.
