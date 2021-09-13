package com.ajdaakter.footballapp.ui.fixtureslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ajdaakter.footballapp.data.datarepository.DataRepository
import com.ajdaakter.footballapp.data.model.Match
import com.ajdaakter.footballapp.utilities.DataWrapper


class FixturesListViewModel(
    private val dataRepository: DataRepository
): ViewModel() {

    val filterNumber = MutableLiveData<Int>()
    val fixturesList: LiveData<DataWrapper<List<Match>>> = Transformations.switchMap(filterNumber) {
        when(it) {
            REMOTE_FIXTURES -> dataRepository.getFixturesList()
            else -> throw IllegalArgumentException("Unknown filter type")
        }
    }

    init {
        getRemoteFixtures()
    }

    fun getRemoteFixtures() {
        filterNumber.value = REMOTE_FIXTURES
    }



    companion object {
        const val REMOTE_FIXTURES = 1

    }
}