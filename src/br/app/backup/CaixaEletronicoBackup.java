package br.app.backup;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import br.app.beans.Admin;
import br.app.beans.Cota;
import br.app.beans.ICaixaEletronico;
import br.app.dao.AdministradorDAO;
import br.app.dao.CotaDAO;

public class CaixaEletronicoBackup implements ICaixaEletronico {

	Admin admin = new Admin();

	/**
	 * Pega o valor total disponivel no caixa eletronico
	 * 
	 * @retorna uma string formatada com o valor total disponivel
	 */
	public String pegaValorTotalDisponivel() {

		double valor = 0, total = 0, total100 = 0, total50 = 0, total20 = 0, total10 = 0, total5 = 0, total2 = 0;

		Admin t100 = new AdministradorDAO().retrieve("100");
		valor = Integer.parseInt(t100.getNota()) * Integer.parseInt(t100.getQuantidade());
		total100 += valor;

		Admin t50 = new AdministradorDAO().retrieve("50");
		valor = Integer.parseInt(t50.getNota()) * Integer.parseInt(t50.getQuantidade());
		total50 += valor;

		Admin t20 = new AdministradorDAO().retrieve("20");
		valor = Integer.parseInt(t20.getNota()) * Integer.parseInt(t20.getQuantidade());
		total20 += valor;

		Admin t10 = new AdministradorDAO().retrieve("10");
		valor = Integer.parseInt(t10.getNota()) * Integer.parseInt(t10.getQuantidade());
		total10 += valor;

		Admin t5 = new AdministradorDAO().retrieve("5");
		valor = Integer.parseInt(t5.getNota()) * Integer.parseInt(t5.getQuantidade());
		total5 += valor;

		Admin t2 = new AdministradorDAO().retrieve("2");
		valor = Integer.parseInt(t2.getNota()) * Integer.parseInt(t2.getQuantidade());
		total2 += valor;

		total = total100 + total50 + total20 + total10 + total5 + total2;

		String mensagem = Double.toString(total);

		return mensagem;

	}

	/**
	 * Efetua o saque
	 * 
	 * @param valor
	 *            a ser sacado
	 * @param
	 * @retorna uma string formatada informando o resultado da operacao
	 */
	public String sacar(Integer valor) {

		String mensagem;

		int N100, N50, N20, N10, N5, N2;
		int a;

		Admin admin = new AdministradorDAO().retrieve("100");

		if (Integer.parseInt(admin.getQuantidade()) > 0) {
			N100 = valor / 100;
			valor = valor % 100;
			N50 = valor / 50;
			valor = valor % 50;
			N20 = valor / 20;
			valor = valor % 20;
			N10 = valor / 10;
			valor = valor % 10;
			N5 = valor / 5;
			valor = valor % 5;
			N2 = valor / 2;
			valor = valor % 2;

			Admin n100 = new AdministradorDAO().retrieve("100");
			a = Integer.parseInt(n100.getQuantidade()) - N100;
			n100.setNota("100");
			n100.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n100);
			a = 0;

			// 50
			Admin n50 = new AdministradorDAO().retrieve("50");
			a = Integer.parseInt(n50.getQuantidade()) - N50;
			n50.setNota("50");
			n50.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n50);
			a = 0;

