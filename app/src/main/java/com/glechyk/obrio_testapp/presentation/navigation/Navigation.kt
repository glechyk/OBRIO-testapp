package com.glechyk.obrio_testapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.glechyk.obrio_testapp.presentation.feature.balance.BalanceRoute
import com.glechyk.obrio_testapp.presentation.feature.transaction.TransactionRoute

enum class Screen {
    BALANCE,
    TRANSACTION,
}

sealed class NavigationItem(val route: String) {
    data object Balance : NavigationItem(Screen.BALANCE.name)
    data object Transaction : NavigationItem(Screen.TRANSACTION.name)
}

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationItem.Balance.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Balance.route) {
            BalanceRoute(
                onNavigateToTransaction = { navController.navigate(NavigationItem.Transaction.route) }
            )
        }
        composable(NavigationItem.Transaction.route) {
            TransactionRoute(onNavigateBack = { navController.popBackStack() })
        }
    }
}