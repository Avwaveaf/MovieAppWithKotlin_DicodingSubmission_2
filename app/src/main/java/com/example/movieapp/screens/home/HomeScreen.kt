package com.example.movieapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.MyApp
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.model.getProfile
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.navigation.MovieScreen
import com.example.movieapp.widgets.CurratedMovieRow
import com.example.movieapp.widgets.MovieRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Row {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.movie_symbol),
                            contentDescription = "Splash Logo",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Films", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Column {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent)
                    ) {
                        Text(
                            text = "Hi There!",
                            modifier = Modifier.clickable { navController.navigate(route = MovieScreen.ProfileScreen.name) })
                        Spacer(modifier = Modifier.width(6.dp))
                        IconButton(onClick = { navController.navigate(route = MovieScreen.ProfileScreen.name) }) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(
                                        LocalContext.current
                                    )
                                        .data(getProfile().imageUrl)
                                        .crossfade(true)
                                        .transformations(CircleCropTransformation())
                                        .build()
                                ), contentDescription = getProfile().fullName
                            )
                        }

                    }
                }
            }

        }
    }) {
        MainContent(navController)
    }
}

@Composable
fun MainContent(navController: NavController, movieList: List<Movie> = getMovies()) {
    val curratedMovies = movieList.filter { it.rating.toFloat() >= 8.0 }
    Column(modifier = Modifier.fillMaxSize()) {
        Row {

            Column(modifier = Modifier.padding(2.dp)) {
                Text(
                    text = "Top Currated Movies",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    fontWeight = FontWeight.Bold
                )


                LazyRow(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                ) {
                    items(items = curratedMovies.sortedByDescending { it.rating.toFloat() }) {
                        CurratedMovieRow(movie = it) { recomendedMovie ->
                            navController.navigate(route = MovieScreen.MovieDetailScreen.name + "/$recomendedMovie")
                        }
                    }
                }

            }
        }
        Row {
            Column {


                Text(
                    text = "Current Latest Movies",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )


                Card(
                    modifier = Modifier
                        .offset(y = 14.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                    elevation = 8.dp
                ) {

                    LazyColumn(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)) {

                        items(items = movieList) {
                            MovieRow(movie = it) { movie ->
                                navController.navigate(route = MovieScreen.MovieDetailScreen.name + "/$movie")
                            }
                        }
                    }
                }
            }
        }

    }


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}