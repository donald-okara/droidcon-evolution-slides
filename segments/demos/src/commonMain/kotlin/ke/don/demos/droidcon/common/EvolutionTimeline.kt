package ke.don.demos.droidcon.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ke.don.domain.LocalRevealStep
import ke.don.domain.values.Values
import kotlin.math.min

/**
 * One era of the app's history: its year, a short title, the repo that carried it, and cited detail lines.
 *
 * @param year The year shown above the node, for example "2018".
 * @param title A short name for the era, shown under the node.
 * @param repo The repo or era name shown on the details card.
 * @param details Two or three cited facts shown when the stage is hovered or selected.
 */
data class TimelineStage(
    val year: String,
    val title: String,
    val repo: String,
    val details: List<String>
)

/**
 * The seven stages of the droidcon Kenya app, from Harun's overview.
 *
 * Facts come from `docs/talk/harun-overview.md`: section 1.1 (the family tree table) and section 1.2 (era by era).
 */
val EvolutionStages: List<TimelineStage> = listOf(
    TimelineStage(
        year = "2018",
        title = "The I/O clone",
        repo = "droidconKE",
        details = listOf(
            "Forked from google/iosched on 2018-03-24; the first screens copied the I/O app.",
            "230 commits by 6 authors. Java first, with a Kotlin branch from August 2018.",
            "Cloud Functions sent push notifications to people who starred a session, July 2018."
        )
    ),
    TimelineStage(
        year = "2019",
        title = "All Kotlin, all Jetpack",
        repo = "droidconKE2019App",
        details = listOf(
            "Fully Kotlin, with Jetpack Navigation and Firestore. 153 commits in about four months.",
            "The README still reads like Google's I/O app: \"Guide to App Architecture\".",
            "The package becomes com.android254.droidconke19, the community's name in the code."
        )
    ),
    TimelineStage(
        year = "2020",
        title = "The big modular one",
        repo = "droidconKE2020App",
        details = listOf(
            "Seven dynamic-feature modules. 393 commits by 15 authors.",
            "120 stars and 40 forks: the most-starred Android repo in the org.",
            "The first README without any I/O wording."
        )
    ),
    TimelineStage(
        year = "2022",
        title = "Compose arrives",
        repo = "droidconKE2022Android",
        details = listOf(
            "Jetpack Compose, Hilt, Ktor and Paging 3 land together. 622 commits by 25 authors.",
            "chai, the design system, is named after the Swahili word for tea.",
            "Its own Adobe XD designs, in light and dark themes."
        )
    ),
    TimelineStage(
        year = "2023",
        title = "One repo to keep",
        repo = "droidconKeKotlin",
        details = listOf(
            "Created 2023-02-11 and not a rewrite: 173 of 177 Kotlin files kept their path.",
            "No year in the name, so contributors keep their history from one year to the next.",
            "Offline first for sessions, organisers and feed in 2023."
        )
    ),
    TimelineStage(
        year = "2025",
        title = "KMP, tried and taken out",
        repo = "droidconKeKMP",
        details = listOf(
            "Compose Multiplatform with an iOS app in June 2025, dropped on 2025-10-23 to keep Android shippable.",
            "KMP was tried twice, in 2024 and 2025. Both times it was taken out.",
            "A separate droidconKeKMP repo appeared in October 2025 with a one-commit skeleton."
        )
    ),
    TimelineStage(
        year = "2026",
        title = "Ticketing and a new look",
        repo = "droidconKeKotlin",
        details = listOf(
            "The 2026 rebrand, Material 3 Expressive, and a Home that follows the conference days.",
            "Your ticket on the phone, shown at the door with the gate's own QR code.",
            "Cold start down 22% (1,335 to 1,047 ms) and release APK down 28%, both measured."
        )
    )
)

private val NodeRowHeight = 56.dp
private val NodeSize = 28.dp
private val LineStroke = 3.dp
private val CardMaxWidth = 320.dp

/**
 * A horizontal timeline with one node per [TimelineStage], a year above each node and a title below.
 *
 * Stage `i` is lit once the reveal step reaches `i + 1` (see [LocalRevealStep]). Lit nodes spring up and pulse
 * once; unlit nodes are muted. The line draws on to the highest lit stage over 500 ms.
 *
 * Hovering a stage, or clicking it to pin it, shows a details card under that stage.
 *
 * @param stages The stages in chronological order. Must not be empty.
 * @param modifier The modifier for the whole timeline.
 * @param gapAfterIndex The index of the stage after which a gap marker is drawn, or null for no gap. The marker
 *   is a dashed link with a "?" node, labelled with the years missing between the two stages (2021 for 2020 to 2022).
 */
