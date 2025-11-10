package cl.duoc.dsy1105.app

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test
import cl.duoc.dsy1105.app.viewmodel.PostViewModel
import cl.duoc.dsy1105.app.viewmodel.UiState

class PostViewModelTest {
    @Test
    fun `initial state eventually becomes Success or Error`() = runBlocking {
        val vm = PostViewModel(autoLoad = false)
        var tries = 0
        while (tries < 50 && vm.state.value is UiState.Loading) {
            tries++
            delay(50)
        }
        assertTrue(vm.state.value !is UiState.Loading)
    }

    @Test
    fun `createPost with empty title and body returns Error`() = runBlocking {
        // autoLoad = false para que no se lance loadPosts en el init
        val vm = PostViewModel(autoLoad = false)

        vm.createPost("", "")

        assertTrue(vm.state.value is UiState.Error)
        val message = (vm.state.value as UiState.Error).message
        assertEquals("Título y contenido no pueden estar vacíos", message)
    }
}
