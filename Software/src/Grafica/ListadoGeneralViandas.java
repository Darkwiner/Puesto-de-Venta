package Grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logica.exceptions.LaVentaNoTienePedidosException;
import Logica.exceptions.NoExisteLaVentaException;
import Logica.exceptions.NoExisteLaViandaException;
import Logica.exceptions.NoExistenViandasException;
import Logica.valueobjects.VOTipoVianda;
import Logica.valueobjects.VOVegetariana;
import Logica.valueobjects.VOVianda;
import Logica.valueobjects.VOViandaVenta;
import Logica.valueobjects.VOViandaVentaVegetariana;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListadoGeneralViandas extends JFrame {

	private JPanel contentPane;
	private JTable tabMisViandas;
	private static ListadoGeneralViandas instancia;
	private JButton btnListarTodasLasViandas;
	private JLabel lblBuscar;
	private JTextField txtCodigo;
	private JLabel lblBuscarDesc;
	private JTextField txtDescripcion;
	private JButton btnBuscarCodigo;
	private JButton btnBuscarDesc;
	private JPanel panel;
	private JPanel PanelCreoVentaInicial;
	private ListadoGeneralViandas miVentana;
	private JScrollPane scrollPane;
	private JTextField txtNumeroVenta;

	private void noVacio (JTextField campo, JButton boton)
	{       
		String texto=campo.getText();
		texto=texto.replaceAll(" ", "");
		if(texto.length()==0)
		{
			boton.setEnabled(false);
		}
		else
		{
			boton.setEnabled(true);
		}
	}

	private boolean camposConDatos (JTextField campo)
	{
		String codigo=campo.getText();
		codigo=codigo.replaceAll(" ", "");
		if(codigo.length()==0)
		{
			return false;
		}
		return true;
	}

	public synchronized static ListadoGeneralViandas getInstancia()
	{
		try{
			if (instancia == null)
			{
				instancia = new ListadoGeneralViandas();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return instancia;
	}

	public ListadoGeneralViandas() throws MalformedURLException, RemoteException, NotBoundException {
		setTitle("Sistema Viandas - Listado general de viandas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(true);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(15, 189, 676, 213);
		panel.setOpaque(true);
		panel.setPreferredSize(new Dimension(900, 300));//hint at size
		panel.setMinimumSize(new Dimension(100, 200));//hint at size
		contentPane.add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 659, 213);
		panel.add(scrollPane);

		tabMisViandas = new JTable();
		scrollPane.setViewportView(tabMisViandas);
		tabMisViandas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Codigo", "Descripción", "Precio", "Es ovolactea", "Descripción adicional", "Cantidad", "Vegetariana"
				}
				) 	
			{
			//Tabla no es editable
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){return false;}}
			);
		tabMisViandas.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabMisViandas.getColumnModel().getColumn(1).setPreferredWidth(185);
		tabMisViandas.getColumnModel().getColumn(2).setPreferredWidth(94);
		tabMisViandas.getColumnModel().getColumn(3).setPreferredWidth(84);
		tabMisViandas.getColumnModel().getColumn(4).setPreferredWidth(188);
		tabMisViandas.getColumnModel().getColumn(5).setPreferredWidth(70);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(new Rectangle(5, 50, 676, 137));
		panel_1.setPreferredSize(new Dimension(900, 100));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(166, 46, 89, 20);
		panel_1.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(166, 71, 89, 20);
		panel_1.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		btnBuscarCodigo = new JButton("Buscar");
		btnBuscarCodigo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!camposConDatos (txtCodigo))
				{
					JOptionPane.showMessageDialog(panel_1, "Debe ingresar una codigo para realizar la busqueda");
				}
				else
				{
					try {
						limpioTabla();
						CargoTablaConVianda(txtCodigo.getText().trim());
						limpioTXT();
					} catch (RemoteException | NoExisteLaViandaException | MalformedURLException | NotBoundException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(btnBuscarCodigo, e1.toString());
						limpioTabla();
						limpioTXT();
					}
				}
			}
		});
		btnBuscarCodigo.setBounds(265, 45, 89, 23);
		panel_1.add(btnBuscarCodigo);

		btnBuscarDesc = new JButton("Buscar");
		btnBuscarDesc.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!camposConDatos(txtDescripcion))
				{
					JOptionPane.showMessageDialog(panel_1, "Debe ingresar una decripcion para realizar la busqueda");
				}
				else
				{
					try {
						limpioTabla();
						CargoTablaviandasDescipcion( txtDescripcion.getText().trim());
						limpioTXT();
					} catch (RemoteException | NoExisteLaViandaException | MalformedURLException | NotBoundException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(btnBuscarCodigo, e1.toString());
						limpioTabla();
						limpioTXT();
					} catch (NoExistenViandasException e1) {
						e1.printStackTrace();
					} 
				}
			}
		});
		btnBuscarDesc.setBounds(265, 70, 89, 23);
		panel_1.add(btnBuscarDesc);

		// Boton mostrar todas la viandas del sistema
		btnListarTodasLasViandas = new JButton("Mostrar");
		btnListarTodasLasViandas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				try {
					limpioTabla();
					limpioTXT();
					CargoTablaGeneal();
					if(!tablaConDatos())
					{
						dispose ();
					}
				} 
				catch (RemoteException e1) 
				{
					e1.printStackTrace();	
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					e1.printStackTrace();
				} catch (NoExistenViandasException e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnListarTodasLasViandas.setBounds(166, 11, 89, 23);
		panel_1.add(btnListarTodasLasViandas);

		JLabel lblTodasLasVindas = new JLabel("Mostrar todas la viandas");
		lblTodasLasVindas.setBounds(10, 13, 154, 19);
		panel_1.add(lblTodasLasVindas);
		lblTodasLasVindas.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblBuscar = new JLabel("Buscar por codigo");
		lblBuscar.setBounds(10, 47, 102, 19);
		panel_1.add(lblBuscar);
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblBuscarDesc = new JLabel("Buscar por descripcion");
		lblBuscarDesc.setBounds(10, 72, 114, 19);
		panel_1.add(lblBuscarDesc);
		lblBuscarDesc.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblBuscarPorVenta = new JLabel("Buscar por n\u00B0 de venta");
		lblBuscarPorVenta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBuscarPorVenta.setBounds(10, 98, 135, 19);
		panel_1.add(lblBuscarPorVenta);

		txtNumeroVenta = new JTextField();
		txtNumeroVenta.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//Deja digitar solo números
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		txtNumeroVenta.setColumns(10);
		txtNumeroVenta.setBounds(166, 97, 89, 20);
		panel_1.add(txtNumeroVenta);

		JButton btnBuscarPorVenta = new JButton("Buscar");
		btnBuscarPorVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBuscarPorVenta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!camposConDatos(txtNumeroVenta))
				{
					JOptionPane.showMessageDialog(panel_1, "Debe ingresar un número de venta para realizar la busqueda");
				}
				else
				{
					try {
						limpioTabla();
						int numVenta = Integer.parseInt(txtNumeroVenta.getText().trim());
						CargoViandasDeVenta(numVenta);
						limpioTXT();
					} catch (RemoteException | NoExisteLaVentaException | MalformedURLException | NotBoundException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(btnBuscarPorVenta, e1.toString());
						limpioTabla();
						limpioTXT();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}
			}
		});
		btnBuscarPorVenta.setBounds(265, 95, 89, 23);
		panel_1.add(btnBuscarPorVenta);

		PanelCreoVentaInicial = new JPanel();
		PanelCreoVentaInicial.setMaximumSize(new Dimension(32767, 32766));
		PanelCreoVentaInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		PanelCreoVentaInicial.setBackground(new Color(30, 60, 80));
		PanelCreoVentaInicial.setBounds(5, 0, 686, 35);
		contentPane.add(PanelCreoVentaInicial);
		PanelCreoVentaInicial.setLayout(new BorderLayout(0, 0));

		JLabel lblListadoDeViandas = new JLabel("  Listado de viandas");
		lblListadoDeViandas.setHorizontalAlignment(SwingConstants.LEFT);
		lblListadoDeViandas.setForeground(Color.WHITE);
		lblListadoDeViandas.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		PanelCreoVentaInicial.add(lblListadoDeViandas, BorderLayout.CENTER);
		btnListarTodasLasViandas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.setLocationRelativeTo(null); 
	}

	//Limpio el jtable
	private void limpioTabla()
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
		DefaultTableModel modelo = (DefaultTableModel) tabMisViandas.getModel();
		int total=modelo.getRowCount();
		return total>0;
	}

	private void limpioTXT()
	{
		txtCodigo.setText("");
		txtDescripcion.setText("");
		txtNumeroVenta.setText("");
	}

	//Cargo la tabla con el listado general de viandas
	private void CargoTablaGeneal() throws MalformedURLException, RemoteException, NotBoundException, NoExistenViandasException{
		//Variables
		final ControladorListadoGeneralViandas miControlador=new ControladorListadoGeneralViandas(miVentana);
		DefaultTableModel modelo= (DefaultTableModel) tabMisViandas.getModel();
		ArrayList<VOTipoVianda>  listaViandas =	miControlador.ListadoGeneralViandas();
		if(listaViandas!=null)
		{
			String fin;
			VOTipoVianda pVianda;
			for(int a=0; a<listaViandas.size();a++){
				pVianda=listaViandas.get(a);
				if(pVianda.getEsVegetariana()) {
					fin="Si";
				}
				else
				{
					fin="No";
				}
				modelo.addRow(new Object[]{pVianda.getCodigoAlfanumerico(),pVianda.getDescripcion(),pVianda.getPrecioUnitario(),fin});
			}
		}
	}

	//Cargo la tabla con la vianda encontrada 
	private void CargoTablaConVianda(String  codigo) throws MalformedURLException, RemoteException, NotBoundException, NoExisteLaViandaException{
		DefaultTableModel modelo= (DefaultTableModel) tabMisViandas.getModel();
		final ControladorListadoGeneralViandas miControlador=new ControladorListadoGeneralViandas(miVentana);
		VOVianda p0Vianda = miControlador.listadoDetalladoDeVianda(codigo);
		if(!(p0Vianda==null))
		{
			String esVege="No";
			if(p0Vianda instanceof VOVianda && !(p0Vianda instanceof VOVegetariana) )
			{
				modelo.addRow(new Object[]{p0Vianda.getCodigoAlfanumerico(),p0Vianda.getDescripcion(),p0Vianda.getPrecioUnitario()});
			}
			else 
			{
				VOVegetariana pVianda = (VOVegetariana)p0Vianda;
				if(pVianda.getOvolactea())
				{
					esVege="Si";
					String esOvo="Si";
					modelo.addRow(new Object[]{pVianda.getCodigoAlfanumerico(),pVianda.getDescripcion(),pVianda.getPrecioUnitario(),esOvo ,pVianda.getDescripcionAdicional()});
				}
				else
				{
					modelo.addRow(new Object[]{p0Vianda.getCodigoAlfanumerico(),p0Vianda.getDescripcion(),p0Vianda.getPrecioUnitario()});
				}
			}
		}
	}

	//Cargo la tabla con el listado general de viandas
	private void CargoTablaviandasDescipcion(String descripcion) throws MalformedURLException, RemoteException, NotBoundException, NoExisteLaViandaException, NoExistenViandasException {
		//Variables
		setLocationRelativeTo(null);
		DefaultTableModel modelo= (DefaultTableModel) tabMisViandas.getModel();
		final ControladorListadoGeneralViandas miControlador=new ControladorListadoGeneralViandas(miVentana);
		ArrayList<VOTipoVianda>  listaViandas = miControlador.listadoDeViandaDescripcion(descripcion);
		String esVege="No";
		VOTipoVianda p0Vianda;
		for(int a=0; a<listaViandas.size();a++){
			p0Vianda=listaViandas.get(a);
			if(p0Vianda.getEsVegetariana()) {
				esVege="Si";
			}else
			{
				esVege="No";
			}
			modelo.addRow(new Object[]{p0Vianda.getCodigoAlfanumerico(),p0Vianda.getDescripcion(),p0Vianda.getPrecioUnitario()});
		}
	}

	//Cargo la tabla con las viandas de una venta 
	private void CargoViandasDeVenta(int numVenta) throws MalformedURLException, RemoteException, NotBoundException, LaVentaNoTienePedidosException, NoExisteLaVentaException {
		//Obtengo modelo y controlador
		DefaultTableModel modelo = (DefaultTableModel) tabMisViandas.getModel();
		final ControladorListadoGeneralViandas miControlador = new ControladorListadoGeneralViandas(miVentana);
		//Obtengo la lista
		ArrayList<VOTipoVianda> listaViandas = miControlador.listadoViandasVenta(numVenta);
		if(listaViandas!=null)
		{
			//Variables
			VOTipoVianda miVOTipoVianda;
			String esOvo;
			String esVege = "No"; 
			String adicional = "N/A";
			for (int i = 0; i < listaViandas.size(); i++) {
				miVOTipoVianda = listaViandas.get(i);
				if (miVOTipoVianda instanceof VOViandaVenta && !(miVOTipoVianda instanceof VOViandaVentaVegetariana)) {
					VOViandaVenta miViandaVenta = (VOViandaVenta) miVOTipoVianda;
					esOvo="No";
					modelo.addRow(new Object[] { miViandaVenta.getCodigoAlfanumerico(), miViandaVenta.getDescripcion(),
							miViandaVenta.getPrecioUnitario(), esOvo, adicional, miViandaVenta.getCantidad(), esVege });
				} else {
					VOViandaVentaVegetariana miViandaVentaVege = (VOViandaVentaVegetariana) miVOTipoVianda;
					esVege = "Si";
					if (miViandaVentaVege.getOvolactea()) {
						esOvo = "Si";
						modelo.addRow(new Object[] { miViandaVentaVege.getCodigoAlfanumerico(),
								miViandaVentaVege.getDescripcion(), miViandaVentaVege.getPrecioUnitario(),  esOvo,
								miViandaVentaVege.getDescripcionAdicional(), miViandaVentaVege.getCantidad(), esVege });
					} else {
						esOvo = "No";
						modelo.addRow(new Object[] { miViandaVentaVege.getCodigoAlfanumerico(),
								miViandaVentaVege.getDescripcion(), miViandaVentaVege.getPrecioUnitario(),esOvo,  
								miViandaVentaVege.getDescripcionAdicional(), miViandaVentaVege.getCantidad(), esVege });
					}
				}
			}
		}
	}
	
	//Metodo para mostrar string
	public  void mostrar(String msg)
	{
		JOptionPane.showMessageDialog(tabMisViandas, msg);
	}
}
