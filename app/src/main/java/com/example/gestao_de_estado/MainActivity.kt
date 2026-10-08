package com.example.gestao_de_estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestao_de_estado.caixa_de_entrada.CaixaDeEntrada
import com.example.gestao_de_estado.calculo.calcularJuros
import com.example.gestao_de_estado.calculo.calcularMontante
import com.example.gestao_de_estado.ui.theme.GestaodeEstadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestaodeEstadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JurosScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun JurosScreen(modifier: Modifier = Modifier) {
    var capital by remember {
        mutableStateOf("")
    }

    var taxa by remember {
        mutableStateOf("")
    }

    var tempo by remember {
        mutableStateOf("")
    }

    var juros by remember {
        mutableDoubleStateOf(0.0)
    }

    var montante by remember {
        mutableDoubleStateOf(0.0)
    }

    Column (
        modifier = modifier.fillMaxSize(),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(136, 38, 199, 255))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Calculadora Juros Simples",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-30).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF9F6F6)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Dados do investimento",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        OutlinedTextField(
                            value = capital,
                            onValueChange = { capital = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "qual a taxa de juros") },
                            placeholder = { Text(text = "Quanto deseja investir?") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )



                        CaixaDeEntrada(
                            label = "valor investimento",
                            placeholder = "qual a taxa de juros",
                            keyboardType = KeyboardType.Decimal,
                            value = taxa,
                            atualizarValor = {taxa = it}
                        )

                        CaixaDeEntrada(
                            label = "periodo de meses",
                            placeholder = "qual o periodo em meses",
                            keyboardType = KeyboardType.Decimal,
                            value = tempo,
                            atualizarValor = {taxa = it}
                        )

                        Button(
                            onClick = {
                                juros = calcularJuros(
                                    capital = capital.toDouble(),
                                    taxa = taxa.toDouble(),
                                    tempo = tempo.toDouble()
                                )

                                montante = calcularMontante(
                                    capital = capital.toDouble(),
                                    juros = juros
                                )
                            },

                            modifier = Modifier.fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}