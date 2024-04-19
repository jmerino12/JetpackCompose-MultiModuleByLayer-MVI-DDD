package com.user.ui.screens


import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.fragment.app.FragmentManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.user.ui.screens.questions.AgeQuestion
import com.user.ui.screens.questions.EmailQuestion
import com.user.ui.screens.questions.NameQuestion
import java.util.Calendar

@Composable
fun QuestionRoute(
    onSurveyComplete: () -> Unit,
    onNavUp: () -> Unit,
    viewModel: UserFormViewModel = hiltViewModel<UserFormViewModel>()
) {
    val surveyScreenData = viewModel.userScreenData ?: return
    var showDatePicker by remember {
        mutableStateOf(false)
    }

    BackHandler {
        if (!viewModel.onBackPressed()) {
            onNavUp()
        }
    }

    MovieQuestionsScreen(
        userScreenData = surveyScreenData,
        isNextEnabled = viewModel.isNextEnabled,
        onClosePressed = {
            onNavUp()
        },
        onPreviousPressed = { viewModel.onPreviousPressed() },
        onNextPressed = { viewModel.onNextPressed() },
        onDonePressed = { viewModel.onDonePressed(onSurveyComplete) }
    ) { paddingValues ->

        val modifier =
            Modifier
                .padding(paddingValues)


        AnimatedContent(
            targetState = surveyScreenData,
            transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> = tween(500)

                val direction = getTransitionDirection(
                    initialIndex = initialState.questionIndex,
                    targetIndex = targetState.questionIndex,
                )

                slideIntoContainer(
                    towards = direction,
                    animationSpec = animationSpec,
                ) togetherWith slideOutOfContainer(
                    towards = direction,
                    animationSpec = animationSpec
                )
            },
            label = "surveyScreenDataAnimation"
        ) { targetState ->

            when (targetState.userQuestion) {
                UserQuestion.NAME -> {
                    NameQuestion(
                        valueText = viewModel.nameResponse,
                        onValueChange = viewModel::onNameResponse,
                        modifier = modifier,
                    )
                }

                UserQuestion.EMAIL -> EmailQuestion(
                    valueText = viewModel.emailResponse,
                    onValueChange = viewModel::onEmailResponse,
                    modifier = modifier,
                )

                UserQuestion.BIRTHDAY -> {
                    AgeQuestion(
                        dateInMillis = viewModel.birthdayResponse,
                        onClick = {
                            showDatePicker = true
                        },
                        modifier = modifier,
                    )

                    if (showDatePicker) {
                        ShowDatePicker(
                            date = viewModel.birthdayResponse,
                            onDateSelected = viewModel::onBirthdayResponse,
                            onDismiss = { showDatePicker = false }
                        )
                    }


                }


            }
        }
    }
}

private fun getTransitionDirection(
    initialIndex: Int,
    targetIndex: Int
): AnimatedContentTransitionScope.SlideDirection {
    return if (targetIndex > initialIndex) {
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        AnimatedContentTransitionScope.SlideDirection.Right
    }
}

private fun showTakeawayDatePicker(
    date: Long?,
    supportFragmentManager: FragmentManager,
    onDateSelected: (date: Long) -> Unit,
) {

    val picker = MaterialDatePicker.Builder.datePicker()
        .setSelection(date)
        .build()
    picker.show(supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        picker.selection?.let {
            onDateSelected(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowDatePicker(
    date: Long?,
    onDateSelected: (date: Long) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberDatePickerState(
        initialSelectedDateMillis = date
    )
    val currentDate =
        remember { Calendar.getInstance().apply { set(Calendar.HOUR_OF_DAY, 0) }.timeInMillis }
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                state.selectedDateMillis?.let {
                    println(it)
                    onDateSelected(it)
                }
                onDismiss()
            }) {
                Text(text = "Confirmar")
            }
        }
    ) {
        DatePicker(
            state = state,
            dateValidator = { selectedDate ->

                selectedDate <= currentDate
            }
        )
    }
}