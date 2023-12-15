package it.macgood.inputmask_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.macgood.inputmask.bank.BankCardStarsTextField
import it.macgood.inputmask.bank.BankCardTextField
import it.macgood.inputmask.bank.BankCardUnderlinesTextField
import it.macgood.inputmask.date.DateDmyEmptyTextField
import it.macgood.inputmask.date.DateDmyLetterTextField
import it.macgood.inputmask.date.DateDmySlashTextField
import it.macgood.inputmask.date.DateDmyUnderlineTextField
import it.macgood.inputmask.ip.IpTextField
import it.macgood.inputmask.passport.PassportTextField
import it.macgood.inputmask.phone.PhoneTextField
import it.macgood.inputmask.ukban.UkIBanTextField
import it.macgood.inputmask_compose.ui.theme.InputMask_ComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InputMask_ComposeTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                val scrollState = rememberScrollState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                                .verticalScroll(scrollState),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            BankCardTextField(
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Green
                                ),
                                shape = RoundedCornerShape(2.dp),
                                modifier = Modifier.fillMaxWidth(),
                                onSuccess = {},
                                onError = {}
                            )
                            BankCardStarsTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onSuccess = {},
                                onError = {}
                            )
                            BankCardUnderlinesTextField(
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Magenta
                                ),
                                shape = RoundedCornerShape(32.dp),
                                modifier = Modifier.fillMaxWidth(), onSuccess = {}, onError = {}
                            )
                            PhoneTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onSuccess = {},
                                onError = {}
                            )
                            PassportTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onSuccess = {},
                                onError = {}
                            )
                            DateDmyUnderlineTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onSuccess = {},
                                onError = {}
                            )
                            DateDmyEmptyTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onSuccess = {},
                                onError = {}
                            )
                            DateDmyLetterTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onSuccess = {},
                                onError = {}
                            )
                            DateDmySlashTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onSuccess = {},
                                onError = {}
                            )
                            val error = remember { mutableStateOf(false) }
                            val ipTemplate = remember { mutableStateOf("") }
                            IpTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onValueChange = { ip ->
                                    ipTemplate.value = ip
                                },
                                onError = {
                                    error.value = true
                                },
                                isError = if (ipTemplate.value.length >= 3) error.value else {
                                    error.value = false; false
                                },
                                onSuccess = {},
                                colors = TextFieldDefaults.colors(errorContainerColor = Color.Red)

                            )
                            val context = LocalContext.current
                            var message by remember {
                                mutableStateOf(context.getString(R.string.iban))
                            }
                            var text by remember {
                                mutableStateOf("")
                            }

                            UkIBanTextField(
                                modifier = Modifier.fillMaxWidth(),
                                onValueChange = { text = it },
                                onError = {
                                    message = it.message ?: context.getString(R.string.iban)
                                },
                                label = {
                                        Text(text = if (text.length > 1) message else context.getString(R.string.iban))
                                },
                                onSuccess = {}
                            )
                        }
                    }
                }
            }
        }
    }
}