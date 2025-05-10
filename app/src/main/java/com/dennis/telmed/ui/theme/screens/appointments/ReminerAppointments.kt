package com.dennis.telmed.ui.theme.screens.appointments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color


//import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


class ReminderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReminderScreen(rememberNavController())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // This is needed for Material 3 components
@Composable
fun ReminderScreen(navController: NavHostController) {
    var appointmentDate by remember { mutableStateOf(TextFieldValue("")) }
    var appointmentTime by remember { mutableStateOf(TextFieldValue("")) }
    var reminderSet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar( // FIX: Changed TopAppBar to SmallTopAppBar for Material 3
                title = { Text("Appointment Reminder", fontSize = 20.sp) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary) // Background color
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Set your Appointment Details",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 20.dp),
                textAlign = TextAlign.Center
            )

            BasicTextField(
                value = appointmentDate,
                onValueChange = { appointmentDate = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                decorationBox = { innerTextField ->
                    // FIX: Used correct way to create decoration box
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        if (appointmentDate.text.isEmpty()) {
                            Text("Enter Appointment Date", color = Color.Gray) // Hint text
                        }
                        innerTextField() // Actual input field
                    }
                }
            )

            BasicTextField(
                value = appointmentTime,
                onValueChange = { appointmentTime = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                decorationBox = { innerTextField ->
                    // FIX: Used correct way to create decoration box
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        if (appointmentTime.text.isEmpty()) {
                            Text("Enter Appointment Time", color = Color.Gray) // Hint text
                        }
                        innerTextField() // Actual input field
                    }
                }
            )

            Button(
                onClick = { reminderSet = true },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(text = "Set Reminder")
            }

            if (reminderSet) {
                Text(
                    text = "Reminder Set for ${appointmentDate.text} at ${appointmentTime.text}",
                    color = Color.Green,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}


@Preview
@Composable
private fun Reminderprev() {
    ReminderScreen(rememberNavController())

}