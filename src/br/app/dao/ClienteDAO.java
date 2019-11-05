package br.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.app.beans.Cliente;
import br.app.utils.ConexaoDB;

public class ClienteDAO {

	public void create(Cliente cliente) {
		try {
			// 1 Passo - Abrir e obter conexao
			Connection con = ConexaoDB.getConexao();

			// 2 Passo - Criar e executar instrucao SQL (e obter resultados se houver)
			String sql = "INSERT INTO Usuarios VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cliente.getConta()); // 1� interrogacao
			ps.setString(2, cliente.getSenha()); // 2� interrogacao
			ps.setString(3, cliente.getNome()); // 3� interrogacao
			ps.setDouble(4, cliente.getSaldo()); // 4� interrogacao

			ps.executeUpdate(); // Executa o comando SQL preparado acima

			// 3º Passo - Encerrar o processo de execucao de instrucoes e a conexão.
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Cliente retrieve(String conta) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "SELECT * FROM Usuarios WHERE conta = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, conta);
			// Executa o comando SQL preparado acima e obtém o resultado da consulta
			ResultSet rs = ps.executeQuery();

			// Cria um objeto aluno com os dados retornado do banco
			Cliente cliente = new Cliente();
			if (rs.next()) {
				cliente.setConta(rs.getString("conta"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSaldo(rs.getDouble("saldo"));
			}

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

			return cliente; // Retorna o objeto aluno criado
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public int update(Cliente cliente) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "UPDATE Usuarios SET senha=?, nome=?, saldo=? WHERE conta=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cliente.getSenha()); // 1 interrocacao
			ps.setString(2, cliente.getNome()); // 2 interroga��o
			ps.setDouble(3, cliente.getSaldo()); // 3 interroga��o
			ps.setString(4, cliente.getConta()); // 4 interroga��o

			int result = ps.executeUpdate(); // retorna a quantidade de linhas afetadas

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(String conta) {
		try {
			// 1º Passo - Abrir e obter conexão
			Connection con = ConexaoDB.getConexao();

			// 2º Passo - Criar e executar instrução SQL (e obter resultados se houver)
			String sql = "DELETE FROM Usuarios WHERE conta=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, conta);

			ps.executeUpdate();

			// 3º Passo - Encerrar o processo de execução de instruções e a conexão.
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public ArrayList<Cliente> listAll() {
		try {
			// 1� Passo - Abrir e obter conex�o
			Connection con = ConexaoDB.getConexao();

			// 2� Passo - Criar e executar instru��o SQL (e obter resultados se houver)
			String sql = "SELECT * FROM Usuarios";
			PreparedStatement ps = con.prepareStatement(sql);

			// Executa o comando SQL preparado acima e obt�m o resultado da consulta
			ResultSet rs = ps.executeQuery();

			// Cria um objeto aluno com os dados retornado do banco
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setConta(rs.getString("id"));
				cliente.setSenha(rs.getString("descricao"));
				cliente.setNome(rs.getString("quantidade"));
				cliente.setSaldo(rs.getDouble("valor"));

				clientes.add(cliente);
			}

			// 3� Passo - Encerrar o processo de execu��o de instru��es e a conex�o.
			ps.close();
			con.close();

			return clientes; // Retorna o objeto aluno criado
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
