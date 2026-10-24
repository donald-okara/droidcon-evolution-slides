package ke.don.demos.droidcon.talk

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.don.demos.droidcon.common.ChartPoint
import ke.don.demos.droidcon.common.ColumnChart
import ke.don.demos.droidcon.common.Confetti
import ke.don.demos.droidcon.common.RevealItem
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.SourceLine
import ke.don.demos.droidcon.common.popIn
import ke.don.design.theme.LocalChaiColors
import ke.don.domain.values.Values
import kotlin.math.PI
import kotlin.math.roundToInt
import kotlin.math.sin

/** The number of reveal presses on [EightYearsTeachSlide]: one per lesson. Used by `SkiPresentationSlides.kt`. */
const val EIGHT_YEARS_STEPS = 4

private const val NAME_STAGGER_MILLIS = 80
private const val ROW_STAGGER_MILLIS = 320
private const val WIGGLE_DEGREES = 12f
private const val WIGGLE_HALF_PERIOD_MILLIS = 220
private const val LESSON_NUMBER_SIZE_DP = 44
private const val CREDIT_CARD_WIDTH_DP = 300
private const val CREDIT_CARD_HEIGHT_DP = 220
private const val CREDIT_CARD_CORNER_DP = 16
private const val CREDIT_CARD_GAP_DP = 16
private const val CREDIT_STRIPE_DP = 6
private const val CREDIT_AVATAR_DP = 44
private const val CREDIT_HOVER_SCALE = 1.05f
private const val CREDIT_REST_LIFT_DP = 2
private const val CREDIT_HOVER_LIFT_DP = 10
private const val CREDIT_HOVER_MILLIS = 200
private const val CREDITS_VELOCITY_DP = 36f
private const val NANOS_PER_SECOND = 1_000_000_000f
private const val NAME_BOB_AMPLITUDE_DP = 3f
private const val NAME_BOB_PERIOD_MILLIS = 2400
private const val NAME_BOB_PHASE_STEP = 0.12f
private const val CONTRIBUTOR_COUNT_MILLIS = 1200
private const val PILL_PADDING_HORIZONTAL_DP = 12
private const val PILL_PADDING_VERTICAL_DP = 4
private val PILL_SHAPE = RoundedCornerShape(percent = 50)

/**
 * One credit card on [CreditsSlide]: who, which era, their biggest contribution, and how many commits they made.
 */
private data class Credit(val username: String, val era: String, val contribution: String, val commits: Int)

private val thankYouCredits = listOf(
    Credit("wangerekaharun", "2018", "Started the app: first agenda and session UI, later moved it to Firestore", 182),
    Credit("Michael Bukachi", "2018", "Set up Kotlin, added a base ViewModel and the first tests", 19),
    Credit("muhosteve", "2018", "Firebase sign-in, offline data and Cloud Functions", 18),
    Credit("Harun Wangereka", "2019", "Sign-in screens, favourites filter, navigation drawer", 83),
    Credit("Michael Bukachi", "2019", "Sign-in logic, starred sessions, CI and releases", 56),
    Credit("Harun Wangereka", "2020", "Created the modular app and its screen flow", 101),
    Credit("Oliver Muthomi", "2020", "Home, sponsors and session details, plus tests with fakes", 98),
    Credit("Michael Bukachi", "2020", "API endpoints, login token handling, deploy pipeline", 94),
    Credit("Gibson Silali", "2022", "Sessions screen: session cards and the day filter", 84),
    Credit("Harun Wangereka", "2022", "Material 3 setup and convention plugins", 82),
    Credit("Juma Allan", "2022", "Data layer: Hilt, Room on KSP, logging", 58),
    Credit("chepsi", "2023–25", "Founded droidconKeKotlin, kept CI green, set up KMM", 61),
    Credit("Michael Ndiritu", "2023–25", "Compose Multiplatform and the iOS app", 41),
    Credit("Raynafs", "2023–25", "Code coverage and the CI matrix", 29),
    Credit("Harun Wangereka", "2026", "New look (Expressive + rebrand), 17-module split, APK -28%, cube", 64),
    Credit("donald-okara", "2026", "Navigation 3, session stepper, periodic sync, notifications, Glance widget", 107),
    Credit("mutukuian", "2026", "Redesigned the speakers list and details screens", 7)
)

/**
 * S27. Lost and found. A two-column "then and now" table: ideas from the 2018 and 2019 repos, what became of
 * each, and what is still missing. Rows drop in one after another, so the eye reads across each row.
 */
