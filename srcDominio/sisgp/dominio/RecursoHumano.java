package sisgp.dominio;

import java.util.Date;
import java.util.List;

//Referencia: http://blog.codejava.net/nam/implement-getters-and-setters-for-collection-type/

public class RecursoHumano extends EntidadeDominio {
	
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String telefone;
	private String cargo;
	private Double valorHora;
	private Double valorHoraExtra;
	private String observacao;
	
	private Nivel nivel;
	private GrupoAcesso grupoAcesso;
	
	List<ApontamentoHoras> listaApontamentoHoras;
	
	
	
	
	public RecursoHumano() {}
	public RecursoHumano(Integer id, String nome, String sobrenome) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	public RecursoHumano(Integer id) {
		this.id = id;
	}
	public RecursoHumano(String nome, String sobrenome) {
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the sobreNome
	 */
	public String getSobrenome() {
		return sobrenome;
	}
	/**
	 * @param sobreNome the sobreNome to set
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}
	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	/**
	 * @return the idNivel
	 */
	/*public Integer getIdNivel() {
		return idNivel;
	}
	/**
	 * @param idNivel the idNivel to set
	 */
	/*public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}*/
	/**
	 * @return the valorHora
	 */
	public Double getValorHora() {
		return valorHora;
	}
	/**
	 * @param valorHora the valorHora to set
	 */
	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}
	/**
	 * @return the valorHoraExtra
	 */
	public Double getValorHoraExtra() {
		return valorHoraExtra;
	}
	/**
	 * @param valorHoraExtra the valorHoraExtra to set
	 */
	public void setValorHoraExtra(Double valorHoraExtra) {
		this.valorHoraExtra = valorHoraExtra;
	}
	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}
	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/**
	 * @return the nivel
	 */
	public Nivel getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return the grupoAcesso
	 */
	public GrupoAcesso getGrupoAcesso() {
		return grupoAcesso;
	}
	/**
	 * @param grupoAcesso the grupoAcesso to set
	 */
	public void setGrupoAcesso(GrupoAcesso grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * @return the listaApontamentoHoras
	 */
	public List<ApontamentoHoras> getListaApontamentoHoras() {
		return listaApontamentoHoras;
	}
	/**
	 * @param listaApontamentoHoras the listaApontamentoHoras to set
	 */
	public void setListaApontamentoHoras(List<ApontamentoHoras> listaApontamentoHoras) {
		this.listaApontamentoHoras = listaApontamentoHoras;
	}


}
