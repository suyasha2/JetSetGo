package com.example.travel.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
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
fun PackageDetailScreen(
    pkg: TravelPackage,
    onBack: () -> Unit,
    onBookingClick: (String, String) -> Unit
) {
    Scaffold(
        bottomBar = {
            Surface(
                shadowElevation = 30.dp,
                color = Color.White,
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 35.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("TOTAL COST", fontSize = 12.sp, color = Color.DarkGray, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
                        Text("Rs. ${pkg.price}", fontSize = 24.sp, fontWeight = FontWeight.Black, color = Color.Black)
                    }

                    Button(
                        onClick = {

                            onBookingClick(pkg.name, "Rs. ${pkg.price}")
                        },
                        modifier = Modifier.height(56.dp).width(180.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0081C9))
                    ) {
                        Text("CONFIRM BOOKING", fontWeight = FontWeight.ExtraBold, color = Color.White)
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F5F9))
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black, modifier = Modifier.size(28.dp))
                }

                Text(
                    text = pkg.name,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 40.sp,
                    color = Color(0xFF0F172A)
                )
                Text(pkg.location, color = Color(0xFF475569), fontSize = 17.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(25.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2FE)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text("PLAN DETAILS", fontWeight = FontWeight.Black, color = Color(0xFF0369A1), fontSize = 13.sp, letterSpacing = 1.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Info, null, tint = Color(0xFF0369A1), modifier = Modifier.size(20.dp))
                            Text("  ${pkg.duration} Trip", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = Color(0xFF0F172A))
                        }
                    }
                }

                Text(
                    "ITINERARY",
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp,
                    color = Color(0xFF1E293B),
                    modifier = Modifier.padding(top = 35.dp, bottom = 20.dp),
                    letterSpacing = 1.sp
                )
            }

            items(pkg.itinerary) { step ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Surface(
                        modifier = Modifier.size(42.dp),
                        shape = CircleShape,
                        color = Color(0xFF0081C9)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(step.day, fontWeight = FontWeight.Black, color = Color.White, fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(18.dp))
                    Column {
                        Text(
                            text = step.title,
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            color = Color(0xFF0F172A)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = step.detail,
                            fontSize = 15.sp,
                            color = Color(0xFF334155),
                            lineHeight = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), thickness = 1.dp, color = Color(0xFFCBD5E1))
                Text(
                    "HOTELS & INCLUSIONS",
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp,
                    color = Color(0xFF1E293B),
                    modifier = Modifier.padding(top = 20.dp, bottom = 15.dp),
                    letterSpacing = 1.sp
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        pkg.packages.forEach { item ->
                            Row(modifier = Modifier.padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CheckCircle, null, tint = Color(0xFF16A34A), modifier = Modifier.size(20.dp))
                                Text("  $item", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}