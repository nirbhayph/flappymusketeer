package com.nirbhay.flappymusketeer.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nirbhay.flappymusketeer.common.dpToPx
import com.nirbhay.flappymusketeer.ui.theme.GameBackground
import com.nirbhay.flappymusketeer.ui.theme.spaceX
import com.nirbhay.flappymusketeer.ui.theme.spaceXMars
import com.nirbhay.flappymusketeer.ui.theme.spaceXMoon
import com.nirbhay.flappymusketeer.ui.theme.twitter
import com.nirbhay.flappymusketeer.ui.theme.twitterDoge
import com.nirbhay.flappymusketeer.ui.theme.twitterGreen
import com.nirbhay.flappymusketeer.ui.theme.twitterPink
import com.nirbhay.flappymusketeer.ui.theme.twitterWhite

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

@Composable
fun Background() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp.dpToPx()
    val screenHeight = configuration.screenHeightDp.dp.dpToPx()

    val backgroundUrl = getBackgroundUrl(MaterialTheme.colorScheme.primary)

    backgroundUrl?.let {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = "${it}/${screenWidth}x${screenHeight}",
                contentDescription = null,
            )
        }
    }
}

private fun getBackgroundUrl(primaryColor: Color): String? {
    return when (primaryColor) {
        spaceX.primary -> {
            GameBackground.SPACE_X
        }

        twitter.primary -> {
            GameBackground.TWITTER
        }

        twitterWhite.primary -> {
            GameBackground.TWITTER_WHITE
        }

        twitterGreen.primary -> {
            GameBackground.TWITTER_GREEN
        }

        twitterPink.primary -> {
            GameBackground.TWITTER_PINK
        }

        twitterDoge.primary -> {
            GameBackground.TWITTER_DOGE
        }

        spaceXMars.primary -> {
            GameBackground.SPACE_X_MARS
        }

        spaceXMoon.primary -> {
            GameBackground.SPACE_X_MOON
        }

        else -> null
    }?.url
}