# Pre-droidcon Kenya meetup — the app session

**Saturday 24 October 2026 · Android254 × Kotlin Kenya**

Source: overview from Harun Wangereka, pasted by the user on 2026-10-23. Saved verbatim as the source document for phase 4. Not yet reconciled with `outline.md` or `history.md`.

Three parts:

1. **The talk:** how the droidcon Kenya Android app evolved from 2018 to 2026, and how to present it.
2. **The bug bounty:** *Msako wa Mende* ("the cockroach crackdown").
3. **The feature bash:** one feature from the improvement plan, built three ways: by hand, in a pair
   with an agent, and by an agent alone.

**Part 4 is the slide deck.** Every slide has its on-screen text, the visual, speaker notes and the
source. Appendix D holds the data for every chart. Parts 1–3 are the background the slides come
from.

Every count in this file comes from `git log`, `git ls-files` or `gh`, run on 2026-10-22 against
`droidconKE/droidconKeKotlin` (the `ticketing` branch, merged to `main` as #453 the same day) and
fresh clones of the org's older Android repos. Appendix C has the commands, so anyone can check a
number on stage.

---

## Contents

- [Run of show](#run-of-show)
- [Before Saturday: decisions and pre-work](#before-saturday-decisions-and-pre-work)
- [Part 1 — Evolution of the app](#part-1--evolution-of-the-app)
  - [1.1 The family tree](#11-the-family-tree)
  - [1.2 Era by era](#12-era-by-era), starting with [the thread: from I/O clone to its own app](#the-thread-from-io-clone-to-its-own-app)
  - [1.3 The stack, layer by layer](#13-the-stack-layer-by-layer)
  - [1.4 droidconKeKotlin in numbers](#14-droidconkekotlin-in-numbers)
  - [1.5 Lost and found](#15-lost-and-found)
  - [1.6 What the history teaches](#16-what-the-history-teaches)
  - [1.7 How to present it](#17-how-to-present-it)
- [Part 2 — Bug bounty: Msako wa Mende](#part-2--bug-bounty-msako-wa-mende)
- [Part 3 — Feature bash: Mkono, Centaur, Wakala](#part-3--feature-bash-mkono-centaur-wakala)
- [Part 4 — The slide deck](#part-4--the-slide-deck)
- [Appendix A — Bounty issue template](#appendix-a--bounty-issue-template)
- [Appendix B — Score sheets](#appendix-b--score-sheets)
- [Appendix C — Reproducing the numbers](#appendix-c--reproducing-the-numbers)
- [Appendix D — Chart data](#appendix-d--chart-data)
- [Appendix E — Visual assets to collect](#appendix-e--visual-assets-to-collect)

---

## Run of show

Default plan, about 4½ hours. A compressed 2½-hour version follows it.

| Time | Block | Who | Notes |
| --- | --- | --- | --- |
| 09:30 | Doors, Wi-Fi, install or update the app | Volunteers | The build with ticketing (#453). QR on the screen for Play, and an APK fallback. Laptops already cloned and built (see pre-work) |
| 10:00 | Welcome, Android254 × Kotlin Kenya | Hosts | 5 min |
| 10:05 | **Talk: eight years of the droidcon Kenya app** | Harun, plus guest voices | 30 min talk + 10 min Q&A |
| 10:45 | Bug bounty briefing | Bounty lead | 10 min: rules, scoring, bingo card, known issues |
| 10:55 | **Msako wa Mende: the hunt** | Everyone | 60 min. Scoreboard on the projector |
| 11:55 | Lunch; live triage | Triage pair | Confirm, mark duplicates, score |
| 12:40 | Bounty results and prizes | Bounty lead | 10 min |
| 12:50 | Feature bash briefing | Bash lead | 15 min: the feature, slices, modes, rules |
| 13:05 | **Feature bash** | Teams | 2 hours. Mentors move between teams |
| 15:05 | Demos | Teams | 3 min per team |
| 15:35 | Judging, results, "what the agents taught us" | Judges | 15 min |
| 15:50 | Close: what ships before droidcon | Hosts | 10 min |

**Compressed (2½ h):** talk 25 min + Q&A 5 · bounty 40 min · bash 60 min on slice A only (pure
Kotlin, no device) · demos and results 20 min.

---

## Before Saturday: decisions and pre-work

### Decisions for the organisers

**Already decided:**

- **The build includes ticketing.** It merged to `main` as #453 on 2026-10-22, and everyone hunts
  on that build. Keep a release APK on a QR code as a fallback, in case the Play rollout hasn't
  reached every phone.
- **Tickets are real.** Anyone who already has a ticket can add theirs for the "At the door" quest.

**Still to decide:**

| # | Decision | Recommendation |
| --- | --- | --- |
| 1 | **Rate limit at the venue.** The lookup allows 30 requests a minute per IP, and the whole room shares the Wi-Fi's address. | Ask ticket holders to add their ticket **at home on Friday**. On the day, the "At the door" quest uses mobile data. |
| 2 | **Debug or release?** | Release for hunting. R8-only bugs only show there; #446 fixed a release-only crash. Debug builds include Chucker, so for network bugs a hunter can reproduce on debug and attach the Chucker export. |
| 3 | **Reviewers.** `.github/CODEOWNERS` was deleted on 2026-08-13. | Name 2–3 reviewers for bash PRs on the day. |
| 4 | **Labels and template.** The repo has only GitHub's default labels. The existing bug template is stale (it asks for a "Recoin version" and an iOS browser). | Create `bounty`, `bounty:confirmed`, `bounty:duplicate`, `a11y` and `feature-bash`. Add the template in Appendix A as `.github/ISSUE_TEMPLATE/bounty.md`. |
| 5 | **Prizes.** | See the prize table in 2.8. Fill in the actual prizes. |
| 6 | **Device pool.** | At least one tablet or foldable, one old phone on Android 8 (minSdk is 26), one small phone, and a Chromebook or desktop window if available. Bingo needs them. |
| 7 | **Notifications PR.** #448 (reminders, typed pushes, settings, deep links) is open. | Keep it out of the bash so nobody collides with it. If it merges before Saturday, add its features to the bounty scope. |

### Pre-work for participants (send on Thursday)

> **Bring a laptop that has already built the app.** The first build downloads Gradle 9.7, AGP 9.3,
> a JDK and every dependency. On venue Wi-Fi that takes most of the morning.
>
> ```bash
> git clone https://github.com/<you>/droidconKeKotlin   # fork first
> cd droidconKeKotlin
> ./gradlew assembleDebug                               # once, at home
> ```
>
> You do not need to install a JDK. `gradle/gradle-daemon-jvm.properties` pins the daemon and
> foojay provisions it. Use a current stable Android Studio. Read `AGENTS.md`. It is 10 minutes and
> it is the brief for the feature bash.
>
> Bounty only? Bring an Android phone with the latest droidcon Kenya app from Play.
>
> **Have a droidcon ticket?** Add it in the app at home on Friday (Home → "Add your ticket"). The
> venue Wi-Fi shares one address and the ticket lookup is rate limited.

---

## Part 1 — Evolution of the app

### 1.1 The family tree

The droidconKE org has built a conference app every year it has held the conference, often in more
than one stack. These are the Android repos:

| Repo | Years | Commits | Authors¹ | Kotlin files | Kotlin LOC² | ★ / forks | One line |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `droidconKE` | 2018 (maintained to 2021) | 230 | 6 | 82 (+1 Java) | 3,764 | 21 / 23 | Started in Java; a Kotlin branch from August 2018 replaced it by April 2019 |
| `droidconKE2019App` | 2019 | 153 | 6 | 96 | 4,766 | 16 / 15 | Fully Kotlin, Jetpack Navigation, Firestore |
| `droidconKE2020App` | 2019-11 → 2020-11 | 393 | 15 | 122 | 5,056 | **120** / 40 | Multi-module with dynamic features |
| `droidconKE2022Android` | 2022 (Jul → Nov) | 622 | 25 | 180 | 14,418 | 84 / 56 | Compose arrives; chai is born |
| `droidconKeKotlin` | 2023 → now | 686 | 58 | 460 | 39,850 | 116 / 94 | The evergreen repo, with no year in its name |
| `chai` | 2023 → 2026 | 207 | 7 | 27 | 1,529 | 9 / 4 | The design system as its own repo |
| `droidconKeKMP` | 2025-10 | 1 | 1 | 6 | 123 | 2 / 0 | Where KMP went after leaving the main repo |

¹ Includes bots (dependabot, github-actions). ² At the repo's last commit; for `droidconKeKotlin`,
with ticketing (#453) in.

**The same app in other stacks** (worth one slide; it shows the community, not just the Android
app):
Flutter (`droidcon_ke_flutter` 2019, `droidconke2020_flutter`, `droidcon_2022_flutter`), React
Native (`droidconKE2022ReactNative`, `droidconKE2023ReactNative`), web, admin dashboards, and the
PHP `droidconEventsBanckend`, which serves this year's ticket lookup.

Across the five app repos that is **about 2,100 commits**.

### 1.2 Era by era

#### The thread: from I/O clone to its own app

**This is the story to tell through the whole talk.** The droidcon Kenya app started as a clone of
Google's I/O conference app (`google/iosched`), and over four years became an app of its own.
Each era section below picks the thread up again; slides S03, S07, S09, S12, S21 and S29 carry it.

| When | Evidence | Where it stood |
| --- | --- | --- |
| 2018-03-24 | The org forks `google/iosched`. Four days later Harun Wangereka fixes its Gradle conflicts for Android Studio 3.0.1. The I/O code being forked is already Kotlin: 268 Kotlin files, 3 Java | **Studying the I/O app** |
| 2018-05-07 | droidconKE's first day: "agenda ui complete", "added event, travel and info fragments". Two days later: "schedule, info and map activity setup", the same three tabs as I/O | **A clone of its screens** |
| 2018 | The README opens with I/O's words: "sessions, office hours, app reviews, codelabs". Nairobi had none of those. Even its screenshot tag is the I/O README's, `<img align="center" src="schedule.png" alt="Schedule screenshot" height="640" width="360">`, though the screenshot itself is droidcon's own | **A clone of its words** |
| 2018-08-13 | The fork pulls in the I/O 2018 code ("pulling io18 code"). Nine days later droidconKE opens its Kotlin branch | **Following the I/O app** |
| 2019 | The I/O wording is still in the README: "Guide to App Architecture", "office hours, app reviews, codelabs", "fetched and stored" | **Still I/O-shaped** |
| 2020 | The first README without any I/O wording. The team's own architecture (7 dynamic features) and its own architecture diagram | **Its own structure** |
| 2022 | Its own designs (Adobe XD, light and dark themes) and its own design system, chai, the Swahili word for tea | **Its own look** |
| 2026 | The 2026 droidcon brand; Material 3 Expressive; features shaped by this conference: the gate's own QR from droidcon's backend, Home that follows Nairobi's conference days, Updates, the cube | **Fully its own** |

**Screen names, I/O app (March 2018) against droidconKE (May 2018).** This works as a slide. The
names are from each repo's `res/layout/`.

| Google I/O app (the org's fork, 2018-03-28) | droidconKE (2018) |
| --- | --- |
| `fragment_info.xml` | `fragment_info.xml` |
| `fragment_event.xml` | `fragment_event.xml` |
| `fragment_travel.xml` | `fragment_travel.xml` |
| `fragment_schedule.xml` | `fragment_schedule.xml` |
| `fragment_schedule_agenda.xml` | `fragment_agenda.xml` |
| `fragment_customize_schedule.xml` | `customize_schedule_dialog.xml` |
| `fragment_schedule_filter.xml` | `filters_bottom_sheet.xml` |
| `fragment_map.xml` | `activity_map.xml` + `map_bottom_sheet.xml` |

**Detail for Kotlin Kenya:** the I/O code the org forked was already Kotlin, yet droidconKE was
written in Java first. It only followed the I/O app to Kotlin after the I/O 2018 code was pulled
into the fork in August.

**Why it matters:** copying the best app available was the right way to start in 2018. Eight years
later the app has things the I/O app never needed: a ticket that matches the gate's own check-in
code, a design system named after tea, and a Home screen that follows a conference in Nairobi.

#### 2018 — The first one: an I/O clone, Java to Kotlin in its first year

- **Commits:** 230 (78 of them in May 2018 alone). **Top authors:** Harun Wangereka, Michael Bukachi, muhosteve.
- **Package:** `com.droiddevelopers254.droidconke`.
- **Timeline from the log:**
  - 2018-03-24: the org forks Google's I/O app, `google/iosched` (see the thread above).
  - 2018-05-07: initial commit. Day one builds the I/O app's screens: agenda, event, travel, info. By the end of May there were 69 **Java** files.
  - 2018-05: Firebase Auth, Crashlytics (Fabric), Remote Config, FCM; "changed code base to MVVM structure" (05-21); Google Maps showing iHub with a directions bottom sheet.
  - 2018-06-27: "change of database from RealTimeDataBase to Cloud FireStore complete".
  - 2018-07: **Cloud Functions send push notifications to people who starred a session**; RxJava for Room.
  - 2018-08-22/23: a "kotlin branch" and "refactored codebase to kotlin". The main line was still
    Java until December (108 Java files on 2018-12-16). By 2019-04-30 there were 107 Kotlin files
    and 2 Java files.
  - 2018-12: migrated to AndroidX; the first view tests.
- **Stack:** XML layouts (38), ButterKnife, kotlin-android-extensions, Koin, Room + RxJava2, coroutines, Glide, Anko, minSdk 21.
- **Talking point:** Kotlin became official on Android at Google I/O in May 2017. A year later this team started in Java, then ran a Kotlin branch alongside it for months before switching over. That is the realistic way a migration happens. The app it was cloning, Google's I/O app, was already in Kotlin.

#### 2019 — All Kotlin, all Jetpack

- **Commits:** 153 in about 4 months. **Top authors:** Harun Wangereka, Michael Bukachi.
- **Package:** `com.android254.droidconke19`. The community's name is now in the package, and it still is today (`com.android254.*`).
- **New:** Jetpack Navigation with Safe Args, Firestore KTX, Firebase Performance, session filters, announcements, session and event feedback, a venue map (`MapFragment`).
- **Stack:** Kotlin 1.3.60, AGP 3.5.2, Koin, LiveData, Room, coroutines with `runCatching`, ThreeTenABP for dates.
- **CI:** Travis CI, added 2019-11-28.
- **The I/O thread:** the README's Architecture section is still the I/O app's: "Guide to App Architecture", "office hours, app reviews, codelabs", conference data "fetched and stored". Still I/O-shaped.
- **Talking point:** still a single `:app` module. The README promises "phones and tablets of all shapes and sizes". That promise was only kept in 2026 (see 1.5, Lost and found).

#### 2020 — The big modular one, and the conference that didn't happen

- **Commits:** 393, 159 of them in April 2020. **Authors:** 15. **Top authors:** Oliver Muthomi (94), Harun Wangereka, Michael Bukachi, Benard Mathu, David Odari, Jabez Magomere.
- **The most-starred Android repo in the org:** 120 stars, 40 forks.
- **Architecture:** `core`, `network`, `data`, `repository`, `test-utils`, and **seven features as `com.android.dynamic-feature` modules** (about, auth, feed, feedback, home, sessions, speakers).
- **Build:** Gradle Kotlin DSL, a `buildSrc/Dependencies.kt` catalogue, ktlint and detekt on every module, GitHub Actions to build the bundle.
- **Stack:** Koin, Retrofit + OkHttp, Room, Coil (new), data binding, MockK and Kakao for tests, dark mode with a true-black AMOLED variant.
- **The I/O thread:** the first README without the I/O app's wording, and an architecture the I/O app never had. The app's own structure.
- **What the record shows:** the README says the conference was "to be held in Nairobi from August 6–8th 2020". The 2022 README calls 2022 "the 3rd Android Developer conference". The last commits, in November 2020, fix release builds. **Let someone who was there tell this part.** It is a good human moment, and these notes should not guess at it.
- **Talking point:** dynamic features were the modularisation advice of 2019–2020. Nobody uses them for this now. That is fine; the team learned modules here.

#### 2022 — Compose arrives

- **Commits:** 622 in five months (212 in July 2022). **Authors:** 25. **Top authors:** Gibson Silali, Waitara, Theophilus Kibet, Evans Chepsiror, Harun Wangereka, Juma Allan, Michael Bukachi.
- **Firsts:** Jetpack Compose (Compose 1.3 alpha, Material 3 1.0 alpha), Hilt, Ktor, Paging 3, Lottie, a version catalog (`libs.versions.toml`), spotless, fastlane with `whatsnew` notes, "Deploy to playstore" (2022-11-17).
- **Architecture:** `app`, `data`, `domain`, `presentation`, plus `chai` and `chaidemo`.
- **chai:** tamzi added the design-system colours in August 2022. chai got its own repo in March 2023 and still lives in this app as `core:designsystem`. Its package, `com.droidconke.chai`, has not changed.
- **The I/O thread:** the README gets a "Designs" section with the app's own Adobe XD designs, in light and dark themes, and "The app uses a design system: Chai". This is the year the app stops looking like anyone else's.
- **Detail people will enjoy:** "feat: Disable auth" landed on 2022-11-15, the day before the conference opened on November 16.
- **Talking point:** Compose 1.0 shipped in July 2021. A year later, about two dozen volunteers built a production Compose app in five months.

#### 2023 → 2025 — One repo to keep

- **2023-02-11:** `droidconKeKotlin` is created. **It is not a rewrite.** On day three (`f3b81067`, "Setup data, domain and presentation") it took 280 files and 15.9k lines from the 2022 app. 173 of its 177 Kotlin files have the same path as in `droidconKE2022Android`, and 136 are byte-for-byte identical. The repo has no year in its name, so contributors keep their history from one year to the next.
- **2023 (326 commits, about 40 distinct author names):** the busiest year for the community.
  - Convention plugins in `build-logic` for Hilt, Room, Compose and Firebase (Hannah Olukoye, Jul–Aug); added to the version catalog (Eton Otieno, Oct).
  - LiveData to StateFlow and `collectAsStateWithLifecycle` (Peter Chege, yveskalume).
  - Dispatchers injected through Hilt (Kenneth Mathari).
  - **Offline first** for sessions, organisers and feed, plus sync at startup (Peter Chege, Aug–Sep).
  - Shimmer loading states (robert-nganga), Chucker for network debugging (Yves Kalume), kapt to KSP (Michael Ndiritu), push notifications (Evans Chepsiror, Nov).
  - "Remove 2022 references" (Mar). The first attempt to make the app evergreen.
- **2024 (55 commits):** a quiet year.
  - May–June: KMM setup (chepsi) and a multiplatform convention plugin (theophiluskibet), K2 and the Compose Compiler Gradle plugin (#286), Jacoco coverage on a CI matrix (Raynafs).
  - "Feature: Droidcon 2024" (#305) changed **3 lines**: the event slug.
- **2025 (75 commits):**
  - June: **Compose Multiplatform with an iOS app** (Michael Ndiritu, #323). The repo grows to 14 modules, including `multiplatform/*`.
  - 2025-10-23: "drop KMP support" removes 2,111 lines. Two days later a separate `droidconKeKMP` repo appears with a one-commit skeleton. November: CI fixes after the rollback.
  - December: **Navigation 3** migration with multiple back stacks (donald-okara).
- **Talking point (Kotlin Kenya will want this):** KMP has been tried twice, in 2024 and 2025. Both times it was taken out to keep the Android app shippable. Today `core:model` is a JVM-only module, enforced by the build, so a third attempt would be a port and not a rewrite. Plan §17.2 question 10 asks it directly: worth doing if someone wants to build the iOS app.

#### 2026 — Modern Android, measured

- **230 commits so far.** Four or five people, and the most code ever: Kotlin LOC went from 18.8k (August) to 39.9k (now).
- **Jan–Aug (donald-okara):** Navigation 3 tests and animated transitions, MVI-style intents for sessions, live "Now/Up next" session status with a stepper UI, periodic sync with reconciliation, optimistic bookmarks, sticky headers.
- **2026-08-13:** `AGENTS.md`, `CLAUDE.md` and `docs/IMPROVEMENT_PLAN.md` land. `AGENTS.md` opens with "Working notes for AI agents and new contributors". It is the first time the repo is written for two audiences.
- **Aug–Oct (Harun Wangereka, with contributors):**

| Date | PR | What changed |
| --- | --- | --- |
| Aug 13–14 | #374–#376 and direct commits | AGP 9 on Gradle 9.7, Kotlin 2.4, minSdk 26, `kotlin.time` migration, the full check suite on every PR, edge-to-edge, weekly grouped Dependabot |
| Sep 1–2 | — | Shared-element transitions into session and speaker details (Joel Kanyi) |
| Sep 4 | #391 | Phase 0 finished: screenshot baselines, zero build warnings, Credential Manager, chai on Material 3 |
| Sep 7–10 | #392, #397 | Module split into 17 modules; `:presentation` deleted; speakers redesign (mutukuian) |
| Sep 14 | #401, #402 | Baseline profile: **cold start −22% (1,335 → 1,047 ms)**, primary dex −36%, **release APK −28%** |
| Sep 17–21 | #409, #410 | Edge-to-edge finished; adaptive and large-screen layouts |
| Oct 4–6 | #421, #425 | chai on Material 3 roles, **Material 3 Expressive**, the 2026 droidcon rebrand |
| Oct 8–10 | — | **Glance widget** for the next session (donald-okara). Built spec → plan → implementation, and the spec and plan are in the history |
| Oct 9–11 | — | Sync keyed on remote ids (no more duplicate rows); WCAG AA contrast tests for every chai colour pair |
| Oct 12 | #435–#444 | Download size −7.4%, enforced by a CI size gate; session sharing; Compose metrics |
| Oct 12 | — | **Phase-aware Home**: a countdown before the event, today's agenda with clashes during it, a thank-you after |
| Oct 15 | #446 | The **3D Rubik's cube hero**; release crash fixed |
| Oct 20 | #451 | Ready for 2026: this year's event, a calmer Home, the **Updates** timeline; release notes for 1.0.22 |
| Oct 21–22 | #453 | **Your ticket on the phone**: look it up by number or scan the printed code, kept offline, shown at the door with the gate's own QR |

- **Talking point:** the 2026 work is measured. `docs/performance.md` gives each claim a device,
  a median and a caveat. It even records results that did not help: full AOT is *slower* than the
  profile, at 1,228 ms against 1,047 ms. That habit is worth more to the audience than any single number.

### 1.3 The stack, layer by layer

This makes one strong slide. Read across a row to see a layer change over the years.

| Layer | 2018 | 2019 | 2020 | 2022 | 2023–25 | 2026 |
| --- | --- | --- | --- | --- | --- | --- |
| Language | Java → Kotlin | Kotlin 1.3.60 | Kotlin 1.4.10 | Kotlin 1.7.10 | K2 (2024) | Kotlin 2.4.10 |
| UI | XML + ButterKnife + synthetics | XML + Navigation | XML + data binding | Compose + M3 alpha | Compose + M3 | Compose + M3 Expressive, adaptive |
| Navigation | Activities + fragments | Jetpack Navigation + Safe Args | Navigation across dynamic features | Navigation Compose | Navigation 3 (Dec 2025) | Nav3, list-detail scenes, shared elements |
| DI | Koin | Koin | Koin | Hilt | Hilt (KSP) | Hilt |
| Data | Firebase RTDB → Firestore | Firestore | Retrofit + Room | Ktor + Room + Paging | Offline first, sync worker | Sync keyed on remote ids, one transaction per table |
| Async | RxJava + coroutines | Coroutines + LiveData | Coroutines | Coroutines + Flow | StateFlow, lifecycle-aware collection | `kotlin.time`, a shared clock ticker |
| Images | Glide | Glide | Coil 1 | Coil 2 | Coil 2 | Coil, WebP assets |
| Modules | 1 | 1 | 13 (7 dynamic features) | 6 | 5 → 7 (14 with KMP) | 20 |
| Build | Groovy | Groovy | Kotlin DSL + `buildSrc` | Version catalog | Convention plugins | AGP 9.3, Gradle 9.7, toolchain pinned |
| Quality | — | Travis CI | ktlint, detekt, GitHub Actions | spotless, ktlint, detekt | Jacoco, Codecov | + Android Lint, compose-lints, stability check, Roborazzi, size gate, Macrobenchmark |
| Tests | Espresso | ViewModel tests | MockK, Kakao | Compose UI tests | 25 test files | 110 test files, 180 screenshot goldens |
| minSdk | 21 | 21 | 21 | 21 | 21 → 24 (2024) | 26 |

### 1.4 droidconKeKotlin in numbers

Snapshots of the repo over time. This table makes a good line chart.

| Date | Modules | Kotlin files | Kotlin LOC | Test files | Screenshot goldens |
| --- | --- | --- | --- | --- | --- |
| 2023-03-01 | 5 | 182 | 14,699 | 24 | 0 |
| 2023-12-01 | 7 | 260 | 17,046 | 25 | 0 |
| 2024-12-01 | 11 | 270 | 17,392 | 25 | 0 |
| 2025-07-01 | 14 | 271 | 17,322 | 25 | 0 |
| 2026-08-01 | 7 | 272 | 18,774 | 27 | 0 |
| 2026-09-15 | 18 | 323 | 24,804 | 51 | 51 |
| 2026-10-01 | 18 | 345 | 27,842 | 62 | 92 |
| 2026-10-22 (with #453) | 20 | 460 | 39,850 | 110 | 180 |

**Commits per month.** This is the "seasonal" chart. Commits cluster before each conference.

```
2023  Feb 44 · Mar 40 · Apr 25 · May 14 · Jun 27 · Jul 11 · Aug 14 · Sep 54 · Oct 62 · Nov 35
2024  Mar 2 · May 7 · Jun 40 · Nov 6
2025  Jun 54 · Oct 2 · Nov 7 · Dec 12
2026  Jan 6 · Feb 5 · Mar 7 · Apr 24 · May 4 · Aug 79 · Sep 38 · Oct 67
```

The older repos follow the same pattern: 2018 had 78 commits in May, 2020 had 159 in April, and
2022 had 212 in July.

**Distinct human author names per year in this repo:** 2023: ~41 · 2024: 8 · 2025: 9 · 2026: 5.
Some people commit under two names, so read these as rough. **This is the slide that sets up the
afternoon.** The code grew fastest in the year with the fewest people. The bounty and the bash are
how the community gets back in.

**Merged PRs:** 332. **Closed issues:** 94. **Open issues today:** 0. That is a clean slate for the bounty.

### 1.5 Lost and found

Features the app had, lost, and is getting back. This works well as a short "remember when" slide.

| Feature | Then | Gap | Now |
| --- | --- | --- | --- |
| Push for your starred sessions | 2018: Cloud Functions pushed notifications to people who starred a session | Pushes became generic announcements | **#448** (open): local reminders with WorkManager, per-channel settings, permission asked on the first star |
| Venue map | 2018 and 2019: Google Maps with the venue and directions | Gone since 2020 | Plan **§11.6**: an SVG floor plan, tappable rooms. Waiting on organisers for the SVG |
| Phones *and tablets* | Promised in every README since 2018 | Phone layouts only | **2026, #410**: adaptive layouts, list-detail, form-factor screenshot goldens |
| Sign-in | 2018 FirebaseUI; 2022 Google sign-in, then "Disable auth" the day before the event | — | **2026**: Credential Manager |
| Travel details | 2018–2019 had a travel screen | Gone | Not planned. Ask the room whether it should come back |
| Session feedback | 2018 onwards | Kept | Still in `feature:about`; plan §11.5 extends it |

### 1.6 What the history teaches

Candidate closing points. Lead with the first; pick two more.

0. **Start by copying the best, then stand on your own.** The app began as a clone of Google's I/O
   app: its screens, its README, and eventually its language. It took until 2020 to have its own
   structure, and until 2022 to have its own look. Copying wasn't a shortcut to be ashamed of. It
   was how a new community learned.
1. **Repos with a year in the name reset the community every year.** The 2023 evergreen repo
   carried the 2022 code forward, and contributors' history with it.
2. **Experiments that get removed are still progress.** Dynamic features (2020), KMM (2024) and
   Compose Multiplatform (2025) were all taken out. Each one left something behind: modular
   thinking, a JVM-only model module, a separate KMP repo.
3. **Quiet years happen.** "Droidcon 2024" was a three-line change. Volunteer projects need to be
   easy to pick up again, which is what `AGENTS.md`, the plan and `docs/` are for.
4. **Measure before you claim.** The 2026 performance doc records failed experiments and noisy
   devices next to the wins.
5. **Write the repo for two readers.** Since August 2026, the same `AGENTS.md` briefs a new
   contributor and a coding agent. The feature bash tests whether that works.

### 1.7 How to present it

#### Title and abstract (for the meetup post)

> **From I/O clone to our own app: eight years of the droidcon Kenya app**
>
> In 2018 the droidcon Kenya app started as a clone of Google's I/O app: its screens, its README,
> even its words about office hours Nairobi never had. Since then it has been rebuilt by whoever
> turned up: Java to Kotlin, XML to Compose, Koin to Hilt, one module to twenty, and twice to Kotlin
> Multiplatform and back, until it became an app of its own. We'll dig through about 2,100 commits
> across five repos to see what each year chose, what it dropped, and what came back. Then we'll
> hunt bugs in this year's app together and build a feature for it, with and without AI agents.

Alternative title, if the agent angle should lead: **From Java to agents: eight years of the
droidcon Kenya app.**

#### Shape: seven acts in 30 minutes

Part 4 has the slides for each act.

| # | Act | Minutes | Slides | The point |
| --- | --- | --- | --- | --- |
| 0 | **Cold open** | 2 | S03–S05 | "Same app. Eight years. Five repos. About 2,100 commits." It started as someone else's app. Commits come in seasons |
| 1 | **2018: an I/O clone** | 4 | S06–S07 | **The highlight:** born as a clone of Google's I/O app, down to the screen names and the README. Java first, then Kotlin. Firebase for everything |
| 2 | **2019: all Jetpack** | 2 | S08 | `com.android254` enters the package name |
| 3 | **2020: the modular one** | 3 | S09–S10 | Its own structure, 120 stars. **Hand the mic to someone from 2020** for the conference that didn't happen |
| 4 | **2022: Compose arrives** | 3 | S11–S12 | About two dozen volunteers, five months, Compose in production. **Standing on its own:** its own designs and chai |
| 5 | **2023–25: one repo** | 5 | S13–S17 | Evergreen repo carried forward; the 2023 community peak; the quiet 2024; the KMP thread; Navigation 3 |
| 6 | **2026: measured** | 8 | S18–S26 | Two kinds of reader, 20 modules, measured wins, the new look |
| 7 | **Lessons and the ask** | 3 | S27–S31 | From clone to its own app. "The code grew; the community didn't. Let's fix that this afternoon." Hand over to the bounty |

#### "Git archaeology" moment, 1 minute, live in the terminal

```bash
git log --reverse --format='%ad %an %s' --date=short | head -3     # where it started
git show --stat f3b81067 | tail -1                                 # 280 files from 2022
git log --format='%ad' --date=format:%Y | sort | uniq -c           # the seasons
```

#### Pass the mic

The strongest version of this talk is not one voice. Invite one person per era for 90 seconds
each: one memory and one decision they'd make differently. Candidates from the log:

- **2018–19:** Michael Bukachi
- **2020:** Oliver Muthomi, Jabez Magomere or David Odari
- **2022:** Evans Chepsiror, Gibson Silali or Waitara
- **chai:** tamzi
- **2023:** Hannah Olukoye or Peter Chege
- **2025:** Michael Ndiritu (KMP)
- **2026:** donald-okara, Joel Kanyi or mutukuian

Message them this week. Plan the talk to work without them as well.

#### Tailoring to the two communities

- **Android254:** platform history. AndroidX, Jetpack, dynamic features, Compose, adaptive layouts,
  baseline profiles, Material 3 Expressive.
- **Kotlin Kenya:** language and multiplatform history.
  - Java → Kotlin in 2018, and synthetics → Compose.
  - RxJava → coroutines → Flow.
  - kapt → KSP in 2023, K2 in 2024.
  - kotlinx-datetime's `Instant` → `kotlin.time` in 2026.
  - Two KMP attempts, and a JVM-only `core:model` built so a third would be a port.

  A "Kotlin Kenya corner" callout on slides 1, 5 and 6 serves them without slowing the talk.

#### Slide hygiene

- One number per slide, large. Put the source command in small text underneath.
- Use the 2026 brand from `core:designsystem`. Screenshot the old apps instead of trying to build them: AGP 3.5 and jcenter will not build today.
- Credit contributors by name on the era slides. People come to meetups to see their name on a slide.

---

## Part 2 — Bug bounty: Msako wa Mende

*Msako* is a crackdown and *mende* is a cockroach. For one hour, the room is the QA team the app has
never had.

### 2.1 Why this is worth an hour

The plan's next item (§14) is an **accessibility listening pass that "needs a person, not a
contributor slot"**: TalkBack, Switch Access, keyboard, two panes. §16.4 asks that every change
work in dark mode, at 200% font and on a tablet. A room of 40 people with 40 different phones *is*
that pass. Frame the bounty as finishing §14, not as a game the maintainers made up. That framing
also makes the accessibility finds the most valuable ones.

### 2.2 Scope

**In scope:** the Android app's behaviour on a device.

- Every screen: Home, Sessions, Session details, Speakers, Updates, About and feedback, Sign-in, Ticket, the widget, the launcher shortcut.
- Offline behaviour, sync, layout, accessibility, copy, performance you can see.

**Out of scope (do not do these):**

- The backend, the API, or the Firebase project. No scripted requests, no load testing, no fuzzing endpoints.
- Looking up anybody else's ticket. The lookup is by number plus email by design. Use only your own ticket.
- Denial of service, social engineering, or anything on other attendees' phones.
- **App-side security issues** (data in logs, exported components, storage): report these **privately to the organisers**, not as a public issue. They still score (see 2.4).

### 2.3 Known issues (score 0, so don't spend time on them)

| Known | Where it's tracked |
| --- | --- |
| Ticket lookup returns "busy, try again in a minute": 30 requests a minute per IP, and the venue Wi-Fi is one IP | Plan §7.2. Add your ticket before the day |
| Ticket lookup needs the purchase email as well as the number | By design, §7.2 |
| The lookup key is readable from Remote Config | Accepted by the organisers, §7.2 |
| Notification permission is requested on first launch with no explanation | Fixed in #448 (open) |
| Only one "All" notification channel; the sync channel is loud and badly named | §3.10.1 and #448 |
| The clash banner cannot be dismissed | §5.3 |
| The cube misses 60 Hz on low-end phones | §12, measured in `docs/performance.md` |
| The widget refreshes every 30 minutes all year | §11.7 |
| No unread dot on Updates | §6A.5 |
| No venue map, no "add to calendar" | §11.6; §11.4a is the feature bash |

Update this list on Friday against `main`.

### 2.4 Scoring: the Mende Board

Points are *bob* (shillings, in Sheng). The first valid report wins; order is by issue number.

| Category | Examples | Bob |
| --- | --- | --- |
| 🪳 **Crash, ANR, data loss** | Crash on rotate; starred sessions lost after process death; the ticket disappears | **50** |
| ♿ **Accessibility** | TalkBack reading order wrong or doubled; missing label; unreachable by Switch Access or keyboard; clipped text at 200%; touch target under 48 dp; contrast | **30** |
| 💔 **Broken feature** | Wrong session shown as "Now"; a filter that doesn't filter; sync doesn't update; a share that loses its text | **30** |
| 📐 **Layout and theming** | Dark-mode glitches; tablet or foldable two-pane issues; landscape; insets under the system bars | **15** |
| ✏️ **Copy and polish** | Typos; wrong tense; a confusing empty state | **5** |
| 🔒 **App-side security** (reported privately) | Personal data in logs; an exported component that shouldn't be | **50** + a special mention |

**Bonuses:**

- **Video or screen recording** that reproduces it: +10.
- **Second-device confirmation** of someone else's bug, on a different phone or Android version, as a comment: +5. This fills in the device matrix.
- **A failing test** that reproduces it, in a PR: ×2 for that bug.
- **A fix PR** opened before demos (it can be finished in the bash): +50.

**Judgement calls:** triage decides severity. The decision is final, explained in one line, and
argued over lunch.

### 2.5 Bingo card

Print one per team. A full row, column or diagonal earns +20. The whole card earns +100. A square
counts once the team has filed one bug found in that condition.

| | | |
| --- | --- | --- |
| 🌙 **Dark mode** | 🔠 **200% font + largest display size** | 🗣️ **TalkBack on** |
| 📱↔️ **Landscape** | 🗂️ **Tablet, foldable or desktop window** | ✈️ **Airplane mode** |
| 💀 **Developer options → "Don't keep activities"** | ⏳ **Time travel** (date set to a conference day) | ⌨️ **Keyboard or Switch Access only** |

### 2.6 Quests

Guided missions for people who don't know where to start. Each one points at a place the plan says
is fragile or untested.

1. **The time traveller.** Set the phone's date to the day before the conference, to day 1 at
   10:30, to the last evening, and to a week after. Home should show a countdown, today's agenda
   with clashes, and then a thank-you. Check the "Now/Up next" rail and the widget at each step.
   (Phase logic is in `core/domain/.../schedule/Schedule.kt`.)
2. **Process death.** Turn on "Don't keep activities". Turn on "My sessions", star some sessions,
   rotate, go to the background and come back. Plan §3.9 lists this exact area (B6) as having no
   regression test.
3. **Off the grid.** Install, let it sync once, turn on airplane mode, force-stop and reopen.
   Everything you saw should still be there. Then come back online during a refresh.
4. **Big text, small phone.** Use the smallest phone in the room with 200% font and the largest
   display size. Every screen, including the ticket pass, should be readable without overlap.
5. **Two panes.** On a tablet, foldable or resizable window: open a session beside the list,
   rotate, resize, press back. Does the right thing stay selected? (§4.4 says there is no selected
   state yet. Treat that as known; look for things that are *worse* than that.)
6. **At the door** (needs your own ticket). Add it by number
   and by scanning a printed code. Use mobile data, not the venue Wi-Fi. Set brightness to minimum,
   then scan the pass with another phone's camera. Repeat in dark theme, at 200% font, and with no
   network. Try removing it and adding it again. Only touch your own ticket.
7. **Listen to it.** Use TalkBack only, eyes closed if you're brave. Star a session, find its
   speaker, share it. This is §14.1 itself, and a11y findings score 30.
8. **The widget.** Add it, resize it through every size, change the date, toggle dark mode.

### 2.7 Running it

- **Roles:** a bounty lead (rules and scoreboard), a triage pair (two maintainers at a table with
  a laptop), and a scorekeeper.
- **Filing:** GitHub issues on `droidconKE/droidconKeKotlin` with the `bounty` label, using the
  template in Appendix A. One bug per issue. People without a GitHub account file through a
  teammate.
- **Board:** a GitHub Project with the columns *New → Confirmed → Duplicate → Not a bug → Fixed*.
- **Live leaderboard on the projector.** A spreadsheet works. So does a refresh loop:

  ```bash
  watch -n 60 "gh issue list -R droidconKE/droidconKeKotlin --label bounty:confirmed \
    --json author --jq 'group_by(.author.login)|map({who:.[0].author.login,n:length})|sort_by(-.n)'"
  ```

- **Teams:** threes (a "matatu crew"), mixing experience levels. One person drives the phone, one
  writes the issue, and one hunts edge cases.
- **Afterwards:** confirmed bugs that are small become `good first issue`. The best ones feed the
  feature bash's stretch list.

### 2.8 Prizes

| Prize | For |
| --- | --- |
| 🏆 **Mende Mkuu** ("the big cockroach") | Most bob, by team |
| ♿ **Best accessibility find** | The finding that most improves the app for someone using TalkBack or Switch Access |
| 💥 **Best crash** | The most surprising reproducible crash |
| 🌱 **First-timer award** | Best report from someone filing their first GitHub issue |
| 🎯 **Bingo blackout** | First full card |

The organisers fill in the actual prizes: droidcon tickets, swag, and so on.

---

## Part 3 — Feature bash: Mkono, Centaur, Wakala

### 3.1 The idea

Build **one real feature from the improvement plan**, ship it in the release before droidcon, and
run an honest experiment while doing it. The same slice of work is done in three modes:

| Mode | Swahili | Rules |
| --- | --- | --- |
| **Mkono** | *hand* | Humans only. IDE completion is fine; no LLM chat or agent |
| **Centaur** | — | Humans and a coding agent together (Claude Code, Gemini in Android Studio, Copilot, Cursor: any). Humans may edit freely |
| **Wakala** | *agent* | The agent writes all the code. Humans may only prompt, run commands and review. **No manual edits to source files** |

Every team, in every mode, must explain every line of its diff to the judges. That rule keeps the
agent modes honest and makes the comparison worth something.

### 3.2 The feature: add sessions to your calendar (§11.4a)

**Why this one:**

- The plan calls it "the highest value-to-effort ratio feature in this entire document".
- It needs **no backend**. `git grep` finds no `CalendarContract` or `.ics` code, so nobody has started it.
- It **does not collide with #448** (notifications and deep links) or with ticketing.
- It suits both communities. Slice A is pure Kotlin that Kotlin Kenya can own without a phone. Slices B and C are Android and Compose for Android254.
- Attendees will use it at the conference in a few weeks.

**What the user gets:**

1. **"Add to calendar"** on a session's details. It opens the user's calendar app with the
   session filled in.
2. **"Export my agenda"** on the Sessions screen when at least one session is starred. It writes an
   `.ics` file with every starred session and offers it to any calendar app.

### 3.3 The slices

Teams take a slice. With enough people, each slice runs in two or three modes.

#### Slice A — `buildIcs`, pure Kotlin (no device needed)

**Where:** next to the schedule logic in `core/domain/src/main/java/com/android254/domain/`, for
example a new `calendar/Ics.kt`, with tests beside `schedule/ScheduleTest.kt`.

**The plan's snippet is a starting point, not a spec.** It has six problems. Finding them is part
of the exercise; don't show this list to the teams until judging.

1. `appendLine` writes LF. RFC 5545 requires **CRLF** line endings.
2. There is no **line folding**. Content lines must fold at 75 *octets*, not characters, so a
   multi-byte character must not be split across the fold.
3. `Clock.System.now()` inside the function makes `DTSTAMP` untestable. **Pass `now: Instant` in**,
   the way `conferencePhase(now, …)` already does.
4. `Session.startInstant` and `endInstant` are **nullable**. The snippet doesn't compile as
   written. Sessions without instants must be skipped, and a test must show that.
5. **Escaping order:** backslash first, then `;` and `,`, then newlines as `\n`. `rooms` arrives
   comma-joined, so this case really happens.
6. **UIDs must stay the same across exports**, so a second import updates events instead of
   duplicating them. `remoteId@droidcon.co.ke` works; check that it is unique.

**Done when:**

- [ ] It is a pure function, `List<Session>, now: Instant → String`.
- [ ] Tests cover each of the six points, plus an empty list and one session with an emoji or a non-Latin title.
- [ ] Each test fails if its rule is broken.
- [ ] Optional: the output passes an online iCalendar validator. Paste the result in the PR.

#### Slice B — "Add to calendar" for one session

**Where:** `feature/sessions/.../sessionDetails/`, which holds the `SessionDetailsScreen` and its
components (`SessionTimeAndRoom.kt` is a natural neighbour). The intent builder goes in
`core/ui/.../common/` next to `share/ShareTextIntent.kt`, so it can be tested the way
`SessionShareTest` tests sharing.

**Watch for:**

- `Intent.ACTION_INSERT` on `CalendarContract.Events.CONTENT_URI`, with begin and end times from the instants.
- Hide or disable the action when the instants are null.
- A phone might have no calendar app. Catch `ActivityNotFoundException` and show a message. Don't use `resolveActivity`, which needs a `<queries>` entry on API 30+.
- The plan's `VENUE_NAME` does not exist. Use the session's rooms. Pulling the venue in from the event is a stretch goal.
- The plan's `CalendarContract.Reminders.MINUTES` extra is ignored by many calendar apps. Check it on at least two before relying on it.
- Strings in resources, colours and shapes from chai, and an icon with a content description.

**Done when:**

- [ ] A unit test asserts the intent's action, data and extras.
- [ ] Screenshot goldens re-recorded (`./gradlew recordRoborazziDebug --rerun-tasks`) for light, dark and 200%.
- [ ] Tested on a phone and on a tablet two-pane layout.

#### Slice C — "Export my agenda" (`.ics` via FileProvider)

**Where:** the Sessions screen app bar in `feature/sessions`, plus a new `FileProvider`. **The app
has none yet**, so this adds a `<provider>` in `app`'s manifest and an `xml/file_paths.xml` that
exposes only a `cache/agenda/` folder.

**Watch for:**

- Write the file off the main thread using the injected dispatchers.
- Share with `ACTION_SEND` or `ACTION_VIEW`, type `text/calendar`, and `FLAG_GRANT_READ_URI_PERMISSION`.
- Show the action only when at least one session is starred.
- Depends on slice A. Until it lands, stub it behind the same signature.

**Done when:**

- [ ] A test for the share intent.
- [ ] Manual import into Google Calendar and one other calendar app, with screenshots in the PR.
- [ ] Goldens updated.

#### Slice D — Quality and docs (good for mixed-experience teams)

- An accessibility check on the new actions (TalkBack labels, 48 dp targets). Plan §5.6 has the `enableAccessibilityChecks()` pattern.
- `README.md`'s feature paragraph and `docs/architecture.md` updated.
- **Delete §11.4a from `docs/IMPROVEMENT_PLAN.md` once it lands.** The plan records only what is left.

### 3.4 Team layout by headcount

| People | Layout |
| --- | --- |
| ≤ 12 | Three teams, one per mode, each building **slice A**. One shared Centaur team does B and C afterwards |
| 13–30 | A × 3 modes, B × 2 modes (Mkono, Centaur), C × 1 (Centaur), D × 1 |
| 30+ | The full matrix: A, B and C × 3 modes, plus D. Mentors float |

Merge the best implementation of each slice into **one PR**, "Add sessions to your calendar".

### 3.5 Rules of the bash

1. **Fork, then branch** as `bash/<team>/<slice>`, and open a PR to `main` with the `feature-bash` label. The repo's `CONTRIBUTING.md` describes the process.
2. **Same gates as every PR.** CI runs the whole suite on every PR. Locally:

   ```bash
   ./gradlew spotlessApply ktlintFormat
   ./gradlew spotlessCheck ktlintCheck detekt
   ./gradlew :core:domain:testDebugUnitTest          # slice A loop, fast
   ./gradlew :feature:sessions:testDebugUnitTest     # slices B and C
   ./gradlew verifyRoborazziDebug                    # if UI changed
   ```

3. **Declare your mode** in the PR description, and name the agent if there is one.
4. **Agent teams keep their prompts.** Paste them, or a summary, into the PR. They are the raw
   material for the closing discussion.
5. **Point the agent at `AGENTS.md` and this slice's brief.** That is the experiment: is the repo's
   written knowledge enough for an agent?
6. **Explain every line.** Judges pick any three lines of the diff and ask why.
7. **Tests must fail without the change** (§16.4.2). Show it: revert the code and run the test.

### 3.6 Judging (100 points)

| Criterion | Points | How it is checked |
| --- | --- | --- |
| **Works** | 25 | Demo on a device, or the test suite for slice A |
| **Tests that bite** | 20 | Revert the code and the tests fail; edge cases from the brief are covered |
| **Fits the codebase** | 15 | Follows the code around it: no hardcoded strings, colours or sizes; chai; injected dispatchers; module rules in `AGENTS.md` |
| **Definition of done** | 15 | §16.4: dark mode, 200% font, tablet; goldens updated |
| **Explain your diff** | 15 | Three random lines, three good answers |
| **Green CI** | 10 | Passing at demo time |

Also record, per team, for the closing discussion: **time to first green local test run**, **lines
changed**, and **review comments from the mentors**.

### 3.7 Closing: what the agents taught us (15 minutes)

Compare the modes side by side on the projector. Ask:

- Which of the six slice-A traps did each mode catch? Did any agent copy the plan's snippet as written?
- Where did `AGENTS.md` help, and where was an agent (or a person) missing something it didn't say?
- How did the diffs compare on size and readability?

**Output:** a PR that updates `AGENTS.md` with whatever was missing. That is a concrete thing the
meetup gives back to every future contributor, human or agent. It is also a short write-up for the
droidcon talk or blog: *"Humans, centaurs and agents on a real Android codebase."*

### 3.8 Stretch and fallback list

For teams that finish early, or if the room wants a second feature. None of these collides with
#448 or needs the backend.

| Item | Plan | Size | Why it fits a bash |
| --- | --- | --- | --- |
| Cube: drag to spin, tap to twist | §12.2.1 | M | Compose gestures and 3D maths on About. Also gives the WCAG pause control §12 asks for. The crowd favourite |
| Live region on the Now/Next rail, plus an automated a11y check | §5.6 | S | One `semantics` line and a test. Pairs with the bounty's a11y finds |
| Regression tests for B6 (switch survives recreation) and B10 (immutable `NavKey`s) | §3.9 | S–M | Pure test work. The plan marks these as protecting user data |
| Edge-case fake data plus screenshots (200-character titles, eight speakers, non-Latin) | §10.2 | S | Finds layout bugs. Feeds the bounty |
| List-detail selected state for session and speaker cards | §4.4 | S | The plan's snippet is nearly complete |
| Widget refreshes only on conference days | §11.7 | S | Saves battery all year |
| Unread dot on the Updates tab | §6A.5 | S | Small, visible, DataStore |
| `SyncDataWorker` test with WorkManager's `TestDriver` | §3.10.4 | S | Testing practice in the Now in Android style |
| A dependency-review job in CI | §15.1 | S | For the build and CI people in the room |
| Open the ticket PDF in the app (`PdfRenderer` + barcode) | §7.3 | M–L | Removes the typing, the commonest path. Ticketing is on `main` (#453), so it can start |

**Stay away from:** notifications and deep links (#448), anything that needs the backend (§3.10.2–3,
Google Wallet), and the AI features (§6), which the plan deliberately puts last.

---

## Part 4 — The slide deck

Every slide follows the same shape, so the deck can be built in any tool (or generated):

- **On screen:** the words that go on the slide. Keep them short.
- **Visual:** what to show. Chart data is in Appendix D; images to collect are in Appendix E.
- **Say:** the speaker notes.
- **Source:** where the claim comes from, in small type on the slide or in the notes.

53 slides: 31 for the talk, 10 for the bounty, 10 for the bash and 2 to close. Slides marked
*(optional)* can go if time is short. Slides marked *(fill on the day)* get their content during
the event.

### Opening

#### S01 · Title

**On screen**
- From I/O clone to our own app: eight years of the droidcon Kenya app
- Android254 × Kotlin Kenya · pre-droidcon meetup · Saturday 24 October 2026

**Visual:** this year's cube hero on the 2026 brand background.

**Say:** Welcome. Thank both communities and the hosts. Three things today: a story, a bug hunt,
and a build.

#### S02 · Today

**On screen**
- 10:05 The talk
- 10:55 Msako wa Mende: the bug bounty
- 13:05 Feature bash: Mkono, Centaur, Wakala
- 15:35 Results and prizes
- Wi-Fi · app QR · repo QR

**Visual:** three QR codes: the Play listing, the APK fallback, and `github.com/droidconKE/droidconKeKotlin`.

**Say:** Install or update the app now; you'll need it in an hour. If you have a ticket and didn't
add it at home, wait and use mobile data. The lookup is rate limited per Wi-Fi address.

### Act 0 — Cold open

#### S03 · Same app. Eight years.

**On screen**
- Same app. Eight years. Five repos.
- ~2,100 commits

**Visual:** the 2018 schedule screenshot next to this year's Home, side by side, the same size.

**Say:** Both of these are the droidcon Kenya app. The one on the left began as a copy of somebody
else's app. Everything between them is in git, and we read all of it.

**Source:** `git rev-list --count HEAD` in each of the five app repos (Appendix D, D3).

#### S04 · The family tree

**On screen**
- 2018 `droidconKE` → 2019 `droidconKE2019App` → 2020 `droidconKE2020App` → 2022 `droidconKE2022Android` → 2023– `droidconKeKotlin`
- Side branches: `chai` (2023), `droidconKeKMP` (2025)
- Same app, other stacks: Flutter ×3, React Native ×2

**Visual:** a horizontal timeline, one box per repo with its star count. Draw a gap at 2021 and
dashed lines for the Flutter and React Native siblings.

**Say:** A new repo almost every year until 2023. After that, one repo with no year in its name.
Keep the gap in 2021 in mind; we come back to it.

**Source:** `gh repo list droidconKE` (D3).

#### S05 · We code in seasons

**On screen**
- Commits come before the conference
- 2018: 78 in May · 2020: 159 in April · 2022: 212 in July · 2026: 79 in August

**Visual:** a column chart of commits per month in `droidconKeKotlin`, Feb 2023 to Oct 2026 (D1).
Mark each conference.

**Say:** It's a volunteer project, and volunteers work to a deadline. The quiet stretches matter as
much as the spikes. Hold on to that for the end of the talk.

**Source:** `git log --format=%ad --date=format:%Y-%m | sort | uniq -c` (D1, D2).

### Act 1 — 2018

#### S06 · 2018: the first one

**On screen**
- Started in **Java**, with a Kotlin branch alongside
- Firebase for everything: Auth, Realtime DB → Firestore, FCM, Remote Config, Crashlytics
- Cloud Functions pushed notifications for starred sessions
- A map pointing at iHub
- ButterKnife · Koin · Room + RxJava · minSdk 21

**Visual:** bars of Java against Kotlin files: May 2018 69 Java · Dec 2018 108 Java · Apr 2019 107
Kotlin, 2 Java (D6).

**Say:** Kotlin had been official on Android for a year, and this app still started in Java. A
Kotlin branch ran beside it from August and took over by April 2019. Real migrations look like that.

**Source:** `droidconKE` git history: "kotlin branch" (2018-08-22), "refactored codebase to kotlin" (2018-08-23).

#### S07 · Born as a clone of Google's I/O app

**This is the highlight of act 1.** Give it time.

**On screen**
- 24 March 2018: droidconKE forks `google/iosched`
- 7 May 2018, day one: "added event, travel and info fragments"
- `fragment_info` = `fragment_info` · `fragment_event` = `fragment_event` · `fragment_travel` = `fragment_travel`
- "…sessions, office hours, app reviews, codelabs…" (droidconKE README, 2018)

**Visual:** split screen. Left: the I/O 2018 app (the screenshot in the fork's README). Right:
droidconKE 2018 (`schedule.png`). Under them, the screen-name table from 1.2.

**Say:** The first droidcon Kenya app was a clone of Google's I/O app. The screens had the same
names, the tabs were the same (schedule, info and map), and the README used the same words, down
to office hours and app reviews Nairobi never had. That was the right way to start: copy the best
app you can find and learn from it. The rest of this talk is the app slowly becoming its own.
For Kotlin Kenya: the I/O code was already Kotlin. We cloned its screens in Java anyway, then
followed it to Kotlin after pulling in the I/O 2018 code that August.

**Source:** `droidconKE/iosched` (a fork of `google/iosched`), its commits of 2018-03-28 and
2018-08-13; the first commits of `droidconKE`; both repos' `res/layout/`; both READMEs.

### Act 2 — 2019

#### S08 · 2019: all Kotlin, all Jetpack

**On screen**
- 100% Kotlin from day one
- Jetpack Navigation + Safe Args · Firestore KTX · Koin · LiveData · Firebase Performance
- Filters, announcements, session feedback, venue map
- Package: `com.android254.droidconke19`
- Travis CI

**Visual:** the 2019 `schedule.png` and `sesion_details.png` from the repo.

**Say:** The community's name went into the package that year, and it is still there:
`com.android254`. Still one module. The README promised phones *and tablets*. Remember that promise.

**Source:** `droidconKE2019App`: 153 commits, Jul–Nov 2019.

### Act 3 — 2020

#### S09 · 2020: the modular one

**On screen**
- 13 modules, 7 of them **dynamic features**
- Kotlin DSL · `buildSrc` · ktlint · detekt · GitHub Actions
- Retrofit · Room · Coil · data binding · MockK
- 15 authors · **120 stars**, the most in the org

**Visual:** the `include(...)` block from `settings.gradle.kts`, with `:features:*` highlighted. The
repo also has an architecture diagram (`Droid Con Architectire.png`).

**Say:** The biggest jump in ambition: modules, static analysis, CI. Dynamic features were the
modularisation advice of the time. Nobody uses them for this now, but the team learned modules here.
On the I/O thread: this is the first README without the I/O app's words, and the first
architecture the team designed for itself.

**Source:** `droidconKE2020App` `settings.gradle.kts`, `buildSrc/.../Dependencies.kt`.

#### S10 · The conference that didn't happen

**On screen**
- "…the 3rd Android Developer conference- droidcon to be held in Nairobi from August 6-8th 2020."
- "…the 3rd Android Developer conference- droidcon to be held in Nairobi from November 16-18th 2022."

**Visual:** the two README lines stacked. Nothing else.

**Say:** Both apps call themselves the third droidcon. **Hand the mic to a guest from 2020** for 90
seconds: what building an app for a cancelled conference was like.

**Source:** the `droidconKE2020App` and `droidconKE2022Android` READMEs.

### Act 4 — 2022

#### S11 · 2022: Compose arrives

**On screen**
- 622 commits in 5 months · ~two dozen volunteers
- Jetpack Compose · Material 3 (alpha) · Hilt · Ktor · Room + Paging
- Version catalog · spotless · fastlane → Play Store
- 2022-11-15: `feat: Disable auth`. The conference opened 2022-11-16

**Visual:** the D2 chart for 2022: 212 commits in July alone.

**Say:** Compose 1.0 came out in July 2021. A year later a volunteer team shipped a Compose app to
the Play Store. It was built on alpha Material 3, which took some nerve. And a lesson: sign-in was
switched off the day before the conference. Ship what works.

**Source:** `droidconKE2022Android` `gradle/libs.versions.toml`; commit "Deploy to playstore" (2022-11-17).

#### S12 · Standing on its own: chai

**On screen**
- Its own designs: light and dark, in Adobe XD
- Its own design system: **chai**. "Chai is swahili word for tea."
- Born in Aug 2022 · its own repo in Mar 2023 · still `com.droidconke.chai` today
- 2018 · I/O's screens → 2020 · its own structure → **2022 · its own look**

**Visual:** chai's cover image (`droidconKE/iconPack`) next to the S07 I/O screenshot, so the
change is obvious.

**Say:** Remember the I/O clone from 2018? This is the year it stopped looking like anyone else's
app. The team had its own designs and named its design system after tea. chai is still the
app's `core:designsystem` four years later.

**Source:** the "Designs" section of the `droidconKE2022Android` README; the chai README.

### Act 5 — 2023 to 2025

#### S13 · 2023: one repo to keep

**On screen**
- `droidconKeKotlin`, with no year in its name
- Day 3: **280 files, 15,888 lines** from the 2022 app
- 173 of 177 Kotlin files kept their path · 136 byte-for-byte identical
- Not a rewrite. A carry-forward

**Visual:** the terminal: `git show --stat f3b81067 | tail -1`.

**Say:** Every earlier year started a fresh repo, and every fresh repo reset the contributor list.
In 2023 the code moved over and stayed.

**Source:** `git show --stat f3b81067`; a file-by-file comparison with `droidconKE2022Android`.

#### S14 · 2023: the community peak

**On screen**
- 326 commits · ~41 people
- Convention plugins · StateFlow · offline first · kapt → KSP · Chucker · push notifications

**Visual:** a wall of the 2023 author names, generated by
`git log --since=2023-01-01 --until=2023-12-31 --format=%an | sort -u`. Leave out the bots.

**Say:** The busiest year for people. Name a few: Hannah Olukoye's convention plugins, Peter
Chege's offline-first work, Kenneth Mathari's injected dispatchers, robert-nganga's loading states.

**Source:** `git shortlog` for 2023.

#### S15 · 2024: the quiet year

**On screen**
- 55 commits
- `Feature: Droidcon 2024 (#305)`: 3 files, 3 lines
- K2 and the Compose compiler plugin · coverage on CI

**Visual:** the 3-line diff (the event slug), full screen.

**Say:** Quiet years happen in volunteer projects, and that's fine. What matters is whether the
next person can pick it up. That question drives everything in 2026.

**Source:** `git show --stat` of #305.

#### S16 · Kotlin Kenya corner: the KMP thread

**On screen**
- May 2024: KMM set up
- Jun 2025: Compose Multiplatform + iOS · 14 modules
- Oct 2025: removed (−2,111 lines) · moved to `droidconKeKMP`
- Today: `core:model` is JVM-only, so a third try is **a port, not a rewrite**

**Visual:** the module-count line, 7 → 11 → 14 → 7 → 20 (D8), with the KMP stretch shaded.

**Say:** Tried twice and taken out twice to keep the Android app shippable. That isn't failure.
The model layer is now ready, and the plan asks one question: does someone want to build the iOS app?

**Source:** git log; `docs/IMPROVEMENT_PLAN.md` §17.2 question 10.

#### S17 · Late 2025: Navigation 3

**On screen**
- Dec 2025: Navigation 3, multiple back stacks
- 2026: animated transitions · MVI-style intents · live "Now / Up next" sessions

**Visual:** a screen recording of switching tabs with the back stacks kept, and the Now/Next rail.

**Say:** donald-okara carried the app through the winter. Most of what the app now does with live
sessions started here.

**Source:** git log, Dec 2025 to Aug 2026.

### Act 6 — 2026

#### S18 · Written for two readers

**On screen**
- 2026-08-13: `AGENTS.md`
- "Working notes for AI agents and new contributors."
- Plus a 3,500-line improvement plan that deletes what's done

**Visual:** the top of `AGENTS.md`.

**Say:** The fix for quiet years: write down what you know. The same file now briefs a new
contributor and a coding agent. This afternoon we test whether that works.

**Source:** `AGENTS.md`; `docs/IMPROVEMENT_PLAN.md`.

#### S19 · Seven modules to twenty

**On screen**
- `app` · 7 features · 10 core modules · `widget` · `benchmarks`
- Features never depend on features
- `core:model` has no Android in it

**Visual:** the module graph (the mermaid diagram in `README.md`).

**Say:** The rules matter more than the count. One rule was learned the hard way: a feature's
tests reached into another feature's test fakes, and that is why `core:testing` exists.

**Source:** `README.md`, `AGENTS.md` (module layout, dependency rules).

#### S20 · Measured, not asserted

**On screen**
- Cold start **−22%**: 1,335 → 1,047 ms
- Primary dex **−36%**
- Release APK **−28%**: 6.4 → 4.6 MB
- Download **−7.4%**, enforced in CI
- Full AOT was *slower* (1,228 ms)

**Visual:** the four startup modes as bars (D7).

**Say:** Every number has a device, a median and a caveat. The doc also keeps the experiments that
didn't help, like full AOT. That habit is the most useful thing to copy from this year.

**Source:** `docs/performance.md`.

#### S21 · A new look

**On screen**
- Material 3 Expressive + the 2026 droidcon rebrand
- Phones, foldables, tablets, desktop windows
- Shared-element transitions · redesigned speakers
- WCAG AA contrast tested for every colour pair

**Visual:** the four form-factor goldens for the sessions screen: phone, foldable, tablet and
desktop (Appendix E).

**Say:** The tablet promise from 2018 is finally kept. Credit Joel Kanyi for the shared elements
and mutukuian for the speakers redesign. On the I/O thread: by now there is nothing of the I/O
app left on screen. The brand, the motion and the cube are all droidcon Kenya's own.

**Source:** #410, #421, #425; git log.

#### S22 · The cube

**On screen**
- A 3D Rubik's cube hero, drawn on a Compose `Canvas`, spinning
- Spec, Plan and Code chips beside the cube

**Visual:** the cube spinning, with the Spec / Plan / Code chips.

**Say:** The cube is pure play, and the plan has a list of easter eggs it could grow. The chips
name the order the work went in: spec first, then plan, then code. That is a good habit, with or
without an agent.

**Source:** #446.

#### S23 · An app that follows the conference

**On screen**
- **Before:** a countdown
- **During:** today's agenda, with clashes named
- **After:** thank you, and a feedback link
- Updates: schedule changes and organiser news in one timeline
- Your ticket, at the door

**Visual:** three Home screenshots side by side, one per phase. The goldens `home_before`,
`home_agenda` and `home_after` are ready-made (Appendix E).

**Say:** The app reads the clock and the schedule and changes with the event. Let's see it.

**Source:** `core/domain/.../schedule/Schedule.kt`; #451, #453.

#### S25 · The stack, layer by layer *(optional)*

**On screen:** a condensed version of the 1.3 table with five rows: UI, DI, Data, Build, Tests.

| | 2018 | 2020 | 2022 | 2026 |
| --- | --- | --- | --- | --- |
| UI | XML + ButterKnife | XML + data binding | Compose + M3 alpha | Compose + M3 Expressive, adaptive |
| DI | Koin | Koin | Hilt | Hilt |
| Data | Firebase | Retrofit + Room | Ktor + Room | Offline first + sync |
| Build | Groovy | Kotlin DSL + buildSrc | Version catalog | Convention plugins, AGP 9 |
| Tests | Espresso | MockK, Kakao | Compose UI tests | Roborazzi, Macrobenchmark |

**Say:** Read across a row and you get Android's history.

#### S26 · How the code grew

**On screen**
- 2023: 14.7k lines · 24 test files · 0 screenshots
- Now: 39.9k lines · 110 test files · 180 screenshots

**Visual:** a line chart from D4 with two lines, Kotlin LOC and test files. Show screenshot goldens
as bars.

**Say:** Flat for three years, then a step. The tests grew faster than the code.

**Source:** snapshots from `git ls-tree` (D4).

### Act 7 — What it means

#### S27 · Lost and found

**On screen**
- Starred-session pushes · 2018 → **#448** (open)
- Venue map · 2018–19 → **§11.6** (needs an SVG)
- Phones *and tablets* · promised 2018 → **kept 2026**
- Travel details · 2018–19 → gone. Should it come back?

**Visual:** a two-column table, "then" and "now".

**Say:** Some of the best ideas are already in the old repos. Ask the room about travel details.

**Source:** package lists of the 2018 and 2019 repos; plan §11.6; #410; #448.

#### S28 · The uncomfortable chart

**On screen**
- People committing per year: **41 → 8 → 9 → 5**
- The code grew. The community didn't.

**Visual:** D5 as bars, next to the LOC line from S26.

**Say:** The year with the most code had the fewest people. That's not a boast; it's a risk.
This afternoon is how we change it.

**Source:** distinct author names per year, bots excluded (D5).

#### S29 · What eight years teach

**On screen**
1. Copy the best, then stand on your own
2. Repos with a year in the name reset the community
3. Removing an experiment is still progress
4. Write it down for whoever comes next, human or agent

**Visual:** four lines, nothing else. Optionally, a thumbnail of the 2018 I/O-clone screen next to
point 1.

**Say:** One sentence each. Point 1 closes the I/O thread: we started as a clone of Google's app
and became our own, and that is a good way to begin anything. Point 4 sets up the bash.

#### S30 · Thank you

**On screen:** the contributors, grouped by era.

- 2018–19: Harun Wangereka, Michael Bukachi, muhosteve, Victor, johnGachihi
- 2020: Oliver Muthomi, Benard Mathu, David Odari, Jabez Magomere, philomenambura, Jama Mohamed
- 2022: Gibson Silali, Waitara, Theophilus Kibet, Evans Chepsiror, Juma Allan, tamzi
- 2023–25: Hannah Olukoye, Peter Chege, Kenneth Mathari, Yves Kalume, robert-nganga, Raynafs, Michael Ndiritu, MamboBryan, brian.orwe
- 2026: donald-okara, Joel Kanyi, mutukuian

**Visual:** GitHub avatars if you have time (the README's contributors section has them).

**Say:** Ask anyone from the list who is in the room to stand up.

**Source:** `git shortlog -sn` per repo. Check the list against the README before the day.

#### S31 · Now, let's break it

**On screen**
- Msako wa Mende starts in 10 minutes
- Phone ready · app updated · GitHub account open

**Visual:** a cockroach emoji, large. 🪳

**Say:** Hand over to the bounty lead.

### Bug bounty briefing

#### S32 · Msako wa Mende

**On screen**
- 60 minutes. Teams of three.
- Find bugs. File them. Earn *bob*.
- *msako* = crackdown · *mende* = cockroach

**Visual:** the Mende Board, empty, as it will look on the projector.

**Say:** Teams of three: one drives the phone, one writes the issue, one hunts edge cases. Mix
experience levels.

#### S33 · Why it matters

**On screen**
- The plan's next item needs **people, not code**
- TalkBack · Switch Access · keyboard · two panes
- A room of 40 phones *is* that test pass

**Visual:** a quote from plan §14.1: "What is left needs a person."

**Say:** Accessibility finds score as high as broken features, on purpose.

**Source:** `docs/IMPROVEMENT_PLAN.md` §14.1, §16.4.

#### S34 · Scope

**On screen**
- ✅ The app, on your phone: every screen, offline, sync, layout, accessibility
- ❌ The backend, the API, Firebase · no scripts, no load tests
- ❌ Anyone else's ticket
- 🔒 Security issues: tell an organiser, don't post them

**Visual:** two columns, green and red.

**Say:** The lookup is rate limited and shared across the venue Wi-Fi. Use mobile data for ticket
quests.

#### S35 · The Mende Board

**On screen**

| | Bob |
| --- | --- |
| 🪳 Crash, ANR, data loss | 50 |
| ♿ Accessibility | 30 |
| 💔 Broken feature | 30 |
| 📐 Layout, theming | 15 |
| ✏️ Copy, polish | 5 |
| 🔒 Security, private | 50 |

**Say:** The first report wins, ordered by issue number. Triage decides severity.

#### S36 · Bonuses and bingo

**On screen**
- +10 video · +5 confirmed on another device · ×2 a failing test · +50 a fix PR
- Bingo: a full line +20 · the whole card +100

**Visual:** the 3×3 bingo card from 2.5.

#### S37 · Quests

**On screen**
1. Time traveller
2. Process death
3. Off the grid
4. Big text, small phone
5. Two panes
6. At the door
7. Listen to it
8. The widget

**Visual:** eight tiles, one icon each. A printed sheet per team has the steps from 2.6.

#### S38 · Already known: don't file these

**On screen:** the ten known issues from 2.3, one line each.

**Say:** These score zero. Look elsewhere.

#### S39 · How to file

**On screen**
- New issue → "Bounty report (meetup)"
- One bug per issue · steps · device · screenshot or video
- Label: `bounty`

**Visual:** a QR code to `github.com/droidconKE/droidconKeKotlin/issues/new?template=bounty.md`.

#### S40 · Leaderboard *(fill on the day)*

**Visual:** the live Mende Board (the `gh` loop in 2.7, or the spreadsheet).

#### S41 · Results *(fill on the day)*

**On screen:** Mende Mkuu · Best accessibility find · Best crash · First-timer · Bingo blackout.

**Visual:** the winning issue for each prize.

### Feature bash briefing

#### S42 · One feature, three ways

**On screen**
- **Mkono** (hand): humans only
- **Centaur**: humans and an agent together
- **Wakala** (agent): the agent writes all the code; humans prompt and review
- Everyone explains every line

**Visual:** three columns: a hand, a centaur, a robot.

**Say:** This is an experiment, not a race between people and machines. We want to learn what
our repo teaches an agent, and what it doesn't.

#### S43 · The feature: add to calendar

**On screen**
- "Add to calendar" on any session
- "Export my agenda" as one `.ics` file
- The plan calls it its best value for effort
- No backend · not started · shipping before droidcon

**Visual:** a mock-up of the two actions.

**Source:** plan §11.4a.

#### S44 · The slices

**On screen**
- **A:** `buildIcs()`: pure Kotlin, no phone needed
- **B:** "Add to calendar" for one session
- **C:** "Export my agenda" through a `FileProvider`
- **D:** accessibility, screenshots, docs

**Visual:** four cards, with the layout for the room's headcount (3.4).

**Say:** Kotlin Kenya, slice A is yours.

#### S45 · Slice A: find the six bugs

**On screen**
- This is the plan's sketch
- It has six bugs
- Your tests should catch all of them

**Visual:** the `buildIcs` snippet from plan §11.4a, as is.

**Say:** Don't trust the plan, test it. This is the one slide agent teams should look at hard. Do
they copy it as written? (Don't reveal the list until S51.)

#### S46 · Rules

**On screen**
- Fork · branch `bash/<team>/<slice>` · label `feature-bash`
- Declare your mode in the PR
- Agent teams keep their prompts
- Tests must fail without your change

**Say:** Point your agent at `AGENTS.md` and the slice brief. That's the whole setup.

#### S47 · The gates

**On screen**

```bash
./gradlew spotlessApply ktlintFormat
./gradlew spotlessCheck ktlintCheck detekt
./gradlew :core:domain:testDebugUnitTest
./gradlew :feature:sessions:testDebugUnitTest
./gradlew verifyRoborazziDebug
```

#### S48 · Judging

**On screen:** Works 25 · Tests that bite 20 · Fits the codebase 15 · Definition of done 15 · Explain your diff 15 · Green CI 10

**Say:** We'll also time each team to its first green test run and count the lines changed.

#### S49 · Demos *(fill on the day)*

**On screen:** the team order, with a 3-minute timer.

#### S50 · What the agents taught us *(fill on the day)*

**On screen**

| | Mkono | Centaur | Wakala |
| --- | --- | --- | --- |
| Bugs caught (of 6) | | | |
| Time to first green test | | | |
| Lines changed | | | |
| Review comments | | | |

**Say:** Discuss where `AGENTS.md` helped and where it was silent. Those gaps become the
follow-up PR.

#### S51 · The six bugs, revealed

**On screen**
1. LF instead of CRLF
2. No folding at 75 octets
3. `Clock.System.now()` inside the function
4. Nullable instants
5. Escaping order
6. Stable UIDs

**Say:** Who caught all six, and in which mode?

### Close

#### S52 · What ships before droidcon

**On screen**
- The merged "Add sessions to your calendar" PR → next release
- The confirmed bounty bugs → `good first issue`
- The `AGENTS.md` update from today

**Say:** Name the owner for each, and a date.

#### S53 · Thank you

**On screen**
- See you at droidcon Kenya
- Repo · the app · Android254 · Kotlin Kenya

**Visual:** QR codes, and the cube.

---

## Appendix A — Bounty issue template

Save as `.github/ISSUE_TEMPLATE/bounty.md`.

```markdown
---
name: Bounty report (meetup)
about: A bug found during the Msako wa Mende bug bounty
title: "[Bounty] "
labels: bounty
---

**Category:** crash / accessibility / broken feature / layout / copy

**What happened**
One or two sentences.

**What you expected**

**Steps to reproduce**
1.
2.
3.

**Device and settings**
- Phone and Android version:
- App version (Play listing or Settings → Apps):
- Dark mode / font scale / display size / TalkBack / orientation / tablet:
- Online or offline:

**Evidence**
Screenshot or screen recording. For network bugs on a debug build, attach the Chucker export.

**Team:**
```

---

## Appendix B — Score sheets

**Mende Board (triage)**

| Issue | Team | Category | Bob | Bonuses | Total | Triage note |
| --- | --- | --- | --- | --- | --- | --- |
| #… | | | | | | |

**Feature bash (judges)**

| Team | Mode | Slice | Works /25 | Tests /20 | Fits /15 | DoD /15 | Explain /15 | CI /10 | Total | Time to green | Lines | Review comments |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| | | | | | | | | | | | | |

---

## Appendix C — Reproducing the numbers

```bash
# This repo
git rev-list --count HEAD                                        # 686
git log --format='%ad' --date=format:%Y | sort | uniq -c         # per year
git log --format='%ad' --date=format:%Y-%m | sort | uniq -c      # per month
git shortlog -sn --no-merges HEAD | head -20                     # top authors
git show --stat f3b81067 | tail -1                               # 280 files, 15,888 lines
gh pr list -R droidconKE/droidconKeKotlin --state merged --limit 1000 --json number --jq length

# The older repos
gh repo list droidconKE --limit 100 --json name,createdAt,stargazerCount,primaryLanguage
git clone https://github.com/droidconKE/droidconKE2020App && cd droidconKE2020App
git rev-list --count HEAD; git shortlog -sn --no-merges HEAD | wc -l

# A snapshot on any date
c=$(git rev-list -1 --before=2026-08-01 HEAD)
git ls-tree -r --name-only $c | grep -c '\.kt$'
```

Sources inside the repo: `docs/IMPROVEMENT_PLAN.md` (§7, §11.4, §12, §14, §16, §17),
`docs/performance.md` (startup, size), `docs/architecture.md`, `AGENTS.md`, and open PR #448.

---

## Appendix D — Chart data

Comma-separated, ready to paste into a spreadsheet or a charting tool.

### D1 · `droidconKeKotlin` commits per month (S05)

Includes bot commits. Months with no commits are left out; plot them as zero.

```csv
month,commits
2023-02,44
2023-03,40
2023-04,25
2023-05,14
2023-06,27
2023-07,11
2023-08,14
2023-09,54
2023-10,62
2023-11,35
2024-03,2
2024-05,7
2024-06,40
2024-11,6
2025-06,54
2025-10,2
2025-11,7
2025-12,12
2026-01,6
2026-02,5
2026-03,7
2026-04,24
2026-05,4
2026-08,79
2026-09,38
2026-10,67
```

### D2 · The older repos, commits per month (S05, S11)

```csv
repo,month,commits
droidconKE,2018-05,78
droidconKE,2018-06,16
droidconKE,2018-07,16
droidconKE,2018-08,7
droidconKE,2018-09,13
droidconKE,2018-10,12
droidconKE,2018-12,20
droidconKE2019App,2019-07,62
droidconKE2019App,2019-08,46
droidconKE2019App,2019-10,16
droidconKE2019App,2019-11,29
droidconKE2020App,2019-11,4
droidconKE2020App,2020-01,7
droidconKE2020App,2020-02,39
droidconKE2020App,2020-03,83
droidconKE2020App,2020-04,159
droidconKE2020App,2020-05,36
droidconKE2020App,2020-06,9
droidconKE2020App,2020-07,3
droidconKE2020App,2020-10,2
droidconKE2020App,2020-11,51
droidconKE2022Android,2022-07,212
droidconKE2022Android,2022-08,115
droidconKE2022Android,2022-09,94
droidconKE2022Android,2022-10,121
droidconKE2022Android,2022-11,80
```

### D3 · The repos (S03, S04)

```csv
repo,first_year,commits,authors_incl_bots,kotlin_files,kotlin_loc,stars,forks
droidconKE,2018,230,6,82,3764,21,23
droidconKE2019App,2019,153,6,96,4766,16,15
droidconKE2020App,2019,393,15,122,5056,120,40
droidconKE2022Android,2022,622,25,180,14418,84,56
droidconKeKotlin,2023,686,58,460,39850,116,94
chai,2023,207,7,27,1529,9,4
droidconKeKMP,2025,1,1,6,123,2,0
```

### D4 · `droidconKeKotlin` growth (S26)

```csv
date,modules,kotlin_files,kotlin_loc,test_files,screenshot_goldens
2023-03-01,5,182,14699,24,0
2023-12-01,7,260,17046,25,0
2024-12-01,11,270,17392,25,0
2025-07-01,14,271,17322,25,0
2026-08-01,7,272,18774,27,0
2026-09-15,18,323,24804,51,51
2026-10-01,18,345,27842,62,92
2026-10-22,20,460,39850,110,180
```

### D5 · People committing per year, bots excluded (S28)

Distinct author names. Some people commit under two names, so these are approximate.

```csv
year,authors
2023,41
2024,8
2025,9
2026,5
```

### D6 · 2018: Java to Kotlin (S06)

```csv
date,java_files,kotlin_files
2018-05-11,22,0
2018-05-30,69,0
2018-07-03,92,0
2018-09-29,100,0
2018-12-16,108,0
2019-04-30,2,107
2019-06-05,1,85
```

### D7 · Performance (S20)

Cold start on the CS50C, API 34, median of 15 runs, in ms.

```csv
mode,median_ms
No compilation,1334.9
"Warmed, no profile",953.8
Baseline profile,1046.7
Full AOT,1228.2
```

Size (bytes):

```csv
measure,before,after
Release APK (universal),6417593,4618830
Primary dex,6067100,3898064
```

Download size over time (Pixel-class, bytes), from `docs/performance.md`:

```csv
date,build,download,install
2026-09-03,end of Phase 0,5205416,8553954
2026-10-12,before the size pass,4678844,8708023
2026-10-12,size pass,4334173,8305630
2026-10-20,2026 polish and Updates,4387684,8416243
2026-10-21,tickets,4498576,8670701
```

### D8 · Module count over time (S16, S19)

```csv
date,modules,note
2023-02-12,1,initial commit
2023-03-01,5,app chai data domain presentation
2023-12-01,7,+ datasource local and remote
2024-12-01,11,KMM shared modules
2025-07-01,14,Compose Multiplatform + iOS
2026-08-01,7,KMP removed
2026-09-11,17,module split; :presentation gone
2026-10-22,20,+ feature:ticket widget benchmarks
```

### D9 · From I/O clone to its own app (S07, S12, S29)

For a timeline graphic. `stage` runs from 0 (copying) to 4 (fully its own).

```csv
date,stage,event
2018-03-24,0,The org forks google/iosched
2018-03-28,0,Gradle conflicts in the fork fixed for Android Studio 3.0.1
2018-05-07,1,droidconKE day one: agenda; event; travel and info fragments
2018-05-09,1,Schedule; info and map: the I/O app's three tabs
2018-08-13,1,The fork pulls in the I/O 2018 code
2018-08-22,1,droidconKE opens its Kotlin branch
2019-07-16,1,2019 app: the README still uses the I/O app's words
2020-02-12,2,2020 app: own README; own modular architecture
2022-08-17,3,2022 app: own designs and the chai design system
2026-10-06,4,2026 droidcon brand and Material 3 Expressive (#425)
2026-10-22,4,Ticket pass with the gate's own QR (#453)
```

---

## Appendix E — Visual assets to collect

| For | Asset | Where |
| --- | --- | --- |
| S03, S06 | 2018 schedule screenshot | `schedule.png` at the root of `droidconKE` (branch `master`) |
| S08 | 2019 screenshots | `schedule.png` and `sesion_details.png` at the root of `droidconKE2019App` |
| S09 | 2020 architecture diagram | `Droid Con Architectire.png` at the root of `droidconKE2020App` |
| S07 | The I/O app's screenshot | `schedule.png` at the root of `droidconKE/iosched` (branch `master`). Put it beside droidconKE's own `schedule.png` |
| S07 | The fork | `github.com/droidconKE/iosched` ("forked from google/iosched"), and its commits page showing "pulling io18 code" (2018-08-13) |
| S07 | The two READMEs | Both open with the same `<img … alt="Schedule screenshot">` and the same "office hours, app reviews, codelabs" line. A side-by-side crop works well |
| S12 | chai cover | `iconPack/images/chaicover.png` in `droidconKE/iconPack` |
| S12 | The 2022 designs | The Adobe XD links in the "Designs" section of this repo's `README.md` (light and dark) |
| S03, S23 | This year's Home: before, during and after | Goldens already in the repo: `feature/home/src/test/screenshots/screens/home_before`, `home_agenda`, `home_after`. Each has light, dark and 200% variants. Or take phone screenshots using the time-travel trick |
| S23 | Home with a ticket | `feature/home/src/test/screenshots/screens/home_ticket` |
| S21 | Phone, foldable, tablet, desktop | `phone.png`, `foldable.png`, `tablet.png` and `desktop.png` in any `form_factors/` folder, for example `feature/sessions/src/test/screenshots/form_factors/sessions` |
| S19 | Module graph | The mermaid diagram in `README.md`. Render it with mermaid.live |
| S22 | The cube spinning, with the Spec / Plan / Code chips | Screen recording from a phone |
| S30 | Contributor avatars | The contributors section of `README.md` |
| All | Brand | Colours and type from `core:designsystem` (chai); the 2026 droidcon identity from #425 |
