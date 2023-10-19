package uz.isytem.themoviedb.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.isytem.themoviedb.data.models.now_playing.NowPlayingResponse
import uz.isytem.themoviedb.data.models.popular.PopularResponse
import uz.isytem.themoviedb.data.models.top_rated.TopRatedResponse
import uz.isytem.themoviedb.data.models.upcoming.UpcomingResponse
import uz.isytem.themoviedb.domain.repository.Repository
import uz.isytem.themoviedb.utills.Constants
import uz.isytem.themoviedb.utills.ResultWrapper
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val successLiveData: MutableLiveData<NowPlayingResponse?> =
        MutableLiveData<NowPlayingResponse?>()
    val successResponse: LiveData<NowPlayingResponse?>
        get() = successLiveData

    private val errorLiveData: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponse: LiveData<String?>
        get() = errorLiveData

    init {
        getNowPlaying()
        getPopular()
        getTopRated()
        getUpcoming()
    }

    fun getNowPlaying() {

        viewModelScope.launch {
            when (val result = repository.getMovieList(
                apiKey = Constants.API_KEY,
                language = "en", page = 1
            )) {
                is ResultWrapper.Success -> {
                    successLiveData.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorLiveData.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLiveData.postValue("No internet")
                }
            }
        }
    }

    private val successLiveDataPopular: MutableLiveData<PopularResponse?> =
        MutableLiveData<PopularResponse?>()
    val successResponsePopular: LiveData<PopularResponse?>
        get() = successLiveDataPopular

    private val errorLiveDataPopular: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponsePopular: LiveData<String?>
        get() = errorLiveDataPopular

    fun getPopular() {
        viewModelScope.launch {
            when (val result = repository.getPopular(
                apiKey = Constants.API_KEY, language = "en", page = 1
            )) {
                is ResultWrapper.Success -> {
                    successLiveDataPopular.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorLiveDataPopular.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLiveDataPopular.postValue("No internet")
                }
            }
        }
    }

    private val successLiveDataTopRated: MutableLiveData<TopRatedResponse?> =
        MutableLiveData<TopRatedResponse?>()
    val successResponseTopRated: LiveData<TopRatedResponse?>
        get() = successLiveDataTopRated

    private val errorLiveDataTopRated: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponseTopRated: LiveData<String?>
        get() = errorLiveDataTopRated

    fun getTopRated() {
        viewModelScope.launch {
            when (val result = repository.getTopRated(
                apiKey = Constants.API_KEY, language = "en", page = 1
            )) {
                is ResultWrapper.Success -> {
                    successLiveDataTopRated.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorLiveDataTopRated.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLiveDataTopRated.postValue("No internet")
                }
            }
        }
    }

    private val successLiveDataUpcoming: MutableLiveData<UpcomingResponse?> =
        MutableLiveData<UpcomingResponse?>()
    val successResponseUpcoming: LiveData<UpcomingResponse?>
        get() = successLiveDataUpcoming

    private val errorLiveDataUpcoming: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponseUpcoming: LiveData<String?>
        get() = errorLiveDataUpcoming

    fun getUpcoming() {
        viewModelScope.launch {
            when (val result = repository.getUpcoming(
                apiKey = Constants.API_KEY, language = "en", page = 1
            )) {
                is ResultWrapper.Success -> {
                    successLiveDataUpcoming.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorLiveDataUpcoming.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLiveDataUpcoming.postValue("No internet")
                }
            }
        }
    }
}