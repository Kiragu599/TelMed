package com.dennis.telmed.ui.theme.screens.profiles

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dennis.telmed.data.doctorviewmodel
import com.dennis.telmed.model.Doctor
//import com.dennis.firebase.data.productviewmodel
//import com.dennis.firebase.model.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener


@Composable
fun UpdateDoctorsScreen(navController: NavHostController,id:String) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var specialty by remember { mutableStateOf("") }
        var experience by remember { mutableStateOf("") }

        var currentDataRef = com.google.firebase.database.FirebaseDatabase.getInstance().getReference()
            .child("Doctors/$id")
        currentDataRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var doctor = snapshot.getValue(Doctor::class.java)
                name = doctor!!.name
                specialty = doctor!!.specialty
                experience = doctor!!.experience
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        Text(
            text = "Add Doctor",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var doctorName by remember { mutableStateOf(TextFieldValue(name)) }
        var doctorSpecialty by remember { mutableStateOf(TextFieldValue(specialty)) }
        var doctorExperience by remember { mutableStateOf(TextFieldValue(experience)) }

        OutlinedTextField(
            value = doctorName,
            onValueChange = { doctorName= it },
            modifier = Modifier.width(300.dp),
            label = { Text(text = "Doctor name *",
                color = Color.Black,
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
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp

            ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = doctorExperience,
            onValueChange = { doctorExperience = it },
            modifier = Modifier.width(300.dp),
            label = { Text(text = "Doctor Experience *",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp

            ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var doctorRepository = doctorviewmodel(navController, context)
            doctorRepository.updateDoctor(doctorName.text.trim(),doctorSpecialty.text.trim(),
                doctorExperience.text.trim(),id)

        },
            modifier = Modifier.width(300.dp),) {
            Text(text = "Update",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp


            )
        }

    }
}

@Preview
@Composable
fun updateprev() {
    UpdateDoctorsScreen(rememberNavController(), id = "")
}