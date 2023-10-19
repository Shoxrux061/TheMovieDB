package uz.isytem.themoviedb.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.isytem.themoviedb.data.models.search.SearchResponse
import uz.isytem.themoviedb.domain.repository.Repository
import uz.isytem.themoviedb.utills.Constants
import uz.isytem.themoviedb.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    private var page = 1

    private val successLiveDataSearch: MutableLiveData<SearchResponse?> =
        MutableLiveData<SearchResponse?>()
    val successResponseSearch: LiveData<SearchResponse?>
        get() = successLiveDataSearch

    private val errorLiveDataSearch: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponseSearch: LiveData<String?>
        get() = errorLiveDataSearch

    fun searchMovie(query:String) {

        viewModelScope.launch {
            when (val result = repository.searchMovie(
                apiKey = Constants.API_KEY,
                language = "en",
                page = page,
                query = query
            )) {
                is ResultWrapper.Success -> {
                    successLiveDataSearch.postValue(result.data)
                    page++
                }

                is ResultWrapper.Error -> {
                    errorLiveDataSearch.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLiveDataSearch.postValue("No internet")
                }
            }
        }
    }
}