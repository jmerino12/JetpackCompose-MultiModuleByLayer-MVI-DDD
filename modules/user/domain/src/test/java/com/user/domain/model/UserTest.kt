package com.user.domain.model

import org.junit.Assert
import org.junit.Test
import java.util.Calendar


class UserTest {
    @Test
    fun `User 25 age`() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        val birthday = Calendar.getInstance()
        birthday.set(Calendar.YEAR, currentYear - 25)
        birthday.set(Calendar.MONTH, Calendar.JANUARY)
        birthday.set(Calendar.DAY_OF_MONTH, 4)
        val user =
            User("pepito", "@gmail.com", birthday)

        Assert.assertEquals(
            25, user.getAge()
        )
    }
}