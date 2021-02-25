package com.newton.zone.model

class QueryCreatorFilter() {

    fun returnByParams(params: HashMap<String, String>, table: String): String {
        var query = "SELECT * FROM $table"

        if (params.isNotEmpty()) {
            query += " WHERE "
            params.forEach { map ->

                query += if (map.key == @Params TPV) {
                    "${map.key} ${map.value} AND "
                } else {
                    "${map.key} = '${map.value}' AND "
                }
            }
        }
        return removeLastAnd(query, params)
    }

    private fun removeLastAnd(query: String, params: java.util.HashMap<String, String>): String {
        return if (params.isNotEmpty()) {
            query.substring(0, query.length - 5)
        } else query
    }
}