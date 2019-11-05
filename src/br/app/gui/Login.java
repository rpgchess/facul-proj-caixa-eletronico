package br.app.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.app.beans.CaixaEletronico;
import br.app.beans.Cliente;
import br.app.beans.Cota;
import br.app.dao.ClienteDAO;
import br.app.dao.CotaDAO;

public class Login extends JFrame {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textSenha;
	private static String nome;
	private static double saldo;
	private static int nmrConta;

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 348, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Conta:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setBounds(10, 21, 70, 30);
		contentPane.add(lblLogin);

		textUsuario = new JTextField();
		textUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		textUsuario.setBounds(91, 23, 234, 30);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(10, 64, 70, 30);
		contentPane.add(lblSenha);

		textSenha = new JPasswordField();
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textSenha.setHorizontalAlignment(SwingConstants.LEFT);
		textSenha.setColumns(10);
		textSenha.setBounds(91, 64, 234, 30);
		contentPane.add(textSenha);

		Cota cota = new CotaDAO().retrieve(1);

		CaixaEletronico caixa = new CaixaEletronico();

		JButton btnNewButton = new JButton("Logar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String conta = textUsuario.getText();
				char[] csenha = textSenha.getPassword();
				String senha = String.valueOf(csenha);

				Cliente cliente = new ClienteDAO().retrieve(conta);

				if (conta.equals("administrador") && senha.equals("admin")) {
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Bem vindo Administrador!");

					TelaPrincipalAdm tela = new TelaPrincipalAdm();
					tela.setLocationRelativeTo(null);
					tela.setVisible(true);

				} else if (conta.equals("") && senha.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos acima!");
					textUsuario.requestFocus();
				} else if (conta.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha corretamente o campo Usuario!");
					textSenha.setText(null);
					textUsuario.requestFocus();
					
				} else if (senha.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha corretamente o campo Senha!");
					textSenha.requestFocus();
				} else if (conta.equals(cliente.getConta()) && senha.equals(cliente.getSenha())) {
					if (Double.parseDouble(caixa.pegaValorTotalDisponivel()) < cota.getMinimo()) {
						JOptionPane.showMessageDialog(null,
								"Valor do caixa abaixo da cota minima. Por favor chame o administrador", "Aviso",
								JOptionPane.WARNING_MESSAGE);
						textUsuario.setText(null);
						textSenha.setText(null);
						textUsuario.requestFocus();

					} else {
						nome = cliente.getNome();
						saldo = cliente.getSaldo();
						nmrConta = Integer.parseInt(cliente.getConta());
						setVisible(false);
						JOptionPane.showMessageDialog(null, "Bem vindo " + nome + "!");

						TelaPrincipalCli tela = new TelaPrincipalCli();
						tela.setLocationRelativeTo(null);
						tela.setVisible(true);

					}
				} else {
					JOptionPane.showMessageDialog(null, "Dados incorretos!");
					textUsuario.setText(null);
					textSenha.setText(null);
					textUsuario.requestFocus();
				}
			}
		});
		btnNewButton.setBounds(48, 110, 114, 30);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Sair");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(188, 110, 114, 30);
		contentPane.add(btnNewButton_1);

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		Login.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		Login.saldo = saldo;
	}

	public int getNmrConta() {
		return nmrConta;
	}

	public void setNmrConta(int nmrConta) {
		Login.nmrConta = nmrConta;
	}
}
