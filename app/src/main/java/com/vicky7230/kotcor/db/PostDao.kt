package com.vicky7230.kotcor.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vicky7230.kotcor.api.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(posts: List<Post>)

    @Query("SELECT * FROM posts")
    fun loadAllPosts(): LiveData<List<Post>>
}