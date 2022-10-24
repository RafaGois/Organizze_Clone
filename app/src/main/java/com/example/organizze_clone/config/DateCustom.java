package com.example.organizze_clone.config;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

public class DateCustom {

    public static String dataAtual () {

        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String dataString = simpleDateFormat.format(date);

        return dataString;
    }
}
