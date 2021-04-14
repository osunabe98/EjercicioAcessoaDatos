package Relacion2;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class PrincipalE3 {

	private static final String NOMBRE_FICHERO = "resultados.xml";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			leerXMLConSax(NOMBRE_FICHERO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void leerXMLConSax(String nombreFichero)throws FileNotFoundException, SAXException, IOException, ParserConfigurationException 
	{
		SAXParserFactory parseFactory = SAXParserFactory.newInstance();
		SAXParser parser =parseFactory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		reader.setContentHandler(new GestionContenidoE3());
		reader.parse(new InputSource(nombreFichero));
		
	}

}
