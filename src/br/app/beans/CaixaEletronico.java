package br.app.beans;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import br.app.dao.AdministradorDAO;
import br.app.dao.CotaDAO;

public class CaixaEletronico implements ICaixaEletronico {
	// inicializando as variaveis e os arrays
	private int[][] notas = new int[6][2];
	private static int cedula[] = { 100, 50, 20, 10, 5, 2 };
	private static boolean saqueStatus = false;

	// retorna o status do saque
	public boolean getSaqueStatus() {
		return saqueStatus;
	}

	// atualizando as quantidades de notas dentro do array utilizando um for
	// atraves do metodo sem paramentros
	private void atualizaNotas() {
		for (int i = 0; i < cedula.length; i++) {
			notas[i][0] = Integer.parseInt(new AdministradorDAO().retrieve(String.valueOf(cedula[i])).getNota());
			notas[i][1] = Integer.parseInt(new AdministradorDAO().retrieve(String.valueOf(cedula[i])).getQuantidade());
		}
	}

	// atualizando as quantidades de notas no banco de dados utilizando um for
	// atraves do metodo com parametro
	private void atualizaNotas(int[] nota) {
		// criando objeto de apoio
		Admin admTemp = new Admin();
		for (int i = 0; i < cedula.length; i++) {
			admTemp.setNota(String.valueOf(cedula[i]));
			admTemp.setQuantidade(String.valueOf(notas[i][1] - nota[i]));
			new AdministradorDAO().update(admTemp);
		}
	}

	/**
	 * Pega o valor total disponivel no caixa eletronico
	 * 
	 * @retorna uma string formatada com o valor total disponivel
	 */
	public String pegaValorTotalDisponivel() {
		// Criando variavel de apoio
		double soma = 0.0;
		// Atualizando notas na matriz
		atualizaNotas();
		// Efetuando a soma do valores de cada cedula
		for (int i = 0; i < cedula.length; i++)
			soma += notas[i][1] * notas[i][0];
		// Fazendo uma conversão
		String mensagem = Double.toString(soma);
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
		// iniciando variaveis de apoio
		int[] notasValor = new int[6];
		int[] notasQtd = new int[6];
		int count = 0, somaQtd = 0;

		// criando objeto de extração da informação de cota minima
		Cota cota = new CotaDAO().retrieve(1);

		// obtendo o total de cedulas do caixa
		double total = Double.parseDouble(pegaValorTotalDisponivel());

		// Verifica se o total de cedulas e menor que a cota minima
		if (cota.getMinimo() >= total)
			return "Ultimo saque atingiu o limite da conta minima!";

		// Verifica se o valor do saque e maior que o total de cedulas
		if (total < valor)
			return "Caixa nao possui quantidade de cedulas suficientes para efetuar este saque!";

		// Verifica se o total de cedulas menos valor de saque e menor que a cota minima
		double tiraValor = (total > valor) ? (total - valor) : 0;
		if (cota.getMinimo() >= tiraValor)
			return "Este valor nao poderá ser retirado do caixa, pois utrapassa o limite de valor minimo de caixa!";

		while (valor > 0) {
			for (int i = 0; i < cedula.length; i++) {
				// Verificando se o valor e maior do que a cedula especifica do loop
				// E se quantidade da nota em questao e diferente de ZERO
				if (valor >= cedula[i] && notas[i][1] != 0) {
					notasQtd[i] = valor / cedula[i];
					valor %= cedula[i];
					// Verifica se quantidade de notas do caixa e menor que quantdade de notas do
					// saque
					if (notasQtd[i] > notas[i][1]) {
						valor = ((notasQtd[i] - notas[i][1]) * cedula[i]);
						notasQtd[i] = notas[i][1];
					}
					// Verifica se resto da divisão é 1 ou 3
					if ((notasQtd[i] > 0) && (valor == 1) || (valor == 3)) {
						valor += cedula[i];
						notasQtd[i] -= 1;
					}
					notasValor[i] = notasQtd[i] * cedula[i];
					somaQtd += notasQtd[i];
				}
			}
			// Caso não exista notas suficientes para efetuar este saque
			if (count > 30)
				return "Caixa nao possui quantidade de cedulas suficientes para efetuar este saque!";
			count++;
		}

		saqueStatus = false;

		// Verifica se a quantidade de cedulas emitidas para este saque é maior que 30
		if (somaQtd > 30)
			return "Quantidade de cedulas ultrapassar o limite de 30 cedulas";

		// Produz mensagem de retorno
		String mensagem = "Saque efetuado com sucesso!\n";
		if (valor == 0) {
			atualizaNotas(notasQtd);
			for (int i = 0; i < cedula.length; i++)
				mensagem += "\n" + notasQtd[i] + " x R$" + cedula[i];
		}

		saqueStatus = true;
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
		// criando um novo objeto Admin
		Admin admin = new Admin();
		admin.setNota(String.valueOf(cedula));
		admin.setQuantidade(String.valueOf(quantidade));
		// Efetuamos a operacao de UPDATE
		new AdministradorDAO().update(admin);

		String mensagem = "Reposicao das notas de " + cedula + " feito com sucesso";

		return mensagem;

	}

	/**
	 * Efetua a leitura da cota minima de atendimento
	 * 
	 * @param minimo
	 * @retorna uma string formatada informando o resultado da operacao
	 */
	public String armazenaCotaMinima(Integer minimo) {
		// criando um novo objeto Cota
		Cota cota = new Cota();
		cota.setMinimo(minimo);

		// Efetuamos a operacao de UPDATE
		new CotaDAO().update(cota);

		return "Cota minima definida para: R$" + minimo + ",00";
	}
}
