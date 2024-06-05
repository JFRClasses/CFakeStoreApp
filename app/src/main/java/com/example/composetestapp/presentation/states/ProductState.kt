package com.example.composetestapp.presentation.states

import com.example.composetestapp.domain.models.Product
import com.example.composetestapp.domain.models.Rating


data class ProductState(
    val isLoading : Boolean = false,
    val product : Product = Product(
        id = 0,
        title = "",
        description = "",
        price = 0.0,
        category = "",
        rating = Rating(count = 0, rate = 0.0),
        image = ""
    ),
    val errorMessage : String = ""
)