package fr.ferfoui.amogus.ui.mainscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        MainScreenContent(
            currentRandomNumbers = uiState.currentRandomNumbers,
            onUserGenerateNumbers = screenViewModel::generateNumbers,
            modifier = Modifier
                .padding(vertical = 32.dp)
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
        var generatedCount by remember { mutableIntStateOf(5) }

        TextField(
            modifier = Modifier.padding(16.dp),
            value = if (intervalMax != 0) intervalMax.toString() else "",
            onValueChange = {
                intervalMax = it.toIntOrNull() ?: 0
                if (generatedCount > intervalMax) {
                    generatedCount = intervalMax
                }
            },
            label = { Text(stringResource(R.string.enter_number_text)) }
        )

        TextField(
            modifier = Modifier.padding(16.dp),
            value = if (generatedCount != 0) generatedCount.toString() else "",
            onValueChange = {
                val value = it.toIntOrNull()
                generatedCount = if (value != null && value > 0) {
                    if (value > intervalMax) intervalMax else value
                } else 0
            },
            label = { Text(stringResource(R.string.enter_count_text)) }
        )

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { onUserGenerateNumbers(intervalMax, generatedCount) }
        ) {
            Text(
                text = stringResource(R.string.generate_text),
                fontSize = 16.sp
            )
        }

        NumberList(numbers = currentRandomNumbers)
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun NumberList(numbers: List<Int>, modifier: Modifier = Modifier) {
    LazyColumn (
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .size(500.dp)
            .fillMaxWidth()
    ) {
        itemsIndexed(numbers) { index, number ->
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${stringResource(R.string.number_text)} ${String.format("%02d", index + 1)} :",
                    fontSize = 24.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = number.toString(),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            HorizontalDivider()

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
