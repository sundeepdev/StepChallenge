package dev.sundeep.stepchallenge.ui.common.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <E>BasicListUi(modifier: Modifier = Modifier, data: List<E>, listItem: @Composable (E) -> Unit) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(data) {
            listItem(it)
        }
    }
}