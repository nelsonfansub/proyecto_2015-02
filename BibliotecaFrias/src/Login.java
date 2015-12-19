import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import Clases.Conexion;
import Clases.Limpiar;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import org.jdesktop.swingx.prompt.PromptSupport;

import com.sun.glass.events.KeyEvent;

public class Login  extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	public JTextField textUsuario;
	protected JPasswordField txtContrasena;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login ventana = new Login();
					ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setUndecorated(true);
		setResizable(false);
		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane_1 = new CambiarImagenDelFondo("/imagenes/login.png");
		contentPane_1.setBorder(new LineBorder(new Color(25, 255, 201), 3, true));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(28, 80, 81, 14);
		contentPane_1.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(107, 77, 281, 20);
		contentPane_1.add(textUsuario);
		textUsuario.setColumns(10);
		PromptSupport.setPrompt("Usuario ", textUsuario);
		
		JLabel lblContrasea = new JLabel("Contrasena:");
		lblContrasea.setBounds(28, 123, 81, 14);
		contentPane_1.add(lblContrasea);
				
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(107, 120, 281, 20);
		contentPane_1.add(txtContrasena);
		PromptSupport.setPrompt("Contrasena Aqui ", txtContrasena);
		
		JButton btnAceptar = new JButton("Aceptar");
		
		btnAceptar.setIcon(new ImageIcon(Login.class.getResource("/imagenes/ok.png")));
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String Usuario = textUsuario.getText();
					String claveBibliotecario = new String(txtContrasena.getPassword());	
			
					try {
						Connection conexion = Conexion.Conectar();
						Statement us;
						ResultSet comp = null;
								
						us = conexion.createStatement();
						comp = us.executeQuery("Select idbibliotecarios,clave,usuario FROM bibliotecarios");
						
						while(comp.next())
						{
							
							if(Usuario.equals(comp.getString(3)) && claveBibliotecario.equals(comp.getString(2)))
							{

					            principal.obtenerInstancia().setVisible(true);
					            principal.obtenerInstancia().setEnabled(true);
								dispose();
								//no utilizo esto pues el metodo pack no deja que la ventana se maximize
								//principal.obtenerInstancia().pack();
								//no llamo la ventana de esta forma pues no me funciona
								//principal f = new principal();
								//f.pack();
					           
							}
						}
						
						
						} catch (SQLException e) {
							e.printStackTrace();
						}
					
				}   
			});
		btnAceptar.setBounds(107, 181, 127, 23);
		contentPane_1.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/imagenes/DeleteHS.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				principal.obtenerInstancia().confirmarCierre();
			}
		});
		
		btnCancelar.setBounds(270, 181, 118, 23);
		contentPane_1.add(btnCancelar);
		
		JLabel lblInicioDeSesin = new JLabel("Iniciar Sesion");
		lblInicioDeSesin.setBounds(198, 23, 89, 23);
		contentPane_1.add(lblInicioDeSesin);
		
	}
	

	
}
