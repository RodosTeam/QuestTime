package dev.rodosteam.questtime.utils

import org.json.JSONArray
import org.json.JSONObject

fun JSONArray.asIterableOfJSONObjects(): Iterable<JSONObject> =
    (0 until length()).map { getJSONObject(it) }