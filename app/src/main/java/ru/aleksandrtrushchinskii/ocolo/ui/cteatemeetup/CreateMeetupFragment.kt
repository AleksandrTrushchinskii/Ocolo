package ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.create_meetup_fragment.*
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.finish
import ru.aleksandrtrushchinskii.ocolo.common.util.inflateBinding
import ru.aleksandrtrushchinskii.ocolo.databinding.CreateMeetupFragmentBinding
import ru.aleksandrtrushchinskii.ocolo.ui.support.ViewModelFactory
import ru.aleksandrtrushchinskii.ocolo.ui.support.picker.date.DatePickerFragment
import javax.inject.Inject


class CreateMeetupFragment: DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: CreateMeetupFragmentBinding
    private lateinit var vm: CreateMeetupViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container ?: return null

        vm = ViewModelProviders.of(activity!!, factory).get(CreateMeetupViewModel::class.java)

        binding = container.inflateBinding(R.layout.create_meetup_fragment)
        binding.setLifecycleOwner(this)
        binding.vm = vm

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_meetup_choose_date.setOnClickListener {
            DatePickerFragment().show(fragmentManager, "datePicker")
        }

        create_meetup.setOnClickListener {
            vm.create {
                finish()
            }
        }
    }

}