package com.ebunnygroup.epara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ebunnygroup.epara.navigation.AppNavigation
import com.ebunnygroup.epara.ui.theme.EParaTheme
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        setContent {
            EParaTheme() {
                AppNavigation()
            }

        }
    }
}
