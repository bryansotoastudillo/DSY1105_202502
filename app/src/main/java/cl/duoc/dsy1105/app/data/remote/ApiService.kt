package cl.duoc.dsy1105.app.data.remote

import cl.duoc.dsy1105.app.data.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
