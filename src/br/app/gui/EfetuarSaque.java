package br.app.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.app.beans.CaixaEletronico;
import br.app.beans.Cliente;
import br.app.beans.Extrato;
import br.app.dao.ClienteDAO;
import br.app.dao.ExtratoDAO;

public class EfetuarSaque extends JDialog {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textValor;

	private String getDataAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy/MM/dd");
		return formatarDate.format(data);
	}

	/**
	 * Create the frame.
	 */
	public EfetuarSaque() {
		setResizable(false);
		setTitle("Efetuar Saque");
		setModal(true);// trava a tela
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(720, 100, 235, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CaixaEletronico caixa = new CaixaEletronico();

		Login login = new Login();
		String nome = login.getNome();
		login.dispose();

		JLabel lblBemVindo = new JLabel("Bem vindo, " + nome);
		lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBemVindo.setBounds(10, 26, 209, 14);
		contentPane.add(lblBemVindo);

		JLabel lblSacar = new JLabel("Valor: ");
		lblSacar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSacar.setBounds(10, 87, 47, 20);
		contentPane.add(lblSacar);

		textValor = new JTextField();
		textValor.setColumns(10);
		textValor.setBounds(78, 87, 141, 20);
		contentPane.add(textValor);

		JButton btnSacar = new JButton("Sacar");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				double saldo = login.getSaldo();

				int valor = 0;

				// Remove todos os caracteres especiais
				textValor.setText(textValor.getText().replaceAll("[^0-9.]", ""));
				
				// Verifica conteudo digita no campo de saque
				if (!textValor.getText().isEmpty())
					try {
						valor = Integer.parseInt(textValor.getText());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Vamos efetuar este saque removendo os centavos!");
						valor = (int) Double.parseDouble(textValor.getText());
					}
				
				// Faz um bloqueio de valores menor igual a ZERO ou UM ou TRES
				if (valor == 1 || valor == 3 || valor <= 0) {
					JOptionPane.showMessageDialog(null, "Valor invalido para saque!");
					textValor.setText(null);
					textValor.requestFocus();
				} else if (valor <= saldo) { // Verifica se tem saldo disponivel para efetuar saque.
					JOptionPane.showMessageDialog(null, caixa.sacar(valor));
					if (caixa.getSaqueStatus()) {
						// Se saque efetuado entao diminui saldo do cliente
						double saldoRestante = saldo - valor;
						Cliente cliente = new ClienteDAO().retrieve(Integer.toString(login.getNmrConta()));
						cliente.setSaldo(saldoRestante);
						new ClienteDAO().update(cliente);
						login.setSaldo(saldoRestante);
						
						// Se efetuado saque entao registra no extrato de movimentacoes
						Extrato extrato = new Extrato();
						extrato.setData(getDataAtual());
						extrato.setRotulo(nome + " - Saque");
						extrato.setCredito(0);
						extrato.setDebito(-valor);

						new ExtratoDAO().create(extrato);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Saldo Insuficiente!");
				}
				login.dispose();
			}
		});
		btnSacar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSacar.setBounds(10, 132, 209, 23);
		contentPane.add(btnSacar);

		JButton btnConsultarSaldo = new JButton("Consultar saldo");
		btnConsultarSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.app.gui.Saldo tela = new br.app.gui.Saldo();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);
			}
		});
		btnConsultarSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultarSaldo.setBounds(10, 166, 209, 23);
		contentPane.add(btnConsultarSaldo);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(10, 200, 209, 23);
		contentPane.add(btnCancelar);
	}

}
