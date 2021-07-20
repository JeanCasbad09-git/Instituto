package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cursos;
import util.ConexionMySQL;

public class CursosModel {

	public List<Cursos> listaCursos(){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="SELECT * FROM tb_cursos";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setIdCurso(rs.getString(1));
			c.setCurso(rs.getString(2));
			c.setCiclo(rs.getInt(3));
			c.setCreditos(rs.getInt(4));
			
			lista.add(c);
		}
		}
		catch(SQLException e) {
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
	
	public int insertarCursos(Cursos c) {
		int insertar=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="insert into tb_cursos values(?,?,?,?)";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, c.getIdCurso());
			pstm.setString(2, c.getCurso());
			pstm.setInt(3, c.getCiclo());
			pstm.setInt(4, c.getCreditos());
			
			System.out.println("SQL-->"+pstm);
			
			insertar=pstm.executeUpdate();//devolvera 1, si se a insertado correctamente
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return insertar;
	}
	
	public int actualizarCursos(Cursos c) {
		int actualizar=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="update tb_cursos set Curso=?,ciclo=?,creditos=? where idCurso=?";//será 1 si se actualizo correctamente
			pstm=con.prepareStatement(sql);
			pstm.setString(1, c.getCurso());
			pstm.setInt(2, c.getCiclo());
			pstm.setInt(3, c.getCreditos());
			pstm.setString(4, c.getIdCurso());
			System.out.println("SQL-->"+pstm);
			actualizar=pstm.executeUpdate();
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
		return actualizar;
	}
	
	public int eliminarCurso( String c) {
		int eliminar=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {con=ConexionMySQL.getConexion();
		String sql="delete from tb_cursos where idCurso=?";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, c);
		System.out.println("SQL-->"+pstm);
		eliminar=pstm.executeUpdate();//será 1 si se eliminó correctamente
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
	
	public List<Cursos> buscarCurso(String curso){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select c.curso from tb_cursos c where curso=?";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, curso);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setCurso(rs.getString(1));
			lista.add(c);
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
	
	public List<Cursos> listarIdCurso(){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select c.idcurso from tb_cursos c order by idcurso asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setIdCurso(rs.getString(1));
			lista.add(c);
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
	
	public List<Cursos> listarCurso(){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select c.curso from tb_cursos c order by curso asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setCurso(rs.getString(1));
			lista.add(c);
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
	
	public List<Cursos> listarCiclo(){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select c.ciclo from tb_cursos c order by ciclo asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setCiclo(rs.getInt(1));
			lista.add(c);
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
	
	public List<Cursos> listarCreditos(){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select c.creditos from tb_cursos c order by creditos asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setCreditos(rs.getInt(1));
			lista.add(c);
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
	
	public List<Cursos> listarIdCurso(String idCurso){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select * from tb_cursos c where idcurso=? order by idcurso asc";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, idCurso);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setIdCurso(rs.getString(1));
			c.setCurso(rs.getString(2));
			c.setCiclo(rs.getInt(3));
			c.setCreditos(rs.getInt(4));
			lista.add(c);
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
	
	public List<Cursos> listarCurso(String curso){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select * from tb_cursos c where curso=? order by curso asc";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, curso);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setIdCurso(rs.getString(1));
			c.setCurso(rs.getString(2));
			c.setCiclo(rs.getInt(3));
			c.setCreditos(rs.getInt(4));
			lista.add(c);
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
	
	public List<Cursos> listarCiclo(int ciclo){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select * from tb_cursos c where ciclo=? order by ciclo asc";
		pstm=con.prepareStatement(sql);
		pstm.setInt(1, ciclo);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setIdCurso(rs.getString(1));
			c.setCurso(rs.getString(2));
			c.setCiclo(rs.getInt(3));
			c.setCreditos(rs.getInt(4));
			lista.add(c);
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
	
	public List<Cursos> listarCreditos(int creditos){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select * from tb_cursos c where creditos=? order by creditos asc";
		pstm=con.prepareStatement(sql);
		pstm.setInt(1, creditos);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setIdCurso(rs.getString(1));
			c.setCurso(rs.getString(2));
			c.setCiclo(rs.getInt(3));
			c.setCreditos(rs.getInt(4));
			lista.add(c);
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
	
	public List<Cursos> listarCodCursos(){
		List<Cursos> lista=new ArrayList<Cursos>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select  c.idCurso,c.curso from tb_cursos c order by idcurso asc";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Cursos c;
		while(rs.next()) {
			c=new Cursos();
			c.setIdCurso(rs.getString(1));
			c.setCurso(rs.getString(2));
			lista.add(c);
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
	
	public String buscarUltimoCodigo(){
		String codigo="";
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select c.idcurso from tb_cursos c order by IdCurso desc limit 1";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL -->"+pstm);
		rs=pstm.executeQuery();
			while(rs.next()) {
				codigo=rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
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
	
}
