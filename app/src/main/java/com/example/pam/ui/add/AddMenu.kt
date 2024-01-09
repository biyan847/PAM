package com.example.pam.ui.add
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam.Navigation.DestinasiNavigasi
import com.example.pam.ui.AddEvent
import com.example.pam.ui.AddMenuTopAppBar
import com.example.pam.ui.AddUIState
import com.example.pam.ui.PenyediaViewModel


import kotlinx.coroutines.launch

object DestinasiEntry : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Add Menu"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addViewModel: AddViewModel = viewModel(factory = PenyediaViewModel.Factory),

    ) {

    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AddMenuTopAppBar(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->

        EntryBody(
            addUIState = addViewModel.addUIState,
            onDataValueChange = addViewModel::updateAddUIState,
            onSaveClick = {
                coroutineScope.launch {
                    addViewModel.addMakanan()
                }
            },
            OnNextClick = {
                navigateBack()
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    addUIState: AddUIState,
    onDataValueChange: (AddEvent) -> Unit,
    onSaveClick: () -> Unit,
    OnNextClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            addEvent = addUIState.addEvent,
            onValueChange = onDataValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .weight(1f, false),
        ){
            Button(
                onClick = onSaveClick,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
            OutlinedButton(modifier = Modifier.weight(1f), onClick =
            OnNextClick) {
                Text("Next")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    addEvent: AddEvent,
    modifier: Modifier = Modifier,
    onValueChange: (AddEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = addEvent.nama,
            onValueChange = { onValueChange(addEvent.copy(nama = it)) },
            label = { Text("Nama Makanan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addEvent.Harga,
            onValueChange = { onValueChange(addEvent.copy(Harga = it)) },
            label = { Text("Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addEvent.Jenis,
            onValueChange = { onValueChange(addEvent.copy(Jenis = it)) },
            label = { Text("Jenis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

    }
}