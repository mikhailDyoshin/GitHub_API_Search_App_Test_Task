package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.ui.theme.ScoreColor

@Composable
fun UserItem(userState: SearchListItemState.UserState) {
    Row(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .height(80.dp)
                .weight(1f)
        ) {
            AsyncImage(
                model = userState.avatarURL,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))

            )
        }

        Text(
            text = userState.name,
            maxLines = 1,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = userState.score.toString(),
            fontSize = 20.sp,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = ScoreColor,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun UserItemPreview() {
    UserItem(
        userState = SearchListItemState.UserState(
            name = "John Malkovich",
            avatarURL = "https://avatars.githubusercontent.com/u/65956?v=4",
            score = 1.0f
        )
    )
}