			// 20
			Admin n20 = new AdministradorDAO().retrieve("20");
			a = Integer.parseInt(n20.getQuantidade()) - N20;
			n20.setNota("20");
			n20.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n20);
			a = 0;

			// 10
			Admin n10 = new AdministradorDAO().retrieve("10");
			a = Integer.parseInt(n10.getQuantidade()) - N10;
			n10.setNota("10");
			n10.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n10);
			a = 0;

			// 5
			Admin n5 = new AdministradorDAO().retrieve("5");
			a = Integer.parseInt(n5.getQuantidade()) - N5;
			n5.setNota("5");
			n5.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n5);
			a = 0;

			// 2
			Admin n2 = new AdministradorDAO().retrieve("2");
			a = Integer.parseInt(n2.getQuantidade()) - N2;
			n2.setNota("2");
			n2.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n2);
			a = 0;

			// tratar menos 1 e null

			// tratar valores de 11 e 13 com for multiplos de 10 com condi�oes if...
			// somatoria de todas notas e verificar se bate com o valor digitado... se nao
			// ignora a logica e vai para a cedula de baixo e depois volta para a nota
			// maior...

			mensagem = "Saque efetuado com sucesso!\n\n" + N100 + " x R$100\n" + N50 + " x R$50\n" + N20 + " x R$20\n"
					+ N10 + " x R$10\n" + N5 + " x R$5\n" + N2 + " x R$2\n";

		} else {
			N50 = valor / 50;
			valor = valor % 50;
			N20 = valor / 20;
			valor = valor % 20;
			N10 = valor / 10;
			valor = valor % 10;
			N5 = valor / 5;
			valor = valor % 5;
			N2 = valor / 2;
			valor = valor % 2;

			// 50
			Admin n50 = new AdministradorDAO().retrieve("50");
			a = Integer.parseInt(n50.getQuantidade()) - N50;
			n50.setNota("50");
			n50.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n50);
			a = 0;

			// 20
			Admin n20 = new AdministradorDAO().retrieve("20");
			a = Integer.parseInt(n20.getQuantidade()) - N20;
			n20.setNota("20");
			n20.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n20);
			a = 0;

			// 10
			Admin n10 = new AdministradorDAO().retrieve("10");
			a = Integer.parseInt(n10.getQuantidade()) - N10;
			n10.setNota("10");
			n10.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n10);
			a = 0;

			// 5
			Admin n5 = new AdministradorDAO().retrieve("5");
			a = Integer.parseInt(n5.getQuantidade()) - N5;
			n5.setNota("5");
			n5.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n5);
			a = 0;

			// 2
			Admin n2 = new AdministradorDAO().retrieve("2");
			a = Integer.parseInt(n2.getQuantidade()) - N2;
			n2.setNota("2");
			n2.setQuantidade(Integer.toString(a));
			new AdministradorDAO().update(n2);
			a = 0;

			mensagem = "Saque efetuado com sucesso!\n\n" + N50 + " x R$50\n" + N20 + " x R$20\n" + N10 + " x R$10\n"
					+ N5 + " x R$5\n" + N2 + " x R$2\n";
			;

		}

		return mensagem;
	}

	/**
	 * Pega um relatorio informando as celulas e a quantidade de celula disponivel
	 * 
	 * @retorna uma string formatada com as celula e suas quantidades
	 */
	public String pegaRelatorioCedulas() {
		// Fonte

		ArrayList<Admin> lista = new AdministradorDAO().listAll();

		// Intermediador : fonte => componente
		DefaultListModel<Admin> model = new DefaultListModel<>();

		// preenchendo o modelo
		for (Admin a : lista) {
			model.addElement(a);
		}

		return model.toString();
	}

	/**
	 * Efetua a reposicao de cedulas
	 * 
	 * @param cedula
	 *            de reposicao
	 * @param quantidade
	 *            de cedulas para reposicao
	 * @retorna uma string formatada informando o resultado da operacao
	 */
	public String reposicaoCedulas(Integer cedula, Integer quantidade) {
		admin.setNota(String.valueOf(cedula));
		admin.setQuantidade(String.valueOf(quantidade));

		new AdministradorDAO().update(admin);

		String mensagem = "Reposi��o das notas de " + cedula + " feito com sucesso";

		return mensagem;

	}

	/**
	 * Efetua a leitura da cota minima de atendimento
	 * 
	 * @param minimo
	 * @retorna uma string formatada informando o resultado da operacao
	 */
	public String armazenaCotaMinima(Integer minimo) {
		Cota cota = new Cota();
		cota.setMinimo(minimo);

		// Efetua a operação de UPDATE
		new CotaDAO().update(cota);

		return "Cota minima definida para: " + minimo;
	}
}
