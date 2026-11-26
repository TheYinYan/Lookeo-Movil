package com.alanturing.lookeo_movil.features.items.data.remote

import com.alanturing.lookeo_movil.features.items.data.remote.model.CreateItemBody
import com.alanturing.lookeo_movil.features.items.data.remote.model.ItemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LookeoItemsApi {

    @GET("api/items")
    suspend fun getMyItems(): Response<List<ItemResponse>>

    @POST("api/items")
    suspend fun createItem(@Body body: CreateItemBody): Response<ItemResponse>

	@PUT("api/items/{id}")
	suspend fun updateItem(
		@Path("id") id: Int,
		@Body body: CreateItemBody
	): Response<ItemResponse>
}
