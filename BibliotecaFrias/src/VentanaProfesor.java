import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

import org.jdesktop.swingx.prompt.PromptSupport;

import Clases.Conexion;
import Clases.Limpiar;
import Clases.Utilidades;

public class VentanaProfesor extends JDialog {

	private JPanel contentPane;
	private JTextField textBuscar;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCalle;
	private JFormattedTextField textTelefono = new JFormattedTextField();
	private JFormattedTextField textCedula = new JFormattedTextField();
	private JTextField textArea;
	private JTextField textInstitucion;
	private JComboBox comboDia;
	private JComboBox comboMes;
	private JComboBox comboAno;
	private final ButtonGroup groupSexo = new ButtonGroup();
	private JComboBox comboTanda;
	String nombre = "Marlene";
	String apellido ="";
	String direccion ="";
	String telefono = "";
	String puesto = "";
	String cedula;
	String area = "";
	char sexo = ' ';
	private JTextField textNumero;
	private JTextField textCiudad;
	private JTextField textEmail;
	private JRadioButton rdbtnFemenino;
	private JRadioButton rdbtnMasculino;
	private JTextField textCodigo;
	String fechaNacimiento;
	String fechaRegistro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProfesor Frame = new VentanaProfesor();
					Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaProfesor() {
		setModal(true);
		setType(Type.UTILITY);
		setTitle("Registrar Profesor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaProfesor.class.getResource("/imagenes/Book.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 395);
		contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
		setLocationRelativeTo(null);
		

		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(162, 10, 33, 23);
		btnNewButton.setIcon(new ImageIcon(VentanaProfesor.class.getResource("/Imagenes/Find.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarUsuarios bus = new BuscarUsuarios(VentanaProfesor.this);
				bus.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
	
		/*
		 * 				LABELS
		 * 
		 * 
		 */
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
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setBounds(10, 151, 142, 14);
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(10, 176, 142, 14);
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblTelefono);
		
		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setBounds(10, 201, 142, 14);
		lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCedula);
		
		JLabel lblArea = new JLabel("Area Académica");
		lblArea.setBounds(10, 226, 142, 14);
		lblArea.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblArea);
		
		JLabel lblInstitucion = new JLabel("Institución");
		lblInstitucion.setBounds(10, 251, 142, 14);
		lblInstitucion.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblInstitucion);
		
		JLabel lblTanda = new JLabel("Tanda");
		lblTanda.setBounds(10, 276, 142, 14);
		lblTanda.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblTanda);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(95, 302, 55, 16);
		contentPane.add(lblEmail);
		
		textBuscar = new JTextField();
		textBuscar.setEditable(false);
		textBuscar.setBounds(64, 11, 86, 20);
		contentPane.add(textBuscar);
		textBuscar.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(162, 45, 135, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		PromptSupport.setPrompt("Nombre", textNombre);
		
		
		textApellido = new JTextField();
		textApellido.setBounds(162, 72, 135, 20);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		PromptSupport.setPrompt("Apellido", textApellido);
		
		textCalle = new JTextField();
		textCalle.setBounds(162, 148, 84, 20);
		contentPane.add(textCalle);
		textCalle.setColumns(10);
		PromptSupport.setPrompt("Calle", textCalle);
		
		rdbtnFemenino = new JRadioButton("F");
		groupSexo.add(rdbtnFemenino);
		groupSexo.add(rdbtnFemenino);
		rdbtnFemenino.setBounds(162, 97, 33, 23);
		rdbtnFemenino.setOpaque(false);
		contentPane.add(rdbtnFemenino);
		
		rdbtnMasculino = new JRadioButton("M");
		groupSexo.add(rdbtnMasculino);
		groupSexo.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(251, 97, 46, 23);
		rdbtnMasculino.setOpaque(false);
		contentPane.add(rdbtnMasculino);
		

		textTelefono.setBounds(162, 173, 135, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		PromptSupport.setPrompt("Telefono", textTelefono);
		
		textCedula.setBounds(162, 198, 135, 20);
		contentPane.add(textCedula);
		textCedula.setColumns(10);
		PromptSupport.setPrompt("Cedula", textCedula);
		
		textArea = new JTextField();
		textArea.setBounds(162, 223, 135, 20);
		contentPane.add(textArea);
		textArea.setColumns(10);
		PromptSupport.setPrompt("AreaAcademica", textArea);
		
		textInstitucion = new JTextField();
		textInstitucion.setBounds(162, 248, 135, 20);
		contentPane.add(textInstitucion);
		textInstitucion.setColumns(10);
		PromptSupport.setPrompt("Instucion", textInstitucion);
		
		textNumero = new JTextField();
		textNumero.setBounds(258, 148, 39, 20);
		contentPane.add(textNumero);
		textNumero.setColumns(10);
		PromptSupport.setPrompt("#", textNumero);
		
		textCiudad = new JTextField();
		textCiudad.setBounds(309, 148, 118, 20);
		contentPane.add(textCiudad);
		textCiudad.setColumns(10);
		PromptSupport.setPrompt("Ciudad", textCiudad);
		
		textEmail = new JTextField();
		textEmail.setBounds(162, 299, 135, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		PromptSupport.setPrompt("email@Bibliomail.com", textEmail);
		
		
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
		
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((textNombre.getText().isEmpty()==true)
						|| ((textApellido.getText().isEmpty()==true)
						|| (textCalle.getText().isEmpty()==true)
						|| (textCalle.getText().isEmpty()==true)
						|| (textNumero.getText().isEmpty()==true)
						|| (textCiudad.getText().isEmpty()==true)
					    || (textTelefono.getText().isEmpty()==true)
					    || (Utilidades.isNumber(textTelefono.getText().replaceAll("-| ",""))==false)
						|| (textInstitucion.getText().isEmpty()==true)
						|| (textCodigo.getText().isEmpty()==true)
						|| (textCedula.getText().isEmpty()==true)
						|| (Utilidades.isNumber(textCedula.getText().replaceAll("-| ","").replaceAll("-",""))==false)
						|| (textArea.getText().isEmpty()==true)
						|| (textEmail.getText().isEmpty())==true))
						{
							if(Utilidades.isNumber(textTelefono.getText().replaceAll("-| ",""))==false) //If para validar que inserten un numero
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
					
							else{ 
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
							
							Conexion con = new Conexion();
							
							con.Conectar();
							try {
								Statement comando = con.Conectar().createStatement();
								comando.execute("UPDATE persona, profesor, direccion, telefonos,email  "
										+ "SET persona.nombre='"+textNombre.getText()+"', "
										+ "persona.apellido = '"+textApellido.getText()+"', "
										+ "persona.sexo = '"+sexo+"', "
										+ "persona.fechaNacimiento = '"+fechaNacimiento+"', "
										+ "persona.cedula = '"+textCedula.getText().replaceAll("-| ","")+"', "
										+ "direccion.calle = '"+textCalle.getText()+"', "
										+ "direccion.numeroEdificio = '"+textNumero.getText()+"', "
										+ "direccion.ciudad = '"+textCiudad.getText()+"', "
										+ "telefonos.telefono = '"+textTelefono.getText().replaceAll("-| ","")+"', "
										+ "email.email= '"+textEmail.getText()+"', "
												+ "profesor.codigoProfesor ='"+textCodigo.getText()+"' "
														+ "WHERE persona.idPersona = profesor.idProfesor "
														+ "AND profesor.idProfesor='"+textBuscar.getText()+"' " );
								
							} catch (SQLException e) {
							
								e.printStackTrace();
							}
							
							Limpiar.limpiarCampos(VentanaProfesor.this.getContentPane());
							JOptionPane.showMessageDialog(VentanaProfesor.this, "Operacion Realiazada exitosamente!");
						}
				
			}
			}
		);
		btnActualizar.setBounds(309, 42, 118, 23);
		btnActualizar.setIcon(new ImageIcon(VentanaProfesor.class.getResource("/Imagenes/NewDocumentHS.png")));
		contentPane.add(btnActualizar);
		
		final JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnGuardar)
				{	
                      if ((textNombre.getText().isEmpty()==true)
							|| ((textApellido.getText().isEmpty()==true)
							|| (textCalle.getText().isEmpty()==true)
							|| (textCalle.getText().isEmpty()==true)
							|| (textNumero.getText().isEmpty()==true)
							|| (textCiudad.getText().isEmpty()==true)
							|| (textTelefono.getText().isEmpty()==true)
							|| (Utilidades.isNumber(textTelefono.getText().replaceAll("-| ",""))==false)
							|| (textInstitucion.getText().isEmpty()==true)
							|| (textCodigo.getText().isEmpty()==true)
							|| (textCedula.getText().isEmpty()==true)
							|| (Utilidades.isNumber(textCedula.getText())==false)
							|| (textArea.getText().isEmpty()==true)
							|| (textEmail.getText().isEmpty())==true))
							{
								if(Utilidades.isNumber(textTelefono.getText().replaceAll("-| ",""))==false) 
								{
									JOptionPane.showMessageDialog(textApellido, "Solo se permiten números, guiones y espacios");
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
										sexo ='M';
									}
									SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");	
									Calendar calendar = new GregorianCalendar ();
								
									fechaRegistro = sdf.format(calendar.getTime());
								
									String ano = (String)comboAno.getSelectedItem();
									String mes = (String)comboMes.getSelectedItem();
									String dia = (String)comboDia.getSelectedItem();
								
									fechaNacimiento = ""+dia+"/" + mes+"/" + ano+ "";
								try {
									
										Conexion cn = new Conexion();
										cn.Conectar();
										Statement comando = cn.Conectar().createStatement();
									
										comando.execute(" insert into persona (nombre,apellido,sexo,fechaNacimiento,cedula,fechaRegistro,tipoUsuario)"
												+ " values ('"+textNombre.getText()+"','"+textApellido.getText()+"','"+sexo+"',"
													+ "'"+fechaNacimiento+"', '"+textCedula.getText()+"',"
															+ "'"+fechaRegistro+"', '"+"Profesor"+"')");			
									
										ResultSet rs = comando.executeQuery("select idPersona from persona");
										int id = 0;
										while(rs.next())
										{
											id = rs.getInt(1);
										}
									
										comando.execute("insert into profesor (idProfesor,areaAcademica,institucionAcademica,tanda,codigoProfesor)"
												+ "values ('"+id+"', '"+textArea.getText()+"',"
													+ " '"+textInstitucion.getText()+"', '"+comboTanda.getSelectedItem()+"', '"+textCodigo.getText()+"')");
									
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
								
								Limpiar.limpiarCampos(VentanaProfesor.this.getContentPane());
								JOptionPane.showMessageDialog(VentanaProfesor.this, "Operacion Realiazada exitosamente!");
			
								}
				}
				}
				});
		btnGuardar.setBounds(309, 72, 118, 23);
		btnGuardar.setIcon(new ImageIcon(VentanaProfesor.class.getResource("/Imagenes/saveHS.png")));
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textNombre.setText(null);
				textApellido.setText(null);
				textCalle.setText(null);
				textNumero.setText(null);
				textCiudad.setText(null);;
				textNumero.setText(null);
				textCiudad.setText(null);
				textTelefono.setText(null);
				textArea.setText(null);
				textInstitucion.setText(null);
				textCedula.setText(null);
				textArea.setText(null);
				textEmail.setText(null);
				textCodigo.setText(null);
				textBuscar.setText(null);
			}
		});
		btnCancelar.setBounds(309, 101, 118, 23);
		btnCancelar.setIcon(new ImageIcon(VentanaProfesor.class.getResource("/Imagenes/DeleteHS.png")));
		contentPane.add(btnCancelar);
		
		comboTanda = new JComboBox();
		comboTanda.setModel(new DefaultComboBoxModel(new String[] {"Ma\u00F1ana", "Tarde", "Noche"}));
		comboTanda.setBounds(162, 273, 135, 20);
		contentPane.add(comboTanda);
		
		JLabel lblCdigoProrfesor = new JLabel("Codigo Prorfesor");
		lblCdigoProrfesor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigoProrfesor.setBounds(33, 329, 118, 14);
		contentPane.add(lblCdigoProrfesor);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(162, 326, 265, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		PromptSupport.setPrompt("Codigo", textCodigo);

	}

	public void cargarDatos(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(id);
		String strI = sb.toString(); 
		ResultSet rs = null;
		String emp = null;
		
		Conexion con = new Conexion();
		Conexion.Conectar();
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
			Statement comando = con.Conectar().createStatement();
			 rs = comando.executeQuery("select persona.idPersona, profesor.idProfesor, "
		     + "persona.nombre, persona.apellido, persona.sexo, persona.cedula, persona.fechaNacimiento, "
  	         + "profesor.areaAcademica, profesor.institucionAcademica, profesor.tanda, profesor.codigoProfesor, "
			 + "telefonos.telefono,"
		     + "direccion.calle, direccion.numeroEdificio, direccion.ciudad,"
			 + "email.email "
			 		+ "from persona, profesor, telefonos, direccion, email "
			 		+ "WHERE persona.idPersona = profesor.idProfesor "
			 		+ "AND persona.idPersona = telefonos.idPersona "
			 		+ "AND persona.idPersona = direccion.idPersona "
			 		+ "AND persona.idPersona = email.idPersona "
			 		+ "AND persona.idPersona="+strI);
			 
			 char sexo = 0;
			 String tanda = null;
			 
			 String fecha = "";
			 while(rs.next())
				{
				 	textNombre.setText(rs.getString(3));
				 	textApellido.setText(rs.getString(4));
				 	sexo = rs.getString(5).charAt(0);
					textCedula.setText(rs.getString(6));
					fecha = rs.getString(7);
					textArea.setText(rs.getString(8));
					textInstitucion.setText(rs.getString(9));
					tanda = rs.getString(10);
					textCodigo.setText(rs.getString(11));
					textTelefono.setText(rs.getString(12));
					textCalle.setText(rs.getString(13));
					textNumero.setText(rs.getString(14));
					textCiudad.setText(rs.getString(15));
					textEmail.setText(rs.getString(16));
				}
			 
			 comboTanda.setSelectedItem(tanda);
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
