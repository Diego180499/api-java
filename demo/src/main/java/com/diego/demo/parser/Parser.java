package com.diego.demo.parser;

import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> insert(String json) {

        String[] datos = json.split(",");
        ArrayList<String> datos2 = new ArrayList<>(); // json separados por la coma
        /* { "{nombre:Diego" , "apellido:Estrada" , "facebook:Dieguin" } */

        for (int i = 0; i < datos.length; i++) {
            datos2.add(datos[i]);
        }

        ArrayList<String> datos3 = new ArrayList<>(); // datos separados por los dos puntos

        for (int i = 0; i < datos2.size(); i++) {
            String[] datosSeparados = datos2.get(i).split(":");
            if(datosSeparados[1].contains("\"")){
                datosSeparados[1] = datosSeparados[1].replace("\"","");
            }
            if(datosSeparados[1].contains("}")){
                datosSeparados[1] = datosSeparados[1].replace("}","");
            }
            datos3.add(datosSeparados[1]);
        }

        return datos3;
    }

}
