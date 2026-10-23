package ke.don.demos.droidcon.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import ke.don.domain.LocalRevealStep
import ke.don.domain.values.Values

/**
 * Shows [content] once the current reveal step reaches [index] (1-based), and keeps its space while hidden.
 *
 * Hidden items are fully transparent rather than removed, so the layout does not jump as steps advance.
 * The fade runs for about 300 ms.
 *
 * @param index The step at which this item appears. The first revealable item is 1.
 * @param content The item to reveal.
 */
@Composable
fun RevealItem(
    index: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val step = LocalRevealStep.current
    val alpha by animateFloatAsState(
        targetValue = if (step >= index) 1f else 0f,
        animationSpec = tween(300),
        label = "revealItemAlpha"
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer { this.alpha = alpha },
        verticalArrangement = Arrangement.Top
    ) {
        content()
    }
}

/**
 * A bulleted list where each bullet is revealed on its own step: item `i` appears at step `i + 1`.
 *
 * @param items The bullet text, in reveal order.
 */
@Composable
fun RevealBulletList(items: List<String>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
    ) {
        items.forEachIndexed { i, item ->
            RevealItem(index = i + 1) {
                BulletRow(item)
            }
        }
    }
}
