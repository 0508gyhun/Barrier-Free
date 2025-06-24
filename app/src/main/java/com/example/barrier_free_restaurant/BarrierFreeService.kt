package com.example.barrier_free_restaurant

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BarrierFreeService {
    @GET("areaBasedList2?&numOfRows=100000&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=C&_type=json&contentTypeId=12")
    fun getAllTouristAttraction(
        @Query("serviceKey") serviceKey: String,
    ): Call<TouristAttractionEntity>
}