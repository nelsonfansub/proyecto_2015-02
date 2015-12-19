import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Frame;
public class principal extends JFrame {

	private static principal instancia;
	private JPanel contentPane;
	private JPanel contentPane_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	principal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setEnabled(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmarCierre();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/imagenes/Book.png")));
		
	
		setTitle("Biblioteca estudiantil UASD 2015-02");
		setBounds(100, 100, 860, 669);
		setLocationRelativeTo(null);
		
		contentPane = new CambiarImagenDelFondo("imagenes/biblioteca.jpg");
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(new BorderLayout(0, 0));
	
		
		JMenuBar barraDelMenu = new JMenuBar();
		setJMenuBar(barraDelMenu);
		        
		JMenu MenuDeAcciones = new JMenu("Acciones");
		barraDelMenu.add(MenuDeAcciones);
		
		JMenuItem MenuAcciones01 = new JMenuItem("Cambiar de Usuario");
		MenuAcciones01.setIcon(new ImageIcon(principal.class.getResource("/imagenes/cambiar_User.jpg")));
		MenuAcciones01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Login HacerLogin = new Login();
				HacerLogin.setVisible(true);
			}
		});
		MenuDeAcciones.add(MenuAcciones01);
		
		JSeparator separator01 = new JSeparator();
		MenuDeAcciones.add(separator01);
		
		JMenuItem MenuAcciones02 = new JMenuItem("Salir");
		MenuAcciones02.setIcon(new ImageIcon(principal.class.getResource("/imagenes/Exit.png")));
		MenuAcciones02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				confirmarCierre();

			}
		});
		MenuDeAcciones.add(MenuAcciones02);
		
		JMenu MenuOperaciones = new JMenu("Operaciones");
		barraDelMenu.add(MenuOperaciones);
		
		JMenu MenuOperacionesRegistrarUsuarios = new JMenu("Registrar Usuarios");
		MenuOperaciones.add(MenuOperacionesRegistrarUsuarios);
		MenuOperacionesRegistrarUsuarios.setIcon(new ImageIcon(principal.class.getResource("/imagenes/registro.png")));
		
		JMenuItem MenuOperacionesRegistrarUsuariosEstudiantes = new JMenuItem("Estudiantes");
		MenuOperacionesRegistrarUsuariosEstudiantes.setIcon(new ImageIcon(principal.class.getResource("/imagenes/estudiant.png")));
		MenuOperacionesRegistrarUsuariosEstudiantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VenatanaEstudiante Estudiante = new VenatanaEstudiante();
				Estudiante.setVisible(true);
			}
		});
		MenuOperacionesRegistrarUsuarios.add(MenuOperacionesRegistrarUsuariosEstudiantes);
		
		JMenuItem MenuOperacionesRegistrarUsuariosProfesores = new JMenuItem("Profesores");
		MenuOperacionesRegistrarUsuariosProfesores.setIcon(new ImageIcon(principal.class.getResource("/imagenes/profesores.png")));
		MenuOperacionesRegistrarUsuariosProfesores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaProfesor Profesor = new VentanaProfesor();
				Profesor.setVisible(true);
			}
		});
		MenuOperacionesRegistrarUsuarios.add(MenuOperacionesRegistrarUsuariosProfesores);
		
		JMenuItem MenuOperacionesRegistrarUsuariosEmpleados = new JMenuItem("Empleados");
		MenuOperacionesRegistrarUsuariosEmpleados.setIcon(new ImageIcon(principal.class.getResource("/imagenes/empleado.png")));
		MenuOperacionesRegistrarUsuariosEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaEmpleado Empleado = null;
				try {
					Empleado = new VentanaEmpleado();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				Empleado.setVisible(true);
				
			}
		});
		MenuOperacionesRegistrarUsuarios.add(MenuOperacionesRegistrarUsuariosEmpleados);
		
		JMenu MenuOperacionesRegistrarRegistrarMaterial = new JMenu("Registrar Material");
		MenuOperacionesRegistrarRegistrarMaterial.setIcon(new ImageIcon(principal.class.getResource("/imagenes/registMaterial.png")));
		MenuOperaciones.add(MenuOperacionesRegistrarRegistrarMaterial);
		
		JMenuItem MenuOperacionesRegistrarUsuariosRegistrarMaterialLibros = new JMenuItem("Libros");
		MenuOperacionesRegistrarUsuariosRegistrarMaterialLibros.setIcon(new ImageIcon(principal.class.getResource("/imagenes/libros.png")));
		MenuOperacionesRegistrarUsuariosRegistrarMaterialLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaRegistrarLibros registrarlibros;
				try {
					registrarlibros = new VentanaRegistrarLibros();
					registrarlibros.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		MenuOperacionesRegistrarRegistrarMaterial.add(MenuOperacionesRegistrarUsuariosRegistrarMaterialLibros);
		
		JMenu MenuOperacionesRegistrarRegistrarMaterialAudioVisual = new JMenu("Audio Visual");
		MenuOperacionesRegistrarRegistrarMaterialAudioVisual.setIcon(new ImageIcon(principal.class.getResource("/imagenes/video.png")));
		MenuOperacionesRegistrarRegistrarMaterial.add(MenuOperacionesRegistrarRegistrarMaterialAudioVisual);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Imagenes y Otros");
		mntmNewMenuItem.setIcon(new ImageIcon(principal.class.getResource("/imagenes/photos.png")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaImagenesYotros imagenesyotros = new VentanaImagenesYotros();
				imagenesyotros.setVisible(true);
			}
		});
		MenuOperacionesRegistrarRegistrarMaterialAudioVisual.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Audio & Video");
		mntmNewMenuItem_4.setIcon(new ImageIcon(principal.class.getResource("/imagenes/videos.png")));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaAudioVideo fmAV = new VentanaAudioVideo();
				fmAV.setVisible(true);
				
			}
		});
		MenuOperacionesRegistrarRegistrarMaterialAudioVisual.add(mntmNewMenuItem_4);
		
		JSeparator separator = new JSeparator();
		MenuOperaciones.add(separator);

		JMenuItem mnModificarInventario = new JMenuItem("Modificar Inventario");
		mnModificarInventario.setIcon(new ImageIcon(principal.class.getResource("/imagenes/modificar.png")));
		mnModificarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ventanaModificarElInventario nuevoModificar = new ventanaModificarElInventario(principal.this);
				nuevoModificar.setVisible(true);
			}
		});	
		MenuOperaciones.add(mnModificarInventario);
		
		JSeparator separator_1 = new JSeparator();
		MenuOperaciones.add(separator_1);

		JMenuItem mnPrestamo = new JMenuItem("Prestamos de Materiales");
		mnPrestamo.setIcon(new ImageIcon(principal.class.getResource("/imagenes/materiales.png")));
		mnPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaPrestamosDeMateriales nuevoPrestamo = new VentanaPrestamosDeMateriales(principal.this);
				nuevoPrestamo.setVisible(true);
			}
		});
		MenuOperaciones.add(mnPrestamo);
		
		
		JMenuItem mnRecibo = new JMenuItem("Recibo de Materiales");
		mnRecibo.setIcon(new ImageIcon(principal.class.getResource("/imagenes/resibo.png")));
		mnRecibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaRealizarReciboDeMateriales nuevoRecibo = new VentanaRealizarReciboDeMateriales(principal.this);
				nuevoRecibo.setVisible(true);
			}
		});	
		MenuOperaciones.add(mnRecibo);
		
		JSeparator separator_3 = new JSeparator();
		MenuOperaciones.add(separator_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar Usuario");
		mntmNewMenuItem_8.setIcon(new ImageIcon(principal.class.getResource("/imagenes/registrarUsuario.png")));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				venatanaDeBibliotecario fmBib = new venatanaDeBibliotecario();
				fmBib.setVisible(true);
			}
		});
		MenuOperaciones.add(mntmNewMenuItem_8);
		
		JSeparator separator_2 = new JSeparator();
		MenuOperaciones.add(separator_2);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Solicitud de Material");
		mntmNewMenuItem_9.setIcon(new ImageIcon(principal.class.getResource("/imagenes/solicitud.png")));
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaDeSolicitudDeMaterial fmSolicitudM = new VentanaDeSolicitudDeMaterial();
				fmSolicitudM.setVisible(true);
			}
		});
		MenuOperaciones.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu_2 = new JMenu("Consultas");
		barraDelMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Usuarios");
		mntmNewMenuItem_5.setIcon(new ImageIcon(principal.class.getResource("/imagenes/empleado.png")));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaPrestamosDeMateriales n =new VentanaPrestamosDeMateriales(principal.this);
				BuscarUsuario nuevo = new BuscarUsuario(n);
				nuevo.setVisible(true);
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JSeparator separator_4 = new JSeparator();
		mnNewMenu_2.add(separator_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Materiales");
		mntmNewMenuItem_6.setIcon(new ImageIcon(principal.class.getResource("/imagenes/materiales.png")));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaModificarElInventario n =new ventanaModificarElInventario(principal.this);
				BuscarMaterialPrincipal nuevoMaterial = new BuscarMaterialPrincipal(n, 0);
				nuevoMaterial.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Inventario");
		mntmNewMenuItem_7.setIcon(new ImageIcon(principal.class.getResource("/imagenes/inventario.png")));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 ventanaModificarElInventario n =new ventanaModificarElInventario(principal.this);
				 BuscarMaterialPrincipal nuevoMaterial = new BuscarMaterialPrincipal(n, 2);
				 nuevoMaterial.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JSeparator separator_5 = new JSeparator();
		mnNewMenu_2.add(separator_5);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Prestamos");
		mntmNewMenuItem_10.setIcon(new ImageIcon(principal.class.getResource("/imagenes/prestamo.png")));
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaPrestamosDeMateriales n =new VentanaPrestamosDeMateriales(principal.this);
				BuscarPrestamo nuevo = new BuscarPrestamo(n);
				nuevo.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Recibos");
		mntmNewMenuItem_11.setIcon(new ImageIcon(principal.class.getResource("/imagenes/resibo.png")));
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaRealizarReciboDeMateriales n =new VentanaRealizarReciboDeMateriales(principal.this);
				BuscarRecibo nuevo = new BuscarRecibo(n);
				nuevo.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_11);
		
		JSeparator separator_6 = new JSeparator();
		mnNewMenu_2.add(separator_6);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Solicitudes");
		mntmNewMenuItem_12.setIcon(new ImageIcon(principal.class.getResource("/imagenes/solicitud.png")));
		mnNewMenu_2.add(mntmNewMenuItem_12);

	
	}

	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
    //instancia para acceder a un metodo
	public static principal obtenerInstancia() {
		if(instancia == null)
		{
			instancia = new principal();
		}
		return instancia;
		
	}
//metodo de confirmar el cierre de una ventana
	public void confirmarCierre() {
		int opcion = JOptionPane.showConfirmDialog(principal.this, "Desea cerrar la aplicacion?" ,
							"Confirmacion", JOptionPane.YES_NO_OPTION);
		
		if(opcion == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}
	}
}
