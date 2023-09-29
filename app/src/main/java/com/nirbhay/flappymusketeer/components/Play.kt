package com.nirbhay.flappymusketeer.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Play(onPlayCallback: () -> Unit) {
    Card(
        modifier = Modifier
            .size(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(20),
        onClick = { onPlayCallback() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = "Play",
                modifier = Modifier.size(128.dp),
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}