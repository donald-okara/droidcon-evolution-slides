package ke.don.demos.droidcon.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import ke.don.design.cube.CubeHero

private const val CUBE_SPIN_MILLIS = 900
private const val CUBE_HERO_FILL = 0.9f

/**
 * The cube hero. It turns from edge-on to face-on once, when the slide first composes. It is sized from the
 * space it is given, so it fills most of the height above the title and stays square.
 */
@Composable
fun SpinningCube(modifier: Modifier = Modifier) {
    val turn = remember { Animatable(-90f) }
    LaunchedEffect(Unit) {
        turn.animateTo(0f, tween(durationMillis = CUBE_SPIN_MILLIS, easing = FastOutSlowInEasing))
    }
    BoxWithConstraints(
        modifier = modifier.graphicsLayer { rotationY = turn.value },
        contentAlignment = Alignment.Center
    ) {
        CubeHero(size = minOf(maxWidth, maxHeight) * CUBE_HERO_FILL)
    }
}
