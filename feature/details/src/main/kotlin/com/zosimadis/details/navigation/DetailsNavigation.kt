package com.zosimadis.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.zosimadis.details.DetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data class DetailsRoute(val id: Int)

fun NavController.navigateToDetails(id: Int, navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = DetailsRoute(id)) { navOptions() }

fun NavGraphBuilder.detailsScreen() {
    composable<DetailsRoute> {
        DetailsScreen()
    }
}
