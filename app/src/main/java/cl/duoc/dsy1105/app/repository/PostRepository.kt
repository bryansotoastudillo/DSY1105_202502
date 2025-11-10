package cl.duoc.dsy1105.app.repository

import cl.duoc.dsy1105.app.data.model.Post
import cl.duoc.dsy1105.app.data.remote.RetrofitInstance

class PostRepository {
    suspend fun fetchPosts(): List<Post> = RetrofitInstance.api.getPosts()
}
