package com.breakingbad

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.breakingbad.databinding.ActorsListFragmentBinding

class ActorsListFragment : Fragment() {

    private val viewModel: ActorsListViewModel by lazy {
        ViewModelProvider(this).get(ActorsListViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ActorsListFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.actors = viewModel

        binding.recyclerView.adapter = ActorListAdapter(ActorListAdapter.OnClickListener {
            viewModel.displayActorDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {
                 this.findNavController().navigate(ActorsListFragmentDirections.actionActorListFragmentToActorDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val adpFilter = (binding.recyclerView.adapter as ActorListAdapter).getFilter()
                adpFilter.filter(newText)
//                adapter.filter.filter(newText)
                return false
            }

//            override fun onQueryTextChange(p0: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                TODO("Not yet implemented")
//            }

        })

        return binding.root
    }

}