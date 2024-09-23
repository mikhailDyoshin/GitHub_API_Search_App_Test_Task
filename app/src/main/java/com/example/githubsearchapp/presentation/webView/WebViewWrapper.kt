package com.example.githubsearchapp.presentation.webView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun WebViewWrapper(url: String) {
    Column(Modifier.fillMaxSize()) {
        RepositoryFileWebView(url)
    }
}