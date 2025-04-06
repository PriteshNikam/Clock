package com.developersphere.clock.presentation.common_compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.developersphere.clock.presentation.navigation.Screen


@Composable
fun CommonBottomAppBar(navController: NavController) {

    val bottomNavigationRoutes = listOf(
        Screen.Alarm,
        Screen.Clock,
        Screen.Timer,
        Screen.StopWatch,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Surface(
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        NavigationBar(
            containerColor = Color(0xff363E46),
            modifier = Modifier.fillMaxWidth()
        ) {
            bottomNavigationRoutes.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination == screen.route,
                    onClick = {
                        if (currentDestination != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(painter = painterResource(id = screen.iconId), contentDescription = screen.route)
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