package com.aminovic.lenador.domain.utils

import android.content.Context
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.squareup.moshi.JsonClass
import java.lang.reflect.Type

@JsonClass(generateAdapter = true)
sealed class UiText private constructor() {
    data class DynamicString(val text: String, val extra: List<String> = emptyList()) : UiText()
    data class StringResource(val resId: Int, val extra: List<String> = emptyList()) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> text
            is StringResource -> {
                if (extra.isEmpty())
                    context.getString(resId)
                else
                    context.getString(resId, extra.first())
            }
        }
    }
}
