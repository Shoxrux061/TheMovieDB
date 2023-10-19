package uz.isytem.themoviedb.data.models.detail.recomendate


import com.google.gson.annotations.SerializedName

data class RecomandateResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<RecomendateResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)