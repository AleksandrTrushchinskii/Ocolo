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
import ru.aleksandrtrushchinskii.ocolo.common.KEY_CURRENT_FRAGMENT
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup.CreateMeetupFragment
import ru.aleksandrtrushchinskii.ocolo.ui.main.MainFragment
import ru.aleksandrtrushchinskii.ocolo.ui.profile.ProfileFragment
import ru.aleksandrtrushchinskii.ocolo.ui.signin.SignInFragment
import ru.aleksandrtrushchinskii.ocolo.common.service.LoadingState
import ru.aleksandrtrushchinskii.ocolo.common.KEY_NEW_USER
import ru.aleksandrtrushchinskii.ocolo.common.util.logDebug
import ru.aleksandrtrushchinskii.ocolo.databinding.MainActivityBinding
import java.lang.RuntimeException
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var auth: Authentication

    private lateinit var binding: MainActivityBinding
    private lateinit var currentFragment: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity).apply {
            loading = LoadingState
            setLifecycleOwner(this@MainActivity)
        }

        setSupportActionBar(toolbar)

        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        val previousFragmentClass = savedInstanceState?.getString(KEY_CURRENT_FRAGMENT)

        if (previousFragmentClass != null) {
            startFragmentByClassName(previousFragmentClass)
        } else {
            LoadingState.startForeground()

            if (auth.isAuth) {
                checkProfileAndRunFragment()
            } else {
                LoadingState.stopForeground()

                startFragment(SignInFragment())
            }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_CURRENT_FRAGMENT, currentFragment)
    }

    fun finishFragment(fragment: Fragment) {
        when (fragment::class) {
            SignInFragment::class -> checkProfileAndRunFragment()
            in listOf(ProfileFragment::class, CreateMeetupFragment::class) -> startFragment(MainFragment())
            MainFragment::class -> if (!auth.isAuth) startFragment(SignInFragment())

            else -> throw RuntimeException("Unknown to do when fragment : ${fragment::class} was finish")
        }
    }

    private fun startFragment(fragment: Fragment) {
        currentFragment = fragment::class.java.simpleName

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow()
    }

    private fun checkProfileAndRunFragment() {
        auth.exist {
            LoadingState.stopForeground()
            if (it) {
                startFragment(MainFragment())
            } else {
                val profileFragment = ProfileFragment()
                profileFragment.arguments = Bundle().apply { putBoolean(KEY_NEW_USER, true) }
                startFragment(profileFragment)
            }
        }
    }

    private fun startFragmentByClassName(className: String) = when (className) {
        SignInFragment::class.java.simpleName -> startFragment(SignInFragment())
        ProfileFragment::class.java.simpleName -> startFragment(ProfileFragment())
        CreateMeetupFragment::class.java.simpleName -> startFragment(CreateMeetupFragment())

        else -> logDebug("Unknown fragment to start : $className after recreating activity")
    }


}