@Composable
fun EvolutionTimeline(
    stages: List<TimelineStage>,
    modifier: Modifier = Modifier,
    gapAfterIndex: Int? = null
) {
    require(stages.isNotEmpty()) { "EvolutionTimeline needs at least one stage" }
    require(gapAfterIndex == null || gapAfterIndex in stages.indices) { "gapAfterIndex must index a stage" }

    val gapSlot = gapAfterIndex?.plus(1)
    val slotCount = stages.size + if (gapSlot != null) 1 else 0
    val gapYear = gapAfterIndex?.let { missingYears(stages[it].year, stages.getOrNull(it + 1)?.year.orEmpty()) }.orEmpty()

    fun slotOf(stageIndex: Int): Int =
        if (gapAfterIndex != null && stageIndex > gapAfterIndex) stageIndex + 1 else stageIndex

    val step = LocalRevealStep.current
    val litCount = min(step, stages.size).coerceAtLeast(0)
    val targetSlot = if (litCount == 0) 0f else slotOf(litCount - 1).toFloat()
    val drawn = animateFloatAsState(
        targetValue = targetSlot,
        animationSpec = tween(durationMillis = 500),
        label = "timelineDrawn"
    )

    var hoveredStage by remember { mutableStateOf<Int?>(null) }
    var selectedStage by remember { mutableStateOf<Int?>(null) }
    var shownStage by remember { mutableStateOf(0) }
    val activeStage = selectedStage ?: hoveredStage
    LaunchedEffect(activeStage) {
        activeStage?.let { shownStage = it }
    }

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val slotWidth = maxWidth / slotCount
        val cardWidth = minOf(maxWidth, CardMaxWidth)
        val shownSlot = slotOf(shownStage)
        val cardLeft = (slotWidth * (shownSlot + 0.5f) - cardWidth / 2).coerceIn(0.dp, maxWidth - cardWidth)

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (slot in 0 until slotCount) {
                    val isGap = slot == gapSlot
                    val stageIndex = when {
                        gapSlot == null || slot < gapSlot -> slot
                        isGap -> -1
                        else -> slot - 1
                    }
                    val leftDashed = gapSlot != null && slot == gapSlot + 1
                    val rightDashed = gapSlot != null && slot == gapSlot - 1
                    if (isGap) {
                        TimelineSlot(
                            modifier = Modifier.weight(1f),
                            year = gapYear,
                            title = "The gap",
                            isGap = true,
                            lit = false,
                            slot = slot,
                            slotCount = slotCount,
                            leftDashed = true,
                            rightDashed = true,
                            drawn = drawn,
                            onHover = null,
                            onClick = null
                        )
                    } else {
                        val stage = stages[stageIndex]
                        TimelineSlot(
                            modifier = Modifier.weight(1f),
                            year = stage.year,
                            title = stage.title,
                            isGap = false,
                            lit = stageIndex < litCount,
                            slot = slot,
                            slotCount = slotCount,
                            leftDashed = leftDashed,
                            rightDashed = rightDashed,
                            drawn = drawn,
                            onHover = { hovered ->
                                hoveredStage = when {
                                    hovered -> stageIndex
                                    hoveredStage == stageIndex -> null
                                    else -> hoveredStage
                                }
                            },
                            onClick = {
                                selectedStage = if (selectedStage == stageIndex) null else stageIndex
                            }
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = activeStage != null,
                modifier = Modifier
                    .offset(x = cardLeft)
                    .width(cardWidth),
                enter = fadeIn(tween(200)) + expandVertically(),
                exit = fadeOut(tween(150)) + shrinkVertically()
            ) {
                TimelineDetailsCard(stage = stages[shownStage.coerceIn(stages.indices)])
            }
        }
    }
}

/**
 * One column of the timeline: a year, a node sitting on the line, and a title.
 *
 * Each slot draws the half-links on either side of its node. Progress along the line comes from [drawn], so the
 * line fills in by reading the value inside the draw phase.
 */
