package com.wgjuh.composetestapp.meet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("MagicNumber", "FunctionNaming")
@Composable
fun Snapshot(content: @Composable (SnapshotStateList<ListItem>, Color) -> Unit) {
    val items = remember { mutableStateListOf<ListItem>() }
    var header by remember { mutableStateOf(randomColor) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = {
                items.add(ListItem())
            }) {
                Text(text = "Add last")
            }

            Button(onClick = { header = randomColor }) {
                Text(text = "Change triangle")
            }
        }

        Text(text = "Raw size now: ${items.size}")
        content(items, header)
    }
}

@Suppress("MagicNumber", "FunctionNaming")
@Composable
fun SnapshotFin(content: @Composable (SnapshotStateList<ListItem>, Color, (ListItem) -> Unit) -> Unit) {
    val items = remember { mutableStateListOf<ListItem>() }
    var header by remember { mutableStateOf(randomColor) }

    fun addItem(item: ListItem, position: Int? = null) {
        if (item.color.isNotBlue()) {
            if (position != null) {
                items.add(position, item)
            } else {
                items.add(item)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = {
                addItem(ListItem())
            }) {
                Text(text = "Add last")
            }

            Button(onClick = { header = randomColor }) {
                Text(text = "Change triangle")
            }
        }

        Text(text = "Raw size now: ${items.size}")
        content(items, header) { item ->
            addItem(item, 0)
        }
    }
}
