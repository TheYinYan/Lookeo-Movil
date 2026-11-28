package com.alanturing.lookeo_movil.features.wardrobe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.alanturing.lookeo_movil.core.ui.components.LookeoBottomBar
import com.alanturing.lookeo_movil.core.ui.components.LookeoTopBar
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite
import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.alanturing.lookeo_movil.features.items.ui.DropdownField

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WardrobeScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToNewItem: () -> Unit,
	onNavigateToItemDetail: (Int) -> Unit,
    viewModel: WardrobeViewModel = hiltViewModel()
) {
    val items by viewModel.filteredItems.collectAsStateWithLifecycle()
	val filters by viewModel.filters.collectAsStateWithLifecycle()

	var showFiltersSheet by remember { mutableStateOf(false) }
	var draftTipo by remember { mutableStateOf("Todos") }
	var draftColor by remember { mutableStateOf("Todos") }
	var draftTemporada by remember { mutableStateOf("Todos") }
	var draftOcasion by remember { mutableStateOf("Todos") }

    Scaffold(
        topBar = {
            LookeoTopBar()
        },
        bottomBar = {
            LookeoBottomBar(
                selectedTab = "wardrobe",
                onNavigateToHome = onNavigateToHome,
                onNavigateToWardrobe = {},
                onNavigateToFavorites = onNavigateToFavorites,
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LookeoBlack)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Tu colección personal", color = LookeoGold, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 12.dp))
            Text("Mi Ropero", color = LookeoWhite, style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(bottom = 12.dp))
            Text("${items.size} prendas. Infinitas posibilidades.", color = LookeoGray, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 12.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNavigateToNewItem,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LookeoGold,
                    contentColor = LookeoBlack
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nueva Prenda")
            }

            Spacer(modifier = Modifier.height(16.dp))

			Button(
				onClick = {
					draftTipo = filters.tipo.toFilterLabel()
					draftColor = filters.color.toFilterLabel()
					draftTemporada = filters.temporada.toFilterLabel()
					draftOcasion = filters.ocasion.toFilterLabel()
					showFiltersSheet = true
				},
				colors = ButtonDefaults.buttonColors(
					containerColor = LookeoGold,
					contentColor = LookeoBlack,
				),
				modifier = Modifier.fillMaxWidth(),
			) {
				Text("Filtros")
			}

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNavigateToOutfits,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LookeoGold,
                    contentColor = LookeoBlack
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ir a mis outfits")
            }

            Spacer(modifier = Modifier.height(20.dp))

			if (items.isEmpty() && filters.hasActiveFilters) {
				Text(
					text = "No hay prendas con estos filtros",
					color = LookeoGray,
					style = MaterialTheme.typography.bodyMedium,
				)
			} else {
				LazyVerticalGrid(
					columns = GridCells.Fixed(2),
					verticalArrangement = Arrangement.spacedBy(12.dp),
					horizontalArrangement = Arrangement.spacedBy(12.dp),
					modifier = Modifier.fillMaxSize()
				) {
					items(items, key = { it.id }) { prenda ->
						PrendaCard(
							item = prenda,
							onClick = { onNavigateToItemDetail(prenda.id) },
						)
					}
				}
			}
        }
    }

	if (showFiltersSheet) {
		ModalBottomSheet(
			onDismissRequest = { showFiltersSheet = false },
			containerColor = LookeoBlack,
		) {
			Column(Modifier.padding(16.dp)) {
				Text("Filtros", color = LookeoWhite, style = MaterialTheme.typography.titleLarge)

				Spacer(Modifier.height(16.dp))

				DropdownField(
					label = "Tipo",
					selectedOption = draftTipo,
					options = listOf("Todos", "Camisa", "Pantalón", "Zapatos", "Abrigo"),
					onOptionSelected = { draftTipo = it },
					modifier = Modifier.fillMaxWidth(),
				)

				Spacer(Modifier.height(16.dp))

				DropdownField(
					label = "Color",
					selectedOption = draftColor,
					options = listOf("Todos", "Blanco", "Negro", "Azul", "Rojo"),
					onOptionSelected = { draftColor = it },
					modifier = Modifier.fillMaxWidth(),
				)

				Spacer(Modifier.height(16.dp))

				DropdownField(
					label = "Temporada",
					selectedOption = draftTemporada,
					options = listOf("Todos", "Primavera", "Verano", "Otoño", "Invierno"),
					onOptionSelected = { draftTemporada = it },
					modifier = Modifier.fillMaxWidth(),
				)

				Spacer(Modifier.height(16.dp))

				DropdownField(
					label = "Ocasión",
					selectedOption = draftOcasion,
					options = listOf("Todos", "Casual", "Formal", "Deportivo"),
					onOptionSelected = { draftOcasion = it },
					modifier = Modifier.fillMaxWidth(),
				)

				Spacer(Modifier.height(16.dp))

				Button(
					onClick = {
						viewModel.applyFilters(
							WardrobeFilters(
								tipo = draftTipo.toFilterValue(),
								color = draftColor.toFilterValue(),
								temporada = draftTemporada.toFilterValue(),
								ocasion = draftOcasion.toFilterValue(),
							)
						)
						showFiltersSheet = false
					},
					modifier = Modifier.fillMaxWidth(),
					colors = ButtonDefaults.buttonColors(containerColor = LookeoGold),
				) {
					Text("Aplicar", color = LookeoBlack)
				}

				Button(
					onClick = {
						viewModel.clearFilters()
						draftTipo = "Todos"
						draftColor = "Todos"
						draftTemporada = "Todos"
						draftOcasion = "Todos"
						showFiltersSheet = false
					},
					modifier = Modifier.fillMaxWidth(),
				) {
					Text("Limpiar filtros")
				}
			}
		}
	}
}

@Composable
fun PrendaCard(
	item: Item,
	onClick: () -> Unit,
) {
    Card(
		onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = LookeoBlack),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            if (!item.imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.tipo,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Text(
                text = item.tipo,
                color = LookeoWhite,
                style = MaterialTheme.typography.titleMedium,
            )
            if (item.color.isNotEmpty()) {
                Text(text = item.color, color = LookeoGold, style = MaterialTheme.typography.bodySmall)
            }
            if (item.temporada.isNotEmpty()) {
                Text(text = item.temporada, color = LookeoGray, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

private fun String?.toFilterLabel(): String = this ?: "Todos"
private fun String.toFilterValue(): String? = takeIf { it != "Todos" }