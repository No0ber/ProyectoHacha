package ProyectoHacha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Codigo {
	public static void main(String[] args) throws IOException {
		//separandoArr();
		juntando();
	}
	/* ESTA CLASE ES UNA CLASE DE PRUEBA Y ERROR, NO INTERACTÚA CON EL PROGRAMA EN SÍ,
	 * AÚN ASÍ LA VOY A DEJAR AQUÍ PORQUE ME PERMITE SEGUIR EXPERIMENTANDO Y 
	 * ME MOTIVA UN MONTÓN VER DE LO QUE SOY CAPAZ */
	
	//OUTDATED ---------------------------------------------------------------------------------------
	public static void leerSeparar() throws IOException {
		File archivo1 = new File("Recursos/imagen.jpg");
		File archivoSal = new File("Recursos/imgcopia.jpg");
		File archivoSal2 = new File("Recursos/imgcopia2.jpg");
		
		//Lee - escribe el archivo por bytes
		FileInputStream fis = new FileInputStream(archivo1);
		FileOutputStream fos = new FileOutputStream(archivoSal);
		
		//Lee bit a bit
		int char1 = fis.read();
		
		int contador=0;
		
		while(char1 != -1) {
			fos.write(char1);
			char1 = fis.read();
			contador++;
			
			if(contador==archivo1.length()/2) {
				fos = new FileOutputStream(archivoSal2);
			}
		}
		
		fis.close();
		fos.close();
	}
	//-------------------------------------------------------------------------------------------------
	
	//ESTE CÓDIGO ESTÁ MEJOR COMENTADO Y EXPANDIDO EN divisor.java
	public static void separandoArr() throws IOException {
		//NOTAS: n tiene que ser un txtField para que el usuario introduzca el número de partes deseado
		int n=4, partes=0;
		
		File archPrin = new File("Recursos/imagen.jpg"); //NOTAS: archPrin tiene que ser introducido por el usuario
		File[] archSal = new File[n];
		
		//Pone en un array de ficheros tantos como el usuario introduzca
		for(int i=0;i<n;i++) {
			archSal[i]=new File("Recursos/imagen"+(i+1)+".jpg");
		}
		
		FileInputStream lecturaB = new FileInputStream(archPrin);
		FileOutputStream escrituraB = new FileOutputStream(archSal[partes]);
		
		int char1 = lecturaB.read();
		int contador=0;
		//Divide el archivo en tantas partes como el usuario indique
		int calculo = (int)(archPrin.length()/n);
		
		//Bucle que lee bits hasta que no hay más
		while(char1 != -1) {
			escrituraB.write(char1);
			char1 = lecturaB.read();
			
			//El contador se usa para hacer una comparación con la división de partes
			contador++;
			
			//if que controla que se escribe cada parte
			if(contador==calculo && partes<n-1) {
				contador=0;
				partes++;
				escrituraB = new FileOutputStream(archSal[partes]);
			}
		}
		
		//Cierra los Stream
		lecturaB.close();
		escrituraB.close();
	}

	//ESTE MÉTODO HA SIDO EXPANDIDO Y MEJOR COMENTADO EN juntador.java
	public static void juntando() throws IOException {
		//NOTAS: Tiene que leer los archivos seleccionados por el usuario
		File parte1 = new File("Recursos/imagen.jpg.part1");
		File parte2 = new File("Recursos/imagen.jpg.part2");
		File parte3 = new File("Recursos/imagen.jpg.part3");
		File parte4 = new File("Recursos/imagen.jpg.part4");
		File juntitas = new File("Recursos/juntado.jpg");
		
		FileInputStream lecturaB = new FileInputStream(parte1);
		FileOutputStream escrituraB = new FileOutputStream(juntitas);
		
		int char1 = lecturaB.read();
		int contador = 1;
		int cambiar = 0;
		
		while(char1 != -1) {
			escrituraB.write(char1);
			char1 = lecturaB.read();
			contador++;
			
			if(contador==((int)(parte1.length())) && cambiar==0) {
				lecturaB = new FileInputStream(parte2);
				cambiar++;
				contador = 0;
			}
			
			if(contador==((int)(parte2.length())) && cambiar==1) {
				lecturaB = new FileInputStream(parte3);
				cambiar++;
				contador = 0;
			}
			if(contador==((int)(parte3.length())) && cambiar==2) {
				lecturaB = new FileInputStream(parte4);
				cambiar++;
				contador = 0;
			}
		}
		
		lecturaB.close();
		escrituraB.close();
	}
}

