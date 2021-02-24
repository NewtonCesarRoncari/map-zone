package com.newton.zone.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.newton.zone.R
import com.newton.zone.model.*
import kotlinx.android.synthetic.main.dialog_filter.*

class FilterDialog(
    context: Context,
    private val activity: FragmentActivity,
    var loadNameEc: (nameEcAutoComplete: AutoCompleteTextView) -> Unit = {},
    var loadAddress: (addressEcAutoComplete: AutoCompleteTextView) -> Unit = {},
    var returnQuery: (query: String) -> Unit = {}
) {
    private val view = Dialog(context)

    fun showFilterDialog() {
        with(view) {
            setContentView(R.layout.dialog_filter)
            window?.attributes!!.gravity = Gravity.BOTTOM
            window?.setBackgroundDrawable(ColorDrawable(TRANSPARENT))
            show()

            loadNameEc(view.autoCompleteTextView_filter_name_edit)
            loadAddress(view.autoCompleteTextView_filter_address_edit)
            saveBtnListeners()
        }
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
            params[@Params SEGMENT] = view.dropDownTextView_filter_segment_edit.text.toString().trim()
        }
        if (view.dropDownTextView_filter_cluster_tpv_edit.text.toString().trim().isNotEmpty()) {
            params[@Params TPV] =
                view.dropDownTextView_filter_cluster_tpv_edit.text.toString().trim()
        }
        return params
    }
}