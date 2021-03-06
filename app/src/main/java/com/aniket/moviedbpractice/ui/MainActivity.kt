package com.aniket.moviedbpractice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.aniket.moviedbpractice.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            add(R.id.container, MovieListFragment(), null)
        }
    }

    override fun onBackPressed() {
        val myFragment: Fragment? =
            supportFragmentManager.findFragmentByTag(MovieDetailFragment.FRAGMENT_TAG)
        if (myFragment is MovieDetailFragment && myFragment.isVisible()) {
            supportFragmentManager.popBackStack()
        } else
            super.onBackPressed()
    }
}
