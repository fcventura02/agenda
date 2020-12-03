package br.com.agenda.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "schedule_data" ,
   /* foreignKeys = arrayOf(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
        )
        )*/
)
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val task: String,
    val cost: Float,
    val date: String,
    val timer: String,
    val complet: Boolean,
    val user_id: Int
):Parcelable {
}