package fr.ferfoui.amogus.ui.mainscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScreenContent(
        currentRandomNumbers = uiState.currentRandomNumbers,
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    )
}

@Composable
fun MainScreenContent(
    currentRandomNumbers: List<Int>,
    modifier: Modifier = Modifier
) {

}
