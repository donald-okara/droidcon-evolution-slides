# Phase 1: Specification

## Requirements

### R1. History fact base
- `docs/talk/history.md` splits the app's life (2023-02-11 to 2026-10-22) into eras with start and end dates.
- Each era lists the active maintainers (git author names, with commit counts), the main changes, and stack or architecture shifts (Gradle/AGP/Kotlin versions, module layout, UI toolkit, DI, networking, multiplatform), each citing at least one commit SHA.
- It includes the 2026 period, when the app was updated alongside 2 other apps, as its own era.
- Facts only come from the clone at `/Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin`. Anything inferred is marked "(inferred)".
- **Check:** pick three SHAs from the doc and run `git -C /Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin show --stat <sha>`; each matches its claim.

### R2. Story outline
- `docs/talk/outline.md` states the talk's one-line thesis and a story arc built on the R1 eras.
- Sections each have a minute budget, and the budgets add up to 45 minutes, including Q&A.
- Each section is assigned to `Presenter 1`, `Presenter 2`, or `Presenter 3`. All three present.
- A slide list gives, per slide, a label (as used in `slide(label = ...)`) and one-line intent. Progressive reveals are noted on one slide, never as duplicated slides (`context.md` rule 1).
- **Check:** add up the budgets (45); every section has a presenter; no two consecutive slides differ only by an extra bullet.

### R3. Abstract
- `docs/talk/abstract.md` follows every section of `docs/abstract-template.md`, with the template comments removed.
- The body is 100 to 150 words. The speaker line lists three presenter placeholders.
- **Check:** `wc -w` on the body (title and metadata lines excluded) gives 100 to 150, and every template section is present.

### R4. Design inventory
- `docs/talk/design-inventory.md` lists the current (2026) design tokens from the app's `core/designsystem` module: light and dark color schemes with hex values, `ChaiColors` extras, typography (font, sizes, weights), shapes, motion scheme. Each entry cites its source file path.
- It lists the brand assets (logos, launcher, cube SVGs, `ChaiIcons`, images, fonts) with path, format, size, license where known, and a conversion verdict for Compose Multiplatform: `as-is`, `convert to SVG/ImageVector`, or `skip`.
- It lists the signature components (e.g. cube hero) with paths and whether they are portable as plain Compose.
- It gives a design timeline (palette and theme changes, with commit SHAs and dates), including the pre-2026 palette, for "then vs now" slides.
- It maps each token and asset to its target in the slides repo (`shared/design/.../theme/Color.kt`, `Type.kt`, `Dimens.kt`, `shared/resources/.../composeResources/font|drawable/`, `Resources.kt`), and ends with "Decisions for phase 2" open questions.
- **Check:** spot-check three hex values and two asset paths against the app repo; every asset row has a verdict.

## Non-Functional Requirements

- **NF1. Read-only source:** no file in the droidcon app clone is modified (`git -C /Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin status` shows no new changes).
