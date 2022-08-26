package DiegoE.proyecto;

import arbolAVL.ArbolAVL;
import arbolAVL.Nodo;
import java.util.ArrayList;

public class Juego {

    private ArbolAVL arbol;
    private ArrayList<Integer> valoresUnicos; //ESTE ARRAY ES PARA GUARDAR UNICAMENTE NUMEROS QUE ACOMPAÑAN A LOS SIMBOLOS
    private ArrayList<String> simbolos;
    private ArrayList<Nodo> nodosPorNivel;
    private ArrayList<String> numerosOrdenados;

    public Juego() {
        arbol = new ArbolAVL();
    }

    public int insertar(String json) {

        ArrayList<Integer> numeros = parsearJson(json);
        ArrayList<Integer> valoresUnicos = this.valoresUnicos;

        for (int i = 0; i < valoresUnicos.size(); i++) {
            if (valoresUnicos.get(i) > 13 || valoresUnicos.get(i) < 1) {
                return 400;
            }
        }

        for (int i = 0; i < numeros.size(); i++) {

            if (arbol.existeNodo(numeros.get(i), arbol.getNodoRaiz())) {
                return 406;
            }
        }

        //insertamos los valores al arbol
        for (int i = 0; i < numeros.size(); i++) {
            arbol.insertar(numeros.get(i), valoresUnicos.get(i), simbolos.get(i));
        }

        System.out.println("VALORES INSERTADOS CORRECTAMENTE");
        return 200;
    }

    public int eliminar(String json) {
        ArrayList<Integer> numeros = parsearJson(json);
        ArrayList<Integer> numerosSinCorrimiento = this.valoresUnicos;

        for (int i = 0; i < numerosSinCorrimiento.size(); i++) {
            if (numerosSinCorrimiento.get(i) > 13) {
                return 400;
            }
        }

        if (sumanTrece(numerosSinCorrimiento)) {
            for (int i = 0; i < numeros.size(); i++) {
                if (!arbol.existeNodo(numeros.get(i), arbol.getNodoRaiz())) {
                    return 404;
                }
                Nodo nodo = arbol.buscarNodo(numeros.get(i), arbol.getNodoRaiz());
                if (!arbol.esHoja(nodo)) {
                    return 409;
                }
                int eliminado = arbol.eliminarNodo(numeros.get(i), arbol.getNodoRaiz(), arbol.getNodoRaiz());
            }
            return 200;
        } else {
            return 406;
        }
    }

    public int iniciarJuego(String json) {
        ArrayList<Integer> numeros = parsearJson(json);

        ArrayList<Integer> valoresUnicos = this.valoresUnicos;
        
        
        //VEMOS SI EL VALOR UNICO (SIN CORRIMIENTO) DEBE SER MENOR A 13 Y MAYOR A 1
        for (int i = 0; i < valoresUnicos.size(); i++) {
            if (valoresUnicos.get(i) > 13 || valoresUnicos.get(i) < 1) {
                return 400;
            }
        }
        
        //VEMOS SI LA CARTA EXISTE O NO 
        for (int i = 0; i < numeros.size(); i++) {

            if (arbol.existeNodo(numeros.get(i), arbol.getNodoRaiz())) {
                return 406;
            }
        }

        //insertamos los valores al arbol
        for (int i = 0; i < numeros.size(); i++) {
            arbol.insertar(numeros.get(i), valoresUnicos.get(i), simbolos.get(i));
        }
        System.out.println("Inserción con éxito");
        return 200;
    }

    public void buscarNivel(int nivel) {
        nodosPorNivel = new ArrayList<>();
        arbol.buscarNivel(nodosPorNivel, arbol.getNodoRaiz(), nivel);
    }

    public String nodosObtenidos() {
        String json = "{\n";

        for (int i = 0; i < this.nodosPorNivel.size(); i++) {
            json += "\"" + i + "\":\"" + nodosPorNivel.get(i).getValorUnico() + nodosPorNivel.get(i).getSimbolo() + "\"\n";
        }
        json += "}";

        return json;
    }

    public void actualizarNivel() {
        arbol.asignarNivel(arbol.getNodoRaiz());
    }

