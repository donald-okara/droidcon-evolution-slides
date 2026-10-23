# Phase 2: Droidcon design port and slide skeleton

## Goal

Give the deck the droidcon Kenya 2026 look (palette, Montserrat, logo, cube hero, a custom frame) and replace Ski's 18 demo slides with the talk's 19 slides as registered placeholders, so phase 3 only has to fill in content and notes.

## What This Phase Delivers

- The 2026 Chai colour schemes (light and dark), `ChaiColors` extras, cube colours and the pre-2026 palette in `:shared:design`, replacing Ski purple.
- Montserrat as the font family for every text style, with Ski's type sizes and weights unchanged, and the `GOOGLE_SANS_EXTRA_LIGHT` bug gone.
- The droidcon logo (light and dark) as SVG resources.
- The cube hero ported to common Compose code, with a static cube bullet.
- A droidcon frame: header with the logo and the cube, footer with the cube bullet, used by the deck's main frame.
- The 19 slides from `docs/talk/outline.md`, in order, with exact labels, each a named placeholder composable with placeholder notes.
- The 18 Ski demo slides, their notes and their demo-only composables removed.

## Out of Scope

- Real slide content, reveals and detailed presenter notes (phase 3), including the "then vs now" palette comparison on `launch-design`.
- Screen recordings, screenshots and presenter photos (phase 3, once the presenters choose).
- Rehearsal polish, background decorator choice, app identity rename (`ke.don.ski`, "ski") (phase 4).
- Removing the `:segments:introduction` module and the component gallery.
- Any change to the published library `:shared:components`.

## Success Criteria

- `./gradlew :desktopApp:run` opens both windows on `opening-title`, and stepping through shows 19 slides in outline order, droidcon colours, Montserrat text, the logo and cube in the header and the cube bullet in the footer.
- No Ski demo slide or Ski purple remains in the deck.
- `./gradlew :webApp:wasmJsBrowserDistribution` succeeds.

## Related Docs

- Design inputs and decisions: `docs/talk/design-inventory.md` (sections 1, 2, 3, 5, 6).
- Slide list, labels and presenters: `docs/talk/outline.md`.
- Where slides and notes go: `docs/architecture.md` (Decisions); slide-authoring rules: `context.md`.
- Model usage and escalation: `docs/preferences.md`.
