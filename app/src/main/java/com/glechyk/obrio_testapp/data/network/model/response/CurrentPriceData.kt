package com.glechyk.obrio_testapp.data.network.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CurrentPriceData(
    val bpi: BpiData?,
    val chartName: String?,
    val disclaimer: String?,
    val time: CurrentPriceTimeData?
)

@Keep
data class BpiData(
    @SerializedName("EUR")
    val eur: EURData?,
    @SerializedName("GBP")
    val gbp: GBPData?,
    @SerializedName("USD")
    val usd: USDData?
)

@Keep
data class EURData(
    val code: String?,
    val description: String?,
    val rate: String?,
    @SerializedName("rate_float")
    val rateFloat: Double?,
    val symbol: String?
)

@Keep
data class GBPData(
    val code: String?,
    val description: String?,
    val rate: String?,
    @SerializedName("rate_float")
    val rateFloat: Double?,
    val symbol: String?
)

@Keep
data class USDData(
    val code: String?,
    val description: String?,
    val rate: String?,
    @SerializedName("rate_float")
    val rateFloat: Double?,
    val symbol: String?
)

@Keep
data class CurrentPriceTimeData(
    val updated: String?,
    val updatedISO: String?,
    @SerializedName("updateduk")
    val updateDuk: String?
)
