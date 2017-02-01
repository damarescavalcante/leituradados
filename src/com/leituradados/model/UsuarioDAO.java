package com.leituradados.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.leituradados.controller.Usuario;

public class UsuarioDAO {

	private String sql;
	private ConnectionDatabase banco = ConnectionDatabase.getInstance();
	
	public void cadastrarUsuario (Usuario usuario) throws Exception{
		
		try {
			banco.openConnection();
			sql = "INSERT INTO usuario (id, nome, observacao) VALUES (?,?,?)";
			
			PreparedStatement stm = banco.preparandoStatement(sql);
			
			stm.setInt(1, usuario.getId());
			stm.setString(2, usuario.getNome());
			stm.setString(3, usuario.getObservacao());
			
			banco.executaUpdate(sql, stm);
			
			
		} catch (Exception e) {
			throw new Exception("Erro do cadastrarUsuario no dao");
		}finally {
			banco.closeConnection();
		}
		
	}
	
	public boolean existeUsuario (Usuario u) throws Exception{
		boolean existe = false;
		try {
			
			sql = "SELECT * FROM usuario";
			PreparedStatement stm = banco.preparandoStatement(sql);
			ResultSet rs = banco.executaQuery(sql, stm);
			while(rs.next()){
				existe = true;
			}
			
		} catch (Exception e) {
			throw new Exception("erro no existeusuario do dao");
		} finally {
			banco.closeConnection();
		}
		return existe;
	}
	
}
