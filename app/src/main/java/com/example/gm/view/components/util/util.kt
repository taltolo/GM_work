package com.example.gm.view.components.util

import androidx.compose.ui.graphics.Color

class util {


    companion object {
        var listColor = listOf(Color.Black,Color.White,Color.Blue,Color.Cyan,Color.Gray,Color.Green,
            Color.LightGray,Color.Magenta,Color.Transparent,Color.Red,Color.Yellow)


    fun getRandonColor(): Color {
        return listColor.random()
    }}
}