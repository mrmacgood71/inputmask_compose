package it.macgood.inputmask.passport

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PassportMaskTransform : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return passportMaskFilter(text)
    }
}


fun passportMaskFilter(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 16
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("             ")

    for (i in filteredText.indices) {
        out[when(i) {
            in 0..1 -> i
            in 2..3 -> i + 1
            in 4..9 -> i + 2
            else -> i + 3
        }] = filteredText[i]
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