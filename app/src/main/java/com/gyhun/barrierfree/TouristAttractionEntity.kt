package com.gyhun.barrierfree

import com.google.gson.annotations.SerializedName

data class TouristAttractionEntity(
    @SerializedName("response")
    val response: TourResponse
)

data class TourResponse(
    @SerializedName("header")
    val header: TourHeader,
    @SerializedName("body")
    val body: TourBody
)

data class TourHeader(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String
)

data class TourBody(
    @SerializedName("items")
    val items: TourItems,
)

data class TourItems(
    @SerializedName("item")
    val item: List<TourEntity>
)

data class TourEntity(
    @SerializedName("addr1")
    val address: String,
    @SerializedName("areacode")
    val areaCode: String,
    @SerializedName("sigungucode")
    val sigunguCode: String,
    @SerializedName("firstimage")
    val imageUrl1: String,
    @SerializedName("firstimage2")
    val imageUrl2: String,
    @SerializedName("contentid")
    val contentId: String,
    @SerializedName("contenttypeid")
    val contentTypeId: String,
    @SerializedName("mapx")
    val mapX: String,
    @SerializedName("mapy")
    val mapY: String,
    @SerializedName("modifiedtime")
    val modifiedTime: String,
    @SerializedName("tel")
    val tel: String,
    @SerializedName("title")
    val title: String
)
