package com.code.myapplication.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.code.myapplication.domain.entities.MostViewData
import com.code.myapplication.databinding.PopularViewedUsersItemsBinding


class MostViewedAdapter(
    var item: ArrayList<MostViewData>, val context: Context
) : RecyclerView.Adapter<MostViewedAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PopularViewedUsersItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun getItemCount(): Int {
        return item.size
    }

    class ViewHolder(binding: PopularViewedUsersItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val mostViewedBinding: PopularViewedUsersItemsBinding = binding

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: MostViewData = item[position]
        holder.mostViewedBinding.txtTitle.text = model.title
        holder.mostViewedBinding.txtJoinDate.text = model.publishDate
        val imageUri = model.imageUrl
        Glide.with(context).load(imageUri).into(holder.mostViewedBinding.ivUserPic)
    }


}