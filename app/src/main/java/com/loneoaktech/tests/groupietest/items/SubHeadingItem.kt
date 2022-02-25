package com.loneoaktech.tests.groupietest.items

import android.view.View
import com.loneoaktech.tests.groupietest.R
import com.loneoaktech.tests.groupietest.databinding.ItemSubHeadingBinding
import com.loneoaktech.tests.groupietest.databinding.ItemTopHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class SubHeadingItem(
    val title: String
): BindableItem<ItemSubHeadingBinding>() {

    override fun initializeViewBinding(view: View): ItemSubHeadingBinding =
        ItemSubHeadingBinding.bind(view)

    override fun getLayout(): Int = R.layout.item_sub_heading

    override fun bind(viewBinding: ItemSubHeadingBinding, position: Int) {
        with(viewBinding) {
            titleView.text = title
        }
    }
}