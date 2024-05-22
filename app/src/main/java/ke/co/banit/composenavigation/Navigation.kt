package ke.co.banit.composenavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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