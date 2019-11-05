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
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "INSERT INTO Notas VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getNota()); // 1º interrogação
			ps.setString(2, admin.getQuantidade()); // 2º interrogação

			ps.executeUpdate(); // Executa o comando SQL preparado acima

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Admin retrieve(String nota) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "SELECT * FROM Notas WHERE valores = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nota);
			// Executa o comando SQL preparado acima e obtém o resultado da consulta
			ResultSet rs = ps.executeQuery();

			// Cria um objeto aluno com os dados retornado do banco
			Admin admin = new Admin();
			if (rs.next()) {
				admin.setNota(rs.getString("valores"));
				admin.setQuantidade(rs.getString("quantidade"));

			}

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

			return admin; // Retorna o objeto aluno criado
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public int update(Admin admin) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "UPDATE Notas SET quantidade=? WHERE valores=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getQuantidade());
			ps.setString(2, admin.getNota()); // 2º interrogação

			int result = ps.executeUpdate(); // retorna a quantidade de linhas afetadas

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(int nota) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "DELETE FROM Notas WHERE valores=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, nota);

			ps.executeUpdate();

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public ArrayList<Admin> listAll() {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "SELECT * FROM Notas";
			PreparedStatement ps = con.prepareStatement(sql);

			// Executa o comando SQL preparado acima e obtém o resultado da consulta
			ResultSet rs = ps.executeQuery();

			// Cria um objeto aluno com os dados retornado do banco
			ArrayList<Admin> admins = new ArrayList<Admin>();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setNota(rs.getString("valores"));
				admin.setQuantidade(rs.getString("quantidade"));

				admins.add(admin);
			}

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

			return admins; // Retorna o objeto aluno criado
		} catch (

		Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
