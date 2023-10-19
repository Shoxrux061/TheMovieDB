package uz.isytem.themoviedb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isytem.themoviedb.data.models.now_playing.NowPlayingResult
import uz.isytem.themoviedb.data.models.upcoming.UpcomingResult
import uz.isytem.themoviedb.databinding.ItemTopBinding
import uz.isytem.themoviedb.utills.Constants

class TopAdapter : RecyclerView.Adapter<TopAdapter.ViewHolder>() {

    lateinit var onClickItem : (Int,String) -> Unit

    private val data = ArrayList<NowPlayingResult>()

    fun setData(data:List<NowPlayingResult>){
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemTopBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: NowPlayingResult){
            binding.name.text = data.title
            binding.title.text = data.overview
            binding.image.load(Constants.IMAGE_URL.plus(data.backdropPath))
            binding.poster.load(Constants.IMAGE_URL.plus(data.posterPath))
            binding.root.setOnClickListener {
                onClickItem.invoke(data.id,data.voteAverage.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTopBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}