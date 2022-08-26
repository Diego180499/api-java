package arbolAVL;

import java.util.ArrayList;

public class Nodo {

    private int valor;
    private int altura;
    private int nivel;
    private int factorEquilibrio;
    private boolean esHoja;
    private Nodo hijoIzquierdo;
    private Nodo hijoDerecho;
    private int valorUnico;
    private String simbolo;

    public Nodo() {
    }

    public Nodo(int valor) {
        this.valor = valor;
        this.altura = 0;
        this.nivel = 0;
        this.esHoja = false;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.factorEquilibrio = 0;
    }

    public int getValor() {
        return valor;
    }

    public void enOrden(ArrayList<String> numeros) {

        if (hijoIzquierdo != null) {
            hijoIzquierdo.enOrden(numeros);
        }

        numeros.add(this.valorUnico + this.simbolo);

        if (hijoDerecho != null) {
            hijoDerecho.enOrden(numeros);
        }
    }

    public void preOrden(ArrayList<String> numeros) {

        numeros.add(this.valorUnico + this.simbolo);

        if (hijoIzquierdo != null) {
            hijoIzquierdo.preOrden(numeros);
        }
        if (hijoDerecho != null) {
            hijoDerecho.preOrden(numeros);
        }
    }

    public void postOrden(ArrayList<String> numeros) {

        if (hijoIzquierdo != null) {
            hijoIzquierdo.postOrden(numeros);
        }

        if (hijoDerecho != null) {
            hijoDerecho.postOrden(numeros);
        }

        numeros.add(this.valorUnico + this.simbolo);
    }

    public String textoGraphviz() {

        if (hijoIzquierdo == null && hijoDerecho == null) {
            String simbolo = letraSimbolo(this.simbolo);
            return String.valueOf("<" + this.valorUnico + simbolo + ">");
        } else {
            String texto = "";
            String simbolo = letraSimbolo(this.simbolo);
            if (hijoIzquierdo != null) {
                texto = "<" + this.valorUnico + simbolo + ">" + "->" + hijoIzquierdo.textoGraphviz() + "\n";
            }

            if (hijoDerecho != null) {
                texto += "<" + this.valorUnico + simbolo + ">" + "->" + hijoDerecho.textoGraphviz() + "\n";
            }

            return texto;
        }

    }

    public String letraSimbolo(String simbolo) {

        if (simbolo.equals("♣")) {
            return "T";
        }
        if (simbolo.equals("♥")) {
            return "C";
        }
        if (simbolo.equals("♦")) {
            return "D";
        }
        if (simbolo.equals("♠")) {
            return "P";
        }

        return "";

    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Nodo getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isEsHoja() {
        return esHoja;
    }

    public void setEsHoja(boolean esHoja) {
        this.esHoja = esHoja;
    }

    public int getFactorEquilibrio() {
        return factorEquilibrio;
    }

    public void setFactorEquilibrio(int factorEquilibrio) {
        this.factorEquilibrio = factorEquilibrio;
    }

    public int getValorUnico() {
        return valorUnico;
    }

    public void setValorUnico(int valorUnico) {
        this.valorUnico = valorUnico;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

}
