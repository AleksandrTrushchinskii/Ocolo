package ru.aleksandrtrushchinskii.ocolo.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.sign_in_fragment.*
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.service.Internet
import ru.aleksandrtrushchinskii.ocolo.common.util.finish
import ru.aleksandrtrushchinskii.ocolo.common.util.inflate
import javax.inject.Inject


class SignInFragment : DaggerFragment() {

    companion object {
        private const val RC_SIGN_IN = 100
    }


    @Inject
    lateinit var internet: Internet


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.sign_in_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_in_button.setOnClickListener {
            val providers = listOf(AuthUI.IdpConfig.GoogleBuilder().build())

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(context, "SignIn was Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                internet.ifAvailable {
                    Toast.makeText(context, "SignIn was Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}