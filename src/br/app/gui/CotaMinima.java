package br.app.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.app.beans.CaixaEletronico;
import br.app.beans.Cota;
import br.app.dao.CotaDAO;

public class CotaMinima extends JDialog {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCota;

	/**
	 * Create the frame.
	 */
	public CotaMinima() {
		setResizable(false);
		setTitle("Cota minima");
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(410, 100, 398, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 11, 41, 25);
		contentPane.add(lblValor);

		textCota = new JTextField();
		textCota.setBounds(61, 11, 101, 25);
		contentPane.add(textCota);
		textCota.setColumns(10);

		Cota cota = new CotaDAO().retrieve(1);

		JLabel label = new JLabel("Ultima cota minima definida: R$" + cota.getMinimo());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 46, 372, 80);
		contentPane.add(label);

		JButton btnNewButton = new JButton("Definir cota minima");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textCota.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira um valor v�lido para definir a Cota m�nima!");
					textCota.setText(null);
					textCota.requestFocus();
				} else {

					int minimo = Integer.parseInt(textCota.getText());
					if (minimo <= 0) {
						JOptionPane.showMessageDialog(null, "Valor n�o permitido para esta opera��o!");
						textCota.setText(null);
						textCota.requestFocus();
					} else {
						CaixaEletronico caixa = new CaixaEletronico();

						JOptionPane.showMessageDialog(null, caixa.armazenaCotaMinima(minimo));
						label.setText("Ultima cota minima definida: R$" + minimo + ",00");
						textCota.setText(null);
						textCota.requestFocus();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(172, 10, 210, 27);
		contentPane.add(btnNewButton);

	}
}
