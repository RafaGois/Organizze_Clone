package com.example.organizze_clone.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze_clone.R;
import com.example.organizze_clone.config.ConfiguracaoFirebase;
import com.example.organizze_clone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail,inputSenha;
    private Button btnLogin;

    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);

        inputEmail = findViewById(R.id.inputLoginEmail);
        inputSenha = findViewById(R.id.inputLoginSenha);

        btnLogin = findViewById(R.id.btnEntrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                String senha = inputSenha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Informe todos os valores", Toast.LENGTH_SHORT).show();
                } else {

                    usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    validarLogin();
                }
            }
        });
    }

    public void validarLogin () {

        auth = ConfiguracaoFirebase.getFirebaseAutenticacao();

        auth.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    abrirTelaPrincipal();

                } else {

                    String exception = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "Usuário não cadastrado";
                    } catch (FirebaseAuthInvalidUserException e) {
                        exception = "Email inválido ";
                    } catch (Exception e) {
                        exception = "erro";
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, exception, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal () {
        Intent intent = new Intent(this,Principal.class);
        startActivity(intent);
    }
}