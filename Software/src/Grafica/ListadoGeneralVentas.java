package Grafica;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JTable;
import java.awt.Color;

import javax.swing.SwingConstants;
import java.awt.Dimension;

import Logica.valueobjects.VOVenta;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;

public class ListadoGeneralVentas extends JFrame {

	private JPanel contentPane;
	private JTable tabMisVentas;
	private static ListadoGeneralVentas instancia;
	private JPanel panel_1;
	private JPanel PanelCreoVentaInicial;
	private JLabel lblNewLabel;
	private  ListadoGeneralVentas miVentana;
	private JButton btnModificar;
	private  int idVenta;
	private JScrollPane scrollPane;

	

	public synchronized static ListadoGeneralVentas getInstancia()
	{
		try{
			if (instancia == null)
			{
				instancia = new ListadoGeneralVentas();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return instancia;
	}

	//Limpio el jtable
	private void limpioTabla()
	{
		DefaultTableModel modelo = (DefaultTableModel) tabMisVentas.getModel();
		int total=modelo.getRowCount();
		for(int i=total-1; i>-1; i--)
		{
			modelo.removeRow(i);
		}
	}

	//Valido si la tabla tiene datos
	private boolean tablaConDatos()
	{
		DefaultTableModel modelo = (DefaultTableModel) tabMisVentas.getModel();
		int total=modelo.getRowCount();
		return total>0;
	}

	private void CargoTabla(){
		//Obtengo el modelo y el controlador
		final ControladorListadoGeneralVentas micontrolador= new ControladorListadoGeneralVentas(miVentana);
		DefaultTableModel modelo= (DefaultTableModel) tabMisVentas.getModel();
		//Obtengo la lista
		ArrayList<VOVenta> listaVentas=micontrolador.listadoGeneralDeVentas();
		if(listaVentas!=null)
		{
			String fin;
			VOVenta pVenta;
			String fecha;
			for(int a=0; a<listaVentas.size();a++){
				pVenta=listaVentas.get(a);
				if(pVenta.getFinalizado()) {
					fin="Finalizado";
				}
				else
				{
					fin="Pendiente";
				}
				fecha=pVenta.convertirFechaAString(pVenta.getFechaYHora());
				modelo.addRow(new Object[]{pVenta.getNumero(),pVenta.getDireccion(),fecha,pVenta.getMontoTotal(),fin});
			}
		}
	}


	public ListadoGeneralVentas() throws MalformedURLException, RemoteException, NotBoundException {

		setTitle("Sistema Viandas - Listado general de ventas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setSize(700, 400);
		setResizable(false);
		setLocation(400, 100);
		contentPane.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 686, 321);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		PanelCreoVentaInicial = new JPanel();
		PanelCreoVentaInicial.setBounds(0, 0, 686, 35);
		PanelCreoVentaInicial.setMaximumSize(new Dimension(32767, 32766));
		PanelCreoVentaInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		PanelCreoVentaInicial.setBackground(new Color(30, 60, 80));
		panel_1.add(PanelCreoVentaInicial);
		PanelCreoVentaInicial.setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel("  Listado de ventas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		PanelCreoVentaInicial.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 666, 264);
		panel_1.add(scrollPane);

		//Obtengo id de venta de la fila de la tabla con un click
		tabMisVentas = new JTable();
		scrollPane.setViewportView(tabMisVentas);
		tabMisVentas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int numFilaSeleccionada=tabMisVentas.getSelectedRow();
				String id=tabMisVentas.getValueAt(numFilaSeleccionada, 0).toString().trim();
				idVenta=Integer.parseInt(id);
			}
		});

		// Defino el jtable
		tabMisVentas.setColumnSelectionAllowed(true);
		tabMisVentas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Número venta", "Fecha", "Descripción", "Monto Total", "Finalizada"
				}
				) 
		{
		//Tabla no es editable
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column){return false;}}
		);
		tabMisVentas.getColumnModel().getColumn(0).setPreferredWidth(116);
		tabMisVentas.getColumnModel().getColumn(2).setPreferredWidth(228);
		tabMisVentas.getColumnModel().getColumn(3).setPreferredWidth(117);
		tabMisVentas.getColumnModel().getColumn(4).setPreferredWidth(83);

		//Boton modificar ventas
		btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				ModificarVenta.getInstancia().setpVenta(idVenta);
				limpioTabla();
				ListadoGeneralVentas.getInstancia().setVisible(false);
				ModificarVenta.getInstancia().setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(104, 337, 89, 23);
		contentPane.add(btnModificar);

		//Boton finalizar venta
		JButton btnFinalizarVenta = new JButton("Finalizar venta");
		btnFinalizarVenta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (idVenta!=0)
				{
					FinalizarVenta.getInstancia().setIdVenta(idVenta);
					limpioTabla(); 
					ListadoGeneralVentas.getInstancia().setVisible(false);
					FinalizarVenta.getInstancia().setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(panel_1, "Debe seleccionar una venta para avanzar.");
				}}

		});
		btnFinalizarVenta.setBounds(203, 337, 121, 23);
		contentPane.add(btnFinalizarVenta);

		//Muestro contenido de la tabla
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				limpioTabla();
				//Cargo la tabla con las ventas 
				CargoTabla();
				if(!tablaConDatos())
				{
					ListadoGeneralVentas.getInstancia().setVisible(false);
					dispose ();
				}
			}
		});
		btnMostrar.setBounds(5, 337, 89, 23);
		contentPane.add(btnMostrar);
		this.setLocationRelativeTo(null); 
	}
}