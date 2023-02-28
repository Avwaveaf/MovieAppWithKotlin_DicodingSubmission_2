package com.example.movieapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .fillMaxWidth()
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = 8.dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.images[0])
                            .crossfade(true)
                            .transformations(CircleCropTransformation())
                            .build()
                    ), contentDescription = movie.synopsis
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {

                Text(text = movie.title.uppercase(), style = MaterialTheme.typography.h6)
                Row {
                    Text(text = movie.year, style = MaterialTheme.typography.caption)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = movie.rating,
                        style = MaterialTheme.typography.caption,
                        color = Color.Green
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ExpandingText(synopsis = movie.synopsis)
            }
        }
    }
}