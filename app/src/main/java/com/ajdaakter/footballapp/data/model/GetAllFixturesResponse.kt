package com.ajdaakter.footballapp.data.model

data class GetAllFixturesResponse(
    val competition: Competition,
    val count: Int,
    val matches: List<Match>
)