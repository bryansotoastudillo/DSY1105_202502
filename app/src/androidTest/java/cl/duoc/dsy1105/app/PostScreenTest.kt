package cl.duoc.dsy1105.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasSetTextAction
import cl.duoc.dsy1105.app.ui.screens.PostScreen
import cl.duoc.dsy1105.app.viewmodel.PostViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class PostScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setContent() {
        composeTestRule.setContent {
            // autoLoad = false para que no llame a la API ni cargue posts al iniciar
            PostScreen(viewModel = PostViewModel(autoLoad = false))
        }
    }

    @Test
    fun showsTopBarAndRefresh() {
        setContent()

        composeTestRule
            .onNodeWithText("DSY1105 - Duoc UC (Posts)")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Refrescar")
            .assertIsDisplayed()
    }

    @Test
    fun whenSaveWithEmptyFields_showsErrorMessage() {
        setContent()

        // No escribimos nada en los campos, solo presionamos "Guardar"
        composeTestRule
            .onNodeWithText("Guardar")
            .performClick()

        // Debe aparecer el mensaje de error definido en el ViewModel
        composeTestRule
            .onNodeWithText("Título y contenido no pueden estar vacíos")
            .assertIsDisplayed()
    }

    @Test
    fun whenValidPostIsSaved_itAppearsInList() {
        setContent()

        val title = "Título de prueba"
        val body = "Contenido de prueba"

        // Escribir en el TextField de "Título"
        composeTestRule
            .onNode(hasText("Título") and hasSetTextAction())
            .performTextInput(title)

        // Escribir en el TextField de "Contenido"
        composeTestRule
            .onNode(hasText("Contenido") and hasSetTextAction())
            .performTextInput(body)

        // Pulsar el botón "Guardar"
        composeTestRule
            .onNodeWithText("Guardar")
            .performClick()

        // Verificar que el nuevo post aparece en la lista
        composeTestRule
            .onNodeWithText(title)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(body)
            .assertIsDisplayed()
    }
}