package br.app.beans;

public class Extrato {
	private String data;
	private String rotulo;
	private double credito;
	private double debito;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public double getCredito() {
		return credito;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}

	public double getDebito() {
		return debito;
	}

	public void setDebito(double debito) {
		this.debito = debito;
	}

	public String toString() {
		return data + " - " + rotulo + " .............. R$:" + debito;
	}
}