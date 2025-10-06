package com.example.appmovil.data.repository

import com.example.appmovil.data.model.Producto
import com.example.appmovil.data.remote.RetrofitClient

class ProductoRepository {
    private val api = RetrofitClient.api

    suspend fun getProductos() = api.getAll()
    suspend fun buscar(nombre: String) = api.buscar(nombre)
    suspend fun crear(producto: Producto) = api.create(producto)
    suspend fun actualizar(id: Long, producto: Producto) = api.update(id, producto)
    suspend fun eliminar(id: Long) = api.delete(id)
}