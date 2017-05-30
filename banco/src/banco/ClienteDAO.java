package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

//acesso a base de dados
public class ClienteDAO {

	static Connection conn;
	static ClienteDAO dao;
	static ResultSet rs;
	static PreparedStatement stm;

	public List<Cliente> primeiro() {

		List<Cliente> clientes = new ArrayList<Cliente>();

		// Abri a conexao com o banco
		conn = ConexaoMySql.getConnection();
		// Responsavel por executar a query

		try {
			// Criacao da query
			stm = conn
					.prepareStatement("Select idcliente, nome, rg, cpf, dataConta from clientes");
			// Execucao da query e armazenamento do resultado
			rs = stm.executeQuery();
			// Primeiro
			rs.first();
			// Criando objetos cliente
			Cliente cli = new Cliente();
			cli.setIdCliente(rs.getInt("idcliente"));
			cli.setNome(rs.getString("nome"));
			cli.setRg(rs.getString("rg"));
			cli.setCpf(rs.getString("cpf"));
			cli.setDataConta(rs.getDate("dataConta"));
			// adicionando na lista de retorno
			clientes.add(cli);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// retornei a lista
		return clientes;
	}

	public List<Cliente> ultimo() {

		List<Cliente> clientes = new ArrayList<Cliente>();

		// Abri a conexao com o banco
		conn = ConexaoMySql.getConnection();
		// Responsavel por executar a query

		try {
			// Criacao da query
			stm = conn.prepareStatement("Select idcliente, nome, rg, cpf, dataConta from clientes");
			// Execucao da query e armazenamento do resultado
			rs = stm.executeQuery();
			// Ultimo
			rs.last();
			// Criando objetos cliente
			Cliente cli = new Cliente();
			cli.setIdCliente(rs.getInt("idcliente"));
			cli.setNome(rs.getString("nome"));
			cli.setRg(rs.getString("rg"));
			cli.setCpf(rs.getString("cpf"));
			cli.setDataConta(rs.getDate("dataConta"));
			// adicionando na lista de retorno
			clientes.add(cli);

		} catch (SQLException e) {
			System.out.println("Erro ao mostrar dados\nErro : " + e);
		}
		// retornei a lista
		return clientes;
	}

	public List<Cliente> anterior() {
		// Abri a conexao com o banco
		//conn = ConexaoMySql.getConnection();
		// Responsavel por executar a query

		List<Cliente> clientes = new ArrayList<Cliente>();

		try {
			// Criacao da query
			//stm = conn.prepareStatement("Select idcliente, nome, rg, cpf, dataConta from clientes");
			// Execucao da query e armazenamento do resultado
			//rs = stm.executeQuery();

				rs.previous();
				// Criando objetos cliente
				Cliente cli = new Cliente();
				cli.setIdCliente(rs.getInt("idcliente"));
				cli.setNome(rs.getString("nome"));
				cli.setRg(rs.getString("rg"));
				cli.setCpf(rs.getString("cpf"));
				cli.setDataConta(rs.getDate("dataConta"));
				// adicionando na lista de retorno
				clientes.add(cli);
			
		} catch (SQLException e) {
			//System.out.println("Erro ao mostrar dados\nErro : " + e);
			JOptionPane.showMessageDialog(null, "Fim do registro");
		}
		// retornei a lista
		return clientes;
	}

	public List<Cliente> proximo() {
		// Abri a conexao com o banco
		//conn = ConexaoMySql.getConnection();
		// Responsavel por executar a query
		List<Cliente> clientes = new ArrayList<Cliente>();

		try {
			//stm = conn.prepareStatement("Select idcliente, nome, rg, cpf, dataConta from clientes");
			// Execucao da query e armazenamento do resultado
			//rs = stm.executeQuery();
				rs.next();
				// Criando objetos cliente
				Cliente cli = new Cliente();
				cli.setIdCliente(rs.getInt("idcliente"));
				cli.setNome(rs.getString("nome"));
				cli.setRg(rs.getString("rg"));
				cli.setCpf(rs.getString("cpf"));
				cli.setDataConta(rs.getDate("dataConta"));
				// adicionando na lista de retorno
				clientes.add(cli);
			
		} catch (SQLException e) {
			//System.out.println("Erro ao mostrar dados\nErro : " + e);
			JOptionPane.showMessageDialog(null, "Fim do registro");
		}
		// retornei a lista
		return clientes;
	}

	
	// Adiciona
	public boolean adicionaCliente(Cliente cliente) {
		// Nenhum insert foi realizado
		boolean retorno = false;

		// Abri a conexao com o banco
		conn = ConexaoMySql.getConnection();

		try {
			// Criacao da query
			stm = conn.prepareStatement("Insert into clientes(nome, rg, cpf, dataConta) values (?,?,?,?)");
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getRg());
			stm.setString(3, cliente.getCpf());
			stm.setDate(4, cliente.getDataConta());
			if (stm.executeUpdate() > 0) {
				retorno = true;
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	// Alualiza
	public boolean atualizaCliente(Cliente cliente) {
		boolean retorno = false;

		// Abri a conexao com o banco
		conn = ConexaoMySql.getConnection();

		try {
			// Criacao da query
			stm = conn.prepareStatement("Update clientes Set nome=?, rg=?, cpf=?, dataConta=? Where idcliente=?");
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getRg());
			stm.setString(3, cliente.getCpf());
			stm.setDate(4, cliente.getDataConta());
			stm.setInt(5, cliente.getIdCliente());
			if (stm.executeUpdate() > 0) {
				retorno = true;
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	// Apaga
	public boolean apagaCliente(int id) {
		boolean retorno = false;
		// Abri a conexao com o banco
		conn = ConexaoMySql.getConnection();

		try {
			// Criacao da query
			stm = conn.prepareStatement("Delete from clientes Where idcliente=?");
			stm.setInt(1, id);
			if (stm.executeUpdate() > 0) {
				retorno = true;
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static void main(String[] args) throws SQLException {
		
		conn = ConexaoMySql.getConnection();
		stm = conn.prepareStatement("Select idcliente, nome, rg, cpf, dataConta from clientes");
		// Execucao da query e armazenamento do resultado
		rs = stm.executeQuery();
		
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dao = new ClienteDAO();

		// Visualizar os clientes cadastrados
		for (Cliente cli : dao.proximo()) {
			System.out.println("id: " + cli.getIdCliente() + " - Nome: "
					+ cli.getNome() + " - CPF: " + cli.getCpf() + " - RG: "
					+ cli.getRg() + " - Data Conta: "
					+ sdf.format(cli.getDataConta()));
		}
		for (Cliente cli : dao.proximo()) {
			System.out.println("id: " + cli.getIdCliente() + " - Nome: "
					+ cli.getNome() + " - CPF: " + cli.getCpf() + " - RG: "
					+ cli.getRg() + " - Data Conta: "
					+ sdf.format(cli.getDataConta()));
		}
		for (Cliente cli : dao.primeiro()) {
			System.out.println("id: " + cli.getIdCliente() + " - Nome: "
					+ cli.getNome() + " - CPF: " + cli.getCpf() + " - RG: "
					+ cli.getRg() + " - Data Conta: "
					+ sdf.format(cli.getDataConta()));
		}
		for (Cliente cli : dao.ultimo()) {
			System.out.println("id: " + cli.getIdCliente() + " - Nome: "
					+ cli.getNome() + " - CPF: " + cli.getCpf() + " - RG: "
					+ cli.getRg() + " - Data Conta: "
					+ sdf.format(cli.getDataConta()));
		}
		*/
	}
}
