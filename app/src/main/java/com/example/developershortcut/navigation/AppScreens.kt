package com.example.developershortcut.navigation

sealed class AppScreens(val route: String) {
    data object OneScreen : AppScreens("one_screen")
    data object TwoScreen : AppScreens("two_screen")
    data object ThreeScreen : AppScreens("three_screen")

}