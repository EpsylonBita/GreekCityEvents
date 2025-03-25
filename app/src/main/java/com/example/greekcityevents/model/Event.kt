package com.example.greekcityevents.model

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val city: City,
    val category: Category,
    val imageUrl: String,
    val date: String,
    val viewCount: Int = 0,
    val location: String
)

enum class City {
    ATHENS,
    THESSALONIKI,
    PATRAS,
    LARISSA
}

enum class Category {
    MUSIC,
    SPORTS,
    CULTURE,
    FOOD,
    ARTS,
    EDUCATION,
    ENTERTAINMENT
} 