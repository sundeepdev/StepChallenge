package com.publicissapient.stepchallenge.ui.steps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.publicissapient.stepchallenge.domain.entity.StepData

@Composable
fun StepListItem(step: StepData) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${step.count}",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = step.stepDataDisplayDate(),
                color = Color.DarkGray,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}