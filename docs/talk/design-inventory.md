# DroidconKE design inventory

Source: `/Users/donaldisoe/AndroidStudioProjects/droidconKeKotlin` (read-only, snapshot of `main`). The design system is the `chai` package in `core/designsystem`. All paths below are relative to that repo unless prefixed with `slides:`. `DS` = `core/designsystem/src/main/java/com/droidconke/chai`.

## 1. Tokens

### 1.1 Palette atoms (`DS/atoms/Color.kt`)

| Token | Hex | Token | Hex |
|---|---|---|---|
| ChaiBlue100 | #D6EAFF | ChaiGrey100 | #F5F5F5 |
| ChaiBlue200 | #B5DBFF | ChaiGrey150 | #EEEEEE |
| ChaiBlue300 | #83C6FF | ChaiGrey200 | #E5E7EB |
| ChaiBlue700 | #0055FF | ChaiGrey400 | #A3A3A3 |
| ChaiBlue800 | #0842C5 | ChaiGrey500 | #808080 |
| ChaiBlue900 | #0D3C9B | ChaiGrey600 | #666666 |
| ChaiGreen300 | #70FF9B | ChaiGrey800 | #2B2B29 |
| ChaiGreen500 | #00FF4F | ChaiGrey900 | #191D1D |
| ChaiGreen800 | #06752A | ChaiNavy700 | #26343F |
| ChaiCyan400 | #2EE6FF | ChaiNavy900 | #0B1117 |
| ChaiPink500 | #FF57E9 | ChaiInk | #20201E |
| ChaiPurple400 | #A66BFF | ChaiWhite / ChaiBlack | #FFFFFF / #000000 |
| ChaiYellow400 | #FFD21F | ChaiErrorLight / ChaiErrorDark / ChaiOnErrorDark | #BA1A1A / #FFB4AB / #690005 |

Identity in one line: electric blue `#0055FF` plus neon green `#00FF4F`, on white (light) or true black (dark).

### 1.2 Color schemes (`DS/colors/ChaiColorScheme.kt`)

Material 3 `lightColorScheme` / `darkColorScheme`. Comments in source: neon is never text on a light surface (1.36:1 on white), so in light it is a container with ink on it; blue text on black is 3.7:1, so dark leads with green.

| Role | Light | Dark |
|---|---|---|
| primary | #0055FF | #00FF4F |
| onPrimary | #FFFFFF | #20201E |
| primaryContainer | #D6EAFF | #0842C5 |
| onPrimaryContainer | #0D3C9B | #FFFFFF |
| inversePrimary | #83C6FF | #0055FF |
| secondary | #06752A | #70FF9B |
| onSecondary | #FFFFFF | #20201E |
| secondaryContainer | #00FF4F | #00FF4F |
| onSecondaryContainer | #20201E | #20201E |
| tertiary | #0D3C9B | #83C6FF |
| onTertiary | #FFFFFF | #0D3C9B |
| tertiaryContainer | #B5DBFF | #0D3C9B |
| onTertiaryContainer | #0D3C9B | #D6EAFF |
| background / surface | #FFFFFF | #000000 |
| onBackground / onSurface | #20201E | #FFFFFF |
| surfaceVariant | #F5F5F5 | #191D1D |
| onSurfaceVariant | #666666 | #A3A3A3 |
| surfaceContainerLowest | #FFFFFF | #000000 |
| surfaceContainerLow | #FFFFFF | #191D1D |
| surfaceContainer | #F5F5F5 | #191D1D |
| surfaceContainerHigh | #EEEEEE | #20201E |
| surfaceContainerHighest | #E5E7EB | #2B2B29 |
| inverseSurface | #20201E | #F5F5F5 |
| inverseOnSurface | #FFFFFF | #20201E |
| outline | #808080 | #808080 |
| outlineVariant | #E5E7EB | #2B2B29 |
| error | #BA1A1A | #FFB4AB |
| onError | #FFFFFF | #690005 |

### 1.3 Extras: `ChaiColors` (`DS/colors/ChaiColors.kt`)

