import com.dnlm.posts_client.api.ApiConstants
import com.dnlm.posts_client.models.AuthorResponse
import com.dnlm.posts_client.models.CommentResponse
import com.dnlm.posts_client.models.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET(ApiConstants.PATH_POSTS)
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): Response<List<PostResponse>>

    @GET("${ApiConstants.PATH_USERS}{userId}")
    suspend fun getAuthor(
        @Path("userId") userId: Int
    ): Response<AuthorResponse>

    @GET("${ApiConstants.PATH_POSTS}{postId}${ApiConstants.PATH_COMMENTS}")
    suspend fun getPostComments(
        @Path("postId") postId: Int
    ): Response<List<CommentResponse>>
}