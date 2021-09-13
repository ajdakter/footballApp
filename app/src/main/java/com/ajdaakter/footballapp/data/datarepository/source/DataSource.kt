package com.ajdaakter.footballapp.data.datarepository.source

import androidx.lifecycle.LiveData
import com.ajdaakter.footballapp.data.model.Match
import com.ajdaakter.footballapp.utilities.DataWrapper

interface DataSource {

    fun getFixturesList(): LiveData<DataWrapper<List<Match>>>

    fun getFavoriteFixturesList(): LiveData<DataWrapper<List<Match>>>

    fun toggleFixtureFavoriteState(match: Match, isFavorite: Boolean)

}