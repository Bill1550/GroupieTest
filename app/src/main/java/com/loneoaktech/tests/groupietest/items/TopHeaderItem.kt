package com.loneoaktech.tests.groupietest.items

import android.view.View
import com.loneoaktech.tests.groupietest.R
import com.loneoaktech.tests.groupietest.databinding.ItemTopHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class TopHeaderItem(
    val title: String
): BindableItem<ItemTopHeaderBinding>() {

    override fun initializeViewBinding(view: View): ItemTopHeaderBinding =
        ItemTopHeaderBinding.bind(view)

    override fun getLayout(): Int = R.layout.item_top_header

    override fun bind(viewBinding: ItemTopHeaderBinding, position: Int) {
        with(viewBinding) {
            titleView.text = title
        }

    }
}