Same values in both themes (`ChaiLightColorPalette = ChaiDarkColorPalette = ChaiBrandColors`), exposed as `MaterialTheme.chaiColorsPalette` (`DS/Theme.kt`).

| Field | Value | Use |
|---|---|---|
| heroContainerColor | #0055FF | hero panel |
| heroContentColor | #00FF4F | neon on blue panel, display/headline text only (4.1:1) |
| heroOnContainerColor | #FFFFFF | smaller text on the panel |
| contrastLightSurface | #F5F5F5 | fixed light backing for a dark logo |
| contrastDarkSurface | #191D1D | fixed dark backing for a light logo |

### 1.4 Cube colors (`DS/colors/CubeColors.kt`), same in both themes

Gap #000000, tile shadow #0B1117, tile lit #26343F, sheen #FFFFFF, edge #E5E7EB, stage #000000, stage glow #0055FF, stage title gradient [#83C6FF, #A66BFF, #FF57E9], stage text #E5E7EB.

### 1.5 Session accents (`DS/colors/SessionAccents.kt`)

`venueAccentColor(venue)`: room "Opal" gives `colorScheme.secondary`, "Sapphire" gives `colorScheme.tertiary`, unknown room gives `onSurfaceVariant`. First room of a comma-joined list wins. Depends on `@Composable` and `MaterialTheme` only.

### 1.6 Typography (`DS/atoms/ChaiTypography.kt`)

Font: Montserrat variable (`R.font.montserrat_variable`), registered per weight Thin through ExtraBold via `FontVariation.weight`. Sizes not listed inherit Material 3 defaults. Chai-sized roles set letterSpacing 0.

| Style | Weight | Size / line height |
|---|---|---|
| displayLarge / Medium / Small | Bold | M3 default |
| headlineLarge | Bold | M3 default |
| headlineMedium / Small | SemiBold | M3 default |
| titleLarge | Bold | 20 / 28 sp |
| titleMedium | Bold | 18 / 24 sp |
| titleSmall | Medium | 14 / 20 sp |
| bodyLarge | Normal | 18 / 26 sp |
| bodyMedium | Normal | 16 / 24 sp |
| bodySmall | Normal | 14 / 20 sp |
| labelLarge | SemiBold | 18 / 24 sp |
| labelMedium | Normal | 12 / 16 sp |
| labelSmall | Normal | 11 / 16 sp |

`*Emphasized` variants: one weight heavier (Normal to Medium, Medium to SemiBold, SemiBold to Bold, else ExtraBold); display and headline emphasized also get letterSpacing -0.01em.

### 1.7 Shapes and spacing

- Shapes: `DS/utils/Shape.kt` is `val ChaiShapes = Shapes()`, so Material 3 default corner radii (no custom shape tokens).
- Spacing: `DS/utils/Spacing.kt` has three spacer composables only: `BreathingSpace13` (15 dp, name and value disagree), `BreathingSpace26` (26 dp), `SeparatorSpace` (5 dp). No spacing token scale.

### 1.8 Motion

`DS/Theme.kt` wraps `MaterialExpressiveTheme(motionScheme = MotionScheme.expressive(), ...)`. Cube motion in `DS/hero/CubeMotion.kt`: hold 2600 ms (linear), travel 1300 ms with `CubicBezierEasing(0.2, 0, 0, 1)`, click pop to 1.04x over 90 ms then spring (damping 0.4, `StiffnessMediumLow`).

## 2. Assets

License column: repo `LICENSE` is Apache-2.0 and files carry DroidconKE Apache headers; no separate license is stated for the art or images, so "repo (Apache-2.0), art not separately stated".

