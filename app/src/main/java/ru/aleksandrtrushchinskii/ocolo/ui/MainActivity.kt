package ru.aleksandrtrushchinskii.ocolo.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.KEY_CURRENT_FRAGMENT
import ru.aleksandrtrushchinskii.ocolo.common.NEW_USER
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.common.service.LoadingState
import ru.aleksandrtrushchinskii.ocolo.databinding.MainActivityBinding
import ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup.CreateMeetupFragment
import ru.aleksandrtrushchinskii.ocolo.ui.meetupsline.MeetupsLineFragment
import ru.aleksandrtrushchinskii.ocolo.ui.profile.ProfileFragment
import ru.aleksandrtrushchinskii.ocolo.ui.signin.SignInFragment
import java.lang.RuntimeException
import javax.inject.Inject
import kotlin.reflect.KClass


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

        val previousFragmentClassName = savedInstanceState?.getString(KEY_CURRENT_FRAGMENT)

        if (previousFragmentClassName != null) {
            startFragment(className = previousFragmentClassName)
        } else {
            LoadingState.startForeground()

            if (auth.isAuth) {
                checkProfileAndRunFragment()
            } else {
                LoadingState.stopForeground()

                startFragment(SignInFragment::class)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_meetups_line -> startFragment(MeetupsLineFragment::class)
            R.id.action_create_meetup -> startFragment(CreateMeetupFragment::class)
            R.id.action_profile -> startFragment(ProfileFragment::class)
            R.id.action_sign_out -> AuthUI.getInstance().signOut(this).addOnCompleteListener {
                startFragment(SignInFragment::class)
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
            in listOf(ProfileFragment::class, CreateMeetupFragment::class) -> startFragment(MeetupsLineFragment::class)
            MeetupsLineFragment::class -> if (!auth.isAuth) startFragment(SignInFragment::class)

            else -> throw RuntimeException("Unknown to do when fragment : ${fragment::class} was finish")
        }
    }

    private fun checkProfileAndRunFragment() {
        auth.exist {
            LoadingState.stopForeground()
            if (it) {
                startFragment(MeetupsLineFragment::class)
            } else {
                startFragment(ProfileFragment::class, tag = NEW_USER)
            }
        }
    }

    private fun startFragment(clazz: KClass<*> = Any::class,
                              className: String = "",
                              tag: String = "") {

        val fragmentClassName = if (className == "") clazz.java.simpleName else className
        val fragment: Fragment

        when (fragmentClassName) {
            CreateMeetupFragment::class.java.simpleName -> fragment = CreateMeetupFragment()
            MeetupsLineFragment::class.java.simpleName -> fragment = MeetupsLineFragment()
            ProfileFragment::class.java.simpleName -> {
                fragment = ProfileFragment()
                if (tag == NEW_USER) {
                    fragment.arguments = Bundle().apply { putBoolean(NEW_USER, true) }
                }
            }
            SignInFragment::class.java.simpleName -> fragment = SignInFragment()
            else -> throw RuntimeException("Unknown fragment to start : $className after recreating activity")
        }

        currentFragment = fragmentClassName

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow()
    }

}