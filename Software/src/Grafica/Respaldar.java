package Grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.exceptions.NoHayDatosQueGuardarException;
import Logica.exceptions.PersistenciaException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Respaldar extends JFrame {

	private JPanel contentPane;
	private Respaldar miVentana;

	public Respaldar() {
		setTitle("Sistema Viandas - Respaldo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel DashBoard = new JPanel();
		contentPane.add(DashBoard, BorderLayout.CENTER);
		DashBoard.setLayout(null);
		
		JPanel PanelCreoVentaInicial = new JPanel();
		PanelCreoVentaInicial.setBounds(0, 0, 434, 35);
		PanelCreoVentaInicial.setBackground(new Color(30, 60, 80));
		DashBoard.add(PanelCreoVentaInicial);
		PanelCreoVentaInicial.setLayout(new BorderLayout(0, 0));
		
		JLabel lblGuardar = new JLabel("  Guardar");
		lblGuardar.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuardar.setForeground(Color.WHITE);
		lblGuardar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		PanelCreoVentaInicial.add(lblGuardar, BorderLayout.CENTER);
		
		JLabel lblTexto = new JLabel("Se almacenará la información del sistema en disco. ¿Desea continuar?");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblTexto.setBounds(10, 56, 404, 25);
		DashBoard.add(lblTexto);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ControladorRespaldar miControlador = new ControladorRespaldar(miVentana);
				miControlador.guardarCambios();
				dispose();
			}
		});
		btnAceptar.setBounds(90, 92, 89, 23);
		DashBoard.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			dispose();
			}
		});
		btnCancelar.setBounds(229, 92, 89, 23);
		DashBoard.add(btnCancelar);
		
		this.setLocationRelativeTo(null); 
	}
}
