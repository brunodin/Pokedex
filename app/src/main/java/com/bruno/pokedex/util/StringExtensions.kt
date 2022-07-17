package com.bruno.pokedex.util

const val EMPTY_STRING = ""

fun String.firstCharUpperCase() = this.replaceFirstChar { it.uppercase() }