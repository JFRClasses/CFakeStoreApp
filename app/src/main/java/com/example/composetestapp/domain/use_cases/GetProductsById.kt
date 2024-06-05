package com.example.composetestapp.domain.use_cases

import com.example.composetestapp.data.ProductService
import com.example.composetestapp.domain.ApiResult
import com.example.composetestapp.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProductsById(
    private val productsService : ProductService
) {
    operator fun invoke(id : Int) : Flow<ApiResult<Product>> = flow {
        try {
            emit(ApiResult.Loading("Cargando"))
            val response = productsService.getProductById(id)
            emit(ApiResult.Success(data = response))
        }
        catch (e : Exception){
            emit(ApiResult.Error(message = "Error", data = null))
        }
    }
}