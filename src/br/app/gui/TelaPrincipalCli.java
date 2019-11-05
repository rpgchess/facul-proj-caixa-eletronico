package br.app.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaPrincipalCli extends JFrame {
	static final long serialVersionUID = 1L;

	private void close() {
		ExtratoEND extratoEnd = new ExtratoEND();
		extratoEnd.setLocationRelativeTo(null);
		extratoEnd.setVisible(true);
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipalCli() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (e.getID() == WindowEvent.WINDOW_CLOSING) {
					close();
				}
			}
		});
		setTitle("Caixa eletronico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 328, 397);
		getContentPane().setLayout(null);

		JLabel lblModuloDoCliente = new JLabel("Modulo do Cliente:");
		lblModuloDoCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModuloDoCliente.setBounds(10, 11, 302, 30);
		getContentPane().add(lblModuloDoCliente);

		JButton btnEfetuarSaque = new JButton("Efetuar Saque");
		btnEfetuarSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EfetuarSaque tela = new EfetuarSaque();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);		
			}
		});
		btnEfetuarSaque.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEfetuarSaque.setBounds(10, 48, 302, 30);
		getContentPane().add(btnEfetuarSaque);

		JLabel lblModuloDoAdministrador = new JLabel("Modulo do Administrador:");
		lblModuloDoAdministrador.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModuloDoAdministrador.setBounds(10, 89, 302, 30);
		getContentPane().add(lblModuloDoAdministrador);

		JButton btnRelatorioDeCedulas = new JButton("Relatorio de Cedulas");
		btnRelatorioDeCedulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioDeCedulas rdc = new RelatorioDeCedulas();
				rdc.setVisible(true);
			}
		});
		btnRelatorioDeCedulas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRelatorioDeCedulas.setBounds(10, 119, 302, 30);
		getContentPane().add(btnRelatorioDeCedulas);
		btnRelatorioDeCedulas.setEnabled(false);

		JButton btnValorTotalDisponivel = new JButton("Valor total disponivel");
		btnValorTotalDisponivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValorTotalDisponivel vtd = new ValorTotalDisponivel();
				vtd.setVisible(true);
			}
		});
		btnValorTotalDisponivel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnValorTotalDisponivel.setBounds(10, 160, 302, 30);
		getContentPane().add(btnValorTotalDisponivel);
		btnValorTotalDisponivel.setEnabled(false);

		JButton btnReposicaoDe = new JButton("Reposicao de Cedulas");
		btnReposicaoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReposicaoDeCelulas rdc = new ReposicaoDeCelulas();
				rdc.setVisible(true);
			}
		});
		btnReposicaoDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReposicaoDe.setBounds(10, 201, 302, 30);
		getContentPane().add(btnReposicaoDe);
		btnReposicaoDe.setEnabled(false);

		JButton btnCotaMinima = new JButton("Cota Minima");
		btnCotaMinima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CotaMinima cm = new CotaMinima();
				cm.setVisible(true);
			}
		});
		btnCotaMinima.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCotaMinima.setBounds(10, 242, 302, 30);
		getContentPane().add(btnCotaMinima);
		btnCotaMinima.setEnabled(false);

		JLabel lblModuloDeAmbos = new JLabel("Modulo de Ambos:");
		lblModuloDeAmbos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModuloDeAmbos.setBounds(10, 283, 302, 30);
		getContentPane().add(lblModuloDeAmbos);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSair.setBounds(10, 314, 302, 30);
		getContentPane().add(btnSair);
	}

}
