package com.example.organizze_clone.config;

import android.text.PrecomputedText;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth auth;

    public static FirebaseAuth getFirebaseAutenticacao () {

        if (auth == null) {
            return auth  =FirebaseAuth.getInstance();
        } else {
            return auth;
        }
    }
}
