package ke.don.demos.droidcon.future

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ke.don.demos.droidcon.common.CommitTag
import ke.don.demos.droidcon.common.RevealItem
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.domain.values.Values

/** Number of reveal presses on [FutureRoadmapSlide]: one per stage, in the order the plan ranks them. Used by `SkiPresentationSlides.kt`. */
const val FUTURE_ROADMAP_STEPS = 5

/**
 * The improvement plan: where it lives, whose idea it was, and that it is a living roadmap rather than a commitment.
 *
 * No reveal.
 */
@Composable
fun FuturePlanSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The improvement plan", kicker = "The future")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "docs/IMPROVEMENT_PLAN.md",
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            CommitTag("62f45e2a")
        }
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Harun Wangereka's idea",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "A living roadmap, not a commitment",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "We're not married to it.",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * The ranked items from the improvement plan, one per reveal step, numbered 1 to 5.
 *
 * Reveal steps are [FUTURE_ROADMAP_STEPS]. Each hidden stage keeps its space, so the list does not jump.
 */
@Composable
fun FutureRoadmapSlide() {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The roadmap", kicker = "Ranked, pending work")
        RoadmapStage(step = 1, text = "Accessibility listening pass: TalkBack, Switch Access and keyboard")
        RoadmapStage(step = 2, text = "Ticketing and QR, waiting on a backend question")
        RoadmapStage(step = 3, text = "Notifications leftovers: topic subscriptions and reminder actions")
        RoadmapStage(step = 4, text = "Venue map and calendar export")
        RoadmapStage(step = 5, text = "AI experiences, last, behind default-off flags", muted = true)
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Pending work only. Finished items are deleted as they land.",
            style = MaterialTheme.typography.bodyLarge,
            color = scheme.onSurfaceVariant
        )
    }
}

/**
 * One numbered roadmap stage that appears at its reveal step.
 *
 * @param step the 1-based reveal step at which the stage shows, which is also its number.
 * @param text the stage name and a short qualifier.
 * @param muted true to draw the stage in `onSurfaceVariant`, for the AI stage that is deliberately last.
 */
@Composable
private fun RoadmapStage(step: Int, text: String, muted: Boolean = false) {
    val scheme = MaterialTheme.colorScheme
    RevealItem(index = step) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Values.Dimens.tinyPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$step.",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = scheme.primary
            )
            Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                color = if (muted) scheme.onSurfaceVariant else scheme.onSurface
            )
        }
    }
}

/**
 * The invitation: five contributor tracks as chips, and how to join through a pull request.
 *
 * No reveal.
 */
@Composable
fun FutureInviteSlide() {
    val scheme = MaterialTheme.colorScheme
    val tracks = listOf("Foundations", "Design system", "Adaptive", "AI", "Conference ops")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Make the app your own", kicker = "Five tracks for a small contributor pool")
        tracks.chunked(3).forEach { rowTracks ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                rowTracks.forEach { TrackChip(text = it) }
            }
            Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        }
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Join with a pull request: that's how every maintainer started.",
            style = MaterialTheme.typography.headlineSmall,
            color = scheme.onSurface
        )
    }
}

/**
 * A rounded chip that names one contributor track.
 *
 * @param text the track name.
 */
@Composable
private fun TrackChip(text: String) {
    val scheme = MaterialTheme.colorScheme
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = scheme.onSecondaryContainer,
        modifier = Modifier
            .background(scheme.secondaryContainer, RoundedCornerShape(Values.Dimens.mediumPadding))
            .padding(horizontal = Values.Dimens.mediumPadding, vertical = Values.Dimens.smallPadding)
    )
}
