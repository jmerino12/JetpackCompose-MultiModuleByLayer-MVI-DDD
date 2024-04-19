package com.user.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserFormViewModel @Inject constructor(): ViewModel() {

    private var questionIndex = 0

    private val questionOrder: List<UserQuestion> = listOf(
        UserQuestion.NAME,
        UserQuestion.EMAIL,
        UserQuestion.BIRTHDAY,
    )


    private val _userScreenData = mutableStateOf(createSurveyScreenData())
    val userScreenData: UserScreenData?
        get() = _userScreenData.value


    //---- Data
    private val _nameResponse=  mutableStateOf(String())
    val nameResponse: String
        get() = _nameResponse.value


    private val _emailResponse = mutableStateOf(String())
    val emailResponse: String
        get() = _emailResponse.value

    private val _birthdayResponse = mutableStateOf<Long?>(null)
    val birthdayResponse: Long?
        get() = _birthdayResponse.value



    //Screen conditions
    private val _isNextEnabled = mutableStateOf(false)
    val isNextEnabled: Boolean
        get() = _isNextEnabled.value


    fun onBackPressed(): Boolean {
        if (questionIndex == 0) {
            return false
        }
        changeQuestion(questionIndex - 1)
        return true
    }

    fun onPreviousPressed() {
        if (questionIndex == 0) {
            throw IllegalStateException("onPreviousPressed when on question 0")
        }
        changeQuestion(questionIndex - 1)
    }

    fun onNextPressed() {
        changeQuestion(questionIndex + 1)
    }

    fun onDonePressed(onSurveyComplete: () -> Unit) {
        // Here is where you could validate that the requirements of the survey are complete
        onSurveyComplete()
    }

    private fun changeQuestion(newQuestionIndex: Int) {
        questionIndex = newQuestionIndex
        _isNextEnabled.value = getIsNextEnabled()
        _userScreenData.value = createSurveyScreenData()
    }

    private fun getIsNextEnabled(): Boolean {
        return when (questionOrder[questionIndex]) {
            UserQuestion.NAME -> _nameResponse.value.isNotEmpty()
            UserQuestion.EMAIL -> _emailResponse.value.isNotEmpty()
            UserQuestion.BIRTHDAY ->  _birthdayResponse.value != null
        }
    }

    private fun createSurveyScreenData(): UserScreenData {
        return UserScreenData(
            questionIndex = questionIndex,
            questionCount = questionOrder.size,
            shouldShowPreviousButton = questionIndex > 0,
            shouldShowDoneButton = questionIndex == questionOrder.size - 1,
            userQuestion = questionOrder[questionIndex],
        )
    }

    fun onNameResponse(name: String) {
        _nameResponse.value = name
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onEmailResponse(email: String) {
        _emailResponse.value = email
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onBirthdayResponse(timestamp: Long) {
        _birthdayResponse.value = timestamp
        _isNextEnabled.value = getIsNextEnabled()
    }
}


enum class UserQuestion {
    NAME,
    EMAIL,
    BIRTHDAY,
}

data class UserScreenData(
    val questionIndex: Int,
    val questionCount: Int,
    val shouldShowPreviousButton: Boolean,
    val shouldShowDoneButton: Boolean,
    val userQuestion: UserQuestion,
)