package arbolbinario;

import jdk.jshell.execution.Util;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Stack;

public class Arbol {
    private NodoArbol raiz;

    public Arbol() {
        raiz = null;
    }

    public Arbol(String dato) {
        raiz = new NodoArbol(dato);
    }

    public Arbol(String dato, Arbol izquierdo, Arbol derecho) {
        NodoArbol nodoIzq = null;
        NodoArbol nodoDer = null;
        if (izquierdo != null) {
            nodoIzq = izquierdo.raiz;
        }
        if (derecho != null) {
            nodoDer = derecho.raiz;
        }
        raiz = new NodoArbol(dato, nodoIzq, nodoDer);
    }

    /**
     * Recorrido en preorden
     */
    public void preOrden() {
        System.out.print("Preorden: ");
        this.preOrdenRec(raiz);
        System.out.println();
    }

    private void preOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + "  ");
            this.preOrdenRec(nodo.getIzquierdo());
            this.preOrdenRec(nodo.getDerecho());
        }
    }

    /**
     * Recorrido en orden central
     */
    public void ordenCentral() {
        System.out.print("Orden Central: ");
        this.ordenCentralRec(raiz);
        System.out.println();
    }

    private void ordenCentralRec(NodoArbol nodo) {
        if (nodo != null) {
            this.ordenCentralRec(nodo.getIzquierdo());
            System.out.print(nodo.getDato() + "  ");
            this.ordenCentralRec(nodo.getDerecho());
        }
    }

    /**
     * Recorrido en postorden
     */
    public void postOrden() {
        System.out.print("Postorden: ");
        this.postOrdenRec(raiz);
        System.out.println();
    }

    private void postOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            this.postOrdenRec(nodo.getIzquierdo());
            this.postOrdenRec(nodo.getDerecho());
            System.out.print(nodo.getDato() + "  ");
        }
    }

    /**
     * Recorrido en amplitud con una cola de nodos del árbol
     */
    public void amplitud() {
        Cola cola = new Cola();
        System.out.print("Amplitud: ");
        if (raiz != null) {
            cola.encolar(raiz);
            while (!cola.vacia()) {
                NodoArbol nodo = cola.desencolar();
                System.out.print(nodo.getDato() + "  ");
                if (nodo.getIzquierdo() != null) {
                    cola.encolar(nodo.getIzquierdo());
                }
                if (nodo.getDerecho() != null) {
                    cola.encolar(nodo.getDerecho());
                }
            }
        }
        System.out.println();
    }

    // ------------------------------------------------------------------------
    // TODO 2.3
    public Arbol(String[] reglas) {
        int k = 0;
        Cola colaAux = new Cola();
        raiz = new NodoArbol("S", null, null);
        colaAux.encolar(raiz);
        int numNoTerminales = 1;
        NodoArbol nodo = colaAux.desencolar();
        colaAux.encolar(nodo);
        while((nodo!=null) && (k<reglas.length)){
            nodo=colaAux.desencolar();
            if(esParteIzquierdaDe(nodo,reglas[k])){
                aplicarRegla(nodo,reglas[k],colaAux,numNoTerminales );
            }else{
                System.out.println("La regla " + reglas[k] + " no es parsable.");

            }
            k+=1;
        }
    }

     public boolean esParteIzquierdaDe(NodoArbol nodo, String regla) {
        String aux = Utilidades.getParteIzquierda(regla);
        if(nodo!=null) {
            return nodo.getDato().contains(aux);

        }else {
            return false;
        }
    }

    public int aplicarReglaFinal(NodoArbol nodo, String regla, int numNoTerminales) {
        String aux = Utilidades.getParteDerecha(regla);
        nodo.setIzquierdo(new NodoArbol(aux));
        numNoTerminales--;
        return numNoTerminales;

    }

    public int aplicarReglaIntermedia(NodoArbol nodo, String regla, Cola colaNoTerminales, int numNoTerminales) {
        String aux = (Utilidades.getParteDerecha(regla));
        String[] cadena = aux.split(" ");
        String aux1 = cadena[0];
        NodoArbol nodo1 = new NodoArbol(aux1, null, null);
        String aux2 = cadena[1];
        NodoArbol nodo2 = new NodoArbol(aux2, null, null);
        nodo.setIzquierdo(nodo1);
        nodo.setDerecho(nodo2);
        colaNoTerminales.encolar(nodo1);
        colaNoTerminales.encolar(nodo2);
        numNoTerminales += 2;
        return numNoTerminales;

    }

    public int aplicarRegla(NodoArbol nodo, String regla, Cola colaNoTerminales, int numNoTerminales) {
        int i = -1;
        if (Utilidades.esReglaIntermedia(regla)) {
            i = aplicarReglaIntermedia(nodo, regla, colaNoTerminales, numNoTerminales);
        }
        if (Utilidades.esReglaFinal(regla)) {
            i = aplicarReglaFinal(nodo, regla, numNoTerminales);

        }
        if (i == -1) {
            System.out.println("La regla " + regla + " no es parsable.");
        }
        return i;

    }

    // ------------------------------------------------------------------------
    // TODO 2.4
    public String[] generarDerivaciones() {
        NodoArbol nodo = raiz;
        int k=0;
        String[] reglasAux = new String[100];
        String[] aux =preOrdenArrStr(reglasAux,nodo);
        for(int i=0;i<reglasAux.length;i++){
            if(aux[i]!=null){
                k++;
            }
        }
        String[] reglas = new String[k];
        for(int i=0;i<k;i++){
            reglas[i]=aux[i];
        }
        return reglas;
    }

    private String[] preOrdenArrStr(String[]array,NodoArbol nodo) {
        if (nodo != null) {
            if ((nodo.getIzquierdo() != null) && (nodo.getDerecho() != null)) {
                insertarDato(array,nodo.getDato()+"->"+nodo.getIzquierdo().getDato()+" "+nodo.getDerecho().getDato());
            }
            if ((nodo.getIzquierdo() != null)&&(nodo.getDerecho() == null)) {
                insertarDato(array,nodo.getDato()+"->"+nodo.getIzquierdo().getDato());
            }
            this.preOrdenArrStr(array,nodo.getIzquierdo());
            this.preOrdenArrStr(array,nodo.getDerecho());
        }
        return array;
    }

    public void insertarDato(String[]array,String dato){
        boolean parar = false;
        int i =0;
        while((!parar)&&(i<100)){
            if((array[i]==null)){
                array[i] = dato;
                parar= true;
            }else{
                i++;
            }
        }
    }

    // ------------------------------------------------------------------------
    // TODO 2.5
    public String generarFrase() {
        NodoArbol nodo = raiz;
        int k =0;
        String frase = " ";
        String[] reglasAux = new String[100];
        String[] aux = preOrdenGenFras(reglasAux,nodo);
        for (int i = 0; i < reglasAux.length; i++) {
            if (aux[i] != null) {
                k++;
            }
        }
        String[] reglas = new String[k];
        for(int i = 0; i < k; i++) {
            reglas[i] = aux[i];
        }
        for(int i = 0; i < k; i++) {
            frase=frase+" "+reglas[i];
        }
        return frase;
    }
    private String[] preOrdenGenFras(String[]array,NodoArbol nodo) {
        if (nodo != null) {
            if(Utilidades.esSimboloTerminal(nodo.getDato())){
                insertarDato(array,nodo.getDato());
            }
            this.preOrdenGenFras(array,nodo.getIzquierdo());
            this.preOrdenGenFras(array,nodo.getDerecho());
        }
        return array;
    }


}
