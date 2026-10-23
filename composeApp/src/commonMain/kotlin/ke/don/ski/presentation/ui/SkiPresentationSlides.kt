package ke.don.ski.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import ke.don.demos.droidcon.close.LessonsSlide
import ke.don.demos.droidcon.close.QaSlide
import ke.don.demos.droidcon.future.FutureInviteSlide
import ke.don.demos.droidcon.future.FuturePlanSlide
import ke.don.demos.droidcon.future.FutureRoadmapSlide
import ke.don.demos.droidcon.history.DetourKmmSlide
import ke.don.demos.droidcon.history.DetourKmpSlide
import ke.don.demos.droidcon.history.DETOUR_KMP_STEPS
import ke.don.demos.droidcon.history.DormantYearsSlide
import ke.don.demos.droidcon.history.HISTORY_BOOTSTRAP_STEPS
import ke.don.demos.droidcon.history.HISTORY_PLUGINS_STEPS
import ke.don.demos.droidcon.history.HistoryBootstrapSlide
import ke.don.demos.droidcon.history.HistoryChurnSlide
import ke.don.demos.droidcon.history.HistoryPluginsSlide
import ke.don.demos.droidcon.launch.LAUNCH_FEATURES_STEPS
import ke.don.demos.droidcon.launch.LAUNCH_MODULES_STEPS
import ke.don.demos.droidcon.launch.LAUNCH_WIP_STEPS
import ke.don.demos.droidcon.launch.LaunchDesignSlide
import ke.don.demos.droidcon.launch.LaunchFeaturesSlide
import ke.don.demos.droidcon.launch.LaunchModulesSlide
import ke.don.demos.droidcon.launch.LaunchPerfSlide
import ke.don.demos.droidcon.launch.LaunchStackSlide
import ke.don.demos.droidcon.launch.LaunchWipSlide
import ke.don.demos.droidcon.opening.OpeningThesisSlide
import ke.don.demos.droidcon.opening.OpeningTitleSlide
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
            // Section 1: Opening
            slide(
                label = "opening-title",
                notes = openingTitleNotes,
                transition = ScreenTransition.Fade,
                showFooter = false
            ) {
                OpeningTitleSlide()
            }
            slide(label = "opening-thesis", notes = openingThesisNotes, transition = ScreenTransition.Fade) {
                OpeningThesisSlide()
            }

            // Section 2: History, 2023
            slide(
                label = "history-bootstrap",
                notes = historyBootstrapNotes,
                transition = ScreenTransition.Fade,
                steps = HISTORY_BOOTSTRAP_STEPS
            ) {
                HistoryBootstrapSlide()
            }
            slide(
                label = "history-plugins",
                notes = historyPluginsNotes,
                transition = ScreenTransition.Fade,
                steps = HISTORY_PLUGINS_STEPS
            ) {
                HistoryPluginsSlide()
            }
            slide(label = "history-churn", notes = historyChurnNotes, transition = ScreenTransition.Fade) {
                HistoryChurnSlide()
            }

            // Section 3: Detours and dormant years
            slide(label = "detour-kmm", notes = detourKmmNotes, transition = ScreenTransition.Fade) {
                DetourKmmSlide()
            }
            slide(
                label = "detour-kmp",
                notes = detourKmpNotes,
                transition = ScreenTransition.Fade,
                steps = DETOUR_KMP_STEPS
            ) {
                DetourKmpSlide()
            }
            slide(label = "dormant-years", notes = dormantYearsNotes, transition = ScreenTransition.Fade) {
                DormantYearsSlide()
            }

            // Section 4: 2026 update, done
            slide(label = "launch-stack", notes = launchStackNotes, transition = ScreenTransition.Fade) {
                LaunchStackSlide()
            }
            slide(
                label = "launch-modules",
                notes = launchModulesNotes,
                transition = ScreenTransition.Fade,
                steps = LAUNCH_MODULES_STEPS
            ) {
                LaunchModulesSlide()
            }
            slide(label = "launch-perf", notes = launchPerfNotes, transition = ScreenTransition.Fade) {
                LaunchPerfSlide()
            }
            slide(label = "launch-design", notes = launchDesignNotes, transition = ScreenTransition.Fade) {
                LaunchDesignSlide()
            }
            slide(
                label = "launch-features",
                notes = launchFeaturesNotes,
                transition = ScreenTransition.Fade,
                steps = LAUNCH_FEATURES_STEPS
            ) {
                LaunchFeaturesSlide()
            }

            // Section 5: 2026 update, work in progress
            slide(
                label = "launch-wip",
                notes = launchWipNotes,
                transition = ScreenTransition.Fade,
                steps = LAUNCH_WIP_STEPS
            ) {
                LaunchWipSlide()
            }

            // Section 6: The improvement plan
            slide(label = "future-plan", notes = futurePlanNotes, transition = ScreenTransition.Fade) {
                FuturePlanSlide()
            }
            slide(label = "future-roadmap", notes = futureRoadmapNotes, transition = ScreenTransition.Fade) {
                FutureRoadmapSlide()
            }
            slide(label = "future-invite", notes = futureInviteNotes, transition = ScreenTransition.Fade) {
                FutureInviteSlide()
            }

            // Section 7: Lessons and close
            slide(label = "lessons", notes = lessonsNotes, transition = ScreenTransition.Fade) {
                LessonsSlide()
            }

            // Section 8: Q&A
            slide(label = "qa", notes = qaNotes, transition = ScreenTransition.Fade, showFooter = false) {
                QaSlide()
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
