package it.macgood.inputmask_compose.passport

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PassportMastTransform : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return passportMaskFilter(text)
    }
}


fun passportMaskFilter(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 10
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
                in 4..10 -> filteredText.length + 2
                else -> filteredText.length
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}