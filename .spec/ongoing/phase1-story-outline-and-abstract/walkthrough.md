# Phase 1: Walkthrough

## Current state

- Done: all 4 tasks. Task 1 (`docs/talk/history.md`, 5 eras plus 8 open questions); Task 2 (`docs/talk/outline.md`, 8 sections, 45 min); Task 3 (`docs/talk/abstract.md`, 135 words); Task 4 (`docs/talk/design-inventory.md`, tokens/assets/components/timeline/mapping, ends with phase 2 decisions).
- Next: `/donatello:phase-review`, then `/donatello:phase-archive`.
- Phase 2 decisions recorded in design-inventory.md section 6: 2026 identity and Montserrat everywhere, cube hero in the header next to the logo, cube as the footer bullet, likely a custom frame, marks cleared, then-vs-now slide, logo as SVG, Ski type sizes kept with Montserrat swapped in. Presenters review this evening.
- User answers folded in: speakers (Donald Isoe, Annunziata Kinya, Ian Nthuli), community rotation model replaces "handovers" in thesis, aliases confirmed, 2 other apps and KMP reversal reason unknown, format includes screen recordings/screenshots.
- Talk restructured into three topic segments (history / about to launch / future = droidconKeKotlin docs/IMPROVEMENT_PLAN.md, framed as an invitation, not a commitment). Level Intermediate confirmed; Donald's bio shortened; same Harun confirmed.
- Speakers mapped: Annunziata Kinya = history (brings her own content), Donald Isoe = about to launch, Ian Nthuli = future. Harun credited by name. adaptive-large-screen, fix/speaker-details, fix/sponsor-logo-contrast are done.
- Open: lessons wording; presenter photos.
- Known issues: Haiku implementer ran out of turns mining git history; for repo-mining tasks, ask for batched git queries or use Sonnet.

## How to verify

- Task 1: `git -C /Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin show --stat <sha>` on three SHAs cited in `docs/talk/history.md` (e.g. c42e0cdb, 6a6845e4, d66ca423) and confirm each matches its claim. `git -C … status` shows no changes.
- Task 2: in `docs/talk/outline.md`, section budgets add up to 45 (3+5+5+6+8+6+6+6), every section has a presenter (all three used), and each reveal is one slide.
- Task 3: strip the title and bold metadata lines from `docs/talk/abstract.md`, then `wc -w` the body: 100 to 150 (currently 135). Every template section is present and no comments remain.

- Task 4: in `docs/talk/design-inventory.md`, check ChaiBlue700 #0055FF, ChaiGreen500 #00FF4F, ChaiNavy700 #26343F against `core/designsystem/.../atoms/Color.kt`; confirm `core/ui/src/main/res/drawable/droidcon_logo.xml` and `core/designsystem/src/main/res/font/montserrat_variable.ttf` exist; every asset row has a verdict; `git -C /Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin status` is clean.

## Changes by task

### Task 1: Mine the droidcon app's history
- `docs/talk/history.md` (new): summary table, stack-snapshot table (Kotlin, AGP, Hilt, Room, Ktor, modules at 9 dates), 5 era sections, 8 open questions; every claim cites a short SHA, guesses marked "(inferred)".
- Eras: bootstrap (2023-02–06); convention plugins and Chai (2023-07–11); KMM experiment (2024-03–06); dormant years with KMP attempt and rollback (2024-11–2025-12); 2026 (module split, AGP 9, rebrand, widget, notifications).
- Author aliases merged (inferred). The catalog's `agp` key lagged `gradleplugin` through 2024–25.
- NOTE (reviewer): end date is 2026-10-23 rather than R1's 2026-10-22, because the clone's last commit is 2026-10-23.
- Escalated to Sonnet (Haiku hit its turn limit without writing the file).

### Task 2: Draft the story outline
- `docs/talk/outline.md` (new): thesis, 8 sections. Eras 3 and 4 merged into "Detours"; era 5 split into "modernise" and "design and features". Presenter load 20/13/12 min. Slide table with Reveal column.
- Lesson wording on the `lessons` slide is a draft for presenters to confirm.
- NOTE (reviewer): thesis says "three maintainer handovers"; not verified against history.md. Confirm before the abstract reuses it.

### Task 3: Write the abstract
- `docs/talk/abstract.md` (new): title "A shared teaching codebase outlived its maintainers"; body is 135 words.
- Handover count is not in history.md (the 2024 handover is inferred, 2026 is an open question), so the abstract gives no number.
- Facts used: the 211-day commit gap (2024-11-13 to 2025-06-12), two multiplatform attempts (KMM 2024, CMP 2025), Kotlin 1.8 to 2.4.
- Level "Intermediate" and Format "slides only" are the implementer's choices and need sign-off.
- NOTE (reviewer): "KMP attempted twice" is supported by combining eras 3 and 4, not stated in history.md as such.

### Task 4: Inventory the droidcon design language
- `docs/talk/design-inventory.md` (new): tokens (palette, light/dark schemes, ChaiColors, cube colors, session accents, typography, shapes/spacing, motion), assets table with verdicts, components, timeline with pre-2026 palette (#000CEB, #FF6E4D, #00E2C3), mapping to slides targets, Decisions for phase 2.
- Findings: Shapes is the M3 default `Shapes()`; Spacing has no scale (BreathingSpace13 is 15 dp). Only `CubePainter.kt` in the cube hero uses android.* APIs; session cards (R/Coil/LocalContext) and ChaiTypography (R.font) are Android-bound.
- Dark logo differs only in black fills turned white; launcher background is #0055FF. Art license not stated (repo Apache-2.0, font OFL).
- Compose Resources SVG support per target is hedged as a phase 2 decision, not verified.
- NOTE (reviewer): timeline adds a966dae1 and d66ca423 beyond the required list; harmless.
- NOTE (reviewer): Kotlin, React and Swift cube marks and team/smiling photos have no stated license; flagged in phase 2 decisions.
- Run on Sonnet from the start, per the known issue on repo-mining tasks.

## Deviations from spec

- R1: history runs to 2026-10-23 (latest commit in the clone), one day past the spec's 2026-10-22.
