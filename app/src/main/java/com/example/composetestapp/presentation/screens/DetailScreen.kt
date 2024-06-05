package com.example.composetestapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.composetestapp.R
import com.example.composetestapp.data.ProductService
import com.example.composetestapp.domain.models.Product
import com.example.composetestapp.domain.models.Rating
import com.example.composetestapp.presentation.theme.Purple40
import com.example.composetestapp.presentation.viewmodels.ProductDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun DetailScreen(
    productId:Int,
    viewModel : ProductDetailViewModel = hiltViewModel()
){

    val state = viewModel.productState.value

    if(state.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            state.product?.let { product ->
                Text(
                    text = product.title,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(
                    product.description,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 16.sp,
                        letterSpacing = 2.sp
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = product.rating.rate.toString(),
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
                            text = product.category,
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 12.sp,
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
                Spacer(modifier = Modifier.weight(1f))
                Text(product.computedPrice)
            }


        }
    }

}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DetailScreenPreview(){
    DetailScreen(1)
}