package com.example.travel.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.travel.R

@Composable
fun DestinationDetailScreen(
    name: String,
    location: String,
    price: String,
    rating: String,
    imageRes: Int,
    description: String,
    onBackClick: () -> Unit,
    onViewPackages: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(400.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 360.dp)
                .background(Color.White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .padding(30.dp)
        ) {
            Text(name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(location, color = Color.Gray)

            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, null, tint = Color(0xFFFFB300))
                Text(" $rating", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(description, color = Color.DarkGray, lineHeight = 20.sp)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onViewPackages, // Click garda package page ma jane
                modifier = Modifier.fillMaxWidth().height(55.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A90E2))
            ) {
                Text("View Packages - $price", color = Color.White)
            }
        }

        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(top = 45.dp, start = 20.dp).background(Color.White.copy(0.6f), CircleShape)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
        }
    }
}