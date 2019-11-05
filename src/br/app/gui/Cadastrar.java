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
import javax.swing.border.EmptyBorder;

import br.app.beans.Cliente;
import br.app.dao.ClienteDAO;

public class Cadastrar extends JDialog {
	//inicializando as variaveis
	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textConta;
	private JTextField textSenha;
	private JTextField textNome;
	private JTextField textSaldo;

	/**
	 * Create the frame.
	 */
	public Cadastrar() {
		setResizable(false);
		setModal(true);
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 100, 354, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
	// inicializando os componentes
		
		JLabel label_2 = new JLabel("Conta:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(10, 11, 46, 20);
		contentPane.add(label_2);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 42, 46, 20);
		contentPane.add(lblSenha);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 73, 46, 20);
		contentPane.add(lblNome);

		JLabel lblSaldo = new JLabel("Saldo R$:");
		lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSaldo.setBounds(10, 104, 67, 20);
		contentPane.add(lblSaldo);

		textConta = new JTextField();
		textConta.setColumns(10);
		textConta.setBounds(66, 13, 134, 20);
		contentPane.add(textConta);

		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(66, 44, 134, 20);
		contentPane.add(textSenha);

		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(66, 75, 270, 20);
		contentPane.add(textNome);

		textSaldo = new JTextField();
		textSaldo.setColumns(10);
		textSaldo.setBounds(76, 106, 124, 20);
		contentPane.add(textSaldo);

	// utilizando o botao cadastrar para cadastrar um novo cliente
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Mostrar mensagem caso o campo Conta esteja vazio
				if (textConta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o Campo Conta!");
					textConta.requestFocus();
				} 
				// Mostrar mensagem caso o campo Senha esteja vazio
				else if (textSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o Campo Senha!");
					textSenha.requestFocus();
				}
				// Mostrar mensagem caso o campo Nome esteja vazio
				else if (textNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o Campo Nome!");
					textNome.requestFocus();
				}
				// Mostrar mensagem caso o campo Saldo esteja vazio
				else if (textSaldo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o Campo Saldo!");
					textNome.requestFocus();
				}
				// Mostrar mensagem caso o campo saldo seja negativo
				else if (Integer.parseInt(textSaldo.getText()) < 0) {
					JOptionPane.showMessageDialog(null, "Cliente Não Pode Ser Cadastrado Com Saldo Devedor!");
					textSaldo.setText(null);
					textSaldo.requestFocus();
				}
				// Cadastrar cliente caso esteja todos os campos preenchidos corretamente
				else {

					// Criando um objeto cliente com os dados
					// dos campos do formulario
					Cliente cliente = new Cliente();
					cliente.setConta(textConta.getText());
					cliente.setSenha(textSenha.getText());
					cliente.setNome(textNome.getText());
					cliente.setSaldo(Integer.parseInt(textSaldo.getText()));

					// Salvando os dados no Banco de Dados
					ClienteDAO dao = new ClienteDAO();
					dao.create(cliente);

					// Avisando ao usuario
					JOptionPane.showMessageDialog(Cadastrar.this, "Cadastro feito com sucesso!");

					// Limpando os campos e colocando o foco no campo Conta
					textConta.setText(null);
					textSenha.setText(null);
					textNome.setText(null);
					textSaldo.setText(null);
					textConta.requestFocus();
				}
			}
		});
		btnCadastrar.setBounds(221, 105, 115, 23);
		contentPane.add(btnCadastrar);

		// Utilizando o botao Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.showConfirmDialog(btnCancelar, "Deseja realmente cancelar a Operação?", "", JOptionPane.YES_NO_OPTION);
				if(dialogButton == JOptionPane.YES_OPTION) {
					dispose();					
				}
			}
		});
		btnCancelar.setBounds(20, 135, 91, 23);
		contentPane.add(btnCancelar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente();
				cliente.setConta(textConta.getText());
				cliente.setSenha(textSenha.getText());
				cliente.setNome(textNome.getText());
				cliente.setSaldo(Double.parseDouble((textSaldo.getText())));

				// Efetuamos a operação de UPDATE
				new ClienteDAO().update(cliente);

				// Avisando ao usuário
				JOptionPane.showMessageDialog(Cadastrar.this, "Dados Alterados com sucesso!");

				// Limpando os campos e colocando o foco no campo RGM
				textConta.setText(null);
				textSenha.setText(null);
				textNome.setText(null);
				textSaldo.setText(null);
				textConta.requestFocus();

			}
		});
		btnAlterar.setBounds(221, 135, 115, 23);
		contentPane.add(btnAlterar);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String conta = textConta.getText();

				Cliente cliente = new ClienteDAO().retrieve(conta);

				if (cliente == null) {
					JOptionPane.showMessageDialog(Cadastrar.this, "Registro n�o encontrado!");

					// Limpando os campos e colocando o foco no campo Conta
					textConta.setText(null);
					textSenha.setText(null);
					textNome.setText(null);
					textSaldo.setText(null);
					textConta.requestFocus();

				} else {
					textConta.setText(cliente.getConta());
					textSenha.setText(cliente.getSenha());
					textNome.setText(cliente.getNome());
					textSaldo.setText(String.valueOf(cliente.getSaldo() + "0"));
				}
			}
		});
		btnConsultar.setBounds(210, 12, 126, 23);
		contentPane.add(btnConsultar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Exclus�o", JOptionPane.YES_NO_OPTION);
				int status = JOptionPane.showConfirmDialog(Cadastrar.this, "Confirma a Exclus�o");

				if (status == 0) {
					String conta = textConta.getText();

					new ClienteDAO().delete(conta);

					// Avisando ao usuário
					JOptionPane.showMessageDialog(Cadastrar.this, "Cliente excluido com sucesso!");

					// Limpando os campos e colocando o foco no campo RGM

					textConta.setText(null);
					textSenha.setText(null);
					textNome.setText(null);
					textSaldo.setText(null);
					textConta.requestFocus();

				}
			}
		});
		btnExcluir.setBounds(120, 135, 91, 23);
		contentPane.add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar Campos");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textConta.setText(null);
				textSenha.setText(null);
				textNome.setText(null);
				textSaldo.setText(null);
				textConta.requestFocus();
			}
		});
		btnLimpar.setBounds(210, 43, 126, 23);
		contentPane.add(btnLimpar);
	}
}
