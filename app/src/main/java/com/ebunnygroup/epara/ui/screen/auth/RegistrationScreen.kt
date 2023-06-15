package com.ebunnygroup.epara.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ebunnygroup.epara.ui.common.ScreenContent


@Composable
fun RegistrationScreen(screenName: String, previousScreen: String?, onNextScreenClick: () -> Unit) {
    ScreenContent(screenName, previousScreen, onNextScreenClick)
}

@Preview
@Composable
fun RegistrationScreenPreview() {
//    RegistrationScreen()
}