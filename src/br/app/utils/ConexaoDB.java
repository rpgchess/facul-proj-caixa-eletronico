package br.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDB {
	public static Connection getConexao() {
		try {

			// Carregando o Driver do SQLite e suas bibliotecas
			// Esta linha poder� causar uma exce��o em tempo de compila��o
			// chamada ClassNotFoundException
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:CaixaEletronico.db";

			// Estabelecendo e obtendo uma conex�o com o Banco de dados
			// Esta linha poder� causar uma exce��o em tempo de compila��o
			// chamada SQLException
			return DriverManager.getConnection(url);

		} catch (Exception e) {
			// Caso uma das duas linhas especificada causem alguma exce��o
			// este bloco ir� tratar lan�ando uma exce��o em tempo de execu��o.
			throw new RuntimeException(e.getMessage());
		}

	}
}
