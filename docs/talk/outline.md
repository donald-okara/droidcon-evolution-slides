# Talk outline: droidcon Kenya Android app, 2023 to 2026

**Thesis:** The droidcon Kenya app outlived years of contributors joining through pull requests and stepping back when busy, plus two multiplatform detours, by staying a shared teaching codebase. Its 2026 rebuild shows what that buys.

**Speakers:** Donald Isoe, Annunziata Kinya, and Ian Nthuli. Which presenter (1, 2 or 3) maps to which name, and which era each one covers, is TBD. The era mapping is not decided, so the tables below keep the Presenter 1/2/3 placeholders.

**Format:** 45 minutes including Q&A, three presenters. Slides, with screen recordings and screenshots on some slides. Facts come from `docs/talk/history.md`; anything marked (inferred) there stays marked on stage.

## Arc

1. **Opening:** what the app is, why its history matters, and the thesis.
2. **Era 1, community bootstrap (2023):** the first layered build and Chai colours.
3. **Era 2, convention plugins and Chai (2023):** build-logic, offline-first data, and the first silence.
4. **Detours, KMM and KMP (2024 to 2025):** the two multiplatform attempts and the Navigation 3 move, merged from eras 3 and 4.
5. **Era 5, modernise and modularise (2026):** stack jump, module split, and performance gates.
6. **Era 5, design and features (2026):** the 2026 rebrand, the cube hero, and the widget.
7. **Lessons:** what carries across rotations of contributors.
8. **Q&A.**

## Sections and budgets

| # | Section | Eras | Minutes | Presenter |
|---|---------|------|---------|-----------|
| 1 | Opening | none | 3 | Presenter 1 |
| 2 | Community bootstrap | 1 | 5 | Presenter 1 |
| 3 | Convention plugins and Chai | 2 | 5 | Presenter 2 |
| 4 | Detours: KMM, KMP, and the dormant years | 3, 4 | 6 | Presenter 3 |
| 5 | Modernise and modularise | 5 (Aug to Sep 2026) | 8 | Presenter 2 |
| 6 | Design and features | 5 (Oct 2026) | 6 | Presenter 3 |
| 7 | Lessons | none | 6 | Presenter 1 |
| 8 | Q&A | none | 6 | Presenter 1 |
| | **Total** | | **45** | |

Sum check: 3 + 5 + 5 + 6 + 8 + 6 + 6 + 6 = 45. Presenter 1 has 20 minutes, Presenter 2 has 13, Presenter 3 has 12. All three present.

## Slide list

Labels match `slide(label = ...)`. The Reveal column marks the one slide that discloses points step by step, using state inside a single slide (`context.md` rule 1). No slide is duplicated to show a bullet. Some slides use screen recordings or screenshots instead of static content; which slides do so is to be chosen with the presenters.

| Section | Slide label | One-line intent | Reveal |
|---------|-------------|-----------------|--------|
| 1 | `opening-title` | Talk title, the app, and the three presenters | none |
| 1 | `opening-thesis` | Put the thesis on screen and preview the five eras | none |
| 2 | `era1-setup` | 2023 layered modules (app, chai, data, domain, presentation) with Hilt, Room, Ktor, Compose | Stack items appear one per click: Hilt, Room, Ktor, Compose |
| 2 | `era1-chai` | Colour scheme moves to Chai, and screens adopt Chai buttons and theme (`740ce113`, `ab733f32`) | none |
| 3 | `era2-plugins` | Convention plugins replace per-module build setup (`6a6845e4`, `996ca6fe`) | Three plugin bullets appear one per click: Hilt, Compose, Kotlin application |
| 3 | `era2-churn` | Contributor rotation, offline-first feed (`91322d29`), and the silence after 2023-11-09 | none |
| 4 | `detour-kmm` | 2024 KMM experiment in parallel with the Android modules (`3433931f`, `26f6a3d0`), gone within a quarter | none |
| 4 | `detour-kmp` | 2025 Compose Multiplatform attempt (`cdd713bd`) and its rollback (`c42e0cdb`, `26a9e9cf`) | Rollback steps appear one per click: drop KMP, CI fixes, five-layer layout back |
| 4 | `dormant-years` | Droidcon 2024 work (`c02c6652`) and the Navigation 3 move (`8faf624f`) | none |
| 5 | `era5-stack` | Stack jump from Kotlin 2.1.21 and AGP 8.10.1 to Kotlin 2.4.21 and AGP 9.3.1 | none |
| 5 | `era5-modules` | Module split: 17 modules and `:presentation` removed (`ac81b8ac`), then 21 modules | Module groups appear one per click: core, feature, widget and benchmarks |
| 5 | `era5-perf` | Baseline profile, release APK down 28% (`5a42a5a7`), and the size gate in CI (`1fc20ee5`) | none |
| 6 | `era5-rebrand` | Chai on Material 3 Expressive (`87d23cb7`) and the 2026 identity (`eb7c436e`) | none |
| 6 | `era5-features` | Cube hero (`5941f20f`), Glance widget (`4492346b`), and the notifications module | Feature items appear one per click: cube hero, widget, notifications |
| 7 | `lessons` | Three lessons that carry across rotations (docs for the next contributor, CI gates, recorded reversals) | Lessons appear one per click |
| 8 | `qa` | Questions, with the open questions from `docs/talk/history.md` as backup | none |

## Open items

- Speaker-to-presenter mapping (Presenter 1, 2, 3 to Donald Isoe, Annunziata Kinya, Ian Nthuli) and the era mapping are TBD.
- Presenter photos still come from the user. Names are known.
- Which slides use screen recordings or screenshots is to be chosen with the presenters.
- Lesson wording on `lessons` is a draft and needs the presenters' sign-off.
