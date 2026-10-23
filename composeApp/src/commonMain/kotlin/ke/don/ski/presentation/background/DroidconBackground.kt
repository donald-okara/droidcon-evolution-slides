package ke.don.ski.presentation.background

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import io.github.donald_okara.components.backgrounds.Background
import io.github.donald_okara.components.backgrounds.decorator_image.DecoratorImage
import io.github.donald_okara.components.backgrounds.pattern.Pattern
import ke.don.design.cube.CubeHero
import ke.don.ski.presentation.ui.NeonGreen
import kotlin.math.ceil
import kotlin.math.floor

private const val HORIZON = 0.4f
private const val FLOOR_ALPHA = 0.06f
private const val TILE_DEPTH = 0.5f
private const val ROWS = 30
private const val COLUMNS_ACROSS = 6f
private const val TILE_MILLIS = 4000

/** Checkered perspective floor scrolling toward the horizon, with the cube hero at bottom-end. */
@Stable
class DroidconBackground : Background {
    // Unused: Render() draws everything itself.
    override val pattern: Pattern = Pattern.Wavy(colors = emptyList())
    override val decoratorImage: DecoratorImage? = null
    override val alignment: Alignment = Alignment.BottomEnd

    @Composable
    override fun Render() {
        val backgroundColor = MaterialTheme.colorScheme.background
        // Two tiles per loop so the checker parity repeats and the loop is seamless.
        val scroll by rememberInfiniteTransition(label = "floor").animateFloat(
            initialValue = 0f,
            targetValue = 2f,
            animationSpec = infiniteRepeatable(tween(TILE_MILLIS * 2, easing = LinearEasing), RepeatMode.Restart),
            label = "floorScroll",
        )
        Box(Modifier.fillMaxSize().background(backgroundColor)) {
            Canvas(Modifier.fillMaxSize()) {
                val horizonY = size.height * HORIZON
                val floorHeight = size.height - horizonY
                val cx = size.width / 2f
                val cellWidth = size.width / COLUMNS_ACROSS
                val shift = scroll
                val whole = floor(shift).toInt()
                val frac = shift - whole
                val color = NeonGreen.copy(alpha = FLOOR_ALPHA)

                // Ground depth d maps to screen y = horizon + floorHeight / d (d = 1 at the bottom edge).
                for (r in -1 until ROWS) {
                    val id = r - whole
                    val dNear = 1f + (r + frac) * TILE_DEPTH
                    val dFar = 1f + (r + 1 + frac) * TILE_DEPTH
                    if (dFar <= 0f) continue
                    val nearSafe = dNear.coerceAtLeast(0.05f)
                    val yNear = horizonY + floorHeight / nearSafe
                    val yFar = horizonY + floorHeight / dFar
                    if (yFar > size.height + 1f && yNear > size.height + 1f) continue
                    val limit = ceil(size.width / 2f / cellWidth * dFar).toInt() + 1
                    for (i in -limit until limit) {
                        if ((i + id) and 1 != 0) continue
                        val path = Path().apply {
                            moveTo(cx + i * cellWidth / nearSafe, yNear)
                            lineTo(cx + (i + 1) * cellWidth / nearSafe, yNear)
                            lineTo(cx + (i + 1) * cellWidth / dFar, yFar)
                            lineTo(cx + i * cellWidth / dFar, yFar)
                            close()
                        }
                        drawPath(path, color)
                    }
                }
            }
            CubeHero(
                size = 200.dp,
                modifier = Modifier.align(alignment).padding(16.dp),
            )
        }
    }
}
