/*
 * Copyright 2026 DroidconKE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ke.don.design.cube

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach

/** The conference cube, turning through its logos. */
@Composable
fun CubeHero(
    modifier: Modifier = Modifier,
    size: Dp = 240.dp,
) {
    val motion = remember { CubeMotion() }
    LaunchedEffect(Unit) { CubeMarks.cutAll() }
    LaunchedEffect(motion) { motion.run() }
    val painter = remember { CubePainter() }
    Canvas(modifier = modifier.size(size).graphicsLayer().clearAndSetSemantics { }) {
        val quads = motion.state.visibleQuads(motion.orientation, motion.turn)
        val scale = this.size.minDimension / (2 * MOVING_REACH * POP_SCALE) * motion.pop
        quads.fastForEach { with(painter) { drawFace(it, center, scale) } }
    }
}

/** The cube drawn still in its first orientation, small enough to lead a list item. */
@Composable
fun CubeBullet(
    modifier: Modifier = Modifier,
    size: Dp = 12.dp,
) {
    LaunchedEffect(Unit) { CubeMarks.cutAll() }
    val painter = remember { CubePainter() }
    val quads = remember { CubeState.solved().visibleQuads(Poses[0]) }
    Canvas(modifier = modifier.size(size).clearAndSetSemantics { }) {
        val scale = this.size.minDimension / (2 * RESTING_REACH)
        quads.fastForEach { with(painter) { drawFace(it, center, scale) } }
    }
}
