package EjerciciosRepaso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Ejercicio2 {

	public static void main(String[] args) {
		String [] alumno;
		String nombreArchivo=null;
		String nombreArchivoEscritura=null;
		alumno=lecturaArchivo(nombreArchivo);
		ordenarArrayYEscrituraArchivo(alumno,nombreArchivoEscritura);
	}
	public static String[]  lecturaArchivo( String nombreArchivo) {
		String linea;
		String[] array = null;
		int contador=0;
		try(FileReader flujoLectura=new FileReader(nombreArchivo);
				BufferedReader filtroLectura=new BufferedReader(flujoLectura);){
				linea=filtroLectura.readLine();
				while ( linea!=null){
					array[contador]=linea;
					linea=filtroLectura.readLine();
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
	public static  void  ordenarArrayYEscrituraArchivo(String[] array, String nombreArchivoEscritura) {
		Arrays.sort(array);
		try(BufferedWriter filtroEscritura=new BufferedWriter(new FileWriter(nombreArchivoEscritura));){
			filtroEscritura.write(array.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
