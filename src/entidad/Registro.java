package entidad;

public class Registro {
	private String idREGISTRO;
	private String IdAlumno;
	private String nombreCom;
	private String IdCurso;
	private String curso;
	public String getIdAlumno() {
		return IdAlumno;
	}
	public void setIdAlumno(String idAlumno) {
		IdAlumno = idAlumno;
	}
	public String getIdCurso() {
		return IdCurso;
	}
	public void setIdCurso(String idCurso) {
		IdCurso = idCurso;
	}
	public String getNombreCom() {
		return nombreCom;
	}
	public void setNombreCom(String nombreCom) {
		this.nombreCom = nombreCom;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getIdREGISTRO() {
		return idREGISTRO;
	}
	public void setIdREGISTRO(String idREGISTRO) {
		this.idREGISTRO = idREGISTRO;
	}
	
}
