import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import Clases.Conexion;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
public class BuscarUsuarios extends JDialog {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textNombre;
	private JTable table;
	DefaultTableModel TablaModelo = new DefaultTableModel();
	JTable tabla;
	ResultSet rs;
	private static JDialog ventanaPadre;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarUsuarios frame = new BuscarUsuarios(ventanaPadre);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BuscarUsuarios(final JDialog ventanaPadre) {
		setModal(true);
		setTitle("Buscar Usuarios\r\n");
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarUsuarios.class.getResource("imagenes/Find.png")));
		
		this.ventanaPadre = ventanaPadre;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane = new CambiarImagenDelFondo("imagenes/wallpaper.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblID.setBounds(28, 28, 46, 14);
		contentPane.add(lblID);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(28, 53, 46, 14);
		contentPane.add(lblNombre);
		
		textID = new JTextField();
		textID.setBounds(84, 25, 287, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(84, 50, 287, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		
		table = new JTable();
		table.setBounds(10, 78, 414, 172);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 78, 414, 172);
		contentPane.add(scrollPane);
		
		JButton btnBuscarID = new JButton("");
		btnBuscarID.setIcon(new ImageIcon(BuscarUsuarios.class.getResource("imagenes/Find.png")));
		btnBuscarID.setBounds(379, 24, 45, 23);
		contentPane.add(btnBuscarID);
		
		JButton btnBuscarNombre = new JButton("");
		btnBuscarNombre.setIcon(new ImageIcon(BuscarUsuarios.class.getResource("imagenes/Find.png")));
		btnBuscarNombre.setBounds(379, 49, 45, 23);
		contentPane.add(btnBuscarNombre);
		
		tabla = this.table;
		tabla.setModel(TablaModelo);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setColumnHeaderView(scrollBar);
		
		TablaModelo.setColumnIdentifiers(new Object[]{"ID Usuario", "Nombre", "Apellido","Sexo", "Tipo Usuario"});
		Conexion con = new Conexion();
		String tipo = null;
		if(ventanaPadre instanceof VentanaEmpleado)
		{
			tipo = "'Empleado'";
		}
		if(ventanaPadre instanceof VenatanaEstudiante)
		{
			tipo = "'Estudiante'";
		}
		if(ventanaPadre instanceof VentanaProfesor)
		{
			tipo = "'Profesor'";
		}
		try {
			
			Statement st = con.Conectar().createStatement();
			rs = st.executeQuery("Select idPersona, nombre, apellido, sexo, tipoUsuario FROM persona WHERE tipoUsuario="+tipo);
			while(rs.next()){
				TablaModelo.addRow(new Object[]{rs.getInt("idpersona"), rs.getString("nombre"), rs.getString("apellido"), 
						rs.getString("sexo"), rs.getString("tipoUsuario")});
				}
			
			
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(scrollBar, "No se ha podido establecer la conexión");
			ex.printStackTrace();
		}
		btnBuscarID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// para buscar por id
				
				Conexion con = new Conexion();
				PreparedStatement pst = null;
				ResultSet rs = null;
				int id = Integer.parseInt(textID.getText());;
				
				try {
					Conexion.Conectar();
					pst = Conexion.Conectar().prepareStatement("Select idPersona, nombre, apellido, sexo, tipoUsuario FROM persona WHERE idpersona = ?" );
					pst.setInt(1, id);
					rs = pst.executeQuery();	
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				
				if(	textID.getText().isEmpty()){
					
					JOptionPane.showMessageDialog(rootPane, "No se ah indicado un Codigo para buscar");
					textID.requestFocus();
				}
				
				else {
					
					
					con.Conectar();
					try {
						rs = pst.executeQuery();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					table.setModel(TablaModelo);
					TablaModelo.setColumnIdentifiers(new Object[] {"ID Usuario", "Nombre", "Apellido","Sexo", "Tipo Usuario"});
					
					try {
						
							while(rs.next()){
								TablaModelo.addRow(new Object[]{rs.getInt("idpersona"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("sexo"), rs.getString("tipoUsuario")});
							int idPersonaTomado = rs.getInt(1);
							}
						
					} catch(Exception e){
						
					}
					
				}	
			}
		});
		btnBuscarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Conexion cn = new Conexion();
				PreparedStatement pst = null;
				ResultSet rs = null;
				String nombre = "%"+ textNombre.getText().toString()+"%";
				
				try {
					cn.Conectar();
					pst = cn.Conectar().prepareStatement("Select idPersona, nombre, apellido, sexo, tipoUsuario FROM persona WHERE nombre like ?" );
					pst.setString(1, nombre);
					rs = pst.executeQuery(); 
				}catch(SQLException ex){

					ex.printStackTrace();
				}
				
				
				
			if(	textNombre.getText().isEmpty()){
				
				JOptionPane.showMessageDialog(rootPane, "No se ah indicado un Nombre para buscar");
				textNombre.requestFocus();
				}
			
				else {
					
					nombre = "%"+ textNombre.getText().toString()+"%";
					System.out.println(nombre);
					
					try {
						rs = pst.executeQuery();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					};
					
					table.setModel(TablaModelo);
					TablaModelo.setColumnIdentifiers(new Object[] {"ID Usuario", "Nombre", "Apellido","Sexo", "Tipo Usuario"});
					
					try {
						
							while(rs.next()){
							TablaModelo.addRow(new Object[]{rs.getInt("idpersona"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("sexo"), rs.getString("tipoUsuario")});
						}
						
					} catch(Exception e){
						
					}
				 }
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = tabla.getSelectedRow();
				int columna = 0;
				int idP=0;
				
				idP = Integer.parseInt(TablaModelo.getValueAt(fila, columna).toString());
				System.out.println(idP);
				if(ventanaPadre instanceof VentanaEmpleado){
				
					((VentanaEmpleado) ventanaPadre).cargarDatos(idP);
				}
				if(ventanaPadre instanceof VenatanaEstudiante){
					
					((VenatanaEstudiante) ventanaPadre).cargarDatos(idP);
				}
				if(ventanaPadre instanceof VentanaProfesor){
					
					((VentanaProfesor) ventanaPadre).cargarDatos(idP);
				}
				
				dispose();
			}
		});

	}
}
