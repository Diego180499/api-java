package arbolAVL;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ArbolAVL {

    private Nodo nodoRaiz;
    private int altura;
    private int nivel;
    private int cantidadImagen;

    public ArbolAVL() {
        this.nodoRaiz = null;
        this.altura = 0;
        this.nivel = 0;
        this.cantidadImagen = 0;
    }

    /*METODOS DE LAS ACCIONES DEL ARBOL*/
    //BUSCAR UN NODO
    public Nodo buscarNodo(int dato, Nodo raiz) {

        try {
            if (nodoRaiz == null) {
                return null;
            } else if (raiz.getValor() == dato) {
                return raiz;
            } else if (raiz.getValor() < dato) {
                return buscarNodo(dato, raiz.getHijoDerecho());
            } else {
                return buscarNodo(dato, raiz.getHijoIzquierdo());
            }
        } catch (NullPointerException np) {
            return null;
        }

    }

    public boolean existeNodo(int valor, Nodo nodoRaiz) {
        return buscarNodo(valor, nodoRaiz) != null;
    }

    private int obtenerFactorEquilibrio(Nodo nodo) {

        if (nodo == null) {
            return -1;
        } else {
            return nodo.getFactorEquilibrio();
        }

    }

    private Nodo rotacionIzquierda(Nodo nodo) {

        Nodo auxiliar = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(auxiliar.getHijoDerecho());
        auxiliar.setHijoDerecho(nodo);
        int feIzquierdo = obtenerFactorEquilibrio(nodo.getHijoIzquierdo());
        int feDerecho = obtenerFactorEquilibrio(nodo.getHijoDerecho());
        int feIzquierdo2 = obtenerFactorEquilibrio(auxiliar.getHijoIzquierdo());
        int feDerecho2 = obtenerFactorEquilibrio(auxiliar.getHijoDerecho());
        nodo.setFactorEquilibrio(Math.max(feIzquierdo, feDerecho + 1));
        auxiliar.setFactorEquilibrio(Math.max(feIzquierdo2, feDerecho2 + 1));

        return auxiliar;
    }

    private Nodo rotacionDerecha(Nodo nodo) {

        Nodo auxiliar = nodo.getHijoDerecho();
        nodo.setHijoDerecho(auxiliar.getHijoIzquierdo());
        auxiliar.setHijoIzquierdo(nodo);
        int feIzquierdo = obtenerFactorEquilibrio(nodo.getHijoIzquierdo());
        int feDerecho = obtenerFactorEquilibrio(nodo.getHijoDerecho());
        int feIzquierdo2 = obtenerFactorEquilibrio(auxiliar.getHijoIzquierdo());
        int feDerecho2 = obtenerFactorEquilibrio(auxiliar.getHijoDerecho());
        nodo.setFactorEquilibrio(Math.max(feIzquierdo, feDerecho + 1));
        auxiliar.setFactorEquilibrio(Math.max(feIzquierdo2, feDerecho2 + 1));

        return auxiliar;
    }

    private Nodo rotacionDobleIzquierda(Nodo nodo) {

        Nodo temporal;
        nodo.setHijoIzquierdo(rotacionDerecha(nodo.getHijoIzquierdo()));
        temporal = rotacionIzquierda(nodo);

        return temporal;
    }

    private Nodo rotacionDobleDerecha(Nodo nodo) {

        Nodo temporal;
        nodo.setHijoDerecho(rotacionIzquierda(nodo.getHijoDerecho()));
        temporal = rotacionDerecha(nodo);

        return temporal;
    }

    private Nodo insertarAVL(Nodo nuevo, Nodo subArbol) {

        Nodo nuevoPadre = subArbol;

        if (nuevo.getValor() < subArbol.getValor()) {
            if (subArbol.getHijoIzquierdo() == null) {
                subArbol.setHijoIzquierdo(nuevo);
            } else {
                subArbol.setHijoIzquierdo(insertarAVL(nuevo, subArbol.getHijoIzquierdo()));
                if ((obtenerFactorEquilibrio(subArbol.getHijoIzquierdo()) - obtenerFactorEquilibrio(subArbol.getHijoDerecho())) == 2) {
                    if (nuevo.getValor() < subArbol.getHijoIzquierdo().getValor()) {
                        nuevoPadre = rotacionIzquierda(subArbol);
                    } else {
                        nuevoPadre = rotacionDobleIzquierda(subArbol);
                    }
                }
            }

        } else if (nuevo.getValor() > subArbol.getValor()) {
            if (subArbol.getHijoDerecho() == null) {
                subArbol.setHijoDerecho(nuevo);
            } else {
                subArbol.setHijoDerecho(insertarAVL(nuevo, subArbol.getHijoDerecho()));
                if ((obtenerFactorEquilibrio(subArbol.getHijoDerecho()) - obtenerFactorEquilibrio(subArbol.getHijoIzquierdo())) == 2) {
                    if (nuevo.getValor() > subArbol.getHijoDerecho().getValor()) {
                        nuevoPadre = rotacionDerecha(subArbol);
                    } else {
                        nuevoPadre = rotacionDerecha(subArbol);
                    }
                }

            }
        } else {
            return null; //ESTE ERROR ES PORQUE EL NODO ESTÁ   ***D U P  L I C A D O***
        }

        if ((subArbol.getHijoIzquierdo() == null) && (subArbol.getHijoDerecho() != null)) {
            subArbol.setFactorEquilibrio(subArbol.getHijoDerecho().getFactorEquilibrio() + 1);
        } else if ((subArbol.getHijoDerecho() == null) && (subArbol.getHijoIzquierdo() != null)) {
            subArbol.setFactorEquilibrio(subArbol.getHijoIzquierdo().getFactorEquilibrio() + 1);
        } else {
            int feIzquierdo = obtenerFactorEquilibrio(subArbol.getHijoIzquierdo());
            int feDerecho = obtenerFactorEquilibrio(subArbol.getHijoDerecho());
            subArbol.setFactorEquilibrio(Math.max(feIzquierdo, feDerecho));
        }

        return nuevoPadre;
    }

    /*INSERTA CARTA AL ARBOL*/
    public int insertar(int valor, int valorUnico, String simbolo) {

        Nodo nodoNuevo = new Nodo(valor);
        nodoNuevo.setValorUnico(valorUnico);
        nodoNuevo.setSimbolo(simbolo);

        if (this.nodoRaiz == null) {
            this.nodoRaiz = nodoNuevo;
        } else {
            this.nodoRaiz = insertarAVL(nodoNuevo, nodoRaiz);
            if (this.nodoRaiz == null) {
                return 406;
            }
        }

        return 200;
    }

    public void asignarNivel(Nodo nodoRaiz) {
        Nodo nodoIzquierdo = null;
        Nodo nodoDerecho = null;

        if (nodoRaiz.getHijoIzquierdo() != null) {
            int nivelHijo = nodoRaiz.getNivel() + 1;
            nodoRaiz.getHijoIzquierdo().setNivel(nivelHijo);
            nodoIzquierdo = nodoRaiz.getHijoIzquierdo();
        }

        if (nodoRaiz.getHijoDerecho() != null) {
            int nivelHijo = nodoRaiz.getNivel() + 1;
            nodoRaiz.getHijoDerecho().setNivel(nivelHijo);
            nodoDerecho = nodoRaiz.getHijoDerecho();
        }

        if (nodoIzquierdo != null) {
            asignarNivel(nodoIzquierdo);
        }
        if (nodoDerecho != null) {
            asignarNivel(nodoDerecho);
        }

    }

    public void buscarNivel(ArrayList<Nodo> nodos, Nodo nodoRaiz, int nivel) {

        if (nodoRaiz.getNivel() == nivel) {
            nodos.add(nodoRaiz);
            return;
        }

        if (nodoRaiz.getHijoIzquierdo() != null) {
            if (nodoRaiz.getHijoIzquierdo().getNivel() == nivel) {
                nodos.add(nodoRaiz.getHijoIzquierdo());
            } else {
                Nodo tempRaiz = nodoRaiz.getHijoIzquierdo();
                buscarNivel(nodos, tempRaiz, nivel);
            }
        }

        if (nodoRaiz.getHijoDerecho() != null) {
            if (nodoRaiz.getHijoDerecho().getNivel() == nivel) {
                nodos.add(nodoRaiz.getHijoDerecho());
            } else {
                Nodo tempRaiz = nodoRaiz.getHijoDerecho();
                buscarNivel(nodos, tempRaiz, nivel);
            }
        }

    }

    //ELIMINACION EN EL ARBOL AVL
    //pendiente de terminar xd
    public int eliminarNodo(int valor, Nodo nodoRaiz, Nodo nodoPadre) {

        if (nodoRaiz.getValor() == valor) {
            if (esHoja(nodoRaiz)) {
                return eliminarNodoHoja(valor, nodoRaiz, nodoPadre);
            }
        }

        if (valor < nodoRaiz.getValor()) {
            Nodo padre = nodoRaiz;
            Nodo raiz = nodoRaiz.getHijoIzquierdo();
            eliminarNodo(valor, raiz, padre);
        }

        if (valor > nodoRaiz.getValor()) {
            Nodo padre = nodoRaiz;
            Nodo raiz = nodoRaiz.getHijoDerecho();
            eliminarNodo(valor, raiz, padre);
        }

        return 200;
    }

    //metodo para ir agregando los hijos del nodoHijoDerecho
    private void agregarHijos(Nodo nodoRaiz, ArrayList<Nodo> hijos) {

        if (esHoja(nodoRaiz)) {
            hijos.add(nodoRaiz);
        }

        if (nodoRaiz.getHijoIzquierdo() != null) {
            hijos.add(nodoRaiz.getHijoIzquierdo());
            Nodo raizNueva = nodoRaiz.getHijoIzquierdo();
            agregarHijos(raizNueva, hijos);
        }

        if (nodoRaiz.getHijoDerecho() != null) {
            hijos.add(nodoRaiz.getHijoDerecho());
            Nodo raizNueva = nodoRaiz.getHijoDerecho();
            agregarHijos(raizNueva, hijos);
        }

    }

    //ELIMINACION DE UN NODO HOJA
    private int eliminarNodoHoja(int valor, Nodo nodoRaiz, Nodo nodoPadre) {

        if (valor > nodoPadre.getValor()) {
            nodoPadre.setHijoDerecho(null);
        } else if (valor < nodoPadre.getValor()) {
            nodoPadre.setHijoIzquierdo(null);
        }

        return 200;
    }

    //VEMOS SI EL NODO ES UNA HOJA
    public boolean esHoja(Nodo nodo) {
        return ((nodo.getHijoIzquierdo() == null) && (nodo.getHijoDerecho() == null));
    }

    public void enOrden(ArrayList<String> numeros) {
        this.nodoRaiz.enOrden(numeros);

    }

    public void preOrden(ArrayList<String> numeros) {
        this.nodoRaiz.preOrden(numeros);
    }

    public void postOrden(ArrayList<String> numeros) {
        this.nodoRaiz.postOrden(numeros);
    }

    public String obtenerGraphviz() {

        String texto = "digraph G\n"
                + "{\n"
                + "    node[shape = cicle]\n"
                + "    node[style = filled]\n"
                + "    node[fillcolor = \"#008000\"]\n"
                + "    node[color = \"#008000\"]\n"
                + "    edge[color = \"#6C541E\"]\n";

        if (nodoRaiz != null) {
            texto += nodoRaiz.textoGraphviz();
        }

        texto += "}\n";

        return texto;
    }

    public void escribirArchivo(String ruta, String contenido) {

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(ruta);
            pw = new PrintWriter(archivo);
            pw.write(contenido);
            pw.close();
            archivo.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public String dibujar() {

        try {
            this.cantidadImagen++;
            escribirArchivo("archivo.dot", obtenerGraphviz());
            ProcessBuilder pb;
            pb = new ProcessBuilder("dot", "-Tjpg", "-o", "arbol" + this.cantidadImagen + ".jpg", "archivo.dot");
            pb.redirectErrorStream(true);
            pb.start();
            String ruta = "arbol" + this.cantidadImagen + ".jpg";
            return ruta;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "-";

    }

    public Nodo getNodoRaiz() {
        return nodoRaiz;
    }

}
