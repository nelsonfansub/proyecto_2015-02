import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Clases.ImagenesyOtros;
import Clases.Limpiar;
import Clases.Utilidades;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.*;

import javax.swing.DefaultComboBoxModel;

import Clases.*;
public class VentanaImagenesYotros extends JFrame {
	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField textCodigo;
	private JTextField textTitulo;
	private JTextField textAutor;
	private JLabel lblTamanio;
	private String tipo;
	private String tamanio;
	private String tipoMaterial;
	private String publicacion;
	private String tematica;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaImagenesYotros frame = new VentanaImagenesYotros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaImagenesYotros() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Imagenes y Otros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane_1 = new CambiarImagenDelFondo("imagenes/Fondo.png");
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblCdigo = new JLabel("Codigo:");
		lblCdigo.setBounds(38, 64, 46, 14);
		contentPane_1.add(lblCdigo);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setBounds(126, 61, 86, 20);
		contentPane_1.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblTtulo = new JLabel("Titulo:");
		lblTtulo.setBounds(38, 92, 46, 14);
		contentPane_1.add(lblTtulo);
		
		textTitulo = new JTextField();
		textTitulo.setColumns(10);
		textTitulo.setBounds(126, 89, 208, 20);
		contentPane_1.add(textTitulo);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(38, 120, 46, 14);
		contentPane_1.add(lblAutor);
		
		textAutor = new JTextField();
		textAutor.setColumns(10);
		textAutor.setBounds(126, 117, 208, 20);
		contentPane_1.add(textAutor);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(38, 148, 46, 14);
		contentPane_1.add(lblTipo);
		
		lblTamanio = new JLabel("Tamano:");
		lblTamanio.setBounds(38, 177, 58, 14);
		contentPane_1.add(lblTamanio);
		
		JComboBox comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Imagen", "Mapa"}));
		comboTipo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tipo = ((JComboBox)(e.getSource())).getSelectedItem().toString();
			}
		});
		comboTipo.setBounds(126, 143, 120, 20);
		contentPane_1.add(comboTipo);
		
		JComboBox comboTamanio = new JComboBox();
		comboTamanio.setModel(new DefaultComboBoxModel(new String[] {"640 x 480", "1024 x 768", "1600 x 1200", "1280 x 960"}));
		comboTamanio.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tamanio = ((JComboBox)(e.getSource())).getSelectedItem().toString();
			}
		});
		comboTamanio.setBounds(126, 172, 86, 20);
		contentPane_1.add(comboTamanio);
		
		JComboBox comboBoxTematica = new JComboBox();
		try {
			ImagenesyOtros.CargarComboBoxTematica(comboBoxTematica);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		comboBoxTematica.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tematica = ((JComboBox)(e.getSource())).getSelectedItem().toString();
				try {
					String idTematica = RegistrarLibros.CargarIdTematica(tematica).toString();
					int idt = Integer.parseInt(idTematica);
					System.out.println(idt);
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBoxTematica.setBounds(450, 87, 92, 20);
		contentPane_1.add(comboBoxTematica);
		
		JComboBox comboTipoMaterial = new JComboBox();
		comboTipoMaterial.setModel(new DefaultComboBoxModel(new String[] {"", "Libros", "Audio Visuales"}));
		getContentPane().add(comboTipoMaterial, BorderLayout.SOUTH);
		comboTipoMaterial.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tipoMaterial = ((JComboBox)(e.getSource())).getSelectedItem().toString();
			}
		});
		comboTipoMaterial.setBounds(450, 115, 92, 20);
		contentPane_1.add(comboTipoMaterial);
		
		JComboBox comboAnioP = new JComboBox(Utilidades.obtenerFechas());
		comboAnioP.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				publicacion = ((JComboBox)(e.getSource())).getSelectedItem().toString();
			}
		});
		comboAnioP.setBounds(450, 143, 92, 20);
		contentPane_1.add(comboAnioP);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(38, 212, 73, 14);
		contentPane_1.add(lblDescripcion);
		
		final JTextArea textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(126, 207, 208, 70);
		contentPane_1.add(textAreaDescripcion);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String titulo = textTitulo.getText();
					String autor = textAutor.getText();
					String descripcion = textAreaDescripcion.getText();
					
					ImagenesyOtros nuevaImagen = new ImagenesyOtros();
					
					nuevaImagen.setTitulo(titulo);
					nuevaImagen.setAutor(autor);
					nuevaImagen.setDescripcion(descripcion);
					nuevaImagen.setTipo(tipo);
					nuevaImagen.setTamanio(tamanio);
					nuevaImagen.setTipomaterial(tipoMaterial);
					nuevaImagen.setAniopublicacion(publicacion);
					
					nuevaImagen.CargarRegistroMateriales();
					nuevaImagen.CargarRegistroImagenesOtros();
					Limpiar.limpiarCampos(VentanaImagenesYotros.this.getContentPane());
					JOptionPane.showMessageDialog(VentanaImagenesYotros.this, "Operacion realizada Exitosamente!");
					
				
				} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
	
			}
			});
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setIcon(new ImageIcon(VentanaImagenesYotros.class.getResource("/imagenes/saveHS.png")));
		btnGuardar.setBounds(422, 226, 120, 20);
		contentPane_1.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(VentanaImagenesYotros.class.getResource("/imagenes/DeleteHS.png")));
		btnCancelar.setBounds(422, 257, 120, 20);
		contentPane_1.add(btnCancelar);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(VentanaImagenesYotros.class.getResource("/imagenes/Find.png")));
		btnBuscar.setBounds(222, 46, 35, 33);
		contentPane_1.add(btnBuscar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(VentanaImagenesYotros.class.getResource("/imagenes/NewDocumentHS.png")));
		btnActualizar.setBounds(422, 195, 120, 20);
		contentPane_1.add(btnActualizar);
		
		JLabel lblAoDePunblicacion = new JLabel("Anio de Publicacion:");
		lblAoDePunblicacion.setBounds(331, 147, 126, 16);
		contentPane_1.add(lblAoDePunblicacion);
		
		JLabel lblTematica = new JLabel("Tematica:");
		lblTematica.setBounds(353, 91, 67, 16);
		contentPane_1.add(lblTematica);
		
		JLabel lblTipo_1 = new JLabel("Tipo de Material:");
		lblTipo_1.setBounds(353, 119, 110, 16);
		contentPane_1.add(lblTipo_1);
	
	}
}	
