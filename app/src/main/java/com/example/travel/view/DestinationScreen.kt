package com.example.travel

import androidx.compose.runtime.Composable
import com.example.travel.view.DestinationDetailScreen

@Composable
fun EverestDetailScreen(onBack: () -> Unit, onViewPackages: () -> Unit) {
    DestinationDetailScreen(
        name = "Mount Everest",
        location = "Solukhumbu, Nepal",
        price = "Rs 1,50,000",
        rating = "4.9",
        imageRes = R.drawable.mountain,
        description = "Step into the heart of the Himalayas, a realm of raw natural beauty and thrilling adventure. Majestic peaks, deep valleys, and ever-changing mountain light form a landscape unlike anywhere else on Earth. From scenic hikes and photography to quiet moments of reflection, the mountains offer both excitement and tranquility, leaving visitors with memories as vast as the horizons themselves.",
        onBackClick = onBack,
        onViewPackages = onViewPackages
    )
}

@Composable
fun PokharaDetailScreen(onBack: () -> Unit, onViewPackages: () -> Unit) {
    DestinationDetailScreen(
        name = "Pokhara Lakeside",
        location = "Pokhara, Nepal",
        price = "Rs 35,000",
        rating = "4.7",
        imageRes = R.drawable.pokhara,
        description = "Escape to Pokhara, Nepal’s most picturesque lakeside city, where tranquil waters and majestic Himalayan views create an atmosphere of pure serenity. The peaceful Phewa Lake, reflecting the iconic Machhapuchhre mountain, sets the stage for unforgettable moments — from gentle boat rides to breathtaking sunrise views from Sarangkot. Blending natural beauty with adventure, Pokhara offers world-class paragliding, charming cafés, vibrant streets, and evenings filled with calm, mountain-kissed air. It is the perfect destination to unwind, explore, and simply breathe",
        onBackClick = onBack,
        onViewPackages = onViewPackages
    )
}

@Composable
fun ChitwanDetailScreen(onBack: () -> Unit, onViewPackages: () -> Unit) {
    DestinationDetailScreen(
        name = "Chitwan National Park",
        location = "Chitwan, Nepal",
        price = "Rs 28,000",
        rating = "4.6",
        imageRes = R.drawable.chitwan,
        description = "Venture into the untamed beauty of Chitwan National Park, a UNESCO World Heritage Site and Nepal’s premier wildlife sanctuary. Dense jungles, winding rivers, and vast grasslands are home to extraordinary creatures, including the rare One-Horned Rhino and the elusive Royal Bengal Tiger. Jungle safaris, canoe rides, and guided nature walks reveal a world rich in biodiversity and natural wonder",
        onBackClick = onBack,
        onViewPackages = onViewPackages
    )
}

@Composable
fun PashupatiDetailScreen(onBack: () -> Unit, onViewPackages: () -> Unit) {
    DestinationDetailScreen(
        name = "Pashupatinath Temple",
        location = "Kathmandu, Nepal",
        price = "Rs 5,000",
        rating = "4.8",
        imageRes = R.drawable.pashupatinath,
        description = "Explore one of the most sacred Hindu temples in the world, where spirituality, history, and tradition blend seamlessly. Pashupatinath is more than a temple — it is a living cultural landmark filled with centuries-old rituals, intricate pagoda-style architecture, and a deeply serene atmosphere. Set along the holy banks of the Bagmati River, this revered UNESCO World Heritage Site offers a profound glimpse into Nepal’s religious heritage, sacred ceremonies, and timeless devotion",
        onBackClick = onBack,
        onViewPackages = onViewPackages
    )
}