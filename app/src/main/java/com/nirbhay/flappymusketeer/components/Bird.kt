package com.nirbhay.flappymusketeer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nirbhay.flappymusketeer.R
import com.nirbhay.flappymusketeer.ui.theme.spaceX
import com.nirbhay.flappymusketeer.ui.theme.spaceXMars
import com.nirbhay.flappymusketeer.ui.theme.spaceXMoon
import com.nirbhay.flappymusketeer.ui.theme.twitterDoge

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

@Composable
fun Bird(birdOffset: Dp, updateBirdRect: (Rect) -> Unit) {

    val bird = when (MaterialTheme.colorScheme.primary) {
        spaceX.primary, spaceXMars.primary, spaceXMoon.primary -> {
            R.drawable.space
        }

        twitterDoge.primary -> {
            R.drawable.doge
        }

        else -> R.drawable.bird
    }

    bird.let {
        Box(
            modifier = Modifier
                .size(64.dp)
                .offset(y = birdOffset)
                .padding(5.dp)
                .onGloballyPositioned {
                    updateBirdRect(it.boundsInRoot())
                }
        ) {
            when (MaterialTheme.colorScheme.primary) {
                spaceX.primary, spaceXMoon.primary, spaceXMars.primary -> {
                    Image(painterResource(id = it), contentDescription = "rocket")
                }

                else -> {
                    if (MaterialTheme.colorScheme.primary == twitterDoge.primary) {
                        Image(painterResource(id = it), contentDescription = "doge rocket")
                    } else {
                        Icon(
                            painterResource(id = it),
                            tint = MaterialTheme.colorScheme.secondary,
                            contentDescription = "bird"
                        )
                    }
                }
            }
        }
    }
}