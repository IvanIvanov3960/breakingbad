package com.breakingbad

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.breakingbad.databinding.ActorDetailedFragmentBinding

class ActorDetailedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = ActorDetailedFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val singleActor = ActorDetailedFragmentArgs.fromBundle(arguments!!).selectedActor
        val viewModelFactory = DetailViewModelFactory(singleActor, application)
        binding.model = ViewModelProvider(
            this, viewModelFactory).get(ActorDetailedViewModel::class.java)
        return binding.root
    }

}