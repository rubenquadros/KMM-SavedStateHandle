package io.github.rubenquadros.savedstatehandle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(factory = MainViewModel.FACTORY)
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Count: ${uiState.counter}")

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
           Button(
               enabled = uiState.counter > 0,
               onClick = viewModel::decrement
           ) {
               Text("-")
           }

            Button(
                onClick = viewModel::increment
            ) {
                Text("+")
            }
        }
    }
}