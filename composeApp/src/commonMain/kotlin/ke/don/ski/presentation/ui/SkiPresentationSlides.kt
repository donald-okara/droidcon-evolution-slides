package ke.don.ski.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import ke.don.demos.droidcon.talk.BreakItSlide
import ke.don.demos.droidcon.talk.COLD_TITLE_LABEL
import ke.don.demos.droidcon.talk.CUBE_WIDGET_LABEL
import ke.don.demos.droidcon.talk.CodeGrewSlide
import ke.don.demos.droidcon.talk.ChaiSlide
import ke.don.demos.droidcon.talk.CommunityPeakSlide
import ke.don.demos.droidcon.talk.CubeWidgetSlide
import ke.don.demos.droidcon.talk.EIGHT_YEARS_STEPS
import ke.don.demos.droidcon.talk.EightYearsTeachSlide
import ke.don.demos.droidcon.talk.FAMILY_TREE_STEPS
import ke.don.demos.droidcon.talk.FamilyTreeSlide
import ke.don.demos.droidcon.talk.FollowsConferenceSlide
import ke.don.demos.droidcon.talk.KmpThreadSlide
import ke.don.demos.droidcon.talk.LostAndFoundSlide
import ke.don.demos.droidcon.talk.MeasuredSlide
import ke.don.demos.droidcon.talk.ModulesSlide
import ke.don.demos.droidcon.talk.Navigation3Slide
import ke.don.demos.droidcon.talk.NewLookSlide
import ke.don.demos.droidcon.talk.OneRepoSlide
import ke.don.demos.droidcon.talk.QuietYearSlide
import ke.don.demos.droidcon.talk.SeasonsSlide
import ke.don.demos.droidcon.talk.StackSlide
import ke.don.demos.droidcon.talk.ThankYouSlide
import ke.don.demos.droidcon.talk.TitleSlide
import ke.don.demos.droidcon.talk.TodaySlide
import ke.don.demos.droidcon.talk.TwoReadersSlide
import ke.don.demos.droidcon.talk.UncomfortableChartSlide
import ke.don.demos.droidcon.talk.Y2018CloneSlide
import ke.don.demos.droidcon.talk.Y2018FirstSlide
import ke.don.demos.droidcon.talk.Y2019Slide
import ke.don.demos.droidcon.talk.Y2020ModularSlide
import ke.don.demos.droidcon.talk.Y2020NoConferenceSlide
import ke.don.demos.droidcon.talk.Y2022ComposeSlide
import ke.don.domain.ScreenTransition
import ke.don.domain.SlideConfig
import ke.don.domain.SlidesConstants.SESSION_DURATION
import ke.don.domain.generateDeck
import ke.don.domain.timer.TimerController
import kotlin.time.Duration

