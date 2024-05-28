package com.example.composetestapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composetestapp.R
import com.example.composetestapp.models.Product
import com.example.composetestapp.ui.theme.PurpleGrey80

@Composable
fun HomeScreen(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp)
    ){
        items(Product.productList){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = PurpleGrey80
                ),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    AsyncImage(
                        model = it.image,
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.ic_launcher_background)
                    )
                    Text(text = it.computedTitle)
                    Text(text = it.category)
                    Row {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "Rating")
                        Text(text = it.rating.rate.toString())
                        Text(text = it.computedPrice)
                    }
                }
            }


        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}