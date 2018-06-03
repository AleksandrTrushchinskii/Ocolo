package ru.aleksandrtrushchinskii.ocolo.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ru.aleksandrtrushchinskii.ocolo.R
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.main_activity.*
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.common.util.invisible
import ru.aleksandrtrushchinskii.ocolo.common.util.visible
import ru.aleksandrtrushchinskii.ocolo.ui.main.MainFragment
import ru.aleksandrtrushchinskii.ocolo.ui.profile.ProfileFragment
import ru.aleksandrtrushchinskii.ocolo.ui.signin.SignInFragment
import ru.aleksandrtrushchinskii.ocolo.ui.tool.LoadingState
import ru.aleksandrtrushchinskii.ocolo.ui.tool.NEW_USER
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var auth: Authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        LoadingState.active.observe(this, Observer {
            if (it!!) {
                fragment_container.invisible()
                progressBar.visible()
            } else {
                fragment_container.visible()
                progressBar.invisible()
            }
        })

        if (auth.isAuth) {
            checkProfileAndRunFragment()
        } else {
            startFragment(SignInFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_profile -> {
            startFragment(ProfileFragment())
            true
        }
        R.id.action_sign_out -> {
            AuthUI.getInstance().signOut(this).addOnCompleteListener {
                startFragment(SignInFragment())
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    fun finishFragment(fragment: Fragment) {
        when (fragment::class) {
            SignInFragment::class -> checkProfileAndRunFragment()
            ProfileFragment::class -> startFragment(MainFragment())
            MainFragment::class -> if (!auth.isAuth) startFragment(SignInFragment())
            else -> println(fragment)
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow()
    }

    private fun checkProfileAndRunFragment() {
        auth.exist {
            if (it) {
                startFragment(MainFragment())
            } else {
                val profileFragment = ProfileFragment()
                profileFragment.arguments = Bundle().apply { putBoolean(NEW_USER, true) }
                startFragment(profileFragment)
            }
        }
    }

}