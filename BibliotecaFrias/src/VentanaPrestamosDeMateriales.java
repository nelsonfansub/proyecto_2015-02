import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.*;
import Clases.Conexion;
import Clases.Limpiar;
import Clases.LoginClase;
import Clases.Prestamo;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class VentanaPrestamosDeMateriales extends JDialog {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtIdUsuario;
	JTextField txtNombreUsuario;
	private JLabel lblNewLabel_2;
	private JTextField txtFecha;
	private JTable table;
	private JButton btnBuscarUsuario;
	private JButton btnActualizar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTextField txtTipoUsuario;
	private JTextField txtBibliotecario;
	private JTextField txtFechaMax;
	private JTextField txtHoraMax;
	private JTable tbDetPrestamo;
	private JTextField txtHora;
	private JLabel lblBibliotecario;
	private JTextField txtBiblitecario;
	private JCheckBox chkLecturaSala;
	private static String lecturaSala;
	static JFrame ventanaPadre;
	private static String idBibliotecario; 
	private static int idUsuario;
	private static String nombreUsuario = "";
	private static String tipoUsuario = "";
	public int filaSeleccionada;
	String idMaterialTomado;
	public int idPrestamo;
	public int idDet[] = new int[3];
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrestamosDeMateriales frame = new VentanaPrestamosDeMateriales(ventanaPadre);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrestamosDeMateriales(final JFrame ventanPadre) {
		
		super(ventanaPadre);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrestamosDeMateriales.class.getResource("/imagenes/Book.png")));
		setTitle("Prestamo de Material");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 762, 370);
		contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Codigo ");
		lblNewLabel.setBounds(34, 25, 46, 19);
		contentPane.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(90, 25, 77, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton btnBuscarId = new JButton("");
		btnBuscarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BuscarPrestamo nuevoBuscarPrestamo = new BuscarPrestamo(VentanaPrestamosDeMateriales.this);
				nuevoBuscarPrestamo.setVisible(true);
			}
		});
		btnBuscarId.setBounds(177, 18, 30, 26);
		btnBuscarId.setIcon(new ImageIcon(VentanaPrestamosDeMateriales.class.getResource("/imagenes/Find.png")));
		contentPane.add(btnBuscarId);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(34, 58, 46, 14);

		contentPane.add(lblNewLabel_1);
		
		txtIdUsuario = new JTextField();
		txtIdUsuario.setEditable(false);
		txtIdUsuario.setBounds(90, 56, 77, 20);
		contentPane.add(txtIdUsuario);
		txtIdUsuario.setColumns(10);
		
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setEditable(false);
		txtNombreUsuario.setBounds(217, 55, 401, 20);
		contentPane.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		
		lblNewLabel_2 = new JLabel("Fecha:");
		lblNewLabel_2.setBounds(392, 27, 39, 14);
		contentPane.add(lblNewLabel_2);
		
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(441, 25, 71, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		///---------------------- Asignarle la fecha actual al txtFecha----------------//
	    Date fechaActual = new Date();
		DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.SHORT);
		txtFecha.setText(formatoFecha.format(fechaActual));
	    
		
		table = new JTable();
		table.setBounds(206, 119, -180, 48);
		contentPane.add(table);
		
		btnBuscarUsuario = new JButton("");
		btnBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				BuscarUsuario nuevoBuscarUsuario = new BuscarUsuario(VentanaPrestamosDeMateriales.this);
				nuevoBuscarUsuario.setVisible(true);
			}
	
		});
		

		btnBuscarUsuario.setBounds(177, 50, 30, 26);
		btnBuscarUsuario.setIcon(new ImageIcon(VentanaPrestamosDeMateriales.class.getResource("/imagenes/Find.png")));
		contentPane.add(btnBuscarUsuario);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tbDetPrestamo.isEditing() && tbDetPrestamo.editCellAt(tbDetPrestamo.getSelectedRow(), 4))
				{
					tbDetPrestamo.getCellEditor().stopCellEditing();
				}
				
				if(!txtId.getText().equals("")) 
				{	
					if(txtIdUsuario.getText().isEmpty() 
					|| tbDetPrestamo.getRowCount() == 1 &&  tbDetPrestamo.getValueAt(0,0) == null)
					{
						 JOptionPane.showMessageDialog(VentanaPrestamosDeMateriales.this, "Llene todos los campos antes de Guardar el Prestamo");
					
					} else {
						
						try {
							Connection conexion = Conexion.Conectar();
				
							String sql = "UPDATE prestamo SET idPersona=?, bibliotecario=?, horaEntrega =?, fechaEntrega =?, horaPrestamo =?, fechaPrestamo =? where idPrestamo =?";
							PreparedStatement sentencia = conexion.prepareStatement(sql);	
							sentencia.setInt(1, Integer.parseInt(txtIdUsuario.getText()));	
							sentencia.setString(2, txtBibliotecario.getText());
							sentencia.setString(3 ,txtHoraMax.getText());
							sentencia.setString(4, txtFechaMax.getText());
							sentencia.setString(5, txtHora.getText());
							sentencia.setString(6, txtFecha.getText());
							sentencia.setInt(7, Integer.parseInt(txtId.getText())); 
							
							sentencia.executeUpdate();
							
							
							
							for(int i = 0; i <  tbDetPrestamo.getRowCount(); i++)
							{
								String sql2 = String.format("UPDATE det_prestamo SET idMaterial= '%s', condicionFisica= '%s' where idDet_Prestamo = '%s'", Integer.parseInt(tbDetPrestamo.getValueAt(i, 0).toString()), tbDetPrestamo.getValueAt(i, 4),  idDet[i]);  
						
								PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
								sentencia2.execute();
								actualizarInventario( Integer.parseInt(tbDetPrestamo.getValueAt(i, 0).toString()), tbDetPrestamo.getValueAt(i, 4).toString(), "Prestado");
							}
						
					  } catch  (SQLException e) {
					  JOptionPane.showMessageDialog(rootPane, e.getMessage());
					  }
		
						JOptionPane.showMessageDialog(rootPane, "Datos Actualizados...");
					} 
					
				} else{
				   JOptionPane.showMessageDialog(rootPane, "ID Prestamo no asignado \nPulse guardar para crear un nuevo registro...");
			   }
			}

		});
		btnActualizar.setBounds(627, 55, 108, 23);
		btnActualizar.setIcon(new ImageIcon(VentanaPrestamosDeMateriales.class.getResource("/imagenes/NewDocumentHS.png")));
		contentPane.add(btnActualizar);
		
		//----------------------- Guardar los  datos del encabezado en la base de datos----------------------//
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tbDetPrestamo.isEditing() && tbDetPrestamo.editCellAt(tbDetPrestamo.getSelectedRow(), 4))
				{
					tbDetPrestamo.getCellEditor().stopCellEditing();
				}
				if(txtId.getText().equals("")) //si el txt id guardar nuevo
				{	
					if(txtIdUsuario.getText().isEmpty() || tbDetPrestamo.getRowCount() == 1 &&  tbDetPrestamo.getValueAt(0,0) == null)
					{
						 JOptionPane.showMessageDialog(VentanaPrestamosDeMateriales.this, "Llene todos los campos antes de Guardar el Prestamo");
					} else {
						try {
		
								int idPersona = Integer.parseInt(txtIdUsuario.getText());
								String bibliotecario = txtBiblitecario.getText();
								String horaEntrega = txtHoraMax.getText();
								String fechaEntrega = txtFechaMax.getText();
								String horaPrestamo = txtHora.getText();
								String fechaPrestamo = txtFecha.getText();
								
							    Prestamo nuevoPrestamo = new Prestamo(idPersona, bibliotecario, horaEntrega, fechaEntrega, horaPrestamo, fechaPrestamo, lecturaSala );
							    nuevoPrestamo.guardarDatosEncabezado() ;
							    idPrestamo = nuevoPrestamo.getIdPrestamo();
							    insertarDatosTabla(idPrestamo);
							    
							}
							catch(NumberFormatException excepcion)
							{
							   JOptionPane.showMessageDialog(VentanaPrestamosDeMateriales.this, "No se digito un usuario");
							} 
							catch (ClassNotFoundException e1) 
							{
						         e1.printStackTrace();
							} 
							catch (SQLException e1)
							{
								e1.printStackTrace();
							} 
			
							 JOptionPane.showMessageDialog(rootPane, "Datos Guardados Correstamente e Inventario Actualizado");
							 Limpiar cancelar = new Limpiar();
							 cancelar.limpiarCampos( VentanaPrestamosDeMateriales.this.getContentPane() );
							 limpiarTabla();
					}
				} else 
					JOptionPane.showMessageDialog(rootPane, "Pulse Boton Actualizar para Modificar Prestamo ");

			} 

		});
		btnGuardar.setBounds(628, 24, 107, 23);
		btnGuardar.setIcon(new ImageIcon(VentanaPrestamosDeMateriales.class.getResource("/imagenes/saveHS.png")));
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Limpiar cancelar = new Limpiar();
				cancelar.limpiarCampos( VentanaPrestamosDeMateriales.this.getContentPane() );
				limpiarTabla(); 
			 	//La fecha y la Hora
			  	Date fechaActual = new Date();
				DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.SHORT);
				txtFecha.setText(formatoFecha.format(fechaActual));
				
				Date horaActual = new Date();
				DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.SHORT);
				txtHora.setText(formatoHora.format(horaActual));

			}
	
		});
		btnCancelar.setBounds(628, 82, 108, 23);
		btnCancelar.setIcon(new ImageIcon(VentanaPrestamosDeMateriales.class.getResource("/imagenes/DeleteHS.png")));
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo Usuario");
		lblNewLabel_3.setBounds(10, 91, 88, 14);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3);
		
		txtTipoUsuario = new JTextField();
		txtTipoUsuario.setEditable(false);
		txtTipoUsuario.setBounds(90, 87, 117, 20);
		contentPane.add(txtTipoUsuario);
		txtTipoUsuario.setColumns(10);
		
		
		chkLecturaSala = new JCheckBox("Lectura en la Sala");
		chkLecturaSala.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chkLecturaSala.isSelected()){
					lecturaSala = "SI";
				} else {
					lecturaSala = "NO"; 
				}
			}
		});
		chkLecturaSala.setSelected(true);
		chkLecturaSala.setOpaque(false);
		chkLecturaSala.setBounds(227, 87, 142, 23);
		chkLecturaSala.setBackground(new Color(173, 216, 230));
		chkLecturaSala.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		if(chkLecturaSala.isSelected()){
			lecturaSala = "SI";
		} else {
			lecturaSala = "NO";
		}
	
		contentPane.add(chkLecturaSala);
		
		
		JLabel lblNewLabel_5 = new JLabel("Tiempo Maximo Para ser Devuelto:");
		lblNewLabel_5.setBounds(10, 119, 215, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha:");
		lblNewLabel_6.setBounds(90, 144, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		txtFechaMax = new JTextField();
		txtFechaMax.setEditable(false);
		txtFechaMax.setBounds(139, 142, 86, 20);
		contentPane.add(txtFechaMax);
		txtFechaMax.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Hora:");
		lblNewLabel_7.setBounds(235, 144, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		txtHoraMax = new JTextField();
		txtHoraMax.setEditable(false);
		txtHoraMax.setBounds(269, 142, 88, 20);
		contentPane.add(txtHoraMax);
		txtHoraMax.setColumns(10);
		
		tbDetPrestamo = new JTable();
		tbDetPrestamo.setToolTipText("Pulse F2 Para Buscar Material y TAB para nueva fila");
		tbDetPrestamo.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID Material", "Descripcion", "Tipo Material", "Ubicacion Fisica ", "Condicion Fisica"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbDetPrestamo.getColumnModel().getColumn(2).setPreferredWidth(83);
		tbDetPrestamo.getColumnModel().getColumn(3).setPreferredWidth(90);
		tbDetPrestamo.getColumnModel().getColumn(4).setPreferredWidth(105);
		
		
		
		JScrollPane scrollPane = new JScrollPane(tbDetPrestamo);
		scrollPane.setToolTipText("Pulse F2 Para Buscar Material y TAB para nueva fila");
		tbDetPrestamo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				
			
				if(tbDetPrestamo.getSelectedColumn() == 0)
				{
					
					if(e.getKeyCode() == KeyEvent.VK_TAB)
					{
						tbDetPrestamo.getCellEditor().stopCellEditing();
						cargarDatosDetalle(tbDetPrestamo.getValueAt(tbDetPrestamo.getSelectedRow(), 0).toString());
					}else if(e.getKeyCode() == KeyEvent.VK_F2)
					{
						if(txtTipoUsuario.getText().equals("Profesor") || txtTipoUsuario.getText().equals("Bibliotecario"))
						{
							BuscarMaterialPrincipal nuevoBuscarMaterial = new BuscarMaterialPrincipal(VentanaPrestamosDeMateriales.this, 0);
							nuevoBuscarMaterial.setVisible(true);
						
						}else if(txtTipoUsuario.getText().equals("Empleado") || txtTipoUsuario.getText().equals("Estudiante"))
						{
							BuscarMaterialPrincipal nuevoBuscarMaterial = new BuscarMaterialPrincipal(VentanaPrestamosDeMateriales.this, 3);
							nuevoBuscarMaterial.setVisible(true);
						}
						
					}

				}else if(tbDetPrestamo.getSelectedColumn() == 4)
				{
					if(e.getKeyCode() == KeyEvent.VK_TAB)
					{
						DefaultTableModel modelo = (DefaultTableModel) tbDetPrestamo.getModel();
						int totalfilas = modelo.getRowCount();
						
						if(totalfilas < 3)
						{
							modelo.addRow(new Object[]{"","","","",""});
							
						} else{
							JOptionPane.showMessageDialog(rootPane, "Solo se Pueden prestar 3 Materiales a la vez");
						}
					}
				}
			}
		});
		scrollPane.setBounds(20, 178, 715, 107);
		contentPane.add(scrollPane);
		

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblHora.setBounds(522, 27, 30, 14);
		contentPane.add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setText("Nov 26, 2014");
		txtHora.setColumns(10);
		txtHora.setBounds(562, 24, 56, 20);
		contentPane.add(txtHora);
		
		//-------------------------Asignarle la hora actual al txthora--------------------//
	    Date horaActual = new Date();
		DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.SHORT);
		txtHora.setText(formatoHora.format(horaActual));
		
		lblBibliotecario =new JLabel("Bibliotecario:");
		lblBibliotecario.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBibliotecario.setBounds(534, 296, 88, 14);
		contentPane.add(lblBibliotecario);
		
		txtBiblitecario = new JTextField();
		txtBiblitecario.setEditable(false);
		txtBiblitecario.setColumns(10);
		txtBiblitecario.setBounds(619, 294, 117, 20);
		contentPane.add(txtBiblitecario);
		
		idBibliotecario = LoginClase.getUsuario();
		String nombreCompleto = "";

		try {
			
			
			Connection conexion = Conexion.Conectar(); 
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT  nombre, apellido FROM persona  WHERE idpersona = "+ idBibliotecario ;
			ResultSet resultados = sentencia.executeQuery(sql);
			
			resultados.next() ;
				
				nombreCompleto = resultados.getObject(1).toString()+ " " +resultados.getObject(2).toString();

			} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			}
		
		txtBiblitecario.setText(nombreCompleto);
		
			
	}

	public void cargarDatosUsuario(int idPersonaTomado)
	{
		
		try {
			
			Connection conexion = Conexion.Conectar(); //obj de clase conexion para conectar la base de datos 
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT idpersona, nombre, apellido, tipoUsuario  FROM persona  WHERE idpersona = "+ idPersonaTomado ;
			ResultSet resultados = sentencia.executeQuery(sql);
			
			resultados.next() ;
				
				idUsuario = idPersonaTomado;
				nombreUsuario = resultados.getObject(2).toString()+ " " +resultados.getObject(3).toString();
				tipoUsuario = resultados.getObject(4).toString() ;
		
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

		
	}

	public void validadUsuarios(){

		if(tipoUsuario.equals("Estudiante") || tipoUsuario.equals("Empleado"))
		{

		    Date fechaMax = new Date();
			DateFormat formatoFechaMax = DateFormat.getDateInstance(DateFormat.SHORT);
			txtFechaMax.setText(formatoFechaMax.format(fechaMax));
	
		    Date actual = new Date();
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(actual);
			calendario.add(Calendar.HOUR_OF_DAY, 3);
			
			int hora = calendario.get(Calendar.HOUR_OF_DAY);
			int minutos = calendario.get(Calendar.MINUTE);
			int segundos = calendario.get(Calendar.SECOND);
			String fullHoraMax = hora +":"+ minutos+":"+segundos;
	        txtHoraMax.setText(fullHoraMax);
		
			this.chkLecturaSala.setEnabled(false);
			

		} else if(tipoUsuario.equals("Profesor")){ 
		
			Date actual = new Date();
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(actual);
			calendario.add(Calendar.DAY_OF_MONTH, 7);
			
			int mes = calendario.get(Calendar.MONTH)+1;
			int dia = calendario.get(Calendar.DAY_OF_MONTH);
			int anio = calendario.get(Calendar.YEAR);
			String fullFechaMax = mes+"/"+dia+"/"+ anio;
			txtFechaMax.setText(fullFechaMax);
		
		    Date horaActual = new Date();
			DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.SHORT);
			txtHoraMax.setText(formatoHora.format(horaActual));
			
		}
			
		
	}
	
	//---------------------------para mostrar los datos de usuario en el txt --------------------------///

	public void mostrarDatosentxt() {
		
        this.txtIdUsuario.setText(Integer.toString(idUsuario));
		this.txtNombreUsuario.setText(nombreUsuario.toString());
		this.txtTipoUsuario.setText(tipoUsuario.toString());
   
	}
	
	
	//-------------------------cargar los datos en la tabla de det_prestamo  --------------------------------------------//

	public void cargarDatosDetalle(String idMaterialTomado) {
		
		try {

			String idInventario = tbDetPrestamo.getValueAt(tbDetPrestamo.getSelectedRow(), 0).toString();
			Connection conexion = Conexion.Conectar(); 
			
			Statement sentencia = conexion.createStatement();
			String sql = String.format("SELECT inventario.idInventario, material.titulo, material.tipoMaterial, inventario.ubicacionfisica FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.idInventario = '%s' and inventario.estado <> 'Prestado' and inventario.estado <> 'No Disponible'", idInventario);
			ResultSet conjuntoResultados = sentencia.executeQuery(sql);
			
			if(conjuntoResultados.first())
			{
				conjuntoResultados.beforeFirst();
				while( conjuntoResultados.next() )
				{
						String descripcion = conjuntoResultados.getObject(2).toString(); 
						tbDetPrestamo.setValueAt(descripcion, tbDetPrestamo.getSelectedRow(), 1);
						
						String tipoMaterial = conjuntoResultados.getObject(3).toString(); 
						tbDetPrestamo.setValueAt(tipoMaterial, tbDetPrestamo.getSelectedRow(), 2);
						
						String ubicacionFisica = conjuntoResultados.getObject(4).toString(); 
						tbDetPrestamo.setValueAt(ubicacionFisica, tbDetPrestamo.getSelectedRow(), 3);
				}
			}else {
			
				JOptionPane.showMessageDialog(rootPane, "Este material no esta Disponible o esta Prestado actualmente");
				tbDetPrestamo.setValueAt("", tbDetPrestamo.getSelectedRow(), 0);
			} 
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		}

		if(tipoUsuario.equals("Estudiante") || tipoUsuario.equals("Empleado"))
		{
			String tipoMaterialUsuario = tbDetPrestamo.getValueAt(tbDetPrestamo.getSelectedRow(), 2).toString();
			
			if (!tipoMaterialUsuario.equals("Libro"))
			{
				JOptionPane.showMessageDialog(rootPane, "Este Tipo de Usuario No se le permite prestar este Tipo de Material");
				for(int i=0; i<= 4; i++)
				{
					tbDetPrestamo.setValueAt("", tbDetPrestamo.getSelectedRow(), i);
				}
				
			}

		}
	}
	
	public void mostrarDatoEnTabla(String idMaterialTomado)
	{
		int ultimafila = tbDetPrestamo.getRowCount() -1;
	    tbDetPrestamo.setValueAt(idMaterialTomado, ultimafila, 0);
	}


	public void insertarDatosTabla(int idPrestamo) {
		
		
		Connection cn = Conexion.Conectar();
		try {	

			for(int i=1; i <= tbDetPrestamo.getRowCount(); i++)
			{
				
				int idMaterial = Integer.parseInt(tbDetPrestamo.getValueAt(i-1,0).toString());
				String CondicionFisica = tbDetPrestamo.getValueAt(i-1, 4).toString();
				String sql = "INSERT INTO det_prestamo(idPrestamo, idMaterial, condicionFisica) VALUES ('"+ idPrestamo +"','"+ idMaterial + "','" + CondicionFisica +"')";
				PreparedStatement instruccion = cn.prepareStatement(sql);
				instruccion.execute(); 
				
				actualizarInventario(idMaterial, CondicionFisica, "Prestado");
		
			}
			} catch (SQLException e) {
		
					e.printStackTrace();
			}
		
		}
	
	
	//-------------------Para Limpiar la tabla -------------------------//
	public void limpiarTabla(){
		
		DefaultTableModel modelo =(DefaultTableModel) tbDetPrestamo.getModel();
		int filasTabla = tbDetPrestamo.getRowCount();
		
		for(int i =0; filasTabla >i; i++ ){
			
			modelo.removeRow(0);
		}
		
		modelo.addRow(new Object[]{"","","","",""});
	}

	//---------------------- para actualizar los datos en la tabla Inventario-------------------------------//
	
	public void actualizarInventario(int idMaterial, String condicionFisica, String estado) {
		
			try {
				
				Connection conexion = Conexion.Conectar();
				
				String sql = String.format("UPDATE inventario SET estado= '%s', condicionFisica= '%s' where idInventario = '%s'",estado, condicionFisica, idMaterial);  	
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.execute();	
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		}

	//------------------Para actualizar datos-------------------------------------//
	public void cargarDatosPrestamo(int idPrestamoTomado) {
		
		txtId.setText(idPrestamoTomado +"");
		
	// para mostrar el encabezado	
	try {
			
			Connection conexion = Conexion.Conectar(); 
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * from Prestamo where idPrestamo =" + idPrestamoTomado;
			ResultSet resultados = sentencia.executeQuery(sql);
			
			resultados.next() ;
				
				txtIdUsuario.setText(resultados.getObject(2).toString());
				txtBiblitecario.setText(resultados.getObject(3).toString());
				txtHora.setText(resultados.getObject(4).toString());
				txtFecha.setText(resultados.getObject(5).toString()) ;
				txtHoraMax.setText(resultados.getObject(6).toString());
				txtFechaMax.setText(resultados.getObject(7).toString());
				String lecturaSalaDatos = resultados.getObject(7).toString();
				if(lecturaSalaDatos.equals("Si")){
					chkLecturaSala.setSelected(true);
				} else 
					chkLecturaSala.setSelected(false);
				
				int idPersonaTomado = Integer.parseInt(txtIdUsuario.getText());
				cargarDatosUsuario(idPersonaTomado);
				mostrarDatosentxt();
		
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	
	//  para mostrar el detalle
		try {
		
		    DefaultTableModel modelo = (DefaultTableModel) tbDetPrestamo.getModel();
		    Connection conexion = Conexion.Conectar(); 
		    Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM det_prestamo where idprestamo = " + idPrestamoTomado;
			ResultSet resultados = sentencia.executeQuery(sql);
		    modelo.removeRow(0);
		    
		    
		    while(resultados.next()){
		    
		    	modelo.addRow(new Object[]{resultados.getInt("idMaterial"),"", "","", resultados.getString("condicionFisica")});
		        for(int i =0; i < 1 ; i++){
		        	idDet[i] = resultados.getInt("idDet_Prestamo");
		        
		        }
		        
		    }
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		

		  try {

				  for(int i=0; i < tbDetPrestamo.getRowCount(); i++)
			     {
					String idInventario = tbDetPrestamo.getValueAt(i, 0).toString();
					Connection conexion = Conexion.Conectar();  
					
					Statement sentencia = conexion.createStatement();
					String sql = String.format("SELECT inventario.idInventario, material.titulo, material.tipoMaterial, inventario.ubicacionfisica FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.idInventario = '%s'", idInventario);
					ResultSet conjuntoResultados = sentencia.executeQuery(sql);
					
					while( conjuntoResultados.next() )
					{
						String descripcion = conjuntoResultados.getObject(2).toString(); 
						tbDetPrestamo.setValueAt(descripcion, i, 1);
						
						String autor = conjuntoResultados.getObject(3).toString(); 
						tbDetPrestamo.setValueAt(autor, i, 2);
						
						String tipoMaterial = conjuntoResultados.getObject(4).toString(); 
						tbDetPrestamo.setValueAt(tipoMaterial, i, 3);
			    
					}
							
			     }	
			  
				  } catch (SQLException e) {
						JOptionPane.showMessageDialog(this, e.getMessage());
				  }		
		

		}
		
		
		
}
