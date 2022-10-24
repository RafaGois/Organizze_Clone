package com.example.organizze_clone.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze_clone.R;
import com.example.organizze_clone.config.ConfiguracaoFirebase;
import com.example.organizze_clone.config.DateCustom;
import com.example.organizze_clone.helper.Base64Custom;
import com.example.organizze_clone.model.Movimentacao;
import com.example.organizze_clone.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Despesa extends AppCompatActivity {

    private TextInputEditText inputData, inputCategoria, inputDescricao;
    private EditText inputValor;
    private Movimentacao movimentacao;
    private DatabaseReference fireBaseRef = ConfiguracaoFirebase.getFirebase();
    private FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private double despesaTotal;
    private double despesaGerada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        inputData = findViewById(R.id.inputData);
        inputCategoria = findViewById(R.id.inputCategoria);
        inputDescricao = findViewById(R.id.inputDescricao);
        inputValor = findViewById(R.id.inputValorReceita);

        inputData.setText(DateCustom.dataAtual());

        recuperaDespesaTotal();
    }

    public void salvarDespesa (View v) {

        if (validarCamposDespesa()) {
            movimentacao = new Movimentacao();
            String data = inputData.getText().toString();
            Double valorRecuperado = Double.parseDouble(inputValor.getText().toString());

            movimentacao.setValor( valorRecuperado );
            movimentacao.setCategoria( inputCategoria.getText().toString() );
            movimentacao.setDescricao( inputDescricao.getText().toString() );
            movimentacao.setData( inputData.getText().toString() );
            movimentacao.setTipo( "d" );

            despesaGerada = valorRecuperado;
            double despesaAtualizada = despesaTotal + despesaGerada;
            atualizarDespesa(despesaAtualizada);


            movimentacao.salvar(data);
        }
    }

    public Boolean validarCamposDespesa () {

        String textoValor = inputValor.getText().toString();
        String textoData = inputData.getText().toString();
        String textoCategoria = inputCategoria.getText().toString();
        String textoDescricao = inputDescricao.getText().toString();

        if (textoValor.isEmpty()) {

            Toast.makeText(this, "Informe o valor", Toast.LENGTH_SHORT).show();
            return false;
        } else if (textoData.isEmpty()) {
            Toast.makeText(this, "Informe a data", Toast.LENGTH_SHORT).show();
            return false;
        } else if (textoCategoria.isEmpty()) {
            Toast.makeText(this, "Informe a categoria", Toast.LENGTH_SHORT).show();
            return false;
        } else if (textoDescricao.isEmpty()) {
            Toast.makeText(this, "Informe a descrição", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(this, "Despesa salva!", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void recuperaDespesaTotal () {

        String emailUser =  auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUser);
        DatabaseReference usuaruoRef = fireBaseRef.child("usuarios").child( idUsuario );

        usuaruoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue( Usuario.class );//converte do firebase em um objeto java
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void atualizarDespesa ( double despesaAtualizada) {

        String emailUser =  auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUser);
        DatabaseReference usuaruoRef = fireBaseRef.child("usuarios").child( idUsuario );

        usuaruoRef.child("despesaTotal").setValue(despesaAtualizada);
    }
}