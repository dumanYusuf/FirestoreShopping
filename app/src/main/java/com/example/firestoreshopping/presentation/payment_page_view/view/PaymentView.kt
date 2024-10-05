import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firestoreshopping.R
import com.example.firestoreshopping.domain.model.Card
import com.example.firestoreshopping.presentation.payment_page_view.PaymentViewModel

@Composable
fun PaymentPage(
    onBackPressed: () -> Unit,
    viewModel: PaymentViewModel= hiltViewModel()
) {
    var cardNumber by remember { mutableStateOf("") }
    var cardYearsMonth by remember { mutableStateOf("") }
    var cardCvv by remember { mutableStateOf("") }
    var cardHolder by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var switchState by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()


    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription = "")
            }
            Text(
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp),
                text = "Siparişi Tamamla"
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.cardpayment), contentDescription = ""
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = cardNumber,
            onValueChange = {
                if (it.length <= 16) {
                    cardNumber = it
                    errorMessage = ""
                }
            },
            label = { Text("Kard Numarası giriniz") },
            visualTransformation = PasswordVisualTransformation(),
            isError = errorMessage.isNotEmpty()
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = cardYearsMonth,
            onValueChange = { input ->
                var formattedInput = input.filter { it.isDigit() }

                if (formattedInput.length > 2) {
                    formattedInput = formattedInput.substring(0, 2) + "/" + formattedInput.substring(2)
                }

                if (formattedInput.length <= 5) {
                    cardYearsMonth = formattedInput
                }
            },
            label = { Text("Kart Ay/Yıl giriniz") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = cardCvv,
            onValueChange = { input ->
                if (input.length <= 3) {
                    cardCvv = input
                }
            },
            label = { Text("CVV giriniz") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = cardHolder,
            onValueChange = {
                cardHolder=it
            },
            label = { Text("Kart sahibinin adını giriniz") },
        )

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            , horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                fontSize = 20.sp,
                text = "Kartı Kaydet")
            Switch(
                checked =switchState ,
                onCheckedChange = {
                    switchState=it
                    val newCard=Card(cardId = "", cardCv = cardCvv, cardHolder = cardHolder, cardNumber = cardNumber, cardYearsMonth = cardYearsMonth)
                    viewModel.addCard(newCard)
                })
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                if (cardNumber.length != 16) {
                    errorMessage = "Kart numarası 16 haneli olmalıdır."
                } else {

                }
            }
        ) {
            Text(
                fontSize = 20.sp,
                text = "Ödemeyi Tamamla")
        }
    }
}
