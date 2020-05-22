package ProyectoHacha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTextField;

public class Divisor {
	JTextField txtNPartes;
	JTextField rutaAbsolutaEntrar;
	
	//Variables llamadas por xml.java
	public static int tamañoOriginal; 
	public static String strnPartes, nomArchi, extArch, rutaDivisiones;
	
	public void dividiendo(JTextField partes, JTextField rutaAbsIn) {
		txtNPartes = partes;
		rutaAbsolutaEntrar = rutaAbsIn;
		
		//Almacena la ruta absoluta en el String
		String rutaAbsolutaIn = rutaAbsolutaEntrar.getText();
		
		//Lee del JTextField el número de partes que el usuario introduce y lo convierte en int para ser utilizado
		strnPartes = txtNPartes.getText();
		int nPartes=Integer.parseInt(strnPartes), archPartes=0; //archPartes es un contador para recorrer el array de archivos más adelante
		
		//File para el archivo de entrada y los de salida
		File archPrin = new File(rutaAbsolutaIn); //Ruta elegida por el usuario
		File[] archSal = new File[nPartes]; //El array de archivos es tan grande como partes quiera el usuario
		
		//Almacena la extensión del archivo
		extArch = archPrin.getName();
		if(extArch.lastIndexOf(".") != -1 && extArch.lastIndexOf(".") != 0) {
			extArch = extArch.substring(extArch.lastIndexOf("."));
		}
		
		//Coge el nombre del archivo sin extensión
		nomArchi = archPrin.getName();
		nomArchi = nomArchi.replaceFirst("[.][^.]+$", ""); //Quita la extensión del archivo en el String
		
		//Crea una carpeta contenedora para posteriormente meter las partes divididas
		File carpetaCont = new File(archPrin.getParent()+"/"+nomArchi);
		carpetaCont.mkdir();
		
		//Guarda la ruta donde se almacenarán las divisiones
		rutaDivisiones = archPrin.getParent()+"/"+nomArchi;
		
		//Tamaño del archivo que pasará al XML
		tamañoOriginal = (int)archPrin.length();
		
		//Pone en un array de ficheros tantos como el usuario introduzca
		/*for(int i=0;i<nPartes;i++) {
			archSal[i]=new File(rutaAbsolutaIn+".part"+(i+1)); //NOTAS: Este nombre tiene que ser el indicado por el usuario
		}*/
		
		//Almacena en el array File para los archivos de salida divididos los mismos y la carpeta que los contendrá
		for(int i=0;i<nPartes;i++) {
			archSal[i]=new File(carpetaCont+"/"+archPrin.getName()+".part"+(i+1)); //NOTAS: Este nombre tiene que ser el indicado por el usuario
		}
		
		//Crea el archivo XML
		XML.crearDoc();
		
		//Cosas necesarias para leer y escribir
		FileInputStream lecturaB;
		FileOutputStream escrituraB;
		
		try {
			//Se crean los stream que leeran y escribiran los archivos
			lecturaB = new FileInputStream(archPrin);
			escrituraB = new FileOutputStream(archSal[archPartes]);
			//Almacena el tamaño total del archivo
			int char1 = lecturaB.read();
			//El contador se usa para hacer una comparación con la división de partes y poder separarlas
			int contador=0;
			//Guarda el valor de lo que ocupa cada parte
			int calculo = (int)(archPrin.length()/nPartes);
			
			//Bucle que lee bits hasta que no hay más
			while(char1 != -1) {
				escrituraB.write(char1);
				char1 = lecturaB.read();
				contador++;
				
				//if que controla que se escribe cada parte
				if(contador==calculo && archPartes<nPartes-1) {
					//contador siempre se resetea para poder comparar el tamaño de las partes
					contador=0;
					archPartes++;
					
					//archPartes cambia y escrituraB se convierte en el siguiente archivo
					escrituraB = new FileOutputStream(archSal[archPartes]);
				}
			}
			
			//Cierra los Stream
			lecturaB.close();
			escrituraB.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
