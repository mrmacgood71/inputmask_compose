package it.macgood.inputmask_compose

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import java.util.Locale

@Composable
fun ReservationTextField(
    modifier: Modifier = Modifier,
    value: String,
    @StringRes resId: Int,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = stringResource(id = resId)
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle.Default.copy(
            color = Color.Black,
            fontSize = 16.sp
        )
    )
}

@Composable
fun PhoneTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    val systemLanguage = Locale.getDefault().language
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(10)
        },
        label = {
            Text(
                text = stringResource(id = R.string.phone_number),
            )
        },
        leadingIcon = {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 4.dp, top = 12.dp)
                    .zIndex(2f),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.ic_russia),
                contentDescription = null
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PhoneMaskTransformation(systemLanguage)
    )
}

@Composable
fun DateTextField() {
    var numberTemplate by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = numberTemplate,
        onValueChange = { it ->
            numberTemplate = it.filter { it.isDigit() }.take(8)
        },
        label = {
            Text(
                text = stringResource(id = R.string.date),
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = DateMaskTransformation()
    )
}

@Composable
fun BankCardTextField(
    onSuccess: (Any) -> Unit,
    onError: (Any) -> Unit,
) {
    var numberTemplate by remember { mutableStateOf("") }
    var resId by remember { mutableIntStateOf(0) }
    Box(
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = numberTemplate,
                onValueChange = { it ->
                    numberTemplate = it.filter { it.isDigit() }.take(16)
                },
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(start = 4.dp, top = 12.dp)
                            .zIndex(2f),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = if (resId == 0) R.drawable.ic_mastercard else resId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.bank_card),
                    )
                },
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = BankMaskTransformation { resId = it }
            )
        }
    }
}