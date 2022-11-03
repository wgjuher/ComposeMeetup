package com.wgjuh.composetestapp.meet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun Circle(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun Rectangle(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(RectangleShape)
            .background(color)
    )
}

@Composable
fun Triangle(
    color: Color ,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(GenericShape { size, _ ->
                moveTo(size.width / 2, 0f)
                lineTo(size.width - 1, size.height - 1)
                relativeLineTo(-size.width, 0f)
            })
            .background(color)
    )
}

@Composable
@Preview
fun RowWithATriangle() {
    Row {
        Triangle(randomColor)
        Rectangle(randomColor)
        Circle(randomColor)
    }
}

fun Color.isNotBlue() = this != Color.Blue
val randomColor get() = colors[Random.nextInt(colors.size)]
private val colors = listOf(Color.Red, Color.Green, Color.Blue)
