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
import com.loneoaktech.tests.groupietest.databinding.FragmentMainBinding
import com.loneoaktech.tests.groupietest.items.SimpleItem
import com.loneoaktech.tests.groupietest.utils.lazyViewBinding
import com.loneoaktech.tests.groupietest.utils.withViews
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class SimpleDynamicFragment : Fragment() {

    private val holder = lazyViewBinding { FragmentMainBinding.inflate(layoutInflater) }
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private val rawData = listOf(
        "This is item 1",
        "this is item 2",
        "this another item, call it 3",
        "Now for a another item",
        "And yet one more",
        "Now for item 6",
        "Again it's item 7",
        "and finally item 8"
    )


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
        loadData(null)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                while(true) {
                    delay(2_000L)
                    val skip = rawData.indices.random()
                    Timber.i("Skipping $skip")
                    loadData( skip = skip)
                    repeat(7) {
                        delay(800L)
                        updateLevels()
                    }
                }
            }
        }
    }



    private fun loadData( skip: Int? ) {
        groupAdapter.update(
            rawData.mapIndexedNotNull {i, t ->
                if (i != skip)
                    SimpleItem(t, i)
                else
                    null
            }
        )
    }

    private fun updateLevels() {
//        val updates = groupAdapter.indices().mapNotNull { groupAdapter.getItem(it) as? SimpleItem }
//            .map { SimpleItem(it.title, (it.level+1) % 7) }
//        groupAdapter.update(updates)
        groupAdapter.indices().map { pos ->
            (groupAdapter.getItem(pos) as? SimpleItem)?.let { item ->
                item.level = (item.level+1) % SimpleItem.NUM_LEVELS
                groupAdapter.notifyItemChanged(pos)
            }
        }
    }
}

fun GroupAdapter<*>.indices(): IntRange =
    0 until itemCount