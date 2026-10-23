package ke.don.ski

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import ke.don.demos.droidcon.talk.COLD_TITLE_LABEL
import ke.don.domain.frames.FrameBuilder
import io.github.donald_okara.components.frames.defaultSkiFrames
import ke.don.domain.frames.LocalSkiFrames
import ke.don.domain.values.Values
import ke.don.domain.DeckMode
import ke.don.domain.DeckNavigator
import ke.don.domain.LocalDeckMode
import ke.don.domain.SlideConfig
import ke.don.domain.SlidesConstants.FRAME_OPACITY
import ke.don.ski.presentation.PresentationDeck
import ke.don.ski.presentation.background.DroidconBackground
import ke.don.ski.presentation.ui.skiPresentationSlides
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Composable entry point that renders the slide deck UI with theme support, navigation, and frame layout.
 *
 * @param navigator Navigator responsible for slide/screen navigation within the deck.
 * @param mode Mode to use for the deck (for example local or remote presentation behavior).
 * @param slides List of slide configurations to display in the deck.
 */
@Composable
fun Deck(
    mode: DeckMode = DeckMode.Local,
    slides: List<SlideConfig> = skiPresentationSlides(),
    navigator: DeckNavigator = remember { DeckNavigator(slides) },
) {

    CompositionLocalProvider(
        LocalSkiFrames provides defaultSkiFrames()
    ) {
        val guidesFrame = FrameBuilder()
            .setFrame { basic }
            .setOpacity(FRAME_OPACITY)
            .build()

        val mainFrame = FrameBuilder()
            .setFrame { snake }
            .setOpacity(FRAME_OPACITY)
            .build()

        // The title slide shows the cube as its own large hero, so the background cube steps aside there.
        val background = remember(navigator) {
            DroidconBackground(cubeVisible = { navigator.currentSlide.label != COLD_TITLE_LABEL })
        }


        CompositionLocalProvider(
            LocalDeckMode provides mode
        ) {
            PresentationDeck(
                mainFrame = mainFrame,
                guidesFrame = guidesFrame,
                background = background,
                navigator = navigator,
                slides = slides,
                shareFrame = true
            )
        }
    }
}


