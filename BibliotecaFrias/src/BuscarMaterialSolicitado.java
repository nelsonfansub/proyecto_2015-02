import java.awt.BorderLayout;	
import java.awt.EventQueue;	
import java.awt.Font;	
import java.awt.Toolkit;	
import java.awt.event.ActionEvent;	
import java.awt.event.ActionListener;	
import java.awt.event.MouseAdapter;	
import java.awt.event.MouseEvent;	
import java.sql.ResultSet;
import javax.swing.ImageIcon;	
import javax.swing.JButton;	
import javax.swing.JFrame;	
import javax.swing.JLabel;	
import javax.swing.JOptionPane;	
import javax.swing.JPanel;	
import javax.swing.JScrollPane;	
import javax.swing.JTable;	
import javax.swing.JTextField;	
import javax.swing.SwingConstants;	
import javax.swing.border.EmptyBorder;	
import javax.swing.table.DefaultTableModel;
import Clases.Conexion;
	public class BuscarMaterialSolicitado extends JFrame {

		private JPanel contentPane;
		private JTextField txtBuscarCodigo;
		private JTextField txtBuscarNombre;
		private JTable table1;
		JTable tabla;
		static JFrame ventanaPadre;
		static int ventanaAlterna;
		ResultSet rs;
		DefaultTableModel dfm = new DefaultTableModel();
	
		

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						BuscarMaterialSolicitado frame = new BuscarMaterialSolicitado(ventanaPadre);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	
		public BuscarMaterialSolicitado(final JFrame ventanaPadre) {
			
			setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarMaterialSolicitado.class.getResource("/imagenes/Find.png")));
			setTitle("Busqueda de Materiales");
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 699, 364);
			contentPane = new CambiarImagenDelFondo("imagenes/wallpaper.jpg");
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setLocationRelativeTo(null);
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
		    this.ventanaPadre = ventanaPadre;
		    this.ventanaAlterna = ventanaAlterna;
			JLabel lblNewLabel = new JLabel("Codigo:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(22, 25, 53, 14);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(22, 58, 53, 14);
			contentPane.add(lblNewLabel_1);
			
			txtBuscarCodigo = new JTextField();
			txtBuscarCodigo.setBounds(85, 23, 450, 20);
			contentPane.add(txtBuscarCodigo);
			txtBuscarCodigo.setColumns(10);
			
			txtBuscarNombre = new JTextField();
			txtBuscarNombre.setBounds(85, 56, 450, 20);
			contentPane.add(txtBuscarNombre);
			txtBuscarNombre.setColumns(10);
			
			table1 = new JTable();
			table1.setBounds(22, 110, 562, 205);
			contentPane.add(table1);
			
			JScrollPane scrollPane = new JScrollPane(table1);
			scrollPane.setBounds(10, 110, 663, 205);
			contentPane.add(scrollPane);
			
	            tabla= this.table1;
				tabla.setModel(dfm);
				Conexion conexion = new Conexion();
				 if(ventanaPadre instanceof VentanaDeSolicitudDeMaterial )
				{
					JOptionPane.showMessageDialog(this, "buscando materiales!");
					dfm.setColumnIdentifiers(new Object[]{"ID Material Solicitado", "nombre",  "Tipo de material", "autor", 
							"Clasificacion Tematica", "Cantidad"});
					rs = conexion.SeleccionarMaterialSolicitado();
					try {
						
						while(rs.next()){
							dfm.addRow(new Object[]{rs.getInt("idMaterialSolicitado"), rs.getString("nombre"), rs.getString("tipoMaterial"), 
									rs.getString("autor"), rs.getString("clasificacionTematica"), rs.getString("cantidadDeSolicitudes")});
						}
						} catch(Exception e){
					
						}
				}
				 if(ventanaPadre instanceof VentanaAudioVideo )
					{
					 
					 ResultSet rs = conexion.SeleccionarAudioVideo();
						dfm.setColumnIdentifiers(new Object[]{"ID Audio Video", "Titulo", "Director", "Tipo"});
						
						try {
							
							while(rs.next()){
								dfm.addRow(new Object[]{rs.getInt("idMaterial"), rs.getString("titulo"), 
										rs.getString("director"), rs.getString("tipo")});
							}
							} catch(Exception e){
						
							}
					}
					

				//--------------------- para Buscar por id y nombre -------------------------//
				
				JButton btnBuscarPorID = new JButton("");
				btnBuscarPorID.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// para buscar por id
						
						if(	txtBuscarCodigo.getText().isEmpty()){
							
							JOptionPane.showMessageDialog(rootPane, "No se ha indicado un Codigo para buscar");
							txtBuscarCodigo.requestFocus();
						}
						else {

							DefaultTableModel dfmBuscar = new DefaultTableModel();
							table1.setModel(dfmBuscar);
							int id = Integer.parseInt(txtBuscarCodigo.getText());
							Conexion conexion = new Conexion();
							
							if(ventanaPadre instanceof VentanaDeSolicitudDeMaterial)
							{
								
								ResultSet rs = conexion.SeleccionarMaterialPorID(id);
								dfmBuscar.setColumnIdentifiers(new Object[]{"ID Material Solicitado", "nombre", "Tipo de material", "autor", 
										"Clasificacion Tematica", "Cantidad"});
								try {
									
										while(rs.next()){
										dfmBuscar.addRow(new Object[]{rs.getInt("idmaterialSolicitado"), rs.getString("nombre"), rs.getString("tipoMaterial"), rs.getString("autor"),
												rs.getString("clasificacionTematica"),rs.getString("cantidadDeSolicitudes")});
									}
								} catch(Exception e){
								
								}
							}
							
							if(ventanaPadre instanceof VentanaAudioVideo)
							{
								id = Integer.parseInt(txtBuscarCodigo.getText());
								ResultSet rs = conexion.SeleccionarAudioVideoPorID(id);								
								dfmBuscar.setColumnIdentifiers(new Object[]{"ID Audio Video", "Titulo", "Director", "Tipo"});
								try {
									
										while(rs.next()){
										dfmBuscar.addRow(new Object[]{rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("director"), rs.getString("tipo")});
									}
										
								} catch(Exception e){
								
							      }
							}
							
							
						}	
					}
				});
				btnBuscarPorID.setIcon(new ImageIcon(BuscarUsuario.class.getResource("/imagenes/Find.png")));
				btnBuscarPorID.setBounds(545, 23, 39, 22);
				contentPane.add(btnBuscarPorID);
				
				JButton btnBuscarPorNombre = new JButton("");
				btnBuscarPorNombre.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent Argumento) {
						
					
					if(	txtBuscarNombre.getText().isEmpty()){
						
						JOptionPane.showMessageDialog(rootPane, "No se ah indicado un Nombre para buscar");
						txtBuscarNombre.requestFocus();
					}
					else {
							DefaultTableModel dfmBuscar = new DefaultTableModel();
							table1.setModel(dfmBuscar);
							String nombre = "%"+ txtBuscarNombre.getText().toString()+"%";
							Conexion cn = new Conexion();
							
							if(ventanaPadre instanceof VentanaDeSolicitudDeMaterial )
							{
								ResultSet rs = cn.SeleccionarMaterialPorDescripcion(nombre);
								dfmBuscar.setColumnIdentifiers(new Object[]{"ID Material Solicitado", "nombre", "Tipo de material", "autor", 
										"Clasificacion Tematica", "Cantidad"});
								try {
									
										while(rs.next()){
										dfmBuscar.addRow(new Object[]{rs.getInt("idmaterialSolicitado"), rs.getString("nombre"), rs.getString("tipoMaterial"), rs.getString("autor"),
												rs.getString("clasificacionTematica"),rs.getString("cantidadDeSolicitudes")});
									}
								} catch(Exception e){
								
								}
							}
							else if(ventanaPadre instanceof VentanaAudioVideo)
							{
							
								ResultSet rs = cn.SeleccionarAudioVideoPorNombre(nombre);								
								dfmBuscar.setColumnIdentifiers(new Object[]{"ID Audio Video", "Titulo", "Director", "Tipo"});
								try {
									
										while(rs.next()){
										dfmBuscar.addRow(new Object[]{rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("director"), rs.getString("tipo")});
									}
										
								} catch(Exception e){
								
								}
							}
							
						 }
					}
				});
				btnBuscarPorNombre.setIcon(new ImageIcon(BuscarUsuario.class.getResource("/imagenes/Find.png")));
				btnBuscarPorNombre.setBounds(545, 55, 39, 23);
				contentPane.add(btnBuscarPorNombre);
				
				//--------------------Evento para seleccionar el ID Del material ---------------//
				

				table1.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent e) {
						
						int fila = table1.getSelectedRow(); 
						int columna = 0;
						String idMaterialTomado;
						
						idMaterialTomado = dfm.getValueAt(fila, columna).toString();
						
						
						if(ventanaPadre instanceof VentanaAudioVideo){
							((VentanaAudioVideo) ventanaPadre).cargarDatosMaterial(idMaterialTomado);
						}
						
						if(ventanaPadre instanceof VentanaDeSolicitudDeMaterial ){
							((VentanaDeSolicitudDeMaterial) ventanaPadre).cargarDatosMaterial(idMaterialTomado);
						}
						
						
						dispose();
					}
				});
				
				
				
				
		}
	}
