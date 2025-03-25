package com.example.greekcityevents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greekcityevents.data.EventRepository
import com.example.greekcityevents.model.Category
import com.example.greekcityevents.model.City
import com.example.greekcityevents.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {
    private val repository = EventRepository()

    private val _selectedCity = MutableStateFlow<City?>(null)
    val selectedCity: StateFlow<City?> = _selectedCity

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?> = _selectedCategory

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    private val _mostViewedEvents = MutableStateFlow<List<Event>>(emptyList())
    val mostViewedEvents: StateFlow<List<Event>> = _mostViewedEvents

    init {
        loadMostViewedEvents()
        observeEvents()
    }

    private fun observeEvents() {
        viewModelScope.launch {
            combine(
                repository.getAllEvents(),
                selectedCity,
                selectedCategory
            ) { allEvents, city, category ->
                when {
                    city != null && category != null -> 
                        allEvents.filter { it.city == city && it.category == category }
                    city != null -> 
                        allEvents.filter { it.city == city }
                    category != null -> 
                        allEvents.filter { it.category == category }
                    else -> allEvents
                }
            }.collect { filteredEvents ->
                _events.value = filteredEvents
            }
        }
    }

    private fun loadMostViewedEvents() {
        viewModelScope.launch {
            repository.getMostViewedEvents().collect { events ->
                _mostViewedEvents.value = events
            }
        }
    }

    fun setSelectedCity(city: City?) {
        _selectedCity.value = city
    }

    fun setSelectedCategory(category: Category?) {
        _selectedCategory.value = category
    }
} 