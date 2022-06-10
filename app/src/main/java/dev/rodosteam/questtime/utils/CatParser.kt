package dev.rodosteam.questtime.utils

import org.json.JSONArray
import org.json.JSONTokener

fun parseCatImageURL(json: String): String {
    val URL = "url"
    return (JSONTokener(json).nextValue() as JSONArray)
        .getJSONObject(0)
        .getString(URL)
}