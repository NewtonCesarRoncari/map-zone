package com.newton.zone.model

class QueryCreatorFilter() {

    fun returnByParams(params: HashMap<String, String>, table: String): String {
        var query = "SELECT * FROM $table WHERE 1 = 1"

        if (params.isNotEmpty()) {
            params.forEach { map ->
                query += if (map.key == @Params TPV) {
                    " AND ${map.key} ${map.value}"
                } else {
                    " AND ${map.key} = '${map.value}'"
                }
            }
        }
        return query
    }
}