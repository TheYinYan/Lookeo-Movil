package com.alanturing.lookeo_movil.core.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(){
    @Serializable
    object Home

    @Serializable
    object Login

    @Serializable
    object Register

    @Serializable
    object Wardrobe

    @Serializable
    object Outfits

    @Serializable
    object Profile

    @Serializable
    object Settings

    @Serializable
    object NewItem

    @Serializable
    object Favorites

    @Serializable
    data class DetailItem(val itemId: String) //: Routes("ItemDetailScreen/${itemId}")

    @Serializable
    data class DetailOutfit(val outfitId : String) //: Routes("OutfitDetailScreen/${outfitId}")
}