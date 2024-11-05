package com.zosimadis.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zosimadis.list.ListScreen
import kotlinx.serialization.Serializable

@Serializable
data object ListRoute

fun NavController.navigateToList(navOptions: NavOptions) = navigate(route = ListRoute, navOptions)

fun NavGraphBuilder.listScreen(onBookClick: (Int) -> Unit) {
    composable<ListRoute> {
        ListScreen(onBookClick = onBookClick)
    }
}
