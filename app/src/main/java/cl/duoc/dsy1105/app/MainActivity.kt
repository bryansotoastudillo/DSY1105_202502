package cl.duoc.dsy1105.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import cl.duoc.dsy1105.app.ui.theme.DuocDSY1105Theme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.duoc.dsy1105.app.ui.screens.PostScreen
import cl.duoc.dsy1105.app.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuocDSY1105Theme {
                Surface {
                    val vm: PostViewModel = viewModel()
                    PostScreen(vm)
                }
            }
        }
    }
}
