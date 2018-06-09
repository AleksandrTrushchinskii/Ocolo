package ru.aleksandrtrushchinskii.ocolo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.inflateBinding
import ru.aleksandrtrushchinskii.ocolo.databinding.MainFragmentBinding
import ru.aleksandrtrushchinskii.ocolo.model.Meetup
import ru.aleksandrtrushchinskii.ocolo.ui.support.adapter.MeetupAdapter


class MainFragment : DaggerFragment() {

    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container ?: return null

        binding = container.inflateBinding(R.layout.main_fragment)
        binding.meetupAdapter = MeetupAdapter

        MeetupAdapter.setData(listOf(
                Meetup(title = "1 Meetup"),
                Meetup(title = "2 Meetup"),
                Meetup(title = "3 Meetup"),
                Meetup(title = "4 Meetup"),
                Meetup(title = "5 Meetup"),
                Meetup(title = "6 Meetup"),
                Meetup(title = "7 Meetup"),
                Meetup(title = "8 Meetup"),
                Meetup(title = "9 Meetup")
        ))

        return binding.root
    }

}