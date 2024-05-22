package ke.co.banit.composenavigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object DetailedScreen : Screen("detailed_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}