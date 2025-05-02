package com.dennis.telmed.ui.theme.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dennis.telmed.R
import com.dennis.telmed.navigation.ROUTE_LOGIN
import com.dennis.telmed.navigation.ROUTE_REGISTER

@Composable
fun HomeScreen(navController: NavHostController) {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Image(painter = painterResource(id = R.drawable.telmed1), contentDescription = "TelMed1",
            modifier = Modifier.fillMaxWidth().height(250.dp))

        Text("Welcome to TelMed",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black)

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            navController.navigate(ROUTE_REGISTER)
        },
            modifier = Modifier
                .width(300.dp)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
            Text("Signup",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(ROUTE_LOGIN)
        },
            modifier = Modifier
                .width(300.dp)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
            Text("Login",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )
        }




    }

}






@Preview
@Composable
private fun Homeprev() {
    HomeScreen(rememberNavController())
}