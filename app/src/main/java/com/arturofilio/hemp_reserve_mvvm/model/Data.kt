package com.arturofilio.hemp_reserve_mvvm.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("description")
    val description: String?
)