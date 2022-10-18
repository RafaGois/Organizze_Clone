package com.example.organizze_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

import java.util.Objects;

public class MainActivity extends IntroActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

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
                .canGoBackward(false)
                .canGoForward(false)
                .build()
        );

/*
        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new SimpleSlide.Builder()
                .title("Título 1")
                .description("Descricao 1")
                .image(R.drawable.um)
                .background(android.R.color.holo_blue_bright)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Título 2")
                .description("Descricao 2")
                .image(R.drawable.dois)
                .background(android.R.color.holo_blue_dark)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Título 3")
                .description("Descricao 3")
                .image(R.drawable.tres)
                .background(android.R.color.holo_blue_light)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Título 4")
                .description("Descricao 4")
                .image(R.drawable.quatro)
                .background(android.R.color.holo_orange_light)
                .build());

 */

    }
}