| Path | Format | Size | License | Verdict |
|---|---|---|---|---|
| `core/ui/src/main/res/drawable/droidcon_logo.xml` | Android vector XML (viewport 1246x246; black `#000000` + green `#01FF4F`) | 4.7 KB | repo | convert to SVG/ImageVector |
| `core/ui/src/main/res/drawable/droidcon_logo_dark.xml` | Android vector XML (same paths; white `#FFFFFF` + green) | 4.7 KB | repo | convert to SVG/ImageVector |
| `app/src/main/res/drawable/ic_launcher_foreground.xml` | Android vector XML (108 dp adaptive, white + `#01FF4F`) | 4.9 KB | repo | convert to SVG/ImageVector |
| `app/src/main/res/drawable/ic_launcher_monochrome.xml` | Android vector XML | 4.9 KB | repo | skip (monochrome themed-icon layer) |
| `app/src/main/res/values/ic_launcher_background.xml` | color `#0055FF` | tiny | repo | as-is (use hex in Color.kt) |
| `app/src/main/res/mipmap-anydpi/ic_launcher.xml`, `ic_launcher_round.xml` | adaptive-icon XML | tiny | repo | skip |
| `core/designsystem/art/cube/DroidCon.svg` | SVG (viewBox 48.57x29.71; fills #00f757, #fff) | 2.7 KB | art not stated | as-is |
| `core/designsystem/art/cube/AI.svg` | SVG | 0.6 KB | art not stated | as-is |
| `core/designsystem/art/cube/FlutterCon.svg` | SVG | 2.9 KB | art not stated | as-is |
| `core/designsystem/art/cube/Kotlin.svg` | SVG | 21.5 KB | art not stated (Kotlin mark is JetBrains trademark) | as-is |
| `core/designsystem/art/cube/React.svg` | SVG | 11.1 KB | art not stated (React mark) | as-is |
| `core/designsystem/art/cube/Swift.svg` | SVG | 12.2 KB | art not stated (Swift mark is Apple's) | as-is |
| `DS/icons/ChaiIcons.kt` | Kotlin `ImageVector` builders (`Build`, `MicExternalOn`, `CoPresent`, `Coffee`, ...), imports only `androidx.compose.ui.graphics.*` | code | repo | as-is (plain Compose, portable) |
| `core/designsystem/src/main/res/drawable/droidcon_icon.png` | PNG | 27 KB | repo | skip (superseded by logo vector) |
| `core/designsystem/src/main/res/drawable/{about,feed,home,sessions}_icon.xml`, `ic_back_arrow.xml`, `ic_google_logo_icon.xml` | Android vector XML | 0.4 to 1 KB | repo (Google mark: trademark) | skip |
| `core/designsystem/src/main/res/drawable/toolbar_bg_sign_up_dark.xml`, `topbar_bg_sign_up.xml` | Android shape XML | 0.7 KB | repo | skip |
| `core/ui/src/main/res/drawable/droidcon_event_banner.webp` | WebP | 18 KB | repo | as-is (convert to PNG if the target lacks WebP) |
| `core/ui/src/main/res/drawable/all.webp` | WebP | 25 KB | repo | skip |
| `core/ui/src/main/res/drawable/team.webp` | WebP | 141 KB | repo (photo; permission unknown) | skip until cleared |
| `core/ui/src/main/res/drawable/smiling.webp` | WebP | 124 KB | repo (photo; permission unknown) | skip until cleared |
| `core/ui/src/main/res/drawable/ic_feedback_{bad,neutral_smiling}_face.png` (3 PNGs, 15 to 20 KB), `ic_feedback_emoji.xml` | PNG / vector XML | small | repo | skip |
| `core/ui/src/main/res/drawable/ic_*.xml` (filter, share, send, twitter, listalt, view_agenda, home_speakers_card_*), `btn_google_icon.xml` | Android vector XML | 0.3 to 4 KB | repo | skip |
| `core/designsystem/src/main/res/font/montserrat_variable.ttf` | TTF variable font | 297 KB | SIL OFL 1.1, `core/designsystem/OFL.txt` (Copyright 2024 The Montserrat.Git Project Authors) | as-is (ship OFL.txt beside it) |
| `core/designsystem/src/test/screenshots/chai/**/*.png` | PNG screenshot baselines | 4 to 594 KB | repo | skip (useful as visual reference only) |

## 3. Components

All under `DS/` unless noted. Greps run on `import` lines plus `R.`, `painterResource`, `stringResource`, `LocalContext`.

| Component | Path | Android-only APIs | Portable as plain Compose |
|---|---|---|---|
| Cube hero (state, motion, projection, marks, art) | `DS/hero/CubeHero.kt`, `CubeState.kt`, `CubeMotion.kt`, `CubeProjection.kt`, `CubeMarks.kt`, `CubeArt.kt`, `Orientation.kt` | none found | yes (uses `Canvas`, `Animatable`, `PathParser`) |
| Cube painter | `DS/hero/CubePainter.kt` | yes: `android.graphics.Matrix` and `nativeCanvas` (lines 18, 27, 56, 74) | no, needs rewriting to common `Matrix`/`drawIntoCanvas`/`Path` |
| Cube banner / stage | `DS/hero/CubeHeroBanner.kt`, `CubeHeroStage.kt` | none in imports (depend on `ChaiTheme` and the painter) | yes, once the painter is ported |
| `CText` | `DS/components/CText.kt` | none | yes |
| `CButtons` | `DS/components/CButtons.kt` | none | yes (M3 `Button`, `OutlinedButton`) |
| `CPullToRefresh` | `DS/components/CPullToRefresh.kt` | none | yes (M3 `PullToRefreshBox`) but irrelevant to slides |
| Session cards | `core/ui/src/main/java/com/android254/presentation/common/components/SessionsCard.kt`; `feature/sessions/src/main/java/com/android254/presentation/sessions/components/SessionsCardWithBannerImage.kt` | yes: `ke.droidcon.kotlin.core.ui.R`, `stringResource(R.string...)`, `painterResource(R.drawable.all)`, `LocalContext`, Coil `AsyncImage` | no, rebuild the look from tokens |
| Typography | `DS/atoms/ChaiTypography.kt` | yes: `ke.droidcon.kotlin.chai.R`, `R.font.montserrat_variable` | mostly; swap to Compose resources `Font` |
| Theme | `DS/Theme.kt` | none (`isSystemInDarkTheme` is common) | yes |

## 4. Timeline

Date is author date (`git show -s --date=short`). Files followed: `DS/Theme.kt`, `DS/atoms/Color.kt`, `DS/colors/`.

| SHA | Date | Change |
|---|---|---|
| 740ce113 | 2023-04-02 | Color scheme updated to "Chai colors" (first rewrite of `atoms/Color.kt`). |
| 68387ddd | 2023-04-28 | Adds the custom color palette: `ChaiColors.kt` and `Theme.kt` wiring. |
| 17eb9fa8 | 2023-11-06 | "Feature/designsystem (#249)": moves chai into a design-system module (`core/designsystem` history starts here for `--follow`). |
| 1c9a1176 | 2026-09-04 | Phase 0 finish: screenshot baselines, chai moved onto Material 3. |
| 87d23cb7 | 2026-10-04 | Chai onto Material 3 roles and Material 3 Expressive. |
| eb7c436e | 2026-10-06 | Rebrand to the 2026 droidcon identity (blue + neon green, Montserrat variable, Expressive). |
| a966dae1 | 2026-10-11 | WCAG AA contrast guard on every role pair; greys darkened. |
| 5941f20f | 2026-10-15 | Cube hero added. |

Also relevant: d66ca423 (2026-09-07) module split that created `core/designsystem` paths.

### Pre-2026 palette (`atoms/Color.kt` at `eb7c436e^`)

| Token | Hex |
|---|---|
| ChaiBlue | #000CEB |
| ChaiRed | #FF6E4D |
| ChaiTeal | #00E2C3 |
| ChaiTeal90 | #7DE1C3 |
| ChaiWhite / ChaiBlack | #FFFFFF / #000000 |
| ChaiLightGrey90 | #F6F6F8 |
| ChaiLightGrey | #F5F5F5 |
| ChaiGrey | #B1B1B1 |
| ChaiSmokeyGrey | #707070 |
| ChaiDarkGrey | #5A5A5A |
| ChaiCoal | #191D1D |
| ChaiGrey90 | #20201E |
| ChaiSubtleGrey | #383836 |

Pre-2026 `ChaiColors` extras: loadingShimmerColor, tealAccentColor (#7DE1C3), selectedDayContentColor, badgeContainerColor, switchThumbColor, switchOffIconColor. Pre-2026 typography: static per-weight Montserrat files (`montserrat_regular/bold/semi_bold/light/medium/extra_light/thin.ttf`, `atoms/CFonts.kt`). So "then vs now": blue `#000CEB` + red `#FF6E4D` + teal `#00E2C3` becomes blue `#0055FF` + neon green `#00FF4F`; same font family, now variable.

## 5. Mapping to the slides repo

| Source | Slides target | Note |
|---|---|---|
| Light/dark schemes (1.2), atoms (1.1), `ChaiColors` extras (1.3), cube colors (1.4) | `slides:shared/design/src/commonMain/kotlin/ke/don/design/theme/Color.kt` | Currently Ski purple M3 baseline (`primaryLight = #6D538B` etc.); would be replaced or sit beside it. Extras need a new holder plus `CompositionLocal` in `Theme.kt`. |
| Typography (1.6) | `slides:shared/design/.../theme/Type.kt` | Today uses `displayFontFamily` / `subheadingsFontFamily` built from Google Sans. Slides need larger display sizes than the app's 11 to 20 sp tuned ramp. |
| Spacing (1.7) | `slides:shared/design/.../theme/Dimens.kt` | Existing `Dimens` is 8/16/24/32 dp; chai has no scale to port. Keep. |
| Shapes, motion (1.7, 1.8) | `slides:shared/design/.../theme/Theme.kt` | Defaults; motion constants only if the cube is ported. |
| Montserrat TTF + OFL.txt | `slides:shared/resources/src/commonMain/composeResources/font/` | Expose in `slides:shared/resources/src/commonMain/kotlin/ke/don/resources/Resources.kt` next to `GOOGLE_SANS_*`. Existing bug: `GOOGLE_SANS_EXTRA_LIGHT = Res.font.google_sans_regular` (line 11) points at the regular file, not an extra-light one. |
| Logos, cube SVGs, `droidcon_event_banner` | `slides:shared/resources/src/commonMain/composeResources/drawable/` | SVGs and converted logo vectors; reference via `Resources.kt`. Compose Resources reads SVG on some targets only (Android vector XML is fine, raw SVG support varies), so convert logos to Android vector XML or ImageVector. |
| `ChaiIcons.kt` | `slides:shared/components` or `shared/design` as Kotlin | Already common-code ImageVectors. |
| Cube hero | `slides:segments/demos` (single slide) or `slides:shared/components` (reusable) | Needs `CubePainter` port first. |

## 6. Decisions for phase 2

Answered by the maintainer on 2026-10-23, pending a review with the other presenters later that evening.

1. **Identity:** adopt the 2026 identity (blue/neon green/black) fully and everywhere, replacing Ski purple, including framework demo slides.
2. **Font:** switch to Montserrat everywhere. This means fixing `Resources.kt` (including the `GOOGLE_SANS_EXTRA_LIGHT` bug) and `Type.kt`. Keep Ski's existing type sizes and weights in `Type.kt`, which are already tuned for slides; swap only the font family, not the app's type ramp.
3. **Cube hero:** port it, and show it in the header next to the droidcon logo. It needs `CubePainter` rewritten off `android.graphics.Matrix`/`nativeCanvas`.
4. **Footer:** use the cube as the footer bullet.
5. **Frame:** probably a custom frame for this talk (header with logo and cube, footer with the cube bullet), not one of the stock Ski frames.
6. **Marks:** full permission to use the Kotlin, React and Swift cube marks and the team/smiling photos.
7. **Then vs now:** definitely include a "then vs now" slide, using the pre-2026 palette from section 4.
8. **Logo:** convert the Android vector XML logos to SVG.
