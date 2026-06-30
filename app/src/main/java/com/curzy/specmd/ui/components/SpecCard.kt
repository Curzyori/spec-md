package com.curzy.specmd.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.curzy.specmd.R
import com.curzy.specmd.domain.model.SpecItem
import com.curzy.specmd.domain.model.SpecSection
import com.curzy.specmd.ui.theme.BgBase
import com.curzy.specmd.ui.theme.BgElevated
import com.curzy.specmd.ui.theme.Border
import com.curzy.specmd.ui.theme.CodeTypography
import com.curzy.specmd.ui.theme.TextSecondary
import com.curzy.specmd.ui.theme.TextTertiary

@Composable
fun SpecCard(
    section: SpecSection,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BgElevated)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { expanded = !expanded }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = section.icon,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = section.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = if (expanded) "▼" else "▶",
                color = TextSecondary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.semantics {
                    contentDescription = if (expanded) "Collapse section" else "Expand section"
                }
            )
        }

        if (expanded) {
            Spacer(modifier = Modifier.height(12.dp))

            section.items.forEachIndexed { index, item ->
                SpecRow(
                    item = item,
                    isLast = index == section.items.lastIndex
                )
            }
        }
    }
}

@Composable
private fun SpecRow(
    item: SpecItem,
    isLast: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.label,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                modifier = Modifier.weight(0.4f)
            )

            Text(
                text = if (item.isAvailable) item.value else stringResource(id = R.string.label_na),
                style = CodeTypography,
                color = if (item.isAvailable)
                    MaterialTheme.colorScheme.onSurface
                else
                    TextTertiary,
                modifier = Modifier.weight(0.6f)
            )
        }

        if (!isLast) {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
