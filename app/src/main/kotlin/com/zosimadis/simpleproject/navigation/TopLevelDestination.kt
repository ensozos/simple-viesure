package com.zosimadis.simpleproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.outlined.Build
import androidx.compose.ui.graphics.vector.ImageVector
import com.zosimadis.simpleproject.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    ARTICLES(
        selectedIcon = Icons.Filled.Build,
        unselectedIcon = Icons.Outlined.Build,
        iconTextId = R.string.articles,
        titleTextId = R.string.app_name,
    ),
}
