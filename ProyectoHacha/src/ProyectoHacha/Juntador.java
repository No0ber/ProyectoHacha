package ProyectoHacha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Juntador {
	static ArrayList<File> partes = new ArrayList<File>(); //Contenedor de las rutas de las partes a juntar
	static File[] partecitas;
	
	public void juntando() throws IOException {
		/* -----------------------------------------------------
		// OUTDATED
		File parte1 = new File("Recursos/imagen.jpg.part1");
		File parte2 = new File("Recursos/imagen.jpg.part2");
		File parte3 = new File("Recursos/imagen.jpg.part3");
		File parte4 = new File("Recursos/imagen.jpg.part4");
		 ----------------------------------------------------- */
		
		//Coge el nombre del archivo sin extensión
		String nomArchi = partecitas[0].getName();
		nomArchi = nomArchi.replaceFirst("[.][^.]+$", "");
		nomArchi = nomArchi.replaceFirst("[.][^.]+$", "");
		
		//Leyendo del XML los datos necesarios ------------------------------------------------------------
		File leyendoXML = new File(partecitas[0].getParent()+"/"+nomArchi+".xml"); //Busca el XML para acceder a él
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		Document document;
		String stringPartes = "";
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(leyendoXML);
			
			//Accede al elemento del XML donde se almacena el número de partes
			stringPartes = document.getElementsByTagName("PartesDivididas").item(0).getTextContent();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//-------------------------------------------------------------------------------------------------
		
		//numPartesDiv se utiliza para comprobaciones
		int numPartesDiv = Integer.parseInt(stringPartes);
		
		//-------------------------------------------------------------------------------------------------
		
		//if que controla que se seleccionan las partes necesarias, sacadas del XML
		if(numPartesDiv != partecitas.length) {
			JOptionPane.showConfirmDialog(null, "No has seleccionado el número de partes correctas para la unión del archivo.", "Muy mal :(", JOptionPane.DEFAULT_OPTION);
		}
		
		//Almacena la ruta de salida del archivo unido, que será la misma ruta donde se encuentran los archivos divididos
		String rutaSalida = partecitas[0].toString();
		rutaSalida = rutaSalida.replaceFirst("[.][^.]+$", ""); //Coge la ruta original y quita la extensión .part del final
		
		//Recorre el array partecitas
		int contPartes = 0;
		
		File juntitas = new File(rutaSalida);
		
		FileInputStream lecturaB = new FileInputStream(partecitas[contPartes]);
		FileOutputStream escrituraB = new FileOutputStream(juntitas);
		
		int char1 = lecturaB.read(); //Lee el tamaño de la primera parte y lo guarda en char1
		int contador = 0; //Contador para comparar los bits con lo que pesa cada parte
		
		//System.out.println(char1);
		
		while(char1 != -1) {
			escrituraB.write(char1);
			char1 = lecturaB.read();
			contador++;
			
			if(contador==((partecitas[contPartes].length())) && contPartes<numPartesDiv-1) {
				contPartes++;
				lecturaB = new FileInputStream(partecitas[contPartes]);
				char1 = lecturaB.read();
				contador = 0;
			}
		}
		
		lecturaB.close();
		escrituraB.close();
	}
}