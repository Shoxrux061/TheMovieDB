package uz.isytem.themoviedb.data.models.now_playing


import com.google.gson.annotations.SerializedName

data class NowPlayingDates(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)