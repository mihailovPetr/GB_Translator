package com.example.gb_translator.view.description

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.gb_translator.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val vb get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDescriptionBinding.inflate(inflater, container, false).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackButton()
        setData()
    }

    private fun initBackButton() {
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setData() {
        with(vb) {
            descriptionHeader.text = arguments?.getString(WORD_ARG)
            descriptionTextview.text = arguments?.getString(DESCRIPTION_ARG)
            descriptionImageview.load("https:${arguments?.getString(URL_ARG)}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setHomeButtonEnabled(false)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    companion object {
        private const val WORD_ARG = "word"
        private const val DESCRIPTION_ARG = "description"
        private const val URL_ARG = "url"

        fun newInstance(word: String, description: String, url: String?) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(WORD_ARG, word)
                    putString(DESCRIPTION_ARG, description)
                    putString(URL_ARG, url)
                }
            }
    }
}