@Composable
fun skiPresentationSlides(sessionDuration: Duration = SESSION_DURATION): List<SlideConfig> {
    val timerController = rememberTimerController(sessionDuration)

    val slides = remember(timerController) {
        generateDeck(
            timerController = timerController
        ) {
            // Act 0: Cold open
            slide(
                label = COLD_TITLE_LABEL,
                notes = titleNotes,
                transition = ScreenTransition.Fade,
                showFooter = false
            ) {
                TitleSlide()
            }
            slide(label = "cold-today", notes = todayNotes, transition = ScreenTransition.Fade) {
                TodaySlide()
            }
            slide(
                label = "cold-family-tree",
                notes = familyTreeNotes,
                transition = ScreenTransition.Fade,
                steps = FAMILY_TREE_STEPS
            ) {
                FamilyTreeSlide()
            }
            slide(label = "cold-seasons", notes = seasonsNotes, transition = ScreenTransition.Fade) {
                SeasonsSlide()
            }

            // Act 1: 2018, an I/O clone
            slide(label = "y2018-first", notes = y2018FirstNotes, transition = ScreenTransition.Fade) {
                Y2018FirstSlide()
            }
            slide(label = "y2018-clone", notes = y2018CloneNotes, transition = ScreenTransition.Fade) {
                Y2018CloneSlide()
            }

            // Act 2: 2019, all Jetpack
            slide(label = "y2019-all-jetpack", notes = y2019AllJetpackNotes, transition = ScreenTransition.Fade) {
                Y2019Slide()
            }

            // Act 3: 2020, the modular one
            slide(label = "y2020-modular", notes = y2020ModularNotes, transition = ScreenTransition.Fade) {
                Y2020ModularSlide()
            }
            slide(
                label = "y2020-no-conference",
                notes = y2020NoConferenceNotes,
                transition = ScreenTransition.Fade
            ) {
                Y2020NoConferenceSlide()
            }

            // Act 4: 2022, Compose arrives
            slide(label = "y2022-compose", notes = y2022ComposeNotes, transition = ScreenTransition.Fade) {
                Y2022ComposeSlide()
            }
            slide(label = "y2022-chai", notes = chaiNotes, transition = ScreenTransition.Fade) {
                ChaiSlide()
            }

            // Act 5: 2023 to 2025, one repo
            slide(label = "y2023-one-repo", notes = oneRepoNotes, transition = ScreenTransition.Fade) {
                OneRepoSlide()
            }
            slide(label = "y2023-community-peak", notes = communityPeakNotes, transition = ScreenTransition.Fade) {
                CommunityPeakSlide()
            }
            slide(label = "y2024-quiet-year", notes = quietYearNotes, transition = ScreenTransition.Fade) {
                QuietYearSlide()
            }
            slide(label = "kmp-thread", notes = kmpThreadNotes, transition = ScreenTransition.Fade) {
                KmpThreadSlide()
            }
            slide(label = "y2025-navigation3", notes = navigation3Notes, transition = ScreenTransition.Fade) {
                Navigation3Slide()
            }

            // Act 6: 2026, measured
            slide(label = "y2026-two-readers", notes = twoReadersNotes, transition = ScreenTransition.Fade) {
                TwoReadersSlide()
            }
            slide(label = "y2026-modules", notes = modulesNotes, transition = ScreenTransition.Fade) {
                ModulesSlide()
            }
            slide(label = "y2026-measured", notes = measuredNotes, transition = ScreenTransition.Fade) {
                MeasuredSlide()
            }
            slide(label = "y2026-new-look", notes = newLookNotes, transition = ScreenTransition.Fade) {
                NewLookSlide()
            }
            slide(label = CUBE_WIDGET_LABEL, notes = cubeWidgetNotes, transition = ScreenTransition.Fade) {
                CubeWidgetSlide()
            }
            slide(
                label = "y2026-follows-conference",
                notes = followsConferenceNotes,
                transition = ScreenTransition.Fade
            ) {
                FollowsConferenceSlide()
            }
            slide(label = "y2026-stack", notes = stackNotes, transition = ScreenTransition.Fade) {
                StackSlide()
            }
            slide(label = "y2026-code-grew", notes = codeGrewNotes, transition = ScreenTransition.Fade) {
                CodeGrewSlide()
            }

            // Act 7: Lessons and the ask
            slide(label = "lessons-lost-and-found", notes = lostAndFoundNotes, transition = ScreenTransition.Fade) {
                LostAndFoundSlide()
            }
            slide(
                label = "lessons-uncomfortable-chart",
                notes = uncomfortableChartNotes,
                transition = ScreenTransition.Fade
            ) {
                UncomfortableChartSlide()
            }
            slide(
                label = "lessons-eight-years",
                notes = eightYearsTeachNotes,
                transition = ScreenTransition.Fade,
                steps = EIGHT_YEARS_STEPS
            ) {
                EightYearsTeachSlide()
            }
            slide(label = "lessons-thank-you", notes = thankYouNotes, transition = ScreenTransition.Fade) {
                ThankYouSlide()
            }
            slide(
                label = "lessons-break-it",
                notes = breakItNotes,
                transition = ScreenTransition.Fade,
                showFooter = false
            ) {
                BreakItSlide()
            }
        }
    }
    return slides
}

@Composable
fun rememberTimerController(
    sessionDuration: Duration
): TimerController {
    val scope = rememberCoroutineScope()
    return remember(scope, sessionDuration) {
        TimerController(scope, sessionDuration)
    }
}
