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
import javax.swing.JDialog;
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


public class BuscarMaterialPrincipal extends JDialog {

	private JPanel contentPanel;
	private JTextField txtBuscarCodigo;
	private JTextField txtBuscarNombre;
	private JTable table1;
	JTable tabla;
	static JDialog ventanaPadre;
	static int ventanaAlterna;
	ResultSet rs;
	DefaultTableModel TablaModelo = new DefaultTableModel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarMaterialPrincipal llamaVentana = new BuscarMaterialPrincipal(ventanaPadre, ventanaAlterna);
					llamaVentana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public BuscarMaterialPrincipal(final JDialog ventanaPadre, final int ventanaAlterna) {
		
		super(ventanaPadre);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarMaterialPrincipal.class.getResource("/imagenes/Find.png")));
		setTitle("Buscar Materiales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 699, 364);
		contentPanel = new CambiarImagenDelFondo("/imagenes/wallpaper.jpg");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
	    this.ventanaPadre = ventanaPadre;
	    this.ventanaAlterna = ventanaAlterna;
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(22, 25, 53, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(22, 58, 53, 14);
		contentPanel.add(lblNewLabel_1);
		
		txtBuscarCodigo = new JTextField();
		txtBuscarCodigo.setBounds(85, 23, 450, 20);
		contentPanel.add(txtBuscarCodigo);
		txtBuscarCodigo.setColumns(10);
		
		txtBuscarNombre = new JTextField();
		txtBuscarNombre.setBounds(85, 56, 450, 20);
		contentPanel.add(txtBuscarNombre);
		txtBuscarNombre.setColumns(10);
		
		table1 = new JTable();
		table1.setBounds(22, 110, 562, 205);
		contentPanel.add(table1);
		
		JScrollPane scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(10, 110, 663, 205);
		contentPanel.add(scrollPane);
		
		//--------------------- Mostrar la consulta todos los datos en la tabla---------------------------------------//
		
	
            tabla= this.table1;
			tabla.setModel(TablaModelo);
			Conexion conexion = new Conexion(); 
			
			if(ventanaPadre instanceof VentanaPrestamosDeMateriales && ventanaAlterna == 3)
			{
				TablaModelo.setColumnIdentifiers(new Object[]{"ID Inventario", "ID Material", "Descripcion", "Tipo Material", "Ubicacion Fisica", "Estado"});
				rs = conexion.SeleccionarInventarioLibro();
				try {
					
					while(rs.next()){
						TablaModelo.addRow(new Object[]{rs.getInt("idInventario"), rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial"), rs.getString("ubicacionFisica"), rs.getString("estado")});
					}
					} catch(Exception e){
				
					}
			}else
				
			if(ventanaPadre instanceof VentanaPrestamosDeMateriales || ventanaAlterna == 2)
			{
				TablaModelo.setColumnIdentifiers(new Object[]{"ID Inventario", "ID Material", "Descripcion", "Tipo Material", "Ubicacion Fisica", "Estado"});
				rs = conexion.SeleccionarInventario();
				try {
					
					while(rs.next()){
						TablaModelo.addRow(new Object[]{rs.getInt("idInventario"), rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial"), rs.getString("ubicacionFisica"), rs.getString("estado")});
					}
					} catch(Exception e){
				
					}
			}
			else if(ventanaPadre instanceof ventanaModificarElInventario && ventanaAlterna == 0)
			{
				TablaModelo.setColumnIdentifiers(new Object[]{"ID Material", "Descripcion", "Tipo Material"});
				rs = conexion.SeleccionarMaterial();
				try {
					
					while(rs.next()){
						TablaModelo.addRow(new Object[]{rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial")});
					}
					} catch(Exception e){
				
					}
			}
				

			//--------------------- para Buscar por id y nombre -------------------------//
			
			JButton btnBuscarPorID = new JButton("");
			btnBuscarPorID.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					if(	txtBuscarCodigo.getText().isEmpty()){
						
						JOptionPane.showMessageDialog(rootPane, "No se ah indicado un Codigo para buscar");
						txtBuscarCodigo.requestFocus();
					}
					else {

						DefaultTableModel dfmBuscar = new DefaultTableModel();
						table1.setModel(dfmBuscar);
						int id = Integer.parseInt(txtBuscarCodigo.getText());
						Conexion conexion = new Conexion();
						
						if(ventanaPadre instanceof VentanaPrestamosDeMateriales && ventanaAlterna == 3)
						{
							ResultSet rs = conexion.SeleccionarInventarioPorIDLibro(id);
							dfmBuscar.setColumnIdentifiers(new Object[]{"ID Material", "Descripcion", "Tipo Material"});
							try {
								
									while(rs.next()){
									dfmBuscar.addRow(new Object[]{rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial")});
								}
							} catch(Exception e){
							
							}
						}
						else if(ventanaPadre instanceof VentanaPrestamosDeMateriales || ventanaAlterna == 2)
						{
							ResultSet rs = conexion.SeleccionarInventarioPorID(id);
							dfmBuscar.setColumnIdentifiers(new Object[] {"ID Inventario", "ID Material", "Descripcion", "Tipo Material", "Ubicacion Fisica", "Estado"});
							try {
								
									while(rs.next()){
									dfmBuscar.addRow(new Object[]{rs.getInt("idInventario"), rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial"), rs.getString("ubicacionFisica"), rs.getString("estado")});
								}
								
							} catch(Exception e){
								
							}
							
						}else if(ventanaPadre instanceof ventanaModificarElInventario && ventanaAlterna == 0)
						{
							ResultSet rs = conexion.SeleccionarMaterialPorID(id);
							dfmBuscar.setColumnIdentifiers(new Object[]{"ID Material", "Descripcion", "Tipo Material"});
							try {
								
									while(rs.next()){
									dfmBuscar.addRow(new Object[]{rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial")});
								}
							} catch(Exception e){
							
							}
						}
					}	
				}
			});
			btnBuscarPorID.setIcon(new ImageIcon(BuscarUsuario.class.getResource("/imagenes/Find.png")));
			btnBuscarPorID.setBounds(545, 23, 39, 22);
			contentPanel.add(btnBuscarPorID);
			
			JButton btnBuscarPorNombre = new JButton("");
			btnBuscarPorNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					// para buscar por nombre al darle clic al boton
				if(	txtBuscarNombre.getText().isEmpty()){
					
					JOptionPane.showMessageDialog(rootPane, "No se ah indicado un Nombre para buscar");
					txtBuscarNombre.requestFocus();
				}
				else {
						DefaultTableModel dfmBuscar = new DefaultTableModel();
						table1.setModel(dfmBuscar);
						String descripcion = "%"+ txtBuscarNombre.getText().toString()+"%";
						Conexion cn = new Conexion();
						
						if(ventanaPadre instanceof VentanaPrestamosDeMateriales && ventanaAlterna == 3)
						{
							ResultSet rs = cn.SeleccionarInventarioPorDescripcionLibro(descripcion);
							dfmBuscar.setColumnIdentifiers(new Object[]{"ID Material", "Descripcion", "Tipo Material"});
							try {
								
									while(rs.next()){
									dfmBuscar.addRow(new Object[]{rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial")});
								}
							} catch(Exception e){
							
							}
						}
						else if(ventanaPadre instanceof VentanaPrestamosDeMateriales || ventanaAlterna == 2)
						{	
							ResultSet rs = cn.SeleccionarInventarioPorDescripcion(descripcion);
							dfmBuscar.setColumnIdentifiers(new Object[] {"ID Inventario", "ID Material", "Descripcion", "Tipo Material", "Ubicacion Fisica", "Estado"});
							try {
									while(rs.next()){
									dfmBuscar.addRow(new Object[]{rs.getInt("idInventario"), rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial"), rs.getString("ubicacionFisica"), rs.getString("estado")});
								}
								
							} catch(Exception e){
								
							}
						}else if(ventanaPadre instanceof ventanaModificarElInventario && ventanaAlterna == 0)
						{
							ResultSet rs = cn.SeleccionarMaterialPorDescripcion(descripcion);
							dfmBuscar.setColumnIdentifiers(new Object[]{"ID Material", "Descripcion", "Tipo Material"});
							try {
								
									while(rs.next()){
									dfmBuscar.addRow(new Object[]{rs.getInt("idmaterial"), rs.getString("titulo"), rs.getString("tipoMaterial")});
								}
							} catch(Exception e){
							
							}
						}
					 }
				}
			});
			btnBuscarPorNombre.setIcon(new ImageIcon(BuscarUsuario.class.getResource("/imagenes/Find.png")));
			btnBuscarPorNombre.setBounds(545, 55, 39, 23);
			contentPanel.add(btnBuscarPorNombre);
			
			//--------------------Evento para seleccionar el ID Del material ---------------//
			

			table1.addMouseListener(new MouseAdapter() 
			{

				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = table1.getSelectedRow(); 
					int columna = 0;
					String idMaterialTomado;
					
					idMaterialTomado = table1.getValueAt(fila, columna).toString();
					
					if(ventanaPadre instanceof VentanaPrestamosDeMateriales){
						
						((VentanaPrestamosDeMateriales) ventanaPadre).mostrarDatoEnTabla(idMaterialTomado);
						((VentanaPrestamosDeMateriales) ventanaPadre).cargarDatosDetalle(idMaterialTomado);
						
					}
					
					if(ventanaPadre instanceof ventanaModificarElInventario && ventanaAlterna == 0){
						
						((ventanaModificarElInventario) ventanaPadre).cargarDatosMaterial(Integer.parseInt(idMaterialTomado));
						
					}
					
					if(ventanaPadre instanceof ventanaModificarElInventario && ventanaAlterna == 2){
						((ventanaModificarElInventario) ventanaPadre).disminuirInventario(Integer.parseInt(idMaterialTomado));
					}
					
					dispose();
				}
			});
			
			
			
			
	}
}
