package com.newton.zone.view.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.snackbar.Snackbar
import com.newton.zone.R
import com.newton.zone.extension.formatForBrazilianDate
import com.newton.zone.extension.formatForBrazilianHour
import com.newton.zone.extension.returnUUID
import com.newton.zone.model.*
import com.newton.zone.view.recyclerview.adapter.BusinessAdapter
import com.newton.zone.view.viewmodel.BusinessViewModel
import com.newton.zone.view.viewmodel.VisitViewModel
import kotlinx.android.synthetic.main.visit_popup.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

open class BaseListBusinessFragment : Fragment() {

    private val viewModel: BusinessViewModel by viewModel()
    private val visitViewModel: VisitViewModel by viewModel()
    private lateinit var date: Date
    private lateinit var hour: Date
    protected lateinit var popup: Dialog
    private lateinit var dateBtn: Button
    private lateinit var hourBtn: Button
    private lateinit var positiveButton: Button
    private lateinit var edtObs: EditText

    protected fun initAdapter(business: MutableList<Business>, recyclerView: RecyclerView) {
        val adapter = context?.let { BusinessAdapter(
            it,
            business,
            onItemClickPopupTurnClient = { business ->
                business.type = @Type CLIENT
                viewModel.update(business)
            },
            onItemClickPopupVisit = { business ->
                showVisitPopup(business)
            }
        ) }
        recyclerView.adapter = adapter
    }

    private fun showVisitPopup(business: Business) {
        popup.setContentView(R.layout.visit_popup)
        initFieldsCountPopup()
        positiveButton.setOnClickListener {
            val obs = if (edtObs.text.toString().trim().isEmpty()) "" else edtObs.text.toString().trim()
            if (allIsInitialized()){
                visitViewModel.insert(Visit(
                    business = business,
                    day = date,
                    hour = hour,
                    observation = obs
                ))
                popup.dismiss()
            } else {
                popup.msg_error.visibility = VISIBLE
            }
        }
        dateBtn.setOnClickListener { initDateDialog() }
        hourBtn.setOnClickListener { initTimeDialog() }
        popup.show()
    }

    private fun initFieldsCountPopup() {
        dateBtn = popup.findViewById(R.id.form_visit_date_btn)
        hourBtn = popup.findViewById(R.id.form_visit_hour_btn)
        positiveButton = popup.findViewById(R.id.form_visit_positive_btn)
        edtObs = popup.findViewById(R.id.form_visit_obs)
    }

    fun ifEmptyPlayAnimation(business: List<Business>, listAnimation: LottieAnimationView) {
        if (business.isEmpty()) {
            initAnimation(listAnimation)
        } else {
            listAnimation.visibility = View.GONE
        }
    }

    private fun initAnimation(listAnimation: LottieAnimationView) {
        with(listAnimation) {
            scaleX = 0.5f
            scaleY = 0.5f
            visibility = VISIBLE
            playAnimation()
        }
    }

    private fun initDateDialog() {
        val datePicker = DatePickerHelper(
            onDataSet = { currentDate ->
                date = currentDate
                dateBtn.text = currentDate.formatForBrazilianDate()
            }
        )
        activity?.supportFragmentManager?.let { fragmentManager ->
            datePicker.show(fragmentManager, "time picker")
        }
    }

    private fun initTimeDialog() {
        val timePicker = TimePickerHelper(
            onTimeSet = { currentHour ->
                hour = currentHour
                hourBtn.text = currentHour.formatForBrazilianHour()
            }
        )
        activity?.supportFragmentManager?.let { fragmentManager ->
            timePicker.show(fragmentManager, "time picker")
        }
    }

    private fun allIsInitialized() =
        ::date.isInitialized && ::hour.isInitialized
}