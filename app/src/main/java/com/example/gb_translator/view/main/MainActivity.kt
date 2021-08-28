package com.example.gb_translator.view.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_translator.R
import com.example.gb_translator.databinding.ActivityMainBinding
import com.example.gb_translator.model.data.AppState
import com.example.gb_translator.presenter.Presenter
import com.example.gb_translator.view.base.View
import com.example.gb_translator.view.main.adapter.MainAdapter

class MainActivity : AppCompatActivity(), View {

    private var adapter: MainAdapter? = null
    private val presenter: Presenter<View> = MainPresenterImpl()
    private var _binding: ActivityMainBinding? = null
    private val vb get() = _binding!!

    private val textWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (!vb.searchEditText.text.isNullOrEmpty()) {
                vb.searchButton.isEnabled = true
                vb.clearTextImageView.visibility = VISIBLE
            } else {
                vb.searchButton.isEnabled = false
                vb.clearTextImageView.visibility = GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.searchEditText.addTextChangedListener(textWatcher)

        vb.clearTextImageView.setOnClickListener {
            vb.searchEditText.text = null
        }

        vb.searchButton.setOnClickListener {
            presenter.getData(vb.searchEditText.text.toString(), true)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.error_empty_response))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        vb.mainActivityRv.layoutManager =
                            LinearLayoutManager(applicationContext)
                        vb.mainActivityRv.adapter = MainAdapter(dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        vb.errorTextView.text = error ?: getString(R.string.error_undefined)
        vb.reloadButton.setOnClickListener { vb.searchButton.performClick() }
    }

    private fun showViewSuccess() {
        vb.successLinearLayout.visibility = VISIBLE
        vb.loadingFrameLayout.visibility = GONE
        vb.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        vb.successLinearLayout.visibility = GONE
        vb.loadingFrameLayout.visibility = VISIBLE
        vb.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        vb.successLinearLayout.visibility = GONE
        vb.loadingFrameLayout.visibility = GONE
        vb.errorLinearLayout.visibility = VISIBLE
    }
}