package com.newton.zone.view.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newton.zone.R
import com.newton.zone.extension.formatForBrazilianDate
import com.newton.zone.extension.formatForBrazilianHour
import com.newton.zone.model.Visit
import kotlinx.android.synthetic.main.list_item_visit.view.*

class VisitAdapter(
    private val context: Context,
    private val visits: MutableList<Visit>,
): RecyclerView.Adapter<VisitAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.list_item_visit,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(visits[position])
    }

    override fun getItemCount() = visits.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val date by lazy { itemView.item_visit_date }
        private val hour by lazy { itemView.item_visit_hour }
        private val segment by lazy { itemView.item_visit_segment }
        private val name by lazy { itemView.item_visit_name_ec }
        private val obs by lazy { itemView.item_visit_obs }

        fun bind(visit: Visit) {
            date.text = visit.date.formatForBrazilianDate()
            hour.text = visit.hour.formatForBrazilianHour()
            segment.text = visit.segment
            name.text = visit.name
            obs.text = visit.observation
        }
    }
}