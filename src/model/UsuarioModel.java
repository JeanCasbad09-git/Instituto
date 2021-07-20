package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Opcion;
import entidad.Usuario;
import util.ConexionMySQL;

public class UsuarioModel {

	public Usuario valida(String login, String clave) {
		Usuario bean=null;
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=ConexionMySQL.getConexion();
			String sql="select*from tb_usuarios where login=? and contra=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, clave);
			System.out.println("SQL-->"+pstm);
			rs=pstm.executeQuery();
			if(rs.next()) {
				bean=new Usuario();
				bean.setIdUsuario(rs.getString(1));
				bean.setNombre(rs.getString(2));
				bean.setApellidos(rs.getString(3));
				bean.setDni(rs.getString(4));
				bean.setLogin(rs.getString(5));
				bean.setContra(rs.getString(6));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	public List<Opcion> obtieneOpciones(String idUsuario) {
		ArrayList<Opcion> data = new ArrayList<Opcion>();
		Opcion bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn =ConexionMySQL.getConexion();
			String sql = "select p.idopcion, p.nombre  from tb_opcion p inner join tb_rol_has_opcion r on p.idopcion = r.idopcion inner join tb_rol c on r.idrol = c.idrol inner join tb_usuario_has_rol h on c.idrol = h.idrol where idusuario = ? order by 2;";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idUsuario);
			System.out.println(pstm);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Opcion();
				bean.setIdOpcion(rs.getString("idopcion"));
				bean.setNombre(rs.getString("nombre"));
				data.add(bean);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}
	public int insertaUsuario(Usuario c) {
		int salida = -1;

		Connection con = null;
		PreparedStatement pstm = null;
		try {
			// 1 Conectar a la base de datos
			con = ConexionMySQL.getConexion();

			// 2 Se prepara el SQL
			String sql = "insert into tb_usuarios values(null,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, c.getNombre());
			pstm.setString(2, c.getApellidos());
			pstm.setString(3, c.getDni());
			pstm.setString(4, c.getLogin());
			pstm.setString(5, c.getContra());

			// 3 envia el sql y se recibe la cantidad de registrados
			salida = pstm.executeUpdate();

			System.out.println("SQL-->" + pstm);
			System.out.println("insertados :  " + salida);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}
	
	public List<Usuario> listaUsuario() {
		ArrayList<Usuario> data = new ArrayList<Usuario>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; // Trae la data de la BD
		try {
			con = ConexionMySQL.getConexion();
			String sql = "select * from tb_usuarios";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);

			// En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();

			// Se pasa la data del rs al ArrayList(data)
			Usuario c = null;
			while (rs.next()) {
				c = new Usuario();
				c.setIdUsuario(rs.getString("idUsuario"));
				c.setNombre(rs.getString("nombre"));
				c.setApellidos(rs.getString("apellido"));
				c.setDni(rs.getString("dni"));
				c.setLogin(rs.getString("login"));
				c.setContra(rs.getString("password"));

				data.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public int actualizaUsuario(Usuario c) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = ConexionMySQL.getConexion();
			String sql = "update tb_usuarios set nombre=?, apellido=?, dni=?, login=?, contra=? where idUsuario=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, c.getNombre());
			pstm.setString(2, c.getApellidos());
			pstm.setString(3, c.getDni());
			pstm.setString(4, c.getLogin());
			pstm.setString(5, c.getContra());
			pstm.setString(6, c.getIdUsuario());
			actualizados = pstm.executeUpdate();
			System.out.println("actualizados :  " + actualizados);
			System.out.println("SQL-->" + pstm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}
	
	public int eliminaUsuario(String idUsuario) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = ConexionMySQL.getConexion();
			String sql = "delete from tb_usuarios where idUsuario=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, idUsuario);
			eliminados = pstm.executeUpdate();
			System.out.println("eliminados :  " + eliminados);
			System.out.println("SQL-->" + pstm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eliminados;
	}
}
