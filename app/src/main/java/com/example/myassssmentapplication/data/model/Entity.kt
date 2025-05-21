package com.example.myassssmentapplication.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Entity(
    @SerializedName("property1")
    val property1: String?,

    @SerializedName("property2")
    val property2: String?,

    @SerializedName("description")
    val description: String?
) : Serializable
