package com.example.travel.repository

import com.example.travel.model.ItineraryStep
import com.example.travel.model.TravelPackage

class TravelRepository {
    private val allPackages = listOf(
        TravelPackage(
            id = "mountain",
            name = "Everest Base Camp Trek",
            location = "Solukhumbu, Nepal",
            price = "1,50,000",
            duration = "12 Days",
            packages = listOf(
                "Accommodation Included",
                "All Standard Meals (Breakfast, Lunch, Dinner)",
                "Experienced Professional Guide",
                "Required Trekking Permits",
                "Hotel Everest View / Yeti Mountain Home"
            ),
            itinerary = listOf(
                ItineraryStep("D1", "Arrival in Kathmandu", "Airport pickup and transfer to hotel. Trip briefing and preparation."),
                ItineraryStep("D2", "Fly to Lukla – Trek to Phakding", "Enjoy a scenic mountain flight followed by a gentle trek to Phakding."),
                ItineraryStep("D3", "Phakding to Namche Bazaar", "Trek through suspension bridges and reach the vibrant Sherpa town of Namche."),
                ItineraryStep("D4", "Acclimatization Day", "Rest day with short hikes to Everest View Hotel and surrounding viewpoints."),
                ItineraryStep("D5", "Namche to Tengboche", "Walk through rhododendron forests and visit the famous Tengboche Monastery."),
                ItineraryStep("D6", "Tengboche to Dingboche", "Experience breathtaking Himalayan landscapes and high-altitude villages."),
                ItineraryStep("D7", "Acclimatization Day", "Allow your body to adapt while exploring the beautiful Dingboche region."),
                ItineraryStep("D8", "Dingboche to Lobuche", "Trek alongside glaciers and dramatic mountain scenery."),
                ItineraryStep("D9", "Lobuche to Everest Base Camp", "Reach the legendary Everest Base Camp – a lifetime achievement."),
                ItineraryStep("D10", "Base Camp to Pheriche", "Descend with stunning views of the Khumbu region."),
                ItineraryStep("D11", "Pheriche to Namche Bazaar", "Continue descending through scenic trails and Sherpa settlements."),
                ItineraryStep("D12", "Namche to Lukla", "Final trekking day followed by preparation for return flight.")
            )

        ),
        TravelPackage(
            id = "pokhara",
            name = "Pokhara Luxury Retreat",
            location = "Pokhara, Nepal",
            price = "35,000",
            duration = "3 Days",
            packages = listOf(
                "Luxury Lakeside Accommodation",
                "Daily Breakfast Included",
                "Private Vehicle for Tours",
                "Boating at Phewa Lake",
                "Fish Tail Lodge / Temple Tree Resort"
            ),
            itinerary = listOf(
                ItineraryStep("D1", "Arrival & City Exploration", "Visit Davis Falls, Gupteshwor Cave, and enjoy a relaxing evening at Lakeside."),
                ItineraryStep("D2", "Sarangkot Sunrise", "Early morning drive to Sarangkot for breathtaking sunrise and Himalayan views."),
                ItineraryStep("D3", "Leisure & Boating", "Enjoy peaceful boating on Phewa Lake followed by free time for cafés and shopping.")
            )

        ),
        TravelPackage(
            id = "chitwan",
            name = "Chitwan Wildlife Safari",
            location = "Chitwan, Nepal",
            price = "28,000",
            duration = "3 Days",
            packages = listOf(
                "Premium Jungle Resort Stay",
                "All Meals Included",
                "Guided Jungle Activities",
                "Canoeing & Safari Experience",
                "Meghauli Serai / Green Park Chitwan"
            ),
            itinerary = listOf(
                ItineraryStep("D1", "Arrival & Cultural Program", "Warm welcome at resort followed by traditional Tharu cultural performance."),
                ItineraryStep("D2", "Jungle Safari Adventure", "Enjoy jungle safari, canoe ride, nature walk, and wildlife exploration."),
                ItineraryStep("D3", "Bird Watching & Departure", "Morning bird watching experience before return journey.")
            )

        ),
        TravelPackage(
            id = "pashupati",
            name = "Pashupatinath Spiritual Experience",
            location = "Kathmandu, Nepal",
            price = "5,000",
            duration = "Full Day",
            packages = listOf(
                "Comfortable Hotel Stay",
                "Professional Local Guide",
                "Private Transportation",
                "Temple Entry Assistance",
                "Evening Bagmati Aarati",
                "Mahadev Hotel / Hotel JS"
            ),
            itinerary = listOf(
                ItineraryStep("Morning", "Temple Exploration", "Visit the sacred Pashupatinath Temple, explore surrounding shrines, and learn about its religious significance."),
                ItineraryStep("Afternoon", "Cultural & Heritage Walk", "Walk along the Bagmati River, observe traditional rituals, and explore nearby heritage sites."),
                ItineraryStep("Evening", "Bagmati Aarati Ceremony", "Experience the peaceful and spiritual evening Aarati on the banks of the Bagmati River.")
            )
        )
    )

    fun getPackageById(id: String): TravelPackage? = allPackages.find { it.id == id }
}