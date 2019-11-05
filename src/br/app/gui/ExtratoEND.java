package br.app.gui;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.app.beans.Cliente;
import br.app.beans.Extrato;
import br.app.dao.ClienteDAO;
import br.app.dao.ExtratoDAO;

public class ExtratoEND extends JDialog {
	static final long serialVersionUID = 1L;

	private void close() {
		dispose();
		Login login = new Login();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
	}

	private String getDataAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy/MM/dd");
		return formatarDate.format(data);
	}

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ExtratoEND() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				close();
			}
		});
		setModal(true);
		setTitle("Extrato do dia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Login login = new Login();
		Cliente cliente = new ClienteDAO().retrieve(Integer.toString(login.getNmrConta()));
		ArrayList<Extrato> lista = new ExtratoDAO().listAll(getDataAtual(), login.getNome() + " - Saque");
		login.dispose();

		// Intermediador : fonte -> componente
		DefaultListModel<Extrato> model = new DefaultListModel<>();

		for (Extrato e : lista)
			model.addElement(e);

		JLabel lblNewLabel = new JLabel("Numero da conta: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 0, 129, 38);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel(cliente.getConta());
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(145, 0, 179, 38);
		contentPane.add(label);

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(10, 37, 51, 38);
		contentPane.add(lblNome);

		JLabel label_2 = new JLabel(cliente.getNome());
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(66, 37, 258, 38);
		contentPane.add(label_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 314, 233);
		contentPane.add(scrollPane);

		JList<Extrato> list = new JList<Extrato>();
		scrollPane.setViewportView(list);

		list.setModel(model);
	}
}
