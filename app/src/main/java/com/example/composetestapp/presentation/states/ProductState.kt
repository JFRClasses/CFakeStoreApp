package com.example.composetestapp.presentation.states

import com.example.composetestapp.domain.models.Product

data class ProductState(
    val isLoading : Boolean = false,
    val product : Product? = null,
    val errorMessage : String = ""
)