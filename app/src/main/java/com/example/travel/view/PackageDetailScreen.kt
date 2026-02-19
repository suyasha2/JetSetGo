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
