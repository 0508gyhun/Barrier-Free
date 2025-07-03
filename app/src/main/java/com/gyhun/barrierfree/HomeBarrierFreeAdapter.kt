package com.gyhun.barrierfree

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.gyhun.barrierfree.databinding.ItemRecyclerViewBinding

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
        holder.bind(items[position], onItemClick)
    }

    class HomeBarrierFreeViewHolder(
        private val binding: ItemRecyclerViewBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pagerItem: PagerItem, onItemClick: ((PagerItem) -> Unit)?) {
            binding.tvTitleRecommendation.text = pagerItem.title
            binding.tvAddressRecommendation.text = pagerItem.address
            binding.ivRecommendation.load(pagerItem.imageUrl)
            onItemClick?.let { clickListener ->
                binding.root.setOnClickListener {
                    clickListener(pagerItem)
                }
            }
        }
    }
}