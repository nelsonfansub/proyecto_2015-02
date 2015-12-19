package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Recibo extends Prestamo {
	
	private int iDdevolucion;
	private int iDPrestamo;
	private String DFecha;
	private String Dhora;
	public int getiDdevolucion() {
		return iDdevolucion;
	}
	public int getiDPrestamo() {
		return iDPrestamo;
	}
	public String getDFecha() {
		return DFecha;
	}
	public String getDhora() {
		return Dhora;
	}
	public void setiDdevolucion(int iDdevolucion) {
		this.iDdevolucion = iDdevolucion;
	}
	public void setiDPrestamo(int iDPrestamo) {
		this.iDPrestamo = iDPrestamo;
	}
	public void setDFecha(String dFecha) {
		DFecha = dFecha;
	}
	public void setDhora(String dhora) {
		Dhora = dhora;
	}
	
	public Recibo(int iDPrestamo, String dhora, String dFecha) {
		super();
		this.iDPrestamo = iDPrestamo;
		DFecha = dFecha;
		Dhora = dhora;
	}
	
	
	public int guardarDatosEncabezadoRecibo()throws ClassNotFoundException, SQLException  {
		
		   Connection cn = Conexion.Conectar(); 
		   String sql = "INSERT INTO devolucion(idPrestamo, hora, fecha) VALUES (?,?,?)";
		   PreparedStatement instruccion = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		   
		   instruccion.setInt(1, getiDPrestamo());
		   instruccion.setString(2, getDhora());
		   instruccion.setString(3, getDFecha());
		   instruccion.execute(); 
					
		   ResultSet clavesGeneradas = instruccion.getGeneratedKeys();
					
			int idReciboGenerado = 0;
			while(clavesGeneradas.next())
			{
				idReciboGenerado = Integer.parseInt( clavesGeneradas.getObject(1).toString() );
			}	
			
			System.out.println(idReciboGenerado);	
			setiDdevolucion(idReciboGenerado);
			return idReciboGenerado;
		}
		
	}
	
	
	

