package cl.condokit.develop.gastoapp.model.repository

import cl.condokit.develop.gastoapp.model.local.ExpenseDao
import cl.condokit.develop.gastoapp.model.local.ExpenseEntity
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val dao: ExpenseDao) {

    fun observarGastos(): Flow<List<ExpenseEntity>> = dao.observarTodos()

    suspend fun obtener(id: Int) = dao.obtenerPorId(id)

    suspend fun guardar(
        id: Int?,
        descripcion: String,
        fecha: String,
        monto: Double,
        categoria: String,
        responsable: String
    ) {
        if (id == null || id == 0) {
            dao.insertar(
                ExpenseEntity(
                    descripcion = descripcion.trim(),
                    fecha = fecha,
                    monto = monto,
                    categoria = categoria.trim(),
                    responsable = responsable.trim()
                )
            )
        } else {
            dao.actualizar(
                ExpenseEntity(
                    id = id,
                    descripcion = descripcion.trim(),
                    fecha = fecha,
                    monto = monto,
                    categoria = categoria.trim(),
                    responsable = responsable.trim()
                )
            )
        }
    }

    suspend fun eliminar(expense: ExpenseEntity) = dao.eliminar(expense)
    suspend fun eliminarTodos() = dao.eliminarTodos()
}