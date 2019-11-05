package br.app.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaPrincipalAdm extends JFrame {
	static final long serialVersionUID = 1L;

	private void close() {
		Login login = new Login();
		login.setLocationRelativeTo(null);
		login.setVisible(true);	
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipalAdm() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				close();
			}
		});
		setTitle("Caixa eletronico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 332, 431);
		getContentPane().setLayout(null);

		JLabel lblModuloDoCliente = new JLabel("Modulo do Cliente:");
		lblModuloDoCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModuloDoCliente.setBounds(10, 11, 306, 30);
		getContentPane().add(lblModuloDoCliente);

		JButton btnEfetuarSaque = new JButton("Efetuar Saque");
		btnEfetuarSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EfetuarSaque tela = new EfetuarSaque();
				tela.setVisible(true);
			}
		});
		btnEfetuarSaque.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEfetuarSaque.setBounds(10, 48, 306, 30);
		getContentPane().add(btnEfetuarSaque);
		btnEfetuarSaque.setEnabled(false);

		JLabel lblModuloDoAdministrador = new JLabel("Modulo do Administrador:");
		lblModuloDoAdministrador.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModuloDoAdministrador.setBounds(10, 89, 306, 30);
		getContentPane().add(lblModuloDoAdministrador);

		JButton btnRelatorioDeCedulas = new JButton("Relatorio de Cedulas");
		btnRelatorioDeCedulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioDeCedulas tela = new RelatorioDeCedulas();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);
			}
		});
		btnRelatorioDeCedulas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRelatorioDeCedulas.setBounds(10, 119, 306, 30);
		getContentPane().add(btnRelatorioDeCedulas);

		JButton btnValorTotalDisponivel = new JButton("Valor total disponivel");
		btnValorTotalDisponivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValorTotalDisponivel tela = new ValorTotalDisponivel();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);
			}
		});
		btnValorTotalDisponivel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnValorTotalDisponivel.setBounds(10, 160, 306, 30);
		getContentPane().add(btnValorTotalDisponivel);

		JButton btnReposicaoDe = new JButton("Reposicao de Cedulas");
		btnReposicaoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReposicaoDeCelulas tela = new ReposicaoDeCelulas();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);
			}
		});
		btnReposicaoDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReposicaoDe.setBounds(10, 201, 306, 30);
		getContentPane().add(btnReposicaoDe);

		JButton btnCotaMinima = new JButton("Cota Minima");
		btnCotaMinima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CotaMinima tela = new CotaMinima();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);
			}
		});
		btnCotaMinima.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCotaMinima.setBounds(10, 242, 306, 30);
		getContentPane().add(btnCotaMinima);

		JLabel lblModuloDeAmbos = new JLabel("Modulo de Ambos:");
		lblModuloDeAmbos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModuloDeAmbos.setBounds(10, 310, 306, 30);
		getContentPane().add(lblModuloDeAmbos);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSair.setBounds(10, 351, 306, 30);
		getContentPane().add(btnSair);

		JButton btnCadastrar = new JButton("Manter Usuario");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastrar tela = new Cadastrar();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCadastrar.setBounds(10, 283, 306, 30);
		getContentPane().add(btnCadastrar);
	}
}
