package com.kks.codingtest.data.models

import kotlinx.serialization.*

@Serializable
data class MovieListModel (
        val page: Long? = 0L,
        val results: List<ResultModel>,
)


