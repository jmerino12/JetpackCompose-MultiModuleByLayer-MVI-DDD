package com.user.infrastructure.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.user.domain.model.User
import com.user.infrastructure.repositories.contracts.UserLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Calendar


class UserDataStoreRepository(private val dataStore: DataStore<Preferences>) : UserLocalRepository {

    companion object {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_BIRTHDAY = stringPreferencesKey("user_birthday")
        val USER_IS_FIRST_TIME = booleanPreferencesKey("user_save_firstime")
    }

    override fun isFirstTime(): Flow<Boolean> {
        return dataStore.data.map {
            it[USER_IS_FIRST_TIME] ?: false
        }
    }

    override fun getUser(): Flow<User> {
        return dataStore.data.map {
            val date = stringToCalendar(it[USER_BIRTHDAY] ?: "0")
            User(it[USER_NAME] ?: "", email = it[USER_EMAIL] ?: "", birthday = date)
        }
    }

    override suspend fun saveUser(user: User) {
        dataStore.edit {
            it[USER_NAME] = user.name
            it[USER_BIRTHDAY] = user.birthday.timeInMillis.toString()

            val isFirstTime = isFirstTime().first()
            if (!isFirstTime) {
                it[USER_IS_FIRST_TIME] = true
            }
        }
    }


    private fun stringToCalendar(date: String): Calendar {
        val cal: Calendar = Calendar.getInstance()
        cal.timeInMillis = date.toLong()
        return cal
    }
}