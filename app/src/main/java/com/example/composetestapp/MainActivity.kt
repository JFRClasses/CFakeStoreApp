package com.example.composetestapp

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetestapp.screens.HomeScreen
import com.example.composetestapp.screens.ProductDetailScreen
import com.example.composetestapp.ui.theme.ComposeTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            HomeScreen(navController)
                        }

                        composable(
                            route = "detail/{productId}",
                            arguments = listOf(navArgument("productId") { type = NavType.IntType })
                        ){
                            val productId = it.arguments?.getInt("productId") ?: 0
                            ProductDetailScreen(productId = productId)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun MyColumn() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
                .weight(1f)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Blue)
                .weight(1f)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Yellow)
                .weight(1f)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Green)
                .weight(1f)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Cyan)
                .weight(1f)
        )
    }
}

@Composable
fun MyList() {
    val products = listOf("Hamburguesa", "Pizza", "Tacos", "Sushi", "Ensalada")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(products) { product ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        print(product)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.List, contentDescription = "ProductIcon")
                Text(product)
            }
        }
    }
}

@Composable
fun MyRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(
                rememberScrollState(),
            ),
    ) {
        Text(
            "Ejemplo 1", modifier = Modifier
                .background(Color.Red)
                .width(100.dp)
        )
        Text(
            "Ejemplo 2", modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
        )
        Text(
            "Ejemplo 3", modifier = Modifier
                .background(Color.Yellow)
                .width(200.dp)
        )
        Text(
            "Ejemplo 4", modifier = Modifier
                .background(Color.Green)
                .width(100.dp)
        )
    }
}

@Composable
fun MyBox() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text("Texto 1")
        Text("Texto mas grande")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MyComplexLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .weight(1f)
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Yellow)
                    .weight(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text("Texto 1")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Green)
                    .weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text("Texto 2")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .weight(1f)
        ) {

        }
    }
}

@Composable
fun MyState() {
//    val counter = remember {
//        mutableStateOf(0)
//    }
    // Funciona solo si no se destruye el activity
//    var counter by remember{
//        mutableIntStateOf(0)
//    }
    var counter by rememberSaveable {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("El valor del contador es ${counter}")
        Button(onClick = {
            counter++
            Log.i("Counter", counter.toString())
        }) {
            Text("Incrementar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPreview() {
    MyState()
}