package com.example.organizze_clone.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.organizze_clone.R;
import com.example.organizze_clone.config.DateCustom;
import com.google.android.material.textfield.TextInputEditText;

public class Despesa extends AppCompatActivity {

    private TextInputEditText inputData, inputCategoria, inputDescricao;
    private EditText inputValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        inputData = findViewById(R.id.inputData);
        inputCategoria = findViewById(R.id.inputCategoria);
        inputDescricao = findViewById(R.id.inputDescricao);
        inputValor = findViewById(R.id.inputValor);

        inputData.setText(DateCustom.dataAtual());

    }
}