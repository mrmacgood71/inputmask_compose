package it.macgood.inputmask.bank

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import it.macgood.inputmask.R
import it.macgood.inputmask.exceptions.NotBankCardNumberException

class BankMaskTransformation(
    private var onIconified: (Int) -> Unit,
    private var onSuccess: (Any) -> Unit,
    private var onError: (Any) -> Unit,
    private var type: BankTextFieldStyle
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        when(type) {
            BankTextFieldStyle.NOTHING -> {
                return bankCardMaskFilter(text, onIconified, onSuccess, onError)
            }
            BankTextFieldStyle.STARS -> {
                return bankCardMaskFilterStars(text, onIconified, onSuccess, onError)
            }
            BankTextFieldStyle.UNDERLINES -> {
                return bankCardMaskFilterUnderlines(text, onIconified, onSuccess, onError)
            }
        }

    }
}


fun bankCardMaskFilter(
    text: AnnotatedString,
    onIconified: (Int) -> Unit,
    onSuccess: (Any) -> Unit,
    onError: (Any) -> Unit
): TransformedText {
    val maxPhoneLength = 19
    val filteredText = text.text.take(maxPhoneLength)
    val out = StringBuilder()
    for (i in filteredText.indices) {
        out.append(filteredText[i])
        when(filteredText[0]) {
            '2' -> {
                if (filteredText.length > 1 && filteredText[1] == '2') onIconified(R.drawable.ic_mir)
            }
            '3' -> { onIconified(R.drawable.ic_mir) }
            '4' -> { onIconified(R.drawable.ic_visa_icon) }
            '5' -> { onIconified(R.drawable.ic_mastercard) }
            '6' -> { onIconified(R.drawable.ic_unionpay) }
            else -> {
                onIconified(R.drawable.ic_attention)
                onError(NotBankCardNumberException())
            }
        }
        if (i % 4 == 3) {
            out.append(" ")
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

fun bankCardMaskFilterUnderlines(
    text: AnnotatedString,
    onIconified: (Int) -> Unit,
    onSuccess: (Any) -> Unit,
    onError: (Any) -> Unit
): TransformedText {
    val maxPhoneLength = 19
    val filteredText = text.text.take(maxPhoneLength)

    var out = StringBuilder("____ ____ ____ ____ ")

    for (i in filteredText.indices) {

        when(filteredText[0]) {
            '2' -> {
                if (filteredText.length > 1 && filteredText[1] == '2') onIconified(R.drawable.ic_mir)
            }
            '3' -> { onIconified(R.drawable.ic_mir) }
            '4' -> { onIconified(R.drawable.ic_visa_icon) }
            '5' -> { onIconified(R.drawable.ic_mastercard) }
            '6' -> { onIconified(R.drawable.ic_unionpay) }
            else -> {
                onIconified(R.drawable.ic_attention)
                onError(NotBankCardNumberException())
            }
        }

        out[when(i) {
            in 0..3 -> { i }
            in 4..7-> { i + 1 }
            in 8..11 -> { i + 2 }
            else -> { i + 3 }

        }] = filteredText[i]
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            Log.d("TAG", "originalToTransformed: ${filteredText.length}")
            return when (filteredText.length) {
                in 5..7 -> filteredText.length + 1
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

fun bankCardMaskFilterStars(
    text: AnnotatedString,
    onIconified: (Int) -> Unit,
    onSuccess: (Any) -> Unit,
    onError: (Any) -> Unit
): TransformedText {
    val maxPhoneLength = 19
    val filteredText = text.text.take(maxPhoneLength)
    var out = StringBuilder("**** **** **** **** ")

    for (i in filteredText.indices) {
        when(filteredText[0]) {
            '2' -> {
                if (filteredText.length > 1 && filteredText[1] == '2') onIconified(R.drawable.ic_mir)
            }
            '3' -> { onIconified(R.drawable.ic_mir) }
            '4' -> { onIconified(R.drawable.ic_visa_icon) }
            '5' -> { onIconified(R.drawable.ic_mastercard) }
            '6' -> { onIconified(R.drawable.ic_unionpay) }
            else -> {
                onIconified(R.drawable.ic_attention)
                onError(NotBankCardNumberException())
            }
        }

        out[when(i) {
            in 0..3 -> { i }
            in 4..7-> { i + 1 }
            in 8..11 -> { i + 2 }
            else -> { i + 3 }

        }] = filteredText[i]
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