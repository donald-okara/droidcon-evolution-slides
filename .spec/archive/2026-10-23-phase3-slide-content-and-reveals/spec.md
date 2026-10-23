# Phase 3: Specification

## Requirements

### R1. Reveal steps in the navigator
- A slide can declare a number of reveal steps (default 0). Step 0 shows the slide with all revealable items hidden.
- Next (Right, Space, Enter) moves to the next step while the slide has hidden items, and to the next slide only once every item is shown. A slide entered going forward starts at step 0.
- Previous (Left, Backspace) hides the last shown item. At step 0 it goes to the previous slide, which then shows all its items.
- Jumping from the table of contents opens the target slide at step 0.
- Slides with no steps navigate exactly as before.
- **Check:** on `./gradlew :desktopApp:run`, Right on `history-bootstrap` shows Hilt, Room, Ktor, Compose one per press and the fifth press moves to `history-plugins`; Left from `history-plugins` returns to `history-bootstrap` fully revealed; Right and Left on `opening-title` change slides directly.

### R2. Reveals stay in sync across windows
- The presenter panel and the audience window show the same reveal step on desktop.
- On web, the `/?slides` popup follows the main tab's reveal steps as well as its slide index.
- **Check:** on `./gradlew :desktopApp:run`, pressing Right in either window reveals the item in both. On `./gradlew :webApp:wasmJsBrowserDevelopmentRun`, open the slides popup and press Right on a reveal slide in the main tab; the popup reveals the same item.

### R3. Shared slide building blocks
- Under `ke.don.demos.droidcon.common` there is a slide title (with an optional kicker line), a bullet list, a revealable item that fades in when the current step reaches its index, a short-SHA commit tag in the monospace font, and a media placeholder: an outlined box with a label saying what will go there (for example "Screen recording: Glance widget").
- A stat tile: a big number with a unit (for example "35%") that animates from 0 to its value, an up or down arrow that animates in, and a label. The arrow points up for an increase and down for a decrease. The colour shows whether the change is an improvement: green when it is, red (`colorScheme.error`) when it is not. For a decrease, the number also shrinks progressively while it counts, ending smaller in proportion to the drop. Each window runs its own animation, which is acceptable per `context.md` rule 3.
- All of them use `MaterialTheme` colours and typography, so they read in light and dark.
- **Check:** `./gradlew :segments:demos:compileKotlinJvm :segments:demos:compileKotlinWasmJs` passes; the blocks are used by the content slides in R4 to R8.

### R4. Opening slides
- `opening-title` shows the talk title, the app, and the three presenters (Annunziata Kinya, Donald Isoe, Ian Nthuli), each with a photo placeholder.
- `opening-thesis` shows the thesis from the outline and the three-part arc: history, about to launch, future.
- **Check:** look at both slides on `./gradlew :desktopApp:run`; the thesis wording matches `docs/talk/outline.md`.

### R5. History slides
- `history-bootstrap`, `history-plugins`, `history-churn`, `detour-kmm`, `detour-kmp` and `dormant-years` show the content in each slide's outline row, using facts from `docs/talk/history.md` eras 1 to 4, with the SHAs cited there.
- Reveals: `history-bootstrap` 4 (Hilt, Room, Ktor, Compose), `history-plugins` 3 (Hilt, Compose, Kotlin application), `detour-kmp` 3 (drop KMP, CI fixes, five-layer layout back).
- Anything `history.md` marks "(inferred)" stays marked on the slide. Nothing states a reason for the KMP rollback, because none is known.
- **Check:** step through the six slides on desktop; every SHA shown appears in `docs/talk/history.md`.

