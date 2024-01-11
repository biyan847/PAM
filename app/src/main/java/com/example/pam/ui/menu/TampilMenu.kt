package com.example.pam.ui.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam.Model.Makanan
import com.example.pam.Navigation.DestinasiNavigasi
import com.example.pam.ui.AddMenuTopAppBar
import com.example.pam.ui.PenyediaViewModel

object TampilScreenMenu : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Kontak"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: MenuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AddMenuTopAppBar(
                title = "Makanin",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStateMakan by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemMakanan = uiStateMakan.listMakanan,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onViewClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemMakanan: List<Makanan>,
    modifier: Modifier = Modifier,
    onViewClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemMakanan.isEmpty()) {
            Text(
                text = "Tidak ada data Makanan",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListMakanan(
                itemMakanan= itemMakanan,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onViewClick(it.id) }
            )
        }
    }
}

@Composable
fun ListMakanan(
    itemMakanan: List<Makanan>,
    modifier: Modifier = Modifier,
    onItemClick: (Makanan) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemMakanan, key = { it.id }) { makanan ->
            DataMakan(
                makanan = makanan,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(makanan) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataMakan(
    makanan: Makanan,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = makanan.namamkn,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                )
                Text(
                    text = makanan.harga,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = makanan.jenis,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}