package ke.don.demos.droidcon.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ke.don.domain.values.Values

/**
 * The title block for a content slide, with an optional kicker line above the title.
 *
 * @param title The slide title, shown large and bold.
 * @param kicker An optional short label shown above the title in the primary colour.
 */
@Composable
fun SlideTitle(
    title: String,
    kicker: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = Values.Dimens.mediumPadding),
        verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)
    ) {
        if (kicker != null) {
            Text(
                text = kicker,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * A plain bulleted list. Each item gets a small dot from `secondaryContainer`.
 *
 * @param items The text of each bullet, in order.
 */
@Composable
fun BulletList(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
    ) {
        items.forEach { item ->
            BulletRow(item)
        }
    }
}

/**
 * One bullet: a dot followed by the item text in `headlineSmall`.
 *
 * Shared by [BulletList] and the revealable bullet list in `Reveal.kt`.
 *
 * @param text The bullet text.
 */
@Composable
internal fun BulletRow(text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .size(Values.Dimens.smallPadding)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )
        Spacer(modifier = Modifier.size(Values.Dimens.smallPadding))
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * A short commit SHA shown as a small monospace tag, for citing a commit on screen.
 *
 * @param sha The abbreviated commit SHA, exactly as cited in `docs/talk/history.md`.
 */
@Composable
fun CommitTag(sha: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(Values.Dimens.tinyPadding))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = Values.Dimens.smallPadding, vertical = Values.Dimens.tinyPadding)
    ) {
        Text(
            text = sha,
            style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * An outlined box that marks where a screenshot, recording or photo will go.
 *
 * @param label What goes here, for example "Screen recording: Glance widget".
 */
@Composable
fun MediaPlaceholder(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(Values.Dimens.smallPadding))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(Values.Dimens.smallPadding)
            )
            .padding(Values.Dimens.mediumPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
