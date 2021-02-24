package com.newton.zone.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.newton.zone.R
import com.newton.zone.model.Visit
import com.newton.zone.view.recyclerview.adapter.VisitAdapter
import com.newton.zone.view.viewmodel.VisitViewModel
import kotlinx.android.synthetic.main.fragment_list_client.*
import kotlinx.android.synthetic.main.fragment_list_visits.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListVisitFragment: Fragment() {

    private val viewModel: VisitViewModel by viewModel()
    private lateinit var adapter: VisitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_visits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        visit_list_animation.setAnimation("anim/list_empty.json")
        initAdapter()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem by lazy { menu.findItem(R.id.action_search) }
        val searchView by lazy { searchItem.actionView as SearchView }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (::adapter.isInitialized) {
                    adapter.filter.filter(newText)
                }
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initAdapter() {
        viewModel.listAll().observe(viewLifecycleOwner, { visits ->
            if (visits != null) {
                ifEmptyPlayAnimation(visits, visit_list_animation)
                val adapter = VisitAdapter(requireContext(), visits)
                visit_rv.adapter = adapter
            }
        })
    }

    private fun ifEmptyPlayAnimation(visits: MutableList<Visit>, listAnimation: LottieAnimationView) {
        if (visits.isEmpty()) {
            initAnimation(listAnimation)
        } else {
            listAnimation.visibility = View.GONE
        }
    }

    private fun initAnimation(listAnimation: LottieAnimationView) {
        with(listAnimation) {
            scaleX = 0.5f
            scaleY = 0.5f
            visibility = View.VISIBLE
            playAnimation()
        }
    }
}