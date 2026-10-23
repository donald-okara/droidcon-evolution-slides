# droidcon Kenya Android app: history fact base

Source: `/Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin` (read-only), 716 commits, 2023-02-11 to 2026-10-23. SHAs are short (8 chars). Commit counts are all commits in the window; bots (github-actions, dependabot) are noted separately. Anything marked "(inferred)" is a guess from git, not stated by it.

Aliases (confirmed by the user): `donald` and `donald-okara` are one person; `chepsi`, `chepsiror` and Evans Chepsiror are one person.

Maintainership model (confirmed by the user): a community app. Whoever is interested starts opening PRs and gets absorbed as a maintainer. Most contributors are employed and busy, so they step back without fault and others take over. There are no formal handovers.

## Summary

| # | Era | Dates | Maintainers (top, commits) | Headline change |
|---|-----|-------|----------------------------|-----------------|
| 1 | Community bootstrap | 2023-02 to 2023-06 | Evans Chepsiror (`chepsi`) 17, Waitara 9, Kenneth Mathari 8, Harun Wangereka 8 | Layered `:app :chai :data :domain :presentation` set up, Hilt, Room, Ktor, Compose (`f3b81067`) |
| 2 | Convention plugins and Chai | 2023-07 to 2023-11 | Evans Chepsiror (`chepsi`) 41, yveskalume 10, robert-nganga 10, Waitara 10, Hannah Olukoye 9 | Build-logic convention plugins, offline-first data, Chai design system (`6a6845e4`, `91322d29`, `17eb9fa8`) |
| 3 | KMM experiment | 2024-03 to 2024-06 | Raynafs 21, theophiluskibet 17 | Kotlin 2.0 and `:shared:*` multiplatform modules (`3433931f`, `26f6a3d0`) |
| 4 | Dormant, Droidcon 2024, KMP attempt | 2024-11 to 2025-12 | Michael Ndiritu 39, Evans Chepsiror (`chepsiror`) 8 | Compose Multiplatform added (`cdd713bd`), then dropped (`c42e0cdb`, `c29b4d01`); Navigation 3 (`8faf624f`) |
| 5 | 2026 | 2026-01 to 2026-10 | donald-okara / donald 144, Harun Wangereka 60, mutukuian 7, Joel Kanyi 5 | Modernise and modularise: AGP 9, 21 modules, Material 3 Expressive, 2026 rebrand, widget, notifications; updated alongside other apps (per the brief; not identifiable) |

Commits per year: 2023 326, 2024 55, 2025 75, 2026 260. Long quiet gaps (no commits at all): 2023-11-09 to 2024-03-28 (140 days), 2024-06-14 to 2024-11-05 (144), 2024-11-13 to 2025-06-12 (211), 2025-06-19 to 2025-10-23 (126), 2026-05-28 to 2026-08-05 (69).

## Stack snapshots (settings.gradle.kts and gradle/libs.versions.toml at a date)

| Date | Modules | Kotlin | AGP | Hilt | Room | Ktor | Compose compiler |
|------|---------|--------|-----|------|------|------|------------------|
| 2023-03 (`0305598f`) | app, chai, data, domain, presentation | 1.8.0 | 7.4.1 | 2.45 | 2.5.0 | 2.2.2 | 1.3.1 |
| 2024-01 (`8f8b2fbd`) | + datasource:local, datasource:remote | 1.9.10 | 7.4.2 (catalog) | 2.48.1 | 2.6.0 | 2.2.2 | 1.5.3 |
| 2024-07 (`9d36a285`) | + shared, shared:data/domain/datasource | 2.0.0 | 7.4.2 (catalog) | 2.48.1 | 2.6.0 | 2.2.2 | 1.5.3 |
| 2025-07 (`f1a24ca4`) | + multiplatform:* incl. composeApp | 2.1.21 | 8.10.1 | 2.56.2 | 2.7.1 | 3.1.3 | 1.5.15, CMP 1.8.1 |
| 2025-12 (`26a9e9cf`) | multiplatform removed again | 2.1.21 | 8.10.1 | 2.56.2 | 2.7.1 | 3.1.3 | 1.5.15 |
| 2026-10 (`575e6055`) | 21 modules (core:*, feature:*, widget, benchmarks) | 2.4.21 | 9.3.1 | 2.60.1 | 2.8.5 | 3.6.0 | Kotlin compose plugin |

Note: the catalog's `agp` key was not bumped in step with the Gradle plugin version through 2024-2025 (`gradleplugin` moved, `agp` stayed 7.4.x); treat AGP for those dates as `gradleplugin` (inferred).

## Era 1: Community bootstrap (2023-02-11 to 2023-06)

