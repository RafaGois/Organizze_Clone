package com.example.organizze_clone.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.organizze_clone.R;
import com.example.organizze_clone.databinding.ActivityPrincipalBinding;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal2);



        listenerReceita();
        listenerDespesa();
    }

    private void listenerReceita () {
        FloatingActionButton fabReceita = findViewById(R.id.fab);

        fabReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Receita", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void listenerDespesa () {
        FloatingActionButton fabReceita = findViewById(R.id.fab2);

        fabReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Despesa", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}