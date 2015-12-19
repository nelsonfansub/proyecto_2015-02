import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;
import javax.swing.DefaultComboBoxModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Clases.Conexion;
import Clases.Limpiar;

public class VentanaAudioVideo extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtAnioPublicacion;
	private JTextField txtIdMaterial;
	private JTextField txtDirector;
	private JTextField txtDuracion;
	private JTable table;
	JComboBox comboBoxClasificacionTematica;
	private JComboBox comboBoxTipoMaterial;
	private JComboBox  comboBoxMedio;
	private JComboBox  comboBoxFormato;
	private JComboBox  comboBoxTipo;
	private String idMaterial;
	private String director;
	private String duracion;
	private String medio;
	private String formato;
	private String tipo;
	private String tipoMaterial;
	private String titulo;
	private String anioPublicacion;
	private String clasificacionTematica;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAudioVideo frame = new VentanaAudioVideo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaAudioVideo() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrestamosDeMateriales.class.getResource("/imagenes/Book.png")));
		
		setTitle("Audio-Video");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 498, 463);
		contentPane = new CambiarImagenDelFondo("imagenes/fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setBounds(12, 206, 133, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblAoDePublicacion = new JLabel("Anio de Publicacion:");
		lblAoDePublicacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAoDePublicacion.setBounds(12, 234, 133, 14);
		contentPane.add(lblAoDePublicacion);
		
		JLabel lblTipoDeMaterial = new JLabel("Tipo de Material:");
		lblTipoDeMaterial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeMaterial.setBounds(12, 262, 133, 14);
		contentPane.add(lblTipoDeMaterial);
		
		JLabel lblClasificacionTematica = new JLabel("Clasificacion Tematica:");
		lblClasificacionTematica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasificacionTematica.setBounds(12, 290, 133, 14);
		contentPane.add(lblClasificacionTematica);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(53, 182, 90, 14);
		contentPane.add(lblTipo);
		
		JLabel lblFormato = new JLabel("Formato:");
		lblFormato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFormato.setBounds(53, 151, 90, 14);
		contentPane.add(lblFormato);
		
		JLabel lblMedio = new JLabel("Medio:");
		lblMedio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedio.setBounds(53, 123, 90, 14);
		contentPane.add(lblMedio);
		
		JLabel lblDuracion = new JLabel("Duracion:");
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuracion.setBounds(53, 95, 90, 14);
		contentPane.add(lblDuracion);
		
		JLabel lblDirector = new JLabel("Director:");
		lblDirector.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirector.setBounds(53, 70, 90, 14);
		contentPane.add(lblDirector);
		
		JLabel lblIdAudiovideo = new JLabel("Id Audio-Video:");
		lblIdAudiovideo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdAudiovideo.setBounds(53, 42, 90, 14);
		contentPane.add(lblIdAudiovideo);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(148, 206, 142, 20);
		contentPane.add(txtTitulo);
		
		txtAnioPublicacion = new JTextField();
		txtAnioPublicacion.setColumns(10);
		txtAnioPublicacion.setBounds(148, 234, 142, 20);
		contentPane.add(txtAnioPublicacion);
		
		txtIdMaterial = new JTextField();
		txtIdMaterial.setEditable(false);
		txtIdMaterial.setColumns(10);
		txtIdMaterial.setBounds(148, 39, 90, 20);
		contentPane.add(txtIdMaterial);
		
		txtDirector = new JTextField();
		txtDirector.setColumns(10);
		txtDirector.setBounds(148, 67, 142, 20);
		contentPane.add(txtDirector);
		
		txtDuracion = new JTextField();
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(148, 92, 142, 20);
		contentPane.add(txtDuracion);
		
		JButton btnBuscarAudioVideo = new JButton("");
		btnBuscarAudioVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BuscarMaterialSolicitado BuscarEnFormulario = new BuscarMaterialSolicitado(VentanaAudioVideo.this);
				BuscarEnFormulario.setVisible(true);
				
			}
		});
		btnBuscarAudioVideo.setIcon(new ImageIcon(VentanaAudioVideo.class.getResource("/imagenes/Find.png")));
		btnBuscarAudioVideo.setBounds(241, 31, 49, 28);
		contentPane.add(btnBuscarAudioVideo);
		
		JButton btnGuardarAudioVideo = new JButton("Guardar");
		btnGuardarAudioVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id;
				asignarValores();
				
				if(!txtDuracion.getText().equals(""))
				{
					id = guardarMaterial();				
					guardarAudioVideo(id);
				}
				else
					JOptionPane.showMessageDialog(VentanaAudioVideo.this, "El campo duracion no puede estar vacio!");
				
			}
		});
		btnGuardarAudioVideo.setIcon(new ImageIcon(VentanaAudioVideo.class.getResource("/imagenes/saveHS.png")));
		btnGuardarAudioVideo.setBounds(316, 70, 111, 28);
		contentPane.add(btnGuardarAudioVideo);
		
		JButton btnCancelarAudioVideo = new JButton("Cancelar");
		btnCancelarAudioVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				dispose();
				
			}
		});
		btnCancelarAudioVideo.setIcon(new ImageIcon(VentanaAudioVideo.class.getResource("/imagenes/DeleteHS.png")));
		btnCancelarAudioVideo.setBounds(317, 147, 110, 28);
		contentPane.add(btnCancelarAudioVideo);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				asignarValores();
				
				if(!txtIdMaterial.getText().equals(""))
				{
					int idPersonaGenerada = Integer.parseInt(idMaterial);
					
					ActualizarMaterial(idPersonaGenerada);
				
					actualizarAudioVideo(idPersonaGenerada);
				}
				else
					JOptionPane.showMessageDialog(VentanaAudioVideo.this, "Primero debes buscar para actualizar!");
				   
			}
		
		});
		btnActualizar.setIcon(new ImageIcon(VentanaAudioVideo.class.getResource("/imagenes/NewDocumentHS.png")));
		btnActualizar.setBounds(316, 105, 111, 28);
		contentPane.add(btnActualizar);
		
		comboBoxClasificacionTematica = new JComboBox();
		comboBoxClasificacionTematica.setBounds(148, 290, 142, 20);
		llenarComboBox();
		contentPane.add(comboBoxClasificacionTematica);
		
		comboBoxTipoMaterial = new JComboBox();
		comboBoxTipoMaterial.setModel(new DefaultComboBoxModel(new String[] {"Libro", "AudioVideo", "ImagenesOtros"}));
		comboBoxTipoMaterial.setBounds(148, 262, 142, 20);
		contentPane.add(comboBoxTipoMaterial);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Audio", "Video"}));
		comboBoxTipo.setBounds(148, 179, 142, 20);
		contentPane.add(comboBoxTipo);
		
		comboBoxFormato = new JComboBox();
		comboBoxFormato.setModel(new DefaultComboBoxModel(new String[] {"MP3","MP4","AVI","MKV","flv","WMA","3GP"}));
		comboBoxFormato.setBounds(148, 148, 142, 20);
		contentPane.add(comboBoxFormato);
		
		comboBoxMedio = new JComboBox();
		comboBoxMedio.setModel(new DefaultComboBoxModel(new String[] {"CD", "DVD","Blu-ray"}));
		comboBoxMedio.setBounds(148, 117, 142, 20);
		contentPane.add(comboBoxMedio);
		
		table = new JTable();
		table.setBorder(new MatteBorder(4, 3, 4, 3, (Color) new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {{null, null, null, null, null},},
			new String[] {"Codigo", "Titulo", "Director", "Duracion", "Tipo"}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(12, 400, 641, -138);
		contentPane.add(table);
		
	
	}
	
	public void asignarValores()
	{
		idMaterial = txtIdMaterial.getText();
		duracion = txtDuracion.getText();
		director = txtDirector.getText();	
		titulo = txtTitulo.getText();
		anioPublicacion = txtAnioPublicacion.getText();
		
		medio = comboBoxMedio.getSelectedItem().toString();		
		formato = comboBoxFormato.getSelectedItem().toString();
		tipo = comboBoxTipo.getSelectedItem().toString();
		tipoMaterial = comboBoxTipoMaterial.getSelectedItem().toString();		
		clasificacionTematica = comboBoxClasificacionTematica.getSelectedItem().toString();
		
		clasificacionTematica = ""+VerIdComboBox();
		
	}
	
	
	public void llenarComboBox()
	{
		
		try {
			
			Connection conexion = Conexion.Conectar(); 
			
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT  idClasificacionTematica, nombre FROM clasificacionTematica";
			
			ResultSet rs = sentencia.executeQuery(sql);

			comboBoxClasificacionTematica.removeAllItems();
			
			while(rs.next())
			{
				comboBoxClasificacionTematica.addItem(rs.getString(2));
			}
			 
			String seleccion = comboBoxClasificacionTematica.getSelectedItem().toString(); 
			int id = 0;
			
			while(rs.next())
			{
				if(seleccion == rs.getString(2))
					id = Integer.parseInt(rs.getString(1));
				 
				JOptionPane.showMessageDialog(this, "El id seleccionado es: "+id);
			}
			
					
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
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
	public void cargarDatosUsuario(String idTomado)
	{
	JOptionPane.showMessageDialog(null, "1) Director es: "+director);
		try {
			int id = Integer.parseInt(idTomado);
			Connection conexion = Conexion.Conectar(); 
			
			Statement sentencia = conexion.createStatement();
			String sql = String.format("SELECT audiovideo.director, audiovideo.duracion, "
					+ "audiovideo.medio ,audiovideo.formato, audiovideo.tipo, material.titulo, material.anioPublicacion,"
					+ "material.tipoMaterial, material.clasificacionTematica "
					+ "FROM audioVideo, material WHERE material.idMaterial = audioVideo.idAudioVideo "
					+ "AND  idAudioVideo = ", id);
			
			ResultSet resultados = sentencia.executeQuery(sql);
			director = resultados.getString("director");
			this.txtDirector.setText(director);
			JOptionPane.showMessageDialog(null, "Director es: "+director);
			JOptionPane.showMessageDialog(null, "2) Director es: "+director.toString());
			txtDuracion.setText(resultados.getObject(2).toString());
			txtDirector.setText(resultados.getObject(3).toString());
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}
		
	public void cargarDatosMaterial(String idTomado)
	{
		txtIdMaterial.setText(idTomado);
		try {
					
					Connection conexion = Conexion.Conectar(); 
					
					Statement sentencia = conexion.createStatement();
					String sql = String.format("SELECT audiovideo.director, audiovideo.duracion, "
							+ "audiovideo.medio ,audiovideo.formato, audiovideo.tipo, material.titulo, material.anioPublicacion,"
							+ "material.tipoMaterial, material.clasificacionTematica "
							+ "FROM audioVideo, material WHERE material.idMaterial = audioVideo.idAudioVideo "
							+ "AND  idAudioVideo = "+ idTomado);
					
					ResultSet resultados = sentencia.executeQuery(sql);
					
					while(resultados.next())
					{
					    txtDirector.setText(resultados.getString(1));						
						txtDuracion.setText(resultados.getObject(2).toString());						
						comboBoxMedio.setSelectedItem(resultados.getObject(3));
						comboBoxFormato.setSelectedItem(resultados.getObject(4));
						comboBoxTipo.setSelectedItem(resultados.getObject(5));
						txtTitulo.setText(resultados.getString(6));
						txtAnioPublicacion.setText(resultados.getString(7));
						comboBoxTipoMaterial.setSelectedItem(resultados.getObject(8));
						comboBoxClasificacionTematica.setSelectedItem(resultados.getObject(9));
					}
						
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
				
		
	}
	
	public int guardarMaterial()
	{	
				
		try {
			Connection conexion = Conexion.Conectar();
			int idPersonaGenerada = 0;
			String sql = " insert into material (titulo, anioPublicacion, tipoMaterial, clasificacionTematica)"
			        + " values (?, ?, ?, ?)";
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, titulo);
			sentencia.setString(2, anioPublicacion);
			sentencia.setString(3, tipoMaterial);
			sentencia.setString(4, clasificacionTematica);
			
			
			sentencia.execute();

			JOptionPane.showMessageDialog(VentanaAudioVideo.this, "Operacion Exitosa!");
					
			Statement st;
			ResultSet rs = null;										
			st = conexion.createStatement();
			
			rs = st.executeQuery("select MAX(idMaterial) from Material");
			
		
			while(rs.next())
			{
				idPersonaGenerada = rs.getInt(1);
			}
			
			JOptionPane.showMessageDialog(VentanaAudioVideo.this, "Id de Audio Vide oGenerado");
		
		    conexion.close();
			
			return idPersonaGenerada;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			String cadena = e.getMessage();			
			JOptionPane.showMessageDialog(VentanaAudioVideo.this, cadena);
			return 0;
		}
		
	}

	public void guardarAudioVideo(int idAudioVideo)
	{
		try {
			Connection conexion = Conexion.Conectar();
			
			String sql = " insert into AudioVideo (idAudioVideo, director, duracion, medio, formato, tipo)"
			        + " values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement sentencia =  conexion.prepareStatement(sql);
			sentencia.setInt(1, idAudioVideo);
			sentencia.setString(2, director);
			sentencia.setString(3, duracion);
			sentencia.setString(4, medio);
			sentencia.setString(5, formato);
			sentencia.setString(6, tipo);
			sentencia.execute();
			 JOptionPane.showMessageDialog(VentanaAudioVideo.this, "AudioVideo Guardado");  
		     conexion.close();
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			String error = e.getMessage();
			JOptionPane.showMessageDialog(VentanaAudioVideo.this, error); 
		}
		
		
	}

	public int ActualizarMaterial(int idMaterial)
	{	
				
		try {
			
			Connection conexion = Conexion.Conectar();
			
			String sql = "update material set titulo = ?, anioPublicacion = ?, tipoMaterial = ?, clasificacionTematica = ?"
					+ " where idMaterial = ?";
			
			PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sql);
	        sentencia.setString(1, titulo);
			sentencia.setString(2, anioPublicacion);
			sentencia.setString(3, tipoMaterial);
			sentencia.setString(4, clasificacionTematica);
			sentencia.setInt(5, idMaterial);		
			sentencia.executeUpdate();
			JOptionPane.showMessageDialog(VentanaAudioVideo.this, "Operacion Realiazada con exito!");
			
		    conexion.close();
			
		    Limpiar.limpiarCampos(VentanaAudioVideo.this.getContentPane());
		
			return 0;
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			String cadena = e.getMessage();			
			JOptionPane.showMessageDialog(VentanaAudioVideo.this, cadena);
			return 0;
		}
		
	}


	public void actualizarAudioVideo(int idPersonaGenerada)
	{
		
	try {		
		Connection conexion = Conexion.Conectar();
			String Comando = "update AudioVideo set director = ?, duracion = ?, medio = ?, formato = ?, tipo = ? where idAudioVideo = ?";
						
			PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(Comando);
			sentencia.setString(1, director);
			sentencia.setString(2, duracion);
			sentencia.setString(3, medio);
			sentencia.setString(4, formato);
			sentencia.setString(5, tipo);
			sentencia.setInt(6, idPersonaGenerada);	
			
						
			sentencia.executeUpdate();
			JOptionPane.showMessageDialog(VentanaAudioVideo.this, "Operacion Realiazada con exito!");
			
		    conexion.close();
			
		    Limpiar.limpiarCampos(VentanaAudioVideo.this.getContentPane());
	
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			String cadena = e.getMessage();			
			JOptionPane.showMessageDialog(VentanaAudioVideo.this, cadena);
			
		}
		
		
	}

	
	
	
	
	
	
	
	
	
		
	
	
	
}
