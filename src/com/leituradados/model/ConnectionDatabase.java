package com.leituradados.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDatabase {

	private static ConnectionDatabase instance;

	private Connection conexao;
	private PreparedStatement stm;

	private ConnectionDatabase() {
	}

	public static ConnectionDatabase getInstance() {

		if (instance == null) {
			instance = new ConnectionDatabase();
		}

		return instance;

	}

	public void openConnection() throws SQLException {

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/leitura", "root", "R00T");

		} catch (SQLException e) {
			// System.out.println(e.getMessage());
			throw new SQLException("Erro na abertura da conexão");
		}
	}

	public void closeConnection() {
		try {
			stm.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// update, insert, delete
	public PreparedStatement preparandoStatement(String sql) throws SQLException {
		try {
			openConnection();
			stm = conexao.prepareStatement(sql);
		} catch (SQLException e) {
			throw new SQLException("Erro no preparedStatement");
		}
		return stm;
	}

	public void executaUpdate(String sql, PreparedStatement stm) throws SQLException {
		try {
			stm.execute();
		} catch (Exception e) {
			throw new SQLException("Erro no executaUpdate");
		}
	}

	// por enquanto fica void
	public ResultSet executaQuery(String sql, PreparedStatement stm) throws SQLException {
		ResultSet rs = null;
		try {
			rs = stm.executeQuery();
		} catch (Exception e) {
			throw new SQLException("Erro no resultset");
		}
		return rs;
	}

}
