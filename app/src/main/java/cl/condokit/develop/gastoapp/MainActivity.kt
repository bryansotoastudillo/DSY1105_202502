package cl.condokit.develop.gastoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cl.condokit.develop.gastoapp.ui.theme.GastoAppTheme
import cl.condokit.develop.gastoapp.viewmodel.ExpenseViewModel
import cl.condokit.develop.gastoapp.viewmodel.ExpenseViewModelFactory

class MainActivity : ComponentActivity() {

    private val vm: ExpenseViewModel by viewModels {
        ExpenseViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GastoAppTheme {
                GastosNavHost(vm)
            }
        }
    }
}