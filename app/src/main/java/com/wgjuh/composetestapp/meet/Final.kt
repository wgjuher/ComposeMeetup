package com.wgjuh.composetestapp.meet

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Ver2Fin(items: SnapshotStateList<ListItem>, triangleColor: Color, setValue: (ListItem) -> Unit) {
    SnapshotFeeds(
        triangleColor = triangleColor,
        updateCollection = { index, item -> setValue.invoke(item) }
    ) {
        FeedFin(items)
    }
}

@Composable
private fun FeedFin(collection: SnapshotStateList<ListItem>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val ref = remember { Ref(0) }
        SideEffect { ref.value++ }
        Text(text = "This row recompose: ${ref.value} times")
        Row(
            modifier = modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            collection.forEach { item ->
                key(item.id) {
                    Item(item)
                }
            }
        }
    }
}
