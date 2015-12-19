package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

public class ImagenesyOtros extends Materiales{
	
	private String idimagenesyotros;
	private String tamanio;
	private String descripcion;
	private String tipo;
	private String autor;
	public ImagenesyOtros(String idmaterial, String titulo,
			String ubicacionfisica, String aniopublicacion,
			String tipomaterial, String clasificaciontematica,
			String condicionfisica, String idimagenesyotros, String tamanio,
			String descripcion, String tipo,String autor) {
		super(idmaterial, titulo, ubicacionfisica, aniopublicacion,
				tipomaterial, clasificaciontematica, condicionfisica);
		this.idimagenesyotros = idimagenesyotros;
		this.tamanio = tamanio;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.autor = autor;
	}

	public ImagenesyOtros() {
		this("","","","","","","","","","","","");
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIdimagenesyotros() {
		return idimagenesyotros;
	}

	public String getTamanio() {
		return tamanio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setIdimagenesyotros(String idimagenesyotros) {
		this.idimagenesyotros = idimagenesyotros;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void CargarRegistroMateriales() throws ClassNotFoundException, SQLException
	{
		Connection conexx = Conexion.Conectar();
		String sql = "INSERT INTO material(titulo, aniopublicacion, tipomaterial) VALUES (?,?,?)";
		PreparedStatement ejecuta = conexx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ejecuta.setString(1, this.getTitulo());
		ejecuta.setString(2, this.getAniopublicacion());
		ejecuta.setString(3, this.getTipomaterial());
		
		
		ejecuta.execute();
		
	}
	
	public void CargarRegistroImagenesOtros() throws ClassNotFoundException, SQLException
	{
		Connection conexx = Conexion.Conectar();
		String sql = "INSERT INTO imagenesotros(tamanio, descripcion, tipo) VALUES (?,?,?)";
		PreparedStatement ejecuta = conexx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ejecuta.setString(1, this.getTamanio());
		ejecuta.setString(2, this.getDescripcion());
		ejecuta.setString(3, this.getTipo());
	
		
		
		ejecuta.execute();
		
	}
	
	public static void CargarComboBoxTematica(JComboBox box) throws ClassNotFoundException, SQLException
	{
	
		Connection conexion = Conexion.Conectar();
		Statement us;
		ResultSet idmat = null;
					
		us = conexion.createStatement();
		idmat = us.executeQuery("Select nombre FROM clasificaciontematica;");
		
		while(idmat.next()){
			
			 box.addItem(idmat.getString(1));
			 
		}
				
		
		
	}

}
