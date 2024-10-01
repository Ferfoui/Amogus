package fr.ferfoui.amogus.ui.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.ferfoui.amogus.R

@Composable
fun MainScreen(
    screenViewModel: MainScreenViewModel = viewModel()
) {
    val uiState by screenViewModel.uiState.collectAsState()
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        MainScreenContent(
            currentRandomNumbers = uiState.currentRandomNumbers,
            onUserGenerateNumbers = screenViewModel::generateNumbers,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(mediumPadding)
        )
    }
}

@Composable
fun MainScreenContent(
    currentRandomNumbers: List<Int>,
    onUserGenerateNumbers: (Int, Int) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var intervalMax by remember { mutableIntStateOf(16) }
        var generatedCount by remember { mutableIntStateOf(0) }

        TextField(
            value = if (intervalMax != 0) intervalMax.toString() else "",
            onValueChange = {
                intervalMax = it.toIntOrNull() ?: 0
            },
            label = { Text(stringResource(R.string.enter_number_text)) }
        )

        Button(onClick = {
            onUserGenerateNumbers(intervalMax, 5)
        }) {
            Text(
                text = stringResource(R.string.generate_text),
                fontSize = 16.sp
            )
        }

        currentRandomNumbers.forEach {
            Text(
                text = it.toString(),
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        currentRandomNumbers = listOf(1, 2, 3, 4, 5)
    )
}
