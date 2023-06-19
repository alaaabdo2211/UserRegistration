package com.code.myapplication.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NewYorkTimesMostViewResponse(
    val status: String,
    val copyright: String,
    @SerialName("num_results") val numResults: Int,
    val results: List<Result>
) {
    @Serializable
    data class Result(
        val uri: String,
        val url: String,
        val id: Long,
        @SerialName("asset_id") val assetId: Long,
        val source: String,
        @SerialName("published_date") val publishedDate: String,
        val updated: String,
        val section: String,
        val subsection: String,
        val nytdsection: String,
        @SerialName("adx_keywords") val adxKeywords: String,
        val byline: String,
        val type: String,
        val title: String,
        val `abstract`: String,
        @SerialName("des_facet") val desFacet: List<String>,
        @SerialName("org_facet") val orgFacet: List<String>,
        @SerialName("per_facet") val perFacet: List<String>,
        @SerialName("geo_facet") val geoFacet: List<String>,
        val media: List<Media>,
        @SerialName("eta_id") val etaId: Int
    ) {
        @Serializable
        data class Media(
            val type: String,
            val subtype: String,
            val caption: String,
            val copyright: String,
            @SerialName("approved_for_syndication") val approvedForSyndication: Int,
            @SerialName("media-metadata") val mediaMetadata: List<MediaMetadata>
        ) {
            @Serializable
            data class MediaMetadata(
                val url: String, val format: String, val height: Int, val width: Int
            )
        }
    }
}

fun NewYorkTimesMostViewResponse.toMostViewData(): List<MostViewData> = results.map {
    MostViewData(
        id = it.id,
        title = it.title,
        url = it.url,
        imageUrl = it.media.lastOrNull()?.mediaMetadata?.lastOrNull()?.url,
        publishDate = it.publishedDate
    )
}