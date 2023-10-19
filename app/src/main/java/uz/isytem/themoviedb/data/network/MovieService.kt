package uz.isytem.themoviedb.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.isytem.themoviedb.data.models.detail.DetailResponse
import uz.isytem.themoviedb.data.models.detail.recomendate.RecomandateResponse
import uz.isytem.themoviedb.data.models.detail.similar.SimilarResponse
import uz.isytem.themoviedb.data.models.now_playing.NowPlayingResponse
import uz.isytem.themoviedb.data.models.popular.PopularResponse
import uz.isytem.themoviedb.data.models.search.SearchResponse
import uz.isytem.themoviedb.data.models.top_rated.TopRatedResponse
import uz.isytem.themoviedb.data.models.upcoming.UpcomingResponse
import uz.isytem.themoviedb.data.models.video.VideoResponse

interface MovieService {
    @GET("/3/movie/now_playing?")
    suspend fun getMovieList(
        @Query("api_key") header: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<NowPlayingResponse?>

    @GET("/3/discover/movie?include_adult=false&include_video=false&sort_by=popularity.desc")
    suspend fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<PopularResponse?>

    @GET("/3/movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<TopRatedResponse?>

    @GET("/3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<UpcomingResponse?>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<DetailResponse?>

    @GET("/3/movie/{id}/similar")
    suspend fun getSimilar(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<SimilarResponse?>

    @GET("/3/movie/{id}/recommendations")
    suspend fun getMovieRecommend(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<RecomandateResponse?>

    @GET("/3/movie/{id}/videos")
    suspend fun getMovieVideo(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<VideoResponse?>


    @GET("/3/search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<SearchResponse?>


}