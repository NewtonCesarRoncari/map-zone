package com.newton.zone.view.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.newton.zone.R
import com.newton.zone.extension.formatForBrazilianDate
import com.newton.zone.extension.formatForBrazilianDateDay
import com.newton.zone.extension.formatForBrazilianDateMonth
import com.newton.zone.extension.formatForBrazilianHour
import com.newton.zone.model.Visit
import kotlinx.android.synthetic.main.list_item_visit.view.*
import java.util.*

class VisitAdapter(
    private val context: Context,
    private var visits: MutableList<Visit>,
): RecyclerView.Adapter<VisitAdapter.MyViewHolder>(), Filterable {

    private val visitsFullList = visits.toList()

    //regionFilter
    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<Visit> = mutableListOf()

            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(visitsFullList)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()

                for (visit in visitsFullList) {
                    if (visit.name.toLowerCase(Locale.getDefault()).contains(filterPattern)
                        || visit.address.toLowerCase(Locale.getDefault()).contains(filterPattern)
                        || visit.segment.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                        filteredList.add(visit)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            visits = (results.values as List<*>).filterIsInstance<Visit>() as MutableList<Visit>
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return filter
    }
    //endregion

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
        private val month by lazy { itemView.item_visit_month }
        private val hour by lazy { itemView.item_visit_hour }
        private val segment by lazy { itemView.item_visit_segment }
        private val name by lazy { itemView.item_visit_name_ec }
        private val obs by lazy { itemView.item_visit_obs }

        fun bind(visit: Visit) {
            date.text = visit.date.formatForBrazilianDateDay()
            month.text = visit.date.formatForBrazilianDateMonth()
            hour.text = visit.hour.formatForBrazilianHour()
            segment.text = visit.segment
            name.text = visit.name.toUpperCase(Locale.ROOT)
            obs.text = visit.observation
        }
    }
}