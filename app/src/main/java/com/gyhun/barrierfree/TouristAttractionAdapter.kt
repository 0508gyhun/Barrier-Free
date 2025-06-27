package com.gyhun.barrierfree

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gyhun.barrierfree.databinding.ItemRecyclerViewBinding

class TouristAttractionAdapter(private val items: List<PagerItem>) :
    RecyclerView.Adapter<TouristAttractionAdapter.TouristAttractionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristAttractionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewBinding.inflate(inflater,parent,false)
        return TouristAttractionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TouristAttractionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class TouristAttractionViewHolder(private val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pagerItem: PagerItem) {
            binding.tvTitleRecommendation.text = pagerItem.title
            binding.tvAddressRecommendation.text = pagerItem.address
            Glide
                .with(binding.ivRecommendation)
                .load(pagerItem.imageUrl)
                .into(binding.ivRecommendation)
        }
    }
}