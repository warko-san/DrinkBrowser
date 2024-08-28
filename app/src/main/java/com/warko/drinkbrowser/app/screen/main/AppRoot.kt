package com.warko.drinkbrowser.app.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.warko.drinkbrowser.app.common.compose.res.DrinkBrowserTheme

@Composable
fun AppRoot() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello, World!")
    }
}

@Preview
@Composable
private fun AppRootPreview() {
    DrinkBrowserTheme {
        AppRoot()
    }
}