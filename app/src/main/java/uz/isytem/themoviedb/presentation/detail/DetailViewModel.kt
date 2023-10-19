package uz.isytem.themoviedb.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.isytem.themoviedb.data.models.detail.DetailResponse
import uz.isytem.themoviedb.data.models.detail.recomendate.RecomandateResponse
import uz.isytem.themoviedb.data.models.detail.similar.SimilarResponse
import uz.isytem.themoviedb.data.models.video.VideoResponse
import uz.isytem.themoviedb.domain.repository.Repository
import uz.isytem.themoviedb.utills.Constants
import uz.isytem.themoviedb.utills.ResultWrapper
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val successLiveData: MutableLiveData<DetailResponse?> =
        MutableLiveData<DetailResponse?>()
    val successResponse: LiveData<DetailResponse?>
        get() = successLiveData

    private val errorLiveData: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponse: LiveData<String?>
        get() = errorLiveData



    fun getDetail(id:Int) {
        viewModelScope.launch {
            when (val result = repository.getMovieDetail(
                apiKey = Constants.API_KEY, language = "en", id = id
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

    private val successLiveDataSimilar: MutableLiveData<SimilarResponse?> =
        MutableLiveData<SimilarResponse?>()
    val successResponseSimilar: LiveData<SimilarResponse?>
        get() = successLiveDataSimilar

    private val errorLiveDataSimilar: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponseSimilar: LiveData<String?>
        get() = errorLiveDataSimilar

    fun getSimilar(id:Int){
        viewModelScope.launch {
            when(val result = repository.getSimilar(
                apiKey = Constants.API_KEY,
                language = "en",
                id = id,
                page = 1
            )){
                is ResultWrapper.Success -> {
                    successLiveDataSimilar.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorLiveDataSimilar.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLiveDataSimilar.postValue("No internet")
                }

            }
        }
    }
    private val successLiveDataRecommended: MutableLiveData<RecomandateResponse?> =
        MutableLiveData<RecomandateResponse?>()
    val successResponseRecommended: LiveData<RecomandateResponse?>
        get() = successLiveDataRecommended

    private val errorLiveDataRecommended: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponseRecommended: LiveData<String?>
        get() = errorLiveDataRecommended

    fun getRecommended(id:Int) {
        viewModelScope.launch {
            when (val result = repository.getRecommend(
                apiKey = Constants.API_KEY, language = "en", id = id , page = 1
            )) {
                is ResultWrapper.Success -> {
                    successLiveDataRecommended.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorLiveDataRecommended.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLiveDataRecommended.postValue("No internet")
                }
            }
        }
    }
    private val successLiveDataVideo: MutableLiveData<VideoResponse?> =
        MutableLiveData<VideoResponse?>()
    val successResponseVideo: LiveData<VideoResponse?>
        get() = successLiveDataVideo

    private val errorLiveDataVideo: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponseVideo: LiveData<String?>
        get() = errorLiveDataVideo

    fun getImage(id:Int) {
        viewModelScope.launch {
            when (val result = repository.getVideo(
                apiKey = Constants.API_KEY,id = id
            )) {
                is ResultWrapper.Success -> {
                    successLiveDataVideo.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorLiveDataVideo.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLiveDataVideo.postValue("No internet")
                }
            }
        }
    }
}