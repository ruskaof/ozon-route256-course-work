package com.ruskaof.data_updater_impl

import androidx.datastore.core.Serializer
import com.ruskaof.data_updater_api.model.DataStoreListHolder
import com.ruskaof.data_updater_api.model.ProductInfoSaveObject
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object ProductsInfoSerializer : Serializer<DataStoreListHolder<ProductInfoSaveObject>> {
    override val defaultValue: DataStoreListHolder<ProductInfoSaveObject>
        get() = DataStoreListHolder()

    override suspend fun readFrom(input: InputStream): DataStoreListHolder<ProductInfoSaveObject> {
        return try {
            Json.decodeFromString(
                deserializer = DataStoreListHolder.serializer(ProductInfoSaveObject.serializer()),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(
        t: DataStoreListHolder<ProductInfoSaveObject>,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(
                serializer = DataStoreListHolder.serializer(ProductInfoSaveObject.serializer()),
                value = t
            ).encodeToByteArray()
        )
    }


}