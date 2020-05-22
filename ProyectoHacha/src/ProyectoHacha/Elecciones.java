package ProyectoHacha;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Elecciones {
	JFileChooser fc = new JFileChooser("Recursos"); //El directorio que mostrará el JFileChooser por defecto será "Recursos"
	String ruta;
	JTextField aDividir;
	Juntador estosArch = new Juntador();
	
	public void elegirUno(JTextField seleccion) {
		aDividir = seleccion;

		fc.setMultiSelectionEnabled(false);//Bloquea la selección a un solo archivo
		//NOTAS: El JFileChooser tiene que tener un comprobador de errores en caso de no seleccionarse un archivo
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY); //Solo se pueden elegir archivos
		fc.showOpenDialog(fc); //Ventana de elección de archivo
		
		ruta = fc.getSelectedFile().getAbsolutePath(); //Guarda la ruta absoluta del archivo en un String
		
		aDividir.setText(ruta); //Pone la ruta en el JTextField
	}
	
	public void elegirVarios() {
		//NOTAS: Falta el comprobador de que los archivos existen.
		fc.setMultiSelectionEnabled(true); //Permite seleccionar varios archivos
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.showOpenDialog(fc);
		
		Juntador.partecitas = fc.getSelectedFiles(); //Almacena en un File[] todas las partes
		
		try {
			estosArch.juntando();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
