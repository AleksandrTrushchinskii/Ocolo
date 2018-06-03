package ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.inflate


class CreateMeetupFragment: DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.create_meetup_fragment)
    }

}