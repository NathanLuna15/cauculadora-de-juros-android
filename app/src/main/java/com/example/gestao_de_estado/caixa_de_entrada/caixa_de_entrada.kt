package com.example.gestao_de_estado.caixa_de_entrada

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CaixaDeEntrada(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    value: String,
    atualizarValor : (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label
            )
        },
        placeholder = {
            Text(
                text = placeholder
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        value = value,
        onValueChange = {atualizarValor(it)}
    )
}