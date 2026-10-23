# Phase 1: Tasks

```
1. Mine history
   └─> 2. Draft outline
          └─> 3. Write abstract
4. Inventory design language (independent)
```

---

### Task 1: Mine the droidcon app's history
**Serves:** R1, NF1
**Files:** docs/talk/history.md (new)

- [x] Read only from `/Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin`. Use `git -C <path> log`, `git -C <path> shortlog -sn --since/--until`, and `git -C <path> log --follow -p` on `gradle/libs.versions.toml`, `settings.gradle.kts`, and `build.gradle.kts`. Also read its `README.md`, `CONTRIBUTING.md`, and `docs/` for context. Never write to that repo.
- [x] Find era boundaries: maintainer handovers (from changes in top authors per quarter), and big stack shifts (module layout in `settings.gradle.kts`, Kotlin/AGP/Compose versions, DI, networking, multiplatform). Mark 2026 (the app updated alongside 2 other apps) as its own era.
- [x] Write `docs/talk/history.md` with: a summary table (era, dates, maintainers, headline change), then one section per era with maintainers and commit counts, main changes, and stack shifts. Cite at least one short SHA per claim. Mark guesses "(inferred)".
- [x] End with an "Open questions for the user" list: commits or handovers whose reason git does not explain.
- [x] Check: run `git -C /Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin show --stat` on three cited SHAs and confirm each matches its claim. `git -C … status` shows no new changes.
- [x] Commit: `docs: add droidcon app history fact base`

### Task 2: Draft the story outline
**Serves:** R2
**Files:** docs/talk/outline.md (new)

- [ ] Read `docs/talk/history.md`, `context.md` (rules 1 and 2), and `docs/architecture.md` ("The talk").
- [ ] Write a one-line thesis, then the arc: an opening, one section per era (or merged eras), lessons, and Q&A.
- [ ] Give each section a minute budget, adding up to 45 including Q&A, and a presenter (`Presenter 1`, `Presenter 2`, `Presenter 3`; all three used). Show the sum at the bottom.
- [ ] Add a slide list table: section, slide label, one-line intent, and a "Reveal" column for slides that disclose points step by step (one slide, not duplicates).
- [ ] Check: budgets add up to 45, every section has a presenter, and no duplicated reveal slides.
- [ ] Commit: `docs: draft talk outline`

### Task 3: Write the abstract
**Serves:** R3
**Files:** docs/talk/abstract.md (new)
**Pattern:** docs/abstract-template.md

- [ ] Fill every section of `docs/abstract-template.md` from `docs/talk/outline.md` (thesis, approach) and `docs/talk/history.md` (facts). Remove the template comments.
- [ ] Title is a claim or question under 10 words. Level, prerequisites, and format (45 min, with Q&A) are filled in. The speaker line lists `Presenter 1`, `Presenter 2`, `Presenter 3`.
- [ ] Check: the body (excluding title and the metadata lines) is 100 to 150 words by `wc -w`, and every template section is present.
- [ ] Commit: `docs: add talk abstract`

### Task 4: Inventory the droidcon design language
**Serves:** R4, NF1
**Files:** docs/talk/design-inventory.md (new)

- [ ] Read only from `/Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin`. Tokens: `core/designsystem/src/main/java/com/droidconke/chai/atoms/Color.kt`, `atoms/ChaiTypography.kt`, `colors/ChaiColorScheme.kt`, `colors/ChaiColors.kt`, `colors/SessionAccents.kt`, `colors/CubeColors.kt`, `utils/Shape.kt`, `utils/Spacing.kt`, `Theme.kt`. Write a tokens section: light and dark scheme tables (role, hex), extras, typography (Montserrat variable, sizes and weights per style), shapes, motion, each with its source path.
- [ ] Assets section, one table row per asset: path, format, size, license, verdict (`as-is` / `convert to SVG/ImageVector` / `skip`). Cover `core/ui/src/main/res/drawable/droidcon_logo.xml` and `droidcon_logo_dark.xml` (Android vector XML, needs conversion), `app/src/main/res/drawable/ic_launcher_*.xml`, `core/designsystem/art/cube/*.svg`, `core/designsystem/src/main/java/com/droidconke/chai/icons/ChaiIcons.kt`, the `core/ui` and `core/designsystem` drawables (webp/png), and `core/designsystem/src/main/res/font/montserrat_variable.ttf` (with its OFL.txt).
- [ ] Components section: the cube hero (`core/designsystem/src/main/java/com/droidconke/chai/hero/`), `components/CText.kt`, `components/CButtons.kt`, session cards. Say for each whether it uses Android-only APIs (`android.*`, `R.drawable`, `painterResource` with Android resources), by grepping its imports.
- [ ] Timeline section from `git -C <path> log --follow` on the theme and color files: at least 740ce113, 68387ddd, 17eb9fa8, 1c9a1176, 87d23cb7, eb7c436e, 5941f20f, with date and one line each, plus the pre-2026 palette hex values.
- [ ] Mapping section: each token or asset group to its slides target (`shared/design/src/commonMain/kotlin/ke/don/design/theme/Color.kt`, `Type.kt`, `Dimens.kt`, `shared/resources/src/commonMain/composeResources/font/`, `.../drawable/`, `shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt`). Note the existing bug: `GOOGLE_SANS_EXTRA_LIGHT` points to `google_sans_regular.ttf`. End with "Decisions for phase 2": adopt the 2026 identity over Ski purple, Montserrat versus Google Sans, the cube hero as a reusable component or a single slide.
- [ ] Check: three hex values and two asset paths match the app repo, every asset row has a verdict, and `git -C /Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin status` shows no new changes.
- [ ] Commit: `docs: add droidcon design inventory`
