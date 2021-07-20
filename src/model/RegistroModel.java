package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Registro;
import util.ConexionMySQL;

public class RegistroModel {
	
	public List<Registro> listaRegistrado(){
		List<Registro> lista=new ArrayList<Registro>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select r.idregistro,r.idalumno,a.nombre,a.apellidos,r.idcurso,cu.curso from tb_registrados r inner join tb_alumno a on r.idAlumno=a.idalumno inner join tb_cursos cu on r.idcurso=cu.idcurso order by r.idregistro asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Registro r;
		while(rs.next()) {
			r=new Registro();
			r.setIdREGISTRO(rs.getString(1));
			r.setIdAlumno(rs.getString(2));
			r.setNombreCom(rs.getString(3)+" "+rs.getString(4));
			r.setIdCurso(rs.getString(5));
			r.setCurso(rs.getString(6));
			
			lista.add(r);
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
	
	public int insertar(Registro r) {
		int insertados=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {con=ConexionMySQL.getConexion();
		String sql="insert into tb_registrados values(?,?,?)";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, r.getIdREGISTRO());
		pstm.setString(2, r.getIdAlumno());
		pstm.setString(3, r.getIdCurso());
		System.out.println("SQL-->"+pstm);
		insertados=pstm.executeUpdate();
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
		return insertados;
	}
	
	public int actualizar(Registro r) {
		int actualizados=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {con=ConexionMySQL.getConexion();
		String sql="update tb_registrados set idcurso=? where idregistro=?";
		pstm=con.prepareStatement(sql);
		pstm.setString(2, r.getIdREGISTRO());
		pstm.setString(1, r.getIdCurso());
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
	
	public int eliminar(String idregistro) {
		int eliminar=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {con=ConexionMySQL.getConexion();
		String sql="delete from tb_registrados where idregistro=?";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, idregistro);
		System.out.println("SQL-->"+pstm);
		eliminar=pstm.executeUpdate();
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
		return eliminar;
	}
	
	public List<Registro> listaIdRegistrado(){
		List<Registro> lista=new ArrayList<Registro>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select r.idregistro from tb_registrados r order by r.idregistro asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Registro r;
		while(rs.next()) {
			r=new Registro();
			r.setIdREGISTRO(rs.getString(1));
			
			lista.add(r);
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
	
	public List<Registro> listaIdAlumnoRegistrado(){
		List<Registro> lista=new ArrayList<Registro>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select a.idalumno from tb_alumno a order by a.idalumno asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Registro r;
		while(rs.next()) {
			r=new Registro();
			r.setIdAlumno(rs.getString(1));
			
			lista.add(r);
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
	
	public List<Registro> listaIdCurso(){
		List<Registro> lista=new ArrayList<Registro>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select c.idcurso from tb_cursos c order by c.idcurso asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Registro r;
		while(rs.next()) {
			r=new Registro();
			r.setIdCurso(rs.getString(1));
			
			lista.add(r);
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
	
	public List<Registro> listaIdRegistrado(String idRegistro){
		List<Registro> lista=new ArrayList<Registro>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select r.idregistro,r.idalumno,a.nombre,a.apellidos,r.idcurso,cu.curso from tb_registrados r inner join tb_alumno a on r.idAlumno=a.idalumno inner join tb_cursos cu on r.idcurso=cu.idcurso where r.idregistro=? order by r.idregistro asc";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, idRegistro);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Registro r;
		while(rs.next()) {
			r=new Registro();
			r.setIdREGISTRO(rs.getString(1));
			r.setIdAlumno(rs.getString(2));
			r.setNombreCom(rs.getString(3)+" "+rs.getString(4));
			r.setIdCurso(rs.getString(5));
			r.setCurso(rs.getString(6));
			
			lista.add(r);
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
	
	public List<Registro> listaIdAlumnoRegistrado(String idAlumno){
		List<Registro> lista=new ArrayList<Registro>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select r.idregistro,r.idalumno,a.nombre,a.apellidos,r.idcurso,cu.curso from tb_registrados r inner join tb_alumno a on r.idAlumno=a.idalumno inner join tb_cursos cu on r.idcurso=cu.idcurso where r.idalumno=? order by r.idregistro asc";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, idAlumno);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Registro r;
		while(rs.next()) {
			r=new Registro();
			r.setIdREGISTRO(rs.getString(1));
			r.setIdAlumno(rs.getString(2));
			r.setNombreCom(rs.getString(3)+" "+rs.getString(4));
			r.setIdCurso(rs.getString(5));
			r.setCurso(rs.getString(6));
			
			lista.add(r);
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
	
	public List<Registro> listaIdCursoRegistrado(String idCurso){
		List<Registro> lista=new ArrayList<Registro>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select r.idregistro,r.idalumno,a.nombre,a.apellidos,r.idcurso,cu.curso from tb_registrados r inner join tb_alumno a on r.idAlumno=a.idalumno inner join tb_cursos cu on r.idcurso=cu.idcurso where r.idcurso=? order by r.idregistro asc";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, idCurso);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Registro r;
		while(rs.next()) {
			r=new Registro();
			r.setIdREGISTRO(rs.getString(1));
			r.setIdAlumno(rs.getString(2));
			r.setNombreCom(rs.getString(3)+" "+rs.getString(4));
			r.setIdCurso(rs.getString(5));
			r.setCurso(rs.getString(6));
			
			lista.add(r);
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
	
	public String ultimoCodigoRegistro() {
		String cod="";
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select r.idregistro from tb_registrados r order by idregistro desc limit 1";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		while(rs.next()) {
			cod=rs.getString(1);
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
		return cod;
	}
	
	public String buscarAlumnoYCurso(String idCurso) {
		String respuesta="";
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select r.IdAlumno from tb_registrados r where IdCurso=?";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, idCurso);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		while(rs.next()) {
			respuesta=rs.getString(1);
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
		return respuesta;
	}
}
