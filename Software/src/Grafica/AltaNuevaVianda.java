package Grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Logica.valueobjects.VOVegetariana;
import Logica.valueobjects.VOVianda;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AltaNuevaVianda extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigoAlfanumerico;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtDescripcionAdicional;
	private boolean ver = true ;
	private  AltaNuevaVianda miVentana;

	private void limpioDatos(JRadioButton rdbtnVegetariana, JRadioButton rdbtnOvolactea,JLabel lblDescripcionAdicional) {
		txtCodigoAlfanumerico.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
		txtDescripcionAdicional.setText("");
		rdbtnVegetariana.setSelected(false);
		rdbtnOvolactea.setSelected(false);
		rdbtnOvolactea.setVisible(false);
		txtDescripcionAdicional.setVisible(false);
		lblDescripcionAdicional.setVisible(false);
	}

	private void noVacio (JTextField campo, JLabel aviso)
	{       
		String texto=campo.getText();
		texto=texto.replaceAll(" ", "");
		if(texto.length()==0)
		{
			aviso.setVisible(true);
		}
		else
		{
			aviso.setVisible(false);
		}
	}

	private boolean camposConDatos (JTextField txtCodigoAlfanumerico, JTextField txtDescripcion, JTextField txtPrecio)
	{
		String codigo=txtCodigoAlfanumerico.getText().trim();
		codigo=codigo.replaceAll(" ", "");

		String descri=txtDescripcion.getText().trim();
		descri=descri.replaceAll(" ", "");

		String precio=txtPrecio.getText().trim();
		precio=precio.replaceAll(" ", "");

		return ((codigo.length()==0) || (descri.length()==0) || (precio.length()==0));
	}

	private void limpioDatos (JTextField campo)
	{
		campo.setText("");
	}

	public AltaNuevaVianda() throws MalformedURLException, RemoteException, NotBoundException{
		setTitle("Sistema Viandas - Nueva vianda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		setBounds(100, 100, 700, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblCodigoAlfanumerico = new JLabel("Codigo Alfanum\u00E9rico");
		lblCodigoAlfanumerico.setBounds(10, 81, 153, 14);
		panel.add(lblCodigoAlfanumerico);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(10, 117, 153, 14);
		panel.add(lblDescripcion);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 158, 153, 14);
		panel.add(lblPrecio);

		JLabel lblNewLabel_2_2 = new JLabel("* Requerido");
		lblNewLabel_2_2.setForeground(Color.RED);
		lblNewLabel_2_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(333, 84, 89, 14);
		panel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_2_1 = new JLabel("* Requerido");
		lblNewLabel_2_2_1.setForeground(Color.RED);
		lblNewLabel_2_2_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_2_2_1.setBounds(333, 120, 89, 14);
		panel.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_2_2_2 = new JLabel("* Requerido");
		lblNewLabel_2_2_2.setForeground(Color.RED);
		lblNewLabel_2_2_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_2_2_2.setBounds(333, 161, 89, 14);
		panel.add(lblNewLabel_2_2_2);

		txtCodigoAlfanumerico = new JTextField();
		txtCodigoAlfanumerico.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				noVacio(txtCodigoAlfanumerico, lblNewLabel_2_2);
			}
		});
		txtCodigoAlfanumerico.setBounds(149, 81, 174, 20);
		panel.add(txtCodigoAlfanumerico);
		txtCodigoAlfanumerico.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				noVacio(txtDescripcion, lblNewLabel_2_2_1);
			}
		});
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(149, 117, 174, 20);
		panel.add(txtDescripcion);

		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//Deja digitar solo numeros
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE) {
					e.consume();
				}
				noVacio(txtPrecio, lblNewLabel_2_2_2);
			}
		});
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(149, 158, 174, 20);
		panel.add(txtPrecio);

		JLabel lblDescripcionAdicional = new JLabel("Descripci\u00F3n Adicional");
		lblDescripcionAdicional.setBounds(10, 242, 141, 14);
		panel.add(lblDescripcionAdicional);
		lblDescripcionAdicional.setVisible(false);

		JRadioButton rdbtnOvolactea = new JRadioButton("Ovolactea");
		rdbtnOvolactea.setBounds(149, 198, 111, 23);
		panel.add(rdbtnOvolactea);
		rdbtnOvolactea.setVisible(false);

		txtDescripcionAdicional = new JTextField();
		txtDescripcionAdicional.setColumns(10);
		txtDescripcionAdicional.setBounds(149, 239, 265, 20);
		panel.add(txtDescripcionAdicional);
		txtDescripcionAdicional.setVisible(false);

		JRadioButton rdbtnVegetariana = new JRadioButton("Vegetariana");
		rdbtnVegetariana.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnVegetariana.isSelected()) {
					rdbtnOvolactea.setVisible(true);
					lblDescripcionAdicional.setVisible(true);
					txtDescripcionAdicional.setVisible(true);
				} else {
					rdbtnOvolactea.setVisible(false);
					lblDescripcionAdicional.setVisible(false);
					txtDescripcionAdicional.setVisible(false);
				}
			}
		});
		rdbtnVegetariana.setBounds(6, 198, 111, 23);
		panel.add(rdbtnVegetariana);

		final ControladorNuevaVianda micontrolador= new ControladorNuevaVianda(miVentana);		
		JButton btnAgregarVianda = new JButton("Crear");
		btnAgregarVianda.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (camposConDatos(txtCodigoAlfanumerico, txtDescripcion, txtPrecio))
				{
					JOptionPane.showMessageDialog(panel, "Debe completar todos los datos requeridos.");
				}
				else
				{
					float precio=Float.parseFloat((txtPrecio.getText().trim()));
					if (rdbtnVegetariana.isSelected()) 
					{
						VOVegetariana miVianda=new VOVegetariana(txtCodigoAlfanumerico.getText().trim(), txtDescripcion.getText().trim(), precio, rdbtnVegetariana.isSelected(), rdbtnOvolactea.isSelected(), txtDescripcionAdicional.getText().trim());
						VOVianda miV=(VOVianda)miVianda;
						micontrolador.altaNuevaVianda(miV);
					}
					else
					{
						VOVianda miVianda=new VOVianda(txtCodigoAlfanumerico.getText().trim(), txtDescripcion.getText().trim(), precio);
						micontrolador.altaNuevaVianda(miVianda);
					}
					limpioDatos(rdbtnVegetariana, rdbtnOvolactea, lblDescripcionAdicional);
				}
				limpioDatos(rdbtnVegetariana, rdbtnOvolactea, lblDescripcionAdicional);
			}
		});

		btnAgregarVianda.setBounds(575, 238, 89, 23);
		panel.add(btnAgregarVianda);

		JPanel PanelCreoVentaInicial = new JPanel();
		PanelCreoVentaInicial.setBackground(new Color(30, 60, 80));
		PanelCreoVentaInicial.setBounds(0, 0, 676, 35);
		panel.add(PanelCreoVentaInicial);
		PanelCreoVentaInicial.setLayout(new BorderLayout(0, 0));

		JLabel lblCreoVianda = new JLabel("  Crear vianda");
		lblCreoVianda.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreoVianda.setForeground(Color.WHITE);
		lblCreoVianda.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		PanelCreoVentaInicial.add(lblCreoVianda, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Campos obligatorios *");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 46, 161, 20);
		panel.add(lblNewLabel);

		this.setLocationRelativeTo(null); 
	}
}
