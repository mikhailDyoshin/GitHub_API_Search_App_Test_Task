package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(text: String, updateSearchInput: (searchInput: String) -> Unit, search: () -> Unit) {

    val backgroundColor = Color(0xFFCCCBCB)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = text,
            onValueChange = {
                updateSearchInput(it)
            },
            label = { Text(text = "Enter keyword") },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(fraction = 0.84f),
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.Gray,
                focusedTextColor = Color.Black,
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                disabledContainerColor = backgroundColor,
                cursorColor = Color.Black,
                selectionColors = LocalTextSelectionColors.current,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    search()
                }
            ),
        )

        IconButton(
            onClick = { search() },
            Modifier
                .wrapContentHeight(align = Alignment.CenterVertically)
                .fillMaxWidth()
        ) {
            Text(text = "Search", color = Color.Black)
//            Image(
//                painter = painterResource(id = R.drawable.search_icon_simple),
//                contentDescription = "home button icon"
//            )
        }
    }
}

@Preview
@Composable
fun SearchFieldPreview() {
    SearchField(text = "Hello", updateSearchInput = {}) {

    }
}