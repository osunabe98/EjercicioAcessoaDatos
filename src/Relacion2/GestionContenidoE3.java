package Relacion2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;





public class GestionContenidoE3 implements ContentHandler {

	boolean esLocal=false;
	String equipoLocal;
	String equipoVisitante;
	String etiquetaActual;
	int golesEquipoLocal;
	int golesEquipoVisitante;
	BufferedWriter bw = null;
    FileWriter fw = null;

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		 esLocal=true;
		 try(FileWriter fw = new FileWriter("ganadores.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);){
			 
		} catch (IOException e) {
			    //exception handling left as an exercise for the reader
		}

	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		etiquetaActual=qName;
	

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		etiquetaActual="";

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		String cadena= new String(ch, start, length);
		cadena = cadena.replaceAll("[\t\n ]", ""); 
		if(esLocal==true) {
			if(etiquetaActual.equals("equipo")) {
				equipoLocal=cadena;
			}
			if(etiquetaActual.equals("goles")) {
				golesEquipoLocal=Integer.parseInt(cadena);
				esLocal=false;
			}
		}else {
			if(etiquetaActual.equals("equipo")) {
				equipoVisitante=cadena;
			}
			if(etiquetaActual.equals("goles")) {
				golesEquipoVisitante=Integer.parseInt(cadena);
				esLocal=true;
				if(golesEquipoLocal>golesEquipoVisitante) {
					System.out.println(equipoLocal + " " + equipoVisitante + "  1");
						try {
							bw.append(equipoLocal);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}else {
					if(golesEquipoLocal<golesEquipoVisitante) {
						System.out.println(equipoLocal + " " + equipoVisitante + " 2");
						try {
							bw.append(equipoVisitante);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						System.out.println(equipoLocal + " " + equipoVisitante + " X");
						
					}
				}
			}

		}

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

}
