package com.example.pam.ui.menu


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam.Model.Makanan
import com.example.pam.Navigation.DestinasiNavigasi
import com.example.pam.ui.AddMenuTopAppBar
import com.example.pam.ui.DetailUIState
import com.example.pam.ui.PenyediaViewModel
import com.example.pam.ui.detail.DetailViewModel
import com.example.pam.ui.toAdmin



object DetailPaymentScreen : DestinasiNavigasi {
    override val route = "item_payment"
    override val titleRes = "Detail Pesanan"
    const val MakananId = "itemId"
    val routeWithArgs = "$route/{$MakananId}"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            AddMenuTopAppBar(
                title = DetailPaymentScreen.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }, modifier = modifier
    ) { innerPadding ->
        ItemDetailsBody(
            detailUIState = uiState.value,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            )
    }
}

@Composable
private fun ItemDetailsBody(
    detailUIState: DetailUIState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ItemDetails(
            makanan  = detailUIState.addEvent.toAdmin(), modifier = Modifier.fillMaxWidth()
        )
        OutlinedButton(
            onClick = { },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Check Out")
        }
    }
}


@Composable
fun ItemDetails(
    makanan: Makanan, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ItemDetailsRow(
                labelResID ="Nama",
                itemDetail = makanan.namamkn,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRow(
                labelResID = "Harga",
                itemDetail = makanan.harga,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRow(
                labelResID = "Jenis",
                itemDetail = makanan.jenis,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
        }

    }
}

@Composable
private fun ItemDetailsRow(
    labelResID: String, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = labelResID, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}
