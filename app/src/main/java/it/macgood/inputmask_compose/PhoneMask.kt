package it.macgood.inputmask_compose

import androidx.annotation.StringRes
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReservationTextField(
    modifier: Modifier = Modifier,
    value: String,
    @StringRes resId: Int,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = stringResource(id = resId)
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle.Default.copy(
            color = Color.Black,
            fontSize = 16.sp
        )
    )
}

@Composable
fun PhoneTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(10)
        },
        label = {
            Text(
                text = stringResource(id = R.string.phone_number),
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskTransformation()
    )
}


class MaskTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return rusPhoneMaskFilterStars(text)
    }
}

fun bankCardMaskFilter(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 19
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("                    ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..3 -> 5
            in 4..8 -> 10
            in 9..12 -> 14
            in 13..16 -> 16
            else -> 16
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun bankCardMaskFilterUnderlines(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 19
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("____ ____ ____ ____ ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..3 -> 5
            in 4..8 -> 10
            in 9..12 -> 14
            in 13..16 -> 16
            else -> 16
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun bankCardMaskFilterStars(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 19
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("**** **** **** **** ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..3 -> 5
            in 4..8 -> 10
            in 9..12 -> 14
            in 13..16 -> 16
            else -> 16
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}


fun dmyMaskFilterUnderline(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("__.__.____ ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..1 -> 3
            in 2..3 -> 5
            in 4..7 -> 8
            else -> 8
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun mdyMaskFilterUnderline(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("__.__.____ ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..1 -> 3
            in 2..3 -> 5
            in 4..7 -> 8
            else -> 8
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}


fun ymdMaskFilterUnderline(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("____.__.__ ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..3 -> 5
            in 4..5 -> 7
            in 6..7 -> 8
            else -> 8
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun rusPhoneMaskFilterStars(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 10
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("+7 (***) ***-**-** ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..2 -> 4
            in 3..5 -> 6
            in 6..7 -> 7
            in 8..9 -> 8
            else -> 5
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun rusPhoneMaskFilterUnderlines(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 10
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("+7 (___) ___-__-__ ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..2 -> 4
            in 3..5 -> 6
            in 6..7 -> 7
            in 8..9 -> 8
            else -> 5
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun rusPhoneMaskFilterEmpty(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 10
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("+7 (   )    -  -   ")

    for (i in filteredText.indices) {
        out[i + when (i) {
            in 0..2 -> 4
            in 3..5 -> 6
            in 6..7 -> 7
            in 8..9 -> 8
            else -> 5
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 9
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}