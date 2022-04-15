package com.example.diary.ui.components.diary

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R
import com.example.diary.models.DataEntry

class DataEntryAdapter(var context: Context) : RecyclerView.Adapter<DataEntryAdapter.ViewHolder>() {

    private var dataList = emptyList<DataEntry>()

    internal fun setDataList(dataList: List<DataEntry>) {
        this.dataList = dataList
    }

    // Provide a direct reference to each of the views with data items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.titleText)
        var desc: TextView = itemView.findViewById(R.id.diaryText)

        init {
            desc.movementMethod = ScrollingMovementMethod()
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Inflate the custom layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_list_layout, parent, false)
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get the data model based on position
        val data = dataList[position]

        // Set item views based on your views and data model
        holder.title.text = data.title
        holder.desc.text = data.data
    }

    //  total count of items in the list
    override fun getItemCount() = dataList.size
}