package com.oceanbrasil.android20250805

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oceanbrasil.android20250805.ui.theme.Android20250805Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android20250805Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Formulario(
                        name = "Android",
                        modifier = Modifier
                                    .padding(innerPadding),
                        onEnviarClick = {
                            //transferir para ResultadoActivity
                            Toast.makeText(this, "Tocou no btn!", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, ResultadoActivity::class.java)
                            startActivity(intent)
                        }
                    )

                }
            }
        }
    }
}

@Composable
fun Formulario(name: String, modifier: Modifier = Modifier
               , onEnviarClick: () -> Unit) {
    var nomeDigitado by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("Aguardando... ")}
    val context = LocalContext.current

    Column {
        Text(
            text = mensagem,
            fontSize = 48.sp,
            color = Color.Red,
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = nomeDigitado,
            onValueChange = { nomeDigitado = it },
            label = {
                Text("Digite o seu nome")
            }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        BotaoAzulOcean(onClick = {
            Log.d("MainActivity", "O nome digitado é: $nomeDigitado")
            mensagem = "Olá, $nomeDigitado"
            onEnviarClick()
        })
    }
}

@Composable
fun BotaoAzulOcean(onClick: () -> Unit) {
    Button(onClick = { onClick() }
        , colors = ButtonColors(containerColor = Color.Blue
            , contentColor = Color.White
            , disabledContainerColor = Color.Gray
            , disabledContentColor = Color.Yellow)
    ) {
        Text("Enviar")
    }
}