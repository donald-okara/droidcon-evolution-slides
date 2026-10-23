package ke.don.ski.presentation.frame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import ke.don.domain.frames.SkiFrame
import ke.don.domain.frames.SkiFrameFactory
import ke.don.domain.frames.rememberFrame
import ke.don.domain.values.Values

/**
 * Main frame for the droidcon deck: header row, curved content box, footer row.
 * Laid out like `BasicFrame`, using the theme's colour roles.
 */
class DroidconFrame(
    private val curve: Dp = Values.cornerRadius,
    val opacity: Float = Values.FRAME_OPACITY,
) : SkiFrame {
    @Composable
    override fun Render(
        modifier: Modifier,
        header: @Composable (() -> Unit)?,
        footer: @Composable (() -> Unit)?,
        content: @Composable (BoxScope.() -> Unit),
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            header?.invoke()
            CurvedBox(
                modifier = Modifier.weight(1f),
                curve = curve,
                opacity = opacity,
                content = content,
            )
            footer?.invoke()
        }
    }
}

/** Factory that the deck's `SkiFrames` builder selects for the main frame. */
val droidconFrameFactory = SkiFrameFactory { curve, opacity ->
    rememberFrame(curve, opacity) {
        DroidconFrame(curve = curve, opacity = opacity)
    }
}

@Composable
private fun CurvedBox(
    modifier: Modifier = Modifier,
    opacity: Float,
    curve: Dp,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        border = BorderStroke(
            width = Values.lineThickness,
            color = MaterialTheme.colorScheme.onSurface,
        ),
        shape = RoundedCornerShape(curve),
        color = MaterialTheme.colorScheme.surface.copy(opacity),
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            content()
        }
    }
}
