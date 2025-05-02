package com.dennis.telmed.ui.theme.screens.payment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            PaymentScreen()
//        }
//    }
//}

@Composable
fun PaymentScreen(navController: NavHostController) {
    // State to hold the phone number
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }

    // State to hold the payment amount
    var amount by remember { mutableStateOf(TextFieldValue("")) }

    // State to show or hide the preview
    var showPreview by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Mpesa Payment",
                fontSize = 24.sp,
                style = MaterialTheme.typography.h5
            )

            // TextField for phone number
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Enter Phone Number") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            // TextField for amount
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Enter Amount") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            // Button to show the preview
            Button(
                onClick = { showPreview = true },
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(text = "Preview Payment")
            }

            // If showPreview is true, display the preview section
            if (showPreview) {
                PaymentPreview(
                    phoneNumber = phoneNumber.text,
                    amount = amount.text,
                    onConfirm = {
                        // Call the Mpesa payment function on confirmation
                        initiateMpesaPayment(phoneNumber.text, amount.text)
                    },
                    onCancel = {
                        // Hide the preview if the user cancels
                        showPreview = false
                    }
                )
            }
        }
    }
}

@Composable
fun PaymentPreview(phoneNumber: String, amount: String, onConfirm: () -> Unit, onCancel: () -> Unit) {
    // A Card to show the preview of payment
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Preview Payment", fontSize = 20.sp, style = MaterialTheme.typography.h6)

            Text(text = "Phone Number: $phoneNumber")
            Text(text = "Amount: Ksh $amount")

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Confirm Payment Button
                Button(
                    onClick = { onConfirm() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Confirm")
                }

                // Cancel Payment Button
                Button(
                    onClick = { onCancel() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}

// Function to simulate an Mpesa STK push
fun initiateMpesaPayment(phoneNumber: String, amount: String) {
    println("Sending Mpesa STK Push to $phoneNumber for amount Ksh $amount")
}

// Preview function to see the layout in Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewPaymentScreen() {
    PaymentScreen(rememberNavController())
}