package entidad;

public class Alumno {
	private String IdAlumno;
	private String Nombre;
	private String Apellidos;
	private String DNI;
	private String fecNac;
	private String fecReg;
	public String getIdAlumno() {
		return IdAlumno;
	}
	public void setIdAlumno(String idAlumno) {
		IdAlumno = idAlumno;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getFecNac() {
		return fecNac;
	}
	public void setFecNac(String fecNac) {
		this.fecNac = fecNac;
	}
	public String getFecReg() {
		return fecReg;
	}
	public void setFecReg(String fecReg) {
		this.fecReg = fecReg;
	}
	
	
}
