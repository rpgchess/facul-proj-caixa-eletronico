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

import br.app.beans.Admin;
import br.app.beans.CaixaEletronico;
import br.app.dao.AdministradorDAO;

public class ReposicaoDeCelulas extends JDialog {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCedula;
	private JTextField textQuantidade;

	/**
	 * Create the frame.
	 */
	public ReposicaoDeCelulas() {
		setTitle("Reposi\u00E7\u00E3o de c\u00E9dulas");
		setResizable(false);
		setModal(true);// trava a tela
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(410, 100, 366, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CaixaEletronico caixa = new CaixaEletronico();

		JLabel lblNota = new JLabel("C\u00E9dula");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNota.setBounds(10, 23, 53, 24);
		contentPane.add(lblNota);

		textCedula = new JTextField();
		textCedula.setHorizontalAlignment(SwingConstants.CENTER);
		textCedula.setBounds(99, 25, 70, 24);
		contentPane.add(textCedula);
		textCedula.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cedula = textCedula.getText();

				Admin admin = new AdministradorDAO().retrieve(cedula);
				if (cedula.equals("2") || cedula.equals("5") || cedula.equals("10") || cedula.equals("20")
						|| cedula.equals("50") || cedula.equals("100")) {

					if (admin == null) {
						JOptionPane.showMessageDialog(null, "Não possuímos Cédulas com esse valor!");

						// Limpando os campos e colocando o foco no campo Conta
						textCedula.setText(null);
						textQuantidade.setText(null);
						textCedula.requestFocus();

					} else {
						textCedula.setText(admin.getNota());
						textQuantidade.setText(admin.getQuantidade());

					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Não possuímos Cédulas com esse valor!");
					textCedula.setText(null);
					textQuantidade.setText(null);
					textCedula.requestFocus();
				
				}
			}

		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPesquisar.setBounds(179, 22, 171, 26);
		contentPane.add(btnPesquisar);

		textQuantidade = new JTextField();
		textQuantidade.setColumns(10);
		textQuantidade.setBounds(99, 60, 251, 24);
		contentPane.add(textQuantidade);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantidade.setBounds(10, 58, 79, 24);
		contentPane.add(lblQuantidade);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cedula = textCedula.getText();
				String quantidade = textQuantidade.getText();

				if (textCedula.getText().isEmpty() && !textQuantidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe uma nota existente para repor!");
					textQuantidade.setText(null);
					textCedula.requestFocus();
				}
				else if (textCedula.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe uma nota existente para repor!");
					textCedula.requestFocus();
				} 
				else if (Integer.parseInt(textQuantidade.getText()) < 0) {
					JOptionPane.showMessageDialog(null,"Esta operação não é permitida!");
					textQuantidade.setText(null);
					textQuantidade.requestFocus();
				}

				else {

					JOptionPane.showMessageDialog(null,
							caixa.reposicaoCedulas(Integer.parseInt(cedula), Integer.parseInt(quantidade)));
					textCedula.setText(null);
					textQuantidade.setText(null);
					textCedula.requestFocus();

				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar.setBounds(231, 95, 119, 26);
		contentPane.add(btnAtualizar);
		contentPane.setLayout(null);

	}

}
