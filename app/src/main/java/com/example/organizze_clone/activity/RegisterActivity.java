package com.example.organizze_clone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.organizze_clone.R;
import com.example.organizze_clone.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;

    private EditText inputNome, inputEmail, inputSenha;
    private Button btnCadastro;

    //todo ao clicar om enter no senha esconder teclado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputNome = findViewById(R.id.inputNome);
        inputEmail = findViewById(R.id.inputEmail);
        inputSenha = findViewById(R.id.inputSenha);

        btnCadastro = findViewById(R.id.btnCadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = inputNome.getText().toString();
                String email = inputEmail.getText().toString();
                String senha = inputSenha.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Informe todos os valores", Toast.LENGTH_SHORT).show();
                } else {
                    cadastrarUsuario(email,senha);
                }

            }
        });

        hideKeyboard();

    }

    private void hideKeyboard () {

        EditText input = findViewById(R.id.inputSenha);

        input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_ENTER) {

                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            input.getWindowToken(), 0);

                    return true;
                } else {
                    return false;
                }
            }
        });



    }
    
    public void cadastrarUsuario (String email, String senha) {

        auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        auth.createUserWithEmailAndPassword(email, senha);

    }
}