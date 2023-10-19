package uz.isytem.themoviedb.data.models.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<SearchResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)