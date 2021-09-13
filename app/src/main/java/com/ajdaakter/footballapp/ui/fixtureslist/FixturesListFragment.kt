package com.ajdaakter.footballapp.ui.fixtureslist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ajdaakter.footballapp.R
import com.ajdaakter.footballapp.common.ViewModelProviderFactory
import com.ajdaakter.footballapp.data.model.Match
import com.ajdaakter.footballapp.databinding.FragmentFixturesListBinding
import com.ajdaakter.footballapp.ui.fixtureslist.adapter.SectionFixturesListAdapter
import com.ajdaakter.footballapp.utilities.DataWrapper
import com.ajdaakter.footballapp.utilities.extension.showSnackBar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FixturesListFragment: Fragment(), KodeinAware {

    override val kodein by kodein()

    private lateinit var binding: FragmentFixturesListBinding
    private lateinit var viewModel: FixturesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.setTitle(R.string.recent_fixtures)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentFixturesListBinding.inflate(inflater, container, false)
        binding.hasFixtures = false
        binding.isLoading = true

        val viewModelFactory: ViewModelProviderFactory by kodein.instance()
        viewModel = viewModelFactory.create(FixturesListViewModel::class.java)
        initUi()

        return binding.root
    }

    private fun initUi() {
        val adapter = SectionFixturesListAdapter(requireContext())
        binding.fixturesList.adapter = adapter
        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: SectionFixturesListAdapter) {
        viewModel.fixturesList.observe(viewLifecycleOwner, Observer <DataWrapper<List<Match>>>{ matches ->
            binding.isLoading = false

            if (matches.status == DataWrapper.Status.ERROR) {
                val errorMessage = matches.message ?: getString(R.string.internet_connection_error)
                view?.showSnackBar(requireContext(), errorMessage, R.string.retry) {
                    binding.isLoading = true
                    viewModel.getRemoteFixtures()
                }
                return@Observer
            }

            val matchData = matches.data ?: listOf()
            binding.hasFixtures = (matchData.count() > 0)
            adapter.submitList(matchData)
        })
    }




}