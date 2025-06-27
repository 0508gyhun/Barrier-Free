package com.gyhun.barrierfree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
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
        binding.vpTodayBarrierFreeRecommend.adapter = ImageSliderAdapter(data)
        binding.rvTouristAttractionRecommendation.adapter = TouristAttractionAdapter(data)
        binding.rvCulturalFacilityRecommendation.adapter = TouristAttractionAdapter(data)
        binding.rvAccommodationRecommendation.adapter = TouristAttractionAdapter(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
}