package com.newton.zone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newton.zone.R
import com.newton.zone.model.Business
import com.newton.zone.model.CLIENT
import com.newton.zone.model.Type
import com.newton.zone.view.viewmodel.BusinessViewModel
import kotlinx.android.synthetic.main.fragment_list_client.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListClientFragment : BaseListBusinessFragment() {

    private val viewModel: BusinessViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        client_list_animation.setAnimation("anim/list_empty.json")

        viewModel.listAll().observe(viewLifecycleOwner, { business ->
            if (business != null) {
                val dataList = business.filter { it.type == @Type CLIENT }
                super.ifEmptyPlayAnimation(dataList, client_list_animation)
                super.initAdapter(dataList as MutableList<Business>, client_rv)
            }
        })

        viewModel.checkBusinessReturned().observe(viewLifecycleOwner, { businessList ->
            if (businessList != null) {
                val dataList = businessList.filter { it.type == @Type CLIENT }
                super.ifEmptyPlayAnimation(dataList, client_list_animation)
                super.initAdapter(dataList as MutableList<Business>, client_rv)
            }
        })
    }

}
