# ComposeNavigation

ComposeNavigation is a simple Android application built using Jetpack Compose. This project demonstrates basic navigation between two screens, where a name is passed from one screen and displayed as a welcome message on the other screen.

### Installation

To set up the project locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/angatiabenson/compose-navigation
   ```

2. **Open the project in Android Studio:**
   - Start Android Studio and select "Open an existing Android Studio project".
   - Navigate to the cloned repository and open it.

3. **Build the project:**
   - Allow Android Studio to build and sync the project. This may take a few minutes as it downloads dependencies.

### Usage

To run the app, use the following steps:

1. **Connect an Android device or start an emulator.**

2. **Run the app:**
   - Click on the "Run" button in Android Studio or use the shortcut `Shift + F10`.

## Screens

### Main Screen

The Main Screen allows the user to input their name. It contains a text field and a button to navigate to the Detailed Screen.

```kotlin
@Composable
fun MainScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter Name") }
        )
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                navController.navigate(Screen.DetailedScreen.withArgs(text))
            }) {
            Text(text = "Go to detailed screen")
        }
    }
}
```

### Detailed Screen

The Detailed Screen displays a personalized welcome message using the name passed from the Home Screen.

```kotlin
@Composable
fun DetailedScreen(name: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello $name, Welcome",
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
```

### Screens

Contains the screens routes and a function to build the arguments

```kotlin
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
```

### Navigation

Navigation between screens is handled using `NavHostController`.

```kotlin
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(
            route = Screen.DetailedScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Bantos"
                    nullable = true
                }
            )
        ) { entry ->
            DetailedScreen(name = entry.arguments?.getString("name"))
        }
    }
}
```

## Technologies Used

- **Kotlin**: Programming language.
- **Jetpack Compose**: Modern toolkit for building native Android UI.
- **Android Studio**: Official IDE for Android development.
