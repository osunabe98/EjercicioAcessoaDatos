package EjerciciosRepaso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {

	public static void main(String[] args) {
		File directorio= new File(".");
		arrayFicheros(directorio);

	}

	public static void arrayFicheros(File file) {
		File[] nombreArchivos=file.listFiles();
		for(int i=0; i<nombreArchivos.length; i++ ) {
			String cadena =nombreArchivos[i].getName().substring((int) (nombreArchivos[i].getName().length()-3));
			if(cadena.equals("txt")) {
				lecturaYEscrituraDeArchivo(nombreArchivos[i].getName());
			}
		}

	}
	
	public static void lecturaYEscrituraDeArchivo(String nombreArchivo) {
		String linea;
		try(FileReader flujoLectura=new FileReader(nombreArchivo);
				BufferedReader filtroLectura=new BufferedReader(flujoLectura);
				BufferedWriter filtroEscritura=new BufferedWriter(new FileWriter("FicheroTotal.txt"));){
				linea=filtroLectura.readLine();
				while ( linea!=null){
					filtroEscritura.write(linea + "\n");
					linea=filtroLectura.readLine();
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
