package com.ajdaakter.footballapp.ui.fixtureslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.airbnb.lottie.LottieAnimationView
import com.ajdaakter.footballapp.R
import kotlinx.android.synthetic.main.fragment_fixtures_list.*

class FixturesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixtures_list)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
       delegate.applyDayNight()


        //soccer_loading.setAnimation("soccer_loading.json")
      //  soccer_loading.setVisibility(LottieAnimationView.INVISIBLE);

    }
}
