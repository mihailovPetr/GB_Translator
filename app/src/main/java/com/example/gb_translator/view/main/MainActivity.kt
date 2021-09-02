package com.example.gb_translator.view.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_translator.R
import com.example.gb_translator.app.App
import com.example.gb_translator.databinding.ActivityMainBinding
import com.example.gb_translator.model.data.AppState
import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.utils.ui.AlertDialogFragment
import com.example.gb_translator.view.base.View
import com.example.gb_translator.view.main.adapter.MainAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(listItemClickListener) }
    private var _binding: ActivityMainBinding? = null
    private val vb get() = _binding!!

    private val listItemClickListener: (DataModel) -> Unit = { data ->
        Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
    }

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

        App.instance.appComponent.inject(this)

        viewModel = viewModelFactory.create(MainViewModel::class.java)
        viewModel.getLiveData().observe(this@MainActivity, { renderData(it) })

        vb.mainActivityRv.layoutManager = LinearLayoutManager(applicationContext)
        vb.mainActivityRv.adapter = adapter

        vb.searchEditText.addTextChangedListener(textWatcher)

        vb.clearTextImageView.setOnClickListener { vb.searchEditText.text = null }

        vb.searchButton.setOnClickListener {
            viewModel.getData(vb.searchEditText.text.toString(), true)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_tittle_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun showViewWorking() {
        vb.loadingFrameLayout.visibility = GONE
    }

    private fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewLoading() {
        vb.loadingFrameLayout.visibility = VISIBLE
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}