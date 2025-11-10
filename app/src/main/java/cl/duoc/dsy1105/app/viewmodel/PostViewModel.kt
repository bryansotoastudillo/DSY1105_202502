package cl.duoc.dsy1105.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import cl.duoc.dsy1105.app.data.model.Post
import cl.duoc.dsy1105.app.repository.PostRepository

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Post>) : UiState()
    data class Error(val message: String) : UiState()
}

class PostViewModel(
    private val repository: PostRepository = PostRepository(),
    autoLoad: Boolean = true
) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    init {
        if (autoLoad) {
            loadPosts()
        }
    }

    fun loadPosts() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            try {
                val posts = repository.fetchPosts()
                _state.value = UiState.Success(posts.take(20))
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    /**
     * Crea un nuevo post en memoria, validando los campos.
     * Si hay error de validación, se setea UiState.Error.
     */
    fun createPost(title: String, body: String) {
        val trimmedTitle = title.trim()
        val trimmedBody = body.trim()

        if (trimmedTitle.isBlank() || trimmedBody.isBlank()) {
            _state.value = UiState.Error("Título y contenido no pueden estar vacíos")
            return
        }

        val currentPosts = (state.value as? UiState.Success)?.data ?: emptyList()
        val nextId = (currentPosts.maxOfOrNull { it.id } ?: 0) + 1

        val newPost = Post(
            userId = 0,
            id = nextId,
            title = trimmedTitle,
            body = trimmedBody
        )

        _state.value = UiState.Success(currentPosts + newPost)
    }
}