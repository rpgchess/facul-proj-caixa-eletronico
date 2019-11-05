package br.app.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.app.beans.CaixaEletronico;

public class RelatorioDeCedulas extends JDialog {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public RelatorioDeCedulas() {
		setTitle("Listagem de c\u00E9dulas");
		setResizable(false);
		setModal(true);// trava a tela
		setBounds(410, 100, 407, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CaixaEletronico caixa = new CaixaEletronico();

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		textPane.setBounds(10, 81, 381, 155);
		contentPane.add(textPane);
		
		JButton btnListarCdulas = new JButton("Listar C\u00E9dulas");
		btnListarCdulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String res = caixa.pegaRelatorioCedulas().replaceAll(", ", "\n");
				res = res.substring(1, res.length() - 1);
				textPane.setText(res);
			}
		});
		btnListarCdulas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListarCdulas.setBounds(10, 11, 381, 34);
		contentPane.add(btnListarCdulas);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantidade.setBounds(195, 57, 196, 23);
		contentPane.add(lblQuantidade);
		
		JLabel lblCdulas = new JLabel("C\u00E9dulas");
		lblCdulas.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdulas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCdulas.setBounds(10, 56, 196, 23);
		contentPane.add(lblCdulas);
	}
}
