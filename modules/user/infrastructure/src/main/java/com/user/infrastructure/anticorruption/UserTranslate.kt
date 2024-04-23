package com.user.infrastructure.anticorruption

import com.google.firebase.auth.FirebaseUser
import com.user.domain.model.User
import java.util.Calendar

class UserTranslate {
    companion object {
        fun fromDtoToDomain(firebaseUser: FirebaseUser): User {
            return User(
                firebaseUser.displayName.toString(),
                firebaseUser.email.toString(),
                birthday = Calendar.getInstance()
            )
        }
    }
}