    public ArrayList<Integer> parsearJson(String json) {
        simbolos = new ArrayList<>();
        valoresUnicos = new ArrayList<>();
        //SEPARAMOS EL JSON CON COMA
        String datos[] = json.split(",");

        /*AHORA CADA LINEA LA SEPARAMOS DE LOS DOS PUNTOS, Y AGREGAMOS SOLO LO QUE ESTÁ DEL LADO DERECHO
        POR EJEMPLO "insert":"4corazon"  ENTONCES SOLO AGREGAMOS EL "4", QUE SERÍA EL NUMERO A INSERTAR AL ARBOL*/
        ArrayList<String> numerosTexto = new ArrayList<>();
        for (int i = 0; i < datos.length; i++) {
            String[] datosNumeros = datos[i].split(":");
            numerosTexto.add(datosNumeros[1]);
        }

        /*LUEGO JALAMOS SOLO LOS DIGITOS QUE HAY, POR EJEMPLO
        "4corazon", SOLO JALAMOS EL 4
        SI VINIERA UN "J♣" JALAMOS J
        " 4 corazon "  numero -> 4  corrimiento -> 20
        " 5 corazon "  numero -> 4  corrimiento -> 20
        " 7 trebol "    numero -> 4  corrimiento -> 20
        " 3 pica "    numero -> 4  corrimiento -> 20
         */
        ArrayList<String> numeros = new ArrayList<>();
        ArrayList<Integer> corrimientos = new ArrayList<>();
        for (int i = 0; i < numerosTexto.size(); i++) {
            char[] caracteres = numerosTexto.get(i).toCharArray();
            String numero = "";
            int corrimiento = 0;
            for (int j = 0; j < caracteres.length; j++) {
                if (Character.isDigit(caracteres[j])) {
                    numero += caracteres[j];
                    corrimiento = agregarCorrimiento(caracteres[j + 1], simbolos);

                }
                if (esLetra(caracteres[j])) {
                    int valorLetra = obtenerValorLetra(caracteres[j]);
                    numero += valorLetra;
                    corrimiento = agregarCorrimiento(caracteres[j + 1], simbolos);
                }
            }
            numeros.add(numero);
            corrimientos.add(corrimiento);
        }

        /*INSERTAMOS LOS NUMEROS OBTENIDOS AL ARRAY YA DE INTEGER DE NUMEROS*/
        ArrayList<Integer> valorNumeros = new ArrayList<>();
        for (int i = 0; i < numeros.size(); i++) {
            int numero = Integer.parseInt(numeros.get(i)) + corrimientos.get(i);
            int numeroUnico = Integer.parseInt(numeros.get(i)); //ESTE NUMERO ES SOLAMENTE EL DIGITO QUE ACOMPAÑA AL SIMBOLO ES DECIR 4♥, SOLO SERÍA 4 
            valoresUnicos.add(numeroUnico);
            System.out.println(numeroUnico);
            valorNumeros.add(numero);
        }

        return valorNumeros;
    }

    public boolean esLetra(char caracter) {
        return (caracter == 'J' || caracter == 'Q' || caracter == 'K' || caracter == 'A');
    }

    public int obtenerValorLetra(char caracter) {

        if (caracter == 'A') {
            return 1;
        }

        if (caracter == 'J') {
            return 11;
        }
        if (caracter == 'Q') {
            return 12;
        }
        if (caracter == 'K') {
            return 13;
        }

        return 0;
    }

    public int agregarCorrimiento(char caracter, ArrayList<String> simbolos) {

        if (caracter == '♣') {
            simbolos.add(String.valueOf(caracter));
            return 0;
        }
        if (caracter == '♥') {
            simbolos.add(String.valueOf(caracter));
            return 40;
        }
        if (caracter == '♦') {
            simbolos.add(String.valueOf(caracter));
            return 20;
        }
        if (caracter == '♠') {
            simbolos.add(String.valueOf(caracter));
            return 60;
        }

        return 0;
    }

    //ORDENAMIENTOS DEL ARBOL:  EN ORDEN, PRE-ORDEN , POST-ORDEN
    public String enOrden() {
        String texto = "";
        numerosOrdenados = new ArrayList<>();
        arbol.enOrden(numerosOrdenados);
        texto += "{\n";
        for (int i = 0; i < numerosOrdenados.size(); i++) {
            texto += "\"" + i + "\":" + "\"" + numerosOrdenados.get(i) + "\"\n";
        }
        texto += "}";
        return texto;
    }

    public String preOrden() {
        String texto = "";
        numerosOrdenados = new ArrayList<>();
        arbol.preOrden(numerosOrdenados);
        texto += "{\n";
        for (int i = 0; i < numerosOrdenados.size(); i++) {
            texto += "\"" + i + "\":" + "\"" + numerosOrdenados.get(i) + "\"\n";
        }
        texto += "}";

        return texto;
    }

    public String postOrden() {
        String texto = "";
        numerosOrdenados = new ArrayList<>();
        arbol.postOrden(numerosOrdenados);
        texto += "{\n";
        for (int i = 0; i < numerosOrdenados.size(); i++) {
            texto += "\"" + i + "\":" + "\"" + numerosOrdenados.get(i) + "\"\n";
        }
        texto += "}";

        return texto;
    }

    public boolean sumanTrece(ArrayList<Integer> numeros) {

        int sumatoria = 0;
        for (int i = 0; i < numeros.size(); i++) {
            sumatoria += numeros.get(i);
        }

        return (sumatoria == 13);
    }

    public String generarGrafica() {

        return arbol.dibujar();
    }

}
