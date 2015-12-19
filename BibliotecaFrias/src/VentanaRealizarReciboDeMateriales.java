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
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Clases.Conexion;
import Clases.Limpiar;
import Clases.Recibo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Toolkit;
public class VentanaRealizarReciboDeMateriales extends JDialog {

	private JPanel contentPane;
	private JTextField txtIDRecibo;
	private static JTextField txtIDprestamo;
	private JTextField txtNombreUsuario;
	private static JTextField txtFechaRecibo;
	private JTextField txtTipoUsuario;
	private JTextField txtHoraPrestamo;
	private JTextField txtFechaPrestamo;
	private JScrollPane scrollPane;
	private JTable tbDetRecibo;
	private static JTextField txtHoraRecibo;
	public int idDetPrestamo[] = new int[3];
	public int idDetDevolucion[] = new int[3];
	static JFrame ventanaPadre;
	private int idUsuario;
	private String tipoUsuario;
	private String HoraMaxEntrega;
	private String FechaMaxEntrega;
	private int idDevolucion;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRealizarReciboDeMateriales frame = new VentanaRealizarReciboDeMateriales(ventanaPadre);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaRealizarReciboDeMateriales(final JFrame ventanaPadre) {
		
		super(ventanaPadre);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRealizarReciboDeMateriales.class.getResource("/imagenes/Book.png")));
		setTitle("Recibo de Materiales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 782, 377);
		contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("Codigo ");
		label.setBounds(46, 19, 74, 19);
		contentPane.add(label);
		
		txtIDRecibo = new JTextField();
		txtIDRecibo.setEditable(false);
		txtIDRecibo.setColumns(10);
		txtIDRecibo.setBounds(90, 18, 77, 20);
		contentPane.add(txtIDRecibo);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BuscarRecibo nuevoBuscarRecibo = new BuscarRecibo(VentanaRealizarReciboDeMateriales.this);
				nuevoBuscarRecibo.setVisible(true);
				
			
			}
		});
		button.setIcon(new ImageIcon(VentanaRealizarReciboDeMateriales.class.getResource("/imagenes/Find.png")));
		button.setBounds(177, 11, 30, 26);
		contentPane.add(button);
		
		JLabel lblPrestamo = new JLabel("Prestamo");
		lblPrestamo.setBounds(32, 52, 88, 14);
		contentPane.add(lblPrestamo);
		
		txtIDprestamo = new JTextField();
		txtIDprestamo.setEditable(false);
		txtIDprestamo.setColumns(10);
		txtIDprestamo.setBounds(90, 49, 77, 20);
		contentPane.add(txtIDprestamo);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BuscarPrestamo nuevoBuscarPrestamo = new BuscarPrestamo(VentanaRealizarReciboDeMateriales.this);
				nuevoBuscarPrestamo.setVisible(true);
	
			}
		});
		button_1.setIcon(new ImageIcon(VentanaRealizarReciboDeMateriales.class.getResource("/imagenes/Find.png")));
		button_1.setBounds(177, 43, 30, 26);
		contentPane.add(button_1);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setEditable(false);
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(267, 48, 351, 20);
		contentPane.add(txtNombreUsuario);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tbDetRecibo.isEditing() && tbDetRecibo.editCellAt(tbDetRecibo.getSelectedRow(), 4))
				{
					tbDetRecibo.getCellEditor().stopCellEditing();
				}
					if(txtIDRecibo.getText().equals("")) //si el txt id guardar nuevo
					{	
						if(txtIDprestamo.getText().isEmpty() || tbDetRecibo.getRowCount() == 1 &&  tbDetRecibo.getValueAt(0,0) == null)
						{
							 JOptionPane.showMessageDialog(rootPane, "Llene todos los campos antes de Guardar el Recibo");
						} else {
								Connection conexion = Conexion.Conectar();
							try {	
									String sql = "UPDATE devolucion SET  idPrestamo=?, hora =?, fecha =? where idDevolucion =?";
									PreparedStatement sentencia = conexion.prepareStatement(sql);	
									VentanaPrestamosDeMateriales nuevo = new VentanaPrestamosDeMateriales(ventanaPadre);
									
									sentencia.setInt(4, Integer.parseInt(txtIDRecibo.getText()));	
									sentencia.setString(1, txtIDprestamo.getText());
									sentencia.setString(2 ,txtHoraRecibo.getText());
									sentencia.setString(3, txtFechaRecibo.getText());
									sentencia.executeUpdate();
									
									for(int i = 0; i <  tbDetRecibo.getRowCount(); i++)
									{
										String sql2 = String.format("UPDATE det_devolucion SET idMaterial= '%s', condicionFisica= '%s' where idDet_Devolucion = '%s'", Integer.parseInt(tbDetRecibo.getValueAt(i, 0).toString()), tbDetRecibo.getValueAt(i, 4),  idDetDevolucion[i]);  
										
										PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
										sentencia2.execute();
		
										if( tbDetRecibo.getValueAt(i, 5) == null){
											
											nuevo.actualizarInventario( Integer.parseInt(tbDetRecibo.getValueAt(i, 0).toString()), tbDetRecibo.getValueAt(i, 4).toString(), "Prestado");
										}
										else if(tbDetRecibo.getValueAt(i, 5).toString().equals("true"))
										{
											nuevo.actualizarInventario( Integer.parseInt(tbDetRecibo.getValueAt(i, 0).toString()), tbDetRecibo.getValueAt(i, 4).toString(), "Devuelto");
										}
									}
								
								  } catch  (SQLException e) {
								  JOptionPane.showMessageDialog(rootPane, e.getMessage());
								  }
				
							JOptionPane.showMessageDialog(rootPane, "Datos Actualizados...");
							 Limpiar cancelar = new Limpiar();
							 cancelar.limpiarCampos( VentanaRealizarReciboDeMateriales.this.getContentPane() );
							 limpiarTabla();
					}
				   } else{
					   JOptionPane.showMessageDialog(rootPane, "ID Recibo no asignado \nPulse guardar para crear un nuevo registro...");
				   }
				}

		});
		btnActualizar.setIcon(new ImageIcon(VentanaRealizarReciboDeMateriales.class.getResource("/imagenes/NewDocumentHS.png")));
		btnActualizar.setBounds(628, 49, 124, 23);
		contentPane.add(btnActualizar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tbDetRecibo.isEditing() && tbDetRecibo.editCellAt(tbDetRecibo.getSelectedRow(), 4))
				{
					tbDetRecibo.getCellEditor().stopCellEditing();
				}
					if(txtIDRecibo.getText().equals("")) //si el txt id guardar nuevo
					{	
					if(txtIDprestamo.getText().isEmpty() || tbDetRecibo.getRowCount() == 1 &&  tbDetRecibo.getValueAt(0,0) == null)
					{
						 JOptionPane.showMessageDialog(rootPane, "Llene todos los campos antes de Guardar el Recibo");
					} else {	
							try {
								
								int idPrestamo= Integer.parseInt(txtIDprestamo.getText());
								String horaRecibo = txtHoraRecibo.getText();
								String fechaRecibo = txtFechaRecibo.getText();
								
							    Recibo nuevoRecibo = new Recibo(idPrestamo, horaRecibo, fechaRecibo);
							    nuevoRecibo.guardarDatosEncabezadoRecibo();
							   idDevolucion = nuevoRecibo.getiDdevolucion();
							    insertarDatosDetalle(idDevolucion);
							    
							}
							catch(NumberFormatException excepcion)
							{
							   JOptionPane.showMessageDialog(VentanaRealizarReciboDeMateriales.this, "No se digito un usuario");
							} 
							catch (ClassNotFoundException e1) 
							{
						         e1.printStackTrace();
							} 
							catch (SQLException e1)
							{
								e1.printStackTrace();
							} 
			
							 JOptionPane.showMessageDialog(rootPane, "Datos Guardados Correctamente e Inventario Actualizado");
							 Limpiar cancelar = new Limpiar();
							 cancelar.limpiarCampos( VentanaRealizarReciboDeMateriales.this.getContentPane() );
							 limpiarTabla();
					}
				} else 
					JOptionPane.showMessageDialog(rootPane, "Pulse Boton Actualizar para Modificar Prestamo ");
			}
		});
		btnGuardar.setIcon(new ImageIcon(VentanaRealizarReciboDeMateriales.class.getResource("/imagenes/saveHS.png")));
		btnGuardar.setBounds(629, 18, 123, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Limpiar cancelar = new Limpiar();
				cancelar.limpiarCampos( VentanaRealizarReciboDeMateriales.this.getContentPane() );
				limpiarTabla();
				
				Date fechaActual = new Date();
			    DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.SHORT);
			    txtFechaRecibo.setText(formatoFecha.format(fechaActual));
			    Date horaActual = new Date();
				DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.SHORT);
				txtHoraRecibo.setText(formatoHora.format(horaActual));
			}
		});
		btnCancelar.setIcon(new ImageIcon(VentanaRealizarReciboDeMateriales.class.getResource("/imagenes/DeleteHS.png")));
		btnCancelar.setBounds(628, 75, 124, 23);
		contentPane.add(btnCancelar);
		
		txtFechaRecibo = new JTextField();
		txtFechaRecibo.setEditable(false);
		txtFechaRecibo.setColumns(10);
		txtFechaRecibo.setBounds(441, 19, 60, 20);
		contentPane.add(txtFechaRecibo);
		//---------------------------Asignarle la fecha actual al txt----------------------------------//
		Date fechaActual = new Date();
	    DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.SHORT);
	    txtFechaRecibo.setText(formatoFecha.format(fechaActual));
		
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(395, 21, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel label_3 = new JLabel("Tipo Usuario");
		label_3.setBounds(12, 84, 98, 14);
		contentPane.add(label_3);
		
		txtTipoUsuario = new JTextField();
		txtTipoUsuario.setEditable(false);
		txtTipoUsuario.setColumns(10);
		txtTipoUsuario.setBounds(90, 80, 117, 20);
		contentPane.add(txtTipoUsuario);
		
		txtHoraPrestamo = new JTextField();
		txtHoraPrestamo.setEditable(false);
		txtHoraPrestamo.setColumns(10);
		txtHoraPrestamo.setBounds(511, 79, 107, 20);
		contentPane.add(txtHoraPrestamo);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(217, 52, 60, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblFechaPrestamo = new JLabel("Fecha Prestamo");
		lblFechaPrestamo.setBounds(217, 81, 101, 14);
		contentPane.add(lblFechaPrestamo);
		
		txtFechaPrestamo = new JTextField();
		txtFechaPrestamo.setEditable(false);
		txtFechaPrestamo.setColumns(10);
		txtFechaPrestamo.setBounds(313, 76, 88, 20);
		contentPane.add(txtFechaPrestamo);
		
		JLabel lblNewLabel_1 = new JLabel("Hora Prestamo");
		lblNewLabel_1.setBounds(411, 78, 90, 14);
		contentPane.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(21, 136, 731, 139);
		contentPane.add(scrollPane);
		
		tbDetRecibo = new JTable();
		tbDetRecibo.setModel(new javax.swing.table.DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, Boolean.FALSE},
			},
			new String[] {
				"ID Material", "Descripcion", "Tipo Material", "Ubicacion Fisica", "Condicion Fisica", "Material Recibido"
			}
		) {
			Class[] types = new Class[] {
				java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return types [columnIndex];
			}
		});
		tbDetRecibo.getColumnModel().getColumn(3).setPreferredWidth(93);
		tbDetRecibo.getColumnModel().getColumn(4).setPreferredWidth(88);
		tbDetRecibo.getColumnModel().getColumn(5).setPreferredWidth(102);
		scrollPane.setViewportView(tbDetRecibo);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(511, 21, 46, 14);
		contentPane.add(lblHora);
		
		txtHoraRecibo = new JTextField();
		txtHoraRecibo.setEditable(false);
		txtHoraRecibo.setColumns(10);
		txtHoraRecibo.setBounds(558, 19, 60, 20);
		contentPane.add(txtHoraRecibo);
		
		//-----------Asignarle la hora actual al form
		Date horaActual = new Date();
		DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.SHORT);
		txtHoraRecibo.setText(formatoHora.format(horaActual));
	}
	
	

	//-------------------Para Limpiar la tabla -------------------------//
	
    public void limpiarTabla(){
			
			DefaultTableModel modelo =(DefaultTableModel) tbDetRecibo.getModel();
			int filasTabla = tbDetRecibo.getRowCount();
			
			for(int i =0; filasTabla >i; i++ ){
				
				modelo.removeRow(0);
			}
			
			modelo.addRow(new Object[]{"","","","","", false});
	
	}
		

	public void cargarDatosPrestamo(int idPrestamoTomado) {
		
		
		
		txtIDprestamo.setText(idPrestamoTomado +"");
	
		try {
				
				Connection conexion = Conexion.Conectar(); 
				Statement sentencia = conexion.createStatement();
				String sql = "SELECT * from Prestamo where idPrestamo =" + idPrestamoTomado;
				ResultSet resultados = sentencia.executeQuery(sql);
				
				resultados.next() ;
					
					idUsuario = Integer.parseInt(resultados.getObject(2).toString());
				
					HoraMaxEntrega = resultados.getObject(4).toString();
					FechaMaxEntrega = resultados.getObject(5).toString();
					txtHoraPrestamo.setText(resultados.getObject(6).toString());
					txtFechaPrestamo.setText(resultados.getObject(7).toString()) ;
				
					cargarDatosUsuario(idUsuario);
			
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		
		DefaultTableModel modelo =(DefaultTableModel) tbDetRecibo.getModel();
		modelo.removeRow(0);
		
			try {
			
			    Connection conexion = Conexion.Conectar(); 
			    Statement sentencia = conexion.createStatement();
				String sql = "SELECT * FROM det_prestamo where idprestamo = " + idPrestamoTomado;
				ResultSet resultados = sentencia.executeQuery(sql);
			    
					int i= 0;
				    while(resultados.next())
				    {
				    	modelo.addRow(new Object[]{resultados.getInt("idMaterial"),"", "","", resultados.getString("condicionFisica")});
				    	idDetPrestamo[i] = resultados.getInt("idDet_Prestamo"); 
				       
				        i++;
				    }
			    
				} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				}

			  try {
				  
					  for(int i=1; i <= tbDetRecibo.getRowCount(); i++)
				     {
							String idInventario = tbDetRecibo.getValueAt(i-1, 0).toString();
							Connection conexion = Conexion.Conectar();  
							
							Statement sentencia = conexion.createStatement();
							String sql = String.format("SELECT inventario.idInventario, material.titulo, material.tipoMaterial, inventario.ubicacionfisica FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.idInventario = '%s' and inventario.estado like 'Prestado'", idInventario);
							ResultSet conjuntoResultados = sentencia.executeQuery(sql);
						
							if(conjuntoResultados.first())
							{

								conjuntoResultados.beforeFirst();
								while( conjuntoResultados.next() )
								{
									String descripcion = conjuntoResultados.getObject(2).toString(); 
									tbDetRecibo.setValueAt(descripcion,  i-1, 1);
									String tipoMaterial = conjuntoResultados.getObject(3).toString(); 
									tbDetRecibo.setValueAt(tipoMaterial, i-1, 2);
									String ubicacionFisica = conjuntoResultados.getObject(4).toString(); 
									tbDetRecibo.setValueAt(ubicacionFisica, i-1, 3);
								}
								
							}else {
								
								tbDetRecibo.setValueAt("", i-1, 0);
								tbDetRecibo.setValueAt("", i-1, 4);
								
								
							} 
							
					}
	
					  
				for(int i=1; i <= tbDetRecibo.getRowCount(); i++)
				{  
					if(tbDetRecibo.getValueAt(i-1, 0).toString().equals("")){
					
						modelo.removeRow(i-1);
						
					}
				}

				} catch (SQLException e) {
					
					JOptionPane.showMessageDialog(this, e.getMessage());
				 }		
			
	
			}

//----------------------------cargar nombre y tipo de usuario de la base de datos
	
	private void cargarDatosUsuario(int idUsuario) {
		
			try {
				
				Connection conexion = Conexion.Conectar(); 
				Statement sentencia = conexion.createStatement();
				String sql = "SELECT nombre, apellido, tipoUsuario  FROM persona  WHERE idpersona = "+ idUsuario ;
				ResultSet resultados = sentencia.executeQuery(sql);
				
				resultados.next() ;
					
					txtNombreUsuario.setText(resultados.getObject(1).toString()+ " " +resultados.getObject(2).toString());
					tipoUsuario = resultados.getObject(3).toString();
					txtTipoUsuario.setText(tipoUsuario) ;
			
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		
	}
	
	//------------------Para validar que los materilaes fueron devueltos a tiempo-------------------//	
	
	public void validarFechaHoraEntrega()
	{
		
		if(tipoUsuario.equals("Estudiante") || tipoUsuario.equals("Empleado")) 
		{
			
			try {
				 //Formataer la fecha y hora del sistema
	        	Date fechaActual = new Date();
				SimpleDateFormat formatearFecha = new SimpleDateFormat("MM/dd/yy");
				SimpleDateFormat formatearHora = new SimpleDateFormat("hh:mm");
				String horaActualFormateada = formatearHora.format(fechaActual);
				String fechaActualFormateada = formatearFecha.format(fechaActual);
				
				//Formatear la fecha maxima de entrega
				SimpleDateFormat formatearF = new SimpleDateFormat("MM/dd/yy");	
				Date FechaMax = formatearF.parse(FechaMaxEntrega) ;
				Date FechaActual = formatearF.parse(fechaActualFormateada);
				
				//formatear la hora maxima de entrega
				SimpleDateFormat formatearH = new SimpleDateFormat("hh:mm");
				Date HoraMax = formatearH.parse(HoraMaxEntrega) ;
				Date HoraActual = formatearH.parse(horaActualFormateada);
				
				
				
				if(FechaMax.equals(FechaActual) && HoraActual.before(HoraMax))
				{
					JOptionPane.showMessageDialog(rootPane, "Los Materiales han sido devueltos a Tiempo");
					
				} else if(FechaActual.equals(FechaMax) && HoraMax.before(HoraActual))
				{
					
					JOptionPane.showMessageDialog(rootPane, "Los Materiales No han sido devueltos a Tiempo.... \n Debieron ser devueltos en la Hora : " + HoraMaxEntrega);
				
				}else if(FechaActual.equals(FechaMax) && HoraActual.equals(HoraMax))
				{
					
					JOptionPane.showMessageDialog(rootPane, "Los Materiales han sido devueltos Justo a Tiempo.... ");
				} else{
					
					JOptionPane.showMessageDialog(rootPane, "Los Materiales No han sido devueltos a Tiempo.... \nDebieron ser devueltos a \n Hora : " + HoraMaxEntrega + "\n Fecha : " + FechaMaxEntrega);
				}
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}

        }else if(tipoUsuario.equals("Profesor"))
          {
			
        	try{
        		
                //Formataer la fecha y hora del sistema
	        	Date fechaActual = new Date();
				SimpleDateFormat formatearFecha = new SimpleDateFormat("MM/dd/yy");
				SimpleDateFormat formatearHora = new SimpleDateFormat("hh:mm");
				String horaActualFormateada = formatearHora.format(fechaActual);
				String fechaActualFormateada = formatearFecha.format(fechaActual);
				
				//Formatear la fecha maxima de entrega
				SimpleDateFormat formatearF = new SimpleDateFormat("MM/dd/yy");	
				Date FechaMax = formatearF.parse(FechaMaxEntrega) ;
				Date FechaActual = formatearF.parse(fechaActualFormateada);
				
				//formatear la hora maxima de entrega
				SimpleDateFormat formatearH = new SimpleDateFormat("hh:mm");
				Date HoraMax = formatearH.parse(HoraMaxEntrega) ;
				Date HoraActual = formatearH.parse(horaActualFormateada);
				
				if(FechaActual.before(FechaMax))
				{
					
					JOptionPane.showMessageDialog(rootPane, "Los Materiales han sido devueltos a Tiempo");
					
				} else if(FechaMax.before(FechaActual))
				{
					
					JOptionPane.showMessageDialog(rootPane, "Los Materiales No han sido devueltos a Tiempo.... \nDebieron ser devueltos en \nFecha : " + FechaMaxEntrega);
				
				}else if(FechaActual.equals(FechaMax) && HoraMax.after(HoraActual))
				{
					
					JOptionPane.showMessageDialog(rootPane, "Los Materiales No han sido devueltos a Tiempo.... \nDebieron ser devueltos a \nHora : " + HoraMaxEntrega);
				}	
		
        	} catch (ParseException e) {
				e.printStackTrace();
			}

	     }			
    }
	
	//-------------------------Para insertar el detalle del prestamo en la base de de datos---------------------//
	

	public void insertarDatosDetalle(int idDevolucion) {
	
			
			VentanaPrestamosDeMateriales nuevo = new VentanaPrestamosDeMateriales(ventanaPadre);
			Connection cn = Conexion.Conectar();
			

			try {	

				for(int i=1; i <= tbDetRecibo.getRowCount(); i++)
				{
					
					
					
					if( tbDetRecibo.getValueAt(i-1, 5) == null){
						
					
						JOptionPane.showMessageDialog(rootPane, "Faltan Materiales Por devolver");
					}
					else if(tbDetRecibo.getValueAt(i-1, 5).toString().equals("true"))
					{
	
						
						int idMaterial = Integer.parseInt(tbDetRecibo.getValueAt(i-1,0).toString());
						String CondicionFisica = tbDetRecibo.getValueAt(i-1, 4).toString();
				
						String sql = "INSERT INTO det_devolucion(idDevolucion, idMaterial, condicionFisica) VALUES ('"+ idDevolucion +"','"+ idMaterial + "','" + CondicionFisica +"')";
						PreparedStatement instruccion = cn.prepareStatement(sql);
						instruccion.execute(); 
						nuevo.actualizarInventario(idMaterial, CondicionFisica, "Devuelto");
			      }
					
				}
				} catch (SQLException e) {
			
						e.printStackTrace();
				}
			
			}

	//----------Cargar Datos del recibo --------------------------------//
	
	public void cargarDatosRecibo(int idReciboTomado) {
		
		//mostrar datos de la devolucion
		txtIDRecibo.setText(idReciboTomado +"");
		
		try {
			
				Connection conexion = Conexion.Conectar(); 
				Statement sentencia = conexion.createStatement();
				String sql = "SELECT * from devolucion where idDevolucion=" + idReciboTomado;
				ResultSet resultados = sentencia.executeQuery(sql);
				
				resultados.next();{
					
					txtIDprestamo.setText(resultados.getObject(2).toString());
					txtHoraRecibo.setText(resultados.getObject(3).toString());
					txtFechaRecibo.setText(resultados.getObject(4).toString());
				}
				
				int idPrestamo = Integer.parseInt(txtIDprestamo.getText().toString());
				
				Statement sentencia2 = conexion.createStatement();
				String sql2 = "SELECT idPersona, horaPrestamo, fechaPrestamo From prestamo where idPrestamo=" + idPrestamo;
				ResultSet resultados2 = sentencia.executeQuery(sql);	
				resultados2 = sentencia2.executeQuery(sql2);
				
				resultados2.next();	
					
					int idPeronaTomado = Integer.parseInt(resultados2.getObject(1).toString());
					txtHoraPrestamo.setText(resultados2.getObject(2).toString());
					txtFechaPrestamo.setText(resultados2.getObject(3).toString());
					cargarDatosUsuario(idPeronaTomado);
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		
		//Mostrar el detalle
		
		try {
				
			    DefaultTableModel modelo = (DefaultTableModel) tbDetRecibo.getModel();
			    Connection conexion = Conexion.Conectar(); 
			    Statement sentencia = conexion.createStatement();
				String sql = "SELECT * FROM det_devolucion where idDevolucion = " + idReciboTomado;
				ResultSet resultados = sentencia.executeQuery(sql);
			    modelo.removeRow(0);

			    while(resultados.next()){
			    
			    	modelo.addRow(new Object[]{resultados.getInt("idMaterial"),"", "","", resultados.getString("condicionFisica")});
			        for(int i =0; i < 1 ; i++){
			        	idDetDevolucion[i] = resultados.getInt("idDet_Devolucion");
			        
			        }
			        
			    }
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
			
	
		try {
	
				 for(int i=0; i < tbDetRecibo.getRowCount(); i++)
				  {
						String idInventario = tbDetRecibo.getValueAt(i, 0).toString();
						Connection conexion = Conexion.Conectar();  
						
						Statement sentencia = conexion.createStatement();
						String sql = String.format("SELECT inventario.idInventario, material.titulo, material.tipoMaterial, inventario.ubicacionfisica FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.idInventario = '%s'", idInventario);
						ResultSet conjuntoResultados = sentencia.executeQuery(sql);
						
						while( conjuntoResultados.next() )
						{
							String descripcion = conjuntoResultados.getObject(2).toString(); 
							tbDetRecibo.setValueAt(descripcion, i, 1);
							
							String autor = conjuntoResultados.getObject(3).toString(); 
							tbDetRecibo.setValueAt(autor, i, 2);
							
							String tipoMaterial = conjuntoResultados.getObject(4).toString(); 
							tbDetRecibo.setValueAt(tipoMaterial, i, 3);
				    
						}
								
				     }	
				  
					  } catch (SQLException e) {
							JOptionPane.showMessageDialog(this, e.getMessage());
					  }		
			
		}

}
