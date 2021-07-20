package entidad;

public class Profesor {
	private String IdProfesor;
	private String Nombre;
	private String Apellidos;
	private String DNI;
	private String IdCrso;
	private String curso;
	public String getIdProfesor() {
		return IdProfesor;
	}
	public void setIdProfesor(String idProfesor) {
		IdProfesor = idProfesor;
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
	public String getIdCrso() {
		return IdCrso;
	}
	public void setIdCrso(String idCrso) {
		IdCrso = idCrso;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
}
