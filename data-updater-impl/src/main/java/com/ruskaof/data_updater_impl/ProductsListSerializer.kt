package com.ruskaof.data_updater_impl

import androidx.datastore.core.Serializer
import com.ruskaof.data_updater_api.model.DataStoreListHolder
import com.ruskaof.data_updater_api.model.ProductInListSaveObject
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object ProductsListSerializer : Serializer<DataStoreListHolder<ProductInListSaveObject>> {
    override val defaultValue: DataStoreListHolder<ProductInListSaveObject>
        get() = DataStoreListHolder()

    override suspend fun readFrom(input: InputStream): DataStoreListHolder<ProductInListSaveObject> {
        return try {
            Json.decodeFromString(
                deserializer = DataStoreListHolder.serializer(ProductInListSaveObject.serializer()),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(
        t: DataStoreListHolder<ProductInListSaveObject>,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(
                serializer = DataStoreListHolder.serializer(ProductInListSaveObject.serializer()),
                value = t
            ).encodeToByteArray()
        )
    }


}