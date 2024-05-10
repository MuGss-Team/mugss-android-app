package com.mugss.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface NavFactory {
    fun create(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    )
}