@Composable
fun LostAndFoundSlide() {
    val scheme = MaterialTheme.colorScheme
    val rows = listOf(
        Triple("Starred-session pushes", "2018", "#448 (open)"),
        Triple("Venue map", "2018–19", "§11.6 (needs an SVG)"),
        Triple("Phones and tablets", "Promised 2018", "Kept 2026"),
        Triple("Travel details", "2018–19", "Gone. Should it come back?")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Lost and found: what came back, what didn't", kicker = "Act 7")
        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("Idea", "Then", "Now").forEachIndexed { index, header ->
                Text(
                    text = header,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = scheme.onSurfaceVariant,
                    modifier = Modifier.weight(if (index == 0) 1.2f else 1f)
                )
            }
        }
        rows.forEachIndexed { index, (idea, then, now) ->
            val lastRow = index == rows.lastIndex
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Values.Dimens.smallPadding)
                    .popIn(delayMillis = index * ROW_STAGGER_MILLIS)
            ) {
                Text(
                    text = idea,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = scheme.primary,
                    modifier = Modifier.weight(1.2f)
                )
                Text(
                    text = then,
                    style = MaterialTheme.typography.titleMedium,
                    color = scheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = now,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (lastRow) scheme.secondary else scheme.onSurface,
                    fontWeight = if (lastRow) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        SourceLine(text = "Source: package lists of the 2018 and 2019 repos; plan §11.6; #410; #448")
    }
}

/**
 * S28. The uncomfortable chart. Two bar charts side by side: people committing per year (D5), which stays flat
 * after 2023, against Kotlin lines of code (D4), which grows. The community bar does not move; the code bar does.
 */
@Composable
fun UncomfortableChartSlide() {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The uncomfortable chart", kicker = "Act 7")
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            Text(
                text = "5",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = scheme.primary
            )
            Column(modifier = Modifier.padding(bottom = Values.Dimens.smallPadding)) {
                Text(
                    text = "people committing in 2026 (41 in 2023)",
                    style = MaterialTheme.typography.titleLarge,
                    color = scheme.onSurface
                )
                Text(
                    text = "The code grew. The community didn't.",
                    style = MaterialTheme.typography.titleMedium,
                    color = scheme.onSurfaceVariant,
                    modifier = Modifier.popIn(delayMillis = 800)
                )
            }
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            ColumnChart(
                points = listOf(
                    ChartPoint(label = "2023", value = 41f, highlight = true),
                    ChartPoint(label = "2024", value = 8f),
                    ChartPoint(label = "2025", value = 9f),
                    ChartPoint(label = "2026", value = 5f)
                ),
                caption = "People committing per year, bots excluded (D5)",
                modifier = Modifier.weight(1f).fillMaxSize()
            )
            ColumnChart(
                points = listOf(
                    ChartPoint(label = "Mar 23", value = 14.7f),
                    ChartPoint(label = "Dec 24", value = 17.4f),
                    ChartPoint(label = "Oct 1", value = 27.8f),
                    ChartPoint(label = "Oct 22", value = 39.9f, highlight = true)
                ),
                caption = "Kotlin lines of code, thousands (D4)",
                modifier = Modifier.weight(1f).fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        SourceLine(text = "Source: distinct author names per year, bots excluded (D5)")
    }
}

/**
 * S29. What eight years teach. Four lessons, one per reveal press. Each lesson is one sentence beside a numbered
 * disc that fills in as its lesson arrives. Hidden lessons keep their space, so the list does not jump.
 */
@Composable
fun EightYearsTeachSlide() {
    val lessons = listOf(
        "Copy the best, then stand on your own",
        "Repos with a year in the name reset the community",
        "Removing an experiment is still progress",
        "Write it down for whoever comes next, human or agent"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "What eight years teach", kicker = "Act 7")
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Column(verticalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)) {
            lessons.forEachIndexed { index, lesson ->
                EightYearsLesson(number = index + 1, text = lesson)
            }
        }
    }
}

/**
 * S30. Thank you. The contributors grouped by era, one column per era, each era headed by a coloured pill. Confetti
 * bursts once as the slide opens, behind the names. A large figure counts up to the total number of names, and each
 * name pops in as the slide opens, one after another, in reading order, then bobs gently in a wave. Not
 * reveal-stepped, so the slide never waits on a press.
 */
