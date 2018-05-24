package ru.aleksandrtrushchinskii.ocolo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.AuthUI
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.finish
import ru.aleksandrtrushchinskii.ocolo.common.util.inflate


class MainFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.main_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_out_button.setOnClickListener {
            AuthUI.getInstance().signOut(context!!).addOnCompleteListener {
                finish()
            }
        }
    }

}