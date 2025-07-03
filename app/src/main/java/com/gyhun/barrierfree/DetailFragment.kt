package com.gyhun.barrierfree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil3.load
import com.gyhun.barrierfree.databinding.FragmentDetailBinding

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
        return homeActivity?.isTablet() ?: false
    }

    private fun setLayout() {
        args.let { item ->
            binding.tvDetailBasicInfoTitle.text = args.pagerItem.title
            binding.tvDetailBasicInfoAddress.text = args.pagerItem.address
            binding.ivDetail.load(args.pagerItem.imageUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}