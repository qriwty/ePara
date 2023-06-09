package com.ebunnygroup.epara.data.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class HomeViewModel : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun onLogout(onLogoutClicked: () -> Unit) {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        onLogoutClicked.invoke()

        val authStateListener = FirebaseAuth.AuthStateListener {
//            if (it.currentUser == null) {
//
//            } else {
//
//            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }

    fun checkForActiveSession() {
        isUserLoggedIn.value = FirebaseAuth.getInstance().currentUser != null
    }

    val emailId: MutableLiveData<String> = MutableLiveData()
    val nameId: MutableLiveData<String> = MutableLiveData()

    fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
            it.displayName?.also { name ->
                nameId.value = name
            }
        }
    }

}