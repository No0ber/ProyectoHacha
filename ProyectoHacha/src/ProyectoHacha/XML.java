package ProyectoHacha;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XML {
	
	public static void crearDoc() {
		String tama�oOriginal = Integer.toString(Divisor.tama�oOriginal);
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			//Elemento ra�z
			Document doc = docBuilder.newDocument();
			Element eleRaiz = doc.createElement("ProyectoHacha");
			doc.appendChild(eleRaiz);
			
			//Elemento principal
			Element stuff = doc.createElement("Cosas");
			eleRaiz.appendChild(stuff);
			
			//Elemento tama�o archivo
			Element tama�o = doc.createElement("TamanoOriginal");
			tama�o.appendChild(doc.createTextNode(tama�oOriginal));
			stuff.appendChild(tama�o);
			
			//Elemento partes a dividir
			Element partes = doc.createElement("PartesDivididas");
			partes.appendChild(doc.createTextNode(Divisor.strnPartes));
			stuff.appendChild(partes);
			
			//Elemento nombre del archivo
			Element nombre = doc.createElement("NombreArchivo");
			nombre.appendChild(doc.createTextNode(Divisor.nomArchi));
			stuff.appendChild(nombre);
			
			//Elemento para la extensi�n del archivo
			Element extension = doc.createElement("Extension");
			extension.appendChild(doc.createTextNode(Divisor.extArch));
			stuff.appendChild(extension);
			
			
			//Escribe la info en el xml y lo crea
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(Divisor.rutaDivisiones+"/"+Divisor.nomArchi+".xml"));
			
			transformer.transform(source, result);
			
			System.out.println("�Archivo guardado!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	}
}
