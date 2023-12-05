package it.macgood.inputmask_compose.date

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation


enum class DmyTextFieldStyle {
    DMY_UNDERLINE,
    DMY_EMPTY,
    DMY_LETTER,
    DMY_SLASH
}

class DateMaskTransformation(
    private val type: DmyTextFieldStyle
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return when(type) {
            DmyTextFieldStyle.DMY_UNDERLINE -> {
                dmyMaskFilterUnderline(text)
            }

            DmyTextFieldStyle.DMY_EMPTY -> {
                dmyMaskFilterEmpty(text)
            }

            DmyTextFieldStyle.DMY_LETTER -> {
                dmyMaskFilterLetters(text)
            }

            DmyTextFieldStyle.DMY_SLASH -> {
                dmyMaskFilterEmptySlash(text)
            }
        }
    }
}


fun dmyMaskFilterEmpty(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "  .  .     "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]} .  .     "}
            1 -> {
                out = "${filteredText[0]}${filteredText[1]}.  .     "
            }
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

fun dmyMaskFilterLetters(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 9
    val filteredText = text.text.take(maxPhoneLength)
    var out = "dd.mm.yyyy "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}d.mm.yyyy "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}.mm.yyyy "}
            2 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}m.yyyy "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.yyyy "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}yyy "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}yy "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}.${filteredText[2]}${filteredText[3]}.${filteredText[4]}${filteredText[5]}${filteredText[6]}y "}
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


