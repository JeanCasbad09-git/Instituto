package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Alumno;
import util.ConexionMySQL;

public class AlumnoModel {

	public List<Alumno> listarAlumnos(){
		ArrayList<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select*from tb_alumno";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno ObjA;
			while(rs.next()) {
				ObjA=new Alumno();
				ObjA.setIdAlumno(rs.getString(1));
				ObjA.setNombre(rs.getString(2));
				ObjA.setApellidos(rs.getString(3));
				ObjA.setDNI(rs.getString(4));
				ObjA.setFecNac(rs.getString(5));
				ObjA.setFecReg(rs.getString(6));
				 
				lista.add(ObjA);
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
	
	public int insertarAlumno(Alumno ObjA) {
		int inserta=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="insert into tb_alumno values(?,?,?,?,?,?)";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, ObjA.getIdAlumno());
			pstm.setString(2, ObjA.getNombre());
			pstm.setString(3, ObjA.getApellidos());
			pstm.setString(4, ObjA.getDNI());
			pstm.setString(5, ObjA.getFecNac());
			pstm.setString(6, ObjA.getFecReg());
			
			System.out.println("SQL-->"+pstm);
			
			inserta=pstm.executeUpdate();//retorna la cantidad de filas afectadas, mayormente 1
			
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
		return inserta;
		
	}
	
	public int actualizarAlumno(Alumno objA) {
		int actualizados=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="update tb_alumno set Nombre=?,Apellidos=?,DNI=?,fecNac=?,fecReg=? where IdAlumno=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, objA.getNombre());
			pstm.setString(2, objA.getApellidos());
			pstm.setString(3, objA.getDNI());
			pstm.setString(4, objA.getFecNac());
			pstm.setString(5, objA.getFecReg());
			pstm.setString(6, objA.getIdAlumno());
			
			System.out.println("SQL-->"+pstm);//retorna la cantidad de filas afectadas, mayormente 1
			
			actualizados=pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}
	
	public int eliminarAlumno(String IdAlumno) {
		int eliminados=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="delete from tb_alumno where IdAlumno=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, IdAlumno);
			
			System.out.println("SQL-->"+pstm);
			
			eliminados=pstm.executeUpdate();//retorna la cantidad de filas afectadas, mayormente 1
			
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
		
		return eliminados;
	}
	
	public List<Alumno> buscarCodigoAlumno(String cod) {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select a.IdAlumno from tb_alumno a where IdAlumno=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, cod);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarCodigosAlumnos() {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select  a.IdAlumno,a.Nombre,a.Apellidos  from bd_instituto.tb_alumno a order by IdAlumno asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				a.setNombre(rs.getString(2)+" "+rs.getString(3));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarCodigosAlumnosCBO() {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select a.idalumno from bd_instituto.tb_alumno a order by IdAlumno asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarNombreAlumnosCBO() {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select a.nombre from bd_instituto.tb_alumno a order by nombre asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setNombre(rs.getString(1));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarApellidosAlumnosCBO() {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select a.apellidos from bd_instituto.tb_alumno a order by apellidos asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setApellidos(rs.getString(1));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarDNIAlumnosCBO() {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select a.dni from bd_instituto.tb_alumno a order by dni asc";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setDNI(rs.getString(1));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	
	
	public List<Alumno> listarCodigosAlumnosCBO(String CodAlumno) {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select * from bd_instituto.tb_alumno a where idalumno=? order by IdAlumno asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, CodAlumno);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				a.setNombre(rs.getString(2));
				a.setApellidos(rs.getString(3));
				a.setDNI(rs.getString(4));
				a.setFecNac(rs.getString(5));
				a.setFecReg(rs.getString(6));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarNombreAlumnosCBO(String nombre) {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select * from bd_instituto.tb_alumno a where nombre=? order by nombre asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, nombre);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				a.setNombre(rs.getString(2));
				a.setApellidos(rs.getString(3));
				a.setDNI(rs.getString(4));
				a.setFecNac(rs.getString(5));
				a.setFecReg(rs.getString(6));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarApellidosAlumnosCBO(String apellidos) {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select * from bd_instituto.tb_alumno a where apellidos=? order by apellidos asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, apellidos);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				a.setNombre(rs.getString(2));
				a.setApellidos(rs.getString(3));
				a.setDNI(rs.getString(4));
				a.setFecNac(rs.getString(5));
				a.setFecReg(rs.getString(6));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarDNIAlumnosCBO(String dni) {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select * from bd_instituto.tb_alumno a where dni=? order by dni asc";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dni);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				a.setNombre(rs.getString(2));
				a.setApellidos(rs.getString(3));
				a.setDNI(rs.getString(4));
				a.setFecNac(rs.getString(5));
				a.setFecReg(rs.getString(6));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarFecNacAlumnosCBO(String desde, String hasta) {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select * from bd_instituto.tb_alumno a where fecnac>=? and fecnac<=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, desde);
			pstm.setString(2, hasta);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				a.setNombre(rs.getString(2));
				a.setApellidos(rs.getString(3));
				a.setDNI(rs.getString(4));
				a.setFecNac(rs.getString(5));
				a.setFecReg(rs.getString(6));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> listarFecRegAlumnosCBO(String desde, String hasta) {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select * from bd_instituto.tb_alumno a where fecreg>=? and fecreg<=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, desde);
			pstm.setString(2, hasta);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			Alumno a;
			while(rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getString(1));
				a.setNombre(rs.getString(2));
				a.setApellidos(rs.getString(3));
				a.setDNI(rs.getString(4));
				a.setFecNac(rs.getString(5));
				a.setFecReg(rs.getString(6));
				lista.add(a);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public List<Alumno> buscarDNIAlumno(String a) {
		List<Alumno> lista=new ArrayList<Alumno>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select a.dni from tb_alumno a where dni=?";
		pstm=con.prepareStatement(sql);
		pstm.setString(1, a);
		System.out.println("SQL-->"+pstm);
		rs=pstm.executeQuery();
		Alumno al;
		while(rs.next()) {
			al=new Alumno();
			al.setDNI(rs.getString(1));
			lista.add(al);
			
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
	
	public String buscarUltimoCodigoAlumno(){
		String cod="";
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {con=ConexionMySQL.getConexion();
		String sql="select a.IdAlumno from tb_alumno a order by IdAlumno desc limit 1";
		pstm=con.prepareStatement(sql);
		System.out.println("SQL->"+pstm);
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
			}catch(Exception e ) {
				e.printStackTrace();
			}
		}
		return cod;
	}
}
