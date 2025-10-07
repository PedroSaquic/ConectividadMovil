package com.example.appmovil.data.repository

import com.example.appmovil.data.model.ProductRequest
import com.example.appmovil.data.remote.RetrofitInstance

class ProductRepository {

    private val api = RetrofitInstance.api

    // Listar todos los productos
    suspend fun listAll() = api.getAllProducts()

    // Obtener un producto por ID
    suspend fun get(id: Long) = api.getProductById(id)

    // Crear un producto
    suspend fun create(request: ProductRequest) = api.createProduct(request)

    // Actualizar un producto existente
    suspend fun update(id: Long, request: ProductRequest) = api.updateProduct(id, request)

    // Eliminar un producto por ID
    suspend fun delete(id: Long) = api.deleteProduct(id)

    // Buscar productos por nombre
    suspend fun search(productName: String) = api.searchProducts(productName)
}