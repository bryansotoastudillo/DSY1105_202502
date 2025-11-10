package cl.duoc.dsy1105.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.duoc.dsy1105.app.viewmodel.PostViewModel
import cl.duoc.dsy1105.app.viewmodel.UiState
import androidx.compose.material3.ExperimentalMaterial3Api


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel) {

    val uiState by viewModel.state.collectAsState()

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DSY1105 - Duoc UC (Posts)") },
                actions = {
                    TextButton(onClick = { viewModel.loadPosts() }) {
                        Text("Refrescar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            // FORMULARIO DE CREACIÓN
            Text(
                text = "Crear nuevo post",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text("Contenido") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 4
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.createPost(title, body)
                    // Si el estado no terminó en Error, limpiamos el formulario
                    if (viewModel.state.value !is UiState.Error) {
                        title = ""
                        body = ""
                    }
                }
            ) {
                Text("Guardar")
            }

            Spacer(Modifier.height(24.dp))

            // LISTA + MANEJO DE ESTADOS
            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Error -> {
                    val message = (uiState as UiState.Error).message
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                is UiState.Success -> {
                    val posts = (uiState as UiState.Success).data
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(posts) { post ->
                            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                                Column(Modifier.padding(12.dp)) {
                                    Text(
                                        post.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        post.body,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}