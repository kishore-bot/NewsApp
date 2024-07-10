package com.example.newsapp.newsapp.presentation.onbording.components

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Country(
    val name: String,
    val code: String,
    @DrawableRes val flag: Int,
)


val countries = listOf(
    Country(name = "United States", code = "us", flag = R.drawable.flag_us),
    Country(name = "United Kingdom", code = "gb", flag = R.drawable.flag_uk),
    Country(name = "China", code = "cn", flag = R.drawable.flag_china),
    Country(name = "India", code = "in", flag = R.drawable.flag_india),
    Country(name = "Japan", code = "jp", flag = R.drawable.flag_japan),
    Country(name = "Germany", code = "de", flag = R.drawable.flag_germany),
    Country(name = "France", code = "fr", flag = R.drawable.flag_france),
    Country(name = "Russia", code = "ru", flag = R.drawable.flag_russia),
    Country(name = "Brazil", code = "br", flag = R.drawable.flag_brazil),
    Country(name = "Spain", code = "es", flag = R.drawable.flag_spain),
)
