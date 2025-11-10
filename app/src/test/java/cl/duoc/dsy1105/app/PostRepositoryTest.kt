package cl.duoc.dsy1105.app

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import cl.duoc.dsy1105.app.repository.PostRepository

class PostRepositoryTest {
    @Test
    fun `fetchPosts returns data`() = runBlocking {
        val repo = PostRepository()
        val result = repo.fetchPosts()
        assertTrue(result.isNotEmpty())
    }
}