@Composable
fun ThankYouSlide() {
    val scheme = MaterialTheme.colorScheme
    val eras = listOf(
        "2018–19" to listOf("Harun Wangereka", "Michael Bukachi", "muhosteve", "Victor", "johnGachihi"),
        "2020" to listOf(
            "Oliver Muthomi", "Benard Mathu", "David Odari", "Jabez Magomere", "philomenambura", "Jama Mohamed"
        ),
        "2022" to listOf(
            "Gibson Silali", "Waitara", "Theophilus Kibet", "Evans Chepsiror", "Juma Allan", "tamzi"
        ),
        "2023–25" to listOf(
            "Hannah Olukoye", "Peter Chege", "Kenneth Mathari", "Yves Kalume", "robert-nganga",
            "Raynafs", "Michael Ndiritu", "MamboBryan", "brian.orwe"
        ),
        "2026" to listOf("donald-okara", "Joel Kanyi", "mutukuian")
    )
    val contributorCount = eras.sumOf { it.second.size }
    var celebrate by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { celebrate = true }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Thank you", kicker = "Act 7")
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            ContributorCount(target = contributorCount)
            Text(
                text = "people, eight years",
                style = MaterialTheme.typography.titleLarge,
                color = scheme.onSurface,
                modifier = Modifier.padding(bottom = Values.Dimens.smallPadding)
            )
        }
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Confetti(trigger = celebrate, modifier = Modifier.fillMaxSize())
            EraColumns(eras = eras, modifier = Modifier.fillMaxSize())
        }
        Text(
            text = "Ask anyone from the list who is in the room to stand up.",
            style = MaterialTheme.typography.titleMedium,
            color = scheme.onSurfaceVariant
        )
        SourceLine(text = "Source: git shortlog -sn per repo; checked against the README contributors section")
    }
}

/**
 * S30b. Credits. The top contributors of each era as one row of cards that scrolls sideways without end. Each card
 * names a contributor, their era, their biggest contribution and their commit count. Hovering the row pauses it, and
 * the hovered card lifts, so a presenter can point at one. Not reveal-stepped.
 */
@Composable
fun CreditsSlide() {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Credits", kicker = "Act 7")
        Text(
            text = "Top contributors, era by era",
            style = MaterialTheme.typography.titleMedium,
            color = scheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            CreditsMarquee(credits = thankYouCredits)
        }
        SourceLine(text = "Source: git shortlog -sn per repo, bots excluded; contributions summarised from each person's commits")
    }
}

/**
 * The colour pair for one era: the fill of its pill, avatar and stripe, and the readable text colour on that fill.
 */
private data class EraStyle(val fill: Color, val content: Color)

/**
 * The one era-to-colour mapping, shared by [ThankYouSlide] and [CreditsSlide]. 2018 and 2019 share the 2018–19
 * colour. The colours are Material roles, so they follow light and dark themes, except 2026, which uses the Chai
 * hero blue with its white content colour.
 *
 * @param era the era label, as written on the slide.
 */
@Composable
private fun eraStyle(era: String): EraStyle {
    val scheme = MaterialTheme.colorScheme
    val chai = LocalChaiColors.current
    return when (era) {
        "2018–19", "2018", "2019" -> EraStyle(scheme.primary, scheme.onPrimary)
        "2020" -> EraStyle(scheme.secondary, scheme.onSecondary)
        "2022" -> EraStyle(scheme.tertiary, scheme.onTertiary)
        "2023–25" -> EraStyle(scheme.primaryContainer, scheme.onPrimaryContainer)
        "2026" -> EraStyle(chai.heroContainerColor, chai.heroOnContainerColor)
        else -> EraStyle(scheme.surfaceVariant, scheme.onSurfaceVariant)
    }
}

/**
 * An era label in a rounded pill, filled with the era's colour from [eraStyle].
 *
 * @param era the era label to show.
 * @param modifier applied outside the pill, for spacing.
 */
@Composable
private fun EraPill(era: String, modifier: Modifier = Modifier) {
    val style = eraStyle(era)
    Text(
        text = era,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        color = style.content,
        maxLines = 1,
        modifier = modifier
            .clip(PILL_SHAPE)
            .background(style.fill)
            .padding(horizontal = PILL_PADDING_HORIZONTAL_DP.dp, vertical = PILL_PADDING_VERTICAL_DP.dp)
    )
}

/**
 * The contributor total as a large figure. It counts up from zero when the slide first composes.
 *
 * @param target The final count.
 */
