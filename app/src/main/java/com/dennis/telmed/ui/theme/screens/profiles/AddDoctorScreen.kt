package com.dennis.telmed.ui.theme.screens.profiles

import android.R.attr.id
import android.content.Context
import android.content.res.Configuration
import android.net.Uri

import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.Button
//import androidx.compose.material.OutlinedTextField
//import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dennis.telmed.R
import com.dennis.telmed.data.doctorviewmodel
//import com.dennis.telmed.data.productviewmodel
import com.dennis.telmed.model.Doctor
import com.dennis.telmed.navigation.ROUTE_VIEW

//import com.dennis.firebase.R
//import com.dennis.firebase.data.productviewmodel
//import com.dennis.firebase.navigation.ROUTE_VIEW_PRODUCT


@Composable
fun AddDoctorScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,) {
        var context = LocalContext.current
        Image(painter = painterResource(id = R.drawable.telmed3), contentDescription = "Homepage", modifier = Modifier.fillMaxWidth().height(300.dp))
        Text(
            text = "Add Doctor",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var doctorName by remember { mutableStateOf(TextFieldValue("")) }
        var doctorSpecialty by remember { mutableStateOf(TextFieldValue("")) }
        var doctorExperience by remember { mutableStateOf(TextFieldValue("")) }
        var doctorId by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = doctorName,
            onValueChange = { doctorName = it },
            modifier = Modifier.width(300.dp),
            label = { Text(text = "Doctor name *",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = doctorSpecialty,
            onValueChange = { doctorSpecialty = it },
            modifier = Modifier.width(300.dp),
            label = { Text(text = "Doctor Specialty *",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = doctorExperience,
            onValueChange = { doctorExperience = it },
            modifier = Modifier.width(300.dp),
            label = { Text(text = "Doctor Experience *",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE SAVE LOGIC HERE---------------//
            var doctorRepository = doctorviewmodel(navController,context)
            doctorRepository.saveDoctor(doctorName.text.trim(),doctorSpecialty.text.trim(),
                doctorExperience.text)

            navController.navigate(ROUTE_VIEW)


        }, modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
            Text(text = "Save",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.height(20.dp))

        //---------------------IMAGE PICKER START-----------------------------------//

        ImagePicker(Modifier,context, navController, doctorName.text.trim(), doctorSpecialty.text.trim(), doctorExperience.text.trim())

        //---------------------IMAGE PICKER END-----------------------------------//

    }
}

@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, name:String, specialty:String, experience:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                }, modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Select Image",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var doctorRepository = doctorviewmodel(navController,context)
                doctorRepository.saveDoctorWithImage(name,specialty,experience,imageUri!!)


            }, modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text(text = "Upload",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White)
            }
        }
    }
}


@Preview
@Composable
fun Addprev() {
    AddDoctorScreen(rememberNavController())

}