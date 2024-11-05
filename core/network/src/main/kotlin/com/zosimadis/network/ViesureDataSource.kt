package com.zosimadis.network

import com.zosimadis.network.model.NetworkBookResource

interface ViesureDataSource {
    suspend fun getBooks(): List<NetworkBookResource>
}
