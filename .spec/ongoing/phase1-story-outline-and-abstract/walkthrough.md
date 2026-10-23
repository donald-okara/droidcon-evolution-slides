# Phase 1: Walkthrough

## Current state

- Done: Task 1 (`docs/talk/history.md`, the history fact base, 5 eras plus 8 open questions).
- Next: Task 2 (draft outline). Task 4 (design inventory) is independent.
- Open: the history's "Open questions for the user" (e.g. which 2 other apps updated in 2026, why KMP was dropped and rolled back) need answers from the user before the outline is final.
- Known issues: Haiku implementer ran out of turns mining git history; for repo-mining tasks, ask for batched git queries or use Sonnet.

## How to verify

- Task 1: `git -C /Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin show --stat <sha>` on three SHAs cited in `docs/talk/history.md` (e.g. c42e0cdb, 6a6845e4, d66ca423) and confirm each matches its claim. `git -C … status` shows no changes.

## Changes by task

### Task 1: Mine the droidcon app's history
- `docs/talk/history.md` (new): summary table, stack-snapshot table (Kotlin, AGP, Hilt, Room, Ktor, modules at 9 dates), 5 era sections, 8 open questions; every claim cites a short SHA, guesses marked "(inferred)".
- Eras: bootstrap (2023-02–06); convention plugins and Chai (2023-07–11); KMM experiment (2024-03–06); dormant years with KMP attempt and rollback (2024-11–2025-12); 2026 (module split, AGP 9, rebrand, widget, notifications).
- Author aliases merged (inferred). The catalog's `agp` key lagged `gradleplugin` through 2024–25.
- NOTE (reviewer): end date is 2026-10-23 rather than R1's 2026-10-22, because the clone's last commit is 2026-10-23.
- Escalated to Sonnet (Haiku hit its turn limit without writing the file).

## Deviations from spec

- R1: history runs to 2026-10-23 (latest commit in the clone), one day past the spec's 2026-10-22.
