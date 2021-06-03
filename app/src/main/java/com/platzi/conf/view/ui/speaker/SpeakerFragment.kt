package com.platzi.conf.view.ui.speaker


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.platzi.conf.R
import com.platzi.conf.model.Speaker
import com.platzi.conf.view.adapter.SpeakerAdapter
import com.platzi.conf.view.adapter.SpeakerListener
import com.platzi.conf.viewModel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speaker.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakerFragment : Fragment(),SpeakerListener {

    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel:SpeakerViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speaker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(SpeakerViewModel::class.java)
        viewModel.onRefresh()


        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context,2)
            adapter=speakerAdapter

        }
        observerViewModel()
    }



    fun observerViewModel(){
        viewModel.listSpeakers.observe(this, Observer<List<Speaker>> {speaker ->
            speakerAdapter.updateData(speaker)
        })

        viewModel.isLoading.observe(this, Observer<Boolean>{
            if(it != null){
                rvBase.visibility =  View.INVISIBLE
            }
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakerDetailFragmentDialog,bundle)
    }


}
