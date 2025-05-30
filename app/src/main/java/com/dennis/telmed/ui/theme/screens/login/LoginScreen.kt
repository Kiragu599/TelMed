package com.dennis.telmed.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


import com.dennis.telmed.data.AuthViewModel
import com.dennis.telmed.navigation.ROUTE_REGISTER


@Composable
fun LoginScreen(navController: NavHostController) {

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var context= LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = "Login here",
            color = Color.Black,
            fontFamily = FontFamily.Monospace,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value =email , onValueChange = {email=it},
            label = { Text(text = "Enter Email",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .width(350.dp)
                .padding(16.dp),
            leadingIcon = {Icon(Icons.Default.Email, contentDescription = "Email")},
            shape = RoundedCornerShape(20.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value =pass , onValueChange = {pass=it},
            label = { Text(text = "Enter Password",
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .width(350.dp)
                .padding(16.dp),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
            shape = RoundedCornerShape(20.dp)


        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val mylogin= AuthViewModel(navController, context )
            mylogin.login(email.text.trim(),pass.text.trim())
        }, modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black,)) {
            Text(text = "Log In",
                color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(ROUTE_REGISTER)
        }, modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black,)) {
            Text(text = "Don't have account? Click to Register",
                color = Color.White)
        }

    }


}
@Preview
@Composable
fun Loginpage() {
    LoginScreen(rememberNavController())
}