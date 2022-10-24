package com.example.organizze_clone.config;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

public class DateCustom {

    public static String dataAtual () {

        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(date);

        return dataString;
    }

    public static String mesAnoDataEscolhida (String data) {

        String retornoData[] =  data.split("/");
        String dia = retornoData[0];
        String mes = retornoData[1];
        String ano = retornoData[2];

        String mesAno = mes + ano;
        return mesAno;
    }
}
