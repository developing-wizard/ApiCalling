package com.example.apicalling.userRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalling.R
import com.example.apicalling.datamodels.PostingData

class FilterRecycler(context : Context, var filterList : ArrayList<PostingData>): RecyclerView.Adapter<FilterRecycler.filterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterRecycler.filterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.post_item,
            parent, false
        )
        return FilterRecycler.filterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilterRecycler.filterViewHolder, position: Int) {
//        holder.filtertitle.text = filterList[position].title
        holder.filteruserId.text = filterList[position].userId.toString()
//        holder.idfilters.text = filterList[position].id.toString()
//        holder.filterbody.text = filterList[position].body

    }


    override fun getItemCount(): Int {
        return filterList.size
    }


    class filterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filteruserId : TextView = itemView.findViewById(R.id.useridfilter)
        val idfilters : TextView = itemView.findViewById(R.id.idfilter)
        val filtertitle : TextView = itemView.findViewById(R.id.titlefilter)
        val filterbody : TextView = itemView.findViewById(R.id.bodyfilter)
    }

}