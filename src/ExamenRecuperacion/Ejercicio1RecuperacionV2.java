package ExamenRecuperacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1RecuperacionV2 {
	
	private static String nombreFichero="carta.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    StringBuilder sb= leerFicheroTxt();
			//escribirCartas(sb, "Manuel", "Pepito");
			leerYMostrarFichero(sb);


	}
	
	public static StringBuilder leerFicheroTxt() {
		StringBuilder sb = new StringBuilder();
	      try (FileReader flujoLectura=new FileReader(nombreFichero);
	    		  BufferedReader filtroLectura=new BufferedReader(flujoLectura);){


	          // Lectura del fichero
	          String linea;
	          while((linea=filtroLectura.readLine())!=null) {
	        	  sb.append(linea+"\n");
	          }
	          		
	       }
	       catch(Exception e){
	          e.printStackTrace();
	       }
	   return sb;
	}
	public static void escribirCartas(StringBuilder sb, String nombre, String calle, int contador) {
		int posicionNombrePrincipio=sb.indexOf("XXX");
		int posicionNombreFinal=posicionNombrePrincipio+3;
		int posicionCallePrincipio=sb.indexOf("YYY")+2;
		int posicionCalleFinal=posicionCallePrincipio+4;
		sb.replace(posicionNombrePrincipio,posicionNombreFinal, nombre);
		sb.replace(posicionCallePrincipio, posicionCalleFinal, calle);
		try(BufferedWriter filtroEscritura=new BufferedWriter(new FileWriter("cartas"+contador+".txt"));){
			filtroEscritura.write(sb.toString());
			filtroEscritura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int pNF=posicionNombrePrincipio+nombre.length();
		int pCF=posicionCallePrincipio+calle.length();
		sb.replace(posicionNombrePrincipio, pNF, "XXX");
		sb.replace(posicionCallePrincipio, pCF, "YYY");
	}
	
	private static void leerYMostrarFichero(StringBuilder sb) {
		int numero;
		boolean terminado = false;
		int contador=1;
		try (FileInputStream flujoEntrada = new FileInputStream("clientes.bin");
				DataInputStream filtroEntrada = new DataInputStream(flujoEntrada);) {
			while (!terminado) {
				try {
					String nombre = filtroEntrada.readUTF();
					String calle = filtroEntrada.readUTF();
					escribirCartas(sb, nombre, calle, contador);
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
