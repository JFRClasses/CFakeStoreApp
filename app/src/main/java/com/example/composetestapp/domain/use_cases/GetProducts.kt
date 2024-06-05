package com.example.composetestapp.domain.use_cases

import com.example.composetestapp.data.ProductService
import com.example.composetestapp.domain.models.Product
import com.example.composetestapp.presentation.events.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetProducts(
    private val productService: ProductService
) {
    operator fun invoke() : Flow<ApiResult<List<Product>>> = flow {
        try{
            emit(ApiResult.Loading("Cargando"))
            val response = productService.getProducts()
            emit(ApiResult.Success(response))
        }
        catch (e: Exception){
            emit(ApiResult.Error(message = "La peticion fallo", data = emptyList()))
        }
    }
}