@Composable
private fun TimelineSlot(
    year: String,
    title: String,
    isGap: Boolean,
    lit: Boolean,
    slot: Int,
    slotCount: Int,
    leftDashed: Boolean,
    rightDashed: Boolean,
    drawn: State<Float>,
    onHover: ((Boolean) -> Unit)?,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    val scheme = MaterialTheme.colorScheme
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()

    LaunchedEffect(hovered) {
        onHover?.invoke(hovered)
    }

    val wobble = remember { Animatable(0f) }
    LaunchedEffect(hovered) {
        if (isGap && hovered) {
            repeat(3) {
                wobble.animateTo(14f, tween(durationMillis = 80))
                wobble.animateTo(-14f, tween(durationMillis = 80))
            }
            wobble.animateTo(0f, tween(durationMillis = 120))
        }
    }

    val initiallyLit = remember { lit }
    val pulse = remember { Animatable(1f) }
    LaunchedEffect(lit) {
        if (lit && !initiallyLit) {
            pulse.animateTo(1.45f, tween(durationMillis = 160, easing = FastOutSlowInEasing))
            pulse.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
        }
    }

    val nodeScale by animateFloatAsState(
        targetValue = if (lit) 1f else 0.7f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "timelineNodeScale"
    )
    val textAlpha by animateFloatAsState(
        targetValue = if (lit || isGap) 1f else 0.4f,
        animationSpec = tween(durationMillis = 300),
        label = "timelineTextAlpha"
    )

    val nodeColor = if (lit) scheme.primary else scheme.outlineVariant
    val trackColor = scheme.outlineVariant
    val litColor = scheme.primary
    val dash = PathEffect.dashPathEffect(floatArrayOf(10f, 8f))

    val slotModifier = modifier
        .padding(horizontal = Values.Dimens.tinyPadding)
        .hoverable(interactionSource = interaction)
        .let { base ->
            if (onClick != null) {
                base.clickable(interactionSource = interaction, indication = null, onClick = onClick)
            } else {
                base
            }
        }

    Column(
        modifier = slotModifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)
    ) {
        Text(
            text = year,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = if (isGap) scheme.onSurfaceVariant else if (lit) scheme.primary else scheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { alpha = textAlpha }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(NodeRowHeight)
                .drawBehind {
                    val y = size.height / 2f
                    val w = size.width
                    val cx = w / 2f
                    val stroke = LineStroke.toPx()
                    val progress = drawn.value

                    if (slot > 0) {
                        drawLine(
                            color = trackColor,
                            start = Offset(0f, y),
                            end = Offset(cx, y),
                            strokeWidth = stroke,
                            pathEffect = if (leftDashed) dash else null
                        )
                    }
                    if (slot < slotCount - 1) {
                        drawLine(
                            color = trackColor,
                            start = Offset(cx, y),
                            end = Offset(w, y),
                            strokeWidth = stroke,
                            pathEffect = if (rightDashed) dash else null
                        )
                    }

                    // Link to the left: its second half lies in this slot.
                    val leftLink = (progress - (slot - 1)).coerceIn(0f, 1f)
                    val leftFrac = (leftLink * 2f - 1f).coerceIn(0f, 1f)
                    if (slot > 0 && leftFrac > 0f) {
                        drawLine(
                            color = litColor,
                            start = Offset(0f, y),
                            end = Offset(cx * leftFrac, y),
                            strokeWidth = stroke
                        )
                    }
                    // Link to the right: its first half lies in this slot.
                    val rightLink = (progress - slot).coerceIn(0f, 1f)
                    val rightFrac = (rightLink * 2f).coerceIn(0f, 1f)
                    if (slot < slotCount - 1 && rightFrac > 0f) {
                        drawLine(
                            color = litColor,
                            start = Offset(cx, y),
                            end = Offset(cx + (w - cx) * rightFrac, y),
                            strokeWidth = stroke
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(NodeSize)
                    .graphicsLayer {
                        val s = nodeScale * pulse.value
                        scaleX = s
                        scaleY = s
                    }
                    .clip(CircleShape)
                    .background(nodeColor),
                contentAlignment = Alignment.Center
            ) {
                if (isGap) {
                    Text(
                        text = "?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = scheme.onSurfaceVariant,
                        modifier = Modifier.graphicsLayer { rotationZ = wobble.value }
                    )
                }
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isGap) scheme.onSurfaceVariant else scheme.onSurface,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { alpha = textAlpha }
        )
    }
}

/**
 * The card shown under a hovered or selected stage: its repo and the cited detail lines.
 */
@Composable
private fun TimelineDetailsCard(stage: TimelineStage, modifier: Modifier = Modifier) {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(Values.Dimens.smallPadding))
            .background(scheme.surfaceContainer)
            .padding(Values.Dimens.mediumPadding),
        verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
    ) {
        Text(
            text = "${stage.year} · ${stage.repo}",
            style = MaterialTheme.typography.labelLarge.copy(fontFamily = FontFamily.Monospace),
            color = scheme.onSurfaceVariant
        )
        stage.details.forEach { detail ->
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(Values.Dimens.tinyPadding)
                        .clip(CircleShape)
                        .background(scheme.secondaryContainer)
                )
                Spacer(modifier = Modifier.width(Values.Dimens.smallPadding))
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodyMedium,
                    color = scheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * The years missing between two stage years, for the gap marker label: "2021" for 2020 and 2022.
 */
private fun missingYears(before: String, after: String): String {
    val a = before.toIntOrNull() ?: return "?"
    val b = after.toIntOrNull() ?: return "?"
    return if (b - a == 2) "${a + 1}" else "${a + 1}–${b - 1}"
}
