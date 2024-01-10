package com.example.pam.ui.DataPelanggan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pam.Navigation.DestinasiNavigasi
import com.example.pam.R
import com.example.pam.ui.AddMenuTopAppBar
import com.example.pam.ui.AddPelanggan
import com.example.pam.ui.AddUIState
import com.example.pam.ui.PenyediaViewModel
import com.example.pam.ui.add.DestinasiEntry
import com.example.pam.ui.detail.DetailDestination
import com.example.pam.ui.edit.EditMakananScreen
import com.example.pam.ui.theme.PAMTheme
import kotlinx.coroutines.launch


object DestinasiDataPel : DestinasiNavigasi{
    override val route = "Item_entry"
    override val titleRes = "add pelanggan"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPel(
    navigateNext: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    detailDatapelViewModel: DetailDatapelViewModel = viewModel(factory = PenyediaViewModel.comsumen)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AddMenuTopAppBar(
                title = DestinasiDataPel.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack )
        }
    ) { innerPadding ->
        Entri(
            addUIState = detailDatapelViewModel.AddUIState,
            onDataValueChange = detailDatapelViewModel::UpdateAddUIState,
            onSaveClick = { coroutineScope.launch {
                detailDatapelViewModel.addpelanggan()
                }
            },
            navigateNext = {
                navigateNext()
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )

    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.3f),
            Alignment.TopEnd,){
            Image(painter = painterResource(id = R.drawable.purpleback),
                contentDescription = "Back App",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds)
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Image(painter = painterResource(id = R.drawable.sendok), contentDescription = "Logo App", modifier = Modifier
                    .weight(5f)
                    .size(250.dp)
                )
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Entri(
    addUIState: AddUIState,
    onDataValueChange: (AddPelanggan) -> Unit,
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit,
    navigateNext: () -> Unit,
    detailDatapelViewModel: DetailDatapelViewModel = viewModel(factory = PenyediaViewModel.comsumen)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Column(modifier = modifier.padding(20.dp))
    {
        UiInput(
            addPelanggan = addUIState.addPelanggan,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onDataValueChange,
            onSaveClick = {
                coroutineScope.launch {
                    detailDatapelViewModel.addpelanggan()
                }
            },
            OnNextClick = {
                navigateNext()
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiInput(
    addPelanggan: AddPelanggan,
    modifier: Modifier = Modifier,
    onValueChange: (AddPelanggan) -> Unit = {},
    enabled: Boolean = true,
    onSaveClick: () -> Unit,
    OnNextClick: () -> Unit,

){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 700.dp, width = 200.dp),

            shape = RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp, bottomEnd = 50.dp, bottomStart = 50.dp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color(0xFFC2AECF),
            ),
            border = BorderStroke(2.dp, Color.Black)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ){
                OutlinedTextField(

                    value = addPelanggan.plgn,
                    onValueChange = { onValueChange(addPelanggan.copy(plgn = it)) },
                    label = { Text("Nama") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = enabled,
                    shape = RoundedCornerShape(20.dp),

                    )

                OutlinedTextField(
                    value = addPelanggan.Nomormeja,
                    onValueChange = { onValueChange(addPelanggan.copy(Nomormeja = it)) },
                    label = { Text("No Meja") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = enabled,
                    shape = RoundedCornerShape(20.dp),
                )
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f, false),
                ){
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ){
                        Button(
                            onClick = onSaveClick,
                            shape = MaterialTheme.shapes.small,
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ApadehPreview() {
    PAMTheme {

    }
}