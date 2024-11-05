package com.zosimadis.network.retrofit

import com.zosimadis.network.model.NetworkBookResource
import kotlinx.serialization.Serializable
import retrofit2.http.GET

internal interface ViesureApi {

    @GET(".")
    suspend fun getBooks(): List<NetworkBookResource>
}

@Serializable
internal data class NetworkResponse<T>(val data: T)
