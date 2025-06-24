package com.example.barrier_free_restaurant

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.barrier_free_restaurant.databinding.FragmentMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.core.graphics.scale

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var naverMap: NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fm = childFragmentManager
        val naverMapFragment =
            fm.findFragmentById(R.id.map_fragment) as? com.naver.maps.map.MapFragment
                ?: com.naver.maps.map.MapFragment.newInstance().also {
                    fm.beginTransaction().replace(R.id.map_fragment, it).commit()
                }

        naverMapFragment.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(p0: NaverMap) {
        this.naverMap = p0
        loadTouristAttraction()
    }

    private fun loadTouristAttraction() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apis.data.go.kr/B551011/KorWithService2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BarrierFreeService::class.java)


        val result = service.getAllTouristAttraction(
            serviceKey = APIConstants.DECODED_KEY
        ).enqueue(object : Callback<TouristAttractionEntity> {
            override fun onResponse(
                call: Call<TouristAttractionEntity>,
                response: Response<TouristAttractionEntity>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Log.d("API_RESPONSE", "불러오기 성공: ${data.toString()}")
                        Toast.makeText(requireActivity(), "데이터 불러오기 성공!", Toast.LENGTH_SHORT).show()
                        makeMarker(data)
                    } else {
                        Log.e("API_RESPONSE", "응답은 성공했으나 데이터가 null입니다.")
                        Toast.makeText(requireActivity(), "데이터가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("API_RESPONSE", "응답 실패: ${response.errorBody()?.string()}")
                    Toast.makeText(requireActivity(), "서버 응답 오류", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TouristAttractionEntity>, t: Throwable) {
                Log.e("API_RESPONSE", "API 호출 실패", t)
                Toast.makeText(requireActivity(), "네트워크 오류 발생", Toast.LENGTH_SHORT).show()
            }
        }
        )
    }

    private fun makeMarker(data: TouristAttractionEntity) {
        data.response.body.items.item.forEach { it ->
            val marker = Marker()
            marker.position = LatLng(it.mapY.toDouble(), it.mapX.toDouble())
            marker.map = naverMap
        }
    }

}