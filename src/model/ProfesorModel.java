package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Profesor;
import util.ConexionMySQL;

public class ProfesorModel {
	public List<Profesor> listaProfesor(){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.*,c.Curso from tb_profesores p inner join tb_cursos c on p.idcurso=c.idCurso";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setIdProfesor(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setDNI(rs.getString(4));
				p.setIdCrso(rs.getString(5));
				p.setCurso(rs.getString(6));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public int insertarProfesor(Profesor p) {
		int insertar=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="insert into tb_profesores values(?,?,?,?,?)";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, p.getIdProfesor());
			pstm.setString(2, p.getNombre());
			pstm.setString(3, p.getApellidos());
			pstm.setString(4, p.getDNI());
			pstm.setString(5, p.getIdCrso());
			System.out.println("SQL-->"+pstm);
			insertar=pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return insertar;
	}
	
	public int actualizarProfesor(Profesor p) {
		int actualizados=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="update tb_profesores set nombre=?,apellidos=?,dni=?,idcurso=? where IdProfesor=?";
			pstm=con.prepareStatement(sql);
			
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getApellidos());
			pstm.setString(3, p.getDNI());
			pstm.setString(4, p.getIdCrso());
			pstm.setString(5, p.getIdProfesor());
			
			System.out.println("SQL-->"+pstm);
			
			actualizados=pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}
	
	public int eliminarProfesor(String idProfesor) {
		int eliminados=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {con=ConexionMySQL.getConexion();
		String sql="delete from tb_profesores where idprofesor=?";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, idProfesor);
		System.out.println("SQL-->"+pstm);
		eliminados=pstm.executeUpdate();
			
		}catch(SQLException e)  {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}
		return eliminados;
	}
	
	public List<Profesor> listaIdProfesor(){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.idprofesor from tb_profesores p order by idprofesor asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setIdProfesor(rs.getString(1));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaNombreProfesor(){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.nombre from tb_profesores p order by nombre asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setNombre(rs.getString(1));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaApellidosProfesor(){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.apellidos from tb_profesores p order by apellidos asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setApellidos(rs.getString(1));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaDNIProfesor(){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.DNI from tb_profesores p order by DNI asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setDNI(rs.getString(1));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaIdCursoProfesor(){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select c.idcurso from tb_cursos c order by idcurso asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setIdCrso(rs.getString(1));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaIdProfesor(String idProfesor){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.*,c.Curso from tb_profesores p inner join tb_cursos c on p.idcurso=c.idCurso where idprofesor=?  order by idprofesor asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, idProfesor);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setIdProfesor(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setDNI(rs.getString(4));
				p.setIdCrso(rs.getString(5));
				p.setCurso(rs.getString(6));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaNombreProfesor(String nombre){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.*,c.Curso from tb_profesores p inner join tb_cursos c on p.idcurso=c.idCurso where nombre=?  order by idprofesor asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, nombre);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setIdProfesor(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setDNI(rs.getString(4));
				p.setIdCrso(rs.getString(5));
				p.setCurso(rs.getString(6));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaApellidosProfesor(String apellidos){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.*,c.Curso from tb_profesores p inner join tb_cursos c on p.idcurso=c.idCurso where apellidos=?  order by idprofesor asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, apellidos);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setIdProfesor(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setDNI(rs.getString(4));
				p.setIdCrso(rs.getString(5));
				p.setCurso(rs.getString(6));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaDNIProfesor(String dni){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.*,c.Curso from tb_profesores p inner join tb_cursos c on p.idcurso=c.idCurso where dni=?  order by idprofesor asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dni);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setIdProfesor(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setDNI(rs.getString(4));
				p.setIdCrso(rs.getString(5));
				p.setCurso(rs.getString(6));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public List<Profesor> listaIdCursoProfesor(String idCurso){
		List<Profesor> lista=new ArrayList<Profesor>();
		Connection con =null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.*,c.Curso from bd_instituto.tb_profesores p inner join bd_instituto.tb_cursos c on p.idcurso=c.idCurso where p.idcurso=? order by idcurso asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, idCurso);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Profesor p;
			while(rs.next()) {
				p=new Profesor();
				p.setIdProfesor(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setDNI(rs.getString(4));
				p.setIdCrso(rs.getString(5));
				p.setCurso(rs.getString(6));
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	public String ultmoCodigo(){
		String codigo="";
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.idprofesor from tb_profesores p order by idprofesor desc limit 1";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			while(rs.next()) {
				codigo=rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return codigo;
	}
	
	public String buscaDNI(String dni){
		String existe="";
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select p.dni from tb_profesores p where dni=? limit 1";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dni);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				existe=rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return existe;
	}
}
