package com.arturofilio.hemp_reserve_mvvm.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductsService {

    private val BASE_URL = "http://10.0.2.2:5000/"
    private val api: ProductsApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    fun getProducts(): Single<List<Product>> {
        return api.getProducts()
    }

}