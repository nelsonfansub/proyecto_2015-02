import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import Clases.Limpiar;
import Clases.RegistrarLibros;
import Clases.Utilidades;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
public class VentanaRegistrarLibros extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textAutor;
	private JTextField textEdicion;
	private JTextField textEditora;
	private JTextField textNumeroPagina;
	private JTextField textTitulo;
	private JTextField textIsbn;
	private String publicacion = "";
	private String tipoMaterial = "";
	private String tematica = "";
	private String genero = "";
	private ImagenDeFondo imagenMaterial;
	private JComboBox comboBoxTematica;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistrarLibros frame = new VentanaRegistrarLibros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VentanaRegistrarLibros() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistrarLibros.class.getResource("/imagenes/Book.png")));
		setForeground(new Color(173, 216, 230));
		setTitle("Registro de Libros");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 425);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new CambiarImagenDelFondo("imagenes/Fondo.png");
		contentPane.setAutoscrolls(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigo = new JLabel("Codigo:");
		lblCdigo.setBounds(23, 46, 46, 14);
		contentPane.add(lblCdigo);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setBounds(79, 46, 93, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(23, 102, 46, 14);
		contentPane.add(lblAutor);
		
		textAutor = new JTextField();
		textAutor.setBounds(79, 102, 289, 20);
		contentPane.add(textAutor);
		textAutor.setColumns(10);
		
		JLabel lblEdicin = new JLabel("Edicion:");
		lblEdicin.setBounds(23, 130, 46, 14);
		contentPane.add(lblEdicin);
		
		textEdicion = new JTextField();
		textEdicion.setBounds(79, 130, 93, 20);
		contentPane.add(textEdicion);
		textEdicion.setColumns(10);
		
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setBounds(23, 158, 46, 14);
		contentPane.add(lblEditora);
		
		textEditora = new JTextField();
		textEditora.setBounds(79, 158, 289, 20);
		contentPane.add(textEditora);
		textEditora.setColumns(10);
		
		JLabel lblGnero = new JLabel("Genero: ");
		lblGnero.setBounds(295, 220, 60, 14);
		contentPane.add(lblGnero);
		
		JComboBox comboBoxGenero= new JComboBox();
		comboBoxGenero.setModel(new DefaultComboBoxModel(new String[] {"","Romantica", "Comedia", "Drama", "Crimen", "Educativa", ""}));
		getContentPane().add(comboBoxGenero, BorderLayout.SOUTH);
		comboBoxGenero.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				genero = ((JComboBox)(e.getSource())).getSelectedItem().toString();
			}
		});
		comboBoxGenero.setBounds(344, 214, 112, 20);
		contentPane.add(comboBoxGenero);
		
		JLabel lblNmeroDePginas = new JLabel("Numero de Paginas:");
		lblNmeroDePginas.setBounds(295, 250, 124, 14);
		contentPane.add(lblNmeroDePginas);
		
		textNumeroPagina = new JTextField();
		textNumeroPagina.setBounds(415, 245, 41, 20);
		contentPane.add(textNumeroPagina);
		textNumeroPagina.setColumns(10);
		
		JLabel lblTipoDeMaterial = new JLabel("Tipo de Material:");
		lblTipoDeMaterial.setBounds(23, 285, 100, 14);
		contentPane.add(lblTipoDeMaterial);
				
		
		final JComboBox comboBoxTipoMaterial = new JComboBox();
		comboBoxTipoMaterial.setModel(new DefaultComboBoxModel(new String[] {"", "Libros", "Audio Visuales"}));
		getContentPane().add(comboBoxTipoMaterial, BorderLayout.SOUTH);
		comboBoxTipoMaterial.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tipoMaterial = ((JComboBox)(e.getSource())).getSelectedItem().toString();
			}
		});
		comboBoxTipoMaterial.setBounds(141, 279, 124, 20);
		contentPane.add(comboBoxTipoMaterial);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					//int codigo = Integer.parseInt(textCodigo.getText());
					String isbn = textIsbn.getText();					
					String titulo = textTitulo.getText();
					String autor = textAutor.getText();
					String edicion = textEdicion.getText();
					String editora = textEditora.getText();
					//String tematica = textTematica.getText();
					int numeroPagina = Integer.parseInt(textNumeroPagina.getText());
					
										
					RegistrarLibros nuevoLibro = new RegistrarLibros();
					
					//nuevoLibro.setIdlibro(codigo);
					nuevoLibro.setIsbn(isbn);
					nuevoLibro.setTitulo(titulo);
					nuevoLibro.setAutor(autor);
					nuevoLibro.setEdicion(edicion);
					nuevoLibro.setCasaeditora(editora);
					nuevoLibro.setGenero(genero);
					nuevoLibro.setAniopublicacion(publicacion);
					//nuevoLibro.setClasificaciontematica(tematica);
					nuevoLibro.setTipomaterial(tipoMaterial);
					nuevoLibro.setNumeropaginas(numeroPagina);
					
					//nuevoLibro.pruebaConexx();
					nuevoLibro.CargarRegistroMateriales(/*comboBoxTematica.getSelectedIndex()*/);
					nuevoLibro.CargarRegistroLibroNuevo();
					nuevoLibro.CargarRegistroAutor();
					
					Limpiar.limpiarCampos(VentanaRegistrarLibros.this.getContentPane());
					JOptionPane.showMessageDialog(VentanaRegistrarLibros.this, "Operacion Realiazada exitosamente!");
					
				
				} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				}
			}
			});
		btnGuardar.setIcon(new ImageIcon(VentanaRegistrarLibros.class.getResource("/imagenes/saveHS.png")));
		btnGuardar.setBounds(535, 87, 107, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Limpiar.limpiarCampos(VentanaRegistrarLibros.this.getContentPane());
				
				
			}
		});
		btnCancelar.setIcon(new ImageIcon(VentanaRegistrarLibros.class.getResource("/imagenes/DeleteHS.png")));
		btnCancelar.setBounds(535, 121, 106, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblTtulo = new JLabel("Titulo:");
		lblTtulo.setBounds(23, 74, 46, 14);
		contentPane.add(lblTtulo);
		
		textTitulo = new JTextField();
		textTitulo.setBounds(79, 74, 289, 20);
		contentPane.add(textTitulo);
		textTitulo.setColumns(10);
		
		
		
		
		JLabel lblFechaDePublicacin = new JLabel("Anio de Publicacion:");
		lblFechaDePublicacin.setBounds(23, 220, 124, 14);
		contentPane.add(lblFechaDePublicacin);
		
		JComboBox comboBoxFechaPublicacion = new JComboBox(Utilidades.obtenerFechas());
		getContentPane().add(comboBoxFechaPublicacion, BorderLayout.SOUTH);
		comboBoxFechaPublicacion.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				publicacion = ((JComboBox)(e.getSource())).getSelectedItem().toString();
			}
		});
		comboBoxFechaPublicacion.setBounds(141, 217, 124, 20);
		contentPane.add(comboBoxFechaPublicacion);
		
		comboBoxTematica = new JComboBox();
		try {
			RegistrarLibros.CargarComboBoxTematica(comboBoxTematica);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		comboBoxTematica.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tematica = ((JComboBox)(e.getSource())).getSelectedItem().toString();
				try {
					RegistrarLibros.CargarIdTematica(tematica);
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBoxTematica.setBounds(141, 247, 124, 20);
		contentPane.add(comboBoxTematica);
		
		JLabel lblTemtica = new JLabel("Tematica:");
		lblTemtica.setBounds(23, 251, 60, 14);
		contentPane.add(lblTemtica);
		
		File archivo = new File("fondos/predeterminado.png");
		String rutaAbsoluta = archivo.getAbsolutePath();
		imagenMaterial = new ImagenDeFondo(rutaAbsoluta);
		imagenMaterial.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Imagen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		imagenMaterial.establecerImagen(rutaAbsoluta);
		obtenerImagenMaterial().setBounds(499, 189, 143, 141);
		contentPane.add(obtenerImagenMaterial());
		
		JLabel lblIsnb = new JLabel("ISBN:");
		lblIsnb.setBounds(241, 49, 35, 14);
		contentPane.add(lblIsnb);
		
		textIsbn = new JTextField();
		textIsbn.setColumns(10);
		textIsbn.setBounds(275, 46, 93, 20);
		contentPane.add(textIsbn);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(VentanaRegistrarLibros.class.getResource("/imagenes/NewDocumentHS.png")));
		btnActualizar.setBounds(535, 53, 107, 23);
		contentPane.add(btnActualizar);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setIcon(new ImageIcon(VentanaRegistrarLibros.class.getResource("/imagenes/Find.png")));
		button.setBounds(182, 43, 41, 23);
		contentPane.add(button);
		
		JButton btnExaminar = new JButton("Examinar");
		btnExaminar.setBounds(499, 332, 143, 23);
		btnExaminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				imagenMaterial.seleccionarImagen();
			}
		});
		
		contentPane.add(btnExaminar);
	}
	

	private ImagenDeFondo obtenerImagenMaterial() {
		return imagenMaterial;
	}

	private void establecerImagenMaterial(String rutaAbsoluta) {
		this.imagenMaterial.establecerImagen(rutaAbsoluta);
	}
}
