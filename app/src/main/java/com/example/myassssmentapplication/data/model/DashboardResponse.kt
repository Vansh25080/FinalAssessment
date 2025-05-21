package com.example.myassssmentapplication.data.model

import com.google.gson.annotations.SerializedName

data class DashboardResponse(
    @SerializedName("entities")
    val entities: List<Entity>,

    @SerializedName("entityTotal")
    val entityTotal: Int
)
