package com.wgjuh.composetestapp.meet

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

class ListItem(val id: Int = Random.nextInt(0, 99), val color: Color = randomColor)

@Stable
class CollectionHolder {
    var items = listOf<ListItem>()

    private fun addAll(newItems: List<ListItem>) {
        items = items + newItems
    }

    fun addFirst(newItem: ListItem) {
        items = items.toMutableList().apply { add(0, newItem) }
    }

    fun updateState(items: List<ListItem>) {
        items
             .filter { it.color.isNotBlue() }
            .filter { this.items.contains(it).not() }
            .run(::addAll)
    }
}


class MutableCollectionHolder {
    var items by mutableStateOf(listOf<ListItem>())

    private fun addAll(newItems: List<ListItem>) {
        items = items + newItems
    }

    fun addFirst(newItem: ListItem) {
        items = items.toMutableList().apply { add(0, newItem) }
    }

    fun updateState(items: List<ListItem>) {
        items
             .filter { it.color.isNotBlue() }
            .filter { this.items.contains(it).not() }
            .run(::addAll)
    }
}

class StableCollectionHolder {

    val items = mutableStateListOf<ListItem>()

    private fun addAll(newItems: List<ListItem>) {
        items.addAll(newItems)
    }

    fun addFirst(newItem: ListItem) {
        items.add(0, newItem)
    }

    fun updateState(items: List<ListItem>) {
        items
             .filter { it.color.isNotBlue() }
            .filter { this.items.contains(it).not() }
            .run(::addAll)
    }
}
