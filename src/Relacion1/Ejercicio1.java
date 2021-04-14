package Relacion1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		menu();

	}
	private static void menu() {
		int opt;
		do {
			System.out.println("1. Crear directorio");
			System.out.println("2. Crear fichero de texto");
			System.out.println("3. Borrar fichero de texto");
			System.out.println("4. Mostrar los ficheros que contiene una carpeta");
			System.out.println("5. Salir");
			opt=teclado.nextInt();
			switch(opt) {
				case 1:
					crearDirectorio();
					break;
				case 2:
					crearFichero();
					break;
				case 3:
					borrarFichero();
					break;		
				case 4: 
					mostrarFicherosCarpeta();
					break;
				case 5: 
					break;
				
			}
		}while(opt<=0 || opt>=6);
	}
	private static void crearDirectorio() {
		String nombre;
		System.out.println("¿Que nombre deseas ponerle al directorio?");
		nombre= teclado.nextLine();
		File directorio = new File(nombre);
		directorio.mkdir();
	}
	private static void crearFichero() {
		System.out.println("¿Que nombre deseas ponerle al fichero?");
		String nombre= teclado.nextLine();
		File fichero = new File(nombre);
		try {
			fichero.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void borrarFichero() {
		System.out.println("¿Que fichero deseas borrar?");
		String nombre= teclado.nextLine();
		File fichero = new File(nombre);
		if(fichero.isFile()) {
			fichero.delete();
		}else {
			System.out.println("Es un directorio");
			
		}
		
	}
	private static void mostrarFicherosCarpeta() {
		System.out.println("¿Que carpeta deseas mostrar?");
		String nombre= teclado.nextLine();
		File fichero = new File(nombre);
		if(fichero.isDirectory()) {
			fichero.list();
		}else {
			System.out.println("Es un directorio");
			
		}
		
	}

}
