package com.zosimadis.simpleproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zosimadis.details.navigation.detailsScreen
import com.zosimadis.details.navigation.navigateToDetails
import com.zosimadis.list.navigation.ListRoute
import com.zosimadis.list.navigation.listScreen

@Composable
fun ViesureNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ListRoute,
        modifier = modifier,
    ) {
        listScreen(
            onBookClick = {
                navController.navigateToDetails(id = it)
            },
        )

        detailsScreen()
    }
}
