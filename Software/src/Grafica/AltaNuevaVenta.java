package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.valueobjects.VOInicioVenta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AltaNuevaVenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Dashboard;
	private static AltaNuevaVenta instancia;
	private JTextField txtDireccion;
	AltaNuevaVenta miVenta;

	private boolean campoConDatos (JTextField txtDireccion)
	{
		String codigo=txtDireccion.getText();
		codigo=codigo.replaceAll(" ", "");
		if(codigo.length()==0)
		{
			return false;
		}
		return true;
	}
	
	public synchronized static AltaNuevaVenta getInstancia()
	{
		try{
			if (instancia == null)
			{
				instancia = new AltaNuevaVenta();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return instancia;
	}
	
	public AltaNuevaVenta() throws RemoteException, NotBoundException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Sistema Viandas - Nueva venta");
		setBounds(100, 100, 814, 557);
		Dashboard = new JPanel();
		Dashboard.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Dashboard.setBackground(new Color (189, 196, 200));
		setContentPane(Dashboard);
		Dashboard.setLayout(null);
		setSize(700, 150);
        setLocation(400, 100);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n de entrega");
		lblDireccion.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblDireccion.setBounds(10, 46, 264, 20);
		Dashboard.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(10, 70, 512, 20);
		Dashboard.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JButton btnInicioVenta = new JButton("Iniciar venta");
		btnInicioVenta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!campoConDatos(txtDireccion))
				{
					JOptionPane.showMessageDialog(Dashboard, "Debe ingresar una dirección.");
				}
				else
				{
					int confirmacion = JOptionPane.showConfirmDialog(Dashboard,  "¿Los datos ingresados son correctos?", getTitle(), 2);
					if (confirmacion == JOptionPane.YES_OPTION) {
						final ControladorNuevaVenta miControlador = new ControladorNuevaVenta(miVenta);
						VOInicioVenta miVOVenta = new VOInicioVenta (LocalDateTime.now(), txtDireccion.getText().trim());
						miControlador.altaNuevaVenta(miVOVenta);
						txtDireccion.setText("");
					}
				}	
			}
		});
		btnInicioVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInicioVenta.setFont(new Font("Segoe UI Light", Font.BOLD, 12));
		btnInicioVenta.setBounds(532, 69, 130, 20);
		Dashboard.add(btnInicioVenta);
		
		JPanel PanelCreoVentaInicial = new JPanel();
		PanelCreoVentaInicial.setBackground(new Color (30, 60, 80));
		PanelCreoVentaInicial.setBounds(0, 0, 694, 35);
		Dashboard.add(PanelCreoVentaInicial);
		PanelCreoVentaInicial.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCreoVenta = new JLabel("  Crear venta");
		lblCreoVenta.setForeground(Color.WHITE);
		lblCreoVenta.setHorizontalAlignment(SwingConstants.LEFT);
		PanelCreoVentaInicial.add(lblCreoVenta);
		lblCreoVenta.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		
		this.setLocationRelativeTo(null); 
	}
}
