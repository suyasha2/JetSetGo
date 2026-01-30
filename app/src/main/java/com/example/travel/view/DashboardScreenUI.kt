package com.example.travel.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travel.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.travel.view.ui.theme.TravelTheme

@Composable
fun DashboardScreenUI(onNavigateToLogin: () -> Unit) {

    val PrimaryBlue = Color(0xFF4A90E2)
    val SecondaryBlue = Color(0xFF6AC5F7)
    val CardBg = Color.White
    var search by remember { mutableStateOf("") }



    Scaffold { padding ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(PrimaryBlue, SecondaryBlue, Color.White)
                    )
                )
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(45.dp))

            Text(

                text = "Explore Nepal 🇳🇵",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Discover beautiful places inside Nepal",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                placeholder = { Text("Search Pokhara, Everest...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            LazyColumn (verticalArrangement = Arrangement.spacedBy(15.dp)) {
                item {
                    DestinationCardUI(
                        image = R.drawable.mountain,
                        title = "Mount Everest",
                        location = "Solukhumbu, Nepal",
                        price = "Rs. 1,50,000",
                        rating = "4.9"
                    )
                }
                item {
                    DestinationCardUI(
                        image = R.drawable.pokhara,
                        title = "Pokhara Lakeside",
                        location = "Pokhara, Nepal",
                        price = "Rs. 35,000",
                        rating = "4.7"
                    )
                }

                item {
                    DestinationCardUI(
                        image = R.drawable.chitwan,
                        title = "Chitwan National Park",
                        location = "Chitwan, Nepal",
                        price = "Rs. 28,000",
                        rating = "4.6"
                    )
                }

                item {
                    DestinationCardUI(
                        image = R.drawable.pashupatinath,
                        title = "Pashupatinath Temple",
                        location = "Kathmandu",
                        price = "Rs. 5,000",
                        rating = "4.8"
                    )
                }
            }
        }
    }
}
@Composable
fun DestinationCardUI(
    image: Int,
    title: String,
    location: String,
    price: String,
    rating: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(image),
                contentDescription = title,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 15.dp)) {
                Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(location, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        price,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A90E2),
                        fontSize = 16.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFB300),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(rating)
                    }
                }
            }
        }
    }

}