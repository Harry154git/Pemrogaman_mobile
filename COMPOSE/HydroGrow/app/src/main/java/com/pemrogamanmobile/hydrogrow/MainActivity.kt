package com.pemrogamanmobile.hydrogrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pemrogamanmobile.hydrogrow.nav.AppNav
import com.pemrogamanmobile.hydrogrow.ui.theme.HydroGrowTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HydroGrowTheme {
                AppNav()
            }
        }
    }
}
