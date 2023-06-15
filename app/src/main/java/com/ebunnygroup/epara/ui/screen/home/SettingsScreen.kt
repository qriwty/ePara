package com.ebunnygroup.epara.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ebunnygroup.epara.ui.common.ScreenContent


@Composable
fun SettingsScreen(screenName: String, previousScreen: String?, onNextScreenClick: () -> Unit) {
    ScreenContent(screenName, previousScreen, onNextScreenClick)
}

@Preview
@Composable
fun SettingsScreenPreview() {
//    SettingsScreen()
}