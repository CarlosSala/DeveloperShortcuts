package com.example.developershortcut.navigation

import kotlinx.serialization.Serializable


sealed class AppScreens(val route: String) {

    data object SplashScreen : AppScreens("splash_screen")
    data object MainScreen : AppScreens("main_screen")
    data object OneScreen : AppScreens("one_screen")
    data object IntentActionsScreen : AppScreens("intent_actions_screen")
    data object ThreeScreen : AppScreens("three_screen")
    data object FourScreen : AppScreens("four_screen")
    data object QuotesScreen : AppScreens("quotes_screen")
    data object QuoteDetailScreen : AppScreens("quote_detail_screen")
    data object QuoteDetailScreenWithArgs : AppScreens("quote_detail_screen/{mediaId}")
}

@Serializable
data class IntentActionsScreenSettings(val intentActionId: Int)
