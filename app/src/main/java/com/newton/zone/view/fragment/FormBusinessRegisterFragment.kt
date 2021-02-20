package com.newton.zone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newton.zone.R
import com.newton.zone.view.dialog.AddressFormDialog
import kotlinx.android.synthetic.main.fragment_business_form.*

class FormBusinessRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_business_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        business_form_address_card.setOnClickListener {
            callInsertAddressDialog()
        }
    }

    private fun callInsertAddressDialog() {
        val addressDialog = AddressFormDialog(requireContext(), view as ViewGroup)
        addressDialog.inflateForm { street, number, district, city ->
            initAddressFields(
                street,
                number,
                district,
                city
            )
            business_form_address_card.isClickable = false
        }
    }

    private fun initAddressFields(street: String, number: String, district: String, city: String) {
        fragment_form_business_msg_card.visibility = INVISIBLE
        turnFieldsVisible()
        val streetConcat = "${street}, $number"
        fragment_form_street.text = streetConcat
        fragment_form_district.text = district
        fragment_form_city.text = city
    }

    private fun turnFieldsVisible() {
        fragment_form_street_label.visibility = VISIBLE
        fragment_form_district_label.visibility = VISIBLE
        fragment_form_city_label.visibility = VISIBLE
        fragment_form_street.visibility = VISIBLE
        fragment_form_district.visibility = VISIBLE
        fragment_form_city.visibility = VISIBLE
    }
}