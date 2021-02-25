package com.newton.zone.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.FragmentActivity
import com.newton.zone.R
import com.newton.zone.model.*
import com.newton.zone.view.dialog.FilterDialog.Constant.BETWEEN
import com.newton.zone.view.dialog.FilterDialog.Constant.MINUS
import com.newton.zone.view.dialog.FilterDialog.Constant.MORE
import kotlinx.android.synthetic.main.dialog_filter.*

class FilterDialog(
    val context: Context,
    var loadNameEc: (nameEcAutoComplete: AutoCompleteTextView) -> Unit = {},
    var loadAddress: (addressEcAutoComplete: AutoCompleteTextView) -> Unit = {},
    var returnQuery: (query: String) -> Unit = {}
) {
    private val view = Dialog(context)
    private val itemsDropdownSegment by lazy {
        listOf(
            @Segment
            TECHNOLOGY,
            RESTAURANT,
            DELIVERY,
            MARKETPLACE,
            ORDER,
            LIBRARY,
            ENTERPRISE,
            OTHERS
        )
    }
    private val itemsDropdownTPV by lazy {
        listOf(
            MINUS,
            BETWEEN,
            MORE
        )
    }

    fun showFilterDialog() {
        with(view) {
            setContentView(R.layout.dialog_filter)
            window?.attributes!!.gravity = Gravity.BOTTOM
            window?.setBackgroundDrawable(ColorDrawable(TRANSPARENT))
            show()

            loadNameEc(view.autoCompleteTextView_filter_name_edit)
            loadAddress(view.autoCompleteTextView_filter_address_edit)
            loadSegmentFiled()
            loadTPVField()
            saveBtnListeners()
        }
    }

    private fun loadTPVField() {
        val adapter = ArrayAdapter(
            context,
            R.layout.list_item,
            itemsDropdownTPV
        )
        (view.dropDownTextView_filter_cluster_tpv.editText as? AutoCompleteTextView)?.setAdapter(
            adapter
        )
    }

    private fun loadSegmentFiled() {
        val adapter = ArrayAdapter(
            context,
            R.layout.list_item,
            itemsDropdownSegment
        )
        (view.dropDownTextView_filter_segment.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun Dialog.saveBtnListeners() {
        dialog_filter_save_btn.setOnClickListener {
            val queryCreatorFilter = QueryCreatorFilter()
            val params = loadAndReturnParams(view)
            val query = queryCreatorFilter.returnByParams(params, "BUSINESS")
            returnQuery(query)
            view.dismiss()
            Log.i("query", query)
        }
    }

    private fun loadAndReturnParams(view: Dialog): HashMap<String, String> {
        val params = hashMapOf<String, String>()
        if (view.autoCompleteTextView_filter_name_edit.text.toString().trim().isNotEmpty()) {
            params[@Params NAME] =
                view.autoCompleteTextView_filter_name_edit.text.toString().trim()
        }
        if (view.autoCompleteTextView_filter_address_edit.text.toString().trim().isNotEmpty()) {
            params[@Params ADDRESS] =
                view.autoCompleteTextView_filter_address_edit.text.toString().trim()
        }
        if (view.dropDownTextView_filter_segment_edit.text.toString().trim().isNotEmpty()) {
            params[@Params SEGMENT] =
                view.dropDownTextView_filter_segment_edit.text.toString().trim()
        }
        if (view.dropDownTextView_filter_cluster_tpv_edit.text.toString().trim().isNotEmpty()) {
            val tpv = when {
                view.dropDownTextView_filter_cluster_tpv_edit.text.toString().trim() == MINUS -> {
                    "< '10000'"
                }
                view.dropDownTextView_filter_cluster_tpv_edit.text.toString().trim() == MORE -> {
                    "> '20000'"
                }
                else -> { ">= '10000' AND tpv <= '20000'" }
            }
            params[@Params TPV] = tpv
        }
        return params
    }

    private object Constant {
        const val MINUS = "< 10K"
        const val BETWEEN = "10-20k"
        const val MORE = "> 20k"
    }
}