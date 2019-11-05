package br.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.app.beans.Admin;
import br.app.utils.ConexaoDB;

public class AdministradorDAO {
	public void create(Admin admin) {
		try {
			// 1� Passo - Abrir e obter conex�o
			Connection con = ConexaoDB.getConexao();

			// 2� Passo - Criar e executar instru��o SQL (e obter resultados se houver)
			String sql = "INSERT INTO Notas VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getNota()); // 1� interroga��o
			ps.setString(2, admin.getQuantidade()); // 2� interroga��o

			ps.executeUpdate(); // Executa o comando SQL preparado acima

			// 3� Passo - Encerrar o processo de execu��o de instru��es e a conex�o.
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Admin retrieve(String nota) {
		try {
			// 1� Passo - Abrir e obter conex�o
			Connection con = ConexaoDB.getConexao();

			// 2� Passo - Criar e executar instru��o SQL (e obter resultados se houver)
			String sql = "SELECT * FROM Notas WHERE valores = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nota);
			// Executa o comando SQL preparado acima e obt�m o resultado da consulta
			ResultSet rs = ps.executeQuery();

			// Cria um objeto aluno com os dados retornado do banco
			Admin admin = new Admin();
			if (rs.next()) {
				admin.setNota(rs.getString("valores"));
				admin.setQuantidade(rs.getString("quantidade"));

			}

			// 3� Passo - Encerrar o processo de execu��o de instru��es e a conex�o.
			ps.close();
			con.close();

			return admin; // Retorna o objeto aluno criado
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public int update(Admin admin) {
		try {
			// 1� Passo - Abrir e obter conex�o
			Connection con = ConexaoDB.getConexao();

			// 2� Passo - Criar e executar instru��o SQL (e obter resultados se houver)
			String sql = "UPDATE Notas SET quantidade=? WHERE valores=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getQuantidade());
			ps.setString(2, admin.getNota()); // 2� interroga��o

			int result = ps.executeUpdate(); // retorna a quantidade de linhas afetadas

			// 3� Passo - Encerrar o processo de execu��o de instru��es e a conex�o.
			ps.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(int nota) {
		try {
			// 1� Passo - Abrir e obter conex�o
			Connection con = ConexaoDB.getConexao();

			// 2� Passo - Criar e executar instru��o SQL (e obter resultados se houver)
			String sql = "DELETE FROM Notas WHERE valores=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, nota);

			ps.executeUpdate();

			// 3� Passo - Encerrar o processo de execu��o de instru��es e a conex�o.
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public ArrayList<Admin> listAll() {
		try {
			// 1� Passo - Abrir e obter conex�o
			Connection con = ConexaoDB.getConexao();

			// 2� Passo - Criar e executar instru��o SQL (e obter resultados se houver)
			String sql = "SELECT * FROM Notas";
			PreparedStatement ps = con.prepareStatement(sql);

			// Executa o comando SQL preparado acima e obt�m o resultado da consulta
			ResultSet rs = ps.executeQuery();

			// Cria um objeto aluno com os dados retornado do banco
			ArrayList<Admin> admins = new ArrayList<Admin>();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setNota(rs.getString("valores"));
				admin.setQuantidade(rs.getString("quantidade"));

				admins.add(admin);
			}

			// 3� Passo - Encerrar o processo de execu��o de instru��es e a conex�o.
			ps.close();
			con.close();

			return admins; // Retorna o objeto aluno criado
		} catch (

		Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
