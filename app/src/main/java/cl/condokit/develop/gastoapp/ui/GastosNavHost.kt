package cl.condokit.develop.gastoapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.condokit.develop.gastoapp.viewmodel.ExpenseViewModel

@Composable
fun GastosNavHost(vm: ExpenseViewModel) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "lista") {
        composable("lista") {
            ListaScreen(
                vm = vm,
                onAdd = { nav.navigate("form") },
                onEdit = { gasto ->
                    vm.cargarParaEditar(gasto)
                    nav.navigate("form")
                }
            )
        }
        composable("form") {
            FormScreen(
                vm = vm,
                onBack = {
                    vm.limpiarFormulario()
                    nav.popBackStack()
                },
                onSaved = {
                    vm.guardar()
                    nav.popBackStack()
                }
            )
        }
    }
}