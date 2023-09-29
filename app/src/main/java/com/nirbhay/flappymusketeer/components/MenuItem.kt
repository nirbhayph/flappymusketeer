package com.nirbhay.flappymusketeer.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nirbhay.flappymusketeer.common.AppRoutes
import com.nirbhay.flappymusketeer.common.dpToPx

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

@Composable
fun MenuItem(
    navController: NavController,
    backgroundUrl: String,
    name: String,
    originalName: String
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        ),
        shape = RoundedCornerShape(
            bottomStart = 30.dp,
            topEnd = 30.dp,
            topStart = 10.dp,
            bottomEnd = 10.dp
        ),
        modifier = Modifier
            .size(width = 300.dp, height = 450.dp)
            .clickable(onClick = {
                navController.navigate("${AppRoutes.GAME.name}/$originalName")
            })
            .border(
                BorderStroke(2.dp, Color.Black),
                RoundedCornerShape(
                    bottomStart = 30.dp,
                    topEnd = 30.dp,
                    topStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
    ) {
        val width = 300.dp.dpToPx()
        val height = 450.dp.dpToPx()
        Box(contentAlignment = Alignment.Center) {
            AsyncImage(model = "$backgroundUrl/${width}x$height", contentDescription = "menu")
            Text(
                name,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}