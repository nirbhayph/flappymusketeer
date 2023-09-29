package com.nirbhay.flappymusketeer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.nirbhay.flappymusketeer.R
import com.nirbhay.flappymusketeer.ui.theme.getNavBarColor
import kotlinx.coroutines.delay

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

@Composable
fun XLottie(navController: NavController, lottieResourceId: Int = R.raw.x_lottie) {
    val backgroundColor: Color = getNavBarColor() ?: Color.Black

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResourceId))
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        navController.popBackStack()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            modifier = Modifier.size(320.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Text(
            "GAME OVER",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}