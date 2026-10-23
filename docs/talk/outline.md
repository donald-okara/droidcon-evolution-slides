# Talk outline: droidcon Kenya Android app, 2023 to 2026

**Thesis:** The droidcon Kenya app has outlived years of contributors joining through pull requests and stepping back when busy, and two multiplatform detours, because it stays a shared teaching codebase. The 2026 update shows what that buys, and the improvement plan is an invitation to make the app your own.

**Speakers:** Annunziata Kinya (history; brings her own content, so the history rows below are a placeholder fact base from `history.md`), Donald Isoe (current: about to be launched), Ian Nthuli (the future).

**Format:** 45 minutes including Q&A, three presenters, one topic segment each. Slides, with screen recordings and screenshots on some slides. Facts come from `docs/talk/history.md` and the app repo; anything marked (inferred) there stays marked on stage.

## Arc

Three topic segments, history to now to future:

1. **Opening:** what the app is and the thesis (Annunziata Kinya).
2. **History (Annunziata Kinya):** the eras so far, compressed: the 2023 bootstrap and convention plugins, then the KMM and KMP detours and the dormant years.
3. **About to be launched (Donald Isoe):** what is done in the 2026 update, and what is still work in progress.
4. **The future (Ian Nthuli):** the improvement plan. It was Harun Wangereka's idea, the team is not married to it, and everyone is encouraged to make the app their own. Framed as an invitation to contribute, matching the community rotation model.
5. **Lessons and close (Ian Nthuli).**
6. **Q&A (all, moderated by Annunziata Kinya).**

## Sections and budgets

| # | Section | Segment | Eras | Minutes | Presenter |
|---|---------|---------|------|---------|-----------|
| 1 | Opening | History | none | 2 | Annunziata Kinya |
| 2 | 2023: bootstrap, plugins, Chai | History | 1, 2 | 5 | Annunziata Kinya |
| 3 | Detours: KMM, KMP, and the dormant years | History | 3, 4 | 5 | Annunziata Kinya |
| 4 | 2026 update: done | About to be launched | 5 | 12 | Donald Isoe |
| 5 | 2026 update: work in progress | About to be launched | 5 | 3 | Donald Isoe |
| 6 | The improvement plan: an invitation | The future | none | 10 | Ian Nthuli |
| 7 | Lessons and close | The future | none | 3 | Ian Nthuli |
| 8 | Q&A | all | none | 5 | all, Annunziata Kinya moderates |
| | **Total** | | | **45** | |

Sum check: 2 + 5 + 5 + 12 + 3 + 10 + 3 + 5 = 45. Annunziata Kinya has 12 minutes, Donald Isoe has 15, Ian Nthuli has 13, and the 5-minute Q&A is shared.

## Source of the improvement plan

`docs/IMPROVEMENT_PLAN.md` in the app repo (added in `62f45e2a`, "Docs: add improvement plan, AGENTS.md, and CLAUDE.md"; last pruned 2026-10-12, with finished items deleted as they land). It is a living roadmap of pending work only. Main items, by its section 16:

- Accessibility listening pass: TalkBack, Switch Access and keyboard (section 14). Next up, needs a person.
- Ticketing and QR (section 7), waiting on a backend question (section 17.2).
- Notifications leftovers: topic subscriptions and reminder actions (sections 8.4, 8.5).
- Other surfaces: session notes (5.5), calendar export (11.4a), venue map (11.6).
- Intelligent (AI) experiences behind default-off flags (section 6), deliberately last.
- Ship it: perf and size pass, E2E tests in CI, Play Store presence, staged rollout (sections 9, 10.3, 13).
- Foundations and developer experience: the event slug into Remote Config so the 2027 app is a config edit (3.6), CI and contributor tooling (15).
- Parallel tracks for a small contributor pool: Foundations, Design system, Adaptive, AI, Conference ops.
- Deliberately deferred: job board, code challenge, booking, connections, each needing a backend or a named content owner.

