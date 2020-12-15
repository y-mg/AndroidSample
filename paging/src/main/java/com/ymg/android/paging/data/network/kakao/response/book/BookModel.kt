package com.ymg.android.paging.data.network.kakao.response.book

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BookModel(
    @SerializedName("documents")
    var documents: List<Document>,

    @SerializedName("meta")
    var meta: Meta
) {
    data class Document(
        // 좋아요 체크 여부
        @SerializedName("isCheckGood")
        var isCheckGood: Boolean = false,

        @SerializedName("title")
        var title: String,

        @SerializedName("contents")
        var contents: String,

        @SerializedName("url")
        var url: String,

        @SerializedName("isbn")
        var isbn: String,

        @SerializedName("datetime")
        var datetime: String,

        @SerializedName("authors")
        var authors: List<String>,

        @SerializedName("publisher")
        var publisher: String,

        @SerializedName("translators")
        var translators: List<String>,

        @SerializedName("price")
        var price: String,

        @SerializedName("sale_price")
        var sale_price: String,

        @SerializedName("thumbnail")
        var thumbnail: String
    ): Serializable

    data class Meta(
        @SerializedName("total_count")
        var totalCount: Int,

        @SerializedName("pageable_count")
        var pageableCount: Int,

        @SerializedName("is_end")
        var isEnd: Boolean
    )
}