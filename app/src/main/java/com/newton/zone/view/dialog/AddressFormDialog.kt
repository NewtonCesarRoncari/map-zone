package com.newton.zone.view.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.newton.zone.R
import com.newton.zone.view.dialog.AddressFormDialog.Constant.NEGATIVE_BUTTON_NAME
import com.newton.zone.view.dialog.AddressFormDialog.Constant.POSITIVE_BUTTON_NAME
import com.newton.zone.view.dialog.AddressFormDialog.Constant.TITLE_DIALOG
import kotlinx.android.synthetic.main.address_form.view.*

class AddressFormDialog(
    private val context: Context,
    private val viewGroup: ViewGroup
) {
    private val viewCreated = initView()
    private val fieldStreet by lazy { viewCreated.address_street_form }
    private val fieldNumber by lazy { viewCreated.address_number_form }
    private val fieldDistrict by lazy { viewCreated.address_district_form }
    private val fieldCity by lazy { viewCreated.address_city_form }

    fun inflateForm(
        dialogClickListener: (
            street: String,
            number: String,
            district: String,
            city: String
        ) -> Unit
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(TITLE_DIALOG)
            .setView(viewCreated)
            .setPositiveButton(POSITIVE_BUTTON_NAME) { _, _ ->

                dialogClickListener(
                    "${fieldStreet.text.toString().trim()} ",
                    fieldNumber.text.toString().trim(),
                    fieldDistrict.text.toString().trim(),
                    fieldCity.text.toString().trim()
                )
            }
            .setNegativeButton(NEGATIVE_BUTTON_NAME, null)
            .show()
    }


    private fun initView(): View {
        return LayoutInflater.from(context).inflate(
            R.layout.address_form,
            viewGroup,
            false
        )
    }

    object Constant {
        const val TITLE_DIALOG = "Cadastro de endereço"
        const val POSITIVE_BUTTON_NAME = "SALVAR"
        const val NEGATIVE_BUTTON_NAME = "CANCELAR"
    }
}