package com.wgjuh.composetestapp.meet


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wgjuh.composetestapp.ui.theme.ComposeTestAppTheme

@Composable
fun Ver1(items: List<ListItem>, triangleColor: Color) {
    val filtered = items.filter { it.color.isNotBlue() }.toMutableList()
    CollectionFeeds(filtered, triangleColor)
}

@Composable
fun Ver2(items: List<ListItem>, triangleColor: Color, setValue: ((ListItem) -> Unit)? = null) {
    val filtered = remember(items.size) { items.filter { it.color.isNotBlue() }.toMutableList() }
    CollectionFeeds(filtered, triangleColor, setValue)
}


@Composable
fun Ver3(items: List<ListItem>, triangleColor: Color) {
    val filtered = remember(items.size) { items.filter { it.color.isNotBlue() }.toMutableList() }
    CollectionFeeds(filtered, triangleColor)
}

@Composable
fun Ver3_1(items: List<ListItem>, triangleColor: Color) {
    val filtered = remember(items.size) { items.filter { it.color.isNotBlue() }.toMutableList() }
    SnapshotFeeds(
        triangleColor = triangleColor,
        updateCollection = { index, item -> filtered.add(index, item) }
    ) {
        Feed(filtered)
    }
}

@Composable
fun Ver3_2(items: List<ListItem>, triangleColor: Color) {
    val filtered = remember(items.size) { items.filter { it.color.isNotBlue() }.toMutableList() }
    Button(onClick = { filtered.add(0, ListItem()) }) {
        /**/
    }
    Triangle(color = triangleColor)
    Feed(filtered)
}

@Composable
fun Ver3_3(items: List<ListItem>, triangleColor: Color) {
    var filtered by remember(items.size) { mutableStateOf(items.filter { it.color.isNotBlue() }) }
    Button(onClick = { filtered = filtered.toMutableList().apply { add(0, ListItem()) } }) {
        /**/
    }
    Triangle(color = triangleColor)
    Feed(filtered)
}

@Composable
fun Ver3_4(items: List<ListItem>, triangleColor: Color) {
    val (filtered, setValue) = remember(items.size) { mutableStateOf(items.filter { it.color.isNotBlue() }) }
    Button(onClick = { setValue(filtered.toMutableList().apply { add(0, ListItem()) }) }) {
        /**/
    }
    Triangle(color = triangleColor)
    Feed(filtered)
}

@Composable
fun Ver3_5(items: List<ListItem>, triangleColor: Color) {
    val filtered = remember(items.size) { items.filter { it.color.isNotBlue() }.toMutableStateList() }
    Button(onClick = { filtered.add(0, ListItem()) }) {
        /**/
    }
    Triangle(color = triangleColor)
    Feed(filtered)
}


@Composable
fun Ver4(items: List<ListItem>, triangleColor: Color) {
    val filtered by remember {
        mutableStateOf(items.filter { it.color.isNotBlue() }.toMutableList())
    }
    CollectionFeeds(filtered, triangleColor)
}

@Composable
private fun CollectionFeeds(
    filtered: MutableList<ListItem>,
    triangleColor: Color,
    setValue : ((ListItem) -> Unit)? = null,
) {
    SnapshotFeeds(
        triangleColor = triangleColor,
        updateCollection = { index, item -> setValue?.invoke(item) ?: filtered.add(index, item) }
    ) {
        Feed(filtered)
    }
}

@Composable
fun Ver5(items: List<ListItem>, triangleColor: Color) {
    val (filtered, setValue) = remember(items.size) {
        mutableStateOf(items.filter { it.color.isNotBlue() })
    }

    SnapshotFeeds(triangleColor, { index, item ->
        setValue(filtered.toMutableList().apply { add(index, item) })
    }) {
        Feed(filtered)
    }
}

@Composable
fun Ver6(items: List<ListItem>, triangleColor: Color) {
    val filtered =
        remember(items.size) { items.filter { it.color.isNotBlue() }.toMutableStateList() }

    SnapshotFeeds(triangleColor, { index, item -> filtered.add(index, item) }) {
        Feed(filtered)
    }
}

@Composable
fun Ver7(items: List<ListItem>, triangleColor: Color) {
    val filtered = remember { CollectionHolder() }
    RightJustUpdateState(items, filtered::updateState)
    SnapshotFeeds(triangleColor, { _, item -> filtered.addFirst(item) }) {
        Feed(filtered)
    }
}

@Composable
fun Ver8(items: List<ListItem>, triangleColor: Color) {
    val filtered = remember { MutableCollectionHolder() }
    RightJustUpdateState(items, filtered::updateState)
    SnapshotFeeds(triangleColor, { _, item -> filtered.addFirst(item) }) {
        Feed(filtered)
    }
}

@Composable
fun Ver9(items: List<ListItem>, triangleColor: Color) {
    val filtered = remember { StableCollectionHolder() }
    RightJustUpdateState(items, filtered::updateState)
    SnapshotFeeds(
        triangleColor,
        { _, item ->
            filtered.addFirst(item)
        }
    ) {
        Feed(filtered)
    }
}

@Composable
internal fun SnapshotFeeds(
    triangleColor: Color,
    updateCollection: (Int, ListItem) -> Unit,
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Button(onClick = { updateCollection(0, getListItem()) }) {
            Text(text = "Add first")
        }
        Triangle(color = triangleColor)
        content()
    }
}


private fun getListItem() = ListItem(color = randomColor)

@Composable
private fun RightJustUpdateState(
    items: List<ListItem>,
    collectionHolder: (List<ListItem>) -> Unit,
) {
    collectionHolder(items)
}

@Composable
private fun Feed(holder: StableCollectionHolder, modifier: Modifier = Modifier) {
    Feed(collection = holder.items, modifier = modifier)
}

@Composable
private fun Feed(holder: MutableCollectionHolder, modifier: Modifier = Modifier) {
    Feed(collection = holder.items, modifier = modifier)
}

@Composable
private fun Feed(holder: CollectionHolder, modifier: Modifier = Modifier) {
    Feed(collection = holder.items, modifier = modifier)
}

@Composable
private fun Feed(collection: List<ListItem>, modifier: Modifier = Modifier) {
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

@Composable
internal fun Item(item: ListItem) {
    Circle(color = item.color)
}

@Preview
@Composable
fun SnapshotPreview() {
    ComposeTestAppTheme {
        SnapshotFin { items, heatriangleColorer, setValue ->
            Ver2Fin(
                items = items,
                triangleColor = heatriangleColorer,
                setValue = setValue,
            )
        }
    }
}
