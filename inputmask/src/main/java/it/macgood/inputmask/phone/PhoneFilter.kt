package it.macgood.inputmask.phone

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneMaskTransformation(
    private val country: String
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        when(country) {
            "ru" -> {
                return ruPhoneMaskFilterStars(text)
            }
            "en" -> {
                return usPhoneMaskFilterStars(text)
            }
            else -> {
                return ruPhoneMaskFilterStars(text)
            }
        }
    }
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

fun usPhoneMaskFilterStars(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 11
    val filteredText = text.text.take(maxPhoneLength)
    var out = "+1 (***) ***-**** "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "+1 (${filteredText[0]}**) ***-**** "}
            1 -> {out = "+1 (${filteredText[0]}${filteredText[1]}*) ***-**** "}
            2 -> {out = "+1 (${filteredText[0]}${filteredText[1]}${filteredText[2]}) ***-**** "}
            3 -> {out = "+1 (${filteredText[0]}${filteredText[1]}${filteredText[2]}) ${filteredText[3]}**-**** "}
            4 -> {out = "+1 (${filteredText[0]}${filteredText[1]}${filteredText[2]}) ${filteredText[3]}${filteredText[4]}*-**** "}
            5 -> {out = "+1 (${filteredText[0]}${filteredText[1]}${filteredText[2]}) ${filteredText[3]}${filteredText[4]}${filteredText[5]}-**** "}
            6 -> {out = "+1 (${filteredText[0]}${filteredText[1]}${filteredText[2]}) ${filteredText[3]}${filteredText[4]}${filteredText[5]}-${filteredText[6]}*** "}
            7 -> {out = "+1 (${filteredText[0]}${filteredText[1]}${filteredText[2]}) ${filteredText[3]}${filteredText[4]}${filteredText[5]}-${filteredText[6]}${filteredText[7]}** "}
            8 -> {out = "+1 (${filteredText[0]}${filteredText[1]}${filteredText[2]}) ${filteredText[3]}${filteredText[4]}${filteredText[5]}-${filteredText[6]}${filteredText[7]}${filteredText[8]}* "}
            9 -> {out = "+1 (${filteredText[0]}${filteredText[1]}${filteredText[2]}) ${filteredText[3]}${filteredText[4]}${filteredText[5]}-${filteredText[6]}${filteredText[7]}${filteredText[8]}${filteredText[9]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 8
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun gerPhoneMaskFilterStars(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "+49 69 1234 5678 "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "+49 ${filteredText[0]}9 1234 5678 "}
            1 -> {out = "+49 ${filteredText[0]}${filteredText[1]} 1234 5678 "}
            2 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}234 5678 "}
            3 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}34 5678 "}
            4 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} 5678 "}
            5 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}678 "}
            6 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}78 "}
            7 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}${filteredText[8]}8 "}
            8 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}${filteredText[8]}${filteredText[9]} "}
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 4..6 -> filteredText.length + 7
                in 7..8 -> filteredText.length + 8
                in 9..10 -> filteredText.length + 8
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}