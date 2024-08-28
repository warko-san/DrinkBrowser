package com.warko.drinkbrowser.app.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.warko.drinkbrowser.app.common.compose.LocalActivity
import com.warko.drinkbrowser.app.common.compose.res.DrinkBrowserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinkBrowserTheme {
                CompositionLocalProvider(LocalActivity provides this) {
                    AppRoot()
                }
            }
        }
    }

}