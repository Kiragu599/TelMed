package com.dennis.telmed.ui.theme.screens.message

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MessageScreen(navController: NavHostController) {
    // A place to hold the message being typed
    var currentMessage by remember { mutableStateOf("") }

    // A pretend list of messages
    val messages = remember { mutableStateListOf("Hello, Doctor!", "Hi! How can I help you today?") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Message List
        Column(
            modifier = Modifier.weight(1f)
        ) {
            for (message in messages) {
                Text(
                    text = message,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            color = if (message.startsWith("Hi!")) Color.LightGray else Color.Blue,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp),
                    color = if (message.startsWith("Hi!")) Color.Black else Color.White,
                    fontSize = 16.sp
                )
            }
        }

        // Text Input and Send Button
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = currentMessage,
                onValueChange = { currentMessage = it },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                textStyle = TextStyle(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (currentMessage.isNotBlank()) {
                    messages.add(currentMessage)
                    currentMessage = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageScreenPreview() {
    MessageScreen(rememberNavController())
}