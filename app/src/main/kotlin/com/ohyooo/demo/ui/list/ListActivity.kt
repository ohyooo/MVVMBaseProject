package com.ohyooo.demo.ui.list

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ohyooo.demo.databinding.ActivityListBinding
import com.ohyooo.demo.viewmodel.MainViewModel
import com.ohyooo.lib.extension.viewModelOf

class ListActivity : ComponentActivity() {

    private val vm: MainViewModel by viewModelOf()
    private val vdb by lazy { ActivityListBinding.inflate(layoutInflater).also { it.vm = vm } }

    private val adapter = AA()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vdb.root)

        initData()
        initViews()
    }

    private fun initData() {
    }

    private fun initViews() {
        vdb.list.adapter = adapter
    }

    private class AA : RecyclerView.Adapter<VH<TextView>>() {
        private val list = ArrayList<String>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH<TextView> {
            val tv = TextView(parent.context).apply {
                layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                setPadding(20, 20, 20, 0)
            }
            return VH(tv)
        }

        override fun onBindViewHolder(vh: VH<TextView>, position: Int) {
            vh.v.text = list[position]
        }

        override fun getItemCount() = list.size

        fun update(data: List<String>) {
            list.clear()
            list.addAll(data)
            notifyDataSetChanged()
        }
    }

    private class VH<V : TextView>(val v: V) : RecyclerView.ViewHolder(v)
}