package com.example.appmovil.data.remote

import com.example.appmovil.data.model.Producto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductoApiService {
    @GET("productos")
    suspend fun getAll(): List<Producto>

    @GET("productos/buscar")
    suspend fun buscar(@Query("nombre") nombre: String): List<Producto>

    @POST("productos")
    suspend fun create(@Body producto: Producto): Producto

    @POST("productos/{id}")
    suspend fun update(@Path("id") id: Long, @Body producto: Producto): Producto

    @DELETE("productos/{id}")
   suspend fun delete(@Path("id") id: Long)
}