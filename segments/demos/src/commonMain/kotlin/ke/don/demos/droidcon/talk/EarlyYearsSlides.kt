package ke.don.demos.droidcon.talk

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ke.don.demos.droidcon.common.BarChart
import ke.don.demos.droidcon.common.BulletList
import ke.don.demos.droidcon.common.CalloutCard
import ke.don.demos.droidcon.common.ChartPoint
import ke.don.demos.droidcon.common.MediaPlaceholder
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.demos.droidcon.common.popIn
import ke.don.domain.values.Values
import kotlin.math.roundToInt

private const val SWEEP_MILLIS = 1400
private const val COUNT_UP_MILLIS = 900
private const val PACKAGE_DELAY_MILLIS = 400
private const val SHRUG_MILLIS = 600
private const val SHARED_README_LINE = "…sessions, office hours, app reviews, codelabs…"

/**
 * S06. Started in Java, with the Firebase stack and the Java file count growing month by month.
 * The chart plots Java files from D6. The April 2019 bar is the one that collapses to two.
 */
@Composable
fun Y2018FirstSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "2018: the first one", kicker = "Act 1")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                BulletList(
                    items = listOf(
                        "Started in Java, with a Kotlin branch alongside",
                        "Firebase for everything: Auth, Realtime DB, Firestore, FCM, Remote Config, Crashlytics",
                        "Cloud Functions pushed notifications for starred sessions",
                        "A map pointing at iHub",
                        "ButterKnife · Koin · Room + RxJava · minSdk 21"
                    )
                )
            }
            BarChart(
                points = listOf(
                    ChartPoint(label = "May 2018", value = 69f),
                    ChartPoint(label = "Jul 2018", value = 92f),
                    ChartPoint(label = "Sep 2018", value = 100f),
                    ChartPoint(label = "Dec 2018", value = 108f),
                    ChartPoint(label = "Apr 2019", value = 2f, highlight = true)
                ),
                modifier = Modifier.weight(1f),
                valueSuffix = " Java files"
            )
        }
        Text(
            text = "Kotlin files: 0 in Dec 2018, 107 in Apr 2019 · Source: droidconKE git history (D6)",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * S07. The highlight of act 1: the I/O clone. Two READMEs sit side by side, and a highlight sweeps
 * across their matching line once, when the slide first composes. Below them, the screen-name table.
 */
@Composable
fun Y2018CloneSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Born as a clone of Google's I/O app", kicker = "Act 1 · the highlight")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            ScreenshotPair(label = "I/O 2018 app: schedule.png (google/iosched)", year = "I/O 2018", modifier = Modifier.weight(1f))
            ScreenshotPair(label = "droidconKE 2018: schedule.png", year = "droidconKE 2018", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        val sweep = remember { Animatable(0f) }
        LaunchedEffect(Unit) {
            sweep.animateTo(1f, tween(durationMillis = SWEEP_MILLIS, easing = FastOutSlowInEasing))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            ReadmeLine(
                readme = "I/O README",
                text = SHARED_README_LINE,
                sweep = sweep.value,
                modifier = Modifier.weight(1f)
            )
            ReadmeLine(
                readme = "droidconKE README",
                text = SHARED_README_LINE,
                sweep = sweep.value,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Column(verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)) {
            ScreenNameRow(iosName = "fragment_info", droidconName = "fragment_info")
            ScreenNameRow(iosName = "fragment_event", droidconName = "fragment_event")
            ScreenNameRow(iosName = "fragment_travel", droidconName = "fragment_travel")
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: droidconKE/iosched (fork of google/iosched), commits of 2018-03-28 and 2018-08-13; both READMEs",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * One screenshot placeholder with its name above it, used side by side on S07.
 *
 * @param label What goes in the placeholder.
 * @param year The name shown above the screenshot.
 */
@Composable
private fun ScreenshotPair(label: String, year: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = year,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            MediaPlaceholder(label = label)
        }
    }
}

/**
 * One README, showing the sentence both READMEs share. The highlight behind it grows with [sweep], so both
 * cards light up in step, as one sweep across the pair.
 *
 * @param readme The label above the card.
 * @param text The shared sentence.
 * @param sweep How far the highlight has reached, from 0 to 1.
 */
@Composable
private fun ReadmeLine(readme: String, text: String, sweep: Float, modifier: Modifier = Modifier) {
    val highlight = MaterialTheme.colorScheme.secondaryContainer
    val corner = Values.Dimens.tinyPadding
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)) {
        Text(
            text = readme,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRoundRect(
                        color = highlight,
                        size = Size(size.width * sweep, size.height),
                        cornerRadius = CornerRadius(corner.toPx())
                    )
                }
                .padding(horizontal = Values.Dimens.tinyPadding)
        )
    }
}

/**
 * One row of the screen-name table: the I/O name, an equals sign, and the droidconKE name.
 *
 * @param iosName The screen name in the I/O app.
 * @param droidconName The same screen name in droidconKE.
 */
