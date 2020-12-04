package br.com.agenda.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_data",
    indices = [Index(value=["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    @ColumnInfo()
    val email: String,
    val password: String
    ) {
}