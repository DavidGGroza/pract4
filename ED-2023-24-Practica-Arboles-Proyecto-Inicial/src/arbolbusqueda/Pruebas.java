package arbolbusqueda;

public class Pruebas {

	// Pruebas ------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("-------------- Arbol binario de busqueda ------------");
		Alumno felipe = new Alumno("Felipe Garcia",1253,5.3);
		Alumno adriana = new Alumno("Adriana Torres",2345,7.0);
		Alumno alicia = new Alumno("Alicia Blazquez Martin",5622,10.0);
		Alumno diego = new Alumno("Diego Perez Gonzalez",8135,8.0);
		Alumno mar = new Alumno("Mar Hernando Lopez",8217,6.3);
		Alumno pedro = new Alumno("Pedro Jimenez del Pozo",8510,3.0);
		Alumno eduardo = new Alumno("Eduardo Parra Martin",8765,6.7);
		ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
		arbol.insertar(alicia);
		arbol.insertar(pedro);
		arbol.insertar(adriana);
		arbol.insertar(felipe);
		arbol.insertar(eduardo);
		arbol.insertar(diego);
		arbol.insertar(mar);
		System.out.print("ABB alumnos(inicial).");
		arbol.preOrdenNivel();
		System.out.println(" ");

		System.out.print("ABB alumnos tras agregar el rango de matrículas [1300-1310].");
		Alumno temporal = new Alumno("temporal",0,0.0);
		arbol.agregarRangoDeMatriculas(1300,1310,temporal);
		arbol.preOrdenNivel();
		System.out.println(" ");

		System.out.print("ABB alumnos tras eliminar el rango de matrículas [1300-6000].");
		arbol.eliminarRangoMatriculas(1300,6000);
		arbol.preOrdenNivel();
		System.out.println(" ");



		System.out.println("----------------------------------------------------");
	}
}
