package ke.don.demos.droidcon.close

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import ke.don.demos.droidcon.common.RevealItem
import ke.don.demos.droidcon.common.SlideTitle
import ke.don.domain.values.Values

/** Number of reveal presses on [LessonsSlide]: one per lesson. Used by `SkiPresentationSlides.kt`. */
const val LESSONS_STEPS = 3

/**
 * The three lessons that close the talk, one per reveal press.
 *
 * Each lesson is a headline plus one line of evidence. Hidden lessons keep their space, so the list does not jump.
 */
@Composable
fun LessonsSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding)
    ) {
        SlideTitle(title = "Three lessons", kicker = "What a codebase that changes hands teaches")
        LessonItem(
            step = 1,
            headline = "1. Write it down for the next person",
            evidence = "The KMP reversal (c42e0cdb) was dropped with no recorded reason. The docs are the handover."
        )
        LessonItem(
            step = 2,
            headline = "2. Let CI guard it while people are away",
            evidence = "The PR workflow and the size gate (d66ca423, pr.yml) check every change."
        )
        LessonItem(
            step = 3,
            headline = "3. Make it easy to start",
            evidence = "Join with a pull request, the way every maintainer started."
        )
    }
}

/**
 * The closing question slide, with the three presenter names and no footer.
 *
 * No reveal. Registered with `showFooter = false`.
 */
@Composable
fun QaSlide() {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.Dimens.largePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Questions?",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            color = scheme.primary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Values.Dimens.mediumPadding))
        Text(
            text = "Annunziata Kinya, Donald Isoe and Ian Nthuli",
            style = MaterialTheme.typography.headlineSmall,
            color = scheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * One lesson that appears at its reveal step: a headline and one evidence line.
 *
 * @param step the 1-based reveal step at which the lesson shows.
 * @param headline the numbered lesson in bold.
 * @param evidence the one line that backs the lesson.
 */
@Composable
private fun LessonItem(step: Int, headline: String, evidence: String) {
    val scheme = MaterialTheme.colorScheme
    RevealItem(index = step) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Values.Dimens.smallPadding),
            verticalArrangement = Arrangement.spacedBy(Values.Dimens.tinyPadding)
        ) {
            Text(
                text = headline,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = scheme.primary
            )
            Text(
                text = evidence,
                style = MaterialTheme.typography.headlineSmall,
                color = scheme.onSurface
            )
        }
    }
}
