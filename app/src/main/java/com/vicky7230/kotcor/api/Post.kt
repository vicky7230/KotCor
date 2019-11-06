package com.vicky7230.kotcor.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class Post(

    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    @Expose
    var userId: Int? = null,

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String? = null,

    @ColumnInfo(name = "body")
    @SerializedName("body")
    @Expose
    var body: String? = null
)
