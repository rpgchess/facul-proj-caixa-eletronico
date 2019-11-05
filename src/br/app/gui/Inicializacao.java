package br.app.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Inicializacao extends JFrame {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicializacao frame = new Inicializacao();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicializacao() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 175);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMsg1 = new JLabel("");
		lblMsg1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMsg1.setBounds(22, 32, 330, 40);
		contentPane.add(lblMsg1);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximum(150);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.BLUE);
		progressBar.setBounds(22, 83, 330, 29);
		contentPane.add(progressBar);

		new Thread() {
			public void run() {
				for (int i = 0; i < 151; i++) {
					try {
						sleep(25);
						progressBar.setValue(i);

						if (progressBar.getValue() == 150) {
							Login tela = new Login();
							tela.setLocationRelativeTo(null);
							tela.setVisible(true);
							dispose();
						}
						if (progressBar.getValue() <= 140)
							lblMsg1.setText("Quase Pronto...");
						if (progressBar.getValue() <= 135)
							lblMsg1.setText("Contando Notas...");
						if (progressBar.getValue() <= 100)
							lblMsg1.setText("Por favor aguarde...");
						if (progressBar.getValue() <= 70)
							lblMsg1.setText("Conectando-se a Base de Dados...");
						if (progressBar.getValue() <= 25)
							lblMsg1.setText("Iniciando o Sistema...");

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

	}
}
