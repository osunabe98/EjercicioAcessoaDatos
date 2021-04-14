package ExamenRecuperacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1Recuperacion {
	private static String nombreFichero="carta.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		leerYMostrarFichero();

	}
	
	public static void leerFicheroTxtYEscribir(int contador, String nombre, String calle) {
	      try (FileReader flujoLectura=new FileReader(nombreFichero);
	    		  BufferedReader filtroLectura=new BufferedReader(flujoLectura);
	    		  BufferedWriter filtroEscritura=new BufferedWriter(new FileWriter("cartas"+contador+".txt"));){


	          // Lectura del fichero
	          String linea;
	          while((linea=filtroLectura.readLine())!=null) {
	        	 linea = linea.replace("XXX", nombre);
	        	 linea = linea.replace("YYY", calle);
	        	 filtroEscritura.write(linea+"\n");
	          }
	          		
	       }
	       catch(Exception e){
	          e.printStackTrace();
	       }
	}
	
	private static void leerYMostrarFichero() {
		int numero;
		boolean terminado = false;
		int contador=1;
		try (FileInputStream flujoEntrada = new FileInputStream("clientes.bin");
				DataInputStream filtroEntrada = new DataInputStream(flujoEntrada);) {
			while (!terminado) {
				try {
					String nombre = filtroEntrada.readUTF();
					String calle = filtroEntrada.readUTF();
					leerFicheroTxtYEscribir(contador,nombre, calle);
					contador++;
				} catch (EOFException e) {
					terminado = true;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
