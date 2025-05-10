package com.dennis.telmed.ui.theme.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppsOutage
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dennis.telmed.R
import com.dennis.telmed.navigation.ROUTE_ADD
import com.dennis.telmed.navigation.ROUTE_APPOINTMENT
import com.dennis.telmed.navigation.ROUTE_DOCTOR
import com.dennis.telmed.navigation.ROUTE_PAYMENT

@Composable
fun UpdatedHomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                backgroundColor = Color(0xFF1976D2),
                contentColor = Color.White
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.telmed3), contentDescription = "homepic",
                modifier = Modifier.fillMaxWidth().height(350.dp))
            // Icons Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Video Call Icon
                IconButton(
                    onClick = { /* Handle Video Call */ },
                    modifier = Modifier.size(60.dp) // Constrain icon size
                ) {
                    Icon(Icons.Default.VideoCall, contentDescription = "Video Call"
//                        painter = painterResource(id = R.drawable.video), // Replace with your video call icon drawable
//                        contentDescription = "Video Call",
//                        tint = Color(0xFF64B5F6),
//                        modifier = Modifier.height(50.dp).width(250.dp)
                    )
                }

                // Book Appointment Icon
                IconButton(
                    onClick = {
                        navController.navigate(ROUTE_APPOINTMENT)
                    },
                    modifier = Modifier.size(60.dp) // Constrain icon size
                ) {
                    Icon(Icons.Default.AppsOutage, contentDescription = "Appointment"
//                        painter = painterResource(id = R.drawable.appointmenticon), // Replace with your booking icon drawable
//                        contentDescription = "Book Appointment",
//                        tint = Color(0xFF4CAF50)
                    )
                }
            }

            // Doctor Profile Button
            Button(
                onClick = {
                    navController.navigate(ROUTE_DOCTOR)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Constrain the button width to 80% of the screen
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1976D2)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Doctor Profile", color = Color.White, fontSize = 16.sp)
            }

            // Add Doctor Button
            Button(
                onClick = {
                    navController.navigate(ROUTE_ADD)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Constrain the button width
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF64B5F6)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Add Doctor", color = Color.White, fontSize = 16.sp)
            }

            // Make Payment Button
            Button(
                onClick = {
                    navController.navigate(ROUTE_APPOINTMENT)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Constrain the button width
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA726)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Book Appointment", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Preview
@Composable
private fun Homeprev() {
    UpdatedHomeScreen(rememberNavController())

}