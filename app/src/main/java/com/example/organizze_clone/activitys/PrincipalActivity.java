package com.example.organizze_clone.activitys;

import android.content.Intent;
import android.os.Bundle;

import com.example.organizze_clone.MainActivity;
import com.example.organizze_clone.R;
import com.example.organizze_clone.config.ConfiguracaoFirebase;
import com.example.organizze_clone.databinding.ActivityPrincipalBinding;
import com.example.organizze_clone.helper.Base64Custom;
import com.example.organizze_clone.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toolbar;

import java.text.DecimalFormat;
import java.util.Objects;

public class PrincipalActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;

    private TextView txtSaudacao, txtSaldo;
    private Double despesaTotal = 0.0;
    private Double receitaTotal = 0.0;
    private Double resumoUsuario = 0.0;

    private DatabaseReference fireBaseRef = ConfiguracaoFirebase.getFirebase();
    private FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);

        txtSaudacao = findViewById(R.id.textSaudacao);
        txtSaldo = findViewById(R.id.textSaldo);

        calendarView = findViewById(R.id.calendarView);
        configuraCalendarView();
        recuperarResumo();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_sair:
                auth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void recuperarResumo() {

        String emailUser =  auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUser);
        DatabaseReference usuaruoRef = fireBaseRef.child("usuarios").child( idUsuario );

        usuaruoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuario = snapshot.getValue( Usuario.class);

                despesaTotal = usuario.getDespesaTotal();
                receitaTotal = usuario.getReceitaTotal();
                resumoUsuario = receitaTotal - despesaTotal;

                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                String resultadoFormatado = decimalFormat.format( resumoUsuario );

                txtSaudacao.setText("Olá " + usuario.getNome());
                txtSaldo.setText("R$ " + resultadoFormatado);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void configuraCalendarView() {

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });
    }


    public void adicionarReceita (View view) {
        Intent intent = new Intent(this,Receita.class);
        startActivity(intent);
    }

    public void adicionarDespesa (View view) {
        Intent intent = new Intent(this,Despesa.class);
        startActivity(intent);
    }
}