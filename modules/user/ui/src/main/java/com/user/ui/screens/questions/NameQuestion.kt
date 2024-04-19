package com.user.ui.screens.questions

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.theme.MovieAppThemeShapes
import com.movie.theme.MovieTheme

@Composable
fun NameQuestion(
    valueText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    QuestionWrapper(titleResourceId = "Nombre completo", modifier = modifier) {

        TextField(
            singleLine = true,
            value = valueText, onValueChange = onValueChange,
            placeholder = {
                Text(text = "Carlos Rodriguez")
            },
            shape = MovieAppThemeShapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .height(54.dp)
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NameQuestionPreview() {
    MovieTheme {
        NameQuestion(
            valueText = "Nombre completo",
            onValueChange = {}
        )
    }
}