package sisgp.dominio;

import java.util.List;

public class Recurso extends EntidadeDominio {

	private String descricao;
	private Integer qtdEstoque;
	private String Observacao;
	
	//private Projeto projeto;
	//private Integer quantidade;
	
	private List<ControleEstoque> controleEstoque;
	
	public Recurso(){}
	public Recurso(Integer id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	public Recurso(Integer id){
		this.id = id;
	}
	public Recurso(String descricao){
		this.descricao = descricao;
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
	 * @return the qtdEstoque
	 */
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	/**
	 * @param qtdEstoque the qtdEstoque to set
	 */
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return Observacao;
	}
	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		Observacao = observacao;
	}
	/**
	 * @return the controleEstoque
	 */
	public List<ControleEstoque> getControleEstoque() {
		return controleEstoque;
	}
	/**
	 * @param controleEstoque the controleEstoque to set
	 */
	public void setControleEstoque(List<ControleEstoque> controleEstoque) {
		this.controleEstoque = controleEstoque;
	}
	
	
}
