package ejercicio3;



import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class PrincipalEjercicio3 {

	
	private static final String FICHERO_ORIGINAL = "universidad.xml";
	private static final String FICHERO_RESULTADO = "universidadNuevo.xml";
	
	public static void main(String[] args) {

	
		try {

			Document arbolDocumento = crearArbolDOM();

			
			modificarArbolDOM(arbolDocumento);
			transformarDOMEnXml(arbolDocumento);
			
			System.out.println("Documento " + FICHERO_RESULTADO+ " creado correctamente.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
	private static void modificarArbolDOM(Document arbol) {
		//Variables
		Element departamento,empleado;
		NodeList listaDepartamentos=arbol.getElementsByTagName("departamento");
		Node textoSalarioMedio;
		int salarioTotal=0;
		int totalEmpleados=0;
		int salarioMedio;
		int contadorProfesor=0;
		
		//Recorremos todos los departamentos
		for(int i=0;i<listaDepartamentos.getLength();i++) {
			
			 departamento=(Element) listaDepartamentos.item(i);
			 //De cada departamento obtenemos una lista de empleados
			NodeList listaEmpleados=departamento.getElementsByTagName("empleado");
			
			 for(int j=0;j<listaEmpleados.getLength();j++) {
				 //Por cada empleado guardamos su sueldo
				 empleado=(Element) listaEmpleados.item(j);
				 if(empleado.getChildNodes().equals("Profesor")) {
					 contadorProfesor++;
				 }
				 salarioTotal=salarioTotal+Integer.parseInt(empleado.getAttribute("salario"));
				 totalEmpleados++;
				 if(contadorProfesor<=2) {
					 Attr atributoProfesor = arbol.createAttribute("Tamaño");
					 atributoProfesor.setValue("pequeño");
					 departamento.appendChild(atributoProfesor);
				 }else {
					 Attr atributoProfesor = arbol.createAttribute("Tamaño");
					 atributoProfesor.setValue("grande");
					 departamento.appendChild(atributoProfesor);
				 }
				 /*	 
				  * 
				  */
			 }
			 //Cuando se termina con los empleados se calcula la media
			 salarioMedio=salarioTotal/totalEmpleados;
			 //Y se crea un nuevo atributo para departamento
			 departamento.setAttribute("salario_medio",String.valueOf(salarioMedio));
			

			 
			/*
			 * Si numeroProfesores>2 atributo=grande
			 * Si numeroProfesores<=2 pequeÃ±o
			 */
			
			 
			 
		}
	}


	private static void transformarDOMEnXml(Document arbolDocumento)
			throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		Source source = new DOMSource(arbolDocumento);
		Result result = new StreamResult(FICHERO_RESULTADO);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result);
	}

	private static Document crearArbolDOM() throws ParserConfigurationException, SAXException, IOException {
		// Extraemos el arbol de nuestro documento XML.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document arbolDocumento = builder.parse(new File(FICHERO_ORIGINAL));
		return arbolDocumento;
	}

}
