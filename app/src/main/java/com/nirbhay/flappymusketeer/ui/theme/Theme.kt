package com.nirbhay.flappymusketeer.ui.theme

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView


@Composable
fun AppTheme(
    colorScheme: ColorScheme = twitter,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            window.navigationBarColor = colorScheme.primary.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

val spaceX = darkColorScheme(
    primary = spacePurple,
    secondary = Color.Black,
    tertiary = Color.Black
)

val twitter = darkColorScheme(
    primary = earthYellow,
    secondary = twitterBlue,
    tertiary = Color.Black
)

val twitterGreen = darkColorScheme(
    primary = earthGreen,
    secondary = earthGreen,
    tertiary = Color.Black
)

val twitterPink = darkColorScheme(
    primary = flowerPink,
    secondary = flowerPink,
    tertiary = Color.White
)

val twitterWhite = darkColorScheme(
    primary = roadGray,
    secondary = Color.White,
    tertiary = Color.White
)

val spaceXMoon = darkColorScheme(
    primary = darkMoon,
    secondary = Color.White,
    tertiary = Color.White
)

val spaceXMars = darkColorScheme(
    primary = marsRust,
    secondary = Color.White,
    tertiary = Color.White
)

val twitterDoge = darkColorScheme(
    primary = moonLight,
    secondary = Color.White,
    tertiary = Color.White
)

val menuTheme = darkColorScheme(
    primary = Color.Black,
    secondary = Color.White,
    tertiary = Color.White
)


enum class GameBackground(val url: String) {
    TWITTER_DOGE("https://source.unsplash.com/qIRJeKdieKA"),
    SPACE_X("https://source.unsplash.com/ln5drpv_ImI"),
    SPACE_X_MOON("https://source.unsplash.com/Na0BbqKbfAo"),
    SPACE_X_MARS("https://source.unsplash.com/-_5dCixJ6FI"),
    TWITTER("https://source.unsplash.com/vC8wj_Kphak"),
    TWITTER_WHITE("https://source.unsplash.com/4nqSD0YmbDE"),
    TWITTER_GREEN("https://source.unsplash.com/MHNjEBeLTgw"),
    TWITTER_PINK("https://source.unsplash.com/5UCZREYSXDs")
}

fun getGameTheme(gameId: String?): ColorScheme {
    return when (gameId) {
        GameBackground.SPACE_X.name -> spaceX
        GameBackground.TWITTER.name -> twitter
        GameBackground.TWITTER_PINK.name -> twitterPink
        GameBackground.TWITTER_GREEN.name -> twitterGreen
        GameBackground.TWITTER_DOGE.name -> twitterDoge
        GameBackground.TWITTER_WHITE.name -> twitterWhite
        GameBackground.SPACE_X_MOON.name -> spaceXMoon
        GameBackground.SPACE_X_MARS.name -> spaceXMars
        else -> twitter
    }
}

@Composable
fun getNavBarColor(): Color? {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        return Color(window.navigationBarColor)
    }
    return null
}