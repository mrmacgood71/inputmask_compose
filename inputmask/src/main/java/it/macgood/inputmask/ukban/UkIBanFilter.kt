package it.macgood.inputmask.ukban

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import it.macgood.inputmask.exceptions.NotCountryCodeException
import it.macgood.inputmask.passport.passportMaskFilter

class UkIBanMaskTransformation(
    private val onError: (Exception) -> Unit
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return ukIBanMaskFilter(text, onError)
    }
}

fun ukIBanMaskFilter(
    text: AnnotatedString,
    onError: (Exception) -> Unit
): TransformedText {
    val maxPhoneLength = 34
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("__ __ ____ ______ ________")

    for (i in filteredText.indices) {
        out[when(i) {
            in 0..1 -> i
            in 2..3 -> {
                if (filteredText[0].isDigit().and(filteredText[1].isDigit())) {
                    onError(NotCountryCodeException())
                    break
                }
                i + 1
            }
            in 4..7 -> i + 2
            in 8..13 -> i + 3
            in 14..21 -> i + 4
            else -> i + 5
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 2..3 -> filteredText.length + 1
                in 4..7 -> filteredText.length + 2
                in 8..13 -> filteredText.length + 3
                in 14..22 -> filteredText.length + 4
                else -> filteredText.length
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}

