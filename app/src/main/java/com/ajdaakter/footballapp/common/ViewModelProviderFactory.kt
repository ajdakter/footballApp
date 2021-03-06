package com.ajdaakter.footballapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajdaakter.footballapp.data.datarepository.DataRepository
import com.ajdaakter.footballapp.ui.fixtureslist.FixturesListViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ViewModelProviderFactory(override val kodein: Kodein): ViewModelProvider.NewInstanceFactory(), KodeinAware {

    val dataRepository: DataRepository by instance()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(FixturesListViewModel::class.java) -> {
                return FixturesListViewModel(dataRepository) as T
            }
        }

        throw IllegalArgumentException("Unknown view model class: " + modelClass.name)
    }
}