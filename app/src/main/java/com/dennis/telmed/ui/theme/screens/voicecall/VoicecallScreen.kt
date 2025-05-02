package com.dennis.telmed.ui.theme.screens.voicecall

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun VoiceCallScreen(navController: NavHostController) {
    var isMuted by remember { mutableStateOf(false) } // Track mute state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Profile Picture
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Replace with actual image when available
            Image(
                painter = painterResource(android.R.drawable.sym_def_app_icon),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Call Status
        Text(
            text = "Calling...",
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        // Call Action Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Mute Button
            Button(
                onClick = {
                    isMuted = !isMuted
                    if (isMuted) println("Microphone muted")
                    else println("Microphone unmuted")

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                shape = CircleShape,
                modifier = Modifier.size(60.dp)
            ) {
                Text("Mute", color = Color.White, fontSize = 14.sp)
            }

            // End Call Button
            Button(
                onClick = {
                    // Code to end the call
                    println("Call ended")
                    // Navigate back or perform any cleanup actions
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = CircleShape,
                modifier = Modifier.size(60.dp)
            ) {
                Text("End", color = Color.White, fontSize = 14.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VoiceCallScreenPreview() {
    VoiceCallScreen(rememberNavController())
}