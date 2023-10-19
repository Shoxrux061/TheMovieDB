package uz.isytem.themoviedb.domain.repository

import kotlinx.coroutines.Dispatchers
import uz.isytem.themoviedb.data.models.detail.DetailResponse
import uz.isytem.themoviedb.data.models.detail.recomendate.RecomandateResponse
import uz.isytem.themoviedb.data.models.detail.similar.SimilarResponse
import uz.isytem.themoviedb.data.models.now_playing.NowPlayingResponse
import uz.isytem.themoviedb.data.models.popular.PopularResponse
import uz.isytem.themoviedb.data.models.search.SearchResponse
import uz.isytem.themoviedb.data.models.top_rated.TopRatedResponse
import uz.isytem.themoviedb.data.models.upcoming.UpcomingResponse
import uz.isytem.themoviedb.data.models.video.VideoResponse
import uz.isytem.themoviedb.data.network.MovieService
import uz.isytem.themoviedb.utills.ResultWrapper
import uz.isytem.themoviedb.utills.parseResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val service: MovieService) {

    suspend fun getMovieList(
        page: Int,
        apiKey: String,
        language: String
    ): ResultWrapper<NowPlayingResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getMovieList(
                header = apiKey,
                language = language,
                page = page
            )
        }
    }

    suspend fun getPopular(
        page: Int,
        apiKey: String,
        language: String
    ): ResultWrapper<PopularResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPopular(
                apiKey = apiKey,
                language = language,
                page = page
            )
        }
    }

    suspend fun getTopRated(
        page: Int,
        apiKey: String,
        language: String
    ): ResultWrapper<TopRatedResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getTopRated(
                apiKey = apiKey,
                language = language,
                page = page
            )
        }
    }

    suspend fun getUpcoming(
        page: Int,
        apiKey: String,
        language: String
    ): ResultWrapper<UpcomingResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getUpcoming(
                apiKey = apiKey,
                language = language,
                page = page
            )
        }
    }

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: Int
    ): ResultWrapper<DetailResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getMovieDetail(
                apiKey = apiKey,
                language = language,
                id = id
            )
        }
    }

    suspend fun getSimilar(
        apiKey: String,
        language: String,
        id: Int,
        page: Int
    ): ResultWrapper<SimilarResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getSimilar(
                apiKey = apiKey,
                language = language,
                id = id,
                page = page
            )
        }
    }

    suspend fun getRecommend(
        apiKey: String,
        language: String,
        id: Int,
        page: Int
    ): ResultWrapper<RecomandateResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getMovieRecommend(
                apiKey = apiKey,
                language = language,
                id = id,
                page = page
            )
        }
    }
    suspend fun getVideo(
        apiKey: String,
        id: Int
    ): ResultWrapper<VideoResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getMovieVideo(
                apiKey = apiKey,
                id = id,
            )
        }
    }
    suspend fun searchMovie(
        apiKey: String,
        query:String,
        language: String,
        page: Int
    ): ResultWrapper<SearchResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.searchMovie(
                apiKey = apiKey,
                language = language,
                page = page,
                query = query
            )
        }
    }
}