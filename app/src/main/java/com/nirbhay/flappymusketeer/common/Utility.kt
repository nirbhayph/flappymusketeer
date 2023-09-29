package com.nirbhay.flappymusketeer.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.Dp

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

enum class GameState {
    PAUSE, PLAYING, NOT_STARTED, COMPLETED
}

enum class AppRoutes {
    GAME, MENU, GAME_OVER
}

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx().toInt() }

fun openUri(uriHandler: UriHandler, uri: String) {
    uriHandler.openUri(uri)
}