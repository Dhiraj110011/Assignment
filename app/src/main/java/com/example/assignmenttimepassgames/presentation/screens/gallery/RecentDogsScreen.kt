package com.example.assignmenttimepassgames.presentation.screens.gallery

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.assignmenttimepassgames.presentation.screens.home.Blue
import com.example.assignmenttimepassgames.presentation.viewmodel.DogViewModel

@Composable
fun RecentDogsScreen(
    viewModel: DogViewModel = viewModel(LocalViewModelStoreOwner.current!!)
) {
    val cachedDogs by viewModel.cachedDogs.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dog Gallery",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                if (cachedDogs.isEmpty()) {
                    Text(
                        text = "No dogs in gallery yet!",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray
                    )
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(
                            items = cachedDogs,
                            key = { it.id }
                        ) { dog ->
                            AsyncImage(
                                model = dog.imageUrl,
                                contentDescription = "Dog Image",
                                modifier = Modifier
                                    .size(300.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            Button(
                onClick = { viewModel.clearCache() },
                colors = ButtonDefaults.buttonColors(Blue),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Clear Gallery", color = Color.White)
            }
        }
    }
}