@Composable
private fun ScreenNameRow(iosName: String, droidconName: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = iosName,
            style = MaterialTheme.typography.labelLarge.copy(fontFamily = FontFamily.Monospace),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(200.dp)
        )
        Text(
            text = "=",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(32.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = droidconName,
            style = MaterialTheme.typography.labelLarge.copy(fontFamily = FontFamily.Monospace),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * S08. All Kotlin and all Jetpack. The package name enters the slide last, as a chip that pops in.
 */
@Composable
fun Y2019Slide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "2019: all Kotlin, all Jetpack", kicker = "Act 2")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                BulletList(
                    items = listOf(
                        "100% Kotlin from day one",
                        "Jetpack Navigation + Safe Args · Firestore KTX · Koin · LiveData · Firebase Performance",
                        "Filters, announcements, session feedback, venue map",
                        "Travis CI"
                    )
                )
                PackageChip(name = "com.android254.droidconke19")
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                MediaPlaceholder(label = "2019 schedule.png", modifier = Modifier.weight(1f))
                MediaPlaceholder(label = "2019 sesion_details.png", modifier = Modifier.weight(1f))
            }
        }
        Text(
            text = "Source: droidconKE2019App, 153 commits, Jul to Nov 2019",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * The package name as a monospace chip. It pops in after the bullets have landed.
 *
 * @param name The application ID, exactly as it appears in the manifest.
 */
@Composable
private fun PackageChip(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .popIn(delayMillis = PACKAGE_DELAY_MILLIS)
            .clip(RoundedCornerShape(Values.Dimens.tinyPadding))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = Values.Dimens.mediumPadding, vertical = Values.Dimens.smallPadding)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Monospace),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

/**
 * S09. The modular one: 13 modules, 7 of them dynamic features. The module count counts up from zero.
 */
@Composable
fun Y2020ModularSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "2020: the modular one", kicker = "Act 3")
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Values.Dimens.largePadding)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
            ) {
                ModuleCountUp(target = MODULE_COUNT)
                Text(
                    text = "of them dynamic features",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                BulletList(
                    items = listOf(
                        "Kotlin DSL · buildSrc · ktlint · detekt · GitHub Actions",
                        "Retrofit · Room · Coil · data binding · MockK",
                        "15 authors · 120 stars, the most in the org"
                    )
                )
            }
            MediaPlaceholder(
                label = "settings.gradle.kts include(...) block, with :features:* highlighted",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(Values.Dimens.smallPadding))
        Text(
            text = "Source: droidconKE2020App settings.gradle.kts and buildSrc/.../Dependencies.kt; architecture diagram in the repo",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/** The number of modules in the 2020 app's `settings.gradle.kts`, from Appendix D. */
private const val MODULE_COUNT = 13

/**
 * The module count as a large figure. It counts up from zero when the slide first composes.
 *
 * @param target The final count.
 */
@Composable
private fun ModuleCountUp(target: Int) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        progress.animateTo(1f, tween(durationMillis = COUNT_UP_MILLIS, easing = FastOutSlowInEasing))
    }
    Text(
        text = (target * progress.value).roundToInt().toString(),
        style = MaterialTheme.typography.displayLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

/** The two README lines, one from 2020 and one from 2022, with 2021 missing between them. */
private const val NO_CONFERENCE_2020 = "…the 3rd Android Developer conference- droidcon to be held in Nairobi from August 6-8th 2020."
private const val NO_CONFERENCE_2022 = "…the 3rd Android Developer conference- droidcon to be held in Nairobi from November 16-18th 2022."

/**
 * S10. The conference that didn't happen. The two README lines are stacked, and the "pass the mic" callout
 * names the 2020 team. The 2021 gap gets a shrug that settles in once.
 */
@Composable
fun Y2020NoConferenceSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "The conference that didn't happen", kicker = "Act 3")
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
        ) {
            Text(
                text = NO_CONFERENCE_2020,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = NO_CONFERENCE_2022,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Values.Dimens.mediumPadding)
            ) {
                Text(
                    text = "2021: nothing",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Shrug()
            }
            CalloutCard(title = "Pass the mic") {
                Text(
                    text = "Top 2020 authors: Oliver Muthomi, Jabez Magomere and David Odari. Pass the mic to one of them.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Text(
            text = "Source: droidconKE2020App and droidconKE2022Android READMEs",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * A text shrug that tilts once, then settles upright, beside the 2021 gap.
 */
@Composable
private fun Shrug(modifier: Modifier = Modifier) {
    val tilt = remember { Animatable(-20f) }
    LaunchedEffect(Unit) {
        tilt.animateTo(0f, tween(durationMillis = SHRUG_MILLIS, easing = FastOutSlowInEasing))
    }
    Text(
        text = "¯\\_(ツ)_/¯",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.graphicsLayer { rotationZ = tilt.value }
    )
}
