package com.ymg.android.mvvm.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable



data class SearchModel(
    @SerializedName("total_count")
    var totalCount: String,

    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,

    @SerializedName("items")
    var items: List<Items>
) {
    data class Items(
        // 좋아요 체크 여부
        @SerializedName("isCheckGood")
        var isCheckGood: Boolean = false,


        @SerializedName("login")
        var login: String,

        @SerializedName("id")
        var id: String,

        @SerializedName("node_id")
        var node_id: String,

        @SerializedName("avatar_url")
        var avatarUrl: String,

        @SerializedName("gravatar_id")
        var gravatarId: String,

        @SerializedName("url")
        var url: String,

        @SerializedName("html_url")
        var htmlUrl: String,

        @SerializedName("followers_url")
        var followersUrl: String,

        @SerializedName("subscriptions_url")
        var subscriptionsUrl: String,

        @SerializedName("organizations_url")
        var organizationsUrl: String,

        @SerializedName("repos_url")
        var reposUrl: String,

        @SerializedName("received_events_url")
        var receivedEventsUrl: String,

        @SerializedName("type")
        var type: String,

        @SerializedName("score")
        var score: String,
    ): Serializable
}