package com.gyhun.barrierfree

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.gyhun.barrierfree.databinding.ItemHomeViewPagerBinding

class ImageSliderAdapter(
    private val items: List<PagerItem>,
    private val onItemClick: ((PagerItem) -> Unit)?
) : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeViewPagerBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ImageViewHolder(private val binding: ItemHomeViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pagerItem: PagerItem, onItemClick: ((PagerItem) -> Unit)?) {
            binding.tvBarrierFreeTitle.text = pagerItem.title
            binding.tvBarrierFreeAddress.text = pagerItem.address
            binding.ivHomeRecommendation.load(pagerItem.imageUrl)
            onItemClick?.let { clickListener ->
                binding.root.setOnClickListener {
                    clickListener(pagerItem)
                }
            }
        }
    }
}