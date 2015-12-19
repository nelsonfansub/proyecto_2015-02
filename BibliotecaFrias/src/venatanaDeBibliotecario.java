import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Date;
import javax.swing.JComboBox;
import Clases.Conexion;
import Clases.Limpiar;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.DefaultComboBoxModel;
public class venatanaDeBibliotecario extends JDialog {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtFechaRegistro;
	private JTextField txtIdBibliotecario;
	private JTextField txtContrasenia;
	private JTextField txtPrivilegios;
	private JButton btnGuardarBibliotecario;
	private JButton btnActualizarBibliotecario;
	private JButton btnCancelarBibliotecario;
	private JButton btnBuscarBibliotecario;
	private JTextField txtTipoUsuario;
	private JComboBox CboxSexo;
	private JComboBox CboxAnio;
	private JComboBox cBoxMes;
	private JComboBox cBoxDia;
	
	String idPersona;
	String nombre;
	String apellido;
	String sexo;
	String fechaNacimiento;
	String cedula;
	String fechaRegistro;
	String tipoUsuario = "Bibliotecario";
	String idBibliotecario;
	String contrasenia;
	String privilegios;

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				venatanaDeBibliotecario frame = new venatanaDeBibliotecario();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}

public venatanaDeBibliotecario() {
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 464, 436);
	setTitle("Bibliotecario");
	contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblNombre = new JLabel("* Nombre: ");
	lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNombre.setBounds(46, 60, 92, 14);
	contentPane.add(lblNombre);
	
	JLabel lblApellido = new JLabel("* Apellido:");
	lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
	lblApellido.setBounds(46, 92, 92, 14);
	contentPane.add(lblApellido);
	
	JLabel lblSexo = new JLabel("* Sexo:");
	lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
	lblSexo.setBounds(46, 125, 92, 14);
	contentPane.add(lblSexo);
	
	JLabel lblFechaDeNacimiento = new JLabel("Fecha De Nacimiento:");
	lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFechaDeNacimiento.setBounds(12, 159, 126, 14);
	contentPane.add(lblFechaDeNacimiento);
	
	JLabel lblCedula = new JLabel("Cedula:");
	lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCedula.setBounds(46, 187, 92, 14);
	contentPane.add(lblCedula);
	
	JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro:");
	lblFechaDeRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
	lblFechaDeRegistro.setBounds(14, 223, 124, 14);
	contentPane.add(lblFechaDeRegistro);
	
	JLabel lblIdUsuarios = new JLabel("Id Bibliotecario:");
	lblIdUsuarios.setHorizontalAlignment(SwingConstants.RIGHT);
	lblIdUsuarios.setBounds(33, 32, 103, 14);
	contentPane.add(lblIdUsuarios);
	
	JLabel lblContrasea = new JLabel("Contrasena:");
	lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
	lblContrasea.setBounds(35, 283, 103, 23);
	contentPane.add(lblContrasea);
	
	JLabel lblPrivilegios = new JLabel("Privilegios:");
	lblPrivilegios.setHorizontalAlignment(SwingConstants.RIGHT);
	lblPrivilegios.setBounds(35, 318, 103, 14);
	contentPane.add(lblPrivilegios);
	
	txtNombre = new JTextField();
	txtNombre.setBounds(148, 60, 154, 20);
	contentPane.add(txtNombre);
	txtNombre.setColumns(10);
	
	txtApellido = new JTextField();
	txtApellido.setColumns(10);
	txtApellido.setBounds(148, 92, 154, 20);
	contentPane.add(txtApellido);
	
	txtCedula = new JTextField();
	txtCedula.setColumns(10);
	txtCedula.setBounds(148, 187, 154, 20);
	contentPane.add(txtCedula);
	
	txtFechaRegistro = new JTextField();
	txtFechaRegistro.setColumns(10);
	txtFechaRegistro.setBounds(148, 220, 154, 20);
	contentPane.add(txtFechaRegistro);
	
	
	Date fechaActual = new Date();
	DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.ENGLISH);
	txtFechaRegistro.setText(formatoFecha.format(fechaActual));
	
	txtIdBibliotecario = new JTextField();
	txtIdBibliotecario.setEditable(false);
	txtIdBibliotecario.setColumns(10);
	txtIdBibliotecario.setBounds(146, 29, 93, 20);
	contentPane.add(txtIdBibliotecario);
	
	CboxSexo = new JComboBox();
	CboxSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
	CboxSexo.setBounds(148, 120, 154, 25);
	contentPane.add(CboxSexo);
	
			
	txtContrasenia = new JTextField();
	txtContrasenia.setColumns(10);
	txtContrasenia.setBounds(148, 283, 154, 20);
	contentPane.add(txtContrasenia);
	
	txtPrivilegios = new JTextField();
	txtPrivilegios.setColumns(10);
	txtPrivilegios.setBounds(148, 315, 154, 20);
	contentPane.add(txtPrivilegios);
	
	btnGuardarBibliotecario = new JButton("Guardar");
	btnGuardarBibliotecario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			asignarValores();
			
			if(!txtNombre.getText().equals("") && !txtApellido.getText().equals(""))
			{
				int idPersonaGenerada = guardarPersona();
				
				guardarBibliotecario(idPersonaGenerada, contrasenia, privilegios);
				
			}
				else
					JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, "campos obligatorios: *");
		}
	});
	btnGuardarBibliotecario.setIcon(new ImageIcon(venatanaDeBibliotecario.class.getResource("/imagenes/saveHS.png")));
	btnGuardarBibliotecario.setBounds(315, 26, 111, 28);
	contentPane.add(btnGuardarBibliotecario);
	
	btnActualizarBibliotecario = new JButton("Actualizar");
	btnActualizarBibliotecario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				
			asignarValores();
			
			if(!txtIdBibliotecario.getText().equals(""))
			{
				int idPersonaGenerada = Integer.parseInt(idBibliotecario);
				
				ActualizarPersona(idPersona, nombre, apellido, sexo, fechaNacimiento, cedula, fechaRegistro, tipoUsuario);
			
				actualizarBibliotecario(idPersonaGenerada);
			}
			else
				JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, "Primero debes buscar para actualizar!");
			   
		}
	});
	btnActualizarBibliotecario.setIcon(new ImageIcon(venatanaDeBibliotecario.class.getResource("/imagenes/NewDocumentHS.png")));
	btnActualizarBibliotecario.setBounds(315, 61, 111, 28);
	contentPane.add(btnActualizarBibliotecario);
	
	btnCancelarBibliotecario = new JButton("Cancelar");
	btnCancelarBibliotecario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			 Limpiar.limpiarCampos(venatanaDeBibliotecario.this.getContentPane());
			 txtTipoUsuario.setText(tipoUsuario);
		}
	});
	
	btnCancelarBibliotecario.setIcon(new ImageIcon(venatanaDeBibliotecario.class.getResource("/imagenes/DeleteHS.png")));
	btnCancelarBibliotecario.setBounds(316, 103, 110, 28);
	contentPane.add(btnCancelarBibliotecario);
	
	btnBuscarBibliotecario = new JButton("");
	btnBuscarBibliotecario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			BuscarUsuario buscar = new BuscarUsuario(venatanaDeBibliotecario.this);
			buscar.setVisible(true);
		}
	});
	btnBuscarBibliotecario.setIcon(new ImageIcon(venatanaDeBibliotecario.class.getResource("/imagenes/Find.png")));
	btnBuscarBibliotecario.setBounds(242, 26, 41, 23);
	contentPane.add(btnBuscarBibliotecario);
	
	JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
	lblTipoDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTipoDeUsuario.setBounds(12, 249, 124, 31);
	contentPane.add(lblTipoDeUsuario);
	
	txtTipoUsuario = new JTextField();
	txtTipoUsuario.setText("Bibliotecario");
	txtTipoUsuario.setEditable(false);
	txtTipoUsuario.setColumns(10);
	txtTipoUsuario.setBounds(148, 252, 154, 20);
	contentPane.add(txtTipoUsuario);
	
	cBoxDia = new JComboBox();
	cBoxDia.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
	cBoxDia.setBounds(148, 156, 44, 20);
	contentPane.add(cBoxDia);
	
	cBoxMes = new JComboBox();
	cBoxMes.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
	cBoxMes.setBounds(196, 156, 47, 20);
	contentPane.add(cBoxMes);
	
	CboxAnio = new JComboBox();
	CboxAnio.setModel(new DefaultComboBoxModel(new String[] {"1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014","2015"}));
	CboxAnio.setToolTipText("Anio\r\n");
	CboxAnio.setBounds(247, 156, 55, 20);
	contentPane.add(CboxAnio);
	setLocationRelativeTo(null);
}


public void asignarValores()
{
					
	idPersona = txtIdBibliotecario.getText();
	nombre = txtNombre.getText();
	apellido = txtApellido.getText();
	
	sexo = CboxSexo.getSelectedItem().toString();
	
	cedula = txtCedula.getText();
	fechaRegistro = txtFechaRegistro.getText();
	tipoUsuario = "Bibliotecario";
	
	idBibliotecario = txtIdBibliotecario.getText();
	contrasenia = txtContrasenia.getText();
	privilegios = txtPrivilegios.getText();
	
	
	fechaNacimiento = CboxAnio.getSelectedItem().toString();
		
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");	
	Calendar calendar = new GregorianCalendar ();

	fechaRegistro = sdf.format(calendar.getTime());

	String anio = CboxAnio.getSelectedItem().toString();
	String mes = cBoxMes.getSelectedItem().toString();
	String dia = cBoxDia.getSelectedItem().toString();

	fechaNacimiento = ""+dia+"/" + mes+"/" + anio+ "";
}


public int guardarPersona()
{	
			
	try {
		//Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca", "nelson", "12345");
		Connection conexion = Conexion.Conectar();
		int idPersonaGenerada = 0;
		String sql = " insert into persona (nombre, apellido, sexo, fechaNacimiento, cedula, fechaRegistro, tipoUsuario)"
		        + " values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sql);
		sentencia.setString(1, nombre);
		sentencia.setString(2, apellido);
		sentencia.setString(3, sexo);
		sentencia.setString(4, fechaNacimiento);
		sentencia.setString(5, cedula);
		sentencia.setString(6, fechaRegistro);
		sentencia.setString(7, tipoUsuario);
		
		
		sentencia.execute();

		JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, "Persona Guardada");
				
		Statement st;
		ResultSet rs = null;										
		st = conexion.createStatement();
		
		rs = st.executeQuery("select MAX(idPersona) from persona");
	
		while(rs.next())
		{
			idPersonaGenerada = rs.getInt(1);
		}
		
		JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, "IdPersonaGenerada");
		 conexion.close();
	
		return idPersonaGenerada;
		
	} catch (SQLException e) {
	
		e.printStackTrace();
		String cadena = e.getMessage();			
		JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, cadena);
		return 0;
	}
	
}

public void guardarBibliotecario(int idBibliotecarios, String contrasenia, String privilegios)
{
	try {
		Connection conexion = Conexion.Conectar();
		
		String sql = " insert into bibliotecarios (idBibliotecarios, clave, privilegios)"
		        + " values (?, ?, ?)";
		
		PreparedStatement sentencia = (PreparedStatement) (conexion.prepareStatement(sql));
		
		sentencia.setInt(1, idBibliotecarios);
		sentencia.setString(2, contrasenia);
		sentencia.setString(3, privilegios);
		
		
		 sentencia.execute();
		 JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, "Bibliotecario Guardado");  
	     conexion.close();
		
				
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		String error = e.getMessage();
		JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, error); 
	}
	
	
}


	
public int ActualizarPersona(String idPersona, String nombre, String apellido, String sexo, String fechaNacimiento, String cedula, String fechaRegistro, String tipoUsuario)
{	
			
	try {
		
		Connection conexion = Conexion.Conectar();
		
		String actuaclizacion = "update persona set nombre = ?, apellido = ?, sexo = ?, fechaNacimiento = ?,"
				+ " cedula = ?, fechaRegistro = ?, tipoUsuario = ? where idPersona = ?";
		
		
		PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(actuaclizacion);
		sentencia.setString(1, nombre);
		sentencia.setString(2, apellido);
		sentencia.setString(3, sexo);
		sentencia.setString(4, fechaNacimiento);
		sentencia.setString(5, cedula);
		sentencia.setString(6, fechaRegistro);
		sentencia.setString(7, tipoUsuario);
		sentencia.setString(8, idPersona);	
		
					
		sentencia.executeUpdate();
		JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, "Realizado Con exito!");
		
	    conexion.close();
		
	    Limpiar.limpiarCampos(venatanaDeBibliotecario.this.getContentPane());
	    txtTipoUsuario.setText(tipoUsuario);
	  
		return 0;
		
	} catch (SQLException e) {

		e.printStackTrace();
		String cadena = e.getMessage();			
		JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, cadena);
		return 0;
	}
	
}


public void actualizarBibliotecario(int idPersonaGenerada)
{
	
try {		
	Connection conexion = Conexion.Conectar();
		String actualizar = "update bibliotecarios set clave = ?, privilegios = ? where idBibliotecarios = ?";
			
		PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(actualizar);
		sentencia.setString(1, contrasenia);
		sentencia.setString(2, privilegios);
		sentencia.setInt(3, idPersonaGenerada);	
							
		sentencia.executeUpdate();
		JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, "Realizado Con exito!");
		
	    conexion.close();
		
	    Limpiar.limpiarCampos(venatanaDeBibliotecario.this.getContentPane());
	    txtTipoUsuario.setText(tipoUsuario);
	 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		String cadena = e.getMessage();			
		JOptionPane.showMessageDialog(venatanaDeBibliotecario.this, cadena);
		
	}
	
	
}

public void cargarDatosUsuario(int idPersonaTomado)
{
	
	String id = ""+idPersonaTomado;
	String fecha;
	txtIdBibliotecario.setText(id);
	
	try {
		
		Connection conexion = Conexion.Conectar();
		
		Statement sentencia = conexion.createStatement();
		String sql = String.format("SELECT  persona.nombre, persona.apellido, persona.sexo, persona.fechaNacimiento, "
				+ "persona.cedula, persona.fechaRegistro, persona.tipoUsuario, "
				+ "bibliotecarios.clave, bibliotecarios.privilegios "
				+ "FROM persona, bibliotecarios WHERE persona.idpersona = bibliotecarios.idBibliotecarios "
				+ "AND  idpersona = "+ id);
		
		ResultSet resultados = sentencia.executeQuery(sql);
		
		while(resultados.next())
		{			
						
			txtNombre.setText(resultados.getString(1));						
			txtApellido.setText(resultados.getObject(2).toString());						
			
			CboxSexo.setSelectedItem(resultados.getObject(3));
			fecha = resultados.getObject(4).toString();
			txtCedula.setText(resultados.getString(5));
			
			txtFechaRegistro.setText(resultados.getString(6));
			txtTipoUsuario.setText(resultados.getString(7));
			txtContrasenia.setText(resultados.getString(8));
			txtPrivilegios.setText(resultados.getString(9));
			
			if(fecha.length()!=0)
			 {
			 CboxAnio.setSelectedItem(fecha.subSequence(6,10));
			 cBoxMes.setSelectedItem(fecha.subSequence(3,5));
			 cBoxDia.setSelectedItem(fecha.subSequence(0,2));
			 }
		}
			
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(this, e.getMessage());
	}
	
	
	
}
}
