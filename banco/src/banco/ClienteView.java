//14.11.14
package banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ClienteView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public ClienteView() throws ParseException {
		initComponents();
		initListeners();		
	}
	
	private void initComponents() {
		//Formulario
		setSize(360, 240);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Cadastro de Clientes");
		//Rotulo
		lblId = new JLabel("ID");
		lblNome = new JLabel("Nome");
		lblCPF = new JLabel("CPF");
		lblRg = new JLabel("RG");
		lblDataConta = new JLabel("Data da Conta");
		//Campo texto
		txtId = new JTextField();
		txtNome = new JTextField();
		txtCpf = new JFormattedTextField();
		txtRg = new JFormattedTextField();
		txtDataConta = new JFormattedTextField();
		//Botao
		btnNovo = new JButton();
		btnSalvar = new JButton();
		btnAtualizar = new JButton();
		btnApagar = new JButton();
		btnPrimeiro = new JButton();
		btnAnterior = new JButton();
		btnProximo = new JButton();
		btnUltimo = new JButton();
		
		//move e redimensiona componestes (eixo x, eixo y, largura, altura);
		//Rotulos
		lblId.setBounds(80, 10, 20, 20);
		add(lblId);
		lblNome.setBounds(60, 40, 40, 20);
		add(lblNome);
		
		lblRg.setBounds(75, 70, 20, 20);
		add(lblRg);
		lblCPF.setBounds(70, 100, 30, 20);
		add(lblCPF);
		lblDataConta.setBounds(10, 130, 100, 20);
		add(lblDataConta);
		//move e redimensiona componestes (eixo x, eixo y, largura, altura);
		//Botoes
		btnNovo.setBounds(50, 160, 40, 30);
		add(btnNovo);
		btnSalvar.setBounds(100, 160, 40, 30);
		add(btnSalvar);
		btnAtualizar.setBounds(150, 160, 40, 30);
		add(btnAtualizar);
		btnApagar.setBounds(200, 160, 40, 30);
		add(btnApagar);
		btnPrimeiro.setBounds(280, 20, 40, 30);
		add(btnPrimeiro);
		btnUltimo.setBounds(280, 60, 40, 30);
		add(btnUltimo);
		btnProximo.setBounds(280, 100, 40, 30);
		add(btnProximo);
		btnAnterior.setBounds(280, 140, 40, 30);
		add(btnAnterior);
	
		//move e redimensiona componestes (eixo x, eixo y, largura, altura);
		//Campo texto
		txtId.setBounds(110, 10, 40, 20);
		add(txtId);
		txtId.setEnabled(false);
		txtNome.setBounds(110, 40, 150, 20);
		add(txtNome);
		txtRg.setBounds(110, 70, 90, 20);
		add(txtRg);
		txtCpf.setBounds(110, 100, 100, 20);
		add(txtCpf);
		txtDataConta.setBounds(110, 130, 75, 20);
		add(txtDataConta);
		
	}
	
	private static void primeiro(){
		dao = new ClienteDAO();
		//Visualizar os clientes cadastrados
		for(Cliente cli : dao.primeiro()) {
			
			int id = cli.getIdCliente();  
			String str = String.valueOf(id);  
			txtId.setText(str);
			
			txtNome.setText(cli.getNome());
			txtRg.setText(cli.getCpf());
			txtCpf.setText(cli.getRg());
			txtDataConta.setText(formData.format(cli.getDataConta()));
		}
	}
	
	private void limpar(){
		txtId.setText("");
		txtNome.setText("");
		txtRg.setText("");
		txtCpf.setText("");
		txtDataConta.setText("");
	}
	
	//listeners=Escuta 
	private void initListeners() throws ParseException {
		
		btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/imagem/save1.png")));
		btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/imagem/new.png")));
		btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/imagem/write.png")));
		btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/imagem/delete.png")));
		btnPrimeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/imagem/first.png")));
		btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/imagem/previws.png")));
		btnProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/imagem/nexr.png"))); 
		btnUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/imagem/last.png")));
		
		//((JFormattedTextField) txtCpf).setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.##")));
	    
		//((JFormattedTextField) txtRg).setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
		
		((JFormattedTextField) txtDataConta).setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
		
		btnNovo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		
		//Adiciona um ActionListener=Acao para o botao Salvar		
		btnSalvar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cliente = new Cliente();
				//Pegar os valores dos campos tipo String
				cliente.setNome(txtNome.getText());
				cliente.setRg(txtRg.getText());
				cliente.setCpf(txtCpf.getText());
				
				Date recebeFormData = null;
				try {
					recebeFormData = new Date( formData.parse(txtDataConta.getText()).getTime() )  ;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Armazena na classe Cliente
				cliente.setDataConta(recebeFormData);
				dao = new ClienteDAO();
				//Adiciona no banco de dados
				if(dao.adicionaCliente(cliente)) {
					JOptionPane.showMessageDialog(null, "Adicionado");
					limpar();
				}
				
			}
		});
		
		btnAtualizar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cliente = new Cliente();
				cliente.setIdCliente(Integer.parseInt(txtId.getText()));
				
				//Pegar os valores dos campos tipo String
				cliente.setNome(txtNome.getText());
				cliente.setRg(txtRg.getText());
				cliente.setCpf(txtCpf.getText());
				
				Date recebeFormData = null;
				try {
					recebeFormData = new Date( formData.parse(txtDataConta.getText()).getTime() )  ;
				} catch (ParseException i) {
					// TODO Auto-generated catch block
					i.printStackTrace();
				}
				
				//Armazena na classe Cliente
				cliente.setDataConta(recebeFormData);
				dao = new ClienteDAO();
				if(dao.atualizaCliente(cliente)){
					JOptionPane.showMessageDialog(null, "Atualizado");
				}
			}
		});
		
		//Adiciona um ActionListener=Acao para o boto Apagar
		btnApagar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				dao = new ClienteDAO();
				//if(txtId.getText()== ""){// Enviar um int
					if(dao.apagaCliente(Integer.parseInt(txtId.getText())))
						JOptionPane.showMessageDialog(null, "Excluido");
						limpar();
				//}else{
					//JOptionPane.showMessageDialog(null, "Nenhum registro encontrado");
					//System.out.println("nulo");
				//}
				
			}
		});

		//Adiciona um ActionListener=acao para o botao Atualizar
		btnPrimeiro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				//primeiro
				primeiro();
			}
		});
		
		btnAnterior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				dao = new ClienteDAO();			
				//Visualizar os clientes cadastrados
				for(Cliente cli : dao.anterior()) {
					
					int id = cli.getIdCliente();  
					String str = String.valueOf(id);  
					txtId.setText(str);
					
					txtNome.setText(cli.getNome());
					txtRg.setText(cli.getCpf());
					txtCpf.setText(cli.getRg());
					txtDataConta.setText(formData.format(cli.getDataConta()));
				}
	
			}
		});
		
		btnProximo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dao = new ClienteDAO();				
				//Visualizar os clientes cadastrados
				for(Cliente cli : dao.proximo()) {
					
					int id = cli.getIdCliente();  
					String str = String.valueOf(id);  
					txtId.setText(str);
					
					txtNome.setText(cli.getNome());
					txtRg.setText(cli.getCpf());
					txtCpf.setText(cli.getRg());
					txtDataConta.setText(formData.format(cli.getDataConta()));
				}
	
			}
		});
		
		btnUltimo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				dao = new ClienteDAO();		
				//Visualizar os clientes cadastrados
				for(Cliente cli : dao.ultimo()) {
					
					int id = cli.getIdCliente();  
					String str = String.valueOf(id);  
					txtId.setText(str);
					
					txtNome.setText(cli.getNome());
					txtRg.setText(cli.getCpf());
					txtCpf.setText(cli.getRg());
					txtDataConta.setText(formData.format(cli.getDataConta()));
				}
			}
		});
	}

	public static void main(String[] args) throws ParseException {
		
		new ClienteView().setVisible(true);	
		primeiro();
	}
	
	private JLabel lblId;
	private JButton btnNovo;
	private JButton btnAtualizar;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNome;
	private JLabel lblRg;
	private JLabel lblCPF;
	private JLabel lblDataConta;
	private static JTextField txtId;
	private static JTextField txtNome;
	private static JTextField txtRg;
	private static JTextField txtCpf;
	private static JTextField txtDataConta;
	private JButton btnSalvar;
	private JButton btnPrimeiro;
	private JButton btnApagar;
	private Cliente cliente;
	private static ClienteDAO dao;
	private static SimpleDateFormat formData = new SimpleDateFormat("dd/mm/yyyy");
	
}
