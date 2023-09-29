package com.nirbhay.flappymusketeer.screens

import android.os.Bundle
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nirbhay.flappymusketeer.common.Analytics
import com.nirbhay.flappymusketeer.common.AppRoutes
import com.nirbhay.flappymusketeer.common.Events
import com.nirbhay.flappymusketeer.common.GameState
import com.nirbhay.flappymusketeer.common.Preferences
import com.nirbhay.flappymusketeer.components.Background
import com.nirbhay.flappymusketeer.components.Bird
import com.nirbhay.flappymusketeer.components.Ground
import com.nirbhay.flappymusketeer.components.Pipes
import com.nirbhay.flappymusketeer.components.Play
import com.nirbhay.flappymusketeer.components.getPipeDimensions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

@Composable
fun GameScreen(navController: NavController, gameId: String?) {
    val preferencesManager = Preferences(LocalContext.current)

    var gameState by remember { mutableStateOf(GameState.NOT_STARTED) }

    var score by remember { mutableLongStateOf(0L) }

    var lastScore by remember { mutableLongStateOf(preferencesManager.getData("last_score", 0L)) }

    var bestScore by remember { mutableLongStateOf(preferencesManager.getData("best_score", 0L)) }

    var birdOffset by remember { mutableStateOf(0.dp) }

    var birdRect by remember { mutableStateOf(Rect(0f, 0f, 64.dp.value, 64.dp.value)) }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var pipeDimensions by remember {
        mutableStateOf(Triple(0.1f, 0.4f, 0.5f))
    }

    val updateScoreCallback: (Long) -> Unit = {
        score += it
    }

    // Making the bird fall
    LaunchedEffect(key1 = birdOffset, gameState) {
        while (gameState == GameState.PLAYING) {
            delay(16)
            birdOffset += 4.dp
        }
    }

    val updateBirdRect: (birdRect: Rect) -> Unit = {
        birdRect = it
        pipeDimensions = getPipeDimensions(it, screenHeight)
    }

    val updatePipeRect: (pipeRect: Rect) -> Unit = {
        if (!it.intersect(birdRect).isEmpty) {
            val bundle = Bundle()
            bundle.putString("theme", gameId)
            bundle.putLong("game_score", score)


            gameState = GameState.COMPLETED
            if (score > bestScore) {
                bestScore = score
                preferencesManager.saveData("best_score", score)
                bundle.putLong("new_best_score", score)
                Analytics.logEvent(Events.NEW_BEST_SCORE, bundle)
            }
            preferencesManager.saveData("last_score", score)
            lastScore = score
            Analytics.logEvent(Events.GAME_OVER, bundle)

            score = 0
            birdOffset = 0.dp
            birdRect = Rect(0f, 0f, 64.dp.value, 64.dp.value)

            navController.navigate(AppRoutes.GAME_OVER.name)
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        if (gameState == GameState.PLAYING) {
                            // Jump by changing the bird's offset
                            coroutineScope.launch {
                                var offsetChange = 80.dp
                                while (offsetChange > 0.dp) {
                                    birdOffset -= 2.dp
                                    delay(2L)
                                    offsetChange -= 2.dp
                                }
                            }

                        }
                    }
                )
            }
    ) {
        Background()

        Pipes(
            updatePipeRect = updatePipeRect,
            updateScoreCallback = updateScoreCallback,
            gameState = gameState,
            pipeDimensions = pipeDimensions.copy()
        )

        when (gameState) {
            GameState.PLAYING -> {
                Bird(birdOffset, updateBirdRect)

                val onPauseCallback = {
                    val bundle = Bundle()
                    bundle.putString("theme", gameId)
                    Analytics.logEvent(Events.GAME_PAUSED, bundle)
                    gameState = GameState.PAUSE
                }

                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    Ground("Flappy Score", score, enablePause = true, onPauseCallback)
                }
            }

            GameState.NOT_STARTED, GameState.COMPLETED -> {
                val onPlayCallback = {
                    gameState = GameState.PLAYING
                }

                Box(modifier = Modifier.align(Alignment.Center)) {
                    Play(onPlayCallback)
                }

                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    if (lastScore > 0) {
                        Column {
                            Ground("Last Score", lastScore)
                            Ground("Best Score", bestScore)
                        }
                    } else {
                        Ground("Best Score", bestScore)
                    }

                }
            }

            GameState.PAUSE -> {
                val onPlayCallback = {
                    val bundle = Bundle()
                    bundle.putString("theme", gameId)
                    Analytics.logEvent(Events.GAME_RESUMED, bundle)

                    gameState = GameState.PLAYING
                }

                Box(modifier = Modifier.align(Alignment.Center)) {
                    Play(onPlayCallback)
                }

                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    Ground("Current Score", score)
                }
            }
        }
    }
}