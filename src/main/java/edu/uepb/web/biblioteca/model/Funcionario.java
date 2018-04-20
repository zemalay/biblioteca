package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoFuncionario;

/**
 * A classe POJO do Funcionario
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class Funcionario {
	private int id;
	private String nome;
	private TipoFuncionario tipoFunc;
	private String cpf;
	private String rg;
	private String naturalidade;
	private String endereco;
	private String telefone;
	private String email;
	private String suario;
	private String senha;

	

	public Funcionario(String nome, TipoFuncionario tipoFunc, String cpf, String rg, String naturalidade,
			String endereco, String telefone, String email, String suario, String senha) {
		
		this.nome = nome;
		this.tipoFunc = tipoFunc;
		this.cpf = cpf;
		this.rg = rg;
		this.naturalidade = naturalidade;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.suario = suario;
		this.senha = senha;
	}

	public Funcionario() {

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSuario() {
		return suario;
	}

	public void setSuario(String suario) {
		this.suario = suario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoFuncionario getTipoFunc() {
		return tipoFunc;
	}

	public void setTipoFunc(String tipoFunc) {
		this.tipoFunc = TipoFuncionario.valueOf(tipoFunc);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", tipoFunc=" + tipoFunc + ", cpf=" + cpf + ", rg=" + rg
				+ ", naturalidade=" + naturalidade + ", endereco=" + endereco + ", telefone=" + telefone + ", email="
				+ email + ", suario=" + suario + ", senha=" + senha + "]";
	}



}
