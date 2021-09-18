package com.example.historyscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_translator.R
import com.example.historyscreen.HISTORY_SCOPE_NAME
import com.example.historyscreen.databinding.FragmentHistoryBinding
import com.example.historyscreen.injectDependencies
import com.example.model.entity.AppState
import com.example.repository.entity.room.HistoryEntity
import com.example.utils.ui.AlertDialogFragment
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val vb get() = _binding!!
    private val scope = run {
        injectDependencies()
        getKoin().createScope("historyScope", named(HISTORY_SCOPE_NAME))
    }
    private val viewModel: HistoryViewModel by scope.viewModel(this)
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }
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
            is AppState.Success<*> -> {
                showViewWorking()
                val data = appState.data as List<HistoryEntity>
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

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}