@Composable
private fun ContributorCount(target: Int) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        progress.animateTo(1f, tween(durationMillis = CONTRIBUTOR_COUNT_MILLIS, easing = FastOutSlowInEasing))
    }
    Text(
        text = (target * progress.value).roundToInt().toString(),
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

/**
 * The contributors' names, one column per era. Each era has an [EraPill] heading. Each name pops in one after
 * another, in reading order, then bobs up and down. One infinite transition drives every name, and each name is
 * offset in phase by its position, so the bob moves as a wave down the list. The bob is a `graphicsLayer`
 * translation, so it never changes the layout.
 */
@Composable
private fun EraColumns(eras: List<Pair<String, List<String>>>, modifier: Modifier = Modifier) {
    val bob = rememberInfiniteTransition(label = "thankYouBob")
    val wave by bob.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = NAME_BOB_PERIOD_MILLIS, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "thankYouBobWave"
    )
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
    ) {
        eras.forEachIndexed { eraIndex, (era, names) ->
            val namesBefore = eras.take(eraIndex).sumOf { it.second.size }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)
            ) {
                EraPill(era = era, modifier = Modifier.padding(bottom = Values.Dimens.tinyPadding))
                names.forEachIndexed { nameIndex, name ->
                    val order = namesBefore + nameIndex
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .popIn(delayMillis = order * NAME_STAGGER_MILLIS)
                            .graphicsLayer {
                                translationY = sin((wave + order * NAME_BOB_PHASE_STEP) * 2f * PI.toFloat()) *
                                    NAME_BOB_AMPLITUDE_DP.dp.toPx()
                            }
                    )
                }
            }
        }
    }
}

/**
 * A single row of [CreditCard]s that scrolls sideways endlessly. The row takes only the height its cards need.
 *
 * Not `basicMarquee`: that slides a pre-drawn image of the row by fractions of a pixel each frame, which makes text
 * and card borders shimmer on web. Here the cards are drawn twice, back to back, and the row is placed at a
 * whole-pixel offset. A frame loop adds to the offset each frame and wraps it at the width of one copy, so the loop
 * is seamless and crisp. Each copy ends with a gap the same width as the gaps between cards, so the seam matches.
 *
 * Hovering the row pauses the loop. The offset is kept, so scrolling resumes from the same place, with no jump.
 *
 * @param credits the cards, in the order they are shown.
 * @param modifier applied outside the marquee, so padding here does not scroll.
 */
@Composable
private fun CreditsMarquee(credits: List<Credit>, modifier: Modifier = Modifier) {
    val pxPerSecond = with(LocalDensity.current) { CREDITS_VELOCITY_DP.dp.toPx() }
    var copyWidthPx by remember { mutableIntStateOf(0) }
    var offsetPx by remember { mutableFloatStateOf(0f) }
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    LaunchedEffect(copyWidthPx, pxPerSecond) {
        if (copyWidthPx == 0) return@LaunchedEffect
        var lastFrameNanos = Long.MIN_VALUE
        while (true) {
            withFrameNanos { frameNanos ->
                if (lastFrameNanos != Long.MIN_VALUE && !hovered) {
                    val seconds = (frameNanos - lastFrameNanos) / NANOS_PER_SECOND
                    offsetPx = (offsetPx + seconds * pxPerSecond) % copyWidthPx
                }
                lastFrameNanos = frameNanos
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clipToBounds()
            .hoverable(interactionSource = interactionSource)
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.Start, unbounded = true)
                .offset { IntOffset(-offsetPx.roundToInt(), 0) }
        ) {
            repeat(2) { copy ->
                Row(
                    modifier = if (copy == 0) Modifier.onSizeChanged { copyWidthPx = it.width } else Modifier
                ) {
                    credits.forEach { credit ->
                        CreditCard(credit = credit)
                        Spacer(modifier = Modifier.width(CREDIT_CARD_GAP_DP.dp))
                    }
                }
            }
        }
    }
}

/**
 * One credit on [CreditsMarquee]. The card has a fixed size, so every card lines up. From the top: an era-coloured
 * stripe, the initials avatar with the username and [EraPill], the contribution, and a commit badge at the bottom.
 * Hovering a card lifts it: it scales up slightly and its shadow deepens, both animated.
 *
 * @param credit the contributor to show.
 */
