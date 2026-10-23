package com.alanturing.lookeo_movil.core.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String){
    @Serializable
    object Home: Routes("HomeScreen")

    @Serializable
    object Login: Routes("LoginScreen")

    @Serializable
    object Register: Routes("RegisterScreen")

    @Serializable
    object Wardrobe: Routes("WardrobeScreen")

    @Serializable
    object Outfits: Routes("OutfitsScreen")

    @Serializable
    object Profile: Routes("ProfileScreen")

    @Serializable
    object Setting: Routes("SettingScreen")

    @Serializable
    object NewItem: Routes("NewItemScreen")

    @Serializable
    object Favorites: Routes("FavoritesScreen")

    @Serializable
    class DetailItem(itemId: String): Routes("ItemDetailScreen/${itemId}")

    @Serializable
    class DetailOutfit(outfitId : String): Routes("OutfitDetailScreen/${outfitId}")
}