package ru.aleksandrtrushchinskii.ocolo.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ru.aleksandrtrushchinskii.ocolo.R
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.main_activity.*
import ru.aleksandrtrushchinskii.ocolo.common.util.invisible
import ru.aleksandrtrushchinskii.ocolo.common.util.visible
import ru.aleksandrtrushchinskii.ocolo.common.viewmodel.ViewModelFactory
import ru.aleksandrtrushchinskii.ocolo.ui.view.MainFragment
import ru.aleksandrtrushchinskii.ocolo.ui.viewmodel.MainViewModel
import ru.aleksandrtrushchinskii.ocolo.ui.view.ProfileFragment
import ru.aleksandrtrushchinskii.ocolo.ui.view.SignInFragment
import ru.aleksandrtrushchinskii.ocolo.ui.viewmodel.ProfileViewModel
import ru.aleksandrtrushchinskii.ocolo.ui.tool.LoadingState
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var profileVM: ProfileViewModel
    private lateinit var mainVM: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        profileVM = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        mainVM = ViewModelProviders.of(this).get(MainViewModel::class.java)

        LoadingState.active.observe(this, Observer {
            if (it!!) {
                fragment_container.invisible()
                progress_bar.visible()
            } else {
                fragment_container.visible()
                progress_bar.invisible()
            }
        })

        if (!profileVM.isAuth) {
            startFragment(SignInFragment())
        } else {
            checkProfileAndRunFragment()
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow()
    }

    fun finishFragment(fragment: Fragment) {
        when (fragment::class) {
            SignInFragment::class -> checkProfileAndRunFragment()
            MainFragment::class -> if (!profileVM.isAuth) startFragment(SignInFragment())
            else -> println(fragment)
        }
    }

    private fun checkProfileAndRunFragment() {
        profileVM.exist({
            startFragment(MainFragment())
        }, {
            startFragment(ProfileFragment())
        })
    }

}