@Composable
private fun CreditCard(credit: Credit) {
    val scheme = MaterialTheme.colorScheme
    val style = eraStyle(credit.era)
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val lift by animateFloatAsState(
        targetValue = if (hovered) CREDIT_HOVER_SCALE else 1f,
        animationSpec = tween(durationMillis = CREDIT_HOVER_MILLIS),
        label = "creditLift"
    )
    val elevation by animateDpAsState(
        targetValue = if (hovered) CREDIT_HOVER_LIFT_DP.dp else CREDIT_REST_LIFT_DP.dp,
        animationSpec = tween(durationMillis = CREDIT_HOVER_MILLIS),
        label = "creditElevation"
    )
    val shape = RoundedCornerShape(CREDIT_CARD_CORNER_DP.dp)
    Column(
        modifier = Modifier
            .width(CREDIT_CARD_WIDTH_DP.dp)
            .height(CREDIT_CARD_HEIGHT_DP.dp)
            .graphicsLayer {
                scaleX = lift
                scaleY = lift
            }
            .hoverable(interactionSource = interactionSource)
            .shadow(elevation = elevation, shape = shape, clip = false)
            .clip(shape)
            .background(scheme.surfaceContainer)
            .border(1.dp, scheme.outlineVariant, shape)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(CREDIT_STRIPE_DP.dp)
                .background(style.fill)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(Values.Dimens.mediumPadding),
            verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                Box(
                    modifier = Modifier
                        .size(CREDIT_AVATAR_DP.dp)
                        .clip(CircleShape)
                        .background(style.fill),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = initialsOf(credit.username),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = style.content,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)
                ) {
                    Text(
                        text = credit.username,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = scheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    EraPill(era = credit.era)
                }
            }
            Text(
                text = credit.contribution,
                style = MaterialTheme.typography.bodyLarge,
                color = scheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            CommitBadge(commits = credit.commits)
        }
    }
}

/**
 * The commit count on a [CreditCard], as a small pill, for example "182 commits".
 *
 * @param commits the number of commits to show.
 */
@Composable
private fun CommitBadge(commits: Int) {
    val scheme = MaterialTheme.colorScheme
    Text(
        text = if (commits == 1) "1 commit" else "$commits commits",
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        color = scheme.onSurface,
        maxLines = 1,
        modifier = Modifier
            .clip(PILL_SHAPE)
            .background(scheme.surfaceContainerHighest)
            .padding(horizontal = PILL_PADDING_HORIZONTAL_DP.dp, vertical = PILL_PADDING_VERTICAL_DP.dp)
    )
}

/**
 * The initials for an avatar: the first letters of the first two words, or the first two letters of a one-word
 * handle. Hyphens count as word breaks, so "donald-okara" gives "DO".
 *
 * @param username the name or handle to take initials from.
 */
private fun initialsOf(username: String): String {
    val words = username.split(' ', '-').filter { it.isNotBlank() }
    val initials = if (words.size >= 2) "${words[0].first()}${words[1].first()}" else username.take(2)
    return initials.uppercase()
}

/**
 * S31. Now, let's break it. The handover to the bounty. A large cockroach wiggles on the slide, and two
 * lines say what is about to start and what to have ready.
 */
@Composable
fun BreakItSlide() {
    val scheme = MaterialTheme.colorScheme
    val transition = rememberInfiniteTransition(label = "breakItWiggle")
    val wiggle by transition.animateFloat(
        initialValue = -WIGGLE_DEGREES,
        targetValue = WIGGLE_DEGREES,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = WIGGLE_HALF_PERIOD_MILLIS, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breakItWiggleAngle"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🪳",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 160.sp),
            modifier = Modifier.graphicsLayer {
                rotationZ = wiggle
                transformOrigin = TransformOrigin.Center
            }
        )
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Now, let's break it",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = scheme.primary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Msako wa Mende starts in 10 minutes",
            style = MaterialTheme.typography.headlineSmall,
            color = scheme.onSurface,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Phone ready · app updated · GitHub account open",
            style = MaterialTheme.typography.titleMedium,
            color = scheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * One lesson on [EightYearsTeachSlide]: a numbered disc and one sentence. It appears at its reveal step.
 *
 * @param number the 1-based lesson number, which is also its reveal step.
 * @param text the lesson sentence.
 */
@Composable
private fun EightYearsLesson(number: Int, text: String) {
    val scheme = MaterialTheme.colorScheme
    RevealItem(index = number) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            Box(
                modifier = Modifier
                    .size(LESSON_NUMBER_SIZE_DP.dp)
                    .clip(CircleShape)
                    .background(scheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$number",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = scheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium,
                color = scheme.onSurface
            )
        }
    }
}
