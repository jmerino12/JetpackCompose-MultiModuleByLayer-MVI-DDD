package com.user.infrastructure.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.user.domain.model.User
import com.user.domain.respository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class UserDataStoreRepository(private val dataStore: DataStore<Preferences>) : UserRepository {

    companion object {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_BIRTHDAY = stringPreferencesKey("user_birthday")

    }

    override fun getUser(): Flow<User> {
        return dataStore.data.map {
            val date = stringToCalendar(it[USER_BIRTHDAY] ?: "")
            User(it[USER_NAME] ?: "", email = it[USER_EMAIL] ?: "", birthday = date)
        }
    }

    override suspend fun saveUser(user: User) {
        dataStore.edit {
            it[USER_NAME] = user.name
            it[USER_BIRTHDAY] = user.birthday.toString()
        }
    }

    private fun stringToCalendar(date: String): Calendar {
        val cal: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("", Locale.getDefault())
        cal.setTime(sdf.parse(date)!!)
        return cal
    }
}