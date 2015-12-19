package Clases;

public class Inventario {
	
	private int iDMaterial;
	private String descripcion;
	private String tipoMaterial;
	private String clasificacionTematica;
	private String estado;
	private String ubicacionFisica;
	

	public int getiDMaterial() {
		return iDMaterial;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getTipoMaterial() {
		return tipoMaterial;
	}
	public String getClasificacionTematica() {
		return clasificacionTematica;
	}
	public String getEstado() {
		return estado;
	}
	public String getUbicacionFisica() {
		return ubicacionFisica;
	}
	

	public void setiDMaterial(int iDMaterial) {
		this.iDMaterial = iDMaterial;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
	public void setClasificacionTematica(String clasificacionTematica) {
		this.clasificacionTematica = clasificacionTematica;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}
	
	public Inventario(){
		
	}
	
	public Inventario(int iDMaterial, String descripcion,
			String tipoMaterial, String clasificacionTematica, String estado,
			String ubicacionFisica) {
		super();
		this.iDMaterial = iDMaterial;
		this.descripcion = descripcion;
		this.tipoMaterial = tipoMaterial;
		this.clasificacionTematica = clasificacionTematica;
		this.estado = estado;
		this.ubicacionFisica = ubicacionFisica;
	}
	
	public Inventario(int iDMaterial, String descripcion,
			String tipoMaterial, String clasificacionTematica) {
		super();
		this.iDMaterial = iDMaterial;
		this.descripcion = descripcion;
		this.tipoMaterial = tipoMaterial;
		this.clasificacionTematica = clasificacionTematica;
	}
	
	
	

}
