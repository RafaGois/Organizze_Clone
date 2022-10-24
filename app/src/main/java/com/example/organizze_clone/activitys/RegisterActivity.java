package com.example.organizze_clone.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze_clone.R;
import com.example.organizze_clone.config.ConfiguracaoFirebase;
import com.example.organizze_clone.helper.Base64Custom;
import com.example.organizze_clone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private Usuario usuario;

    private EditText inputNome, inputEmail, inputSenha;
    private Button btnCadastro;

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

                    usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    cadastrarUsuario();
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
    
    public void cadastrarUsuario () {

        auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //todo mudar para snackbar
                if (task.isSuccessful()) {

                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();

                    finish();

                } else {

                    String exception = "";
                    try {
                        throw task.getException();
                    } catch ( FirebaseAuthWeakPasswordException e) {
                        exception = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "Email inválido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        exception = "Conta já cadastrada";
                    } catch (Exception e) {
                        exception = "erro";
                        e.printStackTrace();
                    }
                    Toast.makeText(RegisterActivity.this, "Erro! " + exception, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}