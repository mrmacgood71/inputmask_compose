package it.macgood.inputmask_compose.date

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import it.macgood.inputmask_compose.R

@Composable
fun DateDmyUnderlineTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(8)
        },
        label = {
            Text(
                text = stringResource(id = R.string.date),
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = DateMaskTransformation(DmyTextFieldStyle.DMY_UNDERLINE)
    )
}

@Composable
fun DateDmyEmptyTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(8)
        },
        label = {
            Text(
                text = stringResource(id = R.string.date),
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = DateMaskTransformation(DmyTextFieldStyle.DMY_EMPTY)
    )
}

@Composable
fun DateDmyLetterTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(8)
        },
        label = {
            Text(
                text = stringResource(id = R.string.date),
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = DateMaskTransformation(DmyTextFieldStyle.DMY_LETTER)
    )
}

@Composable
fun DateDmySlashTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(8)
        },
        label = {
            Text(
                text = stringResource(id = R.string.date),
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = DateMaskTransformation(DmyTextFieldStyle.DMY_SLASH)
    )
}
