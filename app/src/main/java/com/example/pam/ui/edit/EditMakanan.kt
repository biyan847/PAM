package com.example.pam.ui.edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.pam.Navigation.DestinasiNavigasi
import  androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam.ui.AddMenuTopAppBar
import com.example.pam.ui.PenyediaViewModel
import com.example.pam.ui.add.EntryBody
import kotlinx.coroutines.launch

object EditMakanan : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Makanan"
    const val makananId= "itemId"
    val routeWithArgs = "${EditMakanan.route}/{$makananId}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMakanan(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditMakananViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {

                AddMenuTopAppBar(
                    title = EditMakanan.titleRes,
                    canNavigateBack = true,
                    navigateUp = onNavigateUp
                )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            addUIState = viewModel.makanUiState,
            onDataValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatemakanan()
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