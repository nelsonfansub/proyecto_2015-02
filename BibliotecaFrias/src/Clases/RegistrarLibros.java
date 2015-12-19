package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

public class RegistrarLibros extends Materiales {
	
	private int idlibro;
	private String isbn;
	private String titulo;
	private String autor;
	private String edicion;
	private String casaeditora;
	private String genero;
	private String tematica;
	private int numeropaginas;
	private int id = 0;
	
	public RegistrarLibros(String idmaterial, String isbn,String titulo,
			String ubicacionfisica, String aniopublicacion,
			String tipomaterial, String clasificaciontematica,
			String condicionfisica, int idlibro, String autor,
			String edicion, String casaeditora, String genero,
			int numeropaginas) {
		super(idmaterial, titulo, ubicacionfisica, aniopublicacion,
				tipomaterial, clasificaciontematica, condicionfisica);
		this.idlibro = idlibro;
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.edicion = edicion;
		this.casaeditora = casaeditora;
		this.genero = genero;
		this.tematica = tematica;
		this.numeropaginas = numeropaginas;
	}
	
	public RegistrarLibros() {
		this("","","","","","","","",0,"","","","",0);
	}


	public int getIdlibro() {
		return idlibro;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public String getAutor() {
		return autor;
	}

	public String getEdicion() {
		return edicion;
	}

	public String getCasaeditora() {
		return casaeditora;
	}

	public String getGenero() {
		return genero;
	}

	public int getNumeropaginas() {
		return numeropaginas;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public void setCasaeditora(String casaeditora) {
		this.casaeditora = casaeditora;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setNumeropaginas(int numeropaginas) {
		this.numeropaginas = numeropaginas;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
	public void Mostrar (){
		System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%d\n", getIdlibro(),getIsbn(),getTitulo(),getAutor(),getEdicion(),getCasaeditora(),
				getAniopublicacion(),getClasificaciontematica(),getNumeropaginas());
	}
	
	public void CargarRegistroLibroNuevo() throws ClassNotFoundException, SQLException
	{
		Connection conexion = Conexion.Conectar();
		
		String sql = "INSERT INTO libro(isbn, edicion, casaeditora, genero, numeropaginas, idlibro) VALUES (?,?,?,?,?,?)";
		PreparedStatement ejecuta = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ejecuta.setString(1, this.getIsbn());
		ejecuta.setString(2, this.getEdicion() );
		ejecuta.setString(3, this.getCasaeditora() );
		ejecuta.setString(4, this.getGenero() );
		ejecuta.setInt(5, this.getNumeropaginas());
		ejecuta.setInt(6, this.getIdlibro());
		
		ejecuta.execute();
		
		
	} 
	
	public void CargarRegistroMateriales() throws ClassNotFoundException, SQLException
	{
		Connection conexion = Conexion.Conectar();
		
		String sql = "INSERT INTO material(titulo, aniopublicacion, tipomaterial) VALUES (?,?,?)";
		PreparedStatement ejecuta = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ejecuta.setString(1, this.getTitulo());
		ejecuta.setString(2, this.getAniopublicacion());
		ejecuta.setString(3, this.getTipomaterial());
		ejecuta.execute();
	
		
		ResultSet rs = ejecuta.executeQuery("select idmaterial from material");
		while(rs.next())
		{
			id = rs.getInt(1);
			this.setIdlibro(id);
		}
	
		ejecuta.execute("insert into libro(idlibro) values ('"+id+"')");
		
	}
	public void CargarRegistroAutor() throws ClassNotFoundException, SQLException
	{
		Connection conexion = Conexion.Conectar();
		
		String sqlAutor = "INSERT INTO autor(nombreAutor) VALUES (?)";
		PreparedStatement ejecutaA = conexion.prepareStatement(sqlAutor, Statement.RETURN_GENERATED_KEYS);
		
		String sqlIdMaterial = "Select idmaterial FROM material order by idmaterial desc limit 1;";
		PreparedStatement ejecutaM = conexion.prepareStatement(sqlIdMaterial, Statement.RETURN_GENERATED_KEYS);
		
		String sqlIdAutor = "INSERT INTO autor(idmaterial) VALUES (?)";
		PreparedStatement ejecutaIA = conexion.prepareStatement(sqlAutor, Statement.RETURN_GENERATED_KEYS);
		
		ejecutaA.setString(1,this.getAutor());
		
				
		ejecutaA.execute();
		
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
	
	public static ResultSet CargarIdTematica(String tema) throws ClassNotFoundException, SQLException
	{
		
		Connection conexion = Conexion.Conectar();
		PreparedStatement us;
		PreparedStatement ps;
		ResultSet idtem = null;
					
		us = conexion.prepareStatement("Select idclasificaciontematica FROM clasificaciontematica WHERE nombre = ?");
		us.setString(1,tema);
		idtem = us.executeQuery();
		
		
		return idtem;			
		
		
	}

}
