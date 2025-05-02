package com.dennis.telmed.ui.theme.screens.appointments

import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingAppointmentScreen(navController: NavHostController) {
    var selectedDoctor by remember { mutableStateOf("Dr. Smith") }
    val doctors = listOf("Dr. Smith", "Dr. Johnson", "Dr. Lee")

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Book an Appointment", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Doctor Dropdown
        var expanded by remember { mutableStateOf(false) }
        Box {
            OutlinedTextField(
                value = selectedDoctor,
                onValueChange = {},
                label = { Text("Select Doctor") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                doctors.forEach { doctor ->
                    DropdownMenuItem(
                        text = { Text(doctor) },
                        onClick = {
                            selectedDoctor = doctor
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Date Picker
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text("Select Date") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
//                .clickable {
//                    val datePicker = DatePickerDialog(
//                        context,
//                        { _, year, month, day ->
//                            val formattedDate = "%04d-%02d-%02d".format(year, month + 1, day)
//                            selectedDate = formattedDate
//                        },
//                        calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH)
//                    )
//                    datePicker.show()
//                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Time Picker
        OutlinedTextField(
            value = selectedTime,
            onValueChange = {},
            label = { Text("Select Time") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val timePicker = TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            val formattedTime = String.format("%02d:%02d", hour, minute)
                            selectedTime = formattedTime
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    )
                    timePicker.show()
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Notes
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes (Optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Submit Button
        Button(
            onClick = {
                if (selectedDate.isNotBlank() && selectedTime.isNotBlank()) {
                    Toast
                        .makeText(
                            context,
                            "Appointment booked with $selectedDoctor on $selectedDate at $selectedTime",
                            Toast.LENGTH_LONG
                        )
                        .show()
                } else {
                    Toast.makeText(context, "Please select date and time.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Book Appointment")
        }
    }
}


@Preview
@Composable
private fun Bookingprev() {
    BookingAppointmentScreen(rememberNavController())
}