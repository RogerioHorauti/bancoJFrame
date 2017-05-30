package banco;

import java.sql.Date;

public class Cliente {

	private int idCliente;
	private String nome;
	private String rg;
	private String cpf;
	private Date dataConta;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataConta() {
		return dataConta;
	}
	public void setDataConta(Date dataConta) {
		this.dataConta = dataConta;
	}
	
}
