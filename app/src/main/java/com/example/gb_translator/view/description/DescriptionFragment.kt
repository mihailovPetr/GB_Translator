package com.example.gb_translator.view.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.gb_translator.databinding.FragmentDescriptionBinding
import com.example.model.entity.Word
import com.example.repository.utils.getTranslationsString

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val vb get() = _binding!!
    private val word by lazy { arguments?.getParcelable<Word>(WORD_ARG) }

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
        word?.let { word ->
            with(vb) {
                descriptionHeader.text = word.text
                descriptionTextview.text = word.getTranslationsString()
                descriptionImageview.load("https:${word.translations?.get(0)?.imageUrl}")
            }
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

        fun newInstance(word: Word) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(WORD_ARG, word)
                }
            }
    }
}