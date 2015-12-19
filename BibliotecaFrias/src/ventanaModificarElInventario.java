import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import Clases.Conexion;
import Clases.Inventario;
import Clases.Limpiar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
public class ventanaModificarElInventario extends JDialog {

	private JPanel contentPane;
	private static JTextField txtIDMaterial;
	private JLabel lblDescripcion;
	private static JTextField txtDescripcion;
	private JLabel lblTipoMaterial;
	private static JTextField txtTipoMaterial;
	private JLabel lblNewLabel_1;
	private static JTextField txtClasificacionTematica;
	private JLabel lblCantidad;
	private JTextField txtCantidadAumentar;
	private JLabel lblNewLabel_2;
	private JTextField txtIDInventarioDisminuir;
	private JButton btnBuscarInventario;
	private JButton btnAgregarMateriales;
	private JButton btnDisminuirMaterial;
	public static int cantidadAgregar;
	public static int idInventarioGenerados[] = new int[20];
	static JFrame ventanaPadre;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaModificarElInventario frame = new ventanaModificarElInventario(ventanaPadre);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ventanaModificarElInventario(final JFrame ventanaPadre) {
		
		super(ventanaPadre);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaModificarElInventario.class.getResource("/imagenes/Book.png")));
		setTitle("Modificar Invetario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 244);
		contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Codigo Material");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(22, 11, 109, 22);
		contentPane.add(lblNewLabel);
		
		txtIDMaterial = new JTextField();
		txtIDMaterial.setEditable(false);
		txtIDMaterial.setColumns(10);
		txtIDMaterial.setBounds(133, 13, 77, 20);
		contentPane.add(txtIDMaterial);
		
		JButton btnBucarMaterial = new JButton("");
		btnBucarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BuscarMaterialPrincipal nuevoBuscarMaterial = new BuscarMaterialPrincipal(ventanaModificarElInventario.this, 0);
				nuevoBuscarMaterial.setVisible(true);
			}
		});
		btnBucarMaterial.setIcon(new ImageIcon(ventanaModificarElInventario.class.getResource("/imagenes/Find.png")));
		btnBucarMaterial.setBounds(220, 11, 30, 26);
		contentPane.add(btnBucarMaterial);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(43, 44, 88, 22);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEditable(false);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(133, 46, 440, 20);
		contentPane.add(txtDescripcion);
		
		lblTipoMaterial = new JLabel("Tipo Material");
		lblTipoMaterial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoMaterial.setBounds(34, 77, 97, 22);
		contentPane.add(lblTipoMaterial);
		
		txtTipoMaterial = new JTextField();
		txtTipoMaterial.setEditable(false);
		txtTipoMaterial.setColumns(10);
		txtTipoMaterial.setBounds(133, 79, 117, 20);
		contentPane.add(txtTipoMaterial);
		
		lblNewLabel_1 = new JLabel("Clasificacion Tematica");
		lblNewLabel_1.setBounds(260, 82, 137, 14);
		contentPane.add(lblNewLabel_1);
		
		txtClasificacionTematica = new JTextField();
		txtClasificacionTematica.setEditable(false);
		txtClasificacionTematica.setBounds(396, 79, 177, 20);
		contentPane.add(txtClasificacionTematica);
		txtClasificacionTematica.setColumns(10);
		
		lblCantidad = new JLabel("Cantidad a Agregar");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(10, 105, 121, 22);
		contentPane.add(lblCantidad);
		
		txtCantidadAumentar = new JTextField();
		txtCantidadAumentar.setColumns(10);
		txtCantidadAumentar.setBounds(133, 105, 117, 20);
		contentPane.add(txtCantidadAumentar);

		
		lblNewLabel_2 = new JLabel("Material A Disminuir");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 138, 121, 22);
		contentPane.add(lblNewLabel_2);
		
		txtIDInventarioDisminuir = new JTextField();
		txtIDInventarioDisminuir.setColumns(10);
		txtIDInventarioDisminuir.setBounds(133, 136, 77, 20);
		contentPane.add(txtIDInventarioDisminuir);
		
		btnBuscarInventario = new JButton("");
		btnBuscarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarMaterialPrincipal nuevoBuscarMaterial = new BuscarMaterialPrincipal(ventanaModificarElInventario.this, 2);
				nuevoBuscarMaterial.setVisible(true);
			}
		});
		btnBuscarInventario.setIcon(new ImageIcon(ventanaModificarElInventario.class.getResource("/imagenes/Find.png")));
		btnBuscarInventario.setBounds(220, 136, 30, 26);
		contentPane.add(btnBuscarInventario);
		
		btnAgregarMateriales = new JButton("Agregar Materiales");
		btnAgregarMateriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			try {
				  cantidadAgregar = Integer.parseInt(txtCantidadAumentar.getText().toString());
				  Connection cn = Conexion.Conectar(); 
				  
				
				  for(int i =0; i < cantidadAgregar; i++)
				  {
					String sql = "INSERT INTO inventario(idMaterial, estado) VALUES (?,?)";
					PreparedStatement instruccion;
					instruccion = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					instruccion.setInt(1, Integer.parseInt(txtIDMaterial.getText().toString()));
					instruccion.setString(2, "En Estante");
					instruccion.execute(); 
					ResultSet clavesGeneradas = instruccion.getGeneratedKeys();
					while(clavesGeneradas.next())
					{
						idInventarioGenerados[i] = Integer.parseInt( clavesGeneradas.getObject(1).toString() );
					}	
						
						
						
				  }	
				} catch (SQLException e) {
					
					e.printStackTrace();
				}	
			  
			    asignarUbicacionFisca();
				VenatanaAgregarInventario nuevoAddInventario = new VenatanaAgregarInventario(ventanaModificarElInventario.this, cantidadAgregar);
				nuevoAddInventario.setVisible(true); 
				Limpiar cancelar = new Limpiar();
				cancelar.limpiarCampos( ventanaModificarElInventario.this.getContentPane() );
				
			}
		});
		btnAgregarMateriales.setIcon(new ImageIcon(ventanaModificarElInventario.class.getResource("/imagenes/Add.png")));
		btnAgregarMateriales.setBounds(257, 104, 177, 26);
		contentPane.add(btnAgregarMateriales);
		
		btnDisminuirMaterial = new JButton("Disminuir Material");
		btnDisminuirMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conexion = Conexion.Conectar();
				int idInventario = Integer.parseInt(txtIDInventarioDisminuir.getText().toString());
				try {	
						String sql = "UPDATE inventario SET estado = 'No Disponible' where idInventario = ?";
						PreparedStatement sentencia = conexion.prepareStatement(sql);	
						sentencia.setInt(1, idInventario);
						sentencia.executeUpdate();
				       
					} catch  (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
					}
				
				JOptionPane.showMessageDialog(rootPane, "Material Disminuido");
				txtIDInventarioDisminuir.setText(" ");
			}
		});
		btnDisminuirMaterial.setIcon(new ImageIcon(ventanaModificarElInventario.class.getResource("/imagenes/Remove.png")));
		btnDisminuirMaterial.setBounds(257, 137, 177, 26);
		contentPane.add(btnDisminuirMaterial);
	}

	public void cargarDatosMaterial(int idMaterialTomado) {
		 
		txtIDMaterial.setText(idMaterialTomado +"");
		
			
			try {
					
					Connection conexion = Conexion.Conectar(); 
					Statement sentencia = conexion.createStatement();
					String sql = "SELECT titulo, tipoMaterial, ClasificacionTematica FROM material where idMaterial =" + idMaterialTomado;
					ResultSet resultados = sentencia.executeQuery(sql);
					
					resultados.next() ;
						
					    
						String titulo = resultados.getObject(1).toString();
						String tipoMaterial = resultados.getObject(2).toString();
						String clasificacionTematica = resultados.getObject(3).toString();
						Inventario nuevoInventario = new Inventario(idMaterialTomado, titulo, tipoMaterial, clasificacionTematica);
						
						txtDescripcion.setText(nuevoInventario.getDescripcion());
						txtTipoMaterial.setText(nuevoInventario.getTipoMaterial());
						txtClasificacionTematica.setText(nuevoInventario.getClasificacionTematica());
						
						
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
		
	}
	
	//----------------Asignarle la ubicacion fisica al material con todos los datos--------------------//
	
		public static String asignarUbicacionFisca(){
			
			
			int idMaterial = Integer.parseInt(txtIDMaterial.getText().toString());
			String ubicacionFisica;
			String Clasificacion = txtClasificacionTematica.getText().toString();
			String titulo = txtDescripcion.getText().toString();
			String tipoMaterial = txtTipoMaterial.getText().toString();
			String nombreAutor ="'";
			
			try {
				
				Connection conexion = Conexion.Conectar(); 
				Statement sentencia = conexion.createStatement();
				String sql = "SELECT nombreAutor FROM autor where idMaterial =" + idMaterial;
				ResultSet resultados = sentencia.executeQuery(sql);
				resultados.next() ;
				nombreAutor = resultados.getObject(1).toString();
					
			} catch (SQLException e) {
				e.getMessage();
			}
			
			String subTitulo = titulo.substring(0,3);
			String subAutor = nombreAutor.substring(0,3);
			String subTipo = tipoMaterial.substring(0,1);
		
			ubicacionFisica = Clasificacion + "-"+ "-"+ subTitulo + subAutor +"-"+subTipo;
			
			return ubicacionFisica;
			
		}

		//Asignar el IDiNVENTARIO tomao AL TXT
		public void disminuirInventario(int idInventarioTomado) {
			
			txtIDInventarioDisminuir.setText(idInventarioTomado +"");
			
		}


}
