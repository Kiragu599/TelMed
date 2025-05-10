package com.dennis.telmed.ui.theme.screens.profiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.dennis.telmed.data.doctorviewmodel
import com.dennis.telmed.model.Upload
import com.dennis.telmed.navigation.ROUTE_UPDATE

//import com.dennis.firebase.data.productviewmodel
//import com.dennis.firebase.model.Upload
//import com.dennis.firebase.navigation.ROUTE_UPDATE_PRODUCT


@Composable
fun ViewUploadsScreen(navController:NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        var context = LocalContext.current
        var doctorRepository = doctorviewmodel(navController, context)


        val emptyUploadState = remember { mutableStateOf(Upload("","","","","")) }
        var emptyUploadsListState = remember { mutableStateListOf<Upload>() }

        var uploads = doctorRepository.viewUploads(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All uploads",
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(uploads){
                    UploadItem(
                        name = it.name,
                        specialty = it.speciality,
                        experience = it.experience,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        navController = navController,
                        doctorRepository = doctorRepository
                    )
                }
            }
        }
    }
}


@Composable
fun UploadItem(name:String, specialty:String, experience:String, imageUrl:String, id:String,
               navController:NavHostController, doctorRepository: doctorviewmodel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = specialty)
        Text(text = experience)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Button(onClick = {
            doctorRepository.deleteDoctor(id)
        }) {
            Text(text = "Delete",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp

            )
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE+"/$id")
        }) {
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
private fun Viewprev() {
    ViewUploadsScreen(rememberNavController())
}