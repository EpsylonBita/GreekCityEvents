package com.example.greekcityevents.data

import com.example.greekcityevents.model.Category
import com.example.greekcityevents.model.City
import com.example.greekcityevents.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class EventRepository {
    private val events = MutableStateFlow(getSampleEvents())

    fun getAllEvents(): Flow<List<Event>> = events

    fun getEventsByCity(city: City): Flow<List<Event>> =
        events.map { eventList -> eventList.filter { it.city == city } }

    fun getEventsByCategory(category: Category): Flow<List<Event>> =
        events.map { eventList -> eventList.filter { it.category == category } }

    fun getMostViewedEvents(limit: Int = 10): Flow<List<Event>> =
        events.map { eventList -> 
            eventList.sortedByDescending { it.viewCount }.take(limit)
        }

    private fun getSampleEvents(): List<Event> = listOf(
        Event(
            id = "1",
            title = "Athens Music Festival",
            description = "Annual music festival featuring top Greek artists",
            city = City.ATHENS,
            category = Category.MUSIC,
            imageUrl = "https://images.unsplash.com/photo-1470229722913-7c0e2dbbafd3",
            date = "2024-05-15",
            viewCount = 1500,
            location = "Technopolis"
        ),
        Event(
            id = "2",
            title = "Thessaloniki Food Festival",
            description = "Traditional Greek cuisine celebration",
            city = City.THESSALONIKI,
            category = Category.FOOD,
            imageUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836",
            date = "2024-06-20",
            viewCount = 1200,
            location = "Aristotelous Square"
        ),
        Event(
            id = "3",
            title = "Patras Carnival",
            description = "Biggest carnival celebration in Greece",
            city = City.PATRAS,
            category = Category.ENTERTAINMENT,
            imageUrl = "https://images.unsplash.com/photo-1581363111677-0a9b49aaa7d9",
            date = "2024-03-30",
            viewCount = 2000,
            location = "City Center"
        ),
        Event(
            id = "4",
            title = "Larissa Cultural Festival",
            description = "A celebration of art and culture in the heart of Thessaly",
            city = City.LARISSA,
            category = Category.CULTURE,
            imageUrl = "https://images.unsplash.com/photo-1533929736458-ca588d08c8be",
            date = "2024-04-10",
            viewCount = 800,
            location = "Larissa Castle"
        )
    )
} 