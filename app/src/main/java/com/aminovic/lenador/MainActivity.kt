package com.aminovic.lenador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aminovic.presentation.screens.Screens
import com.aminovic.presentation.screens.home_screen.HomeScreen
import com.aminovic.presentation.ui.theme.LenadorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LenadorTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screens.HomeScreen.route,
                    enterTransition = {
                        fadeIn(animationSpec = tween(500))
                    },
                    exitTransition = {
                        fadeOut(animationSpec = tween(500))
                    }
                ) {
                    composable(route = Screens.HomeScreen.route) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
