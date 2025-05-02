package com.dennis.telmed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dennis.telmed.ui.theme.screens.appointments.BookingAppointmentScreen
import com.dennis.telmed.ui.theme.screens.homescreen.HomeScreen
import com.dennis.telmed.ui.theme.screens.login.LoginScreen
import com.dennis.telmed.ui.theme.screens.message.MessageScreen
import com.dennis.telmed.ui.theme.screens.payment.PaymentScreen
import com.dennis.telmed.ui.theme.screens.profiles.DoctorProfileScreen
import com.dennis.telmed.ui.theme.screens.register.RegisterScreen
import com.dennis.telmed.ui.theme.screens.voicecall.VoiceCallScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,navController: NavHostController= rememberNavController(),startDestination: String=ROUTE_HOME) {
    NavHost(modifier=modifier, navController = navController, startDestination = startDestination){
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_DOCTOR) {
            DoctorProfileScreen(navController)
        }
        composable(ROUTE_VOICECALL) {
            VoiceCallScreen(navController)
        }
        composable(ROUTE_MESSAGE) {
            MessageScreen(navController)
        }
        composable(ROUTE_APPOINTMENT) {
            BookingAppointmentScreen(navController)
        }
        composable(ROUTE_PAYMENT) {
            PaymentScreen(navController)
        }
    }

}