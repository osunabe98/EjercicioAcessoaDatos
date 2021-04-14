package Relacion2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class GestionContenidoE2 implements ContentHandler {

	public Scanner teclado = new Scanner(System.in);
	String etiquetaActual;
	boolean repetidor=false;
	boolean noRepetidor=false;
	File fichero=new File("alumnosRepetidores.txt");
	StringBuilder sb = new StringBuilder();
	
	
	
	
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		String cadena= new String(arg0, arg1, arg2);
		cadena = cadena.replaceAll("[\t\n ]", ""); 
		if(repetidor==true) {
			if(etiquetaActual.equals("dni")) {
				sb.append(cadena + " ");
			}
			if(etiquetaActual.equals("nombre")) {
				sb.append(cadena +"\n");
				repetidor=false;
			}
		}
		if(noRepetidor==true) {
			if(etiquetaActual.equals("nombre")) {
				File carpetaAlumno =new File(cadena);
				carpetaAlumno.mkdir();
				noRepetidor=false;
			}
			
		}

	}

	@Override
	public void endDocument() throws SAXException {
		try {
			FileWriter flujoEscritura=new FileWriter (fichero);
			flujoEscritura.write(sb.toString());
			flujoEscritura.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void endElement(String arg0, String arg1, String arg2) throws SAXException {
		etiquetaActual="";

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String nameSpace, String nombre, String nombreC, Attributes arg3) throws SAXException {
		etiquetaActual=nombreC;
		if(etiquetaActual.equals("alumno")) {
			if(Integer.parseInt(arg3.getValue(0))>=2) {
				repetidor=true;
			}else {
				noRepetidor=true;
			}
			
			
		}

	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub
		
	}


}
