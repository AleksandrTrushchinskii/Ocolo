package ru.aleksandrtrushchinskii.ocolo.ui.meetupsline

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.inflateBinding
import ru.aleksandrtrushchinskii.ocolo.databinding.MeetupsLineFragmentBinding
import ru.aleksandrtrushchinskii.ocolo.ui.support.ViewModelFactory
import ru.aleksandrtrushchinskii.ocolo.ui.support.adapter.MeetupAdapter
import javax.inject.Inject


class MeetupsLineFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: MeetupsLineFragmentBinding
    private lateinit var vm: MeetupsLineViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container ?: return null

        binding = container.inflateBinding(R.layout.meetups_line_fragment)
        vm = ViewModelProviders.of(activity!!, factory).get(MeetupsLineViewModel::class.java)
        binding.meetupAdapter = MeetupAdapter

        vm.get { MeetupAdapter.setData(it) }

        return binding.root
    }

}