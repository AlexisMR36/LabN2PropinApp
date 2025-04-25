package dev.ameza.labn2propinapp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ImcCalculatorScreen(){
    var montotxt by remember { mutableStateOf("") }
    var porcentajePropina by remember { mutableStateOf(0.15f) }
    var resultado by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(26.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Spacer(modifier = Modifier.width(16.dp))
        // esto es para el titulo del aplicativo
        Text(text="Calculadora de Propina", style = MaterialTheme.typography.titleLarge)
        //aqui generas el cuadro para ingresr datos
        OutlinedTextField(
            value= montotxt,
            onValueChange = { montotxt=it },
            label = { Text("Monto en soles (S/.)")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text(text = "Porcentaje de Propina: ${(porcentajePropina * 100).toInt()}%")
        //creame el slider para el porcetaje

        Slider(
            value = porcentajePropina,
            onValueChange = { porcentajePropina = it },
            valueRange = 0f..0.30f,
            steps = 5, // Opcional: para marcar saltos entre valores
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val monto = montotxt.toFloatOrNull() ?: 0f
                val propina = monto * porcentajePropina
                val total = monto + propina

                resultado = "Propina: S/. %.2f\nTotal: S/. %.2f".format(propina,total)
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Calcular")
        }
        if(resultado.isNotEmpty())
            Text(text = resultado, style = MaterialTheme.typography.titleMedium)

    }
}