package ke.don.demos.droidcon.opening

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ke.don.demos.droidcon.common.MediaPlaceholder
import ke.don.domain.values.Values

private const val ANNUNZIATA = "Annunziata Kinya"
private const val DONALD = "Donald Isoe"
private const val IAN = "Ian Nthuli"

/**
 * The opening title: the app's name and what it is, then the three presenters with a photo placeholder each.
 */
@Composable
fun OpeningTitleSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "The droidcon Kenya app, 2023 to 2026",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Text(
            text = "The community's shared teaching codebase",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = Values.Dimens.mediumPadding)
        )
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            PresenterCard(name = ANNUNZIATA, segment = "History", modifier = Modifier.weight(1f))
            PresenterCard(name = DONALD, segment = "About to launch", modifier = Modifier.weight(1f))
            PresenterCard(name = IAN, segment = "The future", modifier = Modifier.weight(1f))
        }
    }
}

/**
 * One presenter: a photo placeholder above the name and the segment they present.
 *
 * @param name The presenter's name, as it appears on the slide.
 * @param segment The talk segment the presenter owns.
 */
@Composable
private fun PresenterCard(name: String, segment: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(180.dp)) {
            MediaPlaceholder(label = "Photo: $name")
        }
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Text(
            text = segment,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * The thesis as a quote, then the three arcs of the talk as chips: history, about to launch, future.
 */
@Composable
fun OpeningThesisSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding),
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .width(Values.Dimens.tinyPadding)
                    .height(160.dp)
                    .clip(RoundedCornerShape(Values.Dimens.tinyPadding))
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(Values.Dimens.mediumPadding))
            Text(
                text = "The droidcon Kenya app has outlived years of contributors joining through pull requests " +
                    "and stepping back when busy, and two multiplatform detours, because it stays a shared " +
                    "teaching codebase. The 2026 update shows what that buys, and the improvement plan is an " +
                    "invitation to make the app your own.",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.largePadding))
        Row(horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)) {
            ArcChip("History")
            ArcChip("About to launch")
            ArcChip("Future")
        }
    }
}

/**
 * A rounded chip naming one arc of the talk.
 *
 * @param label The arc's name, as it appears on the slide.
 */
@Composable
private fun ArcChip(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(percent = 50))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = Values.Dimens.mediumPadding, vertical = Values.Dimens.smallPadding)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}
