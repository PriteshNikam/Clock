package com.developersphere.clock.presentation.common_compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.developersphere.clock.presentation.navigation.routes.BottomNavRoute


@SuppressLint("RestrictedApi")
@Composable
fun CommonBottomAppBar(navController: NavController) {

    val bottomNavigationRoutes = listOf(
        BottomNavRoute.Alarm,
        BottomNavRoute.Clock,
        BottomNavRoute.Timer,
        BottomNavRoute.StopWatch,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    Surface(
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff282F35)),
    ) {
        NavigationBar(
            containerColor = Color(0xff363E46),
            modifier = Modifier.fillMaxWidth()
        ) {
            bottomNavigationRoutes.forEach { screen ->
                // Checks if the current route matches the fully qualified class name of the [screen].
                val isSelected = currentRoute?.hierarchy?.any { it.route == screen::class.qualifiedName } == true
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen) {
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.iconId ?: 0),
                            contentDescription = screen.route
                        )
                    },
                    alwaysShowLabel = true,
                    label = { Text(text = screen.route) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Green,
                        unselectedIconColor = Color.White,
                        selectedTextColor = Color.Green,
                        unselectedTextColor = Color.White,
                        indicatorColor = Color(0xff363E46),
                    ),
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewNavBar() {
    CommonBottomAppBar(navController = rememberNavController())
}