- Maintainers: Evans Chepsiror (`chepsi`) is the founding committer (initial commit `5b83edae`, 2023-02-11, "Setup data,domain and presentation" `f3b81067`). Window totals: chepsi 17, Waitara 9, Kenneth Mathari 8, Harun Wangereka 8, Peter Chege 7; 44 bot commits (contributors README). Q1 2023 is the broadest author spread (about 20 humans); Q2 narrows to brian.orwe 8, Peter Chege 6, Raynafs 5, chepsi 6.
- Main changes: Juma Allan sets up `build-logic` (`a6b95222`, 2023-02-15) and copyright headers (`62b423d4`). Hilt injected dispatchers (`5fb10f44`). LiveData to StateFlow with `collectAsStateWithLifecycle` (`b8d3bb71`, `af8617ed`). State moved from composables into ViewModels (`5b943ce7`, `a0e1c172`). Colour scheme switched to Chai colours (`740ce113`, 2023-04-02, MamboBryan); screens adopt Chai buttons and `ChaiTheme` (`ab733f32`, `afdcc29d`).
- Stack: Kotlin 1.8.0, Compose 1.4.0-beta01, Hilt 2.45, Room 2.5.0, Ktor 2.2.2 (`0305598f`).
- Inferred: the burst of many first-time contributors in Q1 suggests an open-source "good first issue" push around the 2023 conference (inferred).

## Era 2: Convention plugins and Chai (2023-07 to 2023-11-09)

- Maintainers: chepsi 41 (his peak), yveskalume 10, robert-nganga 10, Waitara 10, Hannah Olukoye 9, Michael Bukachi 8 in Q4. Contributor churn is high: Q3 and Q4 2023 each show 10+ different authors. Under the rotation model, this is contributors joining via PRs and stepping back when busy.
- Main changes: Hilt convention plugin (`6a6845e4`, #147, 2023-07-17), then all Compose dependencies and Kotlin application config moved into convention plugins (`996ca6fe`, `6435e0e1`, Hannah Olukoye). Remote and local datasources and offline-first feed (`e0c89cfe`, `be8816c4`, `91322d29` #177, Peter Chege, 2023-09-13). Design system module work "Feature/designsystem" (`17eb9fa8`, Jane Waitara, 2023-11-06) and a Chai preview component (`6701b79f`).
- Stack shifts: Kotlin 1.8.20 to 1.9.10, Room 2.6.0, Hilt 2.48 (`5ce6f50e`), `:datasource:local` and `:datasource:remote` added to `settings.gradle.kts` (`8f8b2fbd` snapshot).
- Ends at the first long silence: last commit 2023-11-09, next 2024-03-28.

## Era 3: KMM experiment (2024-03-28 to 2024-06-14)

- Maintainers: Raynafs 21, theophiluskibet (Kibet Theo) 17, Evans Chepsiror (`chepsi`) 3. 4 bot commits. The maintainers changed from the 2023 crowd to two people. Under the community rotation model (user: people join via PRs and step back when busy, with no formal handover), this is a rotation. The reason is not in git and is still open (question 3).
- Main changes: "Feature: Setup KMM" (`3433931f`, chepsi, 2024-05-17), a `droidconke.multiplatform` plugin registered and shared modules migrated (`713f53cb`, `96d3602d`, `26f6a3d0`, theophiluskibet, 2024-06-05), typesafe project accessors (`72aa3459`).
- Stack: Kotlin 2.0.0 and new `:shared`, `:shared:data`, `:shared:domain`, `:shared:datasource` modules (`9d36a285` snapshot), kept in parallel with the Android modules (not a replacement).
- Ends 2024-06-14; next commit 2024-11-05.

## Era 4: Dormant years, Droidcon 2024, and the KMP attempt (2024-11 to 2025-12)

- Maintainers: Evans Chepsiror (`chepsiror`) returns (2024-11-05 "Feature: Droidcon 2024" `c02c6652`, CI fixes `c4deda71`, `c55be11a`). donald-okara's first commit is 2024-11-13 (`efacf25f`, card image fix), his first appearance in the repo. Then 211 days of silence.
- 2025-06: Michael Ndiritu (39 commits, almost all in Q2 2025) leads "Feature: Multiplatform" (`cdd713bd`, chepsiror, 2025-06-12) and Compose Multiplatform (`12fcf57d`, `0f9ce636` "add compose app module", `7b1ac59f` Compose Multiplatform deps, `1ff1ef1b` iOS build phase). Stack at that point: Kotlin 2.1.21, AGP 8.10.1, CMP 1.8.1, Ktor 3.1.3, with `:multiplatform:*` modules (`f1a24ca4`).
- Reversal: "drop KMP support" (`c42e0cdb`, 2025-10-23) then "Fix: Rollback multiplatform" and a run of CI fixes (`c29b4d01`, `26a9e9cf`, 2025-11-04, chepsiror). `settings.gradle.kts` is back to the five-layer layout by `26a9e9cf`. Reason: unknown (user confirmed; open question 2).
- 2025-12-23: donald-okara migrates to Navigation 3 with multiple back stacks (`8faf624f`, `75a03db1`, `680d4805`).
- Material 3 swap in catalog `compose-material3` alias visible by `26a9e9cf`.

## Era 5: 2026, updated alongside other apps (2026-01 to 2026-10-23)

Mark as its own era. The "two other apps" claim comes from the user brief, per the brief; not identifiable in git. The user confirmed those apps are unknown (open question 4), so do not state them as fact.

- Maintainers: donald-okara / donald (same person, confirmed by the user) 144 commits; Harun Wangereka 60 (same person as the 2023 bootstrap contributor with 8 commits, confirmed by the user; returns in Q3 2026); mutukuian 7 and Joel Kanyi 5 (UI contributions); dependabot 42. Sub-phases:
  - 2026-01 to 2026-05-28 (quiet, 34 human commits): donald on session list stepper, status-based UI (`d2ca0bd4`, `deed6637`, `eb64eb13`, 2026-04) and periodic sync (`0bc2d4b5`, 2026-05-28).
  - 2026-08 to 2026-09 modernisation, driven by Harun Wangereka: drop year names so the app reads evergreen (`79e929a2`, 2026-08-13), AGP 9, Gradle 9.7, Kotlin 2.4 (`6bb9b99c`), minSdk 26 (`8f7577d5`), swipe-refresh replaced by Material 3 `PullToRefreshBox` (`ccef8a24`), docs and static analysis (`850096a4`), "Finish Phase 0" with screenshot baselines and Credential Manager (`1c9a1176`, 2026-09-04).
  - 2026-09 module split: "Start the module split" (`d66ca423`, 2026-09-07, `:feature:speakers` as worked example), "Complete the module split: 17 modules, :presentation gone" (`ac81b8ac`, 2026-09-10), baseline profile and benchmarks, release APK -28% (`5a42a5a7`, 2026-09-14), size -7.4% gated in CI (`1fc20ee5`); startup time 35% faster (confirmed by Harun Wangereka, 2026-10-23; no SHA).
  - 2026-10 design and features: Chai on Material 3 and Expressive (`87d23cb7`, 2026-10-04), rebrand to the 2026 droidcon identity (`eb7c436e`, 2026-10-06), WCAG AA contrast guard (`a966dae1`), cube hero (`5941f20f`, 2026-10-15), phase-aware home (`011e9083`), Glance widget (`4492346b`, 2026-10-09), notifications module and settings (`a46f7694`, `7b980827`, `140622db`).
- Stack: Kotlin 2.4.21, AGP 9.3.1, Hilt 2.60.1, Room 2.8.5, Ktor 3.6.0 (`575e6055`); a written `docs/IMPROVEMENT_PLAN.md` (phases) and `AGENTS.md`/`CLAUDE.md` guide the work. Plan notes KMP only if someone wants an iOS app (`docs/IMPROVEMENT_PLAN.md`, question 10).
- README now positions the app as "the community's shared teaching codebase".

## Open questions for the user

Status is shown after each question. Answered items are resolved with the answer.

1. Why did the 2024-06-14 to 2024-11-05 and 2024-11-13 to 2025-06-12 gaps happen, and who owned the app between them? Partly resolved: the maintainership model is the community rotation (user). Contributors join via PRs, get absorbed, and step back when busy without fault, with no formal handovers. The specific reason for each gap is still not in git and not answered.
2. Why was KMP dropped on 2025-10-23 (`c42e0cdb`) and rolled back 2025-11-04 (`c29b4d01`)? Unknown (user confirmed). No commit message gives a reason, and the user does not know one.
3. Why did the 2024 KMM experiment (`3433931f`, `26f6a3d0`) stop after one month, and how did it relate to the 2025 attempt (different people)? Still open.
4. What are the "2 other apps" updated alongside droidcon in 2026, and which commits belong to that parallel work? Unknown (user confirmed). Not identifiable in this repo.
5. How did Harun Wangereka join in 2026-08 (no commits from him between 2023-Q3 and 2026-Q3)? Presumably by opening PRs under the rotation model (user), not verified. Same Harun as in 2023: confirmed by the user.
6. Resolved: `donald` and `donald-okara` are one person; `chepsi`, `chepsiror` and Evans Chepsiror are one person (confirmed by the user).
7. Speakers: names resolved as Donald Isoe, Annunziata Kinya, and Ian Nthuli. Still open: which presenter maps to each era's maintainer.
8. Was the 2025-06 KMP work (Michael Ndiritu) done for an event, a bounty, or personal interest? Still open.
