package arbolbinario;

import java.util.Arrays;

public class Pruebas {

    public static void main(String[] args) {
        System.out.println("********** PRUEBAS ARBOL SINTACTICO **********");
        System.out.println("Gramática:\n" +
                "S→SN PV\n" +
                "SN→DET N\n" +
                "PV→V SN\n" +
                "DET→el|la\n" +
                "N→gato|perro|película\n" +
                "V →ve|come");
        String reglasCad = "S->SN PV,SN->DET N,PV->V SN,DET->el,N->gato,V->ve,SN->DET N,DET->la,N->pelicula";
        System.out.print("Árbol sintáctico para la siguiente secuencia de reglas: ");
        System.out.println(reglasCad);
        String[]reglas = new String[9];
        for(int i=0;i<9;i++){
            reglas[i]=reglasCad.split(",")[i];
        }
        Arbol arbol = new Arbol(reglas);
        System.out.println("Derivaciones en preOrden: ");
        String[]aux = arbol.generarDerivaciones();
        aux.toString();
    }
}

