package com.example.travel.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.travel.model.TravelPackage

@Composable
fun PackageDetailScreen(pkg: TravelPackage, onBack: () -> Unit) {
    Scaffold(
        bottomBar = {
            Surface(shadowElevation = 15.dp) {
                Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("TOTAL COST", fontSize = 11.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                        Text("Rs. ${pkg.price}", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
                    }
                    Button(onClick = { }, modifier = Modifier.height(52.dp).width(170.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0081C9))) {
                        Text("CONFIRM BOOKING", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp)) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = null) }
                Text(pkg.name, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                Text(pkg.location, color = Color.Gray)
                Spacer(modifier = Modifier.height(25.dp))
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7FF)), shape = RoundedCornerShape(20.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(" PLAN YOUR TRIP", fontWeight = FontWeight.ExtraBold, color = Color(0xFF0081C9))
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Info, null, tint = Color(0xFF0081C9), modifier = Modifier.size(18.dp))
                            Text("  ${pkg.duration}", fontWeight = FontWeight.Bold)
                        }
                        pkg.packages.forEach { item ->
                            Row(modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CheckCircle, null, tint = Color(0xFF4CAF50), modifier = Modifier.size(18.dp))
                                Text("  $item", fontSize = 14.sp)
                            }
                        }
                    }
                }
                Text("ITINERARY", fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(top = 25.dp, bottom = 10.dp))
            }
