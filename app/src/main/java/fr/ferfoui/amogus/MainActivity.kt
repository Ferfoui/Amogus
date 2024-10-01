package fr.ferfoui.amogus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import fr.ferfoui.amogus.ui.mainscreen.MainScreen
import fr.ferfoui.amogus.ui.theme.AmogusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmogusTheme {
                MainScreen()
            }
        }
    }
}
