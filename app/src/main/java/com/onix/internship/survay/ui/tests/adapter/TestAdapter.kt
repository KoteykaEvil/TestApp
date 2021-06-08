package com.onix.internship.survay.ui.tests.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.db.local.tables.tests.Test
import kotlinx.android.synthetic.main.test_item.view.*

class TestAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    var list = emptyList<Test>()

    fun update(list: List<Test>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: Test) {
            itemView.item_title.text = model.name
            itemView.item_description.text = model.description
            itemView.setOnClickListener { itemClickListener.onClick(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.test_item, parent, false)
        return TestViewHolder(view.rootView)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    interface ItemClickListener {
        fun onClick(model: Test)
    }
}