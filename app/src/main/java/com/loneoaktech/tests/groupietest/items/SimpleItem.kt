package com.loneoaktech.tests.groupietest.items

import android.view.View
import com.loneoaktech.tests.groupietest.R
import com.loneoaktech.tests.groupietest.databinding.ItemSimpleBinding
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class SimpleItem(
    val title: String,
    var level: Int,
): BindableItem<ItemSimpleBinding>() {

    companion object {
        private val icons = listOf(
            R.drawable.ic_baseline_battery_0_bar_24,
            R.drawable.ic_baseline_battery_1_bar_24,
            R.drawable.ic_baseline_battery_2_bar_24,
            R.drawable.ic_baseline_battery_3_bar_24,
            R.drawable.ic_baseline_battery_4_bar_24,
            R.drawable.ic_baseline_battery_5_bar_24,
            R.drawable.ic_baseline_battery_6_bar_24,
            R.drawable.ic_baseline_battery_full_24
        )

        val NUM_LEVELS = icons.size
    }

    override fun initializeViewBinding(view: View): ItemSimpleBinding = ItemSimpleBinding.bind(view)

    override fun getLayout(): Int = R.layout.item_simple

    override fun bind(viewBinding: ItemSimpleBinding, position: Int) {
        with(viewBinding) {
            titleView.text = title
            iconView.setImageResource( icons[ level.coerceIn(icons.indices) ])
        }
    }

    override fun isSameAs(other: Item<*>): Boolean {
        return (other as? SimpleItem)?.title == this.title
    }


}