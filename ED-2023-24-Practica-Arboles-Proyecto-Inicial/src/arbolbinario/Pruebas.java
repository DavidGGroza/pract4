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

        //PRUEBA 1
        String reglasCad = "S->SN PV,SN->DET N,PV->V SN,DET->el,N->gato,V->ve,SN->DET N,DET->la,N->pelicula";
        System.out.print("Árbol sintáctico para la siguiente secuencia de reglas: ");
        System.out.println(reglasCad);
        String[]reglas = new String[9];
        for(int i=0;i<9;i++){
            reglas[i]=reglasCad.split(",")[i];
        }
        Arbol arbol = new Arbol(reglas);
        System.out.print("Derivaciones en preOrden: ");
        String[]aux = arbol.generarDerivaciones();
        System.out.println(Arrays.toString(aux));
        System.out.print("La frase reconstruida del árbol es: ");
        System.out.println(arbol.generarFrase());
        System.out.println(" ");

        //PRUEBA 2
        String reglasCad2 = "[S->SN PV,PV->V SN,V->come,SN->DET N,DET->el,N->perro";
        System.out.print("Árbol sintáctico para la siguiente secuencia de reglas: ");
        System.out.println(reglasCad2);
        String[]reglas2 = new String[6];
        for(int i=0;i<6;i++){
            reglas2[i]=reglasCad2.split(",")[i];
        }
        Arbol arbol2 = new Arbol(reglas2);
        System.out.print("Derivaciones en preOrden: ");
        String[]aux2 = arbol2.generarDerivaciones();
        System.out.println(Arrays.toString(aux2));
        System.out.print("La frase reconstruida del árbol es: ");
        System.out.println(arbol2.generarFrase());

    }
}

