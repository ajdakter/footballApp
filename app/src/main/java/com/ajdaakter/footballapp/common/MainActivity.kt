package com.ajdaakter.footballapp.common

import kotlinx.android.synthetic.main.fragment_fixtures_list.*

import android.app.Application
import android.content.Context
import com.ajdaakter.footballapp.data.datarepository.DataRepository
import com.ajdaakter.footballapp.data.datarepository.source.local.database.AppDatabase
import com.ajdaakter.footballapp.data.datarepository.source.remote.network.ApiFactory
import com.ajdaakter.footballapp.data.datarepository.source.remote.network.ApiInterface
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import androidx.multidex.MultiDex

class MyApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))



        bind<DataRepository>() with singleton { DataRepository(kodein) }

        bind<ViewModelProviderFactory>() with provider { ViewModelProviderFactory(kodein) }

        bind<AppDatabase>() with singleton { AppDatabase.getInstance(this@MyApplication) }

        bind<ApiInterface>() with singleton { ApiFactory.footballDataApi }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}