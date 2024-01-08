package com.example.signin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.signin.domain.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)
    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun getUserByEmailAndPassword(email: String, password: String): Flow<UserEntity?>
}//TODO what is the usage for live dats and the  flow