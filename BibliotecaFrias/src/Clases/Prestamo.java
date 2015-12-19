package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Prestamo {
	
	// para el encabezado del prestamo de material
	private int idPrestamo;
	private int iDpersona;
	private String bibliotecario;
	private String horaEntrega ;
	private String fechaEntrega;
	private String horaPrestamo;
	private String fechaPrestamo;
	private String lecturaSala;
	// para el detalle
	private int iDMaterial;
	private String condicionFisica;
	
	//Estos campos solo son para mostrar
	private String nombreUsuario = "";
	private String tipoUsuario;
	private String telefono;
	private String DescripcionMaterial;
	private String AutorMaterial;
	private String tiponMaterial;
	private String ubicacionFisica;
	
	
	///---- GET ---///

    public int getIdPrestamo() {
		return idPrestamo;
	}

	public int getiDpersona() {
		return iDpersona;
	}

	public String getBibliotecario() {
		return bibliotecario;
	}

	public String getHoraEntrega() {
		return horaEntrega;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public String getHoraPrestamo() {
		return horaPrestamo;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public String getLecturaSala() {
		return lecturaSala;
	}

	public int getiDMaterial() {
		return iDMaterial;
	}

	public String getCondicionFisica() {
		return condicionFisica;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDescripcionMaterial() {
		return DescripcionMaterial;
	}

	public String getAutorMaterial() {
		return AutorMaterial;
	}

	public String getTiponMaterial() {
		return tiponMaterial;
	}

	public String getUbicacionFisica() {
		return ubicacionFisica;
	}



   ///------- SET------------///
	
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public void setiDpersona(int iDpersona) {
		this.iDpersona = iDpersona;
	}

	public void setBibliotecario(String bibliotecario) {
		this.bibliotecario = bibliotecario;
	}

	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public void setHoraPrestamo(String horaPrestamo) {
		this.horaPrestamo = horaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public void setLecturaSala(String lecturaSala) {
		this.lecturaSala = lecturaSala;
	}

	public void setiDMaterial(int iDMaterial) {
		this.iDMaterial = iDMaterial;
	}

	public void setCondicionFisica(String condicionFisica) {
		this.condicionFisica = condicionFisica;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setDescripcionMaterial(String descripcionMaterial) {
		DescripcionMaterial = descripcionMaterial;
	}

	public void setAutorMaterial(String autorMaterial) {
		AutorMaterial = autorMaterial;
	}

	public void setTiponMaterial(String tiponMaterial) {
		this.tiponMaterial = tiponMaterial;
	}

	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}

	
	//--------------------Contructores -----------------------///
	
    // contructor vacio
	public Prestamo(){
		
	}
	
	/// este constructur para el detalle
	public Prestamo(int idPrestamo, int iDMaterial, String condicionFisica) {
		super();
		this.idPrestamo = idPrestamo;
		this.iDMaterial = iDMaterial;
		this.condicionFisica = condicionFisica;
	}


	// este constructor es para en encabezado
	public Prestamo(int iDpersona, String bibliotecario, String horaEntrega,
			String fechaEntrega, String horaPrestamo, String fechaPrestamo,
			String lecturaSala) {
		super();
		this.iDpersona = iDpersona;
		this.bibliotecario = bibliotecario;
		this.horaEntrega = horaEntrega;
		this.fechaEntrega = fechaEntrega;
		this.horaPrestamo = horaPrestamo;
		this.fechaPrestamo = fechaPrestamo;
		this.lecturaSala = lecturaSala;
	}
	
	
	// constructor de toda la clase
	   public Prestamo(int idPrestamo, int iDpersona, String bibliotecario,
				String horaEntrega, String fechaEntrega, String horaPrestamo,
				String fechaPrestamo, String lecturaSala, int iDMaterial,
				String condicionFisica, String nombreUsuario, String tipoUsuario,
				String telefono, String descripcionMaterial, String autorMaterial,
				String tiponMaterial, String ubicacionFisica) {
			super();
			this.idPrestamo = idPrestamo;
			this.iDpersona = iDpersona;
			this.bibliotecario = bibliotecario;
			this.horaEntrega = horaEntrega;
			this.fechaEntrega = fechaEntrega;
			this.horaPrestamo = horaPrestamo;
			this.fechaPrestamo = fechaPrestamo;
			this.lecturaSala = lecturaSala;
			this.iDMaterial = iDMaterial;
			this.condicionFisica = condicionFisica;
			this.nombreUsuario = nombreUsuario;
			this.tipoUsuario = tipoUsuario;
			this.telefono = telefono;
			DescripcionMaterial = descripcionMaterial;
			AutorMaterial = autorMaterial;
			this.tiponMaterial = tiponMaterial;
			this.ubicacionFisica = ubicacionFisica;
		}
	   
	   

	//---------------------------constructor para mostrar datos de usuario ------------------//
	   
	   public Prestamo(int iDpersona, String nombreUsuario, String tipoUsuario,
			String telefono) {
		super();
		this.iDpersona = iDpersona;
		this.nombreUsuario = nombreUsuario;
		this.tipoUsuario = tipoUsuario;
		this.telefono = telefono;
	}

	//--------------------- GUARDAR DATOS Encabezado de prestamo en tabla prestamo --------------------------------//
	  
	   
	
	public int guardarDatosEncabezado() throws ClassNotFoundException, SQLException {

		   Connection cn = Conexion.Conectar(); 
		
		   
		   String sql = "INSERT INTO prestamo(idPersona, bibliotecario, horaEntrega, fechaEntrega, horaPrestamo, fechaPrestamo, lecturaSala) VALUES (?,?,?,?,?,?,?)";
		   PreparedStatement instruccion = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		   
		   instruccion.setInt(1, getiDpersona());
		   instruccion.setString(2, getBibliotecario());
		   instruccion.setString(3, getHoraEntrega());
		   instruccion.setString(4, getFechaEntrega());
		   instruccion.setString(5, getHoraPrestamo());
		   instruccion.setString(6, getFechaPrestamo());
		   instruccion.setString(7, getLecturaSala());
		   
		   System.out.println("En la clase" +getiDpersona() + getBibliotecario() +  getHoraEntrega());
		   
		   instruccion.execute(); 
					
		   ResultSet clavesGeneradas = instruccion.getGeneratedKeys();
					
			int idPrestamoGenerado = 0;
			while(clavesGeneradas.next())
			{
				idPrestamoGenerado = Integer.parseInt( clavesGeneradas.getObject(1).toString() );
			}	
			System.out.println(idPrestamoGenerado);	
			setIdPrestamo(idPrestamoGenerado);
			return idPrestamoGenerado;
		}
	
	
}
