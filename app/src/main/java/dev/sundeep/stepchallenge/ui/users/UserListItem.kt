package dev.sundeep.stepchallenge.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.sundeep.stepchallenge.R
import dev.sundeep.stepchallenge.domain.entity.User

@Composable
fun UserListItem(modifier: Modifier = Modifier, user: User) {
    Card (
        modifier = modifier,
    ) {
        Row(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.subtitle1
                )
            }
            if (user.isAdmin) {
                Text(
                    text = stringResource(R.string.user_list_label_for_admin_user),
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Green,
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}

@Preview
@Composable
fun UserListItemPreview() {

}