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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class MixedListFragment : Fragment() {

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

        groupAdapter.clear()
        loadData()

    }

    private fun loadData( ) {

        var totalUsed = 0
        val items = listOf( TopHeaderItem(MAIN_TITLE)) +
                HEADINGS.flatMap { (title, num) ->
                    listOf( SubHeadingItem(title) )  +
                            DogData.breeds.drop(totalUsed).take(num)
                                .map { SimpleItem(it.name, 4) }
                                .also { totalUsed += num }
                }

        groupAdapter.update(items)
    }
}
