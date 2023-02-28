package com.example.movieapp.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.getProfile

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController) {
    val (fullName, email, imageUrl) = getProfile()

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }

        }
    }) {

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(
                                LocalContext.current
                            )
                                .data(imageUrl)
                                .crossfade(true)
                                .transformations(CircleCropTransformation())
                                .build()
                        ), contentDescription = fullName
                    )

                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier
                        .width(8.dp)
                        .height(8.dp))
                    Text(text = fullName, fontWeight = FontWeight.Bold)
                    Text(text = email)
                }
            }


        }
    }
}

