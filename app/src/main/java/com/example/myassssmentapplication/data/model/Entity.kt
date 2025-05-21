package com.example.myassssmentapplication.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Entity(
    @SerializedName("assetType")
    val assetType: String?,

    @SerializedName("ticker")
    val ticker: String?,

    @SerializedName("currentPrice")
    val currentPrice: Double?,

    @SerializedName("dividendYield")
    val dividendYield: Double?,

    @SerializedName("description")
    val description: String?
) : Serializable
