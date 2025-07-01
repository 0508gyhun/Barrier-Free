package com.gyhun.barrierfree

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gyhun.barrierfree.databinding.ItemRecyclerViewBinding
import com.gyhun.barrierfree.extensions.loadUrl

class HomeBarrierFreeAdapter(
    private val items: List<PagerItem>,
    private val onItemClick: ((PagerItem) -> Unit)?
) :
    RecyclerView.Adapter<HomeBarrierFreeAdapter.HomeBarrierFreeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBarrierFreeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewBinding.inflate(inflater, parent, false)
        return HomeBarrierFreeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeBarrierFreeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class HomeBarrierFreeViewHolder(private val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pagerItem: PagerItem) {
            binding.tvTitleRecommendation.text = pagerItem.title
            binding.tvAddressRecommendation.text = pagerItem.address
            binding.ivRecommendation.loadUrl(pagerItem.imageUrl)
            onItemClick?.let { clickListener ->
                binding.root.setOnClickListener {
                    clickListener(pagerItem)
                }
            }
        }
    }
}