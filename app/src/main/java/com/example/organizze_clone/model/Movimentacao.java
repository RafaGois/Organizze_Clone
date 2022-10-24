package com.example.organizze_clone.model;

import com.example.organizze_clone.config.ConfiguracaoFirebase;
import com.example.organizze_clone.config.DateCustom;
import com.example.organizze_clone.helper.Base64Custom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Movimentacao {

    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private double valor;

    public Movimentacao() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void salvar(String dataEscolhida) {

        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUser = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());

        String mesAno = DateCustom.mesAnoDataEscolhida(dataEscolhida);

        DatabaseReference reference = ConfiguracaoFirebase.getFirebase();
        reference.child("movimetacao")
                .child( idUser )
                .child( mesAno )
                .push().setValue(this);
    }
}
