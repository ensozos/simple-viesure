package com.zosimadis.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.zosimadis.network.ViesureDataSource
import com.zosimadis.network.model.NetworkBookResource
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://mpa68396c1d2046f93e0.free.beeceptor.com/"

@Singleton
internal class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : ViesureDataSource {

    private val viesureApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(ViesureApi::class.java)

    override suspend fun getBooks(): List<NetworkBookResource> {
        return viesureApi.getBooks()
    }
}
