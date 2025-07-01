package com.gyhun.barrierfree

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gyhun.barrierfree.databinding.FragmentDetailBinding
import com.gyhun.barrierfree.extensions.loadUrl

class DetailFragment() : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private var pagerItem: PagerItem? = null

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setPagerItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout()
        setNavigationIconClickListener()
    }

    private fun setPagerItem() {
        pagerItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("item", PagerItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("item") as? PagerItem
        }

        if (pagerItem == null) {
            pagerItem = args.pagerItem
        }
    }

    private fun setNavigationIconClickListener() {
        binding.homeToolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setLayout() {
        pagerItem?.let { item ->
            binding.tvDetailBasicInfoTitle.text = item.title
            binding.tvDetailBasicInfoAddress.text = item.address
            binding.ivDetail.loadUrl(item.imageUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}