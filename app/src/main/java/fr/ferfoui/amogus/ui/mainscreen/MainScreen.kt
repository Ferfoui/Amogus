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
import fr.ferfoui.amogus.data.random.RandomRepository
import fr.ferfoui.amogus.ui.dashboard.DashboardScreen

@Composable
fun MainScreen(
    screenViewModel: RandomViewModel = viewModel(
        factory = RandomViewModelFactory(randomRepository = RandomRepository())
    )
) {
    var showDashboard by remember { mutableStateOf(false) }

    if (showDashboard) {
        DashboardScreen(
            onBack = { showDashboard = false },
            onUpdateIntervalMax = { screenViewModel.intervalMax = it.toUInt() },
            onUpdateGeneratedCount = { screenViewModel.count = it.toUInt() },
            onUpdateExcludedNumbers = { screenViewModel.excludedNumbers = it },
            currentIntervalMax = screenViewModel.intervalMax.toInt(),
            currentGeneratedCount = screenViewModel.count.toInt(),
            currentExcludedNumbers = screenViewModel.excludedNumbers
        )
    } else {
        val uiState by screenViewModel.uiState.collectAsState()
        val mediumPadding = dimensionResource(R.dimen.padding_medium)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            MainScreenContent(
                uiState = uiState,
                onUserGenerateNumbers = screenViewModel::generateNumbers,
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(mediumPadding),
                onOpenDashboard = { showDashboard = true }
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun MainScreenContent(
    uiState: MainScreenUiState,
    modifier: Modifier = Modifier,
    onUserGenerateNumbers: () -> Unit = {},
    onOpenDashboard: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { onOpenDashboard() }
        ) {
            Text(
                text = stringResource(R.string.open_dashboard_text),
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${stringResource(R.string.interval_max_text)} ${String.format("%02d", uiState.intervalMax.toInt())}",
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "${stringResource(R.string.count_text)} ${String.format("%02d", uiState.currentRandomNumbers.size)}",
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )
        }

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { onUserGenerateNumbers() }
        ) {
            Text(
                text = stringResource(R.string.generate_text),
                fontSize = 16.sp
            )
        }

        NumberList(numbers = uiState.currentRandomNumbers)
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
        uiState = MainScreenUiState(
                currentRandomNumbers = listOf(1, 2, 3, 4, 5),
                intervalMax = 100u,
                excludedNumbers = emptyList()
            )
    )
}
