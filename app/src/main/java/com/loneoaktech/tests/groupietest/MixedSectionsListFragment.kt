package com.loneoaktech.tests.groupietest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.loneoaktech.tests.groupietest.data.DogData
import com.loneoaktech.tests.groupietest.databinding.FragmentMainBinding
import com.loneoaktech.tests.groupietest.items.SimpleItem
import com.loneoaktech.tests.groupietest.items.SubHeadingItem
import com.loneoaktech.tests.groupietest.items.TopHeaderItem
import com.loneoaktech.tests.groupietest.utils.lazyViewBinding
import com.loneoaktech.tests.groupietest.utils.withViews
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.random.Random

class MixedSectionsListFragment : Fragment() {

    companion object {
        const val MAIN_TITLE = "Our Favorite Breeds"
        val HEADINGS = listOf(
            "Most Favorite" to 5,
            "Kind of Favorite" to 3,
            "Not So Much" to 6,
            "Are you kidding" to 2,
        )
    }

    private val holder = lazyViewBinding { FragmentMainBinding.inflate(layoutInflater) }
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val sections = List( HEADINGS.size) { Section() }
    private val subHeadings = HEADINGS.map { (title, _) ->
        SubHeadingItem(title)
    }
    private val topItem = TopHeaderItem(MAIN_TITLE)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return holder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        holder.withViews {
            groupList.adapter = groupAdapter
            groupList.layoutManager = LinearLayoutManager(requireContext())
        }

        with(groupAdapter) {
            add(topItem)
            sections.indices.forEach { i ->
                add( subHeadings[i] )
                add( sections[i])
            }
        }

        loadData()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                while(true) {
                    delay(3_000L)
                    loadData()
                }
            }
        }
    }

    private fun loadData( ) {
        sections.forEachIndexed { ndx, section ->
            val (_, num) = HEADINGS[ndx]
            section.update(
                DogData.breeds.takeRandomSet( Random.nextInt(1,6))
                    .map { SimpleItem(it.name, 4) }
            )
        }
    }


}

fun <T> List<T>.takeRandomSet( num: Int): List<T> =
    List( num ) { Random.nextInt(size) }.map { this[it] }