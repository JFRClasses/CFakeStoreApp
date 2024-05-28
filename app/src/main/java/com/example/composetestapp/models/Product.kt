package com.example.composetestapp.models
data class Product(
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
){
    val computedPrice get() = "$$price"
    val computedTitle get() = if(title.length > 11) title.substring(0,11) else title

    companion object{
        val productList = List(10){
            Product(
                id = 1,
                category = "Electronics",
                description = "The iPhone 12 Pro display has rounded corners that follow a beautiful curved design, and these corners are within a standard rectangle. When measured as a standard rectangular shape, the screen is 6.06 inches diagonally (actual viewable area is less).",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                price = 1000.0,
                rating = Rating(4, 4.5),
                title = "iPhone 12 Pro Max"
            )
        }
    }
}