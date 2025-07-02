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

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    private fun setNavigationIconClickListener() {
        binding.homeToolBar.setNavigationOnClickListener {
            if (isTablet()) {
                val action = DetailFragmentDirections.actionDetailFragmentToEmptyFragment()
                findNavController().navigate(action)
            } else {
                findNavController().navigateUp()
            }
        }
    }

    private fun isTablet(): Boolean {
        val homeActivity = requireActivity() as? HomeActivity
        return homeActivity?.binding?.extendNavigationRail != null
    }

    private fun setLayout() {
        args.let { item ->
            binding.tvDetailBasicInfoTitle.text = args.pagerItem.title
            binding.tvDetailBasicInfoAddress.text = args.pagerItem.address
            binding.ivDetail.loadUrl(args.pagerItem.imageUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}