package fr.ferfoui.amogus.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.ferfoui.amogus.R

@Composable
fun DashboardScreen(
    onBack: () -> Unit,
    onUpdateIntervalMax: (Int) -> Unit,
    onUpdateGeneratedCount: (Int) -> Unit,
    onUpdateExcludedNumbers: (List<Int>) -> Unit,
    currentIntervalMax: Int,
    currentGeneratedCount: Int,
    currentExcludedNumbers: List<Int> = emptyList()
) {
    var intervalMax by remember { mutableIntStateOf(currentIntervalMax) }
    var generatedCount by remember { mutableIntStateOf(currentGeneratedCount) }
    var excludedNumbers by remember { mutableStateOf(currentExcludedNumbers.joinToString()) }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.padding(16.dp),
            value = intervalMax.toString(),
            onValueChange = { intervalMax = it.toIntOrNull() ?: 0 },
            label = { Text(stringResource(R.string.enter_number_text)) }
        )

        TextField(
            modifier = Modifier.padding(16.dp),
            value = generatedCount.toString(),
            onValueChange = { generatedCount = it.toIntOrNull() ?: 0 },
            label = { Text(stringResource(R.string.enter_count_text)) }
        )

        TextField(
            modifier = Modifier.padding(16.dp),
            value = excludedNumbers,
            onValueChange = { excludedNumbers = it },
            label = { Text(stringResource(R.string.enter_excluded_numbers_text)) }
        )

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                onUpdateIntervalMax(intervalMax)
                onUpdateGeneratedCount(generatedCount)
                onUpdateExcludedNumbers(excludedNumbers.split(",").mapNotNull { it.toIntOrNull() })
                onBack()
            }
        ) {
            Text(text = stringResource(R.string.save_and_back_text))
        }
    }
}
