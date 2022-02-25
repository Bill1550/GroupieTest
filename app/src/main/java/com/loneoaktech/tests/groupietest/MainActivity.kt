package com.loneoaktech.tests.groupietest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.loneoaktech.tests.groupietest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Pages( val creator: ()->Fragment) {
        SIMPLE_DYNAMIC( { SimpleDynamicFragment() } ),
        MIXED_LIST( { MixedListFragment() }),
        MIXED_SECTIONS( { MixedSectionsListFragment() }),
        SECTIONS_LIST( { SectionsListFragment() })
    }

    private val viewBinding by lazy { ActivityMainBinding.inflate( LayoutInflater.from(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( viewBinding.root)

        with(viewBinding) {
            viewPager.adapter = ScreenSlidePagerAdapter(this@MainActivity)
        }

//        savedInstanceState?:let {
//
//            supportFragmentManager
//                .beginTransaction()
//                .add( R.id.fragmentHolder, MixedSectionsListFragment(), "main")
//                .commit()
//        }
    }

    override fun onBackPressed() {
        with( viewBinding.viewPager ) {
            if (currentItem == 0) {
                // If the user is currently looking at the first step, allow the system to handle the
            } else {
                currentItem = currentItem - 1
            }
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = Pages.values().size

        override fun createFragment(position: Int): Fragment =
            Pages.values()[position.coerceIn(Pages.values().indices)].creator.invoke()
    }
}