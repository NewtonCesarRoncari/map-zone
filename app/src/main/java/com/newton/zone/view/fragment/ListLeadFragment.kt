package com.newton.zone.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.newton.zone.R
import com.newton.zone.model.Business
import com.newton.zone.model.LEAD
import com.newton.zone.model.Type
import com.newton.zone.view.viewmodel.BusinessViewModel
import kotlinx.android.synthetic.main.fragment_list_lead.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListLeadFragment : BaseListBusinessFragment() {

    private val viewModel: BusinessViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_lead, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lead_list_animation.setAnimation("anim/list_empty.json")
        super.popup = Dialog(requireContext())

        viewModel.listAll().observe(viewLifecycleOwner, { business ->
            if (business != null) {
                val dataList = business.filter { it.type == @Type LEAD }
                super.ifEmptyPlayAnimation(dataList, lead_list_animation)
                super.initAdapter(dataList as MutableList<Business>, lead_rv)
            }
        })
    }
}
