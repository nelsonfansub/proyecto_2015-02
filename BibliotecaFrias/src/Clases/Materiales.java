package Clases;

public class Materiales {
	
	private String idmaterial;
	private String titulo;
	private String ubicacionfisica;
	private String aniopublicacion;
	private String tipomaterial;
	private String clasificaciontematica;
	private String condicionfisica;
	
	public Materiales(String idmaterial, String titulo, String ubicacionfisica,
			String aniopublicacion, String tipomaterial,
			String clasificaciontematica, String condicionfisica) {
		super();
		this.idmaterial = idmaterial;
		this.titulo = titulo;
		this.ubicacionfisica = ubicacionfisica;
		this.aniopublicacion = aniopublicacion;
		this.tipomaterial = tipomaterial;
		this.clasificaciontematica = clasificaciontematica;
		this.condicionfisica = condicionfisica;
	}

	public String getIdmaterial() {
		return idmaterial;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUbicacionfisica() {
		return ubicacionfisica;
	}

	public String getAniopublicacion() {
		return aniopublicacion;
	}

	public String getTipomaterial() {
		return tipomaterial;
	}

	public String getClasificaciontematica() {
		return clasificaciontematica;
	}

	public String getCondicionfisica() {
		return condicionfisica;
	}

	public void setIdmaterial(String idmaterial) {
		this.idmaterial = idmaterial;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setUbicacionfisica(String ubicacionfisica) {
		this.ubicacionfisica = ubicacionfisica;
	}

	public void setAniopublicacion(String aniopublicacion) {
		this.aniopublicacion = aniopublicacion;
	}

	public void setTipomaterial(String tipomaterial) {
		this.tipomaterial = tipomaterial;
	}

	public void setClasificaciontematica(String clasificaciontematica) {
		this.clasificaciontematica = clasificaciontematica;
	}

	public void setCondicionfisica(String condicionfisica) {
		this.condicionfisica = condicionfisica;
	}
	
	
	
	
	
	
	
	

}
