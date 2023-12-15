package it.macgood.inputmask.date

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import it.macgood.inputmask_compose.R

@Composable
fun DateDmyUnderlineTextField(
    modifier: Modifier = Modifier,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit,
    label: @Composable (() -> Unit)? = {
        Text(
            text = stringResource(id = R.string.date),
        )
    },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = DateMaskTransformation(DmyTextFieldStyle.DMY_UNDERLINE),
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
            numberTemplate = it.filter { it.isDigit() }.take(8)
            if (it.length == 8) {
                onSuccess(numberTemplate)
            }
        },
        label = label,
        maxLines = 1,
        shape = shape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
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

@Composable
fun DateDmyEmptyTextField(
    modifier: Modifier = Modifier,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit,
    label: @Composable (() -> Unit)? = {
        Text(
            text = stringResource(id = R.string.date),
        )
    },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = DateMaskTransformation(DmyTextFieldStyle.DMY_EMPTY),
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
            numberTemplate = it.filter { it.isDigit() }.take(8)
            if (it.length == 8) {
                onSuccess(numberTemplate)
            }
        },
        label = label,
        maxLines = 1,
        shape = shape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
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

@Composable
fun DateDmyLetterTextField(
    modifier: Modifier = Modifier,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit,
    label: @Composable (() -> Unit)? = {
        Text(
            text = stringResource(id = R.string.date),
        )
    },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = DateMaskTransformation(DmyTextFieldStyle.DMY_LETTER),
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
            numberTemplate = it.filter { it.isDigit() }.take(8)
            if (it.length == 8) {
                onSuccess(numberTemplate)
            }
        },
        label = label,
        maxLines = 1,
        shape = shape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
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

@Composable
fun DateDmySlashTextField(
    modifier: Modifier = Modifier,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit,
    label: @Composable (() -> Unit)? = {
        Text(
            text = stringResource(id = R.string.date),
        )
    },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = DateMaskTransformation(DmyTextFieldStyle.DMY_SLASH),
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
            numberTemplate = it.filter { it.isDigit() }.take(8)
            if (it.length == 8) {
                onSuccess(numberTemplate)
            }
        },
        label = label,
        maxLines = 1,
        shape = shape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
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
