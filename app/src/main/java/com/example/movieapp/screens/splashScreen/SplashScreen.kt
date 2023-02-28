package com.example.movieapp.screens.splashScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.navigation.MovieScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreens(navController: NavController){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue= 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000L)
        navController.navigate(MovieScreen.HomeScreen.name)
    }
   Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
       Image(
           painter = painterResource(id = R.drawable.movie_symbol),
           contentDescription = "Splash Logo",
           modifier = Modifier.scale(scale.value)
       )
   }
}
