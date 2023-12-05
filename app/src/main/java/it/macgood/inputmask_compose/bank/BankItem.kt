package it.macgood.inputmask_compose.bank

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import it.macgood.inputmask_compose.R

enum class BankTextFieldStyle {
    NOTHING,
    UNDERLINES,
    STARS
}

@Composable
fun BankCardTextField(
    modifier: Modifier = Modifier,
    onSuccess: (String) -> Unit,
    onError: (Any) -> Unit,

    label: @Composable (() -> Unit)? = {
        Text(
            text = stringResource(id = R.string.bank_card),
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {
    var numberTemplate by remember { mutableStateOf("") }
    var resId by remember { mutableIntStateOf(0) }
    Box(
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            TextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = numberTemplate,
                onValueChange = { it ->
                    numberTemplate = it.filter { it.isDigit() }.take(16)
                    if (numberTemplate.length == 16) {
                        onSuccess(numberTemplate)
                    }
                },
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(start = 4.dp, top = 12.dp)
                            .zIndex(2f),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = if (resId == 0) R.drawable.ic_question else resId),
                        contentDescription = null
                    )
                },
                label = label,
                shape = if (shape == TextFieldDefaults.shape) RoundedCornerShape(8.dp) else shape,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = BankMaskTransformation(
                    onIconified = { resId = it },
                    onSuccess = {},
                    onError = {},
                    type = BankTextFieldStyle.NOTHING
                ),

                trailingIcon = trailingIcon,
                prefix = prefix,
                suffix = suffix,
                supportingText = supportingText,
                isError = isError,
                maxLines = maxLines,
                keyboardActions = keyboardActions,
                minLines = minLines,
                interactionSource = interactionSource,
                colors = colors,
                enabled = enabled,
                readOnly = readOnly,
                placeholder = placeholder,
                textStyle = textStyle
            )
        }
    }
}

@Composable
fun BankCardUnderlinesTextField(
    modifier: Modifier = Modifier,
    onSuccess: (Any) -> Unit,
    onError: (Any) -> Unit,
    onValueChange: ((String) -> Unit)? = null,

    label: @Composable (() -> Unit)? = {
        Text(
            text = stringResource(id = R.string.bank_card),
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {
    var numberTemplate by remember { mutableStateOf("") }
    var resId by remember { mutableIntStateOf(0) }
    Box(
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            modifier = modifier
                .zIndex(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            TextField(
                modifier = modifier,
                value = numberTemplate,
                onValueChange = { it ->
                    onValueChange?.invoke(it)
                    numberTemplate = it.filter { it.isDigit() }.take(16)
                    if (numberTemplate.length == 16) {
                        onSuccess(numberTemplate)
                    }
                },
                leadingIcon = {
                    if (leadingIcon == null) {
                        Image(
                            modifier = Modifier
                                .size(36.dp)
                                .padding(start = 4.dp, top = 12.dp)
                                .zIndex(2f),
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = if (resId == 0) R.drawable.ic_question else resId),
                            contentDescription = null
                        )
                    } else {
                        leadingIcon()
                    }
                },
                shape = if (shape == TextFieldDefaults.shape) RoundedCornerShape(8.dp) else shape,
                keyboardOptions = if (keyboardOptions == KeyboardOptions.Default) KeyboardOptions(keyboardType = KeyboardType.Number) else keyboardOptions,
                visualTransformation = if (visualTransformation == VisualTransformation.None) BankMaskTransformation(
                    onIconified = { resId = it },
                    onSuccess = { onSuccess(it) },
                    onError = { onError(it) },
                    type = BankTextFieldStyle.UNDERLINES
                ) else visualTransformation,

                trailingIcon = trailingIcon,
                prefix = prefix,
                suffix = suffix,
                supportingText = supportingText,
                isError = isError,
                label = label,
                maxLines = maxLines,
                keyboardActions = keyboardActions,
                minLines = minLines,
                interactionSource = interactionSource,
                colors = colors,
                enabled = enabled,
                readOnly = readOnly,
                placeholder = placeholder,
                textStyle = textStyle
            )
        }
    }
}

@Composable
fun BankCardStarsTextField(
    modifier: Modifier = Modifier,
    onSuccess: (String) -> Unit,
    onError: (Any) -> Unit,
) {
    var numberTemplate by remember { mutableStateOf("") }
    var resId by remember { mutableIntStateOf(0) }
    Box(
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            TextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = numberTemplate,
                onValueChange = { it ->
                    numberTemplate = it.filter { it.isDigit() }.take(16)
                    if (numberTemplate.length == 16) {
                        onSuccess(numberTemplate)
                    }
                },
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(start = 4.dp, top = 12.dp)
                            .zIndex(2f),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = if (resId == 0) R.drawable.ic_question else resId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.bank_card),
                    )
                },
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = BankMaskTransformation(
                    onIconified = { resId = it },
                    onSuccess = {},
                    onError = {},
                    type = BankTextFieldStyle.STARS
                )
            )
        }
    }
}