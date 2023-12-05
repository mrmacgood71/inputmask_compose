package it.macgood.inputmask_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import it.macgood.inputmask_compose.bank.BankCardStarsTextField
import it.macgood.inputmask_compose.bank.BankCardTextField
import it.macgood.inputmask_compose.bank.BankCardUnderlinesTextField
import it.macgood.inputmask_compose.date.DateDmyEmptyTextField
import it.macgood.inputmask_compose.date.DateDmyLetterTextField
import it.macgood.inputmask_compose.date.DateDmySlashTextField
import it.macgood.inputmask_compose.date.DateDmyUnderlineTextField
import it.macgood.inputmask_compose.ip.IpTextField
import it.macgood.inputmask_compose.passport.PassportTextField
import it.macgood.inputmask_compose.phone.PhoneTextField
import it.macgood.inputmask_compose.ui.theme.InputMask_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InputMask_ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
//                        BankCardTextField(
//                            colors = TextFieldDefaults.colors(
//                                unfocusedContainerColor = Color.Green
//                            ),
//                            shape = RoundedCornerShape(2.dp),
//                            onSuccess = {}, onError = {}
//                        )
//                        BankCardStarsTextField(onSuccess = {}, onError = {})
//                        BankCardUnderlinesTextField(
//                            colors = TextFieldDefaults.colors(
//                                unfocusedContainerColor = Color.Magenta
//                            ),
//                            shape = RoundedCornerShape(32.dp),
//                            modifier = Modifier.fillMaxWidth(), onSuccess = {}, onError = {}
//                        )
                        PhoneTextField()
//                        PassportTextField()
//                        DateDmyUnderlineTextField()
//                        DateDmyEmptyTextField()
//                        DateDmyLetterTextField()
//                        DateDmySlashTextField()
                        IpTextField()
                    }
                }
            }
        }
    }
}