package com.glechyk.obrio_testapp.data.network

import com.glechyk.obrio_testapp.data.network.model.response.CurrentPriceData
import retrofit2.Response
import retrofit2.http.GET

interface BitcoinsApi {

    @GET(CURRENT_PRICE_URL)
    suspend fun getCurrentPrice(): Response<CurrentPriceData>
}