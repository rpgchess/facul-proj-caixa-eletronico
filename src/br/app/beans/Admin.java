package br.app.beans;

public class Admin {
	// inicializando atributos
	private String nota;
	private String quantidade;

	// criando gettes e setters
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	// metodo que retorna uma string formatada
	public String toString() {
		String str = "", str2 = "";
		int num = Integer.parseInt(getNota());
		if (num < 10) str += "  ";
		else if (num < 100) str += " ";
		num = Integer.parseInt(getQuantidade());
		if (num < 10) str2 += "  ";
		else if (num < 100) str2 += " ";
		return "R$ " + str + getNota() + " ................................... " + str2 + getQuantidade();
	}

}
