package br.app.test;

import br.app.beans.CaixaEletronico;

public class TesteSaque {

	public static void main(String[] args) {
		CaixaEletronico caixa = new CaixaEletronico();
		for (int i = 1; i <= 5; i++) {
			if (i != 1 && i != 3)
				System.out.println("Valor R$" + i + " - " + caixa.sacar(i));
		}
	}
}
