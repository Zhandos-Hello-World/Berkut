package kz.cicada.berkut.feature.tasks.data.network

import kz.cicada.berkut.feature.tasks.data.model.TaskDtoRequest
import kz.cicada.berkut.feature.tasks.data.model.TaskDtoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TaskApiService {

    @POST("/task/{child_id}")
    suspend fun addTask(
        @Path("child_id") childId: Int,
        @Body request: TaskDtoRequest,
    )

    @HTTP(
        path = "/task/{child_id}",
        method = "DELETE",
        hasBody = false,
    )
    suspend fun deleteTask(
        @Path("child_id") childId: Int,
        @Query("task-id") taskId: Int,
    )

    @GET("/task/{child_id}")
    suspend fun getTasks(
        @Path("child_id") childId: Int,
    ): List<TaskDtoResponse>


    @PUT("/task/status/{task-id}")
    suspend fun changeStatus(
        @Path("task-id") taskId: Int,
        @Query("status") status: String,
    )
}