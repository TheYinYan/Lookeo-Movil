package com.alanturing.lookeo_movil.core.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String){
    @Serializable
    object Home: Routes("homeScreen")

    @Serializable
    object Login: Routes("loginScreen")

    @Serializable
    object Register: Routes("registerScreen")

    @Serializable
    object Wardrobe: Routes("wardrobeScreen")

    @Serializable
    object Outfits: Routes("outfitsScreen")

    @Serializable
    object Profile: Routes("profileScreen")

    @Serializable
    object Setting: Routes("settingScreen")

    @Serializable
    object NewItem: Routes("newItemScreen")

    @Serializable
    object Favorites: Routes("favoritesScreen")

    @Serializable
    class DetailItem: Routes("detailItemScreen/{itemId}"){
        fun createRoute(itemId: String) = "detailItemScreen/$itemId"
    }

    @Serializable
    class DetailOutfit: Routes("detailOutfitScreen/{outfitId}"){
        fun createRoute(outfitId: String) = "detailOutfitScreen/$outfitId"
    }
}