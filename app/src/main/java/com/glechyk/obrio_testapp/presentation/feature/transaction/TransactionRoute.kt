package com.glechyk.obrio_testapp.presentation.feature.transaction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TransactionRoute(
    viewModel: TransactionViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    val balanceState = viewModel.balanceState.collectAsState()

    TransactionScreen(
        balance = balanceState.value, onAddButtonClick = { scope, amount, category ->
            scope.launch {
                viewModel.insertTransaction(amount, category)
                delay(1000L)
                onNavigateBack()
            }
        }
    )
}