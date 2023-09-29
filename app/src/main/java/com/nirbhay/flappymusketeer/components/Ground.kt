package com.nirbhay.flappymusketeer.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nirbhay.flappymusketeer.R

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

@Composable
fun Ground(
    label: String,
    score: Long,
    enablePause: Boolean = false,
    onPauseCallback: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        Color.White
                    )
                )
            )
            .border(
                BorderStroke(1.dp, Color.Black),
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "${label.uppercase()} :: $score",
            color = Color.Black,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 28.sp,
            fontFamily = FontFamily.Monospace
        )

        if (enablePause) {
            Spacer(modifier = Modifier.width(20.dp))

            Icon(
                painterResource(id = R.drawable.outline_pause_28),
                contentDescription = "Pause",
                modifier = Modifier
                    .size(48.dp)
                    .clickable(onClick = {
                        onPauseCallback()
                    })
            )
        }
    }
}