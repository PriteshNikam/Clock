import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun NavigationController(){
    val navigationController = rememberNavController()

    NavHost(navController = navigationController,  startDestination = Screen.Home){
        composable<Screen.Home>{ Screen.Home }
        composable<Screen.Screen1> { Screen.Screen1  }
    }
}

sealed class Screen{

    @Serializable
    data object Home: Screen()

    @Serializable
    data object Screen1: Screen()
}