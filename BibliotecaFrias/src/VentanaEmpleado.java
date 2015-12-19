import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import Clases.Conexion;
import Clases.Limpiar;
import Clases.Utilidades;

import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;

import org.jdesktop.swingx.prompt.PromptSupport;

public class VentanaEmpleado extends JDialog{

	private JPanel contentPane;
	private JTextField textBuscar;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCalle;
	private JFormattedTextField textTelefono = new JFormattedTextField();
	private JTextField textPuesto;
	private JFormattedTextField textCedula = new JFormattedTextField();
	private JTextField textArea;
	private JComboBox comboAno;
	private JComboBox comboMes;
	private JComboBox comboDia;
	private JButton btnGuardar;
	private final ButtonGroup groupSexo = new ButtonGroup();
	private Statement comando = null;
	private JTextField textNumero;
	private JTextField textCiudad;
	private JTextField textEmail;
	private int idPersona;
	JRadioButton rdbtnFemenino;
	JRadioButton rdbtnMasculino;
	String textValor;
	String fechaNacimiento;
	String fechaRegistro;
	Conexion cn = new Conexion();
	String tipoUsuario;
	char sexo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpleado frame = new VentanaEmpleado();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public VentanaEmpleado() throws FileNotFoundException {
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEmpleado.class.getResource("/Imagenes/Book.png")));
		setTitle("Registrar Empleado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 347);
		contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
		
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
		formatoTelefono.setPlaceholderCharacter('_');  
		DefaultFormatterFactory telefonoFormatterFactory = new
		          DefaultFormatterFactory(formatoTelefono);
		textTelefono.setFormatterFactory(telefonoFormatterFactory);
		
		formatoCedula.setValueClass(String.class);
		formatoCedula.setPlaceholderCharacter('_');  
		DefaultFormatterFactory cedulaFormatterFactory = new
		          DefaultFormatterFactory(formatoCedula);
		textCedula.setFormatterFactory(cedulaFormatterFactory);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 11, 46, 22);
		contentPane.add(lblCodigo);
		
		textBuscar = new JTextField();
		textBuscar.setEditable(false);
		textBuscar.setBounds(66, 12, 86, 20);
		contentPane.add(textBuscar);
		textBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(VentanaEmpleado.class.getResource("/Imagenes/Find.png")));
		btnBuscar.setBounds(162, 11, 33, 23);
		contentPane.add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				BuscarUsuarios bus = new BuscarUsuarios(VentanaEmpleado.this);
				bus.setVisible(true);
			}
		});
		
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 51, 142, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 76, 142, 14);
		contentPane.add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setBounds(10, 101, 142, 14);
		contentPane.add(lblSexo);
		
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeNacimiento.setBounds(10, 126, 142, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuesto.setBounds(10, 202, 142, 14);
		contentPane.add(lblPuesto);
		
		JLabel lblNewLabel = new JLabel("Direccion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 151, 142, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelfono.setBounds(10, 176, 142, 14);
		contentPane.add(lblTelfono);
		
		JLabel lblCdula = new JLabel("Cedula");
		lblCdula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdula.setBounds(20, 228, 132, 16);
		contentPane.add(lblCdula);
		
		JLabel lblAreaDeTrabajo = new JLabel("Area de Trabajo");
		lblAreaDeTrabajo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAreaDeTrabajo.setBounds(10, 255, 142, 14);
		contentPane.add(lblAreaDeTrabajo);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(97, 282, 55, 16);
		contentPane.add(lblEmail);
		
		textNombre = new JTextField();
		textNombre.setBounds(162, 45, 135, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		PromptSupport.setPrompt("Nombre", textNombre);
		
		textApellido = new JTextField();
		textApellido.setBounds(162, 73, 135, 20);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		PromptSupport.setPrompt("Apellido", textApellido);
		
		textCalle = new JTextField();
		textCalle.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textCalle.setBounds(162, 148, 84, 20);
		contentPane.add(textCalle);
		textCalle.setColumns(10);
		PromptSupport.setPrompt("calle", textCalle);
		
		textTelefono.setBounds(162, 173, 135, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		textPuesto = new JTextField();
		textPuesto.setBounds(162, 198, 135, 20);
		contentPane.add(textPuesto);
		textPuesto.setColumns(10);
		PromptSupport.setPrompt("Puesto", textPuesto);
		
		textCedula.setBounds(162, 223, 135, 20);
		contentPane.add(textCedula);
		textCedula.setColumns(10);
		PromptSupport.setPrompt("Cedula", textCedula);
		
		textArea = new JTextField();
		textArea.setBounds(162, 252, 135, 20);
		contentPane.add(textArea);
		textArea.setColumns(10);
		PromptSupport.setPrompt("Area de Trabajo", textArea);
		
		textNumero = new JTextField();
		textNumero.setBounds(258, 148, 39, 20);
		contentPane.add(textNumero);
		textNumero.setColumns(10);
		PromptSupport.setPrompt("#", textNumero);
		
		textCiudad = new JTextField();
		textCiudad.setBounds(309, 148, 115, 20);
		contentPane.add(textCiudad);
		textCiudad.setColumns(10);
		PromptSupport.setPrompt("Ciudad", textCiudad);
		
		textEmail = new JTextField();
		textEmail.setBounds(162, 280, 262, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		PromptSupport.setPrompt("email@mail.com", textEmail);
		

		
		comboDia = new JComboBox();
		comboDia.setBounds(162, 123, 33, 20);
		contentPane.add(comboDia);
		comboDia.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		comboMes = new JComboBox();
		comboMes.setBounds(200, 123, 46, 20);
		contentPane.add(comboMes);
		comboMes.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		
		comboAno = new JComboBox();
		comboAno.setBounds(248, 123, 49, 20);
		contentPane.add(comboAno);
		comboAno.setModel(new DefaultComboBoxModel(new String[] {"1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014","2015","2016"}));
		rdbtnFemenino = new JRadioButton("F");
		groupSexo.add(rdbtnFemenino);
		rdbtnFemenino.setOpaque(false);
		rdbtnFemenino.setBounds(162, 97, 33, 23);
		contentPane.add(rdbtnFemenino);
		
		
		rdbtnMasculino = new JRadioButton("M");
		groupSexo.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(201, 97, 46, 23);
		contentPane.add(rdbtnMasculino);
		rdbtnMasculino.setOpaque(false);
			
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ((textNombre.getText().isEmpty()==true)
						|| ((textApellido.getText().isEmpty()==true)
						|| (textCalle.getText().isEmpty()==true)
						|| (textCalle.getText().isEmpty()==true)
						|| (textNumero.getText().isEmpty()==true)
						|| (textCiudad.getText().isEmpty()==true)
						|| (textTelefono.getText().isEmpty()==true)
						|| (Utilidades.isNumber(textTelefono.getText().replaceAll("-| ",""))==false)
						|| (textPuesto.getText().isEmpty()==true)
						|| (textCedula.getText().isEmpty()==true)
						|| (Utilidades.isNumber(textCedula.getText().replaceAll("-| ",""))==false)
						|| (textArea.getText().isEmpty()==true)
						|| (textEmail.getText().isEmpty())==true))
						{
							
							
							//validar que solo inserten  numero
							if(Utilidades.isNumber(textTelefono.getText().replaceAll("-| ",""))==false) 
							{
								JOptionPane.showMessageDialog(textApellido, "Solo se permiten números, guiones y espacios en cedula");
							}
							if (Utilidades.isNumber(textCedula.getText().replaceAll("-| ",""))==false)
							{
								JOptionPane.showMessageDialog(textCedula, "Solo se permiten números, guiones y espacios en telefono");
							}
							else
							{ 
								JOptionPane.showMessageDialog(textApellido, "Los datos están vacíos o incorrectos");
							}
						}
			
			else
			{
				char sexo;
				String ano = (String)comboAno.getSelectedItem();
				String mes = (String)comboMes.getSelectedItem();
				String dia = (String)comboDia.getSelectedItem();
				
				fechaNacimiento = ""+ano+" " + mes+" " + dia+ "";
				
				if (rdbtnFemenino.isSelected())
				{
					sexo = 'F';
				}
				else
					sexo = 'M';
				
				Conexion.Conectar();
				try {
					Statement comando = Conexion.Conectar().createStatement();
				
					comando.execute("UPDATE persona, email, empleado, direccion, telefonos "
							+ "SET persona.nombre = '"+textNombre.getText()+"', "
							+ "persona.apellido = '"+textApellido.getText()+"', "
							+ "persona.sexo = '"+sexo+"', "
							+ "persona.fechaNacimiento = '"+fechaNacimiento+"', "
							+ "persona.cedula = '"+textCedula.getText().replaceAll("-| ","")+"', "
							+ "email.email = '"+textEmail.getText()+"', "
							+ "direccion.calle = '"+textCalle.getText()+"', "
							+ "direccion.numeroEdificio = '"+textNumero.getText()+"', "
							+ "direccion.ciudad ='"+textCiudad.getText()+"', "
							+ "empleado.puesto ='"+textPuesto.getText()+"', "
							+ "telefonos.telefono = '"+textTelefono.getText().replaceAll("-| ","")+"', "
							+ "empleado.areaTrabajo='"+textArea.getText()+"' "
							+ "WHERE persona.idPersona = empleado.idEmpleado "
							+ "AND persona.idPersona = telefonos.idPersona "
							+ "AND persona.idPersona = email.idPersona "
							+ "AND persona.idPersona = direccion.idPersona "
							+ "AND persona.idPersona = "+textBuscar.getText()+"");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Limpiar.limpiarCampos(VentanaEmpleado.this.getContentPane());
				JOptionPane.showMessageDialog(VentanaEmpleado.this, "Operacion Realiazada exitosamente!");
				
			}
			}
		});
		
		
		btnActualizar.setIcon(new ImageIcon(VentanaEmpleado.class.getResource("/Imagenes/NewDocumentHS.png")));
		btnActualizar.setBounds(309, 42, 115, 23);
		contentPane.add(btnActualizar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(VentanaEmpleado.class.getResource("/Imagenes/saveHS.png")));
		btnGuardar.setBounds(309, 72, 115, 23);
		contentPane.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener()
		{	
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (e.getSource()==btnGuardar)
			{	
				
				//validar que los datos no estén vacíos
				
				if ((textNombre.getText().isEmpty()==true)
						|| ((textApellido.getText().isEmpty()==true)
						|| (textCalle.getText().isEmpty()==true)
						|| (textCalle.getText().isEmpty()==true)
						|| (textNumero.getText().isEmpty()==true)
						|| (textCiudad.getText().isEmpty()==true)
						|| (textTelefono.getText().isEmpty()==true)
						|| (Utilidades.isNumber(textTelefono.getText().replaceAll("-| ",""))==false)
						|| (textPuesto.getText().isEmpty()==true)
						|| (textCedula.getText().isEmpty()==true)
						|| (Utilidades.isNumber(textCedula.getText().replaceAll("-| ",""))==false)
						|| (textArea.getText().isEmpty()==true)
						|| (textEmail.getText().isEmpty())==true))
						{
							// para validar que inserten un numero
							if(Utilidades.isNumber(textTelefono.getText().replaceAll("-| ",""))==false ) 
								{
								JOptionPane.showMessageDialog(textApellido, "Solo se permiten números, guiones y espacios en telefono");
								}
							if (Utilidades.isNumber(textCedula.getText().replaceAll("-| ",""))==false)
							{
								JOptionPane.showMessageDialog(textCedula, "Solo se permiten números, guiones y espacios en cedula");
							}
							else
							{ 
								JOptionPane.showMessageDialog(textApellido, "Los datos están vacíos o incorrectos");
							}
					
				}
			
			else
			{
		         if(rdbtnFemenino.isSelected())
					{
						sexo ='F';
					}
					else 
					{
						sexo = 'M';
					}
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");	
					Calendar calendar = new GregorianCalendar ();
				
					fechaRegistro = formatoFecha.format(calendar.getTime());
				
					String ano = (String)comboAno.getSelectedItem();
					String mes = (String)comboMes.getSelectedItem();
					String dia = (String)comboDia.getSelectedItem();
				
					fechaNacimiento = ""+dia+"/" + mes+"/" + ano+ "";

				
					//*******INSERCION A BASE DE DATOS***************//
				
					try {
					
						cn.Conectar();
						Statement comando = Conexion.Conectar().createStatement();
					
						comando.execute(" insert into persona (nombre,apellido,sexo,fechaNacimiento,cedula,fechaRegistro,tipoUsuario)"
								+ " values ('"+textNombre.getText()
								+"','"+textApellido.getText()+"','"+sexo+"',"
								+ "'"+fechaNacimiento+"', '"+textCedula.getText().replaceAll("-| ","")+"',"
											+ "'"+fechaRegistro+"', '"+"Empleado"+"')");			
					
						ResultSet rs = comando.executeQuery("select idPersona from persona");
						int id = 0;
						while(rs.next())
						{
							id = rs.getInt(1);
						}
					
						comando.execute("insert into empleado (idEmpleado,puesto,areaTrabajo)"
								+ "values ('"+id+"', '"+textPuesto.getText()+"',"
									+ " '"+textArea.getText()+"')");
					
						comando.execute("insert into direccion (idPersona,calle,numeroEdificio,ciudad)"
								+ "values ('"+id+"', '"+textCalle.getText()+"', '"+textNumero.getText()+"', '"+textCiudad.getText()+"')");
					
					
						comando.execute("insert into telefonos (idPersona,telefono)"
								+ "values ('"+id+"','"+textTelefono.getText().replaceAll("-| ","")+"')");
					
						comando.execute("insert into email (idPersona,email)"
								+ "values ('"+id+"','"+textEmail.getText()+"')");
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Limpiar.limpiarCampos(VentanaEmpleado.this.getContentPane());
					JOptionPane.showMessageDialog(VentanaEmpleado.this, "Operacion Realiazada exitosamente!");

				}
			}
			}
			});
		
		
		/*****************	BOTON CANCELAR  *******************
		 * ****************************************************
		 */
		
		final JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnCancelar)
				{
					textNombre.setText(null);
					textApellido.setText(null);
					textCalle.setText(null);
					textNumero.setText(null);
					textCiudad.setText(null);
					textTelefono.setText(null);
					textPuesto.setText(null);
					textCedula.setText(null);
					textArea.setText(null);
					textEmail.setText(null);
					textBuscar.setText(null);
				
				}
			}
		});
		
		btnCancelar.setIcon(new ImageIcon(VentanaEmpleado.class.getResource("/Imagenes/DeleteHS.png")));
		btnCancelar.setBounds(309, 101, 115, 23);
		contentPane.add(btnCancelar);

		setLocationRelativeTo(null);
		
	}
	
	final void cargarDatos (int id)
	{
		//Poner que los datos sean llenados SI O SI
		
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
			Statement comando = Conexion.Conectar().createStatement();
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
			Statement comando = Conexion.Conectar().createStatement();
			rs = comando.executeQuery("select persona.idPersona, empleado.idEmpleado, "
		+ "persona.nombre, persona.apellido, persona.sexo, persona.cedula, persona.fechaNacimiento, "
		+ " empleado.puesto, empleado.areaTrabajo, telefonos.telefono,"
	   + "direccion.calle, direccion.numeroEdificio, direccion.ciudad,"
			+ "email.email "
			 		+ "from persona, empleado, telefonos, direccion, email "
			 		+ "where persona.idPersona = empleado.idEmpleado "
			 		+ "AND persona.idPersona = telefonos.idPersona "
			 		+ "AND persona.idPersona = direccion.idPersona "
			 		+ "AND persona.idPersona = email.idPersona "
			 		+ "AND persona.idPersona="+strI);
			 
			 char sexo = 0;
			 
			 String fecha = "";
			 while(rs.next())
				{
				 	
				 	
				 	textNombre.setText(rs.getString(3));
				 	textApellido.setText(rs.getString(4));
				 	sexo = rs.getString(5).charAt(0);
					textCedula.setText(rs.getString(6));
					fecha = rs.getString(7);
					textPuesto.setText(rs.getString(8));
					textArea.setText(rs.getString(9));
					textTelefono.setText(rs.getString(10));
					textCalle.setText(rs.getString(11));
					textNumero.setText(rs.getString(12));
					textCiudad.setText(rs.getString(13));
					textEmail.setText(rs.getString(14));
				}
			 if(fecha.length()!=0)
			 {
				 comboAno.setSelectedItem(fecha.subSequence(6,10));
				 comboMes.setSelectedItem(fecha.subSequence(3,5));
				 comboDia.setSelectedItem(fecha.subSequence(0,2));
			 }
			 if (sexo == 'F')
			 {
				rdbtnFemenino.setSelected(true);
			 }
			 if (sexo == 'M')
			 {
				rdbtnMasculino.setSelected(true);
			 }
			 else
			 {
				 rdbtnFemenino.setSelected(false);
				 rdbtnMasculino.setSelected(false);
			 }
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "se etrallo el programa");
		}

			
	}
}
