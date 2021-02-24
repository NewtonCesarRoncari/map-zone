package com.newton.zone.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager.widget.ViewPager
import com.newton.zone.R
import com.newton.zone.view.dialog.FilterDialog
import com.newton.zone.view.tabs.adapter.TabsAdapter
import com.newton.zone.view.viewmodel.BusinessViewModel
import com.newton.zone.view.viewmodel.StateAppComponentsViewModel
import com.newton.zone.view.viewmodel.VisualComponents
import kotlinx.android.synthetic.main.fragment_list_business.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListBusinessFragment: Fragment() {

    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val navController by lazy { NavHostFragment.findNavController(this) }
    private val viewModel: BusinessViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_business, container, false)
        appComponentsViewModel.havComponent = VisualComponents(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTabLayout(view)
        list_business_btn_register_lead.setOnClickListener {
            goToFormBusinessRegisterFragment()
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.base_filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_filter) {
            initFilterDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initTabLayout(view: View) {
        val tabsAdapter = context?.let { TabsAdapter(requireActivity().supportFragmentManager, it) }
        val viewPager by lazy {
            view.findViewById<ViewPager>(R.id.fragment_list_lead_client_view_pager)
        }

        viewPager.adapter = tabsAdapter
        fragment_list_lead_client_tabLayout.setupWithViewPager(viewPager)
    }

    private fun goToFormBusinessRegisterFragment() {
        val direction = ListBusinessFragmentDirections
            .actionListBusinessToFormBusinessRegisterFragment()
        navController.navigate(direction)
    }

    private fun initFilterDialog() {
        FilterDialog(
            requireContext(),
            requireActivity(),
            loadNameEc = {},
            loadAddress = {},
            returnQuery = { query -> viewModel.findBusinessFilter(query) }
        ).showFilterDialog()
    }
}