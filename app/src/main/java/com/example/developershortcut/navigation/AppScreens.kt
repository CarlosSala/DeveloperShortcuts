package com.example.developershortcut.navigation

sealed class AppScreens(val route: String) {
    data object TestOneScreen : AppScreens("test_one_screen")
    data object TestTwoScreen : AppScreens("test_two_screen")
    data object TestThreeScreen : AppScreens("test_three_screen")

}