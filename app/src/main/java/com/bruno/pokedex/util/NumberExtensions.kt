package com.bruno.pokedex.util

const val ZERO = 0f
fun Int?.orZero() = this ?: 0
fun Float?.orZero() = this ?: 0f