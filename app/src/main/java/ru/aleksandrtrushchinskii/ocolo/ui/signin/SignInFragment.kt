package ru.aleksandrtrushchinskii.ocolo.ui.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_sign_in.*
import ru.aleksandrtrushchinskii.ocolo.R
import ru.aleksandrtrushchinskii.ocolo.common.util.inflate


class SignInFragment : DaggerFragment() {
    companion object {
        private const val RC_SIGN_IN = 100
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_sign_in)
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
                Toast.makeText(context, "Sign In was Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Sign In was Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}