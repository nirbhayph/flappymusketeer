package com.nirbhay.flappymusketeer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nirbhay.flappymusketeer.R
import com.nirbhay.flappymusketeer.common.Analytics
import com.nirbhay.flappymusketeer.common.Events
import com.nirbhay.flappymusketeer.common.openUri
import com.nirbhay.flappymusketeer.components.MenuItem
import com.nirbhay.flappymusketeer.ui.theme.GameBackground

@Composable
fun GameMenu(navController: NavController) {
    val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth()
                .background(Color(0xFF0E2954))
        )

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(Color(0xFF0E2954)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = {
                Analytics.logEvent(Events.X_CLICKED)
                openUri(uriHandler, "https://x.com")
            }) {
                Icon(
                    painterResource(id = R.drawable.logo),
                    null,
                    modifier = Modifier.size(36.dp),
                    tint = Color.White
                )
            }
            val appName = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.ExtraLight,
                        fontFamily = FontFamily.Monospace
                    )
                ) {
                    append("flappy ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.ExtraBold
                        )
                    ) {
                        append("Musk")
                    }
                    append(".eteer")
                }

            }
            Text(
                appName,
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                OutlinedButton(onClick = {
                    Analytics.logEvent(Events.ABOUT_CLICKED)
                    openUri(
                        uriHandler,
                        "https://linktr.ee/flappymusketeer"
                    )
                }) {
                    Text(
                        "About App",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))

                OutlinedButton(onClick = {
                    Analytics.logEvent(Events.CREATOR_CLICKED)
                    openUri(
                        uriHandler,
                        "https://linktree.com/nirbhaypherwani"
                    )
                }) {
                    Text(
                        "Creator",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(rememberScrollState(), enabled = true)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFF0E2954),
                            Color(0xFF1F6E8C)
                        )
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(30.dp))

            GameBackground.values().forEach {
                MenuItem(
                    navController,
                    backgroundUrl = it.url,
                    name = it.name.replace("_", ".").uppercase(),
                    originalName = it.name
                )
                Spacer(modifier = Modifier.width(30.dp))
            }
        }
    }
}