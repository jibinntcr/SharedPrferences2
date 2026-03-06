package com.example.sharedprferences2

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// BITS Pilani Theme Colors
val BitsBlue = Color(0xFF003366)
val BitsRed = Color(0xFFC41230)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                StudentEnrollmentApp()
            }
        }
    }
}

@Composable
fun StudentEnrollmentApp() {
    val context = LocalContext.current

    // --- 1. OPEN THE STORAGE ---
    // This creates/opens a file named "bits_data.xml" privately for this app
    val sharedPrefs = context.getSharedPreferences("bits_data", Context.MODE_PRIVATE)

    // UI States (Short-term memory - will reset if app is killed)
    var nameInput by remember { mutableStateOf("") }
    var idInput by remember { mutableStateOf("") }
    var isHosteller by remember { mutableStateOf(false) }

    // Loaded Data State (Used to show what we fetched from storage)
    var displayData by remember { mutableStateOf("No data loaded yet.") }

    Column(modifier = Modifier.fillMaxSize()) {

        // --- HEADER ---
        Box(
            modifier = Modifier.fillMaxWidth().background(BitsBlue).padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("BITS PILANI STUDENT PORTAL", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Column(modifier = Modifier.padding(24.dp).weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {

            // USER INPUTS
            OutlinedTextField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = { Text("Enter Student Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = idInput,
                onValueChange = { idInput = it },
                label = { Text("Enter Student ID (Number)") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 12.dp)) {
                Text("Is Hosteller?", fontWeight = FontWeight.Medium)
                Switch(checked = isHosteller, onCheckedChange = { isHosteller = it })
            }

            // --- 2. SAVING THREE VALUES (The Write Action) ---
            Button(
                onClick = {
                    // Step A: Open the Editor (The Pen)
                    val editor = sharedPrefs.edit()

                    // Step B: Write data into unique "Lockers" (Keys)
                    editor.putString("key_name", nameInput)
                    editor.putInt("key_id", idInput.toIntOrNull() ?: 0) // Convert text to number safely
                    editor.putBoolean("key_hostel", isHosteller)

                    // Step C: Save permanently to the disk
                    editor.apply()

                    Toast.makeText(context, "Student Data Saved!", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = BitsRed),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("SAVE STUDENT RECORD")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // --- 3. READING THREE VALUES (The Read Action) ---
            Button(
                onClick = {
                    // Fetch data using the EXACT same keys used during saving
                    // If a key doesn't exist, it returns the "Default Value" (the second parameter)
                    val name = sharedPrefs.getString("key_name", "N/A")
                    val id = sharedPrefs.getInt("key_id", 0)
                    val hostel = sharedPrefs.getBoolean("key_hostel", false)

                    displayData = "NAME: $name\nID: $id\nHOSTELLER: ${if(hostel) "Yes" else "No"}"
                },
                colors = ButtonDefaults.buttonColors(containerColor = BitsBlue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("FETCH RECORD FROM STORAGE")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // RESULT CARD
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = displayData, modifier = Modifier.padding(16.dp), color = BitsBlue, fontWeight = FontWeight.Bold)
            }
        }

        // --- FOOTER ---
        Box(
            modifier = Modifier.fillMaxWidth().background(Color.LightGray).padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Knowledge is Power - WILP SDPD 2026", fontSize = 10.sp, color = Color.Gray)
        }
    }
}