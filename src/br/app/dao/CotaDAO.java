package br.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.app.beans.Cota;
import br.app.utils.ConexaoDB;

public class CotaDAO {

	public Cota retrieve(int id) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "SELECT * FROM Cota WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			// Executa o comando SQL preparado acima e obtém o resultado da consulta
			ResultSet rs = ps.executeQuery();

			// Cria um objeto aluno com os dados retornado do banco
			Cota cota = null;
			if (rs.next()) {
				cota = new Cota();
				cota.setId(rs.getInt("id"));
				cota.setMinimo(rs.getInt("minimo"));

			}

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

			return cota; // Retorna o objeto aluno criado
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public int update(Cota cota) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "UPDATE Cota SET minimo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cota.getMinimo()); // 1 interrocacao

			int result = ps.executeUpdate(); // retorna a quantidade de linhas afetadas

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
