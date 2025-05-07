package fr.ferfoui.amogus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.ferfoui.amogus.data.random.RandomRepository
import fr.ferfoui.amogus.data.storage.DataStoreManager
import fr.ferfoui.amogus.data.storage.DataStoreRepository
import fr.ferfoui.amogus.ui.RandomViewModelFactory
import fr.ferfoui.amogus.ui.mainscreen.MainScreen
import fr.ferfoui.amogus.ui.theme.AmogusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmogusTheme {
                Surface {
                    MainScreen(
                        viewModel(
                            factory = RandomViewModelFactory(
                                randomRepository = RandomRepository(
                                    dataStoreRepository = DataStoreRepository(
                                        dataStoreManager = DataStoreManager(this)
                                    )
                                )
                            )
                        )
                    )
                }
            }
        }
    }
}
