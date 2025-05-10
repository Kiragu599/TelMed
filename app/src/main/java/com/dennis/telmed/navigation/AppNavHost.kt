package com.dennis.telmed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dennis.telmed.ui.theme.screens.appointments.BookingAppointmentScreen
import com.dennis.telmed.ui.theme.screens.appointments.ReminderScreen

import com.dennis.telmed.ui.theme.screens.homescreen.UpdatedHomeScreen
import com.dennis.telmed.ui.theme.screens.login.LoginScreen

import com.dennis.telmed.ui.theme.screens.payment.PaymentScreen
import com.dennis.telmed.ui.theme.screens.profiles.AddDoctorScreen
import com.dennis.telmed.ui.theme.screens.profiles.DoctorProfileScreen
import com.dennis.telmed.ui.theme.screens.profiles.UpdateDoctorsScreen
import com.dennis.telmed.ui.theme.screens.profiles.ViewDoctorScreen
import com.dennis.telmed.ui.theme.screens.profiles.ViewUploadsScreen
import com.dennis.telmed.ui.theme.screens.register.RegisterScreen
import com.dennis.telmed.ui.theme.screens.videoscreen.WhatsAppButton
import com.dennis.telmed.ui.theme.screens.voicecall.VoiceCallScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,navController: NavHostController= rememberNavController(),startDestination: String=ROUTE_LOGIN) {
    NavHost(modifier=modifier, navController = navController, startDestination = startDestination){
        composable(ROUTE_LOGIN) {
           LoginScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_HOME) {
            UpdatedHomeScreen(navController)
        }
        composable(ROUTE_DOCTOR) {
            DoctorProfileScreen(navController)
        }
        composable(ROUTE_VOICECALL) {
            VoiceCallScreen(navController)
        }
        composable(ROUTE_APPOINTMENT) {
            BookingAppointmentScreen(navController)
        }
        composable(ROUTE_PAYMENT) {
            PaymentScreen(navController)
        }
        composable(ROUTE_ADD) {
            AddDoctorScreen(navController)
        }
        composable(ROUTE_VIEW) {
            ViewDoctorScreen(navController)
        }
        composable(ROUTE_UPDATE+ "/{id}") {passedData ->
            UpdateDoctorsScreen(navController,passedData.arguments?.getString("id")!!)
        }
        composable(ROUTE_UPLOAD) {
            ViewUploadsScreen(navController)
        }
        composable(ROUTE_WHATSAPP) {
            WhatsAppButton(navController,"+254737017146")
        }
        composable (ROUTE_REMINDER){
            ReminderScreen(navController)
        }
    }
}