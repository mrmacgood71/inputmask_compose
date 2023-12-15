package it.macgood.inputmask.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import it.macgood.inputmask.R
import java.util.Locale

@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    systemLanguage: String = Locale.getDefault().language,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit,
    label: @Composable (() -> Unit)? = {
        Text(
            text = stringResource(id = R.string.phone_number)
        )
    },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = {
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
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = PhoneMaskTransformation(systemLanguage),
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {
    var numberTemplate by remember { mutableStateOf("") }

    TextField(
        modifier = modifier,
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(10)
        },
        label = label,
        leadingIcon = leadingIcon,
        maxLines = 1,
        shape = shape,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        keyboardActions = keyboardActions,
        minLines = minLines,
        interactionSource = interactionSource,
        colors = colors
    )
}