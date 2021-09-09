package com.example.gb_translator.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_translator.R
import com.example.gb_translator.databinding.FragmentHistoryBinding
import com.example.gb_translator.model.data.AppState
import com.example.gb_translator.utils.ui.AlertDialogFragment
import com.example.gb_translator.view.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val vb get() = _binding!!
    private val viewModel: HistoryViewModel by viewModel()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHistoryBinding.inflate(inflater, container, false).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        vb.historyRv.layoutManager = LinearLayoutManager(requireContext())
        vb.historyRv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

    private fun renderData(appState: AppState) {
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
        vb.loadingFrameLayout.visibility = View.GONE
    }

    private fun showAlertDialog(title: String?, message: String?) {
        activity?.let {
            AlertDialogFragment.newInstance(title, message)
                .show(it.supportFragmentManager, null)
        }
    }

    private fun showViewLoading() {
        vb.loadingFrameLayout.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}