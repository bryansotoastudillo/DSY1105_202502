package cl.condokit.develop.gastoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.condokit.develop.gastoapp.model.local.ExpenseEntity
import cl.condokit.develop.gastoapp.model.repository.ExpenseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class FormState(
    val id: Int? = null,
    val descripcion: String = "",
    val fecha: String = "",
    val monto: String = "",
    val categoria: String = "",
    val responsable: String = "",
    val error: String? = null
)

class ExpenseViewModel(private val repo: ExpenseRepository) : ViewModel() {

    val gastos: StateFlow<List<ExpenseEntity>> =
        repo.observarGastos().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _form = MutableStateFlow(FormState())
    val form: StateFlow<FormState> = _form.asStateFlow()

    fun cargarParaEditar(expense: ExpenseEntity) {
        _form.value = FormState(
            id = expense.id,
            descripcion = expense.descripcion,
            fecha = expense.fecha,
            monto = expense.monto.toString(),
            categoria = expense.categoria,
            responsable = expense.responsable
        )
    }

    fun limpiarFormulario() = run { _form.value = FormState() }

    fun onChangeDescripcion(v: String) = _form.update { it.copy(descripcion = v) }
    fun onChangeFecha(v: String)       = _form.update { it.copy(fecha = v) }
    fun onChangeMonto(v: String)       = _form.update { it.copy(monto = v) }
    fun onChangeCategoria(v: String)   = _form.update { it.copy(categoria = v) }
    fun onChangeResponsable(v: String) = _form.update { it.copy(responsable = v) }

    fun guardar() = viewModelScope.launch {
        val f = _form.value
        val montoDouble = f.monto.toDoubleOrNull()
        if (f.descripcion.isBlank() || f.fecha.isBlank() || montoDouble == null ||
            f.categoria.isBlank() || f.responsable.isBlank()
        ) {
            _form.update { it.copy(error = "Completa todos los campos. Monto debe ser numérico.") }
            return@launch
        }
        repo.guardar(f.id, f.descripcion, f.fecha, montoDouble, f.categoria, f.responsable)
        limpiarFormulario()
    }

    fun eliminar(expense: ExpenseEntity) = viewModelScope.launch { repo.eliminar(expense) }
}