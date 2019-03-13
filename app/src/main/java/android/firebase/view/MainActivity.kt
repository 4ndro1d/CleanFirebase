package android.firebase.view

import android.content.Intent
import android.firebase.R
import android.firebase.authentication.AuthenticationActivity
import android.firebase.presentation.MainActivityPresenter
import android.firebase.presentation.MainActivityView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainActivityView {

    private val presenter: MainActivityPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.start(this)
    }

    override fun doSomething() {
        startActivity(Intent(this, AuthenticationActivity::class.java))
    }
}
