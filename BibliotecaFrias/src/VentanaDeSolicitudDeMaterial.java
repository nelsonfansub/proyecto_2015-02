import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import Clases.Conexion;
import Clases.Limpiar;
public class VentanaDeSolicitudDeMaterial extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdMaterialSolicitado;
	private JTextField txtNombre;
	private JTextField txtAutor;
	private JTextField txtEdiccion;
	private JTextField txtAnio;
	private JTextField txtCantidadSolicitud;
	private JComboBox comboBoxClasificacionTematica;
	private JComboBox comboBoxTipoMaterial;
	private JComboBox comboBoxtipoMaterial;
	private String idMaterialSolicitado;
	private String nombre;
	private String autor;
	private String ediccion;
	private String cantidadSolicitud;
	private String anio;
	private String clasificacionTematica;
	private String tipoMaterial;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDeSolicitudDeMaterial frame = new VentanaDeSolicitudDeMaterial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaDeSolicitudDeMaterial() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 520, 380);
		setTitle("Material Solicitado");
		contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("Id Material Solicitado:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 47, 142, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTipoDeMterial = new JLabel("Tipo de Material:");
		lblTipoDeMterial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeMterial.setBounds(10, 72, 142, 14);
		contentPane.add(lblTipoDeMterial);
		
		JLabel lblNombre = new JLabel("* Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 97, 142, 14);
		contentPane.add(lblNombre);
		
		JLabel lblAutor = new JLabel("* Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setBounds(10, 122, 142, 14);
		contentPane.add(lblAutor);
		
		JLabel lblEdiccion = new JLabel("* Ediccion:");
		lblEdiccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEdiccion.setBounds(10, 147, 142, 14);
		contentPane.add(lblEdiccion);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAo.setBounds(10, 172, 142, 14);
		contentPane.add(lblAo);
		
		JLabel lblClasificacionTematica = new JLabel("* Clasificacion Tematica:");
		lblClasificacionTematica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasificacionTematica.setBounds(10, 197, 142, 14);
		contentPane.add(lblClasificacionTematica);
		
		JLabel lblCantidadDeSolicitudes = new JLabel("* Cantidad de Solicitudes:");
		lblCantidadDeSolicitudes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidadDeSolicitudes.setBounds(0, 222, 152, 14);
		contentPane.add(lblCantidadDeSolicitudes);
		
		txtIdMaterialSolicitado = new JTextField();
		txtIdMaterialSolicitado.setEditable(false);
		txtIdMaterialSolicitado.setBounds(162, 44, 111, 20);
		contentPane.add(txtIdMaterialSolicitado);
		txtIdMaterialSolicitado.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(162, 94, 171, 20);
		contentPane.add(txtNombre);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		txtAutor.setBounds(162, 119, 171, 20);
		contentPane.add(txtAutor);
		
		txtEdiccion = new JTextField();
		txtEdiccion.setColumns(10);
		txtEdiccion.setBounds(162, 144, 171, 20);
		contentPane.add(txtEdiccion);
		
		txtAnio = new JTextField();
		txtAnio.setColumns(10);
		txtAnio.setBounds(162, 169, 171, 20);
		contentPane.add(txtAnio);
		
		txtCantidadSolicitud = new JTextField();
		txtCantidadSolicitud.setColumns(10);
		txtCantidadSolicitud.setBounds(162, 219, 171, 20);
		contentPane.add(txtCantidadSolicitud);
		
		JButton btnGuardarMaterial = new JButton("Guardar");
		btnGuardarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				asignarValores();
				
				if(!txtNombre.getText().equals("") && !txtAutor.getText().equals("") && !txtEdiccion.getText().equals("") && !txtCantidadSolicitud.getText().equals(""))
				{
					guardarSolicitudMaterial();				
					
				}
				else
					JOptionPane.showMessageDialog(VentanaDeSolicitudDeMaterial.this, "No puede dejar campos obligatorios vacios!");
				
				
			}
		});
		btnGuardarMaterial.setIcon(new ImageIcon(VentanaDeSolicitudDeMaterial.class.getResource("/imagenes/saveHS.png")));
		btnGuardarMaterial.setBounds(356, 47, 111, 28);
		contentPane.add(btnGuardarMaterial);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				asignarValores();
				
				if(!txtIdMaterialSolicitado.getText().equals(""))
				{
					int idPersonaGenerada = Integer.parseInt(idMaterialSolicitado);
					ActualizarMaterial(idPersonaGenerada);
				}
				else
					JOptionPane.showMessageDialog(VentanaDeSolicitudDeMaterial.this, "Primero debes buscar para actualizar!");
				   
			}
				
		});
		btnActualizar.setIcon(new ImageIcon(VentanaDeSolicitudDeMaterial.class.getResource("/imagenes/NewDocumentHS.png")));
		btnActualizar.setBounds(356, 82, 111, 28);
		contentPane.add(btnActualizar);
		
		JButton btnCancelarMaterial = new JButton("Cancelar");
		btnCancelarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancelarMaterial.setIcon(new ImageIcon(VentanaDeSolicitudDeMaterial.class.getResource("/imagenes/DeleteHS.png")));
		btnCancelarMaterial.setBounds(357, 124, 110, 28);
		contentPane.add(btnCancelarMaterial);
		
		JButton btnBuscarMaterialSolicitado = new JButton("");
		btnBuscarMaterialSolicitado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BuscarMaterialSolicitado fmBuscar = new BuscarMaterialSolicitado(VentanaDeSolicitudDeMaterial.this);
				fmBuscar.setVisible(true);
				
			}
		});
		btnBuscarMaterialSolicitado.setIcon(new ImageIcon(VentanaDeSolicitudDeMaterial.class.getResource("/imagenes/Find.png")));
		btnBuscarMaterialSolicitado.setBounds(285, 35, 48, 26);
		contentPane.add(btnBuscarMaterialSolicitado);
		comboBoxtipoMaterial = new JComboBox();
		comboBoxtipoMaterial.setModel(new DefaultComboBoxModel(new String[] {"Libro", "AudioVideo", "ImagenesOtros"}));
		comboBoxtipoMaterial.setBounds(162, 69, 171, 20);
		contentPane.add(comboBoxtipoMaterial);
		
		comboBoxClasificacionTematica = new JComboBox();
		comboBoxClasificacionTematica.setBounds(162, 194, 171, 20);
		llenarComboBox();
		contentPane.add(comboBoxClasificacionTematica);
		setLocationRelativeTo(null);
	}
	
	public void llenarComboBox()
	{
		
		try { 
							
			Connection conexion = Conexion.Conectar();		
			String sql = "SELECT  idClasificacionTematica, nombre FROM clasificacionTematica";
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			comboBoxClasificacionTematica.removeAllItems();
			while(rs.next())
			{
				comboBoxClasificacionTematica.addItem(rs.getString(2));
				
				
				//rs.getInt(1);
			}
			
			int id = 0;
			String seleccion = comboBoxClasificacionTematica.getSelectedItem().toString(); 
			while(rs.next())
			{
				if(seleccion == rs.getString(2))
					id = Integer.parseInt(rs.getString(1));
				 				
			}
			
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(VentanaDeSolicitudDeMaterial.this, e.getMessage());
		}
		
	}	
	public int VerIdComboBox()
	{
		int id = 0;
		try {
			
			Connection conexion = Conexion.Conectar(); 
			
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT  idClasificacionTematica, nombre FROM clasificacionTematica";
			
			ResultSet rs = sentencia.executeQuery(sql);

			
			String seleccion = comboBoxClasificacionTematica.getSelectedItem().toString(); 
			
			
			while(rs.next())
			{
				if(seleccion.equals(rs.getString(2)))
				{
					id = Integer.parseInt(rs.getString(1));
				
				}
			}
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			
		}
		
		return id;		
	}
	
	
	public void asignarValores()
	{
		
		idMaterialSolicitado = txtIdMaterialSolicitado.getText();
		nombre = txtNombre.getText();
		autor = txtAutor.getText();
		ediccion = txtEdiccion.getText();
		cantidadSolicitud = txtCantidadSolicitud.getText();
		anio = txtAnio.getText();
			
		tipoMaterial = comboBoxtipoMaterial.getSelectedItem().toString();
	
		clasificacionTematica = comboBoxClasificacionTematica.getSelectedItem().toString(); 
			                        
	}
	
	public void cargarDatosMaterial(String idTomado)
	{
		txtIdMaterialSolicitado.setText(idTomado);
		
		
		try {
			int id = Integer.parseInt(idTomado);
			Connection conexion = Conexion.Conectar();
			
			Statement sentencia = conexion.createStatement();
			String sql = String.format("SELECT tipoMaterial, nombre, autor, edicion, anio, clasificacionTematica, cantidadDeSolicitudes "
					+ "FROM materialSolicitado WHERE idMaterialSolicitado = "+ idTomado);
		
			
			ResultSet resultados = sentencia.executeQuery(sql);
			
			while(resultados.next())
			{				
				comboBoxtipoMaterial.setSelectedItem(resultados.getString(1));	
				txtNombre.setText(resultados.getString(2));						
				txtAutor.setText(resultados.getString(3));
				txtEdiccion.setText(resultados.getString(4));
				txtAnio.setText(resultados.getString(5));
				
				comboBoxClasificacionTematica.setSelectedItem(resultados.getString(6));	
				txtCantidadSolicitud.setText(resultados.getString(6));
			}
	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		}
	
	public void guardarSolicitudMaterial()
	{
		try {
			Connection conexion = Conexion.Conectar();
			
			int cantidad = Integer.parseInt(cantidadSolicitud);
			String sql = " insert into materialSolicitado (tipoMaterial, nombre, autor, edicion, anio, "
					+ "clasificacionTematica, cantidadDeSolicitudes)"
			        + " values (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, tipoMaterial);
			sentencia.setString(2, nombre);
			sentencia.setString(3, autor);
			sentencia.setString(4, ediccion);
			sentencia.setString(5, anio);
			sentencia.setString(6, clasificacionTematica);
			sentencia.setInt(7, cantidad);
			sentencia.execute();

			JOptionPane.showMessageDialog(VentanaDeSolicitudDeMaterial.this, "Tu solicitud ha sido Guardada!");
			
		    conexion.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			String cadena = e.getMessage();			
			JOptionPane.showMessageDialog(VentanaDeSolicitudDeMaterial.this, cadena);
			
		}
		
	}
		
	public void ActualizarMaterial(int id)
	{			
				
		try {		
			Connection conexion = Conexion.Conectar();
			
			String sql2 = "update materialSolicitado set tipoMaterial = ?, nombre = ?, autor = ?, edicion = ?,"
					+ " anio = ?, clasificacionTematica = ?, cantidadDeSolicitudes = ? where idMaterialSolicitado = ?";
				
			PreparedStatement sentencia =  conexion.prepareStatement(sql2);
			sentencia.setString(1, tipoMaterial);
			sentencia.setString(2, nombre);
			sentencia.setString(3, autor);	
			sentencia.setString(4, ediccion);	
			sentencia.setString(5, anio);
			sentencia.setString(6, clasificacionTematica);
			sentencia.setString(7, cantidadSolicitud);
			sentencia.setInt(8, id);
			sentencia.executeUpdate();
			JOptionPane.showMessageDialog(VentanaDeSolicitudDeMaterial.this, "Material Actualizado!");
			
		    conexion.close();
			
		    Limpiar.limpiarCampos(VentanaDeSolicitudDeMaterial.this.getContentPane());
	
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			String cadena = e.getMessage();			
			JOptionPane.showMessageDialog(VentanaDeSolicitudDeMaterial.this, cadena);
			
		}
		
		
	}
}
