package uz.isytem.themoviedb.data.models.upcoming


import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("dates")
    val dates: UpcomingDates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<UpcomingResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)