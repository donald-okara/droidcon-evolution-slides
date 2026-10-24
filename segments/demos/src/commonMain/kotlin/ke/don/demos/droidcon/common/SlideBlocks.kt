package ke.don.demos.droidcon.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ke.don.domain.values.Values
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

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
    val entrance = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        entrance.animateTo(1f, tween(durationMillis = TITLE_ENTRANCE_MILLIS))
    }
    val rise = 24.dp
    Column(
        modifier = modifier
            .padding(bottom = Values.Dimens.mediumPadding)
            .graphicsLayer {
                alpha = entrance.value
                translationY = (1f - entrance.value) * rise.toPx()
            },
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
        items.forEachIndexed { i, item ->
            StaggeredEntrance(delayMillis = i * BULLET_STAGGER_MILLIS) {
                BulletRow(item)
            }
        }
    }
}

/**
 * Fades [content] in and lifts it a few dp after [delayMillis], once, when the slide composes.
 *
 * Used by [BulletList] only. Reveal steps use `RevealItem` and are not affected.
 */
@Composable
private fun StaggeredEntrance(delayMillis: Int, content: @Composable () -> Unit) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        progress.animateTo(1f, tween(durationMillis = BULLET_ENTRANCE_MILLIS))
    }
    val lift = 8.dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                alpha = progress.value
                translationY = (1f - progress.value) * lift.toPx()
            }
    ) {
        content()
    }
}

private const val TITLE_ENTRANCE_MILLIS = 400
private const val BULLET_ENTRANCE_MILLIS = 400
private const val BULLET_STAGGER_MILLIS = 120

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
 * The source line for a slide that cites where its figures come from. Small and muted, shown at the foot of the slide.
 *
 * @param text The full citation, starting with "Source:".
 */
@Composable
fun SourceLine(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}

/**
 * A drawable from `Resources.Images`, fitted inside its box without cropping, with a rounded outline.
 *
 * Does not set a size: the caller sizes it, for example with `fillMaxSize()` or an aspect ratio.
 *
 * @param resource The drawable to show.
 * @param contentDescription What the image shows, read by screen readers.
 */
@Composable
fun SlideImage(resource: DrawableResource, contentDescription: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(resource),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .clip(RoundedCornerShape(Values.Dimens.smallPadding))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(Values.Dimens.smallPadding)
            )
    )
}

/**
 * A two-column slide layout for slides whose media was removed. The left column holds the title and focal
 * element, the right column holds the details. Both columns are equal width and vertically centred.
 *
 * @param lead The left column: the slide title and its focal element.
 * @param details The right column: the body text, rows or steps.
 */
@Composable
fun SplitSlide(
    lead: @Composable ColumnScope.() -> Unit,
    details: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
    ) {
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            lead()
        }
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            details()
        }
    }
}
