package com.ringo.pizzadeleivery.domain

import android.media.Image

data class Pizza(
    val image: String,
    val name:String,
    val price:Int,
    val description: String
)