package com.example.developershortcut.navigation

sealed class AppScreens(val route: String) {

    data object SplashScreen : AppScreens("splash_screen")
    data object MainScreen : AppScreens("main_screen")
    data object OneScreen : AppScreens("one_screen")
    data object TwoScreen : AppScreens("two_screen")
    data object ThreeScreen : AppScreens("three_screen")
    data object FourScreen : AppScreens("four_screen")
    data object QuotesScreen : AppScreens("quotes_screen")
    data object QuoteDetailScreen : AppScreens("quote_detail_screen")
    data object QuoteDetailScreenWithArgs : AppScreens("quote_detail_screen/{mediaId}")
}