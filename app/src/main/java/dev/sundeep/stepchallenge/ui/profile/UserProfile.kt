package dev.sundeep.stepchallenge.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserProfile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Replace with your own image resource
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Person Icon",
            modifier = Modifier
                .padding(16.dp)
                .scale(2.0F)
                .size(100.dp)
        )

//        Image(
//            painter = imageResource,
//            contentDescription = "Profile Image",
//            modifier = Modifier
//                .size(100.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "John Doe",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Age: 30",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Email: john.doe@example.com",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Bio: A passionate software developer who loves creating innovative solutions and exploring new technologies.",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Justify
        )
    }
}
