package ExamenFicheros;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


public class Ejercicio1 {	
	private static String nombreFicheroBinario = "instrucciones.bin";
	private static String nombreFicheroErrores = "errores.txt";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		leerYMostrarFichero();
	}

	private static void leerYMostrarFichero() {
		int numero;
		boolean terminado = false;

		try (BufferedWriter bfErrores = new BufferedWriter(new FileWriter(nombreFicheroErrores));
				FileInputStream flujoEntrada = new FileInputStream(nombreFicheroBinario);
				DataInputStream filtroEntrada = new DataInputStream(flujoEntrada);) {
			while (!terminado) {

				try {
					numero = filtroEntrada.readInt();
					String lectura = filtroEntrada.readUTF();
					if (numero == 1) {
						File fichero = new File(lectura);
						if (fichero.isDirectory()) {
							errorCrearDirectorios(bfErrores);
						} else {
							fichero.mkdir();
						}

					} else {
						File fichero = new File(lectura);
						if (fichero.isFile()) {
							errorCrearFicheros(bfErrores);
						} else {
							fichero.createNewFile();
						}

					}
				} catch (EOFException e) {
					terminado = true;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void errorFicheroAbrir(String nombre) {
		String cadena = "No se ha podido abrir el fichero";
		try (BufferedWriter filtroEscritura = new BufferedWriter(new FileWriter(nombre));) {
			filtroEscritura.write(cadena);
			filtroEscritura.newLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void errorFicheroLeer(String nombre) {
		String cadena = "Error al leer el fichero";
		try (BufferedWriter filtroEscritura = new BufferedWriter(new FileWriter(nombre));) {
			filtroEscritura.write(cadena);
			filtroEscritura.newLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void errorCrearFicheros(BufferedWriter file) {
		String cadena = "Error al crear el fichero, ya existe";
		try {
			file.write(cadena);
			file.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void errorCrearDirectorios(BufferedWriter file) {
		String cadena = "Error al crear el directorio, ya existe";
		try {
			file.write(cadena);
			file.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
