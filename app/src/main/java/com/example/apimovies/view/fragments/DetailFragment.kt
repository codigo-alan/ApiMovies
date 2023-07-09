package com.example.apimovies.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.apimovies.R
import com.example.apimovies.databinding.FragmentDetailBinding
import com.example.apimovies.viewmodel.ListViewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val model: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exampleText.text = model.selectedMovie.value?.originalTitle ?: "No data"

        //To list button
        binding.toListBtn.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }

    }

}