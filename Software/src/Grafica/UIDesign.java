package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class UIDesign extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorNuevaVianda miControladorNuevaVianda;
	private JPanel contentPane;
	boolean a = true;


	//Cambia el color del JPanel
	public void cambiarColor (JPanel panel, Color color)
	{
		panel.setBackground(color);
	}

	//Cambia el color del menú desplegable
	public void clickmenu (JPanel h1, JPanel h2, int numberbool)
	{
		if (numberbool == 1)
		{
			h1.setBackground(new Color(121, 141, 142));
			h2.setBackground(new Color(30, 60, 80));
		}
		else
		{
			h1.setBackground(new Color(30, 60, 80));
			h2.setBackground(new Color (73, 128, 242));
		}
	}

	//Cambia el ícono al desplegar el menú
	public void changeImage (JLabel boton, String rutaImg)
	{
		ImageIcon icon = new ImageIcon (getClass().getResource(rutaImg));
		boton.setIcon(icon);
	}
	
	

	//Funcionamiento del menú desplegable
	public void hideshow (JPanel menushowhide, boolean dashboard, JLabel boton, JPanel buttonUno, JPanel buttonDos, JPanel buttonTres, JPanel buttonCuatro, JPanel buttonCinco, JPanel ButtonSeis)
	{
		if (dashboard == true)
		{
			menushowhide.setBounds(0, 0, 50, 540);
			changeImage(boton, "/Iconos/back_32px.png");
			buttonUno.setVisible(dashboard);
			buttonDos.setVisible(dashboard);
			buttonTres.setVisible(dashboard);
			buttonCuatro.setVisible(dashboard);
			buttonCinco.setVisible(dashboard);
			ButtonSeis.setVisible(dashboard);
		}
		else
		{
			menushowhide.setBounds(0, 0, 310, 540);
			changeImage(boton, "/Iconos/menu_32px.png");
			cambiarColor(menushowhide, new Color (35, 71, 95));
			buttonUno.setVisible(false);
			buttonDos.setVisible(false);
			buttonTres.setVisible(false);
			buttonCuatro.setVisible(false);
			buttonCinco.setVisible(false);
			ButtonSeis.setVisible(false);
		}
	}

	public UIDesign() throws MalformedURLException, RemoteException, NotBoundException {
		setResizable(false);
		setTitle("Sistema Viandas - Inicio");
		setPreferredSize(new Dimension(700, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		//Menu principal  
		JPanel Menu = new JPanel();
		Menu.setPreferredSize(new Dimension(310, 10));
		Menu.setBackground(new Color (73, 128, 242));
		contentPane.add(Menu, BorderLayout.WEST);
		Menu.setLayout(new BorderLayout(0, 0));

		//Menu principal oculto
		JPanel MenuEscondido = new JPanel();
		MenuEscondido.setMaximumSize(new Dimension(50, 700));
		MenuEscondido.setPreferredSize(new Dimension(50, 400));
		MenuEscondido.setBackground(new Color (35, 71, 95));
		Menu.add(MenuEscondido, BorderLayout.CENTER);
		MenuEscondido.setLayout(null);

		//Defino el boton nueva vianda del menu
		JPanel BotonNuevaVianda = new JPanel();
		BotonNuevaVianda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonNuevaVianda.setVisible(false);
		BotonNuevaVianda.setBounds(18, 11, 220, 32);
		BotonNuevaVianda.setBackground(new Color (35, 71, 95));
		MenuEscondido.add(BotonNuevaVianda);
		BotonNuevaVianda.setLayout(new BorderLayout(0, 0));

		//Defino acciones sobre el boton BotonNuevaVianda
		AltaNuevaVianda miVentanaNuevaVianda= new AltaNuevaVianda();
		JLabel lblAltaNuevaVianda = new JLabel("  Ingresar nueva vianda");
		lblAltaNuevaVianda.setForeground(Color.WHITE);
		lblAltaNuevaVianda.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cambiarColor(BotonNuevaVianda, new Color(247, 78, 105));
			}
			public void mouseExited(MouseEvent e) {
				cambiarColor(BotonNuevaVianda, new Color (35, 71, 95));
			}
			public void mouseClicked(MouseEvent e) {
				miVentanaNuevaVianda.setVisible(true);
			}
		});
		lblAltaNuevaVianda.setHorizontalAlignment(SwingConstants.LEFT);
		BotonNuevaVianda.add(lblAltaNuevaVianda);
		lblAltaNuevaVianda.setFont(new Font("Segoe UI Light", Font.BOLD, 15));

		//Defino el boton nueva venta del menu
		JPanel BotonNuevaVenta = new JPanel();
		BotonNuevaVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonNuevaVenta.setVisible(false);
		BotonNuevaVenta.setBounds(18, 54, 220, 32);
		BotonNuevaVenta.setBackground(new Color (35, 71, 95));
		MenuEscondido.add(BotonNuevaVenta);
		BotonNuevaVenta.setLayout(new BorderLayout(0, 0));

		//Defino las acciones sobre el boton BotonNuevaVenta
		JLabel lblAltaNuevaVenta = new JLabel("  Ingresar nueva venta");
		lblAltaNuevaVenta.setForeground(Color.WHITE);
		lblAltaNuevaVenta.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cambiarColor(BotonNuevaVenta, new Color(247, 78, 105));
			}
			public void mouseExited(MouseEvent e) {
				cambiarColor(BotonNuevaVenta, new Color (35, 71, 95));
			}
			public void mouseClicked(MouseEvent e) {
				try{
					AltaNuevaVenta miVentanaAltaNuevaVenta = new AltaNuevaVenta();
					miVentanaAltaNuevaVenta.setVisible(true);
					//miControladorNuevaVianda.altaNuevaVianda();
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);				}
			}
		});
		lblAltaNuevaVenta.setHorizontalAlignment(SwingConstants.LEFT);
		BotonNuevaVenta.add(lblAltaNuevaVenta, BorderLayout.CENTER);
		lblAltaNuevaVenta.setFont(new Font("Segoe UI Light", Font.BOLD, 15));

		//Defino el boton ListadoViandas del menú
		JPanel BotonListadoViandas = new JPanel();
		BotonListadoViandas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonListadoViandas.setVisible(false);
		BotonListadoViandas.setBounds(18, 141, 220, 32);
		BotonListadoViandas.setBackground(new Color (35, 71, 95));
		MenuEscondido.add(BotonListadoViandas);
		BotonListadoViandas.setLayout(new BorderLayout(0, 0));

		//Defino las acciones sobre el boton BotonListadoViandas
		JLabel lblListadoViandas = new JLabel("  Listado de Viandas");
		lblListadoViandas.setForeground(Color.WHITE);
		lblListadoViandas.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cambiarColor(BotonListadoViandas, new Color(247, 78, 105));
			}
			public void mouseExited(MouseEvent e) {
				cambiarColor(BotonListadoViandas, new Color (35, 71, 95));
			}
			public void mouseClicked(MouseEvent e) {
				ListadoGeneralViandas miVentanaListadoViandas;
				try {
					miVentanaListadoViandas = new ListadoGeneralViandas();
					miVentanaListadoViandas.setVisible(true);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		lblListadoViandas.setHorizontalAlignment(SwingConstants.LEFT);
		BotonListadoViandas.add(lblListadoViandas);
		lblListadoViandas.setFont(new Font("Segoe UI Light", Font.BOLD, 15));

		//Defino el boton Listado de Ventas del menu
		JPanel BotonListadoVentas = new JPanel();
		BotonListadoVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonListadoVentas.setVisible(false);
		BotonListadoVentas.setBounds(18, 185, 220, 32);
		BotonListadoVentas.setBackground(new Color (35, 71, 95));
		MenuEscondido.add(BotonListadoVentas);
		BotonListadoVentas.setLayout(new BorderLayout(0, 0));

		//Defino las acciones sobre el boton BotonListadoVentas
		JLabel lblListadoVentas = new JLabel("  Listado de Ventas");
		lblListadoVentas.setForeground(Color.WHITE);
		lblListadoVentas.setHorizontalAlignment(SwingConstants.LEFT);
		BotonListadoVentas.add(lblListadoVentas);
		lblListadoVentas.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cambiarColor(BotonListadoVentas, new Color(247, 78, 105));
			}
			public void mouseExited(MouseEvent e) {
				cambiarColor(BotonListadoVentas, new Color (35, 71, 95));
			}
			public void mouseClicked(MouseEvent arg0) {
				try {
					ListadoGeneralVentas miVentanaListadoVentas = new ListadoGeneralVentas();
					miVentanaListadoVentas.setVisible(true);
				}
				catch (Exception e) {
				}
			}
		});
		lblListadoVentas.setFont(new Font("Segoe UI Light", Font.BOLD, 15));

		//Defino el boton guardar del menu
		JPanel BotonGuardar = new JPanel();
		BotonGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonGuardar.setVisible(false);
		BotonGuardar.setBounds(18, 228, 220, 32);
		BotonGuardar.setBackground(new Color (35, 71, 95));
		MenuEscondido.add(BotonGuardar);
		BotonGuardar.setLayout(new BorderLayout(0, 0));

		//Defino las acciones sobre el boton BotonGuardar
		JLabel lblGuardar = new JLabel("  Guardar");
		lblGuardar.setForeground(Color.WHITE);
		lblGuardar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cambiarColor(BotonGuardar, new Color(247, 78, 105));
			}
			public void mouseExited(MouseEvent e) {
				cambiarColor(BotonGuardar, new Color (35, 71, 95));
			}
			public void mouseClicked(MouseEvent e) {
				Respaldar miVentana = new Respaldar();
				miVentana.setVisible(true);
			}
		});
		lblGuardar.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuardar.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		BotonGuardar.add(lblGuardar);		

		//Defino el boton modificar venta  del menu
		JPanel BotonModificarVenta = new JPanel();
		BotonModificarVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonModificarVenta.setVisible(false);
		BotonModificarVenta.setBounds(18, 97, 220, 33);
		BotonModificarVenta.setBackground(new Color (35, 71, 95));
		MenuEscondido.add(BotonModificarVenta);
		BotonModificarVenta.setLayout(new BorderLayout(0, 0));
		BotonListadoVentas.setVisible(false);

		//Defino las acciones sobre el boton BotonModificarVenta
		JLabel lblModificarVenta = new JLabel("  Modificar venta");
		lblModificarVenta.setForeground(Color.WHITE);
		lblModificarVenta.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cambiarColor(BotonModificarVenta, new Color(247, 78, 105)); 
			}
			public void mouseExited(MouseEvent e) {
				cambiarColor(BotonModificarVenta, new Color (35, 71, 95));
			}
			public void mouseClicked(MouseEvent e) {
				ModificarVenta.getInstancia().setVisible(true);
			}
		});
		lblModificarVenta.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		BotonModificarVenta.add(lblModificarVenta);

		//Defino el menu lateral
		JPanel MenuLateral = new JPanel();
		Menu.add(MenuLateral, BorderLayout.WEST);
		MenuLateral.setPreferredSize(new Dimension(50, 10));
		MenuLateral.setBackground(new Color(30, 60, 80));
		MenuLateral.setLayout(null);

		//Línea del menú lateral
		JPanel LineSeting = new JPanel();
		LineSeting.setBounds(0, 0, 50, 5);
		MenuLateral.add(LineSeting);
		LineSeting.setBackground(new Color (30, 60, 80));

		//Menú lateral, despliegue
		JPanel HideMenu = new JPanel();
		HideMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		HideMenu.setBounds(0, 5, 50, 42);
		MenuLateral.add(HideMenu);
		HideMenu.setBackground(new Color(30, 60, 80));

		JLabel IconoOpciones = new JLabel("");
		IconoOpciones.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cambiarColor(LineSeting, new Color(247, 78, 105));
				cambiarColor(HideMenu, new Color(121, 141, 142));
			}
			public void mouseExited(MouseEvent e) {
				cambiarColor(LineSeting, new Color(30, 60, 80));
				cambiarColor(HideMenu, new Color(30, 60, 80));
			}
			public void mouseClicked(MouseEvent e) {
				clickmenu(HideMenu, MenuEscondido, 1);
				if (a == true)
				{
					hideshow(MenuEscondido, a, IconoOpciones, BotonNuevaVianda, BotonNuevaVenta, BotonListadoViandas, BotonListadoVentas, BotonGuardar, BotonModificarVenta);
					SwingUtilities.updateComponentTreeUI(HideMenu);
					a = false;
				}
				else
				{
					hideshow(MenuEscondido, a, IconoOpciones, BotonNuevaVianda, BotonNuevaVenta, BotonListadoViandas, BotonListadoVentas, BotonGuardar, BotonModificarVenta);
					SwingUtilities.updateComponentTreeUI(HideMenu);
					a = true;
				}
			}
		});

		IconoOpciones.setHorizontalTextPosition(SwingConstants.CENTER);
		IconoOpciones.setIcon(new ImageIcon(UIDesign.class.getResource("/Iconos/menu_32px.png")));
		HideMenu.add(IconoOpciones);

		JPanel Dashboard = new JPanel();
		Dashboard.setBackground(new Color (35, 71, 95));
		contentPane.add(Dashboard, BorderLayout.CENTER);
		Dashboard.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblImagen.setIconTextGap(5);
		Dashboard.add(lblImagen, BorderLayout.CENTER);
		changeImage(lblImagen, "/Iconos/logo4.png");

		JPanel PanelTitulo = new JPanel();
		contentPane.add(PanelTitulo, BorderLayout.NORTH);
		PanelTitulo.setBackground(new Color(30, 60, 80));

		JLabel lblTitulo = new JLabel("Tu puesto de venta");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		PanelTitulo.add(lblTitulo);
		
		

		this.setLocationRelativeTo(null); 
	}
}
