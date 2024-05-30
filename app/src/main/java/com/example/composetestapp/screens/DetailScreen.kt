package com.example.composetestapp.screens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.example.composetestapp.R
import com.example.composetestapp.models.Product
import com.example.composetestapp.ui.theme.Purple40

@Composable
fun DetailScreen(productId:Int){
    val product = Product.productList.first { it.id == productId }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
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

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DetailScreenPreview(){
    DetailScreen(1)
}