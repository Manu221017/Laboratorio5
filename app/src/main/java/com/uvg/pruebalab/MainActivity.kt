package com.uvg.pruebalab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.pruebalab.ui.theme.PruebaLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaLabTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Actualización disponible") },
                actions = {
                    TextButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"))
                        context.startActivity(intent)
                    }) {
                        Text(text = "Descargar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Lunes",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "30 de septiembre",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Terminar jornada")
            }
            Spacer(modifier = Modifier.height(16.dp))
            ScheduleCard(
                name = "El Buen Sabor",
                location = "Calle 10, Zona 1",
                time = "9:00AM - 9:00PM",
                onDirectionsClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=14.634915,-90.506882"))
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun ScheduleCard(name: String, location: String, time: String, onDirectionsClick: () -> Unit) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = location)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = time)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        Toast.makeText(context, "Juan Pérez", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Iniciar")
                }
                TextButton(onClick = {
                    Toast.makeText(context, "Comida Internacional\nNormal: QQ", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Detalles")
                }
                IconButton(onClick = onDirectionsClick) {
                    Icon(
                        imageVector = Icons.Filled.Directions,
                        contentDescription = "Directions"
                    )
                }
            }
        }
    }
}
