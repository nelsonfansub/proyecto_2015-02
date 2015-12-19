package Clases;

import java.sql.*;


public class Conexion {
	
	public Conexion(){
		
	}
	
	public static Connection Conectar(){ 
		
		Connection conexion = null;
		
		try{
            		   
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca", "root", "12345");
			
		   System.out.println( "conexion OK" );
		}catch(Exception e){
			
		}
		 return conexion;
		
	}
	
	//-----------------para seleccionar todos los usuarios de la tabla buscar Usuarios----------------------//
	
	public ResultSet SeleccionarUsuario(){
		
		Connection cn = Conectar();
		Statement st;
		ResultSet rs = null;
		
		try {
			
			st = cn.createStatement();
			rs = st.executeQuery("Select idPersona, nombre, apellido, sexo, tipoUsuario FROM persona");
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
		
	}
	
	//--------------------para filtar un usuario por id directo a la tabla-----------------//
	
	public ResultSet SeleccionarPorID(int id){
		
		Connection cn ;
		PreparedStatement pst;
		ResultSet rs = null;
		
		try {
			cn = Conectar();
			pst = cn.prepareStatement("Select idPersona, nombre, apellido, sexo, tipoUsuario FROM persona WHERE idpersona = ?" );
			pst.setInt(1, id);
			rs = pst.executeQuery(); 
	
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
		
	}
	
	//------------------- para filtrar un usuario por nombre -----------------------------//
	
	public ResultSet SeleccionarPorNombre(String nombre){
		
		Connection cn ;
		PreparedStatement pst;
		ResultSet rs = null;
		
		try {
			cn = Conectar();
			pst = cn.prepareStatement("Select idPersona, nombre, apellido, sexo, tipoUsuario FROM persona WHERE nombre like ?" );
			pst.setString(1, nombre);
			rs = pst.executeQuery(); // para ejecutar el query cuando es con where
			
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
		
	}
	
	//------------------para seleccionar los materiales en el form buscar--------------------------------//

	public ResultSet SeleccionarInventario() {
		
		Connection cn = Conectar();
		Statement st;
		ResultSet rs = null;
		
		try {
			
			st = cn.createStatement();
			rs = st.executeQuery("SELECT inventario.idInventario, inventario.idmaterial, material.titulo,  material.tipoMaterial, inventario.ubicacionFisica, inventario.estado FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.estado <> 'Prestado' and inventario.estado <> 'No Disponible'");
			
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
	}

	//------------------------------Para seleccionar Inventario Por ID -------------------------------//
	
	public ResultSet SeleccionarInventarioPorID(int id) {
		
		Connection cn ;
		PreparedStatement pst;
		ResultSet rs = null;
		
		try {
			cn = Conectar();
			pst = cn.prepareStatement("SELECT inventario.idInventario, inventario.idmaterial, material.titulo,  material.tipoMaterial, inventario.ubicacionFisica, inventario.estado FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.idInventario = ? and inventario.estado <> 'Prestado' and inventario.estado <> 'No Disponible'" );
			pst.setInt(1, id);
			rs = pst.executeQuery(); // para ejecutar el query cuando es con where
			
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
	}
	

	public ResultSet SeleccionarInventarioPorDescripcion(String descripcion) {
		
		Connection cn ;
		PreparedStatement pst;
		ResultSet rs = null;
		
		try {
			cn = Conectar();
			pst = cn.prepareStatement("SELECT inventario.idInventario, inventario.idmaterial, material.titulo,  material.tipoMaterial, inventario.ubicacionFisica, inventario.estado FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and material.titulo like ? and inventario.estado <> 'Prestado' and inventario.estado <> 'No Disponible'" );
			pst.setString(1, descripcion);
			rs = pst.executeQuery(); 
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
		
	}

	public ResultSet SeleccionarPrestamo() {
		
		Connection cn = Conectar();
		Statement st;
		ResultSet rs = null;
		
		try {
			
			st = cn.createStatement();
			rs = st.executeQuery("SELECT prestamo.idPrestamo, prestamo.idpersona, persona.nombre, prestamo.horaprestamo, prestamo.fechaprestamo FROM prestamo, persona WHERE prestamo.idpersona = persona.idpersona");
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet SeleccionarPorIDPrestamo(int id) {
		
		Connection cn ;
		PreparedStatement pst;
		ResultSet rs = null;
		
		try {
			cn = Conectar();
			pst = cn.prepareStatement("SELECT prestamo.idPrestamo, prestamo.idpersona, persona.nombre, prestamo.horaprestamo, prestamo.fechaprestamo FROM prestamo, persona WHERE prestamo.idpersona = persona.idpersona AND prestamo.idPrestamo = ?" );
			pst.setInt(1, id);
			rs = pst.executeQuery(); 
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet SeleccionarPorNombrePrestamo(String nombre) {
		
		Connection cn ;
		PreparedStatement pst;
		ResultSet rs = null;
		
		try {
			cn = Conectar();
			pst = cn.prepareStatement("SELECT prestamo.idPrestamo, prestamo.idpersona, persona.nombre, prestamo.horaprestamo, prestamo.fechaprestamo FROM prestamo, persona WHERE prestamo.idpersona = persona.idpersona AND persona.nombre like ?" );
			pst.setString(1, nombre);
			rs = pst.executeQuery(); 
			
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
	}

	//------------------Para seleccionar datos de la tabla-----------------------//
	public ResultSet SeleccionarMaterial() {
		
		Connection cn = Conectar();
		Statement st;
		ResultSet rs = null;
		
		try {
			
			st = cn.createStatement();
			rs = st.executeQuery("SELECT idmaterial, titulo,  tipoMaterial FROM  material");
			
			
		}catch(SQLException ex){

			ex.printStackTrace();
		}
		
		return rs;
	}
	
	//-----------------------------Seleccionar Material por ID ----------------------------------------------//
		public ResultSet SeleccionarMaterialPorID(int id) {
			
			Connection cn ;
			PreparedStatement pst;
			ResultSet rs = null;
			
			try {
				cn = Conectar();
				pst = cn.prepareStatement("SELECT idmaterial, titulo,  tipoMaterial FROM  material WHERE idMaterial = ?" );
				pst.setInt(1, id);
				rs = pst.executeQuery(); 

			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}
		
//------------------------------Seleccionar Material por ID --------------------------------//

		public ResultSet SeleccionarMaterialPorDescripcion(String descripcion) {
			Connection cn ;
			PreparedStatement pst;
			ResultSet rs = null;
			
			try {
				cn = Conectar();
				pst = cn.prepareStatement("SELECT idmaterial, titulo,  tipoMaterial FROM  material WHERE titulo like ?" );
				pst.setString(1, descripcion);
				rs = pst.executeQuery(); 
				
			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}

		//------------------Para seleccionar datos de la tabla-----------------------//
		
		public ResultSet SeleccionarMaterialSolicitado()
		{
			Connection cn = Conectar();
			Statement st;
			ResultSet rs = null;
			
			try {
				
				st = cn.createStatement();
				rs = st.executeQuery("SELECT idMaterialSolicitado, tipoMaterial,  nombre, autor, "
						+ "clasificacionTematica, cantidadDeSolicitudes FROM  materialSolicitado");
				
			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}
		
//------------------------para buscar todos los recibos--------------------\\
		
		public ResultSet SeleccionarRecibo() {
				
				Connection cn = Conectar();
				Statement st;
				ResultSet rs = null;
				
				try {
					
					st = cn.createStatement();
					rs = st.executeQuery("SELECT * FROM devolucion");
					
				}catch(SQLException ex){

					ex.printStackTrace();
				}
				
				return rs;
			}

		//-------------------------------Seleccionar por id recibo ------------------------//
		
		public ResultSet SeleccionarPorIDRecibo(int id) {
				
				Connection cn ;
				PreparedStatement pst;
				ResultSet rs = null;
				
				try {
					cn = Conectar();
					pst = cn.prepareStatement("SELECT * from devolucion where idDevolucion = ?" );
					pst.setInt(1, id);
					rs = pst.executeQuery(); 
					
				}catch(SQLException ex){

					ex.printStackTrace();
				}
				
				return rs;
		}

		public ResultSet SeleccionarIDPrestamo(int idPrestamo) {
			Connection cn ;
			PreparedStatement pst;
			ResultSet rs = null;
			
			try {
				cn = Conectar();
				pst = cn.prepareStatement("SELECT * from devolucion where idPrestamo = ?" );
				pst.setInt(1, idPrestamo);
				rs = pst.executeQuery(); 
				
			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}
		
		public ResultSet SeleccionarAudioVideo()
		{
			Connection cn = Conectar();
			Statement st;
			ResultSet rs = null;
			
			try {
				
				st = cn.createStatement();
				rs = st.executeQuery("select material.idmaterial, material.titulo, audiovideo.director, audiovideo.tipo "
						+ "from material, audiovideo where material.idmaterial = audiovideo.idAudioVideo");
				
				
				
			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}

		public ResultSet SeleccionarAudioVideoPorID(int id)
		{
			Connection cn = Conectar();
			Statement st;
			ResultSet rs = null;
			
			try {
				
				st = cn.createStatement();
				rs = st.executeQuery("select material.idmaterial, material.titulo, audiovideo.director, audiovideo.tipo "
						+ "from material, audiovideo where material.idmaterial = audiovideo.idAudioVideo and material.idmaterial = "+ id);

				
				
			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}
		public ResultSet SeleccionarAudioVideoPorNombre(String nombre) {
			Connection cn = Conectar();
			Statement st;
			ResultSet rs = null;
			
			try {
				
				st = cn.createStatement();
				rs = st.executeQuery("select material.idmaterial, material.titulo, audiovideo.director, audiovideo.tipo "
						+ "from material, audiovideo where material.idmaterial = audiovideo.idAudioVideo and material.titulo like '"+ nombre+"'");

				
				
			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}
		
		
		
		
		
		
		public ResultSet SeleccionarInventarioLibro() {
			
			Connection cn = Conectar();
			Statement st;
			ResultSet rs = null;
			
			try {
				
				st = cn.createStatement();
				rs = st.executeQuery("SELECT inventario.idInventario, inventario.idmaterial, material.titulo,  material.tipoMaterial, inventario.ubicacionFisica, inventario.estado FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.estado <> 'Prestado' and inventario.estado <> 'No Disponible' and material.tipoMaterial like 'Libro'");
				
				
			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}

		public ResultSet SeleccionarInventarioPorIDLibro(int id) {
			
			Connection cn ;
			PreparedStatement pst;
			ResultSet rs = null;
			
			try {
				cn = Conectar();
				pst = cn.prepareStatement("SELECT inventario.idInventario, inventario.idmaterial, material.titulo,  material.tipoMaterial, inventario.ubicacionFisica, inventario.estado FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.estado <> 'Prestado' and inventario.estado <> 'No Disponible' and material.tipoMaterial like 'Libro' and inventario.idInventario = ?" );
				pst.setInt(1, id);
				rs = pst.executeQuery(); 

			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}

		public ResultSet SeleccionarInventarioPorDescripcionLibro(
				String descripcion) {
			
			Connection cn ;
			PreparedStatement pst;
			ResultSet rs = null;
			
			try {
				cn = Conectar();
				pst = cn.prepareStatement("SELECT inventario.idInventario, inventario.idmaterial, material.titulo,  material.tipoMaterial, inventario.ubicacionFisica, inventario.estado FROM inventario, material WHERE material.idmaterial = inventario.idmaterial and inventario.estado <> 'Prestado' and inventario.estado <> 'No Disponible' and material.tipoMaterial like 'Libro' and material.titulo like ?"  );
				pst.setString(1, descripcion);
				rs = pst.executeQuery(); 
				
			}catch(SQLException ex){

				ex.printStackTrace();
			}
			
			return rs;
		}
	
	
}
