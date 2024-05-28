package com.example.composetestapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composetestapp.R
import com.example.composetestapp.models.Product
import com.example.composetestapp.ui.theme.Purple40
import com.example.composetestapp.ui.theme.roboto

@Composable
fun ProductDetailScreen(productId:Int){
    val product = Product.productList.first { it.id == productId }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Text(
            text = product.title,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = roboto
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
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
                .fillMaxWidth().padding(top = 10.dp),
            style = TextStyle(
                color = Color.DarkGray,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                letterSpacing = 2.sp,
                fontFamily = roboto
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
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
                    .fillMaxWidth() // Para que ocupe todo el ancho disponible
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .background(
                        color = Purple40,
                        shape = RoundedCornerShape(8.dp) // Cambia el valor según lo redondeado que quieras los bordes
                    )
                    .padding(5.dp), // Padding interno del Box
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.category,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier // Para que ocupe todo el ancho disponible
                .align(Alignment.End)
                .background(
                    color = Purple40,
                    shape = RoundedCornerShape(18.dp) // Cambia el valor según lo redondeado que quieras los bordes
                )
                .padding(15.dp), // Padding interno del Box
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = product.computedPrice,
                color = Color.White,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProductDetailScreenPreview(){
    ProductDetailScreen(1)
}