package com.example.composetestapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.composetestapp.R
import com.example.composetestapp.data.ProductService
import com.example.composetestapp.domain.models.Product
import com.example.composetestapp.presentation.theme.Purple40
import com.example.composetestapp.presentation.theme.PurpleGrey80
import com.example.composetestapp.presentation.viewmodels.ProductsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen(
    navController:NavController,
    viewModel : ProductsViewModel = hiltViewModel()
){
    val state = viewModel.productsState.value
    if(state.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else{
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp)
        ){

            items(state.products){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable {
                            navController.navigate("detail/${it.id}")
                        }
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
                            placeholder = painterResource(id = R.drawable.ic_launcher_background),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3f)
                        )
                        Text(
                            text = it.computedTitle,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = it.category,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f),
                            style = TextStyle(
                                fontWeight = FontWeight.W300,
                                fontSize = 12.sp,
                                color = Color.DarkGray
                            )
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = Color.Yellow,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                            Text(
                                text = it.rating.rate.toString(),
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                                    .background(
                                        color = Purple40,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .weight(1f)
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = it.computedPrice,
                                    color = Color.White,
                                    style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }

                        }
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
    HomeScreen(rememberNavController())
}