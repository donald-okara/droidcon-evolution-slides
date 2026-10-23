package ke.don.demos.droidcon.future

import androidx.compose.runtime.Composable
import ke.don.demos.droidcon.common.PlaceholderSlide

private const val IAN = "Ian Nthuli"

@Composable
fun FuturePlanSlide() {
    PlaceholderSlide(
        title = "The improvement plan",
        intent = "The improvement plan: what it is, whose idea it was (Harun Wangereka), and that it is a living roadmap, not a commitment",
        presenter = IAN
    )
}

@Composable
fun FutureRoadmapSlide() {
    PlaceholderSlide(
        title = "The roadmap",
        intent = "The ranked items: accessibility pass, ticketing and QR, notifications leftovers, venue map and calendar, AI last",
        presenter = IAN
    )
}

@Composable
fun FutureInviteSlide() {
    PlaceholderSlide(
        title = "Make it your own",
        intent = "Make the app your own: tracks for a small contributor pool, how to join through a pull request, the rotation model",
        presenter = IAN
    )
}
