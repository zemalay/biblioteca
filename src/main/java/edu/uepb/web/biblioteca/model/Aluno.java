package edu.uepb.web.biblioteca.model;

/**
 * A classe POJO do Aluno
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class Aluno {
	private int id;
	private String matricula;
	private String rg;
	private String cpf;
	private String nome;
	private String nomeMae;
	private String naturalidade;
	private String endereco;
	private String telefone;
	private Curso curso;
	private String ano;
	private String periodoIngresso;
	private String email;
	private String senha;

	

	public Aluno() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getPeriodoIngresso() {
		return periodoIngresso;
	}

	public void setPeriodoIngresso(String periodoIngresso) {
		this.periodoIngresso = periodoIngresso;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Aluno(String matricula, String rg, String cpf, String nome, String nomeMae, String naturalidade,
			String endereco, String telefone, Curso curso, String ano, String periodoIngresso, String email,
			String senha) {
		super();
		this.matricula = matricula;
		this.rg = rg;
		this.cpf = cpf;
		this.nome = nome;
		this.nomeMae = nomeMae;
		this.naturalidade = naturalidade;
		this.endereco = endereco;
		this.telefone = telefone;
		this.curso = curso;
		this.ano = ano;
		this.periodoIngresso = periodoIngresso;
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", matricula=" + matricula + ", rg=" + rg + ", cpf=" + cpf + ", nome=" + nome
				+ ", nomeMae=" + nomeMae + ", naturalidade=" + naturalidade + ", endereco=" + endereco + ", telefone="
				+ telefone + ", curso=" + curso + ", ano=" + ano + ", periodoIngresso=" + periodoIngresso + ", senha="
				+ senha + "]";
	}

}
