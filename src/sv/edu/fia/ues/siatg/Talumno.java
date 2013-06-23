package sv.edu.fia.ues.siatg;

public class Talumno {
	private String id_integrante;
	private String carnet;
	private String correo_a;
	private String nombres_a;
	private String apellidos_a;
	private String telefono_a;
	private String direccion;

	public Talumno() {
	}

	public Talumno(String carnet, String id_integrante, String telefono_a, String correo_a, String nombres_a, String apellidos_a, String direccion) {
		this.id_integrante = id_integrante;
		this.carnet = carnet;
		this.correo_a = correo_a;
		this.nombres_a = nombres_a;
		this.apellidos_a = apellidos_a;
		this.telefono_a = telefono_a;
		this.direccion = direccion;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public String getId_integrante() {
		return id_integrante;
	}
	public void setId_integrante(String id_integrante) {
		this.id_integrante = id_integrante;
	}
	
	public String getCorreo_a() {
		return correo_a;
	}
	public void setCorreo_a(String correo_a) {
		this.correo_a = correo_a;
	}

	public String getNombres_a() {
		return nombres_a;
	}

	public void setNombres_a(String nombres_a) {
		this.nombres_a = nombres_a;
	}
	public String getApellidos_a() {
		return apellidos_a;
	}
	public void setApellidos_a(String apellidos_a) {
		this.apellidos_a = apellidos_a;
	}
	public void setTelefono_a(String telefono_a) {
		this.telefono_a = telefono_a;
	}
	public String getTelefono_a() {
		return telefono_a;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDireccion() {
		return direccion;
	}


}

