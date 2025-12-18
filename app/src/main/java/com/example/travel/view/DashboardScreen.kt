package com.example.travel.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToLogin: () -> Unit
) {

    val PrimaryBlue = Color(0xFF4A90E2)
    val SecondaryBlue = Color(0xFF6AC5F7)
    val DarkTitleColor = Color(0xFF204161)
    val CardBgColor = Color.White.copy(alpha = 0.8f)
    val AccentColor = Color(0xFFF7E8E8)


    Scaffold(
        topBar = {
            DashboardTopBar(
                onLogoutClicked = onNavigateToLogin,
                PrimaryBlue = PrimaryBlue
            )
        },
        bottomBar = {
            DashboardBottomBar(
                PrimaryBlue = PrimaryBlue,
                AccentColor = AccentColor
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                PrimaryBlue,
                                SecondaryBlue,
                                Color.White
                            )
                        )
                    )
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        WelcomeCard(
                            CardBgColor = CardBgColor,
                            DarkTitleColor = DarkTitleColor
                        )
                    }
                    item {
                        SearchBarCard(
                            CardBgColor = CardBgColor,
                            PrimaryBlue = PrimaryBlue
                        )
                    }
                    item {
                        SectionHeader(
                            title = "Top Destinations",
                            DarkTitleColor = DarkTitleColor
                        )
                    }
                    items(listOf("Paris, France", "Kyoto, Japan", "Machu Picchu, Peru")) { destination ->
                        DestinationCard(
                            destination = destination,
                            onDetailsClick = {},
                            PrimaryBlue = PrimaryBlue,
                            DarkTitleColor = DarkTitleColor
                        )
                    }
                    item {
                        SectionHeader(
                            title = "Recommended For You",
                            DarkTitleColor = DarkTitleColor
                        )
                    }
                    items(listOf("Beach Getaway Guide", "Mountain Hikes Checklist")) { title ->
                        RecommendationCard(
                            title = title,
                            onClick = {},
                            AccentColor = AccentColor,
                            DarkTitleColor = DarkTitleColor
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(onLogoutClicked: () -> Unit, PrimaryBlue: Color) {
    TopAppBar(
        title = {
            Text("JetSetGo Dashboard", color = Color.White, fontWeight = FontWeight.SemiBold)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryBlue,
            titleContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = onLogoutClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Logout",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun DashboardBottomBar(PrimaryBlue: Color, AccentColor: Color) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = PrimaryBlue
    ) {
        val items = listOf("Home", "Search", "Favorites")
        val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Favorite)
        val selectedIndex = 0

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = index == selectedIndex,
                onClick = {  },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = AccentColor.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Composable
fun WelcomeCard(CardBgColor: Color, DarkTitleColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardBgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "Hello, Traveller!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTitleColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Ready for your next adventure?",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun SearchBarCard(CardBgColor: Color, PrimaryBlue: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardBgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {  },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = PrimaryBlue,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "Where do you want to go?",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun SectionHeader(title: String, DarkTitleColor: Color) {
    Text(
        title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = DarkTitleColor,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
}

@Composable
fun DestinationCard(destination: String, onDetailsClick: () -> Unit, PrimaryBlue: Color, DarkTitleColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onDetailsClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                destination,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkTitleColor
            )
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Details",
                tint = PrimaryBlue
            )
        }
    }
}

@Composable
fun RecommendationCard(title: String, onClick: () -> Unit, AccentColor: Color, DarkTitleColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = AccentColor.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTitleColor
            )
        }
    }
}