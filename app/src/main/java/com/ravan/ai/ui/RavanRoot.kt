package com.ravan.ai.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ravan.ai.ui.home.HomePlaceholderScreen
import com.ravan.ai.ui.splash.SplashScreen

@Composable
fun RavanRoot() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen(onFinished = { showSplash = false })
    } else {
        HomePlaceholderScreen()
    }
}
