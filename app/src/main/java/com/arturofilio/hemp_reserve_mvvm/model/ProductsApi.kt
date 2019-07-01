package com.arturofilio.hemp_reserve_mvvm.model

import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ProductsApi {

    @GET("api/product/products")
    fun getProducts(): Single<List<Product>>
}