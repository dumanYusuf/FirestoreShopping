import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firestoreshopping.R
import com.example.firestoreshopping.domain.model.LocationUser
import com.example.firestoreshopping.presentation.location_page_view.view.LocationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPage(
    onBackPressed: () -> Unit,
    viewModel: LocationViewModel= hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState(
        //skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    val openSheet = remember { mutableStateOf(false) }
    var il by remember { mutableStateOf("") }
    var ilce by remember { mutableStateOf("") }
    var mahalle by remember { mutableStateOf("") }
    var sokakNo by remember { mutableStateOf("") }
    var katNo by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
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
                    text = "Adres"
                )
            }
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        coroutineScope.launch {
                            openSheet.value = true
                        }
                    },
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon"
            )
        }

        if (openSheet.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    coroutineScope.launch { openSheet.value = false }
                },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Yeni Adres Ekle",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = il,
                        onValueChange = {il=it},
                        placeholder = { Text(text = "Lütfen il giriniz")},
                        label = { Text("İl") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    OutlinedTextField(
                        value = ilce,
                        onValueChange = {ilce=it},
                        placeholder = { Text(text = "Lütfen ilce giriniz")},
                        label = { Text("İlce") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    OutlinedTextField(
                        value = mahalle,
                        onValueChange = {mahalle=it},
                        label = { Text("Mahalle") },
                        placeholder = { Text(text = "Lütfen mahale giriniz")},
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextField(
                        value = sokakNo,
                        onValueChange = {sokakNo=it},
                        label = { Text("Sokak No") },
                        placeholder = { Text(text = "Lütfen sokak Numarası giriniz")},
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    OutlinedTextField(
                        value = katNo,
                        onValueChange = {katNo=it},
                        label = { Text("Kat No") },
                        placeholder = { Text(text = "Lütfen Kat Numarası giriniz")},
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            val locationUser=LocationUser(locationId = "", province = il, country = ilce, neighborhood = mahalle, streetNo = sokakNo, floorNo = katNo)
                            viewModel.addLocation(locationUser)
                        }) {
                        Text(text = "Adresi Ekle")
                    }
                }
            }
        }
    }
}
