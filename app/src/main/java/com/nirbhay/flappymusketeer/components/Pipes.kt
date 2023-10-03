package com.nirbhay.flappymusketeer.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nirbhay.flappymusketeer.common.GameState
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.random.Random

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */


data class Pipe(
    val width: Dp = 100.dp,
    val topPipeWeight: Float,
    val gapWeight: Float,
    val bottomPipeWeight: Float,
    var position: Dp,
)

object PipeTime {
    var lastPipeAddedTime = 0L
}

@Composable
fun Pipes(
    updatePipeRect: (Rect) -> Unit,
    updateScoreCallback: (Long) -> Unit,
    gameState: GameState,
    pipeDimensions: Triple<Float, Float, Float>
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val pipeWidth = 100.dp
    val pipeSpeed = 5.dp

    var pipes by remember { mutableStateOf(emptyList<Pipe>()) }

    if (gameState == GameState.COMPLETED) {
        pipes = emptyList()
        PipeTime.lastPipeAddedTime = 0L
    }

    if (System.currentTimeMillis() - PipeTime.lastPipeAddedTime >= 2000L) {
        val newPipe = Pipe(
            width = pipeWidth,
            topPipeWeight = pipeDimensions.first,
            gapWeight = pipeDimensions.second,
            bottomPipeWeight = pipeDimensions.third,
            position = screenWidth + pipeWidth
        )
        val addToList = if (pipes.isNotEmpty()) {
            abs(pipes.last().position.minus(newPipe.position).value) > 500f
        } else {
            true
        }

        if (addToList) {
            pipes = pipes + newPipe
            updateScoreCallback(10)
            PipeTime.lastPipeAddedTime = System.currentTimeMillis()
        }

    }

    // move pipes from right to left
    LaunchedEffect(key1 = pipes.size, gameState) {
        while (gameState == GameState.PLAYING) {
            delay(16L)
            pipes = pipes.map { pipe ->
                val newPosition = pipe.position - pipeSpeed
                pipe.copy(position = newPosition)
            }.filter { pipe ->
                pipe.position > (-pipeWidth) // remove from list when off the screen
            }
        }
    }

    pipes.forEach { pipe ->
        GapPipe(
            pipe,
            updatePipeRect = updatePipeRect
        )
    }
}


@Composable
fun GapPipe(pipe: Pipe, updatePipeRect: (Rect) -> Unit) {
    pipe.apply {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(width)
                .offset(x = position)
        ) {
            Box(
                modifier = Modifier
                    .width(width)
                    .weight(topPipeWeight)
                    .clip(RoundedCornerShape(bottomEnd = 30f, bottomStart = 30f))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        )
                    )
                    .border(
                        BorderStroke(2.5.dp, MaterialTheme.colorScheme.primary),
                        RoundedCornerShape(bottomEnd = 30f, bottomStart = 30f)
                    )
                    .onGloballyPositioned {
                        val pipeRect = it.boundsInRoot()
                        updatePipeRect(pipeRect)
                    },
            )

            Box(
                modifier = Modifier
                    .width(width)
                    .weight(gapWeight)
                    .background(Color.Transparent)
            )

            Box(
                modifier = Modifier
                    .width(width)
                    .weight(bottomPipeWeight)
                    .clip(RoundedCornerShape(topEnd = 30f, topStart = 30f))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                    .border(
                        BorderStroke(2.5.dp, MaterialTheme.colorScheme.primary),
                        RoundedCornerShape(topEnd = 30f, topStart = 30f)
                    )
                    .onGloballyPositioned {
                        val pipeRect = it.boundsInRoot()
                        updatePipeRect(pipeRect)
                    }
            )
        }
    }
}

/**
 * everything is considered weight here and not actual height
 */
internal fun getPipeDimensions(
    birdPosition: Rect,
    screenHeight: Dp
): Triple<Float, Float, Float> {
    var topPipeWeight = abs(1 - ((birdPosition.bottom.dp + 20.dp) / screenHeight))
    if (topPipeWeight > 0.4 || topPipeWeight < 0) {
        topPipeWeight = 0.4f
    }

    if (topPipeWeight == 0.4f) {
        topPipeWeight = Random.nextFloat() * 0.4f
    }

    // min weight
    if (topPipeWeight < 0.15) {
        topPipeWeight = 0.15f
    }

    // Generate a random gap weight between 0 and maxGap
    val maxGapWeight = 0.3f
    var gapWeight = maxGapWeight * Random.nextFloat()

    if (gapWeight < 0.2) {
        gapWeight = 0.2f
    }

    var bottomPipeWeight = 1.0f - gapWeight - topPipeWeight

    if (bottomPipeWeight > 0.7) {
        bottomPipeWeight = 0.7f
        gapWeight = 1.0f - topPipeWeight - bottomPipeWeight
    }

    // min weight
    if (bottomPipeWeight < 0.2) {
        bottomPipeWeight = 0.3f
        gapWeight = 1.0f - topPipeWeight - bottomPipeWeight
    }

    // safety check
    if (bottomPipeWeight < 0) {
        return Triple(0.15f, 0.2f, 0.65f)
    }

    return Triple(topPipeWeight, gapWeight, bottomPipeWeight)
}