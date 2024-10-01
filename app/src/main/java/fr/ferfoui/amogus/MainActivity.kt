package fr.ferfoui.amogus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.ferfoui.amogus.data.random.generateRandomNumber
import fr.ferfoui.amogus.ui.theme.AmogusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmogusTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    RandomNumbers()
                }
            }
        }
    }
}

@Composable
fun RandomNumbers(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var intervalMax by remember { mutableIntStateOf(16) }
        var generatedNumber by remember { mutableIntStateOf(0) }

        TextField(
            value = intervalMax.toString(),
            onValueChange = {
                intervalMax = it.toIntOrNull() ?: 0
            },
            label = { Text(stringResource(R.string.enter_number_text)) }
        )

        Text(
            text = "The number is $generatedNumber",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Button({ generatedNumber = generateRandomNumber(intervalMax) }) {
            Text(
                text = stringResource(R.string.generate_text),
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewRandomNumbers() {
    AmogusTheme {
        RandomNumbers()
    }
}
