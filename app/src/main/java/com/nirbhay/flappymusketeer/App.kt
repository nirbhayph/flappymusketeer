package com.nirbhay.flappymusketeer

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nirbhay.flappymusketeer.common.Analytics
import com.nirbhay.flappymusketeer.common.AppRoutes
import com.nirbhay.flappymusketeer.common.Events
import com.nirbhay.flappymusketeer.screens.GameMenu
import com.nirbhay.flappymusketeer.screens.GameScreen
import com.nirbhay.flappymusketeer.screens.XLottie
import com.nirbhay.flappymusketeer.ui.theme.AppTheme
import com.nirbhay.flappymusketeer.ui.theme.getGameTheme
import com.nirbhay.flappymusketeer.ui.theme.menuTheme

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.MENU.name) {
        composable(AppRoutes.MENU.name) {
            AppTheme(colorScheme = menuTheme) {
                Analytics.logEvent(Events.APP_MENU)
                GameMenu(navController)
            }
        }
        composable("${AppRoutes.GAME.name}/{gameId}") {
            val gameId = it.arguments?.getString("gameId")
            val gameTheme = getGameTheme(gameId)

            val bundle = Bundle()
            bundle.putString("theme", gameId)
            Analytics.logEvent(Events.GAME_SELECTED, bundle)

            AppTheme(colorScheme = gameTheme) {
                GameScreen(navController, gameId)
            }
        }

        composable(AppRoutes.GAME_OVER.name) {
            XLottie(navController)
        }
    }
}