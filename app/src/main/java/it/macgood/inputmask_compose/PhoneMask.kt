package it.macgood.inputmask_compose

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

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
        visualTransformation = PhoneMaskTransformation()
    )
}

@Composable
fun DateTextField() {
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
                text = stringResource(id = R.string.phone_number),
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = DateMaskTransformation()
    )
}

@Composable
fun BankCardTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    Box(
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .padding(start = 4.dp, top = 12.dp)
                .zIndex(2f),
            painter = painterResource(id = R.drawable.ic_test),
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = numberTemplate,
                onValueChange = { it ->
                    numberTemplate = it.filter { it.isDigit() }.take(16)
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.phone_number),
                    )
                },
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = BankMaskTransformation()
            )
        }
    }
}

class BankMaskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return bankCardMaskFilter(text)
    }
}

class PhoneMaskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return ruPhoneMaskFilterStars(text)
    }
}


class DateMaskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dmyMaskFilterUnderline(text)
    }
}

fun bankCardMaskFilter(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 19
    val filteredText = text.text.take(maxPhoneLength)
    var out = "                    "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}                   "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}                  "}
            2 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}                 "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]}                "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}              "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}             "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}            "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]}           "}
            8 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}         "}
            9 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}        "}
            10 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}       "}
            11 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]}      "}
            12 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}    "}
            13 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}   "}
            14 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}${filteredText[14]}  "}
            15 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}${filteredText[14]}${filteredText[15]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..7 -> filteredText.length + 1
                in 8..11 -> filteredText.length + 2
                in 12..16 -> filteredText.length + 3
                else -> filteredText.length
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
    var out = "____ ____ ____ ____ "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}___ ____ ____ ____ "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}__ ____ ____ ____ "}
            2 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}_ ____ ____ ____ "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ____ ____ ____ "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}___ ____ ____ "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}__ ____ ____ "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}_ ____ ____ "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ____ ____ "}
            8 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}___ ____ "}
            9 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}__ ____ "}
            10 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}_ ____ "}
            11 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ____ "}
            12 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}___ "}
            13 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}__ "}
            14 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}${filteredText[14]}_ "}
            15 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}${filteredText[14]}${filteredText[15]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..7 -> filteredText.length + 1
                in 8..11 -> filteredText.length + 2
                in 12..16 -> filteredText.length + 3
                else -> filteredText.length
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
    var out = "**** **** **** **** "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}*** **** **** **** "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}** **** **** **** "}
            2 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}* **** **** **** "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} **** **** **** "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}*** **** **** "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}** **** **** "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}* **** **** "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} **** **** "}
            8 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}*** **** "}
            9 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}** **** "}
            10 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}* **** "}
            11 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} **** "}
            12 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}*** "}
            13 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}** "}
            14 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}${filteredText[14]}* "}
            15 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} ${filteredText[8]}${filteredText[9]}${filteredText[10]}${filteredText[11]} ${filteredText[12]}${filteredText[13]}${filteredText[14]}${filteredText[15]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..7 -> filteredText.length + 1
                in 8..11 -> filteredText.length + 2
                in 12..16 -> filteredText.length + 3
                else -> filteredText.length
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun dmyMaskFilterEmpty(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "  .  .     "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]} .  .     "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}.  .     "}
            2 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]} .     "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.     "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}    "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}   "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}${filteredText[6]}  "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 2..3 -> filteredText.length + 1
                in 4..8 -> filteredText.length + 2
                else -> filteredText.length
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun dmyMaskFilterEmptySlash(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "  /  /     "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]} /  /     "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}/  /     "}
            2 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]} /     "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/     "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/${filteredText[4]}    "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/${filteredText[4]}${filteredText[5]}   "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/${filteredText[4]}${filteredText[5]}${filteredText[6]}  "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 2..3 -> filteredText.length + 1
                in 4..8 -> filteredText.length + 2
                else -> filteredText.length
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}


fun dmyMaskFilterUnderlineSlash(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "__/__/____ "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}_/__/____ "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}/__.____ "}
            2 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}_/____ "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/____ "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/${filteredText[4]}___ "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/${filteredText[4]}${filteredText[5]}__ "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/${filteredText[4]}${filteredText[5]}${filteredText[6]}_ "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}/${filteredText[2]}${filteredText[3]}/${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 2..3 -> filteredText.length + 1
                in 4..8 -> filteredText.length + 2
                else -> filteredText.length
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun passportMaskFilter(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "          "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}          "}
            1 -> {
                out = "${filteredText[0]}${filteredText[1]}         "
            }
            2 -> {out = "${filteredText[0]}${filteredText[1]} ${filteredText[2]}          "}
            3 -> {out = "${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}       "}
            4 -> {out = "${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]} ${filteredText[4]}      "}
            5 -> {out = "${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}     "}
            6 -> {out = "${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}    "}
            7 -> {out = "${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]}   "}
            8 -> {out = "${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]}${filteredText[8]}  "}
            9 -> {out = "${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]} ${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]}${filteredText[8]}${filteredText[9]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 2..3 -> filteredText.length + 1
                in 4..9 -> filteredText.length + 2
                else -> filteredText.length
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
    var out = "__.__.____ "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}_.__.____ "}
            1 -> {
                val day = "${filteredText[0]}${filteredText[1]}"
                if (day.toInt() < 31) {
                    out = "${filteredText[0]}${filteredText[1]}.__.____ "
                }
            }
            2 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}_.____ "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.____ "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}___ "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}__ "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}${filteredText[6]}_ "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 2..3 -> filteredText.length + 1
                in 4..8 -> filteredText.length + 2
                else -> filteredText.length
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
    var out = "__.__.____ "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}_.__.____ "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}.__.____ "}
            2 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}_.____ "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.____ "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}___ "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}__ "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}${filteredText[6]}_ "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}${filteredText[6]}${filteredText[7]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 2..3 -> filteredText.length + 1
                in 4..8 -> filteredText.length + 2
                else -> filteredText.length
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

fun ruPhoneMaskFilterStars(text: AnnotatedString): TransformedText {
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

fun ruPhoneMaskFilterUnderlines(text: AnnotatedString): TransformedText {
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

fun ruPhoneMaskFilterEmpty(text: AnnotatedString): TransformedText {
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