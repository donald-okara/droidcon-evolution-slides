package ke.don.demos.droidcon.opening

import androidx.compose.runtime.Composable
import ke.don.demos.droidcon.common.PlaceholderSlide

private const val ANNUNZIATA = "Annunziata Kinya"

@Composable
fun OpeningTitleSlide() {
    PlaceholderSlide(
        title = "droidcon Kenya Android app, 2023 to 2026",
        intent = "Talk title, the app, and the three presenters",
        presenter = ANNUNZIATA
    )
}

@Composable
fun OpeningThesisSlide() {
    PlaceholderSlide(
        title = "Thesis",
        intent = "Thesis on screen and the arc: history, about to launch, future",
        presenter = ANNUNZIATA
    )
}
