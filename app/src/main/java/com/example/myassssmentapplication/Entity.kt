package com.example.myassssmentapplication

import java.io.Serializable

data class Entity(
    val property1: String,
    val property2: String,
    val description: String
) : Serializable
