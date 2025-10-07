package com.example.appmovil.data.model

data class Product(
    val id: Long?,              // Opcional porque el backend lo genera automáticamente
    val name: String,
    val price: Double,
    val category: String?,      // Puede ser nulo si el producto no tiene categoría
    val createdAt: String?      // Opcional porque lo asigna el servidor al crear el producto
)

data class ProductRequest(
    val name: String,
    val price: Double,
    val category: String?
)