package sisgp.dominio;

import java.util.List;

public class Atividade extends EntidadeDominio {
	
	private String codigo;
	private String nome;
	private String descricao;
	
	private Projeto projeto;
	private StatusAtividade statusAtividade;
	private StatusSistemaAtividade stSistAtividade;
	
	private List<SubAtividade> listaSubAtividade;
	
	public Atividade() {}
	public Atividade(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public Atividade(Integer id) {
		this.id = id;
	}
	public Atividade(String nome) {
		this.nome = nome;
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the projeto
	 */
	public Projeto getProjeto() {
		return projeto;
	}
	/**
	 * @param projeto the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	/**
	 * @return the statusAtividade
	 */
	public StatusAtividade getStatusAtividade() {
		return statusAtividade;
	}
	/**
	 * @param statusAtividade the statusAtividade to set
	 */
	public void setStatusAtividade(StatusAtividade statusAtividade) {
		this.statusAtividade = statusAtividade;
	}
	/**
	 * @return the stSistAtividade
	 */
	public StatusSistemaAtividade getStSistAtividade() {
		return stSistAtividade;
	}
	/**
	 * @param stSistAtividade the stSistAtividade to set
	 */
	public void setStSistAtividade(StatusSistemaAtividade stSistAtividade) {
		this.stSistAtividade = stSistAtividade;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the listaSubAtividade
	 */
	public List<SubAtividade> getListaSubAtividade() {
		return listaSubAtividade;
	}
	/**
	 * @param listaSubAtividade the listaSubAtividade to set
	 */
	public void setListaSubAtividade(List<SubAtividade> listaSubAtividade) {
		this.listaSubAtividade = listaSubAtividade;
	}
	

}
