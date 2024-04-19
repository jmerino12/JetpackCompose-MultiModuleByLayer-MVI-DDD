package com.user.domain.model

import java.util.Calendar


data class User(
    val name: String,
    val email: String,
    val birthday: Calendar,
) {
    fun getAge(): Int {
        val currentDate = Calendar.getInstance()

        var age = currentDate[Calendar.YEAR] - birthday[Calendar.YEAR]
        if (currentDate[Calendar.DAY_OF_YEAR] < birthday[Calendar.DAY_OF_YEAR]) {
            age--
        }
        return age
    }
}
