package com.newton.zone.view.recyclerview.adapter

import android.content.Context
import android.view.*
import android.view.View.GONE
import android.widget.PopupMenu
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.newton.zone.R
import com.newton.zone.extension.formatCoin
import com.newton.zone.extension.limit
import com.newton.zone.model.Business
import com.newton.zone.model.CLIENT
import com.newton.zone.model.Type
import com.newton.zone.view.recyclerview.adapter.BusinessAdapter.Constant.MAX_CHARACTER
import kotlinx.android.synthetic.main.list_item_client_lead.view.*
import java.util.*

class BusinessAdapter(
    private val context: Context,
    private var businessList: MutableList<Business>,
    var onItemClickPopupSend: (business: Business) -> Unit = {},
    var onItemClickPopupTurnClient: (business: Business) -> Unit = {},
    var onItemClickPopupVisit: (business: Business) -> Unit = {},
    var onItemLongClickListener: (business: Business) -> Unit = {}
) : RecyclerView.Adapter<BusinessAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.list_item_client_lead,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(businessList[position])
    }

    override fun getItemCount() = businessList.size

    fun remove(position: Int) {
        checkHavePositionInList(position)
        businessList.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun checkHavePositionInList(position: Int) {
        if (position < 0 || position > businessList.size) throw IndexOutOfBoundsException()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val colorBar by lazy { itemView.item_business_id }
        private val name by lazy { itemView.list_item_name_ec }
        private val textTag by lazy { itemView.txtTag }
        private val address by lazy { itemView.list_item_address }
        private val segment by lazy { itemView.list_item_segment }
        private val tpv by lazy { itemView.list_item_tpv }
        private val btnViewOptions: AppCompatImageView by lazy { itemView.list_item_option }
        private lateinit var business: Business

        fun bind(business: Business) {
            this.business = business
            whenBusinessIsClient()
            name.text = business.name.toUpperCase(Locale.ROOT)
            textTag.text = business.name[0].toString().toUpperCase(Locale.ROOT)
            address.text = business.address
            segment.text = business.segment
            tpv.text = business.tpv.formatCoin(context)
        }

        private fun whenBusinessIsClient() {
            if (business.type == @Type CLIENT) {
                btnViewOptions.visibility = GONE
                colorBar.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
            }
        }

        init {
            btnViewOptions.setOnClickListener { initOptionPopup() }
            initContextMenu(itemView, onItemLongClickListener)
        }

        private fun initOptionPopup() {
            val popup = PopupMenu(context, btnViewOptions)
            popup.inflate(R.menu.list_options_item_menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.list_item_options_send -> {
                        onItemClickPopupSend(business)
                    }
                    R.id.list_item_options_turn_client -> {
                        onItemClickPopupTurnClient(business)
                    }
                    R.id.list_item_options_visit -> {
                        onItemClickPopupVisit(business)
                    }
                }
                false
            }
            popup.show()
        }

        private fun initContextMenu(
            itemView: View,
            onItemLongClickListener: (business: Business) -> Unit
        ) {
            itemView.setOnCreateContextMenuListener { menu: ContextMenu,
                                                      _: View?,
                                                      _: ContextMenu.ContextMenuInfo? ->
                MenuInflater(context).inflate(R.menu.remove_context_menu, menu)
                menu.findItem(R.id.remove)
                    .setOnMenuItemClickListener {
                        onItemLongClickListener(businessList[adapterPosition])
                        remove(adapterPosition)
                        true
                    }
            }
        }
    }

    private object Constant {
        const val MAX_CHARACTER = 28
    }

}
