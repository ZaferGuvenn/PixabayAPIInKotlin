package com.lafimsize.mypixabaypicture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lafimsize.mypixabaypicture.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var pixabayAppFragmentFactory: PixabayAppFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.fragmentFactory=pixabayAppFragmentFactory



    }
}