### R6. About-to-launch slides
- `launch-stack`, `launch-modules`, `launch-perf`, `launch-design`, `launch-features` and `launch-wip` show the content in each slide's outline row, with facts from `docs/talk/history.md` era 5 and the stack snapshots.
- `launch-perf` shows its improvements as big-number stat tiles in a grid: startup time 35% faster (confirmed by Harun Wangereka, 2026-10-23, no SHA), release APK 28% smaller (`5a42a5a7`), and size 7.4% smaller and gated in CI (`1fc20ee5`). Each tile has a large number that counts up from 0 when the slide appears, an arrow that animates in pointing down (each figure is a drop in time or size), a number that shrinks progressively as it counts, and a short label. All three are improvements, so all three tiles are green. The baseline profile is mentioned as the cause of the gains.
- `launch-design` sets the pre-2026 palette (`Pre2026Palette`) next to the 2026 Chai colours as swatches, labelled "then" and "now".
- `launch-features` includes `adaptive-large-screen`, `fix/speaker-details` and `fix/sponsor-logo-contrast` as done, per the outline's open items.
- `launch-wip` shows notifications as in progress on `feat/notifications`, not on main as of 2026-10-23.
- Reveals: `launch-modules` 3 (core, feature, widget and benchmarks), `launch-features` the number of feature items shown, `launch-wip` 6 (channels, reminders, preferences, deep links, then topic subscriptions, reminder actions).
- **Check:** step through the six slides on desktop; the "then" swatches use `Pre2026Palette` values and the "now" swatches use the live scheme.

### R7. Future slides
- `future-plan` presents the improvement plan as a living roadmap of pending work, credits Harun Wangereka by name as its originator, and says the team is not married to it.
- `future-roadmap` shows the ranked items from the outline's "Source of the improvement plan", one per press (5 steps: accessibility pass, ticketing and QR, notifications leftovers, venue map and calendar, AI last).
- `future-invite` shows the five contributor tracks and how to join through a pull request.
- **Check:** step through the three slides on desktop; the items match the outline.

### R8. Close slides
- `lessons` shows the three lessons from "Lessons (draft)" in the outline, one per press, each as a headline plus one line of evidence. Lesson 3 is one line, because `future-invite` already covers the tracks.
- `qa` shows a questions slide with no footer (as registered in phase 2).
- **Check:** step through both slides on desktop.

### R9. Presenter notes
- Every slide has a notes constant in `SlidesNotes.kt` with 2 to 4 spoken points, following `docs/speaker-notes.md`. The points name the presenter's cue, the exact numbers and names, and the SHAs behind claims.
- Every number on screen (the 28% APK drop, the 7.4% size cut, module and commit counts) has a note saying what it does not measure.
- Reveal slides' notes say what each press shows.
- `lessons` notes say Ian Nthuli delivers all three, with the shared-close option as a cue for the presenters to decide. `qa` notes give Annunziata Kinya, the moderator, backup questions.
- **Check:** `grep -n "PLACEHOLDER" composeApp/src/commonMain/kotlin/ke/don/ski/presentation/ui/SlidesNotes.kt` returns nothing; read the notes in the presenter panel on desktop.

### R10. Placeholder scaffolding removed
- `PlaceholderSlide` and the `placeholderNotes` helper no longer exist. Media placeholders (R3) stay.
- **Check:** `grep -rn "PlaceholderSlide\|placeholderNotes" segments composeApp/src` returns nothing.

## Non-Functional Requirements

- **NF1. Common code builds for desktop and web:** all new code is in `commonMain` and compiles for JVM and wasm. Check: `./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :webApp:wasmJsBrowserDistribution` passes.
- **NF2. Library unchanged:** `git diff --stat dd4c27b -- shared/components` is empty. The reveal step lives in `:core:domain` and `:composeApp`.
- **NF3. Readable on stage:** slide text fits the main frame without clipping in both windows and reads in light and dark. Checked by the user on desktop.
- **NF4. Claims stay checkable:** no on-screen claim or note fact comes from anywhere other than `docs/talk/outline.md`, `docs/talk/history.md` or `docs/talk/design-inventory.md`. The one exception is the 35% startup figure, which Harun Wangereka confirmed and which is added to `history.md` era 5 and the outline's `launch-perf` row in this phase. Checked by the reviewer against those files.
