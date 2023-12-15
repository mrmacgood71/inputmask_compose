package it.macgood.inputmask.ip

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import it.macgood.inputmask.exceptions.NotIpNumberException
import it.macgood.inputmask.passport.passportMaskFilter
import java.lang.StringBuilder

class IpAddressTransform(
    private val onError: (Exception) -> Unit
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return ipMaskFilter(text, onError)
    }
}

fun ipMaskFilter(
    text: AnnotatedString,
    onError: (Exception) -> Unit
): TransformedText {
    val maxPhoneLength = 20
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder("___.___.___.___ ")

    for (i in filteredText.indices) {
        out[when(i) {
            in 0..2 -> {
                if (i == 2) {
                    val number = "${filteredText[0]}${filteredText[1]}${filteredText[2]}".toInt()
                    if (number > 255) {
                        onError(NotIpNumberException())
                    }
                }
                i
            }
            in 3..5 -> i + 1
            in 6..8 -> i + 2
            in 9..11 -> i + 3
            else -> i
        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (filteredText.length) {
                in 3..4 -> filteredText.length + 1
                in 5..10 -> filteredText.length + 2
                in 11..12 -> filteredText.length + 3
                else -> filteredText.length
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return filteredText.length
        }
    }

    return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
}
