package uz.isytem.themoviedb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isytem.themoviedb.data.models.detail.similar.SimilarResult
import uz.isytem.themoviedb.data.models.now_playing.NowPlayingResult
import uz.isytem.themoviedb.data.models.popular.PopularResult
import uz.isytem.themoviedb.data.models.upcoming.UpcomingResult
import uz.isytem.themoviedb.databinding.ItemBottomBinding
import uz.isytem.themoviedb.utills.Constants

class SimilarAdapter : RecyclerView.Adapter<SimilarAdapter.ViewHolder>() {

    lateinit var onClickItem : (Int,String) -> Unit

    private val data = ArrayList<SimilarResult>()

    fun setData(data:List<SimilarResult>){
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBottomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: SimilarResult){
            binding.name.text = data.title
            binding.name.isSelected = true
            binding.stars.text = data.voteAverage.toString()
            binding.image.load(Constants.IMAGE_URL.plus(data.posterPath))
            binding.root.setOnClickListener {

                onClickItem.invoke(data.id,data.voteAverage.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBottomBinding.inflate(
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