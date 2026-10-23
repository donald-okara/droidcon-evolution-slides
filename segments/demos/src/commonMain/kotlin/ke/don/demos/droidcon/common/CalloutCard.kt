package ke.don.demos.droidcon.common

import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ke.don.domain.values.Values

/**
 * A rounded, outlined card with a small title chip, for a side note such as "Kotlin Kenya corner".
 *
 * @param title The text on the chip at the top of the card.
 * @param content The card body, laid out in a column below the chip.
 */
@Composable
fun CalloutCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(Values.Dimens.smallPadding))
            .border(1.dp, scheme.outline, RoundedCornerShape(Values.Dimens.smallPadding))
            .padding(Values.Dimens.mediumPadding),
        verticalArrangement = Arrangement.spacedBy(Values.Dimens.smallPadding)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = scheme.onSecondaryContainer,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(scheme.secondaryContainer)
                .padding(horizontal = Values.Dimens.smallPadding, vertical = Values.Dimens.tinyPadding)
        )
        content()
    }
}