## Slide list

Labels match `slide(label = ...)`. The Reveal column marks slides that disclose points step by step, using state inside a single slide (`context.md` rule 1). No slide is duplicated to show a bullet. Some slides use screen recordings or screenshots; which ones is to be chosen with the presenters.

| Section | Slide label | One-line intent | Reveal |
|---------|-------------|-----------------|--------|
| 1 | `opening-title` | Talk title, the app, and the three presenters | none |
| 1 | `opening-thesis` | Thesis on screen and the arc: history, about to launch, future | none |
| 2 | `history-bootstrap` | 2023 layered modules (app, chai, data, domain, presentation), Chai colours (`740ce113`) | Stack items appear one per click: Hilt, Room, Ktor, Compose |
| 2 | `history-plugins` | Convention plugins replace per-module build setup (`6a6845e4`, `996ca6fe`) | Three plugin bullets appear one per click: Hilt, Compose, Kotlin application |
| 2 | `history-churn` | Contributor rotation, offline-first feed (`91322d29`), and the silence after 2023-11-09 | none |
| 3 | `detour-kmm` | 2024 KMM experiment in parallel with the Android modules (`3433931f`, `26f6a3d0`), gone within a quarter | none |
| 3 | `detour-kmp` | 2025 Compose Multiplatform attempt (`cdd713bd`) and its rollback (`c42e0cdb`, `26a9e9cf`) | Rollback steps appear one per click: drop KMP, CI fixes, five-layer layout back |
| 3 | `dormant-years` | Droidcon 2024 work (`c02c6652`) and the Navigation 3 move (`8faf624f`) | none |
| 4 | `launch-stack` | Stack jump from Kotlin 2.1.21 and AGP 8.10.1 to Kotlin 2.4.21 and AGP 9.3.1 | none |
| 4 | `launch-modules` | Module split: 17 modules and `:presentation` removed (`ac81b8ac`), then 21 modules | Module groups appear one per click: core, feature, widget and benchmarks |
| 4 | `launch-perf` | Baseline profile, release APK down 28% (`5a42a5a7`), size gate in CI (`1fc20ee5`) | none |
| 4 | `launch-design` | Chai on Material 3 Expressive (`87d23cb7`) and the 2026 identity (`eb7c436e`) | none |
| 4 | `launch-features` | Done features on main: cube hero (`5941f20f`) and Glance widget (`4492346b`) | Feature items appear one per click: cube hero, widget |
| 5 | `launch-wip` | In progress: notifications module on branch `feat/notifications` (not yet on main at 2026-10-23) | Items appear one per click: notifications done so far (channels, reminders, preferences, deep links), then what remains (topic subscriptions, reminder actions) |
| 6 | `future-plan` | The improvement plan: what it is, whose idea it was (Harun Wangereka), and that it is a living roadmap, not a commitment | none |
| 6 | `future-roadmap` | The ranked items: accessibility pass, ticketing and QR, notifications leftovers, venue map and calendar, AI last | Roadmap stages appear one per click |
| 6 | `future-invite` | Make the app your own: tracks for a small contributor pool, how to join through a pull request, the rotation model | none |
| 7 | `lessons` | Three lessons that carry across rotations (docs for the next contributor, CI gates, recorded reversals) | Lessons appear one per click |
| 8 | `qa` | Questions, with the open questions from `docs/talk/history.md` as backup | none |

## Open items

- Wording on `future-plan` ("not married to it", "make it your own") needs the presenters' sign-off. Harun Wangereka is credited by name (confirmed by the user).
- `adaptive-large-screen`, `fix/speaker-details` and `fix/sponsor-logo-contrast` are done (confirmed by the user); they belong on `launch-done`. Re-check whether notifications has merged by talk day.
- Presenter photos still come from the user. Names are known.
- Which slides use screen recordings or screenshots is to be chosen with the presenters.
- Lesson wording on `lessons` is a draft and needs the presenters' sign-off.
