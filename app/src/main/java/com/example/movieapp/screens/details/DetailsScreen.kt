package com.example.movieapp.screens.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieapp.model.getMovies

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, movieDataId: String?) {

    val movieData = getMovies().filter { it.id == movieDataId }
    val context = LocalContext.current
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this movie")
    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "I just Check ${movieData.first().title} and it's awesome!"
    )
    val chooserIntent = Intent.createChooser(shareIntent, "Share movie via")

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }

        }
    }) {

        Surface(modifier = Modifier.fillMaxSize()) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {


                LazyRow {
                    items(items = movieData.first().images) { imageUrl ->
                        Card(modifier = Modifier.size(280.dp), elevation = 5.dp) {

                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(
                                        LocalContext.current
                                    )
                                        .data(imageUrl)
                                        .crossfade(true)
                                        .build()
                                ),
                                contentDescription = movieData.first().synopsis,
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier.weight(1f)
                            )
                        }

                    }
                }

                Card(
                    modifier = Modifier
                        .offset(y = (-16).dp)
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                    elevation = 8.dp
                ) {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Row {

                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 2.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                                elevation = 8.dp
                            ) {

                                Row(verticalAlignment = Alignment.CenterVertically) {

                                    Text(
                                        text = movieData.first().title,
                                        style = MaterialTheme.typography.h5,
                                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                                    )

                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {

                                Text(
                                    text = movieData.first().rating,
                                    style = MaterialTheme.typography.caption,
                                    color = Color.DarkGray,
                                    fontSize = 18.sp
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Rating",
                                    tint = Color.DarkGray
                                )
                            }

                            Text(
                                text = movieData.first().year,
                                style = MaterialTheme.typography.caption
                            )
                        }

                        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                            // MOVIE DESCRIPTION
                            Text(buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                ) {
                                    append("Genre: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 16.sp
                                    )
                                ) {
                                    append(movieData.first().genre)
                                }
                            })
                        }
                        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                            // MOVIE DESCRIPTION
                            Text(buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                ) {
                                    append("Synopsis: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 16.sp
                                    )
                                ) {
                                    append(movieData.first().synopsis)
                                }
                            })
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(onClick = {
                                context.startActivity(chooserIntent)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "share"
                                )
                            }

                        }

                    }


                }

            }
        }
    }

}