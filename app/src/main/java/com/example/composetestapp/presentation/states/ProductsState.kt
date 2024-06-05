package com.example.composetestapp.presentation.states

import com.example.composetestapp.domain.models.Product


data class ProductsState(
    val isLoading : Boolean = false,
    val products : List<Product> = emptyList(),
    val errorMessage : String = ""
)
