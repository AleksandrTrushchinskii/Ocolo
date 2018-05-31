package ru.aleksandrtrushchinskii.ocolo.ui.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.inflateBinding
import ru.aleksandrtrushchinskii.ocolo.common.viewmodel.ViewModelFactory
import ru.aleksandrtrushchinskii.ocolo.databinding.ProfileFragmentBinding
import ru.aleksandrtrushchinskii.ocolo.ui.tool.NEW_USER
import ru.aleksandrtrushchinskii.ocolo.ui.viewmodel.ProfileViewModel
import javax.inject.Inject


class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var profileVM: ProfileViewModel

    private var isUserNew: Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container ?: return null

        isUserNew = arguments?.getBoolean(NEW_USER) ?: false
        binding = container.inflateBinding(R.layout.profile_fragment)
        profileVM = ViewModelProviders.of(activity!!, factory).get(ProfileViewModel::class.java)

        binding.vm = profileVM
        binding.isUserNew = isUserNew

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isUserNew) {
            profileVM.createNewUser()
        } else {
            Toast.makeText(context, "Old User", Toast.LENGTH_SHORT).show()
        }
    }

}