package cl.condokit.develop.gastoapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.condokit.develop.gastoapp.model.local.ExpenseEntity
import cl.condokit.develop.gastoapp.viewmodel.ExpenseViewModel
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(
    vm: ExpenseViewModel,
    onAdd: () -> Unit,
    onEdit: (ExpenseEntity) -> Unit
) {
    val gastos by vm.gastos.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Gastos") }) },
        floatingActionButton = { FloatingActionButton(onClick = onAdd) { Text("+") } }
    ) { padding ->
        if (gastos.isEmpty()) {
            Box(
                Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) { Text("Sin registros. Pulsa + para agregar.") }
        } else {
            LazyColumn(Modifier.fillMaxSize().padding(padding)) {
                items(gastos) { g ->
                    ListItem(
                        headlineContent = {
                            Text(g.descripcion, fontWeight = FontWeight.Bold)
                        },
                        supportingContent = {
                            Text("${g.fecha} • ${g.categoria} • ${g.responsable}")
                        },
                        trailingContent = {
                            Text(String.format("$%.2f", g.monto))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEdit(g) }
                            .padding(horizontal = 8.dp)
                    )
                    Divider()
                }
            }
        }
    }
}