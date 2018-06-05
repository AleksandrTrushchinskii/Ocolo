package ru.aleksandrtrushchinskii.ocolo.ui.profile

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.finish
import ru.aleksandrtrushchinskii.ocolo.common.util.inflateBinding
import ru.aleksandrtrushchinskii.ocolo.ui.support.ViewModelFactory
import ru.aleksandrtrushchinskii.ocolo.databinding.ProfileFragmentBinding
import ru.aleksandrtrushchinskii.ocolo.common.NEW_USER
import javax.inject.Inject


class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var vm: ProfileViewModel

    private var isUserNew: Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container ?: return null

        isUserNew = arguments?.getBoolean(NEW_USER) ?: false
        vm = ViewModelProviders.of(activity!!, factory).get(ProfileViewModel::class.java)

        binding = container.inflateBinding(R.layout.profile_fragment)
        binding.setLifecycleOwner(this)
        binding.vm = vm
        binding.isUserNew = isUserNew

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUserButton.setOnClickListener {
            vm.save {
                finish()
            }
        }
    }

}