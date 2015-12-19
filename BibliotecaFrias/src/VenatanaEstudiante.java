import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;

import java.awt.Canvas;
import java.awt.SystemColor;

import javax.swing.JComboBox;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

import org.jdesktop.swingx.prompt.PromptSupport;

import Clases.Conexion;
import Clases.Limpiar;
import Clases.Utilidades;

public class VenatanaEstudiante extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JTextField textBuscar;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textInstitucion;
	private final ButtonGroup groupSexo = new ButtonGroup();
	private JTextField textNumero;
	private JTextField textCiudad;
	private JTextField textEmail;
	private JComboBox NivelAcademico;
	private JComboBox cboxDia;
	private JComboBox cboxMes;
	private JComboBox cboxAnio;
	private JComboBox cboxTanda;
	private JRadioButton radiobtnFemenino;
	private JRadioButton radiobtnMasculino;
	private JFormattedTextField txtTelefono=new JFormattedTextField();
	private JFormattedTextField txtCedula = new JFormattedTextField();
	protected String fechaRegistro;
	private JTextField texto;
	private JTextField txtCalle;
	String nombre = "";
	String apellido ="";
	String direccion ="";
	String telefono = "";
	String puesto = "";
	String cedula;
	String area = "";
	char sexo = 'M';
	String fechaNacimiento;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VenatanaEstudiante frame = new VenatanaEstudiante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VenatanaEstudiante() {
		setModal(true);
		setTitle("Registro de Estudiante");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VenatanaEstudiante.class.getResource("/Imagenes/Book.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		
		contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		MaskFormatter formatoTelefono = null;
		MaskFormatter formatoCedula = null;
		try{
			formatoTelefono = new MaskFormatter("###-###-####");
			formatoCedula = new MaskFormatter("###-#######-#");
		}
		catch(Exception e)
		{
			
		}
		
		formatoTelefono.setValueClass(String.class);
		formatoTelefono.setPlaceholderCharacter('_');  //se vera un espacio de la cantidad de caracteres en este caso los numeros del telefono
		DefaultFormatterFactory telefonoFormatterFactory = new
		          DefaultFormatterFactory(formatoTelefono);
		txtTelefono.setFormatterFactory(telefonoFormatterFactory);
		
		formatoCedula.setValueClass(String.class);
		formatoCedula.setPlaceholderCharacter('_');  
		DefaultFormatterFactory cedulaFormatterFactory = new
		          DefaultFormatterFactory(formatoCedula);
		txtCedula.setFormatterFactory(cedulaFormatterFactory);
		
		
		JLabel lblCdigo = new JLabel("Codigo ");
		lblCdigo.setBounds(10, 11, 46, 22);
		contentPane.add(lblCdigo);
		setLocationRelativeTo(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 51, 142, 14);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 76, 142, 14);
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(10, 101, 142, 14);
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblSexo);
		
		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		lblFecha.setBounds(10, 127, 142, 14);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFecha);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 151, 142, 14);
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(10, 176, 142, 14);
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblTelefono);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 201, 142, 14);
		lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCedula);
		
		JLabel lblNivelAcademico = new JLabel("Nivel Academico");
		lblNivelAcademico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNivelAcademico.setBounds(20, 227, 132, 14);
		contentPane.add(lblNivelAcademico);
		
		JLabel lblNewLabel = new JLabel("Institucion Academica");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 248, 142, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTanda = new JLabel("Tanda");
		lblTanda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTanda.setBounds(10, 274, 142, 14);
		contentPane.add(lblTanda);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(97, 296, 55, 16);
		contentPane.add(lblEmail);
	
		textBuscar = new JTextField();
		textBuscar.setEditable(false);
		textBuscar.setBounds(53, 14, 86, 20);
		contentPane.add(textBuscar);
		textBuscar.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(162, 45, 135, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		PromptSupport.setPrompt("Su Nombre Aqui ", textNombre);// sirve para poner un soporte de ejemplo para el texfiel
		
		textApellido = new JTextField();
		textApellido.setBounds(162, 72, 135, 20);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		PromptSupport.setPrompt("Su Apellido Aqui", textApellido);// sirve para poner un soporte de ejemplo para el texfiel
		
		textInstitucion = new JTextField();
		textInstitucion.setBounds(162, 245, 135, 20);
		contentPane.add(textInstitucion);
		textInstitucion.setColumns(10);
		PromptSupport.setPrompt("Institucion", textInstitucion);// sirve para poner un soporte de ejemplo para el texfiel
		
		textNumero = new JTextField();
		textNumero.setColumns(10);
		textNumero.setBounds(261, 148, 36, 20);
		contentPane.add(textNumero);
		PromptSupport.setPrompt("# de calle", textNumero);// sirve para poner un soporte de ejemplo para el texfiel
		
		textCiudad = new JTextField();
		textCiudad.setColumns(10);
		textCiudad.setBounds(309, 148, 113, 20);
		contentPane.add(textCiudad);
		PromptSupport.setPrompt("Ciudad", textCiudad);
		
		textEmail = new JTextField();
		textEmail.setBounds(162, 296, 260, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		PromptSupport.setPrompt("ejemplo de correo nelsonfansub@gmail.com", textEmail);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(162, 148, 89, 20);
		contentPane.add(txtCalle);
		txtCalle.setColumns(10);
		PromptSupport.setPrompt("Calle", txtCalle);

		txtTelefono.setBounds(162, 173, 135, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		PromptSupport.setPrompt("Telefono", txtTelefono);
		
		txtCedula.setBounds(162, 198, 135, 20);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);
		PromptSupport.setPrompt("Cedula", txtCedula);
		
		NivelAcademico = new JComboBox();
		NivelAcademico.setToolTipText("Seleccione el nivel academico de la lista\r\n");
		NivelAcademico.setModel(new DefaultComboBoxModel(new String[] {"Inicial", "Basica", "Media", "Universitario"}));
		NivelAcademico.setSelectedIndex(1);//el primer se seleciona por defecto
		NivelAcademico.setBounds(162, 219, 135, 20);
		contentPane.add(NivelAcademico);
		
		cboxDia = new JComboBox();
		cboxDia.setToolTipText("Dia\r\n");
		cboxDia.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cboxDia.setBounds(162, 124, 40, 20);
		contentPane.add(cboxDia);
		
		cboxMes = new JComboBox();
		cboxMes.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		cboxMes.setToolTipText("Mes\r\n");
		cboxMes.setBounds(200, 124, 46, 20);
		contentPane.add(cboxMes);
		
		cboxAnio = new JComboBox();
		cboxAnio.setModel(new DefaultComboBoxModel(new String[] {"1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014","2015","2016"}));
		cboxAnio.setToolTipText("Anio\r\n");
		cboxAnio.setBounds(241, 124, 56, 20);
		contentPane.add(cboxAnio);
		
		cboxTanda = new JComboBox();
		cboxTanda.setModel(new DefaultComboBoxModel(new String[] {"Manana", "Tarde", "Noche"}));
		cboxTanda.setBounds(162, 271, 135, 20);
		contentPane.add(cboxTanda);
		
		final JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnActualizar)//si alguno de los campos esta vacio
			{	if ((textNombre.getText().isEmpty() == true)
					|| ((textApellido.getText().isEmpty()==true)
					|| (txtCalle.getText().isEmpty()==true)
					|| (txtCalle.getText().isEmpty()==true)
					|| (textNumero.getText().isEmpty()==true)
					|| (textCiudad.getText().isEmpty()==true)
					|| (txtTelefono.getText().replaceAll("-| ","").isEmpty()==true)
					|| (Utilidades.isNumber(txtTelefono.getText().replaceAll("-| ",""))==false)
					|| (textInstitucion.getText().isEmpty()==true)
					|| (txtCedula.getText().isEmpty()==true)
					|| (Utilidades.isNumber(txtCedula.getText().replaceAll("-| ",""))==false)
					|| (textEmail.getText().isEmpty())==true))
					{
						
				if(Utilidades.isNumber(txtTelefono.getText().replaceAll("-| ",""))==false) 
				{
				JOptionPane.showMessageDialog(textApellido, "Solo se permiten números, guiones y espacios en telefono");
				}
				if (Utilidades.isNumber(txtCedula.getText().replaceAll("-| ",""))==false)
				{
					JOptionPane.showMessageDialog(txtCedula, "Solo se permiten números, guiones y espacios en cedula");
				}
			else
			{ 
				JOptionPane.showMessageDialog(textApellido, "Los datos están vacíos o incorrectos");
			}
				
			}
		
		else
		{
				char sexo;
				String ano = (String)cboxAnio.getSelectedItem();// convierte el combobox a string y se lo asgina a anio
				String mes = (String)cboxMes.getSelectedItem();// convierte el combobox a string y se lo asgina a mes
				String dia = (String)cboxDia.getSelectedItem();// convierte el combobox a string y se lo asgina a dia
				
				fechaNacimiento = ""+ano+" " + mes+" " + dia+ "";
				
				if (radiobtnFemenino.isSelected())//si esta seleccionado  el radio boton femenino
				{
					sexo = 'F';// Asigna F al sexo
				}
				else  
					sexo = 'M';// Asigna M al sexo
				
				Conexion con = new Conexion();
				con.Conectar();
				try {
					Statement comando = con.Conectar().createStatement();
				    comando.execute("UPDATE persona, email, estudiante, direccion, telefonos "
							+ "SET persona.nombre = '"+textNombre.getText()+"', "
							+ "persona.apellido = '"+textApellido.getText()+"', "
							+ "persona.sexo = '"+sexo+"', "
							+ "persona.fechaNacimiento = '"+fechaNacimiento+"', "
							+ "persona.cedula = '"+txtCedula.getText().replaceAll("-| ","")+"', "
							+ "email.email = '"+textEmail.getText()+"', "
							+ "direccion.calle = '"+txtCalle.getText()+"', "
							+ "direccion.numeroEdificio = '"+textNumero.getText()+"', "
							+ "direccion.ciudad ='"+textCiudad.getText()+"', "
							+ "telefonos.telefono = '"+txtTelefono.getText().replaceAll("-| ","")+"', "
							+ "estudiante.nivelAcademico ='"+NivelAcademico.getSelectedItem()+"', "
							+ "estudiante.institucionAcademica='"+textInstitucion.getText()+"', "
							+ "estudiante.tanda='"+cboxTanda.getSelectedItem()+"' "
							+ "WHERE persona.idPersona = estudiante.idEstudiante "
							+ "AND persona.idPersona = telefonos.idPersona "
							+ "AND persona.idPersona = email.idPersona "
							+ "AND persona.idPersona = direccion.idPersona "
							+ "AND persona.idPersona = "+textBuscar.getText()+"");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Limpiar.limpiarCampos(VenatanaEstudiante.this.getContentPane());
				JOptionPane.showMessageDialog(VenatanaEstudiante.this, "Operacion Realiazada exitosamente!");
				
		} 
		}
		}
	});
		btnActualizar.setIcon(new ImageIcon(VenatanaEstudiante.class.getResource("/Imagenes/NewDocumentHS.png")));
		btnActualizar.setBounds(309, 42, 115, 23);
		contentPane.add(btnActualizar);
		
		final JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener()
		{
			
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==btnGuardar)// si el evento es igual a guardar
			{	// si algun campo esta vacio
			    if ((textNombre.getText().isEmpty()==true)
						|| ((textApellido.getText().isEmpty()==true)
						|| (txtCalle.getText().isEmpty()==true)
						|| (txtCalle.getText().isEmpty()==true)
						|| (textNumero.getText().isEmpty()==true)
						|| (textCiudad.getText().isEmpty()==true)
						|| (txtTelefono.getText().isEmpty()==true)
						|| (Utilidades.isNumber(txtTelefono.getText().replaceAll("-| ",""))==false)
						|| (textInstitucion.getText().isEmpty()==true)
						|| (txtCedula.getText().isEmpty()==true)
						|| (Utilidades.isNumber(txtCedula.getText().replaceAll("-| ",""))==false)
						|| (textEmail.getText().isEmpty())==true))
						{
							
					if(Utilidades.isNumber(txtTelefono.getText().replaceAll("-| ",""))==false) 
					{
					JOptionPane.showMessageDialog(txtCedula, "Solo se permiten números, guiones y espacios en telefono");
					}
					if (Utilidades.isNumber(txtCedula.getText().replaceAll("-| ",""))==false)
					{
						JOptionPane.showMessageDialog(txtCedula, "Solo se permiten números, guiones y espacios en cedula");
					}
				else
				{ 
					JOptionPane.showMessageDialog(textApellido, "Los datos están vacíos o incorrectos.");
				}
					
				}
			
			else
			{
				if(radiobtnFemenino.isSelected())
					{
						sexo='F';// Asigna F al sexo
					}
					else 
					{
						sexo='M';// Asigna M al sexo
					}
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");	
					Calendar calendar = new GregorianCalendar ();
				
					fechaRegistro = sdf.format(calendar.getTime());
				
					String ano = (String)cboxAnio.getSelectedItem();
					String mes = (String)cboxMes.getSelectedItem();
					String dia = (String)cboxDia.getSelectedItem();
				
					fechaNacimiento = ""+dia+"/" + mes+"/" + ano+ "";
		
					//*******INSERCION A BASE DE DATOS***************//
				
					try {
						Conexion cn = new Conexion();
						cn.Conectar();
						Statement comando = cn.Conectar().createStatement();
						String tipoUsuario = "Estudiante";
						comando.execute(" insert into persona (nombre,apellido,sexo,fechaNacimiento,cedula,fechaRegistro,tipoUsuario)"
								+ " values ('"+textNombre.getText()+"','"
								+textApellido.getText()+"','"
								+sexo+"',"+ "'"
								+fechaNacimiento+"', '"
								+txtCedula.getText().replaceAll("-| ","")+"',"+ "'"
								+fechaRegistro+"', '"+tipoUsuario+"')");	
					
						ResultSet rs = comando.executeQuery("select idPersona from persona");
						int id = 0;
						while(rs.next())
						{
							id = rs.getInt(1);
						}
						
						comando.execute("insert into estudiante (idEstudiante, nivelAcademico, institucionAcademica, tanda)"
								+ " values ('"+id+"', '"+NivelAcademico.getSelectedItem()+"', "
								+ "'"+textInstitucion.getText()+"', '"+cboxTanda.getSelectedItem()+"')");
						
						comando.execute("insert into direccion (idPersona,calle,numeroEdificio,ciudad)"
								+ "values ('"+id+"', '"+txtCalle.getText()+"', '"+textNumero.getText()+"', '"+textCiudad.getText()+"')");
					
						comando.execute("insert into telefonos (idPersona,telefono)"
								+ "values ('"+id+"','"+txtTelefono.getText().replaceAll("-| ","")+"')");
					
						comando.execute("insert into email (idPersona,email)"
								+ "values ('"+id+"','"+textEmail.getText()+"')");
						
						
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Limpiar.limpiarCampos(VenatanaEstudiante.this.getContentPane());
					JOptionPane.showMessageDialog(VenatanaEstudiante.this, "Operacion Realiazada exitosamente!");

				}
			}
			}
			});
		btnGuardar.setIcon(new ImageIcon(VenatanaEstudiante.class.getResource("/Imagenes/saveHS.png")));
		btnGuardar.setBounds(309, 72, 115, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNombre.setText(null);
				textApellido.setText(null);
				txtCalle.setText(null);
				textNumero.setText(null);
				textCiudad.setText(null);
				txtTelefono.setText(null);
				textInstitucion.setText(null);
				txtCedula.setText(null);
				textEmail.setText(null);
				textBuscar.setText(null);
			}
		});
		btnCancelar.setIcon(new ImageIcon(VenatanaEstudiante.class.getResource("/Imagenes/DeleteHS.png")));
		btnCancelar.setBounds(309, 101, 115, 23);
		contentPane.add(btnCancelar);
		
		
		radiobtnFemenino = new JRadioButton("F");
		groupSexo.add(radiobtnFemenino);
		radiobtnFemenino.setOpaque(false);
		radiobtnFemenino.setBounds(166, 97, 31, 23);
		contentPane.add(radiobtnFemenino);
		
		radiobtnMasculino = new JRadioButton("M");
		groupSexo.add(radiobtnMasculino);
		radiobtnMasculino.setOpaque(false);
		radiobtnMasculino.setBounds(251, 97, 46, 23);
		contentPane.add(radiobtnMasculino);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(144, -32, 31, 26);
		contentPane.add(canvas);
			
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				BuscarUsuarios buscar = new BuscarUsuarios(VenatanaEstudiante.this);
				buscar.setVisible(true);
				
			}
		});
		
		btnBuscar.setIcon(new ImageIcon(VenatanaEstudiante.class.getResource("/Imagenes/Find.png")));
		btnBuscar.setBounds(144, 13, 31, 23);
		contentPane.add(btnBuscar);

	}

	final void cargarDatos (int id)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(id);
		String strI = sb.toString();
		ResultSet rs = null;
		String emp = null;
		
		Conexion con = new Conexion();
		con.Conectar();
		//para que los datos cogidos solo sean de Empleado
		try {
			Statement comando = con.Conectar().createStatement();
			rs = comando.executeQuery("select tipoUsuario from persona where idPersona="+strI);
			while(rs.next())
			{
				emp =rs.getString(1);
				
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
			textBuscar.setText(strI);
			try {
				/*Selecciona todos los campos*/
			Statement comando = con.Conectar().createStatement();
			 rs = comando.executeQuery("select persona.idPersona, estudiante.idEstudiante, "
		+ "persona.nombre, persona.apellido, persona.sexo, persona.cedula, persona.fechaNacimiento, "
		+ " estudiante.nivelAcademico, estudiante.institucionAcademica, estudiante.tanda, telefonos.telefono,"
		+ "direccion.calle, direccion.numeroEdificio, direccion.ciudad,"
		+ "email.email "
			 		+ "from persona, estudiante, telefonos, direccion, email "
			 		+ "where persona.idPersona = estudiante.idEstudiante "
			 		+ "AND persona.idPersona = telefonos.idPersona "
			 		+ "AND persona.idPersona = direccion.idPersona "
			 		+ "AND persona.idPersona = email.idPersona "
			 		+ "AND persona.idPersona="+strI);
			 
			 char sexo = 0;
			 String nivel = null;
			 String tanda = null;
			 
			 String fecha = "";
			 while(rs.next())
				{
				 	textNombre.setText(rs.getString(3));
				 	textApellido.setText(rs.getString(4));
				 	sexo = rs.getString(5).charAt(0);
					txtCedula.setText(rs.getString(6));
					fecha = rs.getString(7);
					nivel = rs.getString(8);
					textInstitucion.setText(rs.getString(9));
					tanda = rs.getString(10);
					txtTelefono.setText(rs.getString(11));
					txtCalle.setText(rs.getString(12));
					textNumero.setText(rs.getString(13));
					textCiudad.setText(rs.getString(14));
					textEmail.setText(rs.getString(15));
				}
			 NivelAcademico.setSelectedItem(nivel);
			 cboxTanda.setSelectedItem(tanda);
			 if(fecha.length()!=0)
			 {
			
			 cboxAnio.setSelectedItem(fecha.subSequence(6,10));
			 cboxMes.setSelectedItem(fecha.subSequence(3,5));
			 cboxDia.setSelectedItem(fecha.subSequence(0,2));
			 }
			 if (sexo == 'F')
			 {
				radiobtnFemenino.setSelected(true);
			 }
			 if (sexo == 'M')
			 {
				radiobtnMasculino.setSelected(true);
			 }
			 else
			 {
				 radiobtnFemenino.setSelected(false);
				 radiobtnMasculino.setSelected(false);
			 }
			 
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "se etrallo el programa");
		
		}
			
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
