package it.macgood.inputmask_compose.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import it.macgood.inputmask_compose.R
import java.util.Locale

@Composable
fun PhoneTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    val systemLanguage = Locale.getDefault().language
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(10)
        },
        label = {
            Text(
                text = stringResource(id = R.string.phone_number_ru)
            )
        },
        leadingIcon = {
            Image(
                modifier = Modifier
                    .size(28.dp)
                    .padding(start = 4.dp, top = 4.dp)
                    .zIndex(2f),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = when(systemLanguage) {
                    "ru" -> {R.drawable.ic_russia}
                    "en" -> {R.drawable.ic_usa}
                    "ge" -> {R.drawable.ic_germany}
                    else -> {R.drawable.ic_belarus}
                }),
                contentDescription = null
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PhoneMaskTransformation(systemLanguage)
    )
}