package br.app.gui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.app.beans.CaixaEletronico;

public class ValorTotalDisponivel extends JDialog {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ValorTotalDisponivel() {
		setResizable(false);
		setTitle("Valor Total");
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(410, 100, 330, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CaixaEletronico caixa = new CaixaEletronico();

		String total = caixa.pegaValorTotalDisponivel();

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(total);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 64, 304, 32);
		contentPane.add(lblNewLabel);

		JLabel lblValorTotalDisponivel = new JLabel();
		lblValorTotalDisponivel.setText("Valor total disponivel no caixa.");
		lblValorTotalDisponivel.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotalDisponivel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblValorTotalDisponivel.setBounds(10, 11, 304, 32);
		contentPane.add(lblValorTotalDisponivel);
	}
}