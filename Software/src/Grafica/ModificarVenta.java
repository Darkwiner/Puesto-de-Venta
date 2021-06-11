package Grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logica.exceptions.CantidadAEliminarINcorrectaException;
import Logica.exceptions.NoExisteLaVentaException;
import Logica.exceptions.NoExisteLaViandaEnPedidoVentaException;
import Logica.exceptions.NoExisteLaViandaException;
import Logica.exceptions.NoExistenViandasException;
import Logica.exceptions.NoPuedoAgregarMasViandasException;
import Logica.exceptions.VentaFinalizadaException;
import Logica.valueobjects.VOInicioVenta;
import Logica.valueobjects.VOTipoVianda;
import Logica.valueobjects.VOVenta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModificarVenta extends JFrame {

	private JPanel contentPane;
	public static JTextField txtCodigoVenta;
	private static JTextField txtCodigoVianda;
	private static JTextField txtCantidad;
	private ModificarVenta miVentana;
	private static ModificarVenta instancia;
	private JTable tabMisViandas;
	private String idVenta;
	private String codVianda;
	private int pVenta = 0;
	private JTable tabMisVentas;

	private void noVacio (JTextField campo, JLabel aviso)
	{       
		String texto=campo.getText().trim();
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

	private boolean camposConDatos (JTextField numeroVenta, JTextField codigoVianda, JTextField cantidadViandas)
	{
		String numero=numeroVenta.getText().trim();
		numero=numero.replaceAll(" ", "");

		String codigo=codigoVianda.getText().trim();
		codigo=codigo.replaceAll(" ", "");

		String cant=cantidadViandas.getText().trim();
		cant=cant.replaceAll(" ", "");

		return ((numero.length()==0) || (codigo.length()==0) || (cant.length()==0));
	}

	public void setpVenta(int miVenta) {
		pVenta = miVenta;
		String mustroVenta = String.format("" + pVenta).trim();
		if(miVenta>0) 
		{
			txtCodigoVenta.setText(mustroVenta);
		}
	}

	public synchronized static ModificarVenta getInstancia() {
		try {
			if (instancia == null) {
				instancia = new ModificarVenta();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return instancia;
	}

	private void limpioDatos() {
		txtCantidad.setText("");
		txtCodigoVenta.setText("");
		txtCodigoVianda.setText("");
	}

	public ModificarVenta() throws MalformedURLException, RemoteException, NotBoundException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {

				limpioDatos();
				limpioTablaViandas();
				limpioTablaVentas();
				dispose();
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(0, 0, 700, 150));
		panel.setPreferredSize(new Dimension(900, 100));
		contentPane.add(panel);
		panel.setBounds(0, 37, 809, 121);
		panel.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("*");
		lblNewLabel_2_2.setForeground(Color.RED);
		lblNewLabel_2_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(103, 35, 12, 14);
		panel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_2_1 = new JLabel("*");
		lblNewLabel_2_2_1.setForeground(Color.RED);
		lblNewLabel_2_2_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_2_2_1.setBounds(243, 36, 12, 14);
		panel.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_2_2_2 = new JLabel("*");
		lblNewLabel_2_2_2.setForeground(Color.RED);
		lblNewLabel_2_2_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_2_2_2.setBounds(337, 36, 12, 14);
		panel.add(lblNewLabel_2_2_2);

		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero de venta");
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 31, 105, 23);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("C\u00F3digo de vianda");
		lblNewLabel_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(150, 31, 105, 23);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Cantidad");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(290, 31, 105, 23);
		panel.add(lblNewLabel_1_1_1);

		txtCodigoVenta = new JTextField();
		txtCodigoVenta.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//Deja digitar solo numeros
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE) {
					e.consume();
				}
				noVacio(txtCodigoVenta, lblNewLabel_2_2);
			}
		});
		txtCodigoVenta.setBounds(10, 56, 105, 20);
		panel.add(txtCodigoVenta);
		txtCodigoVenta.setColumns(10);

		txtCodigoVianda = new JTextField();
		txtCodigoVianda.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				noVacio(txtCodigoVianda, lblNewLabel_2_2_1);
			}
		});
		txtCodigoVianda.setColumns(10);
		txtCodigoVianda.setBounds(150, 56, 105, 20);
		panel.add(txtCodigoVianda);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//Deja digitar solo numeros
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE) {
					e.consume();
				}
				noVacio(txtCantidad, lblNewLabel_2_2_2);
			}
		});
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(290, 56, 105, 20);
		panel.add(txtCantidad);

		//Agrego vianda a venta
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (camposConDatos(txtCodigoVenta, txtCodigoVianda, txtCantidad))
				{
					JOptionPane.showMessageDialog(panel, "Debe completar todos los datos requeridos.");
				}
				else
				{
					
					
					int cantidad = Integer.parseInt(txtCantidad.getText().trim());
					int numV = Integer.parseInt(txtCodigoVenta.getText().trim());
					String codVianda = txtCodigoVianda.getText().trim();
					int confirmacion = JOptionPane.showConfirmDialog(btnAgregar,  "¿Esta seguro que quiere agregar " +cantidad+" viandas de la venta "+numV+ "?", getTitle(), 2);
					if (confirmacion == JOptionPane.YES_OPTION)
					{
						try {
							agregarVianda(codVianda, cantidad, numV);
							limpioTablaVentas();
							limpioDatos();
							CargoTablaVentas();
						} catch (RemoteException | MalformedURLException | NotBoundException | NoPuedoAgregarMasViandasException | VentaFinalizadaException | NoExisteLaVentaException | NoExisteLaViandaException e1){
							e1.printStackTrace();
						}	
					}
				}
			}
		});

		btnAgregar.setFont(new Font("Segoe UI Light", Font.BOLD, 11));
		btnAgregar.setBounds(10, 87, 105, 23);
		panel.add(btnAgregar);

		//Elimino vianda de venta
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (camposConDatos(txtCodigoVenta, txtCodigoVianda, txtCantidad))
				{
					JOptionPane.showMessageDialog(panel, "Debe completar todos los datos requeridos.");
				}
				else
				{
					int cantidad = Integer.parseInt(txtCantidad.getText().trim());
					int numV = Integer.parseInt(txtCodigoVenta.getText().trim());
					String codVianda = txtCodigoVianda.getText().trim();

					int confirmacion = JOptionPane.showConfirmDialog(btnEliminar,  "¿Esta seguro que quiqre eliminar " +cantidad+" viandas de la venta "+numV+ "?", getTitle(), 2);
					if (confirmacion == JOptionPane.YES_OPTION)
					{
						try 
						{
							eliminarVianda(codVianda, cantidad, numV);
							limpioTablaVentas();
							limpioDatos();
							CargoTablaVentas();
						} catch (RemoteException | CantidadAEliminarINcorrectaException | NoExisteLaVentaException
								| NoExisteLaViandaEnPedidoVentaException | VentaFinalizadaException
								| NoExisteLaViandaException | MalformedURLException | NotBoundException e1) {
							e1.printStackTrace();	
						}
					}
				}
			}
		});
		btnEliminar.setFont(new Font("Segoe UI Light", Font.BOLD, 11));
		btnEliminar.setBounds(150, 87, 105, 23);
		panel.add(btnEliminar);

		ButtonGroup grupoDeRadioButton;
		grupoDeRadioButton = new ButtonGroup();

		JPanel PanelTituloModificarVenta = new JPanel();
		PanelTituloModificarVenta.setBackground(new Color(30, 60, 80));
		PanelTituloModificarVenta.setBounds(0, 5, 809, 35);
		contentPane.add(PanelTituloModificarVenta);
		PanelTituloModificarVenta.setLayout(new BorderLayout(0, 0));

		JLabel lblTitulo = new JLabel("  Modificar venta");
		lblTitulo.setToolTipText("Sistema Viandas - Modifica venta");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		PanelTituloModificarVenta.add(lblTitulo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 144, 674, 14);
		contentPane.add(separator_1);

		JPanel panelTablas = new JPanel();
		panelTablas.setBounds(0, 157, 809, 293);
		contentPane.add(panelTablas);
		panelTablas.setLayout(null);

		JLabel lblVentasRegistradas = new JLabel("  Ventas registradas");
		lblVentasRegistradas.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblVentasRegistradas.setBounds(10, 5, 117, 23);
		panelTablas.add(lblVentasRegistradas);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 172, 789, 115);
		panelTablas.add(scrollPane);

		tabMisViandas = new JTable();
		scrollPane.setViewportView(tabMisViandas);
		tabMisViandas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {

				DefaultTableModel tm = (DefaultTableModel)tabMisViandas.getModel();
				//aca capturo el primer dato de la celda seleccionada 
				String dato=String.valueOf(tm.getValueAt(tabMisViandas.getSelectedRow(),0));
				ModificarVenta.txtCodigoVianda.setText(dato);
			}
		});
		tabMisViandas.setColumnSelectionAllowed(true);
		tabMisViandas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Código", "Descripción", "Precio"
				}
				)
				{
				//Tabla no es editable
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column){return false;}}
				);
		
		JScrollPane scbViandas = new JScrollPane();
		scbViandas.setBounds(771, 183, 17, 109);
		tabMisViandas.add(scbViandas);
		scbViandas.setVisible(true);

		JLabel lblViandas = new JLabel("   Viandas");
		lblViandas.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblViandas.setBounds(10, 149, 117, 23);
		panelTablas.add(lblViandas);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 29, 789, 115);
		panelTablas.add(scrollPane_1);

		tabMisVentas = new JTable();
		tabMisVentas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tm = (DefaultTableModel)tabMisVentas.getModel();
				//Capturo el primer dato de la celda seleccionada 
				String dato=String.valueOf(tm.getValueAt(tabMisVentas.getSelectedRow(),0));
				ModificarVenta.txtCodigoVenta.setText(dato);
			}
		});
		scrollPane_1.setViewportView(tabMisVentas);
		tabMisVentas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Número venta", "Dirección", "Fecha", "Monto Total", "Estado"
				}
				)
				{
				//Tabla no es editable
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column){return false;}}
				);
		
		tabMisVentas.setColumnSelectionAllowed(true);
		JScrollPane scbVentas = new JScrollPane();
		scbVentas.setBounds(771, 183, 1, 50);
		tabMisViandas.add(scbVentas);
		scbVentas.setVisible(true);

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cargo la tabla con las ventas 
				limpioTablaViandas();
				limpioTablaVentas();
				try {
					CargoTablaVentas();
					CargoTablaViandas();
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnMostrar.setBounds(649, 88, 150, 23);
		panel.add(btnMostrar);

		JLabel lblNewLabel = new JLabel("Campos obligatorios *");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 161, 20);
		panel.add(lblNewLabel);

		this.setLocationRelativeTo(null);
	}

	//Agrego vianda a venta
	private void agregarVianda(	String codVianda,int cantidad, int numV) throws MalformedURLException, RemoteException, NotBoundException, NoPuedoAgregarMasViandasException, VentaFinalizadaException, NoExisteLaVentaException, NoExisteLaViandaException 
	{
		final ControladorModificarVenta micontrolador = new ControladorModificarVenta(miVentana);
		micontrolador.agregarViandaAVenta(codVianda, cantidad, numV);

	}
	//Elimino vianda de  venta
	private void eliminarVianda(String codVianda,int cantidad, int numV) throws MalformedURLException, RemoteException, NotBoundException, CantidadAEliminarINcorrectaException, NoExisteLaVentaException, NoExisteLaViandaEnPedidoVentaException, VentaFinalizadaException, NoExisteLaViandaException 
	{
		final ControladorModificarVenta micontrolador = new ControladorModificarVenta(miVentana);
		micontrolador.eliminarViandaAVenta(codVianda, cantidad, numV);
	}

	//Cargo la tabla de ventas 
	private void CargoTablaVentas() throws MalformedURLException, RemoteException, NotBoundException {
		//Limpio el jtable y txtfild
		//limpioDatos();
		limpioTablaVentas();
		//Busco el controlador y obtengo la lista
		final ControladorModificarVenta micontrolador = new ControladorModificarVenta(miVentana);
		ArrayList<VOVenta> listaVentas = micontrolador.listadoGeneralDeVentas();
		if(listaVentas!=null)
		{
			//Variables
			DefaultTableModel modeloVenta = (DefaultTableModel) tabMisVentas.getModel();
			String fin;
			for (int a = 0; a < listaVentas.size(); a++) {
				VOVenta p0Venta = listaVentas.get(a);
				if (p0Venta.getFinalizado()) {
					fin = "Finalizado";
				} else {
					fin = "Pendiente";
				}
				String fecha = p0Venta.convertirFechaAString(p0Venta.getFechaYHora());
				modeloVenta.addRow(new Object[] { p0Venta.getNumero(), p0Venta.getDireccion(), fecha, p0Venta.getMontoTotal(), fin });
			}
		}
	}

	//Cargo la tabla con el listado general de viandas
	private void CargoTablaViandas() throws MalformedURLException, RemoteException, NotBoundException {
		//Limpio el jtable y txtfild
		//limpioDatos();
		limpioTablaViandas();
		//Busco el controlador y obtengo la lista
		final ControladorModificarVenta micontrolador = new ControladorModificarVenta(miVentana);
		ArrayList<VOTipoVianda> listaViandas= micontrolador.listadoGeneralDeViandas();
		if(listaViandas!=null)
		{
			//Variables
			DefaultTableModel modeloVianda= (DefaultTableModel) tabMisViandas.getModel();
			String esVege;
			VOTipoVianda p0Vianda;
			for(int a=0; a<listaViandas.size();a++){
				p0Vianda=listaViandas.get(a);
				if(p0Vianda.getEsVegetariana()) {
					esVege="Si";
				}else
				{
					esVege="No";
				}
				modeloVianda.addRow(new Object[]{p0Vianda.getCodigoAlfanumerico(),p0Vianda.getDescripcion(),p0Vianda.getPrecioUnitario()});
			}
		}
	}

	//Limpio el jtable
	private void limpioTablaVentas()
	{
		DefaultTableModel modelo = (DefaultTableModel) tabMisVentas.getModel();
		int total=modelo.getRowCount();
		for(int i=total-1; i>-1; i--)
		{
			modelo.removeRow(i);
		}
	}

	//Limpio el jtable
	private void limpioTablaViandas()
	{
		DefaultTableModel modelo = (DefaultTableModel) tabMisViandas.getModel();
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
		int totalVentas=modelo.getRowCount();
		DefaultTableModel modelo2 = (DefaultTableModel) tabMisViandas.getModel();
		int totalViandas=modelo2.getRowCount();
		return (totalViandas>0 && totalVentas>0);
	}
}
