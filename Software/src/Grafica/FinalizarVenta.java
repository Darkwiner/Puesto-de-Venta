package Grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Logica.exceptions.NoExisteLaVentaException;
import Logica.exceptions.VentaFinalizadaException;
import Logica.valueobjects.VOInicioVenta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class FinalizarVenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static FinalizarVenta instancia;
	private FinalizarVenta miVentana;
	private int pVenta;

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalizarVenta frame = new FinalizarVenta();
					frame.setVisible(true);

				} catch (Exception e) {

				}
			}
		});
	}*/

	public void setIdVenta(int laVenta ) {
		this.pVenta=laVenta;
	}

	public void mostrar(String msg)
	{
		JOptionPane.showMessageDialog(null, msg);	
	}

	public synchronized static FinalizarVenta getInstancia()
	{
		try{
			if (instancia == null)
			{
				instancia = new FinalizarVenta();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return instancia;
	}

	public  FinalizarVenta() throws MalformedURLException, RemoteException, NotBoundException {
		setTitle("Sistema Viandas - Finalizar venta");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel PanelFinalizarVenta = new JPanel();
		PanelFinalizarVenta.setBounds(5, 51, 674, 101);
		PanelFinalizarVenta.setPreferredSize(new Dimension(900, 100));
		contentPane.add(PanelFinalizarVenta);
		PanelFinalizarVenta.setLayout(null);

		JRadioButton rdbtnConfirmar = new JRadioButton("Confirmar");
		rdbtnConfirmar.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		rdbtnConfirmar.setBounds(10, 36, 105, 23);
		PanelFinalizarVenta.add(rdbtnConfirmar);

		JRadioButton rdbtnCancelar = new JRadioButton("Cancelar");
		rdbtnCancelar.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		rdbtnCancelar.setBounds(10, 66, 105, 23);
		PanelFinalizarVenta.add(rdbtnCancelar);

		ButtonGroup grupoDeRadioButton;
		grupoDeRadioButton = new ButtonGroup();
		grupoDeRadioButton.add(rdbtnConfirmar);
		grupoDeRadioButton.add(rdbtnCancelar);

		JButton btnFinalizarVenta = new JButton("Aceptar");
		btnFinalizarVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				boolean confirmar = rdbtnConfirmar.isSelected();
				if (!rdbtnConfirmar.isSelected() && !rdbtnCancelar.isSelected())
				{
					JOptionPane.showMessageDialog(PanelFinalizarVenta, "Debe seleccionar una de las dos opciones.");
				}
				else
				{
					String operacion;
					if(rdbtnConfirmar.isSelected())
					{
						operacion="Confirmar ";
					}else
					{
						operacion="Cancelar ";
					}

					int confirmacion = JOptionPane.showConfirmDialog(btnFinalizarVenta,  operacion + "la venta: "+pVenta, getTitle(), 2);

					if (confirmacion == JOptionPane.YES_OPTION) {
						final ControladorFinalizarVenta miControlador = new ControladorFinalizarVenta(miVentana);
						miControlador.finalizarVentaEnProceso(pVenta, confirmar);
						if (operacion == "Confirmar ")
						{
							JOptionPane.showMessageDialog(PanelFinalizarVenta, "Venta finalizada correctamente.");
						}
						else
						{
							JOptionPane.showMessageDialog(PanelFinalizarVenta, "Venta cancelada correctamente.");
						}
						rdbtnCancelar.resetKeyboardActions();
						rdbtnConfirmar.resetKeyboardActions();
						FinalizarVenta.getInstancia().setVisible(false);
						dispose();
					}
					else if (confirmacion == JOptionPane.NO_OPTION) {
					}
				}		 
			}
		});
		btnFinalizarVenta.setFont(new Font("Segoe UI Light", Font.BOLD, 11));
		btnFinalizarVenta.setBounds(559, 66, 105, 23);
		PanelFinalizarVenta.add(btnFinalizarVenta);

		JPanel PanelTituloModificarVenta = new JPanel();
		PanelTituloModificarVenta.setBackground(new Color (30, 60, 80));
		PanelTituloModificarVenta.setBounds(5, 5, 674, 35);
		contentPane.add(PanelTituloModificarVenta);
		PanelTituloModificarVenta.setLayout(new BorderLayout(0, 0));

		JLabel lblTitulo = new JLabel("  Finalizar venta");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		PanelTituloModificarVenta.add(lblTitulo);

		JLabel lblNewLabel = new JLabel("* Opcional: confirmar o cancelar ");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 11));
		lblNewLabel.setBounds(0, 11, 282, 14);
		PanelFinalizarVenta.add(lblNewLabel);

		this.setLocationRelativeTo(null); 
	}
}
