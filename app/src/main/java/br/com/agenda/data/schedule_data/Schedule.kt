package br.com.agenda.data.schedule_data

import java.util.*

data class Schedule(
    val id: Int,
    val name: String,
    val task: String,
    val cost: Float,
    val date: Date,
    val timer: Timer,
    val complet: Boolean,
) {
}