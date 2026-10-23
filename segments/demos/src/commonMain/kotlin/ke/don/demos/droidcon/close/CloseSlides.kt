package ke.don.demos.droidcon.close

import androidx.compose.runtime.Composable
import ke.don.demos.droidcon.common.PlaceholderSlide

private const val IAN = "Ian Nthuli"
private const val ALL_MODERATED = "All, moderated by Annunziata Kinya"

@Composable
fun LessonsSlide() {
    PlaceholderSlide(
        title = "Three lessons",
        intent = "Three lessons from a codebase that changes hands: write it down for the next person, let CI guard it while people are away, make it easy to start",
        presenter = IAN
    )
}

@Composable
fun QaSlide() {
    PlaceholderSlide(
        title = "Questions",
        intent = "Questions, with the open questions from `docs/talk/history.md` as backup",
        presenter = ALL_MODERATED
    )
}
