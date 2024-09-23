package com.example.githubsearchapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubsearchapp.ui.theme.ErrorScreenBackgroundColor
import com.example.githubsearchapp.ui.theme.ErrorScreenButtonBackgroundColor
import com.example.githubsearchapp.ui.theme.ErrorScreenButtonTextColor

@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ErrorScreenBackgroundColor), // Semi-transparent background
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .widthIn(min = 300.dp, max = 400.dp)
                .padding(vertical = 60.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = errorMessage,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(200.dp),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,

                )
            TextButton(
                onClick = { onRetry() },
                Modifier.background(
                    color = ErrorScreenButtonBackgroundColor,
                    shape = RoundedCornerShape(5.dp)
                ).padding(vertical = 4.dp, horizontal = 10.dp)
            ) {
                Text(text = "Retry", fontSize = 24.sp, color = ErrorScreenButtonTextColor)
            }
        }

    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(errorMessage = "No Internet", onRetry = {})
}