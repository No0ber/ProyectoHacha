package ProyectoHacha;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.io.IOException;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JList;

public class CosasEnPantalla extends JFrame {

	private JPanel contentPane;
	static JTextField txtPartes;
	private JLabel lblSelecPartes;
	static JTextField txtRuta;
	
	static DefaultListModel<String> elementos = new DefaultListModel<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CosasEnPantalla frame = new CosasEnPantalla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public CosasEnPantalla() throws IOException {
		setTitle("Proyecto Hacha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Esto es para que las ventanas adquieran la apariencia del sistema en el que se ejecute
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblPregunta1 = new JLabel("Proyecto Hacha");
		lblPregunta1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPregunta1.setBounds(221, 11, 239, 46);
		lblPregunta1.setFont(new Font("Arial", 0, 30));
		contentPane.add(lblPregunta1);
		
		JLabel lblquPartesQuieres = new JLabel("¿Qué partes quieres unir?");
		lblquPartesQuieres.setHorizontalAlignment(SwingConstants.RIGHT);
		lblquPartesQuieres.setBounds(531, 202, 124, 14);
		contentPane.add(lblquPartesQuieres);
		
		JLabel lblNewLabel = new JLabel("¿Qué archivo quieres dividir?");
		lblNewLabel.setBounds(10, 70, 144, 14);
		contentPane.add(lblNewLabel);
		
		lblSelecPartes = new JLabel("¿Cuántas partes quieres?");
		lblSelecPartes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelecPartes.setBounds(540, 70, 134, 14);
		contentPane.add(lblSelecPartes);
		
		txtPartes = new JTextField();
		txtPartes.setHorizontalAlignment(SwingConstants.CENTER);
		txtPartes.setBounds(569, 95, 86, 20);
		contentPane.add(txtPartes);
		txtPartes.setColumns(10);
		
		txtRuta = new JTextField();
		txtRuta.setBounds(109, 96, 315, 20);
		contentPane.add(txtRuta);
		txtRuta.setColumns(10);
		
		//------------------------------------------------------------------------------------------------------------------------
		//BOTONES
		//Los botones tienen un ActionCommand para darles un identificador y en la clase llamador() un switch les dará propósito
		JButton btnElegir = new JButton("Elegir");
		btnElegir.setBounds(10, 95, 89, 23);
		contentPane.add(btnElegir);
		btnElegir.addActionListener(new Llamador());
		btnElegir.setActionCommand("Elegir");
		
		JButton btnDividir = new JButton("Dividir");
		btnDividir.setBounds(459, 94, 89, 23);
		contentPane.add(btnDividir);
		btnDividir.addActionListener(new Llamador());
		btnDividir.setActionCommand("Dividir");
		
		JButton btnUnir = new JButton("Juntar partes");
		btnUnir.setBounds(541, 227, 114, 23);
		contentPane.add(btnUnir);
		btnUnir.addActionListener(new Llamador());
		btnUnir.setActionCommand("JuntarPartes");
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(10, 227, 89, 23);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new Llamador());
		btnSalir.setActionCommand("Salir");
	}
}
