package ProyectoHacha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Llamador implements ActionListener {
	Divisor dividendo = new Divisor();
	Elecciones esteArch = new Elecciones();
	
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Elegir":
				esteArch.elegirUno(CosasEnPantalla.txtRuta);
				break;
				
			case "Dividir":
				//NOTAS: Hay que poner un comprobador que compruebe que txtRuta no está vacío
				dividendo.dividiendo(CosasEnPantalla.txtPartes, CosasEnPantalla.txtRuta);
				break;
				
			case "JuntarPartes":
				esteArch.elegirVarios();
				break;
				
			case "Salir":
				System.exit(0);
		}
	}
}
