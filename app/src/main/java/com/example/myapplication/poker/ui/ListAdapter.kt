package com.example.myapplication.poker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.poker.model.PlanetsModel
import com.example.myapplication.poker.R

class ListAdapter(planetsList: List<PlanetsModel.Result>): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var allPlanets: List<PlanetsModel.Result> = planetsList
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.list_item,
            viewGroup,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.tvName.text = allPlanets[position].name
        viewHolder.tvUrl.text = allPlanets[position].url
        viewHolder.tvId.text = allPlanets[position].uid
        viewHolder.itemView.tag =  allPlanets[position].uid
    }

    override fun getItemCount(): Int {
        return allPlanets.size
    }

    inner class MyViewHolder(view: View) : ViewHolder(view) {
        val tvName: TextView = view.findViewById<View>(R.id.tvName) as TextView
        val tvUrl: TextView = view.findViewById<View>(R.id.tvUrl) as TextView
        val tvId: TextView = view.findViewById<View>(R.id.tvId) as TextView

        init {
            view.setOnClickListener {
                val bundle = bundleOf("uid" to view.tag)
                Navigation.findNavController(it).navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
            }
        }
    }
}