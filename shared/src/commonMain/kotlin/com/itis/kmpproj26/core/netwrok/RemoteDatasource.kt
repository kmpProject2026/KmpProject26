package com.itis.kmpproj26.core.netwrok

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class RemoteDatasource(val httpClient: HttpClient) {

    suspend fun getUser(name: String): UserResponse {
        return httpClient.get("v1/user") {
            parameter("test", name)
        }.body()
    }
}

@Serializable
data class UserResponse(
    @SerialName("user_name")
    val name: String? = null
)