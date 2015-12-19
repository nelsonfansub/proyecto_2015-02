import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import Clases.Conexion;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarPrestamo extends JDialog {

	private JPanel contentPane;
	private JTextField txtBuscarCodigo;
	private JTextField txtBuscarNombre;
	private JTable table1;
	JTable tabla;
	static JDialog ventanaPadre;
	ResultSet rs;
	DefaultTableModel dfm = new DefaultTableModel();
	DefaultTableModel dfmBuscar = new DefaultTableModel();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarPrestamo frame = new BuscarPrestamo(ventanaPadre);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BuscarPrestamo(final JDialog ventanaPadre) {
		
		super(ventanaPadre);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarPrestamo.class.getResource("/imagenes/Find.png")));
		setTitle("Buscar de Prestamos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 364);
		contentPane = new CambiarImagenDelFondo("imagenes/wallpaper.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.ventanaPadre = ventanaPadre;
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(22, 25, 53, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
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
		scrollPane.setBounds(10, 110, 586, 205);
		contentPane.add(scrollPane);
		
            tabla= this.table1;
			tabla.setModel(dfm);

			dfm.setColumnIdentifiers(new Object[]{"ID Prestamo", "ID Usuario", "Nombre Usuario", "Hora Prestamo","Fecha Prestamo"});
			
			Conexion cn = new Conexion(); 
			
			rs = cn.SeleccionarPrestamo();
			try {
				
				while(rs.next()){
					dfm.addRow(new Object[]{rs.getInt("idPrestamo"), rs.getInt("idpersona"), rs.getString("nombre"),  rs.getString("horaprestamo"), rs.getString("fechaprestamo")});
				}
				
			} catch(Exception e){
				
			}
			
			
			//--------------------- para Buscar por id y nombre -------------------------//
			
			JButton btnBuscarPorID = new JButton("");
			btnBuscarPorID.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// para buscar por id
					
					if(	txtBuscarCodigo.getText().isEmpty()){
						
						JOptionPane.showMessageDialog(rootPane, "No se ah indicado un Codigo para buscar");
						txtBuscarCodigo.requestFocus();
					}
					else {
						
					
						int id = Integer.parseInt(txtBuscarCodigo.getText());
						Conexion cn = new Conexion();
						
						ResultSet rs = cn.SeleccionarPorIDPrestamo(id);
						table1.setModel(dfmBuscar);
						dfmBuscar.setColumnIdentifiers(new Object[] {"ID Prestamo", "ID Usuario", "Nombre Usuario", "Hora Prestamo","Fecha Prestamo"});
						
						try {
								while(rs.next()){
								dfmBuscar.addRow(new Object[]{rs.getInt("idPrestamo"), rs.getInt("idpersona"), rs.getString("nombre"), rs.getString("horaprestamo"), rs.getString("fechaprestamo")});
							}
							
						} catch(Exception e){
							
						}
						
					}	
				}
			});
			btnBuscarPorID.setIcon(new ImageIcon(BuscarUsuario.class.getResource("/imagenes/Find.png")));
			btnBuscarPorID.setBounds(545, 25, 39, 20);
			contentPane.add(btnBuscarPorID);
			
			JButton btnBuscarPorNombre = new JButton("");
			btnBuscarPorNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				if(	txtBuscarNombre.getText().isEmpty()){
					
					JOptionPane.showMessageDialog(rootPane, "No se ah indicado un Nombre para buscar");
					txtBuscarNombre.requestFocus();
					}
					else {
						
						String nombre = "%"+ txtBuscarNombre.getText().toString()+"%";
						Conexion cn = new Conexion();
						
						ResultSet rs = cn.SeleccionarPorNombrePrestamo(nombre);
						table1.setModel(dfmBuscar);
						dfmBuscar.setColumnIdentifiers(new Object[] {"ID Prestamo", "ID Usuario", "Nombre Usuario", "Hora Prestamo","Fecha Prestamo"});
						
						try {
							
								while(rs.next()){
								dfmBuscar.addRow(new Object[]{rs.getInt("idPrestamo"), rs.getInt("idpersona"), rs.getString("nombre"), rs.getString("horaprestamo"), rs.getString("fechaprestamo")});
							}
							
						} catch(Exception e){
							
						}
					 }
				}
			});
			btnBuscarPorNombre.setIcon(new ImageIcon(BuscarUsuario.class.getResource("/imagenes/Find.png")));
			btnBuscarPorNombre.setBounds(545, 55, 39, 23);
			contentPane.add(btnBuscarPorNombre);
			
			table1.addMouseListener(new MouseAdapter() 
			{
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = table1.getSelectedRow();
					int columna = 0;
					int idPrestamoTomado;
					
					idPrestamoTomado = Integer.parseInt(table1.getValueAt(fila, columna).toString());	
					System.out.println(idPrestamoTomado);
					if(ventanaPadre instanceof VentanaPrestamosDeMateriales){
						
						((VentanaPrestamosDeMateriales) ventanaPadre).cargarDatosPrestamo(idPrestamoTomado);

					}
					if(ventanaPadre instanceof VentanaRealizarReciboDeMateriales){
						
					    ((VentanaRealizarReciboDeMateriales) ventanaPadre).cargarDatosPrestamo(idPrestamoTomado);
					    ((VentanaRealizarReciboDeMateriales) ventanaPadre).validarFechaHoraEntrega();
					}
					
					
					dispose();
				}
			});
	
	   }
	}


