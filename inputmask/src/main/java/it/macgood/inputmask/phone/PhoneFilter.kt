package it.macgood.inputmask.phone

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneMaskTransformation(
    private val country: String
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        when(country) {
            "am" -> {
                return amPhoneMaskFilterStars(text)
            }
            "kz" -> {
                return kzPhoneMaskFilterStars(text)
            }
            "ru" -> {
                return ruPhoneMaskFilterStars(text)
            }
            "ua" -> {
                return uaPhoneMaskFilterStars(text)
            }
            "be" -> {
                return belarusPhoneMaskFilterStars(text)
            }
            "ger" -> {
                return gerPhoneMaskFilterStars(text)
            }
            "en" -> {
                return usPhoneMaskFilterStars(text)
            }
            else -> {
                return belarusPhoneMaskFilterStars(text)
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
    var out = "+49 ** **** **** "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "+49 ${filteredText[0]}* **** **** "}
            1 -> {out = "+49 ${filteredText[0]}${filteredText[1]} **** **** "}
            2 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}*** **** "}
            3 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}** **** "}
            4 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}* **** "}
            5 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} **** "}
            6 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}*** "}
            7 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}** "}
            8 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}${filteredText[8]}* "}
            9 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}${filteredText[8]}${filteredText[9]} "}
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


fun belarusPhoneMaskFilterStars(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "+375 ** *** ** ** "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "+375 ${filteredText[0]}* *** ** ** "}
            1 -> {out = "+375 ${filteredText[0]}${filteredText[1]} *** ** ** "}
            2 -> {out = "+375 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}** ** ** "}
            3 -> {out = "+375 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}* ** ** "}
            4 -> {out = "+375 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]} ** ** "}
            5 -> {out = "+375 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]} ${filteredText[5]}* ** "}
            6 -> {out = "+375 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]} ${filteredText[5]}${filteredText[6]} ** "}
            7 -> {out = "+375 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]} ${filteredText[5]}${filteredText[6]} ${filteredText[7]}*  "}
            8 -> {out = "+375 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]} ${filteredText[5]}${filteredText[6]} ${filteredText[7]}${filteredText[8]} "}
        }
    }

    Log.d("TAG", "belarusPhoneMaskFilterStars: ${filteredText.length}")
    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 2..4 -> filteredText.length + 6
                in 5..6 -> filteredText.length + 7
                in 7..9 -> filteredText.length + 8
                else -> filteredText.length + 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

fun kzPhoneMaskFilterStars(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "+49 __ ____ ____ "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "+49 ${filteredText[0]}_ ____ ____ "}
            1 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ____ ____ "}
            2 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}___ ____ "}
            3 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}__ ____ "}
            4 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ____ "}
            5 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}___ "}
            6 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}__ "}
            7 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}${filteredText[8]}_ "}
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

fun uaPhoneMaskFilterStars(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "+49 __ ____ ____ "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "+49 ${filteredText[0]}_ ____ ____ "}
            1 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ____ ____ "}
            2 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}___ ____ "}
            3 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}__ ____ "}
            4 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ____ "}
            5 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}___ "}
            6 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}__ "}
            7 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}${filteredText[8]}_ "}
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

fun amPhoneMaskFilterStars(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "+49 __ ____ ____ "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "+49 ${filteredText[0]}_ ____ ____ "}
            1 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ____ ____ "}
            2 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}___ ____ "}
            3 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}__ ____ "}
            4 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ____ "}
            5 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}___ "}
            6 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}__ "}
            7 -> {out = "+49 ${filteredText[0]}${filteredText[1]} ${filteredText[2]}${filteredText[3]}${filteredText[4]}${filteredText[5]} ${filteredText[6]}${filteredText[7]}${filteredText[8]}_ "}
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