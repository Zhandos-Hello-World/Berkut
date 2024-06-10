package kz.cicada.berkut.feature.children.data.network

import kz.cicada.berkut.feature.children.data.model.ChildrenResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ChildrenApi {

    @GET("/parents/children/{parent_id}")
    suspend fun getChildren(
        @Path("parent_id") parentId: Int,
    ): List<ChildrenResponse>

    @GET("/users/{id}")
    suspend fun getProfilePhoto(
        @Path("id") id: Int,
    ): String
}