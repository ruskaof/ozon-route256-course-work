package com.ruskaof.data_updater_api.model

import kotlinx.serialization.Serializable

@Serializable
data class DataStoreListHolder<T>(
    val list: List<T> = listOf()
)
