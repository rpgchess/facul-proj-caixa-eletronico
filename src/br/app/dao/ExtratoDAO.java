package br.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.app.beans.Extrato;
import br.app.utils.ConexaoDB;

public class ExtratoDAO {

	public void create(Extrato extrato) {
		try {
			// 1 Passo - Abrir e obter conexao
			Connection con = ConexaoDB.getConexao();

			// 2 Passo - Criar e executar instrucao SQL (e obter resultados se houver)
			String sql = "INSERT INTO Extrato VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, extrato.getData());
			ps.setString(2, extrato.getRotulo());
			ps.setDouble(3, extrato.getCredito());
			ps.setDouble(4, extrato.getDebito());

			ps.executeUpdate(); // Executa o comando SQL preparado acima

			// 3º Passo - Encerrar o processo de execucao de instrucoes e a conexão.
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(Extrato extrato) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "DELETE FROM Extrato WHERE data=? rotulo=? debito=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, extrato.getData());
			ps.setString(2, extrato.getRotulo());
			ps.setDouble(3, extrato.getDebito());

			ps.executeUpdate();

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public ArrayList<Extrato> listAll(String data, String rotulo) {
		try {
			// 1� Passo - Abrir e obter conex�o
			Connection con = ConexaoDB.getConexao();

			// 2� Passo - Criar e executar instru��o SQL (e obter resultados se houver)
			String sql = "SELECT * FROM Extrato WHERE data=? AND rotulo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, data);
			ps.setString(2, rotulo);

			// Executa o comando SQL preparado acima e obt�m o resultado da consulta
			ResultSet rs = ps.executeQuery();

			// Cria um objeto aluno com os dados retornado do banco
			ArrayList<Extrato> extratos = new ArrayList<>();
			while (rs.next()) {
				Extrato extrato = new Extrato();
				extrato.setData(rs.getString("data"));
				extrato.setRotulo(rs.getString("rotulo"));
				extrato.setCredito(rs.getDouble("credito"));
				extrato.setDebito(rs.getDouble("debito"));

				extratos.add(extrato);
			}

			// 3� Passo - Encerrar o processo de execu��o de instru��es e a conex�o.
			ps.close();
			con.close();

			return extratos; // Retorna o objeto aluno criado
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}