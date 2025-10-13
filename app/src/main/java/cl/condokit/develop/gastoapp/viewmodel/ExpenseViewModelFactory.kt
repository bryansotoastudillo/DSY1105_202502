package cl.condokit.develop.gastoapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.condokit.develop.gastoapp.model.local.AppDatabase
import cl.condokit.develop.gastoapp.model.repository.ExpenseRepository

class ExpenseViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = AppDatabase.get(app)
        val repo = ExpenseRepository(db.expenseDao())
        return ExpenseViewModel(repo) as T
    }
}