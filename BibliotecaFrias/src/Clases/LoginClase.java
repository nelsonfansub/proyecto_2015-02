package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginClase {
	
	private static String usuario;
	private String contraseña;
	
	public LoginClase(String usuario, String contraseña) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	public LoginClase(){
		
	}

	public static String getUsuario() {
		return usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public void CargarCodigoUsuario() throws ClassNotFoundException, SQLException
	{
		
		Connection conexx = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca", "nelson", "12345");
		Statement us;
		ResultSet idbib = null;
					
		us = conexx.createStatement();
		idbib = us.executeQuery("Select idbibliotecarios FROM bibliotecarios;");
		
		while(idbib.next())
		{
			this.usuario = idbib.getString(1);
		}
		
		
	}
	
	public void CargarClaveUsuario() throws ClassNotFoundException, SQLException
	{
		
		Connection conexx = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca", "nelson", "12345");
		Statement us;
		ResultSet cont = null;
					
		us = conexx.createStatement();
		cont = us.executeQuery("Select clave FROM bibliotecarios;");
		
		while(cont.next())
		{
			this.contraseña = cont.getString(1);
		}
		
	}
	
		


}
