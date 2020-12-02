package br.com.agenda.data.schedule_data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.agenda.data.user_data.User
import java.util.*

@Entity(tableName = "schedule_data",
    foreignKeys = arrayOf(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
        ))
)
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val task: String,
    val cost: Float,
    val date: Date,
    val timer: Timer,
    val complet: Boolean,
    val user_id: Int
) {
}