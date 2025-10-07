import com.example.appmovil.data.model.Product
import com.example.appmovil.data.model.ProductRequest
import retrofit2.http.*
import retrofit2.Response


interface ProductApi {

    @GET("/products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<Product>

    @POST("/products")
    suspend fun createProduct(@Body request: ProductRequest): Response<Product>

    @PUT("/products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Long,
        @Body request: ProductRequest
    ): Response<Product>

    @DELETE("/products/{id}")
    suspend fun deleteProduct(@Path("id") id: Long): Response<Unit>

    @GET("/products/search")
    suspend fun searchProducts(
        @Query("productName") name: String
    ): Response<List<Product>>
}