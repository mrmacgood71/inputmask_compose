package it.macgood.inputmask_compose.ip

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import it.macgood.inputmask_compose.passport.passportMaskFilter

class IpAddressTransform : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return ipMaskFilter(text)
    }
}

fun ipMaskFilter(text: AnnotatedString): TransformedText {
    val maxPhoneLength = 20
    val filteredText = text.text.take(maxPhoneLength)
    var out = "___.___.___.___ "

    for (i in filteredText.indices) {
        when(i) {
            0 -> {out = "${filteredText[0]}__.___.___.___ "}
            1 -> {out = "${filteredText[0]}${filteredText[1]}_.___.___.___ " }
            2 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.___.___.___ "}
            3 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}__.___.___ "}
            4 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}${filteredText[4]}_.___.___ "}
            5 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}${filteredText[4]}${filteredText[5]}.___.___ "}
            6 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}${filteredText[4]}${filteredText[5]}.${filteredText[6]}__.___ "}
            7 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}${filteredText[4]}${filteredText[5]}.${filteredText[6]}${filteredText[7]}_.___ "}
            8 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}${filteredText[4]}${filteredText[5]}.${filteredText[6]}${filteredText[7]}${filteredText[8]}.___ "}
            9 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}${filteredText[4]}${filteredText[5]}.${filteredText[6]}${filteredText[7]}${filteredText[8]}.${filteredText[9]}__ "}
            10 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}${filteredText[4]}${filteredText[5]}.${filteredText[6]}${filteredText[7]}${filteredText[8]}.${filteredText[9]}${filteredText[10]}_ "}
            11 -> {out = "${filteredText[0]}${filteredText[1]}${filteredText[2]}.${filteredText[3]}${filteredText[4]}${filteredText[5]}.${filteredText[6]}${filteredText[7]}${filteredText[8]}.${filteredText[9]}${filteredText[10]}${filteredText[11]} "}
        }
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
