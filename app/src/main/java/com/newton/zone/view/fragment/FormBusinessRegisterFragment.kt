package com.newton.zone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.newton.zone.R
import com.newton.zone.model.*
import com.newton.zone.validation.PatternValidation
import com.newton.zone.validation.Validator
import com.newton.zone.view.dialog.AddressFormDialog
import kotlinx.android.synthetic.main.fragment_business_form.*

class FormBusinessRegisterFragment : Fragment() {

    private val validators = mutableListOf<Validator>()
    private val itemsDropdown by lazy {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_business_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        validateField(businessNameTextInputLayout)
        validateField(businessTPVTextInputLayout)
        validateField(dropDownTextView_segment)
        initSegmentDropDown()
        business_form_address_card.setOnClickListener { callInsertAddressDialog() }
        business_tpv_form_address.doOnTextChanged { text, _, _, _ -> formatFieldForMoneyMask(text) }
        form_business_save_btn.setOnClickListener { makeAndSaveBusiness() }
    }

    private fun makeAndSaveBusiness() {
    }

    private fun initSegmentDropDown() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            itemsDropdown
        )
        (dropDownTextView_segment.editText as? AutoCompleteTextView)?.setAdapter(adapter)
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

    private fun validateField(field: TextInputLayout) {
        val editTextField = field.editText
        val validator = PatternValidation(field)
        validators.add(validator)
        editTextField!!.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validator.isValid()
            }
        }
    }

    private fun formatFieldForMoneyMask(text: CharSequence?) {
        if (!text.toString().matches("^\\$(\\d{1,3}(,\\d{3})*|(\\d+))(\\.\\d{2})?$".toRegex())) {
            val originalCursorPosition: Int = business_tpv_form_address.selectionStart
            var cursorOffset = 0
            val cursorAtEnd = originalCursorPosition == text.toString().length
            val userInput = "" + text.toString().replace("[^\\d]".toRegex(), "")
            val cashAmountBuilder = StringBuilder(userInput)
            while (cashAmountBuilder.length > 3 && cashAmountBuilder[0] == '0') {
                cashAmountBuilder.deleteCharAt(0)
                cursorOffset--
            }
            while (cashAmountBuilder.length < 3) {
                cashAmountBuilder.insert(0, '0')
                cursorOffset++
            }
            cashAmountBuilder.insert(cashAmountBuilder.length - 2, '.')
            cashAmountBuilder.insert(0, '$')
            business_tpv_form_address.setText(cashAmountBuilder.toString())
            business_tpv_form_address.setSelection(
                if (cursorAtEnd) business_tpv_form_address.text.toString()
                    .length else originalCursorPosition + cursorOffset
            )
        }
    }
}