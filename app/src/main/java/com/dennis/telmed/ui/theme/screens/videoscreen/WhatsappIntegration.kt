package com.dennis.telmed.ui.theme.screens.videoscreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dennis.telmed.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Create a NavController

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WhatsAppButton(
                        navController = navController, // Pass the NavController here
                        doctorPhoneNumber = "+254737017146" // Replace with the doctor's phone number
                    )
                }
            }
        }
    }
}

@Composable
fun WhatsAppButton(
    navController: NavHostController, // Added navController parameter
    doctorPhoneNumber: String
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = {
                // Navigate to WhatsApp Screen or directly open WhatsApp
                val whatsappIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://wa.me/$doctorPhoneNumber")
                }
                context.startActivity(whatsappIntent)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.whatsapp), // Replace with your WhatsApp icon resource
                contentDescription = "WhatsApp Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsAppButtonPreview() {
    val navController = rememberNavController() // Create a NavController for the preview
    MaterialTheme {
        WhatsAppButton(
            navController = navController, // Pass the NavController here
            doctorPhoneNumber = "+254737017146"
        )
    }
}