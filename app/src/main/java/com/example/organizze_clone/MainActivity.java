package com.example.organizze_clone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizze_clone.activitys.LoginActivity;
import com.example.organizze_clone.activitys.Principal;
import com.example.organizze_clone.activitys.PrincipalActivity;
import com.example.organizze_clone.activitys.RegisterActivity;
import com.example.organizze_clone.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import java.util.Objects;

public class MainActivity extends IntroActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        isUserLogado();

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_orange_light)
                .fragment(R.layout.intro_1)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoBackward(false)
                .canGoForward(false)
                .build()
        );
    }

    @Override
    protected void onStart() {
        super.onStart();

        isUserLogado();
    }

    public void btnEntrar (View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void btnCadastrar (View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void isUserLogado () {
        auth = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if (auth.getCurrentUser() != null) {
             abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal () {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    private void fab () {

    }
}