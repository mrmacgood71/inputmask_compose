package it.macgood.inputmask_compose

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import it.macgood.inputmask_compose.bank.BankTextFieldStyle


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