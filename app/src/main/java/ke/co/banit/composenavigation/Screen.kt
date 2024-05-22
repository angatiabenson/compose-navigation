package ke.co.banit.composenavigation

sealed class Screen(val route: String){
    data object MainScreen: Screen("main_screen")
    data object DetailedScreen: Screen("detailed_screen")
}