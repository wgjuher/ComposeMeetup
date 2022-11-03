package com.wgjuh.composetestapp.meet

import androidx.compose.runtime.Immutable

@Immutable
class Foo(val id: String, val items: List<String>)

class Boo {
    private val items = mutableListOf<String>()
    fun createInitFoo(): Foo = Foo("", items)
    fun addItem(item: String) {
        items.add(item)
    }
}
class Ref(var value: Int)
