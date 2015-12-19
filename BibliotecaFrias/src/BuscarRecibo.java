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


public class BuscarRecibo extends JDialog {

	private JPanel contentPane;
	private JTextField txtBuscarCodigoRecibo;
	private JTextField txtBuscarCodigoPrestamo;
	private JTable table1;
	JTable tabla;
	ResultSet rs;
	DefaultTableModel TablaModelo = new DefaultTableModel();
   static JDialog ventanaPadre;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarRecibo frame = new BuscarRecibo(ventanaPadre);
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
	public BuscarRecibo(final JDialog ventanaPadre) {

		super(ventanaPadre);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarRecibo.class.getResource("/imagenes/Find.png")));
		setTitle("Buscar Recibos de Materiales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 364);
		contentPane = new CambiarImagenDelFondo("imagenes/wallpaper.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo Recibo:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(22, 25, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo Prestamo:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(22, 58, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		txtBuscarCodigoRecibo = new JTextField();
		txtBuscarCodigoRecibo.setBounds(134, 23, 401, 20);
		contentPane.add(txtBuscarCodigoRecibo);
		txtBuscarCodigoRecibo.setColumns(10);
		
		txtBuscarCodigoPrestamo = new JTextField();
		txtBuscarCodigoPrestamo.setBounds(134, 56, 401, 20);
		contentPane.add(txtBuscarCodigoPrestamo);
		txtBuscarCodigoPrestamo.setColumns(10);
		
		table1 = new JTable();
		table1.setBounds(22, 110, 562, 205);
		contentPane.add(table1);
		
		JScrollPane scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(10, 110, 586, 205);
		contentPane.add(scrollPane);
		
		//--------------------- Mostrar la consulta todos los datos en la tabla---------------------------------------//
		
            tabla= this.table1;
			tabla.setModel(TablaModelo);

			TablaModelo.setColumnIdentifiers(new Object[]{"ID Devolucion", "ID Prestamo",  "Hora Recibo","Fecha Recibo"});
			Conexion cn = new Conexion(); 
			rs = cn.SeleccionarRecibo();
			try {
				
				while(rs.next()){
					TablaModelo.addRow(new Object[]{rs.getInt("idDevolucion"), rs.getInt("idPrestamo"), rs.getString("hora"),  rs.getString("fecha")});
				}
				
			} catch(Exception e){
				
			}
			
			
			//--------------------- para Buscar por id y nombre -------------------------//
			
			JButton btnBuscarPorID = new JButton("");
			btnBuscarPorID.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(	txtBuscarCodigoRecibo.getText().isEmpty()){
						
						JOptionPane.showMessageDialog(rootPane, "No se ah indicado un Codigo para buscar");
						txtBuscarCodigoRecibo.requestFocus();
					}
					else {
						
					
						int id = Integer.parseInt(txtBuscarCodigoRecibo.getText());
						Conexion cn = new Conexion();
						
						ResultSet rs = cn.SeleccionarPorIDRecibo(id);
						DefaultTableModel dfmBuscar = new DefaultTableModel();
						table1.setModel(dfmBuscar);
						dfmBuscar.setColumnIdentifiers(new Object[] {"ID Devolucion", "ID Prestamo",  "Hora Recibo","Fecha Recibo"});
						
						try {
								while(rs.next()){
								dfmBuscar.addRow(new Object[]{rs.getInt("idDevolucion"), rs.getInt("idPrestamo"), rs.getString("hora"),  rs.getString("fecha")});
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
				if(	txtBuscarCodigoPrestamo.getText().isEmpty()){
					
					JOptionPane.showMessageDialog(rootPane, "No se ah indicado un codigo para buscar");
					txtBuscarCodigoPrestamo.requestFocus();
					}
                   			
					else {
						
						int idPrestamo = Integer.parseInt(txtBuscarCodigoPrestamo.getText());
						
						Conexion cn = new Conexion();
						
						ResultSet rs = cn.SeleccionarIDPrestamo(idPrestamo);
						DefaultTableModel dfmBuscar = new DefaultTableModel();
						table1.setModel(dfmBuscar);
						dfmBuscar.setColumnIdentifiers(new Object[] {"ID Devolucion", "ID Prestamo",  "Hora Recibo","Fecha Recibo"});
						
						try {
								while(rs.next()){
								dfmBuscar.addRow(new Object[]{rs.getInt("idDevolucion"), rs.getInt("idPrestamo"), rs.getString("hora"),  rs.getString("fecha")});
							}
							
						} catch(Exception e){
							
						}
					 }
				}

			});
			
			
			
			btnBuscarPorNombre.setIcon(new ImageIcon(BuscarUsuario.class.getResource("/imagenes/Find.png")));
			btnBuscarPorNombre.setBounds(545, 55, 39, 23);
			contentPane.add(btnBuscarPorNombre);
			//--------------------Evento para seleccionar el ID Del usuario ---------------//
	
			table1.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = table1.getSelectedRow();
					int columna = 0;
					int idReciboTomado;
					
					idReciboTomado = Integer.parseInt(table1.getValueAt(fila, columna).toString());	
					if(ventanaPadre instanceof VentanaRealizarReciboDeMateriales){
						
						((VentanaRealizarReciboDeMateriales) ventanaPadre).cargarDatosRecibo(idReciboTomado);

					}
					
					dispose();
				}
			});
	
	}

	
}
