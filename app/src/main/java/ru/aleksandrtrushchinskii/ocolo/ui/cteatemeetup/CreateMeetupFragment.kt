package ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.create_meetup_fragment.*
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.inflate
import ru.aleksandrtrushchinskii.ocolo.ui.support.picker.date.DatePickerFragment


class CreateMeetupFragment: DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.create_meetup_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_meetup_choose_date.setOnClickListener {
            DatePickerFragment().show(fragmentManager, "datePicker")
        }
    }

}