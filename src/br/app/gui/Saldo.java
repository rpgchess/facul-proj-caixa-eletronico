package br.app.gui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.app.beans.Cliente;
import br.app.dao.ClienteDAO;

public class Saldo extends JDialog {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Saldo() {
		setResizable(false);
		setModal(true);
		setTitle("Extrato");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 338, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Login login = new Login();
		Cliente cliente = new ClienteDAO().retrieve(Integer.toString(login.getNmrConta()));
		login.dispose();

		JLabel lblNewLabel = new JLabel("Numero da conta:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 125, 38);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel(cliente.getConta());
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(145, 11, 177, 38);
		contentPane.add(label);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(10, 60, 51, 38);
		contentPane.add(lblNome);

		JLabel label_2 = new JLabel(cliente.getNome());
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(66, 60, 256, 38);
		contentPane.add(label_2);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSaldo.setBounds(10, 109, 43, 38);
		contentPane.add(lblSaldo);

		JLabel label_1 = new JLabel(Double.toString(cliente.getSaldo()));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(58, 109, 264, 38);
		contentPane.add(label_1);

	}
}
