package com.alanturing.lookeo_movil.core.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String){
    object Home: Routes("homeScreen")
    object Login: Routes("loginScreen")
    object Register: Routes("registerScreen")
    object Wardrobe: Routes("wardrobeScreen")
    object Outfits: Routes("outfitsScreen")
    object Profile: Routes("profileScreen")
    object Setting: Routes("settingScreen")
    object NewItem: Routes("newItemScreen")
    object Favorites: Routes("favoritesScreen")

    class DetailItem: Routes("detailItemScreen/{itemId}"){
        fun createRoute(itemId: String) = "detailItemScreen/$itemId"
    }
    class DetailOutfit: Routes("detailOutfitScreen/{outfitId}"){
        fun createRoute(outfitId: String) = "detailOutfitScreen/$outfitId"
    }

}