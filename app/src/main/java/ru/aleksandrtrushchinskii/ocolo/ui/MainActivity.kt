package ru.aleksandrtrushchinskii.ocolo.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ru.aleksandrtrushchinskii.ocolo.R
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.main_activity.*
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup.CreateMeetupFragment
import ru.aleksandrtrushchinskii.ocolo.ui.main.MainFragment
import ru.aleksandrtrushchinskii.ocolo.ui.profile.ProfileFragment
import ru.aleksandrtrushchinskii.ocolo.ui.signin.SignInFragment
import ru.aleksandrtrushchinskii.ocolo.common.service.LoadingState
import ru.aleksandrtrushchinskii.ocolo.common.NEW_USER
import ru.aleksandrtrushchinskii.ocolo.databinding.MainActivityBinding
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var auth: Authentication

    private lateinit var binding: MainActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity).apply {
            loading = LoadingState
            setLifecycleOwner(this@MainActivity)
        }

        setSupportActionBar(toolbar)

        init()
    }

    private fun init() {
        if (auth.isAuth) {
            checkProfileAndRunFragment()
        } else {
            startFragment(SignInFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_main -> startFragment(MainFragment())
            R.id.action_create_meetup -> startFragment(CreateMeetupFragment())
            R.id.action_profile -> startFragment(ProfileFragment())
            R.id.action_sign_out -> AuthUI.getInstance().signOut(this).addOnCompleteListener {
                startFragment(SignInFragment())
            }
            else -> super.onOptionsItemSelected(item)
        }

        return true
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