import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import Clases.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class VenatanaAgregarInventario extends JDialog{

	private JPanel contentPane;
	private JTable table;
	static JDialog ventanaPadre;
	static int cantidadAgregar;
	JTable tabla;
	DefaultTableModel TablaModelo = new DefaultTableModel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VenatanaAgregarInventario frame = new VenatanaAgregarInventario(ventanaPadre, cantidadAgregar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VenatanaAgregarInventario(final JDialog ventanaPadre, int cantidadAgregar) {
		
		super(ventanaPadre);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VenatanaAgregarInventario.class.getResource("/imagenes/Add.png")));
		setTitle("Agregar Inventario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 366);
		contentPane = new CambiarImagenDelFondo("/imagenes/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.ventanaPadre = ventanaPadre;
		this.cantidadAgregar = cantidadAgregar;
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 547, 241);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
			
		));
		scrollPane.setViewportView(table);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: correguir error de tomar el valor del ultimo campo para insertarlo solo lo toma cuando se pulsa enter 
				
				for(int i = 0; i < tabla.getRowCount(); i++)
					{
					Connection conexion = Conexion.Conectar();
					try {	
							String sql = "UPDATE inventario SET ubicacionFisica = ? , condicionFisica = ? where idInventario = ?";
							PreparedStatement sentencia = conexion.prepareStatement(sql);	
							sentencia.setString(1, tabla.getValueAt(i, 3).toString());
							sentencia.setString(2 ,tabla.getValueAt(i, 4).toString());
							sentencia.setInt(3, Integer.parseInt(tabla.getValueAt(i, 0).toString()));	
							sentencia.executeUpdate();
					       
						} catch  (SQLException e) {
							JOptionPane.showMessageDialog(rootPane, e.getMessage());
						}
					}
				
				JOptionPane.showMessageDialog(rootPane, "Inventario Modificado...");
		      dispose();
			}

		});
		btnAgregar.setIcon(new ImageIcon(VenatanaAgregarInventario.class.getResource("/imagenes/Add.png")));
		btnAgregar.setBounds(430, 284, 127, 23);
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// ELIMINAR DE LA BASE DE DATOS IDINVENNTARIO GENERADOS
				for(int i = 1; i <= tabla.getRowCount(); i++)
				{
					Connection conexion = Conexion.Conectar();
					try {	
						String sql = "DELETE FROM inventario WHERE idInventario = ?";
						PreparedStatement sentencia = conexion.prepareStatement(sql);	
						sentencia.setInt(1, Integer.parseInt(tabla.getValueAt(i-1, 0).toString()));	
						sentencia.executeUpdate();
				       
						} catch  (SQLException e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage());
						}
				}
			
				JOptionPane.showMessageDialog(rootPane, "Datos Eliminados...");
			
				DefaultTableModel modelo =(DefaultTableModel) tabla.getModel();
				int filasTabla = tabla.getRowCount();
				
				for(int i =0; filasTabla >i; i++ ){
					
					modelo.removeRow(0);
				}
				
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(VenatanaAgregarInventario.class.getResource("/imagenes/DeleteHS.png")));
		btnCancelar.setBounds(314, 284, 108, 23);
		contentPane.add(btnCancelar);
		
		tabla= this.table;
		tabla.setModel(TablaModelo);
		TablaModelo.isCellEditable(0, 0);
		
		Connection conexion = Conexion.Conectar();
		TablaModelo.setColumnIdentifiers(new Object[]{"ID Inventario", "ID Material", "Estado", "Ubicacion Fisica", "Condicion Fisica"});
	
		
		try {	
			
			
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM  inventario order by idInventario desc limit " + cantidadAgregar;
			ResultSet resultados = sentencia.executeQuery(sql);
			
			while( resultados.next()){

					TablaModelo.addRow(new Object[]{resultados.getInt("idInventario"), resultados.getInt("idMaterial"), resultados.getString("estado"), resultados.getString("ubicacionFisica"), resultados.getString("condicionFisica")});
				}
		
			} catch(Exception e){
					e.printStackTrace();
			}
	
		
		for(int i=1; i <= table.getRowCount(); i++)
		{
			String idInventario = table.getValueAt(i-1, 0).toString();
			String ubicacionFisica = ventanaModificarElInventario.asignarUbicacionFisca();
			StringBuffer sbUbicacionFisica;
			int pos = ubicacionFisica.indexOf("-");	
			sbUbicacionFisica = new 	StringBuffer(ubicacionFisica );	
			sbUbicacionFisica.insert(pos+1, idInventario );
			
			tabla.setValueAt(sbUbicacionFisica, i-1,3 );
	
		}
	
	
	}
	
	//TODO: Hacer todas las columnas no editables menos la 4
	public boolean isCellEditable(int row, int column){
		if(column == 4){
			return true;
		}else
			return false;
	}
	
}
