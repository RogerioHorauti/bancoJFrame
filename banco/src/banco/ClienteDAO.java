package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//acesso a base de dados
public class ClienteDAO implements GenericDao<Cliente>{

	private static Connection conn;
	private static ResultSet rs;
	private static PreparedStatement stm;
	
	@Override
	public List<Cliente> all() {
		// Abri a conexao com o banco
		conn = ConexaoMySql.getConnection();
		// Responsavel por executar a query
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			stm = conn.prepareStatement("Select idcliente, nome, rg, cpf, dataConta from clientes");
			// Execucao da query e armazenamento do resultado
			rs = stm.executeQuery();
				while(rs.next()){
				// Criando objetos cliente
				Cliente cli = new Cliente();
				cli.setIdCliente(rs.getInt("idcliente"));
				cli.setNome(rs.getString("nome"));
				cli.setRg(rs.getString("rg"));
				cli.setCpf(rs.getString("cpf"));
				cli.setDataConta(rs.getDate("dataConta"));
				// adicionando na lista de retorno
				clientes.add(cli);	
				}
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os clientes\nErro : " + e);	
		}
		return clientes;
	}

	@Override
	public boolean insert(Cliente cliente) {
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
			System.out.println("Erro ao adicionar cliente\nErro : " + e);
		}
		return retorno;
	}

	@Override
	public boolean update(Cliente cliente) {
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
			System.out.println("Erro ao atualizar cliente\nErro : " + e);
		}
		return retorno;
	}

	@Override
	public boolean delete(int id) {
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
			System.out.println("Erro ao excluir cliente\nErro : " + e);
		}
		return retorno;
	}

}
