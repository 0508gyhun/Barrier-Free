package com.gyhun.barrierfree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gyhun.barrierfree.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = setDummyData()

        val homeActivity = requireActivity() as? HomeActivity
        val isTabletLayout = homeActivity?.binding?.extendNavigationRail != null

        if (isTabletLayout) {
            setAdapterTablet(data)
        } else {
            setAdapterPhone(data)
        }
    }

    private fun setAdapterPhone(data: List<PagerItem>) {
        binding.vpTodayBarrierFreeRecommend.adapter = ImageSliderAdapter(data) { pagerItem ->
            navigateToDetailFragmentPhone(pagerItem)
        }
        binding.rvTouristAttractionRecommendation.adapter =
            HomeBarrierFreeAdapter(data) { pagerItem ->
                navigateToDetailFragmentPhone(pagerItem)
            }
        binding.rvCulturalFacilityRecommendation.adapter =
            HomeBarrierFreeAdapter(data) { pagerItem ->
                navigateToDetailFragmentPhone(pagerItem)
            }
        binding.rvAccommodationRecommendation.adapter =
            HomeBarrierFreeAdapter(data) { pagerItem ->
                navigateToDetailFragmentPhone(pagerItem)
            }
        binding.rvFoodRecommendation.adapter = HomeBarrierFreeAdapter(data) { pagerItem ->
            navigateToDetailFragmentPhone(pagerItem)
        }
    }

    private fun setAdapterTablet(data: List<PagerItem>) {
        binding.vpTodayBarrierFreeRecommend.adapter = ImageSliderAdapter(data) { pagerItem ->
            navigateToDetailFragmentTablet(pagerItem)
        }
        binding.rvTouristAttractionRecommendation.adapter =
            HomeBarrierFreeAdapter(data) { pagerItem ->
                navigateToDetailFragmentTablet(pagerItem)
            }
        binding.rvCulturalFacilityRecommendation.adapter =
            HomeBarrierFreeAdapter(data) { pagerItem ->
                navigateToDetailFragmentTablet(pagerItem)
            }
        binding.rvAccommodationRecommendation.adapter =
            HomeBarrierFreeAdapter(data) { pagerItem ->
                navigateToDetailFragmentTablet(pagerItem)
            }
        binding.rvFoodRecommendation.adapter = HomeBarrierFreeAdapter(data) { pagerItem ->
            navigateToDetailFragmentTablet(pagerItem)
        }
    }

    private fun navigateToDetailFragmentPhone(item: PagerItem) {
        val action =
            HomeFragmentDirections.actionBottomNavigationHomeToDetailFragment(item)
        findNavController().navigate(action)
    }

    private fun navigateToDetailFragmentTablet(pagerItem: PagerItem) {
        val navController =
            requireActivity().supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment_detail)
                ?.findNavController()
        val action = DetailGraphDirections.actionGlobalDetailFragment(pagerItem)
        navController?.navigate(action)
    }

    private fun setDummyData(): List<PagerItem> {
        val data = listOf(
            PagerItem("https://ifh.cc/g/FcA6Sc.jpg", "과수원", "경기도 구리시 이천면 멍멍"),
            PagerItem("https://i.imgur.com/rhpC3OB.png", "경동시장", "서울시 노원구 이천면 멍멍"),
            PagerItem("https://ifh.cc/g/cyprKF.jpg", "피그마", "경상남도 이천면 멍멍"),
            PagerItem("https://i.imgur.com/rhpC3OB.png", "제천시장", "충북 제천 구리시 이천면 멍멍"),
        )
        return data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}