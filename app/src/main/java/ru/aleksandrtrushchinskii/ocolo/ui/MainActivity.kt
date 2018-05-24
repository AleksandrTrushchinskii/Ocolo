package ru.aleksandrtrushchinskii.ocolo.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ru.aleksandrtrushchinskii.ocolo.R
import android.support.v4.app.Fragment
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.ui.main.MainFragment
import ru.aleksandrtrushchinskii.ocolo.ui.signin.SignInFragment
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var auth: Authentication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (auth.isAuth) {
            startFragment(SignInFragment())
        } else {
            startFragment(MainFragment())
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
    }

    fun finishFragment(fragment: Fragment) {
        when (fragment::class) {
            SignInFragment::class -> startFragment(MainFragment())
            MainFragment::class -> if (!auth.isAuth) startFragment(SignInFragment())
            else -> println